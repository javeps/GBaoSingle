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

import static com.gb.db.bean.Rankstars.Col;

//gbosng_design - rankstars
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class RankstarsInternal extends InternalSupport{
    static Log log = LogFactory.getLog(RankstarsInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public RankstarsInternal(){}

    public static RankstarsDAO DAO(){
        return RankstarsEntity.RankstarsDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Rankstars[MAX];
    }
    private static Rankstars[] FIXED = new Rankstars[MAX];
    public static final Map<Integer, Rankstars> vars = newSortedMap();
    public static final Map<String, Integer> varsByUnqid = newSortedMap();

    private static final List<Rankstars> fixedCache = newList();

    public static void put(Rankstars rankstars, boolean force){
        if(rankstars == null || rankstars.id <= 0) return ;

        int id = rankstars.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(rankstars))
                	FIXED[id - 1] = rankstars;
                if (!fixedCache.contains(rankstars))
                	fixedCache.add(rankstars);
            }
        } else {
            vars.put(id, rankstars);
        }

        { // 单-唯一索引 remove old index unqid
          Object ov = rankstars.oldVal(Col.unqid);
          if(ov != null)
              varsByUnqid.remove(ov);
          if(ov != null || force){ // put new index
            String unqid = rankstars.getUnqid();
            varsByUnqid.put(unqid, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByUnqid.clear();
        FIXED = new Rankstars[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(RankstarsDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(RankstarsDAO DAO, String TABLENAME2){
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

    public static void relocate(RankstarsDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        RankstarsDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(RankstarsDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        RankstarsDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(RankstarsDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        RankstarsDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(RankstarsDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(RankstarsDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(RankstarsDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(RankstarsDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(RankstarsDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        RankstarsDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(RankstarsDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Rankstars[maxId(DAO)];

        List<Rankstars> rankstarss = DAO.selectAll();
        for (Rankstars rankstars : rankstarss) {
            rankstars.reset();
            put(rankstars, true);
        }
    }

    public static Map toMap(Rankstars rankstars){
        return rankstars.toMap();
    }

    public static List<Map> toMap(List<Rankstars> rankstarss){
        List<Map> ret = new Vector<Map>();
        for (Rankstars rankstars : rankstarss){
            Map e = rankstars.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Rankstars> sortZh(List<Rankstars> rankstarss, final String key) {
        Collections.sort(rankstarss, new Comparator<Rankstars>() {
            public int compare(Rankstars o1, Rankstars o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return rankstarss;
    }

    public static List<Rankstars> sort(List<Rankstars> rankstarss, final String key) {
        Collections.sort(rankstarss, new Comparator<Rankstars>() {
            public int compare(Rankstars o1, Rankstars o2) {
                return o1.compareTo(o2, key);
            }
        });
        return rankstarss;
    }

    public static List<Rankstars> sort(List<Rankstars> rankstarss){
        Collections.sort(rankstarss, new Comparator<Rankstars>(){
            public int compare(Rankstars o1, Rankstars o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return rankstarss;
    }

    public static List<Rankstars> sortReverse(List<Rankstars> rankstarss){
        Collections.sort(rankstarss, new Comparator<Rankstars>(){
            public int compare(Rankstars o1, Rankstars o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return rankstarss;
    }

    public static List<Rankstars> sortUnqid(List<Rankstars> rankstarss){
        Collections.sort(rankstarss, new Comparator<Rankstars>(){
            public int compare(Rankstars o1, Rankstars o2) {
                Object v1 = o1.getUnqid();
                Object v2 = o2.getUnqid();
                return compareTo(v1, v2);
            }
        });
        return rankstarss;
    }

    public static List<Rankstars> sortUnqidRo(List<Rankstars> rankstarss){
        Collections.sort(rankstarss, new Comparator<Rankstars>(){
            public int compare(Rankstars o1, Rankstars o2) {
                Object v1 = o1.getUnqid();
                Object v2 = o2.getUnqid();
                return 0 - compareTo(v1, v2);
            };
        });
        return rankstarss;
    }

    public static Rankstars insert(Rankstars rankstars) {
        RankstarsDAO DAO = DAO();
        return insert(DAO, rankstars, DAO.TABLENAME);
    }

    public static Rankstars insert(RankstarsDAO DAO, Rankstars rankstars) {
        return insert(DAO, rankstars, DAO.TABLENAME);
    }

    public static Rankstars insert(Rankstars rankstars, String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return insert(DAO, rankstars, TABLENAME2);
    }

    public static Rankstars insert(RankstarsDAO DAO, Rankstars rankstars, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(rankstars, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rankstars.id = n;
        if(cacheModel != NO_CACHE) put(rankstars, true);

        return rankstars;
    }

    public static Rankstars asyncInsert(Rankstars rankstars) {
        RankstarsDAO DAO = DAO();
        return asyncInsert(DAO, rankstars, DAO.TABLENAME);
    }
    public static Rankstars asyncInsert(RankstarsDAO DAO, Rankstars rankstars) {
        return asyncInsert(DAO, rankstars, DAO.TABLENAME);
    }
    public static Rankstars asyncInsert(Rankstars rankstars, String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return asyncInsert(DAO, rankstars, TABLENAME2);
    }
    public static Rankstars asyncInsert(RankstarsDAO DAO, Rankstars rankstars, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(rankstars, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        rankstars.id = n;
        if(cacheModel != NO_CACHE) put(rankstars, true);
        return rankstars;
    }
    public static Rankstars insert2(Rankstars rankstars) {
        RankstarsDAO DAO = DAO();
        return insert2(DAO, rankstars, DAO.TABLENAME);
    }

    public static Rankstars insert2(RankstarsDAO DAO, Rankstars rankstars) {
        return insert2(DAO, rankstars, DAO.TABLENAME);
    }

    public static Rankstars insert2(Rankstars rankstars, String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return insert2(DAO, rankstars, TABLENAME2);
    }

    public static Rankstars insert2(RankstarsDAO DAO, Rankstars rankstars, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(rankstars, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rankstars.id = n;
        if(cacheModel != NO_CACHE) put(rankstars, true);

        return rankstars;
    }

    public static Rankstars asyncInsert2(Rankstars rankstars) {
        RankstarsDAO DAO = DAO();
        return asyncInsert2(DAO, rankstars, DAO.TABLENAME);
    }
    public static Rankstars asyncInsert2(RankstarsDAO DAO, Rankstars rankstars) {
        return asyncInsert2(DAO, rankstars, DAO.TABLENAME);
    }
    public static Rankstars asyncInsert2(Rankstars rankstars, String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return asyncInsert2(DAO, rankstars, TABLENAME2);
    }
    public static Rankstars asyncInsert2(RankstarsDAO DAO, Rankstars rankstars, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        rankstars.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(rankstars, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(rankstars, true);
        return rankstars;
    }
    public static int[] insert(List<Rankstars> rankstarss) {
        RankstarsDAO DAO = DAO();
        return insert(DAO, rankstarss, DAO.TABLENAME);
    }

    public static int[] insert(RankstarsDAO DAO, List<Rankstars> rankstarss) {
        return insert(DAO, rankstarss, DAO.TABLENAME);
    }

    public static int[] insert(List<Rankstars> rankstarss, String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return insert(DAO, rankstarss, TABLENAME2);
    }

    public static int[] insert(RankstarsDAO DAO, List<Rankstars> rankstarss, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(rankstarss, TABLENAME2);
            int n = 0;
            for(Rankstars rankstars : rankstarss){
                rankstars.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[rankstarss.size()];
        int n = 0;
        for(Rankstars rankstars : rankstarss){
            rankstars = insert(DAO, rankstars, TABLENAME2);
            ret[n++] = (rankstars == null) ? 0 : (int)rankstars.id;
        }
        return ret;
    }

    public static int delete(Rankstars rankstars) {
        int id = rankstars.id;
        RankstarsDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        RankstarsDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(RankstarsDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(RankstarsDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Rankstars rankstars) {
        int id = rankstars.id;
        RankstarsDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        RankstarsDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(RankstarsDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(RankstarsDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        RankstarsDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(RankstarsDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(RankstarsDAO DAO, int[] ids,String TABLENAME2) {
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
        RankstarsDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, RankstarsDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final RankstarsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Rankstars> beans) {
        RankstarsDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rankstars> beans, RankstarsDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rankstars> beans, String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Rankstars> beans, final RankstarsDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Rankstars rankstars : beans){
                int id = rankstars.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Rankstars> getAll() {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankstars> getAll(RankstarsDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankstars> getAll(String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Rankstars> getAll(final RankstarsDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankstars> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rankstars> getNoSortAll() {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankstars> getNoSortAll(RankstarsDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankstars> getNoSortAll(String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Rankstars> getNoSortAll(final RankstarsDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Rankstars> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankstars> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Rankstars rankstars = FIXED[i];
                if (rankstars != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Rankstars> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Rankstars> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(RankstarsDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final RankstarsDAO DAO, final String TABLENAME2) {
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
        RankstarsDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(RankstarsDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final RankstarsDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Rankstars> getIn(List<Integer> keys) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankstars> getIn(List<Integer> keys, RankstarsDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankstars> getIn(List<Integer> keys, String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Rankstars> getIn(final List<Integer> keys, final RankstarsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankstars> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rankstars> getNoSortIn(List<Integer> keys) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankstars> getNoSortIn(List<Integer> keys, RankstarsDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankstars> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Rankstars> getNoSortIn(final List<Integer> keys, final RankstarsDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Rankstars> result = newList();
            for (int key : keys) {
                Rankstars rankstars = getByKeyFromMemory(key);
                if( rankstars == null ) continue;
                result.add(rankstars);
            }
            return result;
        }
    }

    public static List<Rankstars> getLast(int num) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rankstars> getLast(RankstarsDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rankstars> getLast(int num, String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Rankstars> getLast(final RankstarsDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Rankstars> result = newList();
            List<Rankstars> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Rankstars last() {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Rankstars last(RankstarsDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Rankstars last(String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Rankstars last(final RankstarsDAO DAO, final String TABLENAME2) {
        Rankstars result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        RankstarsDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(RankstarsDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final RankstarsDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Rankstars> getGtKey(int id) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rankstars> getGtKey(RankstarsDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rankstars> getGtKey(int id, String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Rankstars> getGtKey(final RankstarsDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankstars> result = newList();
            List<Rankstars> rankstarss = getAll();
            for (Rankstars rankstars : rankstarss) {
                if(rankstars.id <= id) continue;
                result.add(rankstars);
            }
            return result;
        }
    }

    public static Rankstars getByKey(int id) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Rankstars> asyncGetByKey(final int id) {
        Future<Rankstars> f = Async.exec(new Callable<Rankstars>() {
            public Rankstars call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Rankstars getByKey(RankstarsDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Rankstars getByKey(int id, String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Rankstars getByKey(final RankstarsDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Rankstars getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Rankstars deleteFromMemory(final int id) {
        Rankstars rankstars;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            rankstars = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(rankstars);
        } else {
            rankstars = vars.remove(id);
        }
        if(rankstars == null) return null;

        String unqid = rankstars.getUnqid();
        varsByUnqid.remove(unqid);

        return rankstars;
    }

    public static List<Rankstars> getByPage(int page, int size) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rankstars> getByPage(RankstarsDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rankstars> getByPage(int page, int size, String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Rankstars> getByPage(final RankstarsDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankstars> result = newList();
            List<Rankstars> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(RankstarsDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final RankstarsDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Rankstars getByUnqid(String unqid) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static Rankstars getByUnqid(RankstarsDAO DAO, String unqid) {
        return getByUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static Rankstars getByUnqid(String unqid, String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqid(DAO, unqid, TABLENAME2);
    }

    public static Rankstars getByUnqid(final RankstarsDAO DAO, final String unqid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUnqid(unqid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByUnqid.get(unqid);
            if(id == null) return null;
            Rankstars result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getUnqid().equals(unqid)){
                varsByUnqid.remove(unqid);
                return null;
            }
            return result;
        }
    }

    public static int countLikeUnqid(String unqid) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static int countLikeUnqid(RankstarsDAO DAO, String unqid) {
        return countLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static int countLikeUnqid(String unqid, String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqid(DAO, unqid, TABLENAME2);
    }

    public static int countLikeUnqid(final RankstarsDAO DAO, final String unqid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeUnqid(unqid, TABLENAME2);
        }
        List<Rankstars> rankstarss = getLikeUnqid(DAO, unqid, TABLENAME2);
        return rankstarss.size();
    }

    public static List<Rankstars> getLikeUnqid(String unqid) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static List<Rankstars> getLikeUnqid(RankstarsDAO DAO, String unqid) {
        return getLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static List<Rankstars> getLikeUnqid(String unqid, String TABLENAME2) {
        RankstarsDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqid(DAO, unqid, TABLENAME2);
    }

    public static List<Rankstars> getLikeUnqid(final RankstarsDAO DAO, final String unqid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeUnqid(unqid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Rankstars> result = newList();
            List<Rankstars> rankstarss = getAll(DAO, TABLENAME2);
            for(Rankstars e : rankstarss){
                String _var = e.getUnqid();
                if(_var.indexOf(unqid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Rankstars update(Rankstars rankstars) {
        RankstarsDAO DAO = DAO();
        return update(DAO, rankstars, DAO.TABLENAME);
    }

    public static Rankstars update(RankstarsDAO DAO, Rankstars rankstars) {
        return update(DAO, rankstars, DAO.TABLENAME);
    }

    public static Rankstars update(Rankstars rankstars, String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return update(DAO, rankstars, TABLENAME2);
    }

    public static Rankstars update(final RankstarsDAO DAO, final Rankstars rankstars,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(rankstars, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(rankstars, TABLENAME2);
            if(n == -1) 
                return rankstars;
            else if(n <= 0) 
                return null;
        }
        return rankstars;
    }

    public static Rankstars asyncUpdate(Rankstars rankstars) {
        RankstarsDAO DAO = DAO();
        return asyncUpdate(DAO, rankstars, DAO.TABLENAME);
    }

    public static Rankstars asyncUpdate(RankstarsDAO DAO, Rankstars rankstars) {
        return asyncUpdate(DAO, rankstars, DAO.TABLENAME);
    }

    public static Rankstars asyncUpdate(Rankstars rankstars, String TABLENAME2) {
        RankstarsDAO DAO = DAO();
        return asyncUpdate(DAO, rankstars, TABLENAME2);
    }

    public static Rankstars asyncUpdate(final RankstarsDAO DAO, final Rankstars rankstars,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(rankstars, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(rankstars, TABLENAME2);
        }
        return rankstars;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        RankstarsDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(RankstarsDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final RankstarsDAO DAO, final String TABLENAME2) {
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

        List<Rankstars> rankstarss = getAll();
        for (Rankstars rankstars : rankstarss) {
            int n = DAO.insert2(rankstars, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
