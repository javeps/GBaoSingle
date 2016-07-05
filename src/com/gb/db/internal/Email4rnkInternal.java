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


//gbosng_design - email4rnk
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Email4rnkInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Email4rnkInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Email4rnkInternal(){}

    public static Email4rnkDAO DAO(){
        return Email4rnkEntity.Email4rnkDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Email4rnk[MAX];
    }
    private static Email4rnk[] FIXED = new Email4rnk[MAX];
    public static final Map<Integer, Email4rnk> vars = newSortedMap();

    private static final List<Email4rnk> fixedCache = newList();

    public static void put(Email4rnk email4rnk, boolean force){
        if(email4rnk == null || email4rnk.id <= 0) return ;

        int id = email4rnk.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(email4rnk))
                	FIXED[id - 1] = email4rnk;
                if (!fixedCache.contains(email4rnk))
                	fixedCache.add(email4rnk);
            }
        } else {
            vars.put(id, email4rnk);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        FIXED = new Email4rnk[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Email4rnkDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Email4rnkDAO DAO, String TABLENAME2){
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

    public static void relocate(Email4rnkDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Email4rnkDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Email4rnkDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Email4rnkDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Email4rnkDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Email4rnkDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Email4rnkDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Email4rnkDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Email4rnkDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Email4rnkDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Email4rnkDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Email4rnkDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Email4rnkDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Email4rnk[maxId(DAO)];

        List<Email4rnk> email4rnks = DAO.selectAll();
        for (Email4rnk email4rnk : email4rnks) {
            email4rnk.reset();
            put(email4rnk, true);
        }
    }

    public static Map toMap(Email4rnk email4rnk){
        return email4rnk.toMap();
    }

    public static List<Map> toMap(List<Email4rnk> email4rnks){
        List<Map> ret = new Vector<Map>();
        for (Email4rnk email4rnk : email4rnks){
            Map e = email4rnk.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Email4rnk> sortZh(List<Email4rnk> email4rnks, final String key) {
        Collections.sort(email4rnks, new Comparator<Email4rnk>() {
            public int compare(Email4rnk o1, Email4rnk o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return email4rnks;
    }

    public static List<Email4rnk> sort(List<Email4rnk> email4rnks, final String key) {
        Collections.sort(email4rnks, new Comparator<Email4rnk>() {
            public int compare(Email4rnk o1, Email4rnk o2) {
                return o1.compareTo(o2, key);
            }
        });
        return email4rnks;
    }

    public static List<Email4rnk> sort(List<Email4rnk> email4rnks){
        Collections.sort(email4rnks, new Comparator<Email4rnk>(){
            public int compare(Email4rnk o1, Email4rnk o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return email4rnks;
    }

    public static List<Email4rnk> sortReverse(List<Email4rnk> email4rnks){
        Collections.sort(email4rnks, new Comparator<Email4rnk>(){
            public int compare(Email4rnk o1, Email4rnk o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return email4rnks;
    }

    public static Email4rnk insert(Email4rnk email4rnk) {
        Email4rnkDAO DAO = DAO();
        return insert(DAO, email4rnk, DAO.TABLENAME);
    }

    public static Email4rnk insert(Email4rnkDAO DAO, Email4rnk email4rnk) {
        return insert(DAO, email4rnk, DAO.TABLENAME);
    }

    public static Email4rnk insert(Email4rnk email4rnk, String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return insert(DAO, email4rnk, TABLENAME2);
    }

    public static Email4rnk insert(Email4rnkDAO DAO, Email4rnk email4rnk, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(email4rnk, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        email4rnk.id = n;
        if(cacheModel != NO_CACHE) put(email4rnk, true);

        return email4rnk;
    }

    public static Email4rnk asyncInsert(Email4rnk email4rnk) {
        Email4rnkDAO DAO = DAO();
        return asyncInsert(DAO, email4rnk, DAO.TABLENAME);
    }
    public static Email4rnk asyncInsert(Email4rnkDAO DAO, Email4rnk email4rnk) {
        return asyncInsert(DAO, email4rnk, DAO.TABLENAME);
    }
    public static Email4rnk asyncInsert(Email4rnk email4rnk, String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return asyncInsert(DAO, email4rnk, TABLENAME2);
    }
    public static Email4rnk asyncInsert(Email4rnkDAO DAO, Email4rnk email4rnk, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(email4rnk, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        email4rnk.id = n;
        if(cacheModel != NO_CACHE) put(email4rnk, true);
        return email4rnk;
    }
    public static Email4rnk insert2(Email4rnk email4rnk) {
        Email4rnkDAO DAO = DAO();
        return insert2(DAO, email4rnk, DAO.TABLENAME);
    }

    public static Email4rnk insert2(Email4rnkDAO DAO, Email4rnk email4rnk) {
        return insert2(DAO, email4rnk, DAO.TABLENAME);
    }

    public static Email4rnk insert2(Email4rnk email4rnk, String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return insert2(DAO, email4rnk, TABLENAME2);
    }

    public static Email4rnk insert2(Email4rnkDAO DAO, Email4rnk email4rnk, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(email4rnk, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        email4rnk.id = n;
        if(cacheModel != NO_CACHE) put(email4rnk, true);

        return email4rnk;
    }

    public static Email4rnk asyncInsert2(Email4rnk email4rnk) {
        Email4rnkDAO DAO = DAO();
        return asyncInsert2(DAO, email4rnk, DAO.TABLENAME);
    }
    public static Email4rnk asyncInsert2(Email4rnkDAO DAO, Email4rnk email4rnk) {
        return asyncInsert2(DAO, email4rnk, DAO.TABLENAME);
    }
    public static Email4rnk asyncInsert2(Email4rnk email4rnk, String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return asyncInsert2(DAO, email4rnk, TABLENAME2);
    }
    public static Email4rnk asyncInsert2(Email4rnkDAO DAO, Email4rnk email4rnk, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        email4rnk.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(email4rnk, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(email4rnk, true);
        return email4rnk;
    }
    public static int[] insert(List<Email4rnk> email4rnks) {
        Email4rnkDAO DAO = DAO();
        return insert(DAO, email4rnks, DAO.TABLENAME);
    }

    public static int[] insert(Email4rnkDAO DAO, List<Email4rnk> email4rnks) {
        return insert(DAO, email4rnks, DAO.TABLENAME);
    }

    public static int[] insert(List<Email4rnk> email4rnks, String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return insert(DAO, email4rnks, TABLENAME2);
    }

    public static int[] insert(Email4rnkDAO DAO, List<Email4rnk> email4rnks, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(email4rnks, TABLENAME2);
            int n = 0;
            for(Email4rnk email4rnk : email4rnks){
                email4rnk.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[email4rnks.size()];
        int n = 0;
        for(Email4rnk email4rnk : email4rnks){
            email4rnk = insert(DAO, email4rnk, TABLENAME2);
            ret[n++] = (email4rnk == null) ? 0 : (int)email4rnk.id;
        }
        return ret;
    }

    public static int delete(Email4rnk email4rnk) {
        int id = email4rnk.id;
        Email4rnkDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Email4rnkDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Email4rnkDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Email4rnkDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Email4rnk email4rnk) {
        int id = email4rnk.id;
        Email4rnkDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Email4rnkDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Email4rnkDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Email4rnkDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Email4rnkDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Email4rnkDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Email4rnkDAO DAO, int[] ids,String TABLENAME2) {
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
        Email4rnkDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Email4rnkDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Email4rnkDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Email4rnk> beans) {
        Email4rnkDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Email4rnk> beans, Email4rnkDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Email4rnk> beans, String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Email4rnk> beans, final Email4rnkDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Email4rnk email4rnk : beans){
                int id = email4rnk.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Email4rnk> getAll() {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Email4rnk> getAll(Email4rnkDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Email4rnk> getAll(String TABLENAME2) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Email4rnk> getAll(final Email4rnkDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Email4rnk> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Email4rnk> getNoSortAll() {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Email4rnk> getNoSortAll(Email4rnkDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Email4rnk> getNoSortAll(String TABLENAME2) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Email4rnk> getNoSortAll(final Email4rnkDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Email4rnk> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Email4rnk> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Email4rnk email4rnk = FIXED[i];
                if (email4rnk != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Email4rnk> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Email4rnk> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Email4rnkDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Email4rnkDAO DAO, final String TABLENAME2) {
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
        Email4rnkDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Email4rnkDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Email4rnkDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Email4rnk> getIn(List<Integer> keys) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Email4rnk> getIn(List<Integer> keys, Email4rnkDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Email4rnk> getIn(List<Integer> keys, String TABLENAME2) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Email4rnk> getIn(final List<Integer> keys, final Email4rnkDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Email4rnk> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Email4rnk> getNoSortIn(List<Integer> keys) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Email4rnk> getNoSortIn(List<Integer> keys, Email4rnkDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Email4rnk> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Email4rnk> getNoSortIn(final List<Integer> keys, final Email4rnkDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Email4rnk> result = newList();
            for (int key : keys) {
                Email4rnk email4rnk = getByKeyFromMemory(key);
                if( email4rnk == null ) continue;
                result.add(email4rnk);
            }
            return result;
        }
    }

    public static List<Email4rnk> getLast(int num) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Email4rnk> getLast(Email4rnkDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Email4rnk> getLast(int num, String TABLENAME2) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Email4rnk> getLast(final Email4rnkDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Email4rnk> result = newList();
            List<Email4rnk> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Email4rnk last() {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Email4rnk last(Email4rnkDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Email4rnk last(String TABLENAME2) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Email4rnk last(final Email4rnkDAO DAO, final String TABLENAME2) {
        Email4rnk result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Email4rnkDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Email4rnkDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Email4rnkDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Email4rnk> getGtKey(int id) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Email4rnk> getGtKey(Email4rnkDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Email4rnk> getGtKey(int id, String TABLENAME2) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Email4rnk> getGtKey(final Email4rnkDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Email4rnk> result = newList();
            List<Email4rnk> email4rnks = getAll();
            for (Email4rnk email4rnk : email4rnks) {
                if(email4rnk.id <= id) continue;
                result.add(email4rnk);
            }
            return result;
        }
    }

    public static Email4rnk getByKey(int id) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Email4rnk> asyncGetByKey(final int id) {
        Future<Email4rnk> f = Async.exec(new Callable<Email4rnk>() {
            public Email4rnk call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Email4rnk getByKey(Email4rnkDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Email4rnk getByKey(int id, String TABLENAME2) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Email4rnk getByKey(final Email4rnkDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Email4rnk getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Email4rnk deleteFromMemory(final int id) {
        Email4rnk email4rnk;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            email4rnk = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(email4rnk);
        } else {
            email4rnk = vars.remove(id);
        }
        if(email4rnk == null) return null;

        return email4rnk;
    }

    public static List<Email4rnk> getByPage(int page, int size) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Email4rnk> getByPage(Email4rnkDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Email4rnk> getByPage(int page, int size, String TABLENAME2) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Email4rnk> getByPage(final Email4rnkDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Email4rnk> result = newList();
            List<Email4rnk> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Email4rnkDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Email4rnkDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Email4rnkDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Email4rnk update(Email4rnk email4rnk) {
        Email4rnkDAO DAO = DAO();
        return update(DAO, email4rnk, DAO.TABLENAME);
    }

    public static Email4rnk update(Email4rnkDAO DAO, Email4rnk email4rnk) {
        return update(DAO, email4rnk, DAO.TABLENAME);
    }

    public static Email4rnk update(Email4rnk email4rnk, String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return update(DAO, email4rnk, TABLENAME2);
    }

    public static Email4rnk update(final Email4rnkDAO DAO, final Email4rnk email4rnk,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(email4rnk, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(email4rnk, TABLENAME2);
            if(n == -1) 
                return email4rnk;
            else if(n <= 0) 
                return null;
        }
        return email4rnk;
    }

    public static Email4rnk asyncUpdate(Email4rnk email4rnk) {
        Email4rnkDAO DAO = DAO();
        return asyncUpdate(DAO, email4rnk, DAO.TABLENAME);
    }

    public static Email4rnk asyncUpdate(Email4rnkDAO DAO, Email4rnk email4rnk) {
        return asyncUpdate(DAO, email4rnk, DAO.TABLENAME);
    }

    public static Email4rnk asyncUpdate(Email4rnk email4rnk, String TABLENAME2) {
        Email4rnkDAO DAO = DAO();
        return asyncUpdate(DAO, email4rnk, TABLENAME2);
    }

    public static Email4rnk asyncUpdate(final Email4rnkDAO DAO, final Email4rnk email4rnk,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(email4rnk, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(email4rnk, TABLENAME2);
        }
        return email4rnk;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Email4rnkDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Email4rnkDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Email4rnkDAO DAO, final String TABLENAME2) {
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

        List<Email4rnk> email4rnks = getAll();
        for (Email4rnk email4rnk : email4rnks) {
            int n = DAO.insert2(email4rnk, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
