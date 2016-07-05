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


//gbosng_design - logs4rnk
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Logs4rnkInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Logs4rnkInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Logs4rnkInternal(){}

    public static Logs4rnkDAO DAO(){
        return Logs4rnkEntity.Logs4rnkDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Logs4rnk[MAX];
    }
    private static Logs4rnk[] FIXED = new Logs4rnk[MAX];
    public static final Map<Integer, Logs4rnk> vars = newSortedMap();

    private static final List<Logs4rnk> fixedCache = newList();

    public static void put(Logs4rnk logs4rnk, boolean force){
        if(logs4rnk == null || logs4rnk.id <= 0) return ;

        int id = logs4rnk.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(logs4rnk))
                	FIXED[id - 1] = logs4rnk;
                if (!fixedCache.contains(logs4rnk))
                	fixedCache.add(logs4rnk);
            }
        } else {
            vars.put(id, logs4rnk);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        FIXED = new Logs4rnk[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Logs4rnkDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Logs4rnkDAO DAO, String TABLENAME2){
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

    public static void relocate(Logs4rnkDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Logs4rnkDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Logs4rnkDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Logs4rnkDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Logs4rnkDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Logs4rnkDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Logs4rnkDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Logs4rnkDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Logs4rnkDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Logs4rnkDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Logs4rnkDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Logs4rnkDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Logs4rnkDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Logs4rnk[maxId(DAO)];

        List<Logs4rnk> logs4rnks = DAO.selectAll();
        for (Logs4rnk logs4rnk : logs4rnks) {
            logs4rnk.reset();
            put(logs4rnk, true);
        }
    }

    public static Map toMap(Logs4rnk logs4rnk){
        return logs4rnk.toMap();
    }

    public static List<Map> toMap(List<Logs4rnk> logs4rnks){
        List<Map> ret = new Vector<Map>();
        for (Logs4rnk logs4rnk : logs4rnks){
            Map e = logs4rnk.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Logs4rnk> sortZh(List<Logs4rnk> logs4rnks, final String key) {
        Collections.sort(logs4rnks, new Comparator<Logs4rnk>() {
            public int compare(Logs4rnk o1, Logs4rnk o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return logs4rnks;
    }

    public static List<Logs4rnk> sort(List<Logs4rnk> logs4rnks, final String key) {
        Collections.sort(logs4rnks, new Comparator<Logs4rnk>() {
            public int compare(Logs4rnk o1, Logs4rnk o2) {
                return o1.compareTo(o2, key);
            }
        });
        return logs4rnks;
    }

    public static List<Logs4rnk> sort(List<Logs4rnk> logs4rnks){
        Collections.sort(logs4rnks, new Comparator<Logs4rnk>(){
            public int compare(Logs4rnk o1, Logs4rnk o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return logs4rnks;
    }

    public static List<Logs4rnk> sortReverse(List<Logs4rnk> logs4rnks){
        Collections.sort(logs4rnks, new Comparator<Logs4rnk>(){
            public int compare(Logs4rnk o1, Logs4rnk o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return logs4rnks;
    }

    public static Logs4rnk insert(Logs4rnk logs4rnk) {
        Logs4rnkDAO DAO = DAO();
        return insert(DAO, logs4rnk, DAO.TABLENAME);
    }

    public static Logs4rnk insert(Logs4rnkDAO DAO, Logs4rnk logs4rnk) {
        return insert(DAO, logs4rnk, DAO.TABLENAME);
    }

    public static Logs4rnk insert(Logs4rnk logs4rnk, String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return insert(DAO, logs4rnk, TABLENAME2);
    }

    public static Logs4rnk insert(Logs4rnkDAO DAO, Logs4rnk logs4rnk, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(logs4rnk, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        logs4rnk.id = n;
        if(cacheModel != NO_CACHE) put(logs4rnk, true);

        return logs4rnk;
    }

    public static Logs4rnk asyncInsert(Logs4rnk logs4rnk) {
        Logs4rnkDAO DAO = DAO();
        return asyncInsert(DAO, logs4rnk, DAO.TABLENAME);
    }
    public static Logs4rnk asyncInsert(Logs4rnkDAO DAO, Logs4rnk logs4rnk) {
        return asyncInsert(DAO, logs4rnk, DAO.TABLENAME);
    }
    public static Logs4rnk asyncInsert(Logs4rnk logs4rnk, String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return asyncInsert(DAO, logs4rnk, TABLENAME2);
    }
    public static Logs4rnk asyncInsert(Logs4rnkDAO DAO, Logs4rnk logs4rnk, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(logs4rnk, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        logs4rnk.id = n;
        if(cacheModel != NO_CACHE) put(logs4rnk, true);
        return logs4rnk;
    }
    public static Logs4rnk insert2(Logs4rnk logs4rnk) {
        Logs4rnkDAO DAO = DAO();
        return insert2(DAO, logs4rnk, DAO.TABLENAME);
    }

    public static Logs4rnk insert2(Logs4rnkDAO DAO, Logs4rnk logs4rnk) {
        return insert2(DAO, logs4rnk, DAO.TABLENAME);
    }

    public static Logs4rnk insert2(Logs4rnk logs4rnk, String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return insert2(DAO, logs4rnk, TABLENAME2);
    }

    public static Logs4rnk insert2(Logs4rnkDAO DAO, Logs4rnk logs4rnk, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(logs4rnk, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        logs4rnk.id = n;
        if(cacheModel != NO_CACHE) put(logs4rnk, true);

        return logs4rnk;
    }

    public static Logs4rnk asyncInsert2(Logs4rnk logs4rnk) {
        Logs4rnkDAO DAO = DAO();
        return asyncInsert2(DAO, logs4rnk, DAO.TABLENAME);
    }
    public static Logs4rnk asyncInsert2(Logs4rnkDAO DAO, Logs4rnk logs4rnk) {
        return asyncInsert2(DAO, logs4rnk, DAO.TABLENAME);
    }
    public static Logs4rnk asyncInsert2(Logs4rnk logs4rnk, String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return asyncInsert2(DAO, logs4rnk, TABLENAME2);
    }
    public static Logs4rnk asyncInsert2(Logs4rnkDAO DAO, Logs4rnk logs4rnk, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        logs4rnk.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(logs4rnk, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(logs4rnk, true);
        return logs4rnk;
    }
    public static int[] insert(List<Logs4rnk> logs4rnks) {
        Logs4rnkDAO DAO = DAO();
        return insert(DAO, logs4rnks, DAO.TABLENAME);
    }

    public static int[] insert(Logs4rnkDAO DAO, List<Logs4rnk> logs4rnks) {
        return insert(DAO, logs4rnks, DAO.TABLENAME);
    }

    public static int[] insert(List<Logs4rnk> logs4rnks, String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return insert(DAO, logs4rnks, TABLENAME2);
    }

    public static int[] insert(Logs4rnkDAO DAO, List<Logs4rnk> logs4rnks, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(logs4rnks, TABLENAME2);
            int n = 0;
            for(Logs4rnk logs4rnk : logs4rnks){
                logs4rnk.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[logs4rnks.size()];
        int n = 0;
        for(Logs4rnk logs4rnk : logs4rnks){
            logs4rnk = insert(DAO, logs4rnk, TABLENAME2);
            ret[n++] = (logs4rnk == null) ? 0 : (int)logs4rnk.id;
        }
        return ret;
    }

    public static int delete(Logs4rnk logs4rnk) {
        int id = logs4rnk.id;
        Logs4rnkDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Logs4rnkDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Logs4rnkDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Logs4rnkDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Logs4rnk logs4rnk) {
        int id = logs4rnk.id;
        Logs4rnkDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Logs4rnkDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Logs4rnkDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Logs4rnkDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Logs4rnkDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Logs4rnkDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Logs4rnkDAO DAO, int[] ids,String TABLENAME2) {
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
        Logs4rnkDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Logs4rnkDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Logs4rnkDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Logs4rnk> beans) {
        Logs4rnkDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Logs4rnk> beans, Logs4rnkDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Logs4rnk> beans, String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Logs4rnk> beans, final Logs4rnkDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Logs4rnk logs4rnk : beans){
                int id = logs4rnk.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Logs4rnk> getAll() {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getAll(Logs4rnkDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getAll(String TABLENAME2) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Logs4rnk> getAll(final Logs4rnkDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Logs4rnk> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Logs4rnk> getNoSortAll() {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getNoSortAll(Logs4rnkDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getNoSortAll(String TABLENAME2) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Logs4rnk> getNoSortAll(final Logs4rnkDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Logs4rnk> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Logs4rnk> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Logs4rnk logs4rnk = FIXED[i];
                if (logs4rnk != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Logs4rnk> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Logs4rnk> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Logs4rnkDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Logs4rnkDAO DAO, final String TABLENAME2) {
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
        Logs4rnkDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Logs4rnkDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Logs4rnkDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Logs4rnk> getIn(List<Integer> keys) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getIn(List<Integer> keys, Logs4rnkDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getIn(List<Integer> keys, String TABLENAME2) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Logs4rnk> getIn(final List<Integer> keys, final Logs4rnkDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Logs4rnk> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Logs4rnk> getNoSortIn(List<Integer> keys) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getNoSortIn(List<Integer> keys, Logs4rnkDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Logs4rnk> getNoSortIn(final List<Integer> keys, final Logs4rnkDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Logs4rnk> result = newList();
            for (int key : keys) {
                Logs4rnk logs4rnk = getByKeyFromMemory(key);
                if( logs4rnk == null ) continue;
                result.add(logs4rnk);
            }
            return result;
        }
    }

    public static List<Logs4rnk> getLast(int num) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getLast(Logs4rnkDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getLast(int num, String TABLENAME2) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Logs4rnk> getLast(final Logs4rnkDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Logs4rnk> result = newList();
            List<Logs4rnk> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Logs4rnk last() {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Logs4rnk last(Logs4rnkDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Logs4rnk last(String TABLENAME2) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Logs4rnk last(final Logs4rnkDAO DAO, final String TABLENAME2) {
        Logs4rnk result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Logs4rnkDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Logs4rnkDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Logs4rnkDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Logs4rnk> getGtKey(int id) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getGtKey(Logs4rnkDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getGtKey(int id, String TABLENAME2) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Logs4rnk> getGtKey(final Logs4rnkDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Logs4rnk> result = newList();
            List<Logs4rnk> logs4rnks = getAll();
            for (Logs4rnk logs4rnk : logs4rnks) {
                if(logs4rnk.id <= id) continue;
                result.add(logs4rnk);
            }
            return result;
        }
    }

    public static Logs4rnk getByKey(int id) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Logs4rnk> asyncGetByKey(final int id) {
        Future<Logs4rnk> f = Async.exec(new Callable<Logs4rnk>() {
            public Logs4rnk call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Logs4rnk getByKey(Logs4rnkDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Logs4rnk getByKey(int id, String TABLENAME2) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Logs4rnk getByKey(final Logs4rnkDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Logs4rnk getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Logs4rnk deleteFromMemory(final int id) {
        Logs4rnk logs4rnk;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            logs4rnk = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(logs4rnk);
        } else {
            logs4rnk = vars.remove(id);
        }
        if(logs4rnk == null) return null;

        return logs4rnk;
    }

    public static List<Logs4rnk> getByPage(int page, int size) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getByPage(Logs4rnkDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Logs4rnk> getByPage(int page, int size, String TABLENAME2) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Logs4rnk> getByPage(final Logs4rnkDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Logs4rnk> result = newList();
            List<Logs4rnk> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Logs4rnkDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Logs4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Logs4rnkDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Logs4rnk update(Logs4rnk logs4rnk) {
        Logs4rnkDAO DAO = DAO();
        return update(DAO, logs4rnk, DAO.TABLENAME);
    }

    public static Logs4rnk update(Logs4rnkDAO DAO, Logs4rnk logs4rnk) {
        return update(DAO, logs4rnk, DAO.TABLENAME);
    }

    public static Logs4rnk update(Logs4rnk logs4rnk, String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return update(DAO, logs4rnk, TABLENAME2);
    }

    public static Logs4rnk update(final Logs4rnkDAO DAO, final Logs4rnk logs4rnk,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(logs4rnk, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(logs4rnk, TABLENAME2);
            if(n == -1) 
                return logs4rnk;
            else if(n <= 0) 
                return null;
        }
        return logs4rnk;
    }

    public static Logs4rnk asyncUpdate(Logs4rnk logs4rnk) {
        Logs4rnkDAO DAO = DAO();
        return asyncUpdate(DAO, logs4rnk, DAO.TABLENAME);
    }

    public static Logs4rnk asyncUpdate(Logs4rnkDAO DAO, Logs4rnk logs4rnk) {
        return asyncUpdate(DAO, logs4rnk, DAO.TABLENAME);
    }

    public static Logs4rnk asyncUpdate(Logs4rnk logs4rnk, String TABLENAME2) {
        Logs4rnkDAO DAO = DAO();
        return asyncUpdate(DAO, logs4rnk, TABLENAME2);
    }

    public static Logs4rnk asyncUpdate(final Logs4rnkDAO DAO, final Logs4rnk logs4rnk,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(logs4rnk, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(logs4rnk, TABLENAME2);
        }
        return logs4rnk;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Logs4rnkDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Logs4rnkDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Logs4rnkDAO DAO, final String TABLENAME2) {
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

        List<Logs4rnk> logs4rnks = getAll();
        for (Logs4rnk logs4rnk : logs4rnks) {
            int n = DAO.insert2(logs4rnk, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
