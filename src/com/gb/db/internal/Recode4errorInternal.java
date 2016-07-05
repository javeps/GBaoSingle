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


//gbosng_design - recode4error
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Recode4errorInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Recode4errorInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static long LASTID = 0;
    private static AtomicLong LASTID = new AtomicLong();

    public Recode4errorInternal(){}

    public static Recode4errorDAO DAO(){
        return Recode4errorEntity.Recode4errorDAO();
    }


    public static final Map<Long, Recode4error> vars = newSortedMap();

    private static final List<Recode4error> fixedCache = newList();

    public static void put(Recode4error recode4error, boolean force){
        if(recode4error == null || recode4error.id <= 0) return ;

        long id = recode4error.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0) {
                if (!fixedCache.contains(recode4error))
                	fixedCache.add(recode4error);
            }
        } else {
            vars.put(id, recode4error);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Recode4errorDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Recode4errorDAO DAO, String TABLENAME2){
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

    public static void relocate(Recode4errorDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Recode4errorDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Recode4errorDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Recode4errorDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Recode4errorDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Recode4errorDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Recode4errorDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Recode4errorDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Recode4errorDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Recode4errorDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Recode4errorDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Recode4errorDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Recode4errorDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();

        List<Recode4error> recode4errors = DAO.selectAll();
        for (Recode4error recode4error : recode4errors) {
            recode4error.reset();
            put(recode4error, true);
        }
    }

    public static Map toMap(Recode4error recode4error){
        return recode4error.toMap();
    }

    public static List<Map> toMap(List<Recode4error> recode4errors){
        List<Map> ret = new Vector<Map>();
        for (Recode4error recode4error : recode4errors){
            Map e = recode4error.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Recode4error> sortZh(List<Recode4error> recode4errors, final String key) {
        Collections.sort(recode4errors, new Comparator<Recode4error>() {
            public int compare(Recode4error o1, Recode4error o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return recode4errors;
    }

    public static List<Recode4error> sort(List<Recode4error> recode4errors, final String key) {
        Collections.sort(recode4errors, new Comparator<Recode4error>() {
            public int compare(Recode4error o1, Recode4error o2) {
                return o1.compareTo(o2, key);
            }
        });
        return recode4errors;
    }

    public static List<Recode4error> sort(List<Recode4error> recode4errors){
        Collections.sort(recode4errors, new Comparator<Recode4error>(){
            public int compare(Recode4error o1, Recode4error o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return recode4errors;
    }

    public static List<Recode4error> sortReverse(List<Recode4error> recode4errors){
        Collections.sort(recode4errors, new Comparator<Recode4error>(){
            public int compare(Recode4error o1, Recode4error o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return recode4errors;
    }

    public static Recode4error insert(Recode4error recode4error) {
        Recode4errorDAO DAO = DAO();
        return insert(DAO, recode4error, DAO.TABLENAME);
    }

    public static Recode4error insert(Recode4errorDAO DAO, Recode4error recode4error) {
        return insert(DAO, recode4error, DAO.TABLENAME);
    }

    public static Recode4error insert(Recode4error recode4error, String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return insert(DAO, recode4error, TABLENAME2);
    }

    public static Recode4error insert(Recode4errorDAO DAO, Recode4error recode4error, String TABLENAME2) {
        long n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(recode4error, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        recode4error.id = n;
        if(cacheModel != NO_CACHE) put(recode4error, true);

        return recode4error;
    }

    public static Recode4error asyncInsert(Recode4error recode4error) {
        Recode4errorDAO DAO = DAO();
        return asyncInsert(DAO, recode4error, DAO.TABLENAME);
    }
    public static Recode4error asyncInsert(Recode4errorDAO DAO, Recode4error recode4error) {
        return asyncInsert(DAO, recode4error, DAO.TABLENAME);
    }
    public static Recode4error asyncInsert(Recode4error recode4error, String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return asyncInsert(DAO, recode4error, TABLENAME2);
    }
    public static Recode4error asyncInsert(Recode4errorDAO DAO, Recode4error recode4error, String TABLENAME2) {
        long n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(recode4error, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        recode4error.id = n;
        if(cacheModel != NO_CACHE) put(recode4error, true);
        return recode4error;
    }
    public static Recode4error insert2(Recode4error recode4error) {
        Recode4errorDAO DAO = DAO();
        return insert2(DAO, recode4error, DAO.TABLENAME);
    }

    public static Recode4error insert2(Recode4errorDAO DAO, Recode4error recode4error) {
        return insert2(DAO, recode4error, DAO.TABLENAME);
    }

    public static Recode4error insert2(Recode4error recode4error, String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return insert2(DAO, recode4error, TABLENAME2);
    }

    public static Recode4error insert2(Recode4errorDAO DAO, Recode4error recode4error, String TABLENAME2) {
        long n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(recode4error, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        recode4error.id = n;
        if(cacheModel != NO_CACHE) put(recode4error, true);

        return recode4error;
    }

    public static Recode4error asyncInsert2(Recode4error recode4error) {
        Recode4errorDAO DAO = DAO();
        return asyncInsert2(DAO, recode4error, DAO.TABLENAME);
    }
    public static Recode4error asyncInsert2(Recode4errorDAO DAO, Recode4error recode4error) {
        return asyncInsert2(DAO, recode4error, DAO.TABLENAME);
    }
    public static Recode4error asyncInsert2(Recode4error recode4error, String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return asyncInsert2(DAO, recode4error, TABLENAME2);
    }
    public static Recode4error asyncInsert2(Recode4errorDAO DAO, Recode4error recode4error, String TABLENAME2) {
        long n = LASTID.incrementAndGet();
        recode4error.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(recode4error, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(recode4error, true);
        return recode4error;
    }
    public static int[] insert(List<Recode4error> recode4errors) {
        Recode4errorDAO DAO = DAO();
        return insert(DAO, recode4errors, DAO.TABLENAME);
    }

    public static int[] insert(Recode4errorDAO DAO, List<Recode4error> recode4errors) {
        return insert(DAO, recode4errors, DAO.TABLENAME);
    }

    public static int[] insert(List<Recode4error> recode4errors, String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return insert(DAO, recode4errors, TABLENAME2);
    }

    public static int[] insert(Recode4errorDAO DAO, List<Recode4error> recode4errors, String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(recode4errors, TABLENAME2);
            int n = 0;
            for(Recode4error recode4error : recode4errors){
                recode4error.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[recode4errors.size()];
        int n = 0;
        for(Recode4error recode4error : recode4errors){
            recode4error = insert(DAO, recode4error, TABLENAME2);
            ret[n++] = (recode4error == null) ? 0 : (int)recode4error.id;
        }
        return ret;
    }

    public static int delete(Recode4error recode4error) {
        long id = recode4error.id;
        Recode4errorDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(long id) {
        Recode4errorDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Recode4errorDAO DAO, long id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(long id, String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Recode4errorDAO DAO, long id, String TABLENAME2) {
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

    public static void asyncDelete(Recode4error recode4error) {
        long id = recode4error.id;
        Recode4errorDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(long id) {
        Recode4errorDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Recode4errorDAO DAO, long id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(long id, String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Recode4errorDAO DAO, long id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(long[] ids) {
        Recode4errorDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Recode4errorDAO DAO, long[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(long[] ids,String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Recode4errorDAO DAO, long[] ids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(ids, TABLENAME2);
        }
        int[] ret = new int[ids.length];
        int n = 0;
        for(long id : ids){
            ret[n++] = delete(DAO, id, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Long> keys) {
        Recode4errorDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Long> keys, Recode4errorDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Long> keys, String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Long> keys, final Recode4errorDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Long id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Recode4error> beans) {
        Recode4errorDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Recode4error> beans, Recode4errorDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Recode4error> beans, String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Recode4error> beans, final Recode4errorDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Recode4error recode4error : beans){
                long id = recode4error.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Recode4error> getAll() {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Recode4error> getAll(Recode4errorDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Recode4error> getAll(String TABLENAME2) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Recode4error> getAll(final Recode4errorDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recode4error> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Recode4error> getNoSortAll() {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Recode4error> getNoSortAll(Recode4errorDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Recode4error> getNoSortAll(String TABLENAME2) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Recode4error> getNoSortAll(final Recode4errorDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Recode4error> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recode4error> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Long> memoryKeys(){
        return vars.keySet();
    }

    public static Collection<Recode4error> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Recode4error> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Long> getPks() {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Long> getPks(Recode4errorDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Long> getPks(String TABLENAME2) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Long> getPks(final Recode4errorDAO DAO, final String TABLENAME2) {
        if ( cacheModel == NO_CACHE) { 
            return DAO.selectPKs(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Long> result = newList();
            result.addAll(memoryKeys());
            return result;
        } else {
            List<Long> result = newList();
            result.addAll(vars.keySet());
            return result;
        }
    }

    public static List<Map> getInIndex() {
        Recode4errorDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Recode4errorDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Recode4errorDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Recode4error> getIn(List<Long> keys) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recode4error> getIn(List<Long> keys, Recode4errorDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recode4error> getIn(List<Long> keys, String TABLENAME2) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Recode4error> getIn(final List<Long> keys, final Recode4errorDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recode4error> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Recode4error> getNoSortIn(List<Long> keys) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recode4error> getNoSortIn(List<Long> keys, Recode4errorDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recode4error> getNoSortIn(List<Long> keys, String TABLENAME2) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Recode4error> getNoSortIn(final List<Long> keys, final Recode4errorDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Recode4error> result = newList();
            for (long key : keys) {
                Recode4error recode4error = getByKeyFromMemory(key);
                if( recode4error == null ) continue;
                result.add(recode4error);
            }
            return result;
        }
    }

    public static List<Recode4error> getLast(int num) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Recode4error> getLast(Recode4errorDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Recode4error> getLast(int num, String TABLENAME2) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Recode4error> getLast(final Recode4errorDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Recode4error> result = newList();
            List<Recode4error> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Recode4error last() {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Recode4error last(Recode4errorDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Recode4error last(String TABLENAME2) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Recode4error last(final Recode4errorDAO DAO, final String TABLENAME2) {
        Recode4error result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            long id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static long maxId() {
        Recode4errorDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static long maxId(Recode4errorDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static long maxId(String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static long maxId(final Recode4errorDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        long id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Recode4error> getGtKey(long id) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Recode4error> getGtKey(Recode4errorDAO DAO, long id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Recode4error> getGtKey(long id, String TABLENAME2) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Recode4error> getGtKey(final Recode4errorDAO DAO, final long id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recode4error> result = newList();
            List<Recode4error> recode4errors = getAll();
            for (Recode4error recode4error : recode4errors) {
                if(recode4error.id <= id) continue;
                result.add(recode4error);
            }
            return result;
        }
    }

    public static Recode4error getByKey(long id) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Recode4error> asyncGetByKey(final long id) {
        Future<Recode4error> f = Async.exec(new Callable<Recode4error>() {
            public Recode4error call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Recode4error getByKey(Recode4errorDAO DAO, long id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Recode4error getByKey(long id, String TABLENAME2) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Recode4error getByKey(final Recode4errorDAO DAO, final long id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Recode4error getByKeyFromMemory(final long id) {
        if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Recode4error deleteFromMemory(final long id) {
        Recode4error recode4error;
            recode4error = vars.remove(id);
        if(recode4error == null) return null;

        return recode4error;
    }

    public static List<Recode4error> getByPage(int page, int size) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Recode4error> getByPage(Recode4errorDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Recode4error> getByPage(int page, int size, String TABLENAME2) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Recode4error> getByPage(final Recode4errorDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recode4error> result = newList();
            List<Recode4error> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Recode4errorDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Recode4errorDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Recode4errorDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Recode4error update(Recode4error recode4error) {
        Recode4errorDAO DAO = DAO();
        return update(DAO, recode4error, DAO.TABLENAME);
    }

    public static Recode4error update(Recode4errorDAO DAO, Recode4error recode4error) {
        return update(DAO, recode4error, DAO.TABLENAME);
    }

    public static Recode4error update(Recode4error recode4error, String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return update(DAO, recode4error, TABLENAME2);
    }

    public static Recode4error update(final Recode4errorDAO DAO, final Recode4error recode4error,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(recode4error, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(recode4error, TABLENAME2);
            if(n == -1) 
                return recode4error;
            else if(n <= 0) 
                return null;
        }
        return recode4error;
    }

    public static Recode4error asyncUpdate(Recode4error recode4error) {
        Recode4errorDAO DAO = DAO();
        return asyncUpdate(DAO, recode4error, DAO.TABLENAME);
    }

    public static Recode4error asyncUpdate(Recode4errorDAO DAO, Recode4error recode4error) {
        return asyncUpdate(DAO, recode4error, DAO.TABLENAME);
    }

    public static Recode4error asyncUpdate(Recode4error recode4error, String TABLENAME2) {
        Recode4errorDAO DAO = DAO();
        return asyncUpdate(DAO, recode4error, TABLENAME2);
    }

    public static Recode4error asyncUpdate(final Recode4errorDAO DAO, final Recode4error recode4error,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(recode4error, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(recode4error, TABLENAME2);
        }
        return recode4error;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Recode4errorDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Recode4errorDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Recode4errorDAO DAO, final String TABLENAME2) {
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

        List<Recode4error> recode4errors = getAll();
        for (Recode4error recode4error : recode4errors) {
            int n = DAO.insert2(recode4error, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
