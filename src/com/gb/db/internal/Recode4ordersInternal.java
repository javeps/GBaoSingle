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

import static com.gb.db.bean.Recode4orders.Col;

//gbosng_design - recode4orders
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class Recode4ordersInternal extends InternalSupport{
    static Log log = LogFactory.getLog(Recode4ordersInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public Recode4ordersInternal(){}

    public static Recode4ordersDAO DAO(){
        return Recode4ordersEntity.Recode4ordersDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Recode4orders[MAX];
    }
    private static Recode4orders[] FIXED = new Recode4orders[MAX];
    public static final Map<Integer, Recode4orders> vars = newSortedMap();
    public static final Map<String, Integer> varsByUnqkey = newSortedMap();
    public static final Map<String, Set<Integer>> varsByChn = newSortedMap();

    private static final List<Recode4orders> fixedCache = newList();

    public static void put(Recode4orders recode4orders, boolean force){
        if(recode4orders == null || recode4orders.id <= 0) return ;

        int id = recode4orders.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(recode4orders))
                	FIXED[id - 1] = recode4orders;
                if (!fixedCache.contains(recode4orders))
                	fixedCache.add(recode4orders);
            }
        } else {
            vars.put(id, recode4orders);
        }

        { // 单-唯一索引 remove old index unqkey
          Object ov = recode4orders.oldVal(Col.unqkey);
          if(ov != null)
              varsByUnqkey.remove(ov);
          if(ov != null || force){ // put new index
            String unqkey = recode4orders.getUnqkey();
            varsByUnqkey.put(unqkey, id);
          }
        }

        { // 单-非唯一索引 remove old index chn
          Object ov = recode4orders.oldVal(Col.chn);
          if(ov != null) {
              String _val = (String) ov;
              Set m1 = varsByChn.get(_val);
              if(m1 != null) {
                  m1.remove(id);
                  if(m1.isEmpty())
                      varsByChn.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            String chn = recode4orders.getChn();
            Set m1 = varsByChn.get(chn);
            if(m1 == null){
                m1 = newSortedSet();
                varsByChn.put(chn, m1);
            }
            m1.add(id);
          }
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        varsByUnqkey.clear();
        varsByChn.clear();
        FIXED = new Recode4orders[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(Recode4ordersDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(Recode4ordersDAO DAO, String TABLENAME2){
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

    public static void relocate(Recode4ordersDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        Recode4ordersDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(Recode4ordersDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        Recode4ordersDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(Recode4ordersDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        Recode4ordersDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(Recode4ordersDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(Recode4ordersDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(Recode4ordersDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(Recode4ordersDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(Recode4ordersDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        Recode4ordersDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(Recode4ordersDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Recode4orders[maxId(DAO)];

        List<Recode4orders> recode4orderss = DAO.selectAll();
        for (Recode4orders recode4orders : recode4orderss) {
            recode4orders.reset();
            put(recode4orders, true);
        }
    }

    public static Map toMap(Recode4orders recode4orders){
        return recode4orders.toMap();
    }

    public static List<Map> toMap(List<Recode4orders> recode4orderss){
        List<Map> ret = new Vector<Map>();
        for (Recode4orders recode4orders : recode4orderss){
            Map e = recode4orders.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Recode4orders> sortZh(List<Recode4orders> recode4orderss, final String key) {
        Collections.sort(recode4orderss, new Comparator<Recode4orders>() {
            public int compare(Recode4orders o1, Recode4orders o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return recode4orderss;
    }

    public static List<Recode4orders> sort(List<Recode4orders> recode4orderss, final String key) {
        Collections.sort(recode4orderss, new Comparator<Recode4orders>() {
            public int compare(Recode4orders o1, Recode4orders o2) {
                return o1.compareTo(o2, key);
            }
        });
        return recode4orderss;
    }

    public static List<Recode4orders> sort(List<Recode4orders> recode4orderss){
        Collections.sort(recode4orderss, new Comparator<Recode4orders>(){
            public int compare(Recode4orders o1, Recode4orders o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return recode4orderss;
    }

    public static List<Recode4orders> sortReverse(List<Recode4orders> recode4orderss){
        Collections.sort(recode4orderss, new Comparator<Recode4orders>(){
            public int compare(Recode4orders o1, Recode4orders o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return recode4orderss;
    }

    public static List<Recode4orders> sortUnqkey(List<Recode4orders> recode4orderss){
        Collections.sort(recode4orderss, new Comparator<Recode4orders>(){
            public int compare(Recode4orders o1, Recode4orders o2) {
                Object v1 = o1.getUnqkey();
                Object v2 = o2.getUnqkey();
                return compareTo(v1, v2);
            }
        });
        return recode4orderss;
    }

    public static List<Recode4orders> sortUnqkeyRo(List<Recode4orders> recode4orderss){
        Collections.sort(recode4orderss, new Comparator<Recode4orders>(){
            public int compare(Recode4orders o1, Recode4orders o2) {
                Object v1 = o1.getUnqkey();
                Object v2 = o2.getUnqkey();
                return 0 - compareTo(v1, v2);
            };
        });
        return recode4orderss;
    }

    public static List<Recode4orders> sortChn(List<Recode4orders> recode4orderss){
        Collections.sort(recode4orderss, new Comparator<Recode4orders>(){
            public int compare(Recode4orders o1, Recode4orders o2) {
                Object v1 = o1.getChn();
                Object v2 = o2.getChn();
                return compareTo(v1, v2);
            }
        });
        return recode4orderss;
    }

    public static List<Recode4orders> sortChnRo(List<Recode4orders> recode4orderss){
        Collections.sort(recode4orderss, new Comparator<Recode4orders>(){
            public int compare(Recode4orders o1, Recode4orders o2) {
                Object v1 = o1.getChn();
                Object v2 = o2.getChn();
                return 0 - compareTo(v1, v2);
            };
        });
        return recode4orderss;
    }

    public static Recode4orders insert(Recode4orders recode4orders) {
        Recode4ordersDAO DAO = DAO();
        return insert(DAO, recode4orders, DAO.TABLENAME);
    }

    public static Recode4orders insert(Recode4ordersDAO DAO, Recode4orders recode4orders) {
        return insert(DAO, recode4orders, DAO.TABLENAME);
    }

    public static Recode4orders insert(Recode4orders recode4orders, String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return insert(DAO, recode4orders, TABLENAME2);
    }

    public static Recode4orders insert(Recode4ordersDAO DAO, Recode4orders recode4orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(recode4orders, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        recode4orders.id = n;
        if(cacheModel != NO_CACHE) put(recode4orders, true);

        return recode4orders;
    }

    public static Recode4orders asyncInsert(Recode4orders recode4orders) {
        Recode4ordersDAO DAO = DAO();
        return asyncInsert(DAO, recode4orders, DAO.TABLENAME);
    }
    public static Recode4orders asyncInsert(Recode4ordersDAO DAO, Recode4orders recode4orders) {
        return asyncInsert(DAO, recode4orders, DAO.TABLENAME);
    }
    public static Recode4orders asyncInsert(Recode4orders recode4orders, String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return asyncInsert(DAO, recode4orders, TABLENAME2);
    }
    public static Recode4orders asyncInsert(Recode4ordersDAO DAO, Recode4orders recode4orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(recode4orders, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        recode4orders.id = n;
        if(cacheModel != NO_CACHE) put(recode4orders, true);
        return recode4orders;
    }
    public static Recode4orders insert2(Recode4orders recode4orders) {
        Recode4ordersDAO DAO = DAO();
        return insert2(DAO, recode4orders, DAO.TABLENAME);
    }

    public static Recode4orders insert2(Recode4ordersDAO DAO, Recode4orders recode4orders) {
        return insert2(DAO, recode4orders, DAO.TABLENAME);
    }

    public static Recode4orders insert2(Recode4orders recode4orders, String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return insert2(DAO, recode4orders, TABLENAME2);
    }

    public static Recode4orders insert2(Recode4ordersDAO DAO, Recode4orders recode4orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(recode4orders, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        recode4orders.id = n;
        if(cacheModel != NO_CACHE) put(recode4orders, true);

        return recode4orders;
    }

    public static Recode4orders asyncInsert2(Recode4orders recode4orders) {
        Recode4ordersDAO DAO = DAO();
        return asyncInsert2(DAO, recode4orders, DAO.TABLENAME);
    }
    public static Recode4orders asyncInsert2(Recode4ordersDAO DAO, Recode4orders recode4orders) {
        return asyncInsert2(DAO, recode4orders, DAO.TABLENAME);
    }
    public static Recode4orders asyncInsert2(Recode4orders recode4orders, String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return asyncInsert2(DAO, recode4orders, TABLENAME2);
    }
    public static Recode4orders asyncInsert2(Recode4ordersDAO DAO, Recode4orders recode4orders, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        recode4orders.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(recode4orders, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(recode4orders, true);
        return recode4orders;
    }
    public static int[] insert(List<Recode4orders> recode4orderss) {
        Recode4ordersDAO DAO = DAO();
        return insert(DAO, recode4orderss, DAO.TABLENAME);
    }

    public static int[] insert(Recode4ordersDAO DAO, List<Recode4orders> recode4orderss) {
        return insert(DAO, recode4orderss, DAO.TABLENAME);
    }

    public static int[] insert(List<Recode4orders> recode4orderss, String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return insert(DAO, recode4orderss, TABLENAME2);
    }

    public static int[] insert(Recode4ordersDAO DAO, List<Recode4orders> recode4orderss, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(recode4orderss, TABLENAME2);
            int n = 0;
            for(Recode4orders recode4orders : recode4orderss){
                recode4orders.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[recode4orderss.size()];
        int n = 0;
        for(Recode4orders recode4orders : recode4orderss){
            recode4orders = insert(DAO, recode4orders, TABLENAME2);
            ret[n++] = (recode4orders == null) ? 0 : (int)recode4orders.id;
        }
        return ret;
    }

    public static int delete(Recode4orders recode4orders) {
        int id = recode4orders.id;
        Recode4ordersDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        Recode4ordersDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(Recode4ordersDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(Recode4ordersDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Recode4orders recode4orders) {
        int id = recode4orders.id;
        Recode4ordersDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        Recode4ordersDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(Recode4ordersDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(Recode4ordersDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        Recode4ordersDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(Recode4ordersDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(Recode4ordersDAO DAO, int[] ids,String TABLENAME2) {
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
        Recode4ordersDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, Recode4ordersDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final Recode4ordersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Recode4orders> beans) {
        Recode4ordersDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Recode4orders> beans, Recode4ordersDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Recode4orders> beans, String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Recode4orders> beans, final Recode4ordersDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Recode4orders recode4orders : beans){
                int id = recode4orders.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Recode4orders> getAll() {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Recode4orders> getAll(Recode4ordersDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Recode4orders> getAll(String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Recode4orders> getAll(final Recode4ordersDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recode4orders> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Recode4orders> getNoSortAll() {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Recode4orders> getNoSortAll(Recode4ordersDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Recode4orders> getNoSortAll(String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Recode4orders> getNoSortAll(final Recode4ordersDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Recode4orders> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recode4orders> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Recode4orders recode4orders = FIXED[i];
                if (recode4orders != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Recode4orders> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Recode4orders> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(Recode4ordersDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final Recode4ordersDAO DAO, final String TABLENAME2) {
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
        Recode4ordersDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(Recode4ordersDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final Recode4ordersDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Recode4orders> getIn(List<Integer> keys) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recode4orders> getIn(List<Integer> keys, Recode4ordersDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recode4orders> getIn(List<Integer> keys, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Recode4orders> getIn(final List<Integer> keys, final Recode4ordersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recode4orders> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Recode4orders> getNoSortIn(List<Integer> keys) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recode4orders> getNoSortIn(List<Integer> keys, Recode4ordersDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Recode4orders> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Recode4orders> getNoSortIn(final List<Integer> keys, final Recode4ordersDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Recode4orders> result = newList();
            for (int key : keys) {
                Recode4orders recode4orders = getByKeyFromMemory(key);
                if( recode4orders == null ) continue;
                result.add(recode4orders);
            }
            return result;
        }
    }

    public static List<Recode4orders> getLast(int num) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Recode4orders> getLast(Recode4ordersDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Recode4orders> getLast(int num, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Recode4orders> getLast(final Recode4ordersDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Recode4orders> result = newList();
            List<Recode4orders> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Recode4orders last() {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Recode4orders last(Recode4ordersDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Recode4orders last(String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Recode4orders last(final Recode4ordersDAO DAO, final String TABLENAME2) {
        Recode4orders result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        Recode4ordersDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(Recode4ordersDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final Recode4ordersDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Recode4orders> getGtKey(int id) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Recode4orders> getGtKey(Recode4ordersDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Recode4orders> getGtKey(int id, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Recode4orders> getGtKey(final Recode4ordersDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recode4orders> result = newList();
            List<Recode4orders> recode4orderss = getAll();
            for (Recode4orders recode4orders : recode4orderss) {
                if(recode4orders.id <= id) continue;
                result.add(recode4orders);
            }
            return result;
        }
    }

    public static Recode4orders getByKey(int id) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Recode4orders> asyncGetByKey(final int id) {
        Future<Recode4orders> f = Async.exec(new Callable<Recode4orders>() {
            public Recode4orders call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Recode4orders getByKey(Recode4ordersDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Recode4orders getByKey(int id, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Recode4orders getByKey(final Recode4ordersDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Recode4orders getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Recode4orders deleteFromMemory(final int id) {
        Recode4orders recode4orders;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            recode4orders = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(recode4orders);
        } else {
            recode4orders = vars.remove(id);
        }
        if(recode4orders == null) return null;

        String unqkey = recode4orders.getUnqkey();
        varsByUnqkey.remove(unqkey);

        String chn = recode4orders.getChn();
        Set m1 = varsByChn.get(chn);
        if(m1 != null) {
            m1.remove(id);
            if(m1.isEmpty())
                varsByChn.remove(chn);
        }

        return recode4orders;
    }

    public static List<Recode4orders> getByPage(int page, int size) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Recode4orders> getByPage(Recode4ordersDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Recode4orders> getByPage(int page, int size, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Recode4orders> getByPage(final Recode4ordersDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Recode4orders> result = newList();
            List<Recode4orders> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(Recode4ordersDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final Recode4ordersDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Recode4orders getByUnqkey(String unqkey) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static Recode4orders getByUnqkey(Recode4ordersDAO DAO, String unqkey) {
        return getByUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static Recode4orders getByUnqkey(String unqkey, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqkey(DAO, unqkey, TABLENAME2);
    }

    public static Recode4orders getByUnqkey(final Recode4ordersDAO DAO, final String unqkey,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUnqkey(unqkey, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer id = varsByUnqkey.get(unqkey);
            if(id == null) return null;
            Recode4orders result = getByKey(DAO, id, TABLENAME2);
            if(result == null) return null;
            if(!result.getUnqkey().equals(unqkey)){
                varsByUnqkey.remove(unqkey);
                return null;
            }
            return result;
        }
    }

    public static int countLikeUnqkey(String unqkey) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static int countLikeUnqkey(Recode4ordersDAO DAO, String unqkey) {
        return countLikeUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static int countLikeUnqkey(String unqkey, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqkey(DAO, unqkey, TABLENAME2);
    }

    public static int countLikeUnqkey(final Recode4ordersDAO DAO, final String unqkey,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeUnqkey(unqkey, TABLENAME2);
        }
        List<Recode4orders> recode4orderss = getLikeUnqkey(DAO, unqkey, TABLENAME2);
        return recode4orderss.size();
    }

    public static List<Recode4orders> getLikeUnqkey(String unqkey) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static List<Recode4orders> getLikeUnqkey(Recode4ordersDAO DAO, String unqkey) {
        return getLikeUnqkey(DAO, unqkey, DAO.TABLENAME);
    }

    public static List<Recode4orders> getLikeUnqkey(String unqkey, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqkey(DAO, unqkey, TABLENAME2);
    }

    public static List<Recode4orders> getLikeUnqkey(final Recode4ordersDAO DAO, final String unqkey,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeUnqkey(unqkey, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Recode4orders> result = newList();
            List<Recode4orders> recode4orderss = getAll(DAO, TABLENAME2);
            for(Recode4orders e : recode4orderss){
                String _var = e.getUnqkey();
                if(_var.indexOf(unqkey) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static int countByChn(String chn) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByChn(DAO, chn, DAO.TABLENAME);
    }

    public static int countByChn(Recode4ordersDAO DAO, String chn) {
        return countByChn(DAO, chn, DAO.TABLENAME);
    }

    public static int countByChn(String chn, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByChn(DAO, chn, TABLENAME2);
    }

    public static int countByChn(final Recode4ordersDAO DAO, final String chn,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByChn(chn, TABLENAME2);
        }
        List<Recode4orders> recode4orderss = getByChn(DAO, chn, TABLENAME2);
        return recode4orderss.size();
    }

    public static List<Recode4orders> getByChn(String chn) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByChn(DAO, chn, DAO.TABLENAME);
    }

    public static List<Recode4orders> getByChn(Recode4ordersDAO DAO, String chn) {
        return getByChn(DAO, chn, DAO.TABLENAME);
    }

    public static List<Recode4orders> getByChn(String chn, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByChn(DAO, chn, TABLENAME2);
    }

    public static List<Recode4orders> getByChn(final Recode4ordersDAO DAO, final String chn,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByChn(chn, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Recode4orders> result = newList();
            Set<Integer> m1 = varsByChn.get(chn);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Recode4orders e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                String _chn = e.getChn();
                if(!_chn.equals(chn)){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countLikeChn(String chn) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeChn(DAO, chn, DAO.TABLENAME);
    }

    public static int countLikeChn(Recode4ordersDAO DAO, String chn) {
        return countLikeChn(DAO, chn, DAO.TABLENAME);
    }

    public static int countLikeChn(String chn, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeChn(DAO, chn, TABLENAME2);
    }

    public static int countLikeChn(final Recode4ordersDAO DAO, final String chn,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeChn(chn, TABLENAME2);
        }
        List<Recode4orders> recode4orderss = getLikeChn(DAO, chn, TABLENAME2);
        return recode4orderss.size();
    }

    public static List<Recode4orders> getLikeChn(String chn) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeChn(DAO, chn, DAO.TABLENAME);
    }

    public static List<Recode4orders> getLikeChn(Recode4ordersDAO DAO, String chn) {
        return getLikeChn(DAO, chn, DAO.TABLENAME);
    }

    public static List<Recode4orders> getLikeChn(String chn, String TABLENAME2) {
        Recode4ordersDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeChn(DAO, chn, TABLENAME2);
    }

    public static List<Recode4orders> getLikeChn(final Recode4ordersDAO DAO, final String chn,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeChn(chn, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Recode4orders> result = newList();
            List<Recode4orders> recode4orderss = getAll(DAO, TABLENAME2);
            for(Recode4orders e : recode4orderss){
                String _var = e.getChn();
                if(_var.indexOf(chn) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Recode4orders update(Recode4orders recode4orders) {
        Recode4ordersDAO DAO = DAO();
        return update(DAO, recode4orders, DAO.TABLENAME);
    }

    public static Recode4orders update(Recode4ordersDAO DAO, Recode4orders recode4orders) {
        return update(DAO, recode4orders, DAO.TABLENAME);
    }

    public static Recode4orders update(Recode4orders recode4orders, String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return update(DAO, recode4orders, TABLENAME2);
    }

    public static Recode4orders update(final Recode4ordersDAO DAO, final Recode4orders recode4orders,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(recode4orders, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(recode4orders, TABLENAME2);
            if(n == -1) 
                return recode4orders;
            else if(n <= 0) 
                return null;
        }
        return recode4orders;
    }

    public static Recode4orders asyncUpdate(Recode4orders recode4orders) {
        Recode4ordersDAO DAO = DAO();
        return asyncUpdate(DAO, recode4orders, DAO.TABLENAME);
    }

    public static Recode4orders asyncUpdate(Recode4ordersDAO DAO, Recode4orders recode4orders) {
        return asyncUpdate(DAO, recode4orders, DAO.TABLENAME);
    }

    public static Recode4orders asyncUpdate(Recode4orders recode4orders, String TABLENAME2) {
        Recode4ordersDAO DAO = DAO();
        return asyncUpdate(DAO, recode4orders, TABLENAME2);
    }

    public static Recode4orders asyncUpdate(final Recode4ordersDAO DAO, final Recode4orders recode4orders,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(recode4orders, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(recode4orders, TABLENAME2);
        }
        return recode4orders;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        Recode4ordersDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(Recode4ordersDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final Recode4ordersDAO DAO, final String TABLENAME2) {
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

        List<Recode4orders> recode4orderss = getAll();
        for (Recode4orders recode4orders : recode4orderss) {
            int n = DAO.insert2(recode4orders, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
