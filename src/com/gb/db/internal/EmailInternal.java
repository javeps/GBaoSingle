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


//gbosng_design - email
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class EmailInternal extends InternalSupport{
    static Log log = LogFactory.getLog(EmailInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public EmailInternal(){}

    public static EmailDAO DAO(){
        return EmailEntity.EmailDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Email[MAX];
    }
    private static Email[] FIXED = new Email[MAX];
    public static final Map<Integer, Email> vars = newSortedMap();

    private static final List<Email> fixedCache = newList();

    public static void put(Email email, boolean force){
        if(email == null || email.id <= 0) return ;

        int id = email.id;
        if (cacheModel == STATIC_CACHE) {
            if (id > 0 && id <= FIXED.length) {
                if (FIXED[id - 1] == null || !FIXED[id - 1].equals(email))
                	FIXED[id - 1] = email;
                if (!fixedCache.contains(email))
                	fixedCache.add(email);
            }
        } else {
            vars.put(id, email);
        }

        // LASTID = id > LASTID ? id : LASTID;
        if (id > LASTID.get()) LASTID.set(id);
    }

    public static void clear(){
        FIXED = new Email[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(EmailDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(EmailDAO DAO, String TABLENAME2){
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

    public static void relocate(EmailDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        EmailDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(EmailDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        EmailDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(EmailDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        EmailDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(EmailDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        EmailDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(EmailDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(EmailDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        EmailDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(EmailDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(EmailDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        EmailDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(EmailDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Email[maxId(DAO)];

        List<Email> emails = DAO.selectAll();
        for (Email email : emails) {
            email.reset();
            put(email, true);
        }
    }

    public static Map toMap(Email email){
        return email.toMap();
    }

    public static List<Map> toMap(List<Email> emails){
        List<Map> ret = new Vector<Map>();
        for (Email email : emails){
            Map e = email.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Email> sortZh(List<Email> emails, final String key) {
        Collections.sort(emails, new Comparator<Email>() {
            public int compare(Email o1, Email o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return emails;
    }

    public static List<Email> sort(List<Email> emails, final String key) {
        Collections.sort(emails, new Comparator<Email>() {
            public int compare(Email o1, Email o2) {
                return o1.compareTo(o2, key);
            }
        });
        return emails;
    }

    public static List<Email> sort(List<Email> emails){
        Collections.sort(emails, new Comparator<Email>(){
            public int compare(Email o1, Email o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return compareTo(v1, v2);
            }
        });
        return emails;
    }

    public static List<Email> sortReverse(List<Email> emails){
        Collections.sort(emails, new Comparator<Email>(){
            public int compare(Email o1, Email o2) {
                Object v1 = o1.id;
                Object v2 = o2.id;
                return 0 - compareTo(v1, v2);
            }
        });
        return emails;
    }

    public static Email insert(Email email) {
        EmailDAO DAO = DAO();
        return insert(DAO, email, DAO.TABLENAME);
    }

    public static Email insert(EmailDAO DAO, Email email) {
        return insert(DAO, email, DAO.TABLENAME);
    }

    public static Email insert(Email email, String TABLENAME2) {
        EmailDAO DAO = DAO();
        return insert(DAO, email, TABLENAME2);
    }

    public static Email insert(EmailDAO DAO, Email email, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(email, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        email.id = n;
        if(cacheModel != NO_CACHE) put(email, true);

        return email;
    }

    public static Email asyncInsert(Email email) {
        EmailDAO DAO = DAO();
        return asyncInsert(DAO, email, DAO.TABLENAME);
    }
    public static Email asyncInsert(EmailDAO DAO, Email email) {
        return asyncInsert(DAO, email, DAO.TABLENAME);
    }
    public static Email asyncInsert(Email email, String TABLENAME2) {
        EmailDAO DAO = DAO();
        return asyncInsert(DAO, email, TABLENAME2);
    }
    public static Email asyncInsert(EmailDAO DAO, Email email, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(email, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        email.id = n;
        if(cacheModel != NO_CACHE) put(email, true);
        return email;
    }
    public static Email insert2(Email email) {
        EmailDAO DAO = DAO();
        return insert2(DAO, email, DAO.TABLENAME);
    }

    public static Email insert2(EmailDAO DAO, Email email) {
        return insert2(DAO, email, DAO.TABLENAME);
    }

    public static Email insert2(Email email, String TABLENAME2) {
        EmailDAO DAO = DAO();
        return insert2(DAO, email, TABLENAME2);
    }

    public static Email insert2(EmailDAO DAO, Email email, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(email, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        email.id = n;
        if(cacheModel != NO_CACHE) put(email, true);

        return email;
    }

    public static Email asyncInsert2(Email email) {
        EmailDAO DAO = DAO();
        return asyncInsert2(DAO, email, DAO.TABLENAME);
    }
    public static Email asyncInsert2(EmailDAO DAO, Email email) {
        return asyncInsert2(DAO, email, DAO.TABLENAME);
    }
    public static Email asyncInsert2(Email email, String TABLENAME2) {
        EmailDAO DAO = DAO();
        return asyncInsert2(DAO, email, TABLENAME2);
    }
    public static Email asyncInsert2(EmailDAO DAO, Email email, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        email.id = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(email, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(email, true);
        return email;
    }
    public static int[] insert(List<Email> emails) {
        EmailDAO DAO = DAO();
        return insert(DAO, emails, DAO.TABLENAME);
    }

    public static int[] insert(EmailDAO DAO, List<Email> emails) {
        return insert(DAO, emails, DAO.TABLENAME);
    }

    public static int[] insert(List<Email> emails, String TABLENAME2) {
        EmailDAO DAO = DAO();
        return insert(DAO, emails, TABLENAME2);
    }

    public static int[] insert(EmailDAO DAO, List<Email> emails, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(emails, TABLENAME2);
            int n = 0;
            for(Email email : emails){
                email.id = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[emails.size()];
        int n = 0;
        for(Email email : emails){
            email = insert(DAO, email, TABLENAME2);
            ret[n++] = (email == null) ? 0 : (int)email.id;
        }
        return ret;
    }

    public static int delete(Email email) {
        int id = email.id;
        EmailDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id) {
        EmailDAO DAO = DAO();
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(EmailDAO DAO, int id) {
        return delete(DAO, id, DAO.TABLENAME);
    }

    public static int delete(int id, String TABLENAME2) {
        EmailDAO DAO = DAO();
        return delete(DAO, id, TABLENAME2);
    }

    public static int delete(EmailDAO DAO, int id, String TABLENAME2) {
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

    public static void asyncDelete(Email email) {
        int id = email.id;
        EmailDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id) {
        EmailDAO DAO = DAO();
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(EmailDAO DAO, int id) {
        asyncDelete(DAO, id, DAO.TABLENAME);
    }

    public static void asyncDelete(int id, String TABLENAME2) {
        EmailDAO DAO = DAO();
        asyncDelete(DAO, id, TABLENAME2);
    }

    public static void asyncDelete(EmailDAO DAO, int id, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(id, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(id);
        }
    }

    public static int[] delete(int[] ids) {
        EmailDAO DAO = DAO();
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(EmailDAO DAO, int[] ids) {
        return delete(DAO, ids, DAO.TABLENAME);
    }

    public static int[] delete(int[] ids,String TABLENAME2) {
        EmailDAO DAO = DAO();
        return delete(DAO, ids, TABLENAME2);
    }

    public static int[] delete(EmailDAO DAO, int[] ids,String TABLENAME2) {
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
        EmailDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, EmailDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        EmailDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final EmailDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer id : keys){
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static int deleteWith(List<Email> beans) {
        EmailDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Email> beans, EmailDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Email> beans, String TABLENAME2) {
        EmailDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Email> beans, final EmailDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Email email : beans){
                int id = email.id;
                deleteFromMemory(id);
            }
        }
        return result;
    }

    public static List<Email> getAll() {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Email> getAll(EmailDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Email> getAll(String TABLENAME2) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Email> getAll(final EmailDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Email> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Email> getNoSortAll() {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Email> getNoSortAll(EmailDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Email> getNoSortAll(String TABLENAME2) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Email> getNoSortAll(final EmailDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Email> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Email> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Email email = FIXED[i];
                if (email != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Email> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Email> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(EmailDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final EmailDAO DAO, final String TABLENAME2) {
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
        EmailDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(EmailDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        EmailDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final EmailDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Email> getIn(List<Integer> keys) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Email> getIn(List<Integer> keys, EmailDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Email> getIn(List<Integer> keys, String TABLENAME2) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Email> getIn(final List<Integer> keys, final EmailDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Email> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Email> getNoSortIn(List<Integer> keys) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Email> getNoSortIn(List<Integer> keys, EmailDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Email> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Email> getNoSortIn(final List<Integer> keys, final EmailDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Email> result = newList();
            for (int key : keys) {
                Email email = getByKeyFromMemory(key);
                if( email == null ) continue;
                result.add(email);
            }
            return result;
        }
    }

    public static List<Email> getLast(int num) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Email> getLast(EmailDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Email> getLast(int num, String TABLENAME2) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Email> getLast(final EmailDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Email> result = newList();
            List<Email> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Email last() {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Email last(EmailDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Email last(String TABLENAME2) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Email last(final EmailDAO DAO, final String TABLENAME2) {
        Email result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        EmailDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(EmailDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        EmailDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final EmailDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Email> getGtKey(int id) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Email> getGtKey(EmailDAO DAO, int id) {
        return getGtKey(DAO, id, DAO.TABLENAME);
    }

    public static List<Email> getGtKey(int id, String TABLENAME2) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, id, TABLENAME2);
    }

    public static List<Email> getGtKey(final EmailDAO DAO, final int id,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(id, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Email> result = newList();
            List<Email> emails = getAll();
            for (Email email : emails) {
                if(email.id <= id) continue;
                result.add(email);
            }
            return result;
        }
    }

    public static Email getByKey(int id) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Future<Email> asyncGetByKey(final int id) {
        Future<Email> f = Async.exec(new Callable<Email>() {
            public Email call() throws Exception {
                return getByKey(id);
            }
        });
        return f;
    }

    public static Email getByKey(EmailDAO DAO, int id) {
        return getByKey(DAO, id, DAO.TABLENAME);
    }

    public static Email getByKey(int id, String TABLENAME2) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, id, TABLENAME2);
    }

    public static Email getByKey(final EmailDAO DAO, final int id,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(id, TABLENAME2);
        }
        return getByKeyFromMemory(id);
    }

    public static Email getByKeyFromMemory(final int id) {
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id) return null;
            return FIXED[id - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(id);
        }
        return null;
    }

    public static Email deleteFromMemory(final int id) {
        Email email;
        if (cacheModel == STATIC_CACHE) {
            if (id < 1 || FIXED == null || FIXED.length < id || FIXED[id-1]==null) return null;
            email = FIXED[id - 1];
            FIXED[id - 1] = null;
            fixedCache.remove(email);
        } else {
            email = vars.remove(id);
        }
        if(email == null) return null;

        return email;
    }

    public static List<Email> getByPage(int page, int size) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Email> getByPage(EmailDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Email> getByPage(int page, int size, String TABLENAME2) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Email> getByPage(final EmailDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Email> result = newList();
            List<Email> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(EmailDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        EmailDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final EmailDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Email update(Email email) {
        EmailDAO DAO = DAO();
        return update(DAO, email, DAO.TABLENAME);
    }

    public static Email update(EmailDAO DAO, Email email) {
        return update(DAO, email, DAO.TABLENAME);
    }

    public static Email update(Email email, String TABLENAME2) {
        EmailDAO DAO = DAO();
        return update(DAO, email, TABLENAME2);
    }

    public static Email update(final EmailDAO DAO, final Email email,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(email, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(email, TABLENAME2);
            if(n == -1) 
                return email;
            else if(n <= 0) 
                return null;
        }
        return email;
    }

    public static Email asyncUpdate(Email email) {
        EmailDAO DAO = DAO();
        return asyncUpdate(DAO, email, DAO.TABLENAME);
    }

    public static Email asyncUpdate(EmailDAO DAO, Email email) {
        return asyncUpdate(DAO, email, DAO.TABLENAME);
    }

    public static Email asyncUpdate(Email email, String TABLENAME2) {
        EmailDAO DAO = DAO();
        return asyncUpdate(DAO, email, TABLENAME2);
    }

    public static Email asyncUpdate(final EmailDAO DAO, final Email email,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(email, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(email, TABLENAME2);
        }
        return email;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        EmailDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(EmailDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final EmailDAO DAO, final String TABLENAME2) {
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

        List<Email> emails = getAll();
        for (Email email : emails) {
            int n = DAO.insert2(email, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
