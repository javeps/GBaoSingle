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

import static com.gb.db.bean.Cop4fee.Col;

//gbosng_design - cop4fee
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Cop4feeInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Cop4feeInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Cop4feeInternal(){}

    public static Cop4feeDAO DAO(){
        return Cop4feeEntity.Cop4feeDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Cop4fee[MAX];
    }
    private static Cop4fee[] FIXED = new Cop4fee[MAX];
    public static final Map<Integer, Cop4fee> vars = newSortedMap();
    public static final Map<String, Integer> varsByUnqkey = newSortedMap();
    public static final Map<String, Integer> varsByChnVersion = newSortedMap();

    private static final List<Cop4fee> fixedCache = newList();

    public static void put(Cop4fee cop4fee, boolean force){
        if(cop4fee == null || cop4fee.id <= 0) return ;

        int id = cop4fee.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(cop4fee))
                	FIXED[id - 1] = cop4fee;
                if (!fixedCache.contains(cop4fee))
                	fixedCache.add(cop4fee);
            }
        } else {
            vars.put(id, cop4fee);
        }

        { // 单-唯一索引 remove old index unqkey
          Object ov = cop4fee.oldVal(Col.unqkey);
          if(ov != null)
              varsByUnqkey.remove(ov);
          if(ov != null || force){ // put new index
            String unqkey = cop4fee.getUnqkey();
            varsByUnqkey.put(unqkey, id);
          }
        }

        { // chn
          boolean bChanged = cop4fee.inChanged(Col.chn, Col.version);
          if(bChanged) { // 多-唯一索引 remove old index
            Object vchn = cop4fee.oldOrNew(Col.chn);
            Object vversion = cop4fee.oldOrNew(Col.version);
            String okey = com.bowlong.lang.PStr.b().a(vchn, "-", vversion).e();
            varsByChnVersion.remove(okey);
          }
          if(bChanged || force) { // put new index
              String vchn = cop4fee.getChn();
              String vversion = cop4fee.getVersion();
              String vkey = com.bowlong.lang.PStr.b().a(vchn, "-", vversion).e();
              varsByChnVersion.put(vkey, id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByUnqkey.clear();
        varsByChnVersion.clear();
        FIXED = new Cop4fee[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Cop4feeDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Cop4feeDAO DAO, String TABLENAME2){
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

    public static void relocate(Cop4feeDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Cop4feeDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Cop4feeDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Cop4feeDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Cop4feeDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Cop4feeDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Cop4feeDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Cop4feeDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Cop4feeDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Cop4feeDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Cop4feeDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Cop4feeDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Cop4feeDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Cop4fee[maxId(DAO)];

        List<Cop4fee> cop4fees = DAO.selectAll();
        for (Cop4fee cop4fee : cop4fees) {
            cop4fee.reset();
            put(cop4fee, true);
        }
    }

    public static Map toMap(Cop4fee cop4fee){
        return cop4fee.toMap();
    }

    public static List<Map> toMap(List<Cop4fee> cop4fees){
        List<Map> ret = new Vector<Map>();
        for (Cop4fee cop4fee : cop4fees){
            Map e = cop4fee.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Cop4fee> sortZh(List<Cop4fee> cop4fees, final String key) {
        Collections.sort(cop4fees, new Comparator<Cop4fee>() {
            public int compare(Cop4fee o1, Cop4fee o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return cop4fees;
    }

    public static List<Cop4fee> sort(List<Cop4fee> cop4fees, final String key) {
        Collections.sort(cop4fees, new Comparator<Cop4fee>() {
            public int compare(Cop4fee o1, Cop4fee o2) {
                return o1.compareTo(o2, key);
            }
        });
        return cop4fees;
    }

    public static List<Cop4fee> sort(List<Cop4fee> cop4fees){
        Collections.sort(cop4fees, new Comparator<Cop4fee>(){
            public int compare(Cop4fee o1, Cop4fee o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return cop4fees;
    }

    public static List<Cop4fee> sortReverse(List<Cop4fee> cop4fees){
        Collections.sort(cop4fees, new Comparator<Cop4fee>(){
            public int compare(Cop4fee o1, Cop4fee o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return cop4fees;
    }

    public static List<Cop4fee> sortUnqkey(List<Cop4fee> cop4fees){
        Collections.sort(cop4fees, new Comparator<Cop4fee>(){
            public int compare(Cop4fee o1, Cop4fee o2) {
                Object v1 = o1.getUnqkey();
                Object v2 = o2.getUnqkey();
                return compareTo(v1, v2);
            }
        });
        return cop4fees;
    }

    public static List<Cop4fee> sortUnqkeyRo(List<Cop4fee> cop4fees){
        Collections.sort(cop4fees, new Comparator<Cop4fee>(){
            public int compare(Cop4fee o1, Cop4fee o2) {
                Object v1 = o1.getUnqkey();
                Object v2 = o2.getUnqkey();
                return 0 - compareTo(v1, v2);
            };
        });
        return cop4fees;
    }

    public static Cop4fee insert(Cop4fee cop4fee) {
        Cop4feeDAO DAO = DAO();
        return insert(DAO, cop4fee, DAO.TABLENAME);
    }

    public static Cop4fee insert(Cop4feeDAO DAO, Cop4fee cop4fee) {
        return insert(DAO, cop4fee, DAO.TABLENAME);
    }

    public static Cop4fee insert(Cop4fee cop4fee, String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return insert(DAO, cop4fee, TABLENAME2);
    }

    public static Cop4fee insert(Cop4feeDAO DAO, Cop4fee cop4fee, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(cop4fee, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        cop4fee.id = n;
        if(cacheModel != NO_CACHE) put(cop4fee, true);

        return cop4fee;
    }

    public static Cop4fee asyncInsert(Cop4fee cop4fee) {
        Cop4feeDAO DAO = DAO();
        return asyncInsert(DAO, cop4fee, DAO.TABLENAME);
    }
    public static Cop4fee asyncInsert(Cop4feeDAO DAO, Cop4fee cop4fee) {
        return asyncInsert(DAO, cop4fee, DAO.TABLENAME);
    }
    public static Cop4fee asyncInsert(Cop4fee cop4fee, String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return asyncInsert(DAO, cop4fee, TABLENAME2);
    }
    public static Cop4fee asyncInsert(Cop4feeDAO DAO, Cop4fee cop4fee, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(cop4fee, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        cop4fee.id = n;
        if(cacheModel != NO_CACHE) put(cop4fee, true);
        return cop4fee;
    }
    public static Cop4fee insert2(Cop4fee cop4fee) {
        Cop4feeDAO DAO = DAO();
        return insert2(DAO, cop4fee, DAO.TABLENAME);
    }

    public static Cop4fee insert2(Cop4feeDAO DAO, Cop4fee cop4fee) {
        return insert2(DAO, cop4fee, DAO.TABLENAME);
    }

    public static Cop4fee insert2(Cop4fee cop4fee, String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return insert2(DAO, cop4fee, TABLENAME2);
    }

    public static Cop4fee insert2(Cop4feeDAO DAO, Cop4fee cop4fee, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(cop4fee, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        cop4fee.id = n;
        if(cacheModel != NO_CACHE) put(cop4fee, true);

        return cop4fee;
    }

    public static Cop4fee asyncInsert2(Cop4fee cop4fee) {
        Cop4feeDAO DAO = DAO();
        return asyncInsert2(DAO, cop4fee, DAO.TABLENAME);
    }
    public static Cop4fee asyncInsert2(Cop4feeDAO DAO, Cop4fee cop4fee) {
        return asyncInsert2(DAO, cop4fee, DAO.TABLENAME);
    }
    public static Cop4fee asyncInsert2(Cop4fee cop4fee, String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return asyncInsert2(DAO, cop4fee, TABLENAME2);
    }
    public static Cop4fee asyncInsert2(Cop4feeDAO DAO, Cop4fee cop4fee, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        cop4fee.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(cop4fee, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(cop4fee, true);
        return cop4fee;
    }
    public static int[] insert(List<Cop4fee> cop4fees) {
        Cop4feeDAO DAO = DAO();
        return insert(DAO, cop4fees, DAO.TABLENAME);
    }

    public static int[] insert(Cop4feeDAO DAO, List<Cop4fee> cop4fees) {
        return insert(DAO, cop4fees, DAO.TABLENAME);
    }

    public static int[] insert(List<Cop4fee> cop4fees, String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return insert(DAO, cop4fees, TABLENAME2);
    }

    public static int[] insert(Cop4feeDAO DAO, List<Cop4fee> cop4fees, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(cop4fees, TABLENAME2);
            int n = 0;
            for(Cop4fee cop4fee : cop4fees){
                cop4fee.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[cop4fees.size()];
        int n = 0;
        for(Cop4fee cop4fee : cop4fees){
            cop4fee = insert(DAO, cop4fee, TABLENAME2);
            ret[n++] = (cop4fee == null) ? 0 : (int)cop4fee.id;
        }
        return ret;
    }

    public static int delete(Cop4fee cop4fee) {
        int id = cop4fee.id;
        Cop4feeDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Cop4feeDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Cop4feeDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Cop4feeDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Cop4fee cop4fee) {
        int id = cop4fee.id;
        Cop4feeDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Cop4feeDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Cop4feeDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Cop4feeDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Cop4feeDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Cop4feeDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Cop4feeDAO DAO, int[] ids,String TABLENAME2) {
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
        Cop4feeDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Cop4feeDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Cop4feeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Cop4fee> beans) {
        Cop4feeDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Cop4fee> beans, Cop4feeDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Cop4fee> beans, String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Cop4fee> beans, final Cop4feeDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Cop4fee cop4fee : beans){
                int id = cop4fee.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Cop4fee> getAll() {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Cop4fee> getAll(Cop4feeDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Cop4fee> getAll(String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Cop4fee> getAll(final Cop4feeDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Cop4fee> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Cop4fee> getNoSortAll() {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Cop4fee> getNoSortAll(Cop4feeDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Cop4fee> getNoSortAll(String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Cop4fee> getNoSortAll(final Cop4feeDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Cop4fee> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Cop4fee> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Cop4fee cop4fee = FIXED[i];
                if (cop4fee != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Cop4fee> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Cop4fee> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Cop4feeDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Cop4feeDAO DAO, final String TABLENAME2) {
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
        Cop4feeDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Cop4feeDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Cop4feeDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Cop4fee> getIn(List<Integer> keys) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Cop4fee> getIn(List<Integer> keys, Cop4feeDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Cop4fee> getIn(List<Integer> keys, String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Cop4fee> getIn(final List<Integer> keys, final Cop4feeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Cop4fee> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Cop4fee> getNoSortIn(List<Integer> keys) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Cop4fee> getNoSortIn(List<Integer> keys, Cop4feeDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Cop4fee> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Cop4fee> getNoSortIn(final List<Integer> keys, final Cop4feeDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Cop4fee> result = newList();
            for (int key : keys) {
                Cop4fee cop4fee = getByKeyFromMemory(key);
                if( cop4fee == null ) continue;
                result.add(cop4fee);
            }
            return result;
        }
    }

    public static List<Cop4fee> getLast(int num) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Cop4fee> getLast(Cop4feeDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Cop4fee> getLast(int num, String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Cop4fee> getLast(final Cop4feeDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Cop4fee> result = newList();
            List<Cop4fee> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Cop4fee last() {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Cop4fee last(Cop4feeDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Cop4fee last(String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Cop4fee last(final Cop4feeDAO DAO, final String TABLENAME2) {
        Cop4fee result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Cop4feeDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Cop4feeDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Cop4feeDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Cop4fee> getGtKey(int id) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Cop4fee> getGtKey(Cop4feeDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Cop4fee> getGtKey(int id, String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Cop4fee> getGtKey(final Cop4feeDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Cop4fee> result = newList();
            List<Cop4fee> cop4fees = getAll();
            for (Cop4fee cop4fee : cop4fees) {
                if(cop4fee.id <= id) continue;
                result.add(cop4fee);
            }
            return result;
        }
    }

    public static Cop4fee getByKey(int id) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Cop4fee> asyncGetByKey(final int id) {
        Future<Cop4fee> f = Async.exec(new Callable<Cop4fee>() {
            public Cop4fee call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Cop4fee getByKey(Cop4feeDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Cop4fee getByKey(int id, String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Cop4fee getByKey(final Cop4feeDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Cop4fee getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Cop4fee deleteFromMemory(final int id) {
        Cop4fee cop4fee;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            cop4fee = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(cop4fee);
        } else {
            cop4fee = vars.remove(id);
        }
        if(cop4fee == null) return null;

        String unqkey = cop4fee.getUnqkey();
        varsByUnqkey.remove(unqkey);

        { // chn
            String vchn = cop4fee.getChn();
            String vversion = cop4fee.getVersion();
            String vkey = com.bowlong.lang.PStr.b().a(vchn, "-", vversion).e();
            varsByChnVersion.remove(vkey);
        }

        return cop4fee;
    }

    public static List<Cop4fee> getByPage(int page, int size) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Cop4fee> getByPage(Cop4feeDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Cop4fee> getByPage(int page, int size, String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Cop4fee> getByPage(final Cop4feeDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Cop4fee> result = newList();
            List<Cop4fee> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Cop4feeDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Cop4feeDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Cop4fee getByUnqkey(String unqkey) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static Cop4fee getByUnqkey(Cop4feeDAO DAO, String unqkey) {
        return getByUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static Cop4fee getByUnqkey(String unqkey, String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqkey(DAO, unqkey, TABLENAME2);
    }

    public static Cop4fee getByUnqkey(final Cop4feeDAO DAO, final String unqkey,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUnqkey(unqkey, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByUnqkey.get(unqkey);
            if(id == null) return null;
            Cop4fee result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getUnqkey().equals(unqkey)){
                varsByUnqkey.remove(unqkey);
                return null;
            }
            return result;
        }
    }

    public static int countLikeUnqkey(String unqkey) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static int countLikeUnqkey(Cop4feeDAO DAO, String unqkey) {
        return countLikeUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static int countLikeUnqkey(String unqkey, String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqkey(DAO, unqkey, TABLENAME2);
    }

    public static int countLikeUnqkey(final Cop4feeDAO DAO, final String unqkey,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeUnqkey(unqkey, TABLENAME2);
        }
        List<Cop4fee> cop4fees = getLikeUnqkey(DAO, unqkey, TABLENAME2);
        return cop4fees.size();
    }

    public static List<Cop4fee> getLikeUnqkey(String unqkey) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static List<Cop4fee> getLikeUnqkey(Cop4feeDAO DAO, String unqkey) {
        return getLikeUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static List<Cop4fee> getLikeUnqkey(String unqkey, String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqkey(DAO, unqkey, TABLENAME2);
    }

    public static List<Cop4fee> getLikeUnqkey(final Cop4feeDAO DAO, final String unqkey,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeUnqkey(unqkey, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Cop4fee> result = newList();
            List<Cop4fee> cop4fees = getAll(DAO, TABLENAME2);
            for(Cop4fee e : cop4fees){
                String _var = e.getUnqkey();
                if(_var.indexOf(unqkey) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Cop4fee getByChnVersion(String chn, String version) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByChnVersion(DAO, chn, version, DAO.TABLENAME);
    }

    public static Cop4fee getByChnVersion(Cop4feeDAO DAO, String chn, String version) {
        return getByChnVersion(DAO, chn, version, DAO.TABLENAME);
    }

    public static Cop4fee getByChnVersion(String chn, String version, String TABLENAME2) {
        Cop4feeDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByChnVersion(DAO, chn, version, TABLENAME2);
    }

    public static Cop4fee getByChnVersion(final Cop4feeDAO DAO, String chn, String version,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByChnVersion(chn, version, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            String vkey = chn+"-"+version;
            Integer id = varsByChnVersion.get(vkey);
            if(id == null) return null;
            Cop4fee result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getChn().equals(chn)){
                varsByChnVersion.remove(vkey);
                return null;
            }
            if(!result.getVersion().equals(version)){
                varsByChnVersion.remove(vkey);
                return null;
            }
            return result;
        }
    }

    public static Cop4fee update(Cop4fee cop4fee) {
        Cop4feeDAO DAO = DAO();
        return update(DAO, cop4fee, DAO.TABLENAME);
    }

    public static Cop4fee update(Cop4feeDAO DAO, Cop4fee cop4fee) {
        return update(DAO, cop4fee, DAO.TABLENAME);
    }

    public static Cop4fee update(Cop4fee cop4fee, String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return update(DAO, cop4fee, TABLENAME2);
    }

    public static Cop4fee update(final Cop4feeDAO DAO, final Cop4fee cop4fee,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(cop4fee, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(cop4fee, TABLENAME2);
            if(n == -1) 
                return cop4fee;
            else if(n <= 0) 
                return null;
        }
        return cop4fee;
    }

    public static Cop4fee asyncUpdate(Cop4fee cop4fee) {
        Cop4feeDAO DAO = DAO();
        return asyncUpdate(DAO, cop4fee, DAO.TABLENAME);
    }

    public static Cop4fee asyncUpdate(Cop4feeDAO DAO, Cop4fee cop4fee) {
        return asyncUpdate(DAO, cop4fee, DAO.TABLENAME);
    }

    public static Cop4fee asyncUpdate(Cop4fee cop4fee, String TABLENAME2) {
        Cop4feeDAO DAO = DAO();
        return asyncUpdate(DAO, cop4fee, TABLENAME2);
    }

    public static Cop4fee asyncUpdate(final Cop4feeDAO DAO, final Cop4fee cop4fee,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(cop4fee, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(cop4fee, TABLENAME2);
        }
        return cop4fee;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Cop4feeDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Cop4feeDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Cop4feeDAO DAO, final String TABLENAME2) {
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

        List<Cop4fee> cop4fees = getAll();
        for (Cop4fee cop4fee : cop4fees) {
            int n = DAO.insert2(cop4fee, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
