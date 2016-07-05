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

import static com.gb.db.bean.Rankwheel.Col;

//gbosng_design - rankwheel
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class RankwheelInternal extends InternalSupport{
    static Log log = LogFactory.getLog(RankwheelInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public RankwheelInternal(){}

    public static RankwheelDAO DAO(){
        return RankwheelEntity.RankwheelDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Rankwheel[MAX];
    }
    private static Rankwheel[] FIXED = new Rankwheel[MAX];
    public static final Map<Integer, Rankwheel> vars = newSortedMap();
    public static final Map<String, Integer> varsByUnqid = newSortedMap();

    private static final List<Rankwheel> fixedCache = newList();

    public static void put(Rankwheel rankwheel, boolean force){
        if(rankwheel == null || rankwheel.id <= 0) return ;

        int id = rankwheel.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(rankwheel))
                	FIXED[id - 1] = rankwheel;
                if (!fixedCache.contains(rankwheel))
                	fixedCache.add(rankwheel);
            }
        } else {
            vars.put(id, rankwheel);
        }

        { // 单-唯一索引 remove old index unqid
          Object ov = rankwheel.oldVal(Col.unqid);
          if(ov != null)
              varsByUnqid.remove(ov);
          if(ov != null || force){ // put new index
            String unqid = rankwheel.getUnqid();
            varsByUnqid.put(unqid, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByUnqid.clear();
        FIXED = new Rankwheel[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(RankwheelDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(RankwheelDAO DAO, String TABLENAME2){
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

    public static void relocate(RankwheelDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        RankwheelDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(RankwheelDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        RankwheelDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(RankwheelDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        RankwheelDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(RankwheelDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(RankwheelDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(RankwheelDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(RankwheelDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(RankwheelDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        RankwheelDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(RankwheelDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Rankwheel[maxId(DAO)];

        List<Rankwheel> rankwheels = DAO.selectAll();
        for (Rankwheel rankwheel : rankwheels) {
            rankwheel.reset();
            put(rankwheel, true);
        }
    }

    public static Map toMap(Rankwheel rankwheel){
        return rankwheel.toMap();
    }

    public static List<Map> toMap(List<Rankwheel> rankwheels){
        List<Map> ret = new Vector<Map>();
        for (Rankwheel rankwheel : rankwheels){
            Map e = rankwheel.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Rankwheel> sortZh(List<Rankwheel> rankwheels, final String key) {
        Collections.sort(rankwheels, new Comparator<Rankwheel>() {
            public int compare(Rankwheel o1, Rankwheel o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return rankwheels;
    }

    public static List<Rankwheel> sort(List<Rankwheel> rankwheels, final String key) {
        Collections.sort(rankwheels, new Comparator<Rankwheel>() {
            public int compare(Rankwheel o1, Rankwheel o2) {
                return o1.compareTo(o2, key);
            }
        });
        return rankwheels;
    }

    public static List<Rankwheel> sort(List<Rankwheel> rankwheels){
        Collections.sort(rankwheels, new Comparator<Rankwheel>(){
            public int compare(Rankwheel o1, Rankwheel o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return rankwheels;
    }

    public static List<Rankwheel> sortReverse(List<Rankwheel> rankwheels){
        Collections.sort(rankwheels, new Comparator<Rankwheel>(){
            public int compare(Rankwheel o1, Rankwheel o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return rankwheels;
    }

    public static List<Rankwheel> sortUnqid(List<Rankwheel> rankwheels){
        Collections.sort(rankwheels, new Comparator<Rankwheel>(){
            public int compare(Rankwheel o1, Rankwheel o2) {
                Object v1 = o1.getUnqid();
                Object v2 = o2.getUnqid();
                return compareTo(v1, v2);
            }
        });
        return rankwheels;
    }

    public static List<Rankwheel> sortUnqidRo(List<Rankwheel> rankwheels){
        Collections.sort(rankwheels, new Comparator<Rankwheel>(){
            public int compare(Rankwheel o1, Rankwheel o2) {
                Object v1 = o1.getUnqid();
                Object v2 = o2.getUnqid();
                return 0 - compareTo(v1, v2);
            };
        });
        return rankwheels;
    }

    public static Rankwheel insert(Rankwheel rankwheel) {
        RankwheelDAO DAO = DAO();
        return insert(DAO, rankwheel, DAO.TABLENAME);
    }

    public static Rankwheel insert(RankwheelDAO DAO, Rankwheel rankwheel) {
        return insert(DAO, rankwheel, DAO.TABLENAME);
    }

    public static Rankwheel insert(Rankwheel rankwheel, String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return insert(DAO, rankwheel, TABLENAME2);
    }

    public static Rankwheel insert(RankwheelDAO DAO, Rankwheel rankwheel, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(rankwheel, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rankwheel.id = n;
        if(cacheModel != NO_CACHE) put(rankwheel, true);

        return rankwheel;
    }

    public static Rankwheel asyncInsert(Rankwheel rankwheel) {
        RankwheelDAO DAO = DAO();
        return asyncInsert(DAO, rankwheel, DAO.TABLENAME);
    }
    public static Rankwheel asyncInsert(RankwheelDAO DAO, Rankwheel rankwheel) {
        return asyncInsert(DAO, rankwheel, DAO.TABLENAME);
    }
    public static Rankwheel asyncInsert(Rankwheel rankwheel, String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return asyncInsert(DAO, rankwheel, TABLENAME2);
    }
    public static Rankwheel asyncInsert(RankwheelDAO DAO, Rankwheel rankwheel, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(rankwheel, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        rankwheel.id = n;
        if(cacheModel != NO_CACHE) put(rankwheel, true);
        return rankwheel;
    }
    public static Rankwheel insert2(Rankwheel rankwheel) {
        RankwheelDAO DAO = DAO();
        return insert2(DAO, rankwheel, DAO.TABLENAME);
    }

    public static Rankwheel insert2(RankwheelDAO DAO, Rankwheel rankwheel) {
        return insert2(DAO, rankwheel, DAO.TABLENAME);
    }

    public static Rankwheel insert2(Rankwheel rankwheel, String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return insert2(DAO, rankwheel, TABLENAME2);
    }

    public static Rankwheel insert2(RankwheelDAO DAO, Rankwheel rankwheel, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(rankwheel, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        rankwheel.id = n;
        if(cacheModel != NO_CACHE) put(rankwheel, true);

        return rankwheel;
    }

    public static Rankwheel asyncInsert2(Rankwheel rankwheel) {
        RankwheelDAO DAO = DAO();
        return asyncInsert2(DAO, rankwheel, DAO.TABLENAME);
    }
    public static Rankwheel asyncInsert2(RankwheelDAO DAO, Rankwheel rankwheel) {
        return asyncInsert2(DAO, rankwheel, DAO.TABLENAME);
    }
    public static Rankwheel asyncInsert2(Rankwheel rankwheel, String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return asyncInsert2(DAO, rankwheel, TABLENAME2);
    }
    public static Rankwheel asyncInsert2(RankwheelDAO DAO, Rankwheel rankwheel, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        rankwheel.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(rankwheel, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(rankwheel, true);
        return rankwheel;
    }
    public static int[] insert(List<Rankwheel> rankwheels) {
        RankwheelDAO DAO = DAO();
        return insert(DAO, rankwheels, DAO.TABLENAME);
    }

    public static int[] insert(RankwheelDAO DAO, List<Rankwheel> rankwheels) {
        return insert(DAO, rankwheels, DAO.TABLENAME);
    }

    public static int[] insert(List<Rankwheel> rankwheels, String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return insert(DAO, rankwheels, TABLENAME2);
    }

    public static int[] insert(RankwheelDAO DAO, List<Rankwheel> rankwheels, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(rankwheels, TABLENAME2);
            int n = 0;
            for(Rankwheel rankwheel : rankwheels){
                rankwheel.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[rankwheels.size()];
        int n = 0;
        for(Rankwheel rankwheel : rankwheels){
            rankwheel = insert(DAO, rankwheel, TABLENAME2);
            ret[n++] = (rankwheel == null) ? 0 : (int)rankwheel.id;
        }
        return ret;
    }

    public static int delete(Rankwheel rankwheel) {
        int id = rankwheel.id;
        RankwheelDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        RankwheelDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(RankwheelDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(RankwheelDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Rankwheel rankwheel) {
        int id = rankwheel.id;
        RankwheelDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        RankwheelDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(RankwheelDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(RankwheelDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        RankwheelDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(RankwheelDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(RankwheelDAO DAO, int[] ids,String TABLENAME2) {
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
        RankwheelDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, RankwheelDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final RankwheelDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Rankwheel> beans) {
        RankwheelDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rankwheel> beans, RankwheelDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Rankwheel> beans, String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Rankwheel> beans, final RankwheelDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Rankwheel rankwheel : beans){
                int id = rankwheel.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Rankwheel> getAll() {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankwheel> getAll(RankwheelDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankwheel> getAll(String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Rankwheel> getAll(final RankwheelDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankwheel> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rankwheel> getNoSortAll() {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankwheel> getNoSortAll(RankwheelDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Rankwheel> getNoSortAll(String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Rankwheel> getNoSortAll(final RankwheelDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Rankwheel> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankwheel> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Rankwheel rankwheel = FIXED[i];
                if (rankwheel != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Rankwheel> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Rankwheel> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(RankwheelDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final RankwheelDAO DAO, final String TABLENAME2) {
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
        RankwheelDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(RankwheelDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final RankwheelDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Rankwheel> getIn(List<Integer> keys) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankwheel> getIn(List<Integer> keys, RankwheelDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankwheel> getIn(List<Integer> keys, String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Rankwheel> getIn(final List<Integer> keys, final RankwheelDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankwheel> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Rankwheel> getNoSortIn(List<Integer> keys) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankwheel> getNoSortIn(List<Integer> keys, RankwheelDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Rankwheel> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Rankwheel> getNoSortIn(final List<Integer> keys, final RankwheelDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Rankwheel> result = newList();
            for (int key : keys) {
                Rankwheel rankwheel = getByKeyFromMemory(key);
                if( rankwheel == null ) continue;
                result.add(rankwheel);
            }
            return result;
        }
    }

    public static List<Rankwheel> getLast(int num) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rankwheel> getLast(RankwheelDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Rankwheel> getLast(int num, String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Rankwheel> getLast(final RankwheelDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Rankwheel> result = newList();
            List<Rankwheel> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Rankwheel last() {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Rankwheel last(RankwheelDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Rankwheel last(String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Rankwheel last(final RankwheelDAO DAO, final String TABLENAME2) {
        Rankwheel result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        RankwheelDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(RankwheelDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final RankwheelDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Rankwheel> getGtKey(int id) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rankwheel> getGtKey(RankwheelDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Rankwheel> getGtKey(int id, String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Rankwheel> getGtKey(final RankwheelDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankwheel> result = newList();
            List<Rankwheel> rankwheels = getAll();
            for (Rankwheel rankwheel : rankwheels) {
                if(rankwheel.id <= id) continue;
                result.add(rankwheel);
            }
            return result;
        }
    }

    public static Rankwheel getByKey(int id) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Rankwheel> asyncGetByKey(final int id) {
        Future<Rankwheel> f = Async.exec(new Callable<Rankwheel>() {
            public Rankwheel call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Rankwheel getByKey(RankwheelDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Rankwheel getByKey(int id, String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Rankwheel getByKey(final RankwheelDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Rankwheel getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Rankwheel deleteFromMemory(final int id) {
        Rankwheel rankwheel;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            rankwheel = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(rankwheel);
        } else {
            rankwheel = vars.remove(id);
        }
        if(rankwheel == null) return null;

        String unqid = rankwheel.getUnqid();
        varsByUnqid.remove(unqid);

        return rankwheel;
    }

    public static List<Rankwheel> getByPage(int page, int size) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rankwheel> getByPage(RankwheelDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Rankwheel> getByPage(int page, int size, String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Rankwheel> getByPage(final RankwheelDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Rankwheel> result = newList();
            List<Rankwheel> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(RankwheelDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final RankwheelDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Rankwheel getByUnqid(String unqid) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static Rankwheel getByUnqid(RankwheelDAO DAO, String unqid) {
        return getByUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static Rankwheel getByUnqid(String unqid, String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqid(DAO, unqid, TABLENAME2);
    }

    public static Rankwheel getByUnqid(final RankwheelDAO DAO, final String unqid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUnqid(unqid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByUnqid.get(unqid);
            if(id == null) return null;
            Rankwheel result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getUnqid().equals(unqid)){
                varsByUnqid.remove(unqid);
                return null;
            }
            return result;
        }
    }

    public static int countLikeUnqid(String unqid) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static int countLikeUnqid(RankwheelDAO DAO, String unqid) {
        return countLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static int countLikeUnqid(String unqid, String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqid(DAO, unqid, TABLENAME2);
    }

    public static int countLikeUnqid(final RankwheelDAO DAO, final String unqid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeUnqid(unqid, TABLENAME2);
        }
        List<Rankwheel> rankwheels = getLikeUnqid(DAO, unqid, TABLENAME2);
        return rankwheels.size();
    }

    public static List<Rankwheel> getLikeUnqid(String unqid) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static List<Rankwheel> getLikeUnqid(RankwheelDAO DAO, String unqid) {
        return getLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static List<Rankwheel> getLikeUnqid(String unqid, String TABLENAME2) {
        RankwheelDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqid(DAO, unqid, TABLENAME2);
    }

    public static List<Rankwheel> getLikeUnqid(final RankwheelDAO DAO, final String unqid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeUnqid(unqid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Rankwheel> result = newList();
            List<Rankwheel> rankwheels = getAll(DAO, TABLENAME2);
            for(Rankwheel e : rankwheels){
                String _var = e.getUnqid();
                if(_var.indexOf(unqid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Rankwheel update(Rankwheel rankwheel) {
        RankwheelDAO DAO = DAO();
        return update(DAO, rankwheel, DAO.TABLENAME);
    }

    public static Rankwheel update(RankwheelDAO DAO, Rankwheel rankwheel) {
        return update(DAO, rankwheel, DAO.TABLENAME);
    }

    public static Rankwheel update(Rankwheel rankwheel, String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return update(DAO, rankwheel, TABLENAME2);
    }

    public static Rankwheel update(final RankwheelDAO DAO, final Rankwheel rankwheel,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(rankwheel, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(rankwheel, TABLENAME2);
            if(n == -1) 
                return rankwheel;
            else if(n <= 0) 
                return null;
        }
        return rankwheel;
    }

    public static Rankwheel asyncUpdate(Rankwheel rankwheel) {
        RankwheelDAO DAO = DAO();
        return asyncUpdate(DAO, rankwheel, DAO.TABLENAME);
    }

    public static Rankwheel asyncUpdate(RankwheelDAO DAO, Rankwheel rankwheel) {
        return asyncUpdate(DAO, rankwheel, DAO.TABLENAME);
    }

    public static Rankwheel asyncUpdate(Rankwheel rankwheel, String TABLENAME2) {
        RankwheelDAO DAO = DAO();
        return asyncUpdate(DAO, rankwheel, TABLENAME2);
    }

    public static Rankwheel asyncUpdate(final RankwheelDAO DAO, final Rankwheel rankwheel,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(rankwheel, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(rankwheel, TABLENAME2);
        }
        return rankwheel;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        RankwheelDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(RankwheelDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final RankwheelDAO DAO, final String TABLENAME2) {
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

        List<Rankwheel> rankwheels = getAll();
        for (Rankwheel rankwheel : rankwheels) {
            int n = DAO.insert2(rankwheel, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
