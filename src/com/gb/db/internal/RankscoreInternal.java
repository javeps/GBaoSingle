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

import static com.gb.db.bean.Rankscore.Col;

//gbosng_design - rankscore
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class RankscoreInternal extends InternalSupport{
    static Log log = LogFactory.getLog(RankscoreInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public RankscoreInternal(){}

    public static RankscoreDAO DAO(){
        return RankscoreEntity.RankscoreDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Rankscore[MAX];
    }
    private static Rankscore[] FIXED = new Rankscore[MAX];
    public static final Map<Integer, Rankscore> vars = newSortedMap();
    public static final Map<String, Integer> varsByUnqid = newSortedMap();

    private static final List<Rankscore> fixedCache = newList();

    public static void put(Rankscore rankscore, boolean force){
        if(rankscore == null || rankscore.id <= 0) return ;

        int id = rankscore.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(rankscore))
                	FIXED[id - 1] = rankscore;
                if (!fixedCache.contains(rankscore))
                	fixedCache.add(rankscore);
            }
        } else {
            vars.put(id, rankscore);
        }

        { // 单-唯一索引 remove old index unqid
          Object ov = rankscore.oldVal(Col.unqid);
          if(ov != null)
              varsByUnqid.remove(ov);
          if(ov != null || force){ // put new index
            String unqid = rankscore.getUnqid();
            varsByUnqid.put(unqid, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByUnqid.clear();
        FIXED = new Rankscore[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(RankscoreDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(RankscoreDAO DAO, String TABLENAME2){
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

    public static void relocate(RankscoreDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        RankscoreDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(RankscoreDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        RankscoreDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(RankscoreDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        RankscoreDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(RankscoreDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(RankscoreDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(RankscoreDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(RankscoreDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(RankscoreDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        RankscoreDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(RankscoreDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Rankscore[maxId(DAO)];

        List<Rankscore> rankscores = DAO.selectAll();
        for (Rankscore rankscore : rankscores) {
            rankscore.reset();
            put(rankscore, true);
        }
    }

    public static Map toMap(Rankscore rankscore){
        return rankscore.toMap();
    }

    public static List<Map> toMap(List<Rankscore> rankscores){
        List<Map> ret = new Vector<Map>();
        for (Rankscore rankscore : rankscores){
            Map e = rankscore.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Rankscore> sortZh(List<Rankscore> rankscores, final String key) {
        Collections.sort(rankscores, new Comparator<Rankscore>() {
            public int compare(Rankscore o1, Rankscore o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return rankscores;
    }

    public static List<Rankscore> sort(List<Rankscore> rankscores, final String key) {
        Collections.sort(rankscores, new Comparator<Rankscore>() {
            public int compare(Rankscore o1, Rankscore o2) {
                return o1.compareTo(o2, key);
            }
        });
        return rankscores;
    }

    public static List<Rankscore> sort(List<Rankscore> rankscores){
        Collections.sort(rankscores, new Comparator<Rankscore>(){
            public int compare(Rankscore o1, Rankscore o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return rankscores;
    }

    public static List<Rankscore> sortReverse(List<Rankscore> rankscores){
        Collections.sort(rankscores, new Comparator<Rankscore>(){
            public int compare(Rankscore o1, Rankscore o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return rankscores;
    }

    public static List<Rankscore> sortUnqid(List<Rankscore> rankscores){
        Collections.sort(rankscores, new Comparator<Rankscore>(){
            public int compare(Rankscore o1, Rankscore o2) {
                Object v1 = o1.getUnqid();
                Object v2 = o2.getUnqid();
                return compareTo(v1, v2);
            }
        });
        return rankscores;
    }

    public static List<Rankscore> sortUnqidRo(List<Rankscore> rankscores){
        Collections.sort(rankscores, new Comparator<Rankscore>(){
            public int compare(Rankscore o1, Rankscore o2) {
                Object v1 = o1.getUnqid();
                Object v2 = o2.getUnqid();
                return 0 - compareTo(v1, v2);
            };
        });
        return rankscores;
    }

    public static Rankscore insert(Rankscore rankscore) {
        RankscoreDAO DAO = DAO();
        return insert(DAO, rankscore, DAO.TABLENAME);
    }

    public static Rankscore insert(RankscoreDAO DAO, Rankscore rankscore) {
        return insert(DAO, rankscore, DAO.TABLENAME);
    }

    public static Rankscore insert(Rankscore rankscore, String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return insert(DAO, rankscore, TABLENAME2);
    }

    public static Rankscore insert(RankscoreDAO DAO, Rankscore rankscore, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(rankscore, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rankscore.id = n;
        if(cacheModel != NO_CACHE) put(rankscore, true);

        return rankscore;
    }

    public static Rankscore asyncInsert(Rankscore rankscore) {
        RankscoreDAO DAO = DAO();
        return asyncInsert(DAO, rankscore, DAO.TABLENAME);
    }
    public static Rankscore asyncInsert(RankscoreDAO DAO, Rankscore rankscore) {
        return asyncInsert(DAO, rankscore, DAO.TABLENAME);
    }
    public static Rankscore asyncInsert(Rankscore rankscore, String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return asyncInsert(DAO, rankscore, TABLENAME2);
    }
    public static Rankscore asyncInsert(RankscoreDAO DAO, Rankscore rankscore, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(rankscore, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        rankscore.id = n;
        if(cacheModel != NO_CACHE) put(rankscore, true);
        return rankscore;
    }
    public static Rankscore insert2(Rankscore rankscore) {
        RankscoreDAO DAO = DAO();
        return insert2(DAO, rankscore, DAO.TABLENAME);
    }

    public static Rankscore insert2(RankscoreDAO DAO, Rankscore rankscore) {
        return insert2(DAO, rankscore, DAO.TABLENAME);
    }

    public static Rankscore insert2(Rankscore rankscore, String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return insert2(DAO, rankscore, TABLENAME2);
    }

    public static Rankscore insert2(RankscoreDAO DAO, Rankscore rankscore, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(rankscore, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rankscore.id = n;
        if(cacheModel != NO_CACHE) put(rankscore, true);

        return rankscore;
    }

    public static Rankscore asyncInsert2(Rankscore rankscore) {
        RankscoreDAO DAO = DAO();
        return asyncInsert2(DAO, rankscore, DAO.TABLENAME);
    }
    public static Rankscore asyncInsert2(RankscoreDAO DAO, Rankscore rankscore) {
        return asyncInsert2(DAO, rankscore, DAO.TABLENAME);
    }
    public static Rankscore asyncInsert2(Rankscore rankscore, String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return asyncInsert2(DAO, rankscore, TABLENAME2);
    }
    public static Rankscore asyncInsert2(RankscoreDAO DAO, Rankscore rankscore, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        rankscore.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(rankscore, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(rankscore, true);
        return rankscore;
    }
    public static int[] insert(List<Rankscore> rankscores) {
        RankscoreDAO DAO = DAO();
        return insert(DAO, rankscores, DAO.TABLENAME);
    }

    public static int[] insert(RankscoreDAO DAO, List<Rankscore> rankscores) {
        return insert(DAO, rankscores, DAO.TABLENAME);
    }

    public static int[] insert(List<Rankscore> rankscores, String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return insert(DAO, rankscores, TABLENAME2);
    }

    public static int[] insert(RankscoreDAO DAO, List<Rankscore> rankscores, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(rankscores, TABLENAME2);
            int n = 0;
            for(Rankscore rankscore : rankscores){
                rankscore.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[rankscores.size()];
        int n = 0;
        for(Rankscore rankscore : rankscores){
            rankscore = insert(DAO, rankscore, TABLENAME2);
            ret[n++] = (rankscore == null) ? 0 : (int)rankscore.id;
        }
        return ret;
    }

    public static int delete(Rankscore rankscore) {
        int id = rankscore.id;
        RankscoreDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        RankscoreDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(RankscoreDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(RankscoreDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Rankscore rankscore) {
        int id = rankscore.id;
        RankscoreDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        RankscoreDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(RankscoreDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(RankscoreDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        RankscoreDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(RankscoreDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(RankscoreDAO DAO, int[] ids,String TABLENAME2) {
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
        RankscoreDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, RankscoreDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final RankscoreDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Rankscore> beans) {
        RankscoreDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rankscore> beans, RankscoreDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rankscore> beans, String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Rankscore> beans, final RankscoreDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Rankscore rankscore : beans){
                int id = rankscore.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Rankscore> getAll() {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankscore> getAll(RankscoreDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankscore> getAll(String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Rankscore> getAll(final RankscoreDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankscore> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rankscore> getNoSortAll() {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankscore> getNoSortAll(RankscoreDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankscore> getNoSortAll(String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Rankscore> getNoSortAll(final RankscoreDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Rankscore> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankscore> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Rankscore rankscore = FIXED[i];
                if (rankscore != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Rankscore> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Rankscore> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(RankscoreDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final RankscoreDAO DAO, final String TABLENAME2) {
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
        RankscoreDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(RankscoreDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final RankscoreDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Rankscore> getIn(List<Integer> keys) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankscore> getIn(List<Integer> keys, RankscoreDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankscore> getIn(List<Integer> keys, String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Rankscore> getIn(final List<Integer> keys, final RankscoreDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankscore> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rankscore> getNoSortIn(List<Integer> keys) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankscore> getNoSortIn(List<Integer> keys, RankscoreDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankscore> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Rankscore> getNoSortIn(final List<Integer> keys, final RankscoreDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Rankscore> result = newList();
            for (int key : keys) {
                Rankscore rankscore = getByKeyFromMemory(key);
                if( rankscore == null ) continue;
                result.add(rankscore);
            }
            return result;
        }
    }

    public static List<Rankscore> getLast(int num) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rankscore> getLast(RankscoreDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rankscore> getLast(int num, String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Rankscore> getLast(final RankscoreDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Rankscore> result = newList();
            List<Rankscore> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Rankscore last() {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Rankscore last(RankscoreDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Rankscore last(String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Rankscore last(final RankscoreDAO DAO, final String TABLENAME2) {
        Rankscore result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        RankscoreDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(RankscoreDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final RankscoreDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Rankscore> getGtKey(int id) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rankscore> getGtKey(RankscoreDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rankscore> getGtKey(int id, String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Rankscore> getGtKey(final RankscoreDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankscore> result = newList();
            List<Rankscore> rankscores = getAll();
            for (Rankscore rankscore : rankscores) {
                if(rankscore.id <= id) continue;
                result.add(rankscore);
            }
            return result;
        }
    }

    public static Rankscore getByKey(int id) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Rankscore> asyncGetByKey(final int id) {
        Future<Rankscore> f = Async.exec(new Callable<Rankscore>() {
            public Rankscore call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Rankscore getByKey(RankscoreDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Rankscore getByKey(int id, String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Rankscore getByKey(final RankscoreDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Rankscore getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Rankscore deleteFromMemory(final int id) {
        Rankscore rankscore;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            rankscore = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(rankscore);
        } else {
            rankscore = vars.remove(id);
        }
        if(rankscore == null) return null;

        String unqid = rankscore.getUnqid();
        varsByUnqid.remove(unqid);

        return rankscore;
    }

    public static List<Rankscore> getByPage(int page, int size) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rankscore> getByPage(RankscoreDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rankscore> getByPage(int page, int size, String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Rankscore> getByPage(final RankscoreDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankscore> result = newList();
            List<Rankscore> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(RankscoreDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final RankscoreDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Rankscore getByUnqid(String unqid) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static Rankscore getByUnqid(RankscoreDAO DAO, String unqid) {
        return getByUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static Rankscore getByUnqid(String unqid, String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqid(DAO, unqid, TABLENAME2);
    }

    public static Rankscore getByUnqid(final RankscoreDAO DAO, final String unqid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUnqid(unqid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByUnqid.get(unqid);
            if(id == null) return null;
            Rankscore result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getUnqid().equals(unqid)){
                varsByUnqid.remove(unqid);
                return null;
            }
            return result;
        }
    }

    public static int countLikeUnqid(String unqid) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static int countLikeUnqid(RankscoreDAO DAO, String unqid) {
        return countLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static int countLikeUnqid(String unqid, String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqid(DAO, unqid, TABLENAME2);
    }

    public static int countLikeUnqid(final RankscoreDAO DAO, final String unqid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeUnqid(unqid, TABLENAME2);
        }
        List<Rankscore> rankscores = getLikeUnqid(DAO, unqid, TABLENAME2);
        return rankscores.size();
    }

    public static List<Rankscore> getLikeUnqid(String unqid) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static List<Rankscore> getLikeUnqid(RankscoreDAO DAO, String unqid) {
        return getLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static List<Rankscore> getLikeUnqid(String unqid, String TABLENAME2) {
        RankscoreDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqid(DAO, unqid, TABLENAME2);
    }

    public static List<Rankscore> getLikeUnqid(final RankscoreDAO DAO, final String unqid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeUnqid(unqid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Rankscore> result = newList();
            List<Rankscore> rankscores = getAll(DAO, TABLENAME2);
            for(Rankscore e : rankscores){
                String _var = e.getUnqid();
                if(_var.indexOf(unqid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Rankscore update(Rankscore rankscore) {
        RankscoreDAO DAO = DAO();
        return update(DAO, rankscore, DAO.TABLENAME);
    }

    public static Rankscore update(RankscoreDAO DAO, Rankscore rankscore) {
        return update(DAO, rankscore, DAO.TABLENAME);
    }

    public static Rankscore update(Rankscore rankscore, String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return update(DAO, rankscore, TABLENAME2);
    }

    public static Rankscore update(final RankscoreDAO DAO, final Rankscore rankscore,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(rankscore, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(rankscore, TABLENAME2);
            if(n == -1) 
                return rankscore;
            else if(n <= 0) 
                return null;
        }
        return rankscore;
    }

    public static Rankscore asyncUpdate(Rankscore rankscore) {
        RankscoreDAO DAO = DAO();
        return asyncUpdate(DAO, rankscore, DAO.TABLENAME);
    }

    public static Rankscore asyncUpdate(RankscoreDAO DAO, Rankscore rankscore) {
        return asyncUpdate(DAO, rankscore, DAO.TABLENAME);
    }

    public static Rankscore asyncUpdate(Rankscore rankscore, String TABLENAME2) {
        RankscoreDAO DAO = DAO();
        return asyncUpdate(DAO, rankscore, TABLENAME2);
    }

    public static Rankscore asyncUpdate(final RankscoreDAO DAO, final Rankscore rankscore,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(rankscore, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(rankscore, TABLENAME2);
        }
        return rankscore;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        RankscoreDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(RankscoreDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final RankscoreDAO DAO, final String TABLENAME2) {
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

        List<Rankscore> rankscores = getAll();
        for (Rankscore rankscore : rankscores) {
            int n = DAO.insert2(rankscore, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
