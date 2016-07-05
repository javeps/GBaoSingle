package com.gb.db.internal;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

import org.apache.commons.logging.*;

import com.bowlong.sql.*;
import com.bowlong.concurrent.async.*;
import static com.bowlong.sql.CacheModel.*;

import com.gb.db.bean.*;
import com.gb.db.dao.*;
import com.gb.db.entity.*;

import static com.gb.db.bean.Ranksword.Col;

//gbosng_design - ranksword
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class RankswordInternal extends InternalSupport{
    static Log log = LogFactory.getLog(RankswordInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public RankswordInternal(){}

    public static RankswordDAO DAO(){
        return RankswordEntity.RankswordDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Ranksword[MAX];
    }
    private static Ranksword[] FIXED = new Ranksword[MAX];
    public static final Map<Integer, Ranksword> vars = newSortedMap();
    public static final Map<String, Integer> varsByUnqid = newSortedMap();

    private static final List<Ranksword> fixedCache = newList();

    public static void put(Ranksword ranksword, boolean force){
        if(ranksword == null || ranksword.id <= 0) return ;

        int id = ranksword.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(ranksword))
                	FIXED[id - 1] = ranksword;
                if (!fixedCache.contains(ranksword))
                	fixedCache.add(ranksword);
            }
        } else {
            vars.put(id, ranksword);
        }

        { // 单-唯一索引 remove old index unqid
          Object ov = ranksword.oldVal(Col.unqid);
          if(ov != null)
              varsByUnqid.remove(ov);
          if(ov != null || force){ // put new index
            String unqid = ranksword.getUnqid();
            varsByUnqid.put(unqid, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByUnqid.clear();
        FIXED = new Ranksword[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(RankswordDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(RankswordDAO DAO, String TABLENAME2){
        if( cacheModel == NO_CACHE ){
            return DAO.count(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            return fixedCache.size();
        } else {  // FULL_CACHE || FULL_MEMORY
            return vars.size();
        }
    }

    public static void relocate(String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static void relocate(RankswordDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        RankswordDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(RankswordDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        RankswordDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(RankswordDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        RankswordDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(RankswordDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        RankswordDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(RankswordDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(RankswordDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        RankswordDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(RankswordDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(RankswordDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        RankswordDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(RankswordDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Ranksword[maxId(DAO)];

        List<Ranksword> rankswords = DAO.selectAll();
        for (Ranksword ranksword : rankswords) {
            ranksword.reset();
            put(ranksword, true);
        }
    }

    public static Map toMap(Ranksword ranksword){
        return ranksword.toMap();
    }

    public static List<Map> toMap(List<Ranksword> rankswords){
        List<Map> ret = new Vector<Map>();
        for (Ranksword ranksword : rankswords){
            Map e = ranksword.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Ranksword> sortZh(List<Ranksword> rankswords, final String key) {
        Collections.sort(rankswords, new Comparator<Ranksword>() {
            public int compare(Ranksword o1, Ranksword o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return rankswords;
    }

    public static List<Ranksword> sort(List<Ranksword> rankswords, final String key) {
        Collections.sort(rankswords, new Comparator<Ranksword>() {
            public int compare(Ranksword o1, Ranksword o2) {
                return o1.compareTo(o2, key);
            }
        });
        return rankswords;
    }

    public static List<Ranksword> sort(List<Ranksword> rankswords){
        Collections.sort(rankswords, new Comparator<Ranksword>(){
            public int compare(Ranksword o1, Ranksword o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return rankswords;
    }

    public static List<Ranksword> sortReverse(List<Ranksword> rankswords){
        Collections.sort(rankswords, new Comparator<Ranksword>(){
            public int compare(Ranksword o1, Ranksword o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return rankswords;
    }

    public static List<Ranksword> sortUnqid(List<Ranksword> rankswords){
        Collections.sort(rankswords, new Comparator<Ranksword>(){
            public int compare(Ranksword o1, Ranksword o2) {
                Object v1 = o1.getUnqid();
                Object v2 = o2.getUnqid();
                return compareTo(v1, v2);
            }
        });
        return rankswords;
    }

    public static List<Ranksword> sortUnqidRo(List<Ranksword> rankswords){
        Collections.sort(rankswords, new Comparator<Ranksword>(){
            public int compare(Ranksword o1, Ranksword o2) {
                Object v1 = o1.getUnqid();
                Object v2 = o2.getUnqid();
                return 0 - compareTo(v1, v2);
            };
        });
        return rankswords;
    }

    public static Ranksword insert(Ranksword ranksword) {
        RankswordDAO DAO = DAO();
        return insert(DAO, ranksword, DAO.TABLENAME);
    }

    public static Ranksword insert(RankswordDAO DAO, Ranksword ranksword) {
        return insert(DAO, ranksword, DAO.TABLENAME);
    }

    public static Ranksword insert(Ranksword ranksword, String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return insert(DAO, ranksword, TABLENAME2);
    }

    public static Ranksword insert(RankswordDAO DAO, Ranksword ranksword, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(ranksword, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        ranksword.id = n;
        if(cacheModel != NO_CACHE) put(ranksword, true);

        return ranksword;
    }

    public static Ranksword asyncInsert(Ranksword ranksword) {
        RankswordDAO DAO = DAO();
        return asyncInsert(DAO, ranksword, DAO.TABLENAME);
    }
    public static Ranksword asyncInsert(RankswordDAO DAO, Ranksword ranksword) {
        return asyncInsert(DAO, ranksword, DAO.TABLENAME);
    }
    public static Ranksword asyncInsert(Ranksword ranksword, String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return asyncInsert(DAO, ranksword, TABLENAME2);
    }
    public static Ranksword asyncInsert(RankswordDAO DAO, Ranksword ranksword, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(ranksword, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        ranksword.id = n;
        if(cacheModel != NO_CACHE) put(ranksword, true);
        return ranksword;
    }
    public static Ranksword insert2(Ranksword ranksword) {
        RankswordDAO DAO = DAO();
        return insert2(DAO, ranksword, DAO.TABLENAME);
    }

    public static Ranksword insert2(RankswordDAO DAO, Ranksword ranksword) {
        return insert2(DAO, ranksword, DAO.TABLENAME);
    }

    public static Ranksword insert2(Ranksword ranksword, String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return insert2(DAO, ranksword, TABLENAME2);
    }

    public static Ranksword insert2(RankswordDAO DAO, Ranksword ranksword, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(ranksword, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        ranksword.id = n;
        if(cacheModel != NO_CACHE) put(ranksword, true);

        return ranksword;
    }

    public static Ranksword asyncInsert2(Ranksword ranksword) {
        RankswordDAO DAO = DAO();
        return asyncInsert2(DAO, ranksword, DAO.TABLENAME);
    }
    public static Ranksword asyncInsert2(RankswordDAO DAO, Ranksword ranksword) {
        return asyncInsert2(DAO, ranksword, DAO.TABLENAME);
    }
    public static Ranksword asyncInsert2(Ranksword ranksword, String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return asyncInsert2(DAO, ranksword, TABLENAME2);
    }
    public static Ranksword asyncInsert2(RankswordDAO DAO, Ranksword ranksword, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        ranksword.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(ranksword, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(ranksword, true);
        return ranksword;
    }
    public static int[] insert(List<Ranksword> rankswords) {
        RankswordDAO DAO = DAO();
        return insert(DAO, rankswords, DAO.TABLENAME);
    }

    public static int[] insert(RankswordDAO DAO, List<Ranksword> rankswords) {
        return insert(DAO, rankswords, DAO.TABLENAME);
    }

    public static int[] insert(List<Ranksword> rankswords, String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return insert(DAO, rankswords, TABLENAME2);
    }

    public static int[] insert(RankswordDAO DAO, List<Ranksword> rankswords, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(rankswords, TABLENAME2);
            int n = 0;
            for(Ranksword ranksword : rankswords){
                ranksword.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[rankswords.size()];
        int n = 0;
        for(Ranksword ranksword : rankswords){
            ranksword = insert(DAO, ranksword, TABLENAME2);
            ret[n++] = (ranksword == null) ? 0 : (int)ranksword.id;
        }
        return ret;
    }

    public static int delete(Ranksword ranksword) {
        int id = ranksword.id;
        RankswordDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        RankswordDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(RankswordDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(RankswordDAO DAO, int id, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(id, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
        return n;
    }

    public static void asyncDelete(Ranksword ranksword) {
        int id = ranksword.id;
        RankswordDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        RankswordDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(RankswordDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        RankswordDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(RankswordDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        RankswordDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(RankswordDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(RankswordDAO DAO, int[] ids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(ids, TABLENAME2);
        }
        int[] ret = new int[ids.length];
        int n = 0;
        for(int id : ids){
            ret[n++] = delete(DAO, id, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        RankswordDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, RankswordDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final RankswordDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Ranksword> beans) {
        RankswordDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Ranksword> beans, RankswordDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Ranksword> beans, String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Ranksword> beans, final RankswordDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Ranksword ranksword : beans){
                int id = ranksword.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Ranksword> getAll() {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Ranksword> getAll(RankswordDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Ranksword> getAll(String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Ranksword> getAll(final RankswordDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Ranksword> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Ranksword> getNoSortAll() {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Ranksword> getNoSortAll(RankswordDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Ranksword> getNoSortAll(String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Ranksword> getNoSortAll(final RankswordDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Ranksword> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Ranksword> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Ranksword ranksword = FIXED[i];
                if (ranksword != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Ranksword> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Ranksword> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(RankswordDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final RankswordDAO DAO, final String TABLENAME2) {
        if ( cacheModel == NO_CACHE) { 
            return DAO.selectPKs(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Integer> result = newList();
            result.addAll(memoryKeys());
            return result;
        } else {
            List<Integer> result = newList();
            result.addAll(vars.keySet());
            return result;
        }
    }

    public static List<Map> getInIndex() {
        RankswordDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(RankswordDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final RankswordDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Ranksword> getIn(List<Integer> keys) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Ranksword> getIn(List<Integer> keys, RankswordDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Ranksword> getIn(List<Integer> keys, String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Ranksword> getIn(final List<Integer> keys, final RankswordDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Ranksword> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Ranksword> getNoSortIn(List<Integer> keys) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Ranksword> getNoSortIn(List<Integer> keys, RankswordDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Ranksword> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Ranksword> getNoSortIn(final List<Integer> keys, final RankswordDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Ranksword> result = newList();
            for (int key : keys) {
                Ranksword ranksword = getByKeyFromMemory(key);
                if( ranksword == null ) continue;
                result.add(ranksword);
            }
            return result;
        }
    }

    public static List<Ranksword> getLast(int num) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Ranksword> getLast(RankswordDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Ranksword> getLast(int num, String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Ranksword> getLast(final RankswordDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Ranksword> result = newList();
            List<Ranksword> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Ranksword last() {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Ranksword last(RankswordDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Ranksword last(String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Ranksword last(final RankswordDAO DAO, final String TABLENAME2) {
        Ranksword result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        RankswordDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(RankswordDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final RankswordDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Ranksword> getGtKey(int id) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Ranksword> getGtKey(RankswordDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Ranksword> getGtKey(int id, String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Ranksword> getGtKey(final RankswordDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Ranksword> result = newList();
            List<Ranksword> rankswords = getAll();
            for (Ranksword ranksword : rankswords) {
                if(ranksword.id <= id) continue;
                result.add(ranksword);
            }
            return result;
        }
    }

    public static Ranksword getByKey(int id) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Ranksword> asyncGetByKey(final int id) {
        Future<Ranksword> f = Async.exec(new Callable<Ranksword>() {
            public Ranksword call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Ranksword getByKey(RankswordDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Ranksword getByKey(int id, String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Ranksword getByKey(final RankswordDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Ranksword getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Ranksword deleteFromMemory(final int id) {
        Ranksword ranksword;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            ranksword = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(ranksword);
        } else {
            ranksword = vars.remove(id);
        }
        if(ranksword == null) return null;

        String unqid = ranksword.getUnqid();
        varsByUnqid.remove(unqid);

        return ranksword;
    }

    public static List<Ranksword> getByPage(int page, int size) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Ranksword> getByPage(RankswordDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Ranksword> getByPage(int page, int size, String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Ranksword> getByPage(final RankswordDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Ranksword> result = newList();
            List<Ranksword> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(RankswordDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final RankswordDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Ranksword getByUnqid(String unqid) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static Ranksword getByUnqid(RankswordDAO DAO, String unqid) {
        return getByUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static Ranksword getByUnqid(String unqid, String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqid(DAO, unqid, TABLENAME2);
    }

    public static Ranksword getByUnqid(final RankswordDAO DAO, final String unqid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUnqid(unqid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByUnqid.get(unqid);
            if(id == null) return null;
            Ranksword result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getUnqid().equals(unqid)){
                varsByUnqid.remove(unqid);
                return null;
            }
            return result;
        }
    }

    public static int countLikeUnqid(String unqid) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static int countLikeUnqid(RankswordDAO DAO, String unqid) {
        return countLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static int countLikeUnqid(String unqid, String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqid(DAO, unqid, TABLENAME2);
    }

    public static int countLikeUnqid(final RankswordDAO DAO, final String unqid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeUnqid(unqid, TABLENAME2);
        }
        List<Ranksword> rankswords = getLikeUnqid(DAO, unqid, TABLENAME2);
        return rankswords.size();
    }

    public static List<Ranksword> getLikeUnqid(String unqid) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static List<Ranksword> getLikeUnqid(RankswordDAO DAO, String unqid) {
        return getLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static List<Ranksword> getLikeUnqid(String unqid, String TABLENAME2) {
        RankswordDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqid(DAO, unqid, TABLENAME2);
    }

    public static List<Ranksword> getLikeUnqid(final RankswordDAO DAO, final String unqid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeUnqid(unqid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Ranksword> result = newList();
            List<Ranksword> rankswords = getAll(DAO, TABLENAME2);
            for(Ranksword e : rankswords){
                String _var = e.getUnqid();
                if(_var.indexOf(unqid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Ranksword update(Ranksword ranksword) {
        RankswordDAO DAO = DAO();
        return update(DAO, ranksword, DAO.TABLENAME);
    }

    public static Ranksword update(RankswordDAO DAO, Ranksword ranksword) {
        return update(DAO, ranksword, DAO.TABLENAME);
    }

    public static Ranksword update(Ranksword ranksword, String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return update(DAO, ranksword, TABLENAME2);
    }

    public static Ranksword update(final RankswordDAO DAO, final Ranksword ranksword,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(ranksword, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(ranksword, TABLENAME2);
            if(n == -1) 
                return ranksword;
            else if(n <= 0) 
                return null;
        }
        return ranksword;
    }

    public static Ranksword asyncUpdate(Ranksword ranksword) {
        RankswordDAO DAO = DAO();
        return asyncUpdate(DAO, ranksword, DAO.TABLENAME);
    }

    public static Ranksword asyncUpdate(RankswordDAO DAO, Ranksword ranksword) {
        return asyncUpdate(DAO, ranksword, DAO.TABLENAME);
    }

    public static Ranksword asyncUpdate(Ranksword ranksword, String TABLENAME2) {
        RankswordDAO DAO = DAO();
        return asyncUpdate(DAO, ranksword, TABLENAME2);
    }

    public static Ranksword asyncUpdate(final RankswordDAO DAO, final Ranksword ranksword,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(ranksword, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(ranksword, TABLENAME2);
        }
        return ranksword;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        RankswordDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(RankswordDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final RankswordDAO DAO, final String TABLENAME2) {
        int result = 0;
        if(cacheModel != FULL_MEMORY)
            return result;
        try {
            DAO.truncate(TABLENAME2);
            DAO.repair(TABLENAME2);
            DAO.optimize(TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }

        List<Ranksword> rankswords = getAll();
        for (Ranksword ranksword : rankswords) {
            int n = DAO.insert2(ranksword, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
