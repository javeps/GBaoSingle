package com.gb.db.dao;

import org.apache.commons.logging.*;

import java.util.*;
import java.sql.*;
import java.util.concurrent.*;
import javax.sql.*;
import com.bowlong.util.DateEx;
import com.bowlong.objpool.*;
import com.bowlong.sql.mysql.*;
import com.bowlong.text.*;
import com.gb.db.bean.*;

//gbosng_design - email4rnk
@SuppressWarnings({"rawtypes", "unchecked"})
public class Email4rnkDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Email4rnkDAO.class);

    public static final String TABLE = "email4rnk";
    public static String TABLENAME = "email4rnk";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "indexBegin", "indexEnd", "title", "content", "awardJson", "creattime", "validtime"};
    public static String coulmns = "id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime";
    public static String coulmns2 = "indexBegin, indexEnd, title, content, awardJson, creattime, validtime";

    public Email4rnkDAO(Connection conn) {
        super(conn);
    }

    public Email4rnkDAO(DataSource ds) {
        super(ds);
    }

    public Email4rnkDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Email4rnk email4rnk) {
        return insert(email4rnk, TABLENAME);
    }

    public int insert(final Email4rnk email4rnk, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            email4rnk.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (indexBegin, indexEnd, title, content, awardJson, creattime, validtime) VALUES (:indexBegin, :indexEnd, :title, :content, :awardJson, :creattime, :validtime)");
            Map map = super.insert(sql.toString(), email4rnk);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Email4rnk email4rnk) {
        return asyncInsert(email4rnk, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Email4rnk email4rnk, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(email4rnk, TABLENAME2);
                   } catch (Exception e) {
                       log.error(e2s(e));
                       return 0;
                   } finally {
                       decrementAndGet();
                   }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public Future<Integer> asyncInsert2(final Email4rnk email4rnk) {
        return asyncInsert2(email4rnk, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Email4rnk email4rnk, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(email4rnk, TABLENAME2);
                   } catch (Exception e) {
                       log.error(e2s(e));
                       return 0;
                   } finally {
                       decrementAndGet();
                   }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public int insert2(final Email4rnk email4rnk) {
        return insert2(email4rnk, TABLENAME);
    }

    public int insert2(final Email4rnk email4rnk, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            email4rnk.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime) VALUES (:id, :indexBegin, :indexEnd, :title, :content, :awardJson, :creattime, :validtime)");
            Map map = super.insert(sql.toString(), email4rnk);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Email4rnk> email4rnks) {
        return insert(email4rnks, TABLENAME);
    }

    public int[] insert(final List<Email4rnk> email4rnks, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(email4rnks == null || email4rnks.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (indexBegin, indexEnd, title, content, awardJson, creattime, validtime) VALUES (:indexBegin, :indexEnd, :title, :content, :awardJson, :creattime, :validtime)");
            return super.batchInsert(sql.toString(), email4rnks);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int id) {
        return deleteByKey(id, TABLENAME);
    }

    public int deleteByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int id) {
        return asyncDeleteByKey(id, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int id, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(id, TABLENAME2);
                    } catch (Exception e) {
                       log.info(e2s(e));
                       return 0;
                    } finally {
                        decrementAndGet();
                    }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public int[] deleteByKey(final int[] ids) {
        return deleteByKey(ids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id=:id");
            List list = newList();
            for (int id : keys) {
                Map params = newMap();
                params.put("id", id);
                list.add(params);
            }
            return super.batchUpdate(sql.toString(), list);
        } catch(Exception e) {
            log.info(e2s(e));
            return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInKeys(final List<Integer> keys) {
        return deleteInKeys(keys, TABLENAME);
    }

    public int deleteInKeys(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Email4rnk> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Email4rnk> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Email4rnk email4rnk = beans.get(i);
                int id = email4rnk.id;
                sb.append(id);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Email4rnk> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Email4rnk> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Email4rnk.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPKs() {
        return selectPKs(TABLENAME);
    }

    public List<Integer> selectPKs(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Map> selectInIndex() {
        return selectInIndex(TABLENAME);
    }

    public List<Map> selectInIndex(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Email4rnk> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Email4rnk> selectIn(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Email4rnk.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Email4rnk> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Email4rnk> selectIn2(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Email4rnk.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectInPKs(final List<Integer> keys) {
        return selectInPKs(keys, TABLENAME);
    }

    public List<Integer> selectInPKs(final List<Integer> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            List<Integer> result = newList();
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Email4rnk> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Email4rnk> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Email4rnk.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLastPKs(final int num) {
        return selectLastPKs(num, TABLENAME);
    }

    public List<Integer> selectLastPKs(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Email4rnk last() {
        return last(TABLENAME);
    }

    public Email4rnk last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Email4rnk.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Email4rnk> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Email4rnk> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Email4rnk.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Email4rnk> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Email4rnk> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Email4rnk.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int id) {
        return selectGtKeyPKs(id, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Email4rnk selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Email4rnk selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Email4rnk.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int maxId() {
        return maxId(TABLENAME);
    }

    public int maxId(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT MAX(id) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Email4rnk selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Email4rnk selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Email4rnk.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int count() {
        return count(TABLENAME);
    }

    public int count(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append("");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Email4rnk> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Email4rnk> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Email4rnk.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByPagePKs(final int begin, final int num) {
        return selectByPagePKs(begin, num, TABLENAME);
    }

    public List<Integer> selectByPagePKs(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Email4rnk email4rnk) {
        return updateByKey(email4rnk, TABLENAME);
    }

    public int updateByKey(final Email4rnk email4rnk, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = email4rnk.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), email4rnk);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Email4rnk email4rnk) {
        return asyncUpdate(email4rnk, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Email4rnk email4rnk, final String TABLENAME2) {
        try {

            String _ustr = email4rnk.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, email4rnk);
                    } catch (Exception e) {
                        log.error(e2s(e));
                        return 0;
                    } finally {
                        decrementAndGet();
                    }
                }
            });
            return f;
        } catch(Exception e) {
            log.info(e2s(e));
            return null;
        }
    }

    public int updateIndexBeginByKey(final int indexBegin, final int id){
        return updateIndexBeginByKey(indexBegin, id, TABLENAME);
    }

    public int updateIndexBeginByKey(final int indexBegin, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexBegin=indexBegin+:indexBegin WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("indexBegin", indexBegin);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexBeginWithMinByKey(final int id, final int indexBegin, final int _min){
        return updateIndexBeginWithMinByKey(id, indexBegin, _min, TABLENAME);
    }

    public int updateIndexBeginWithMinByKey(final int id, final int indexBegin, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexBegin = (select case when indexBegin+:indexBegin<=:_min then :_min else indexBegin+:indexBegin end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("indexBegin", indexBegin);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexBeginWithMinInKeys(final List<Integer> keys, final int indexBegin, final int _min){
        return updateIndexBeginWithMinInKeys(keys, indexBegin, _min, TABLENAME);
    }

    public int updateIndexBeginWithMinInKeys(final List<Integer> keys, final int indexBegin, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexBegin = (select case when indexBegin+:indexBegin<=:_min then :_min else indexBegin+:indexBegin end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("indexBegin", indexBegin);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexBeginWithMaxByKey(final int id, final int indexBegin, final int _max){
        return updateIndexBeginWithMaxByKey(id, indexBegin, _max, TABLENAME);
    }

    public int updateIndexBeginWithMaxByKey(final int id, final int indexBegin, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexBegin = (select case when indexBegin+:indexBegin>=:_max then :_max else indexBegin+:indexBegin end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("indexBegin", indexBegin);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexBeginWithMaxInKeys(final List<Integer> keys, final int indexBegin, final int _max){
        return updateIndexBeginWithMaxInKeys(keys, indexBegin, _max, TABLENAME);
    }

    public int updateIndexBeginWithMaxInKeys(final List<Integer> keys, final int indexBegin, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexBegin = (select case when indexBegin+:indexBegin>=:_max then :_max else indexBegin+:indexBegin end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("indexBegin", indexBegin);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexBeginWithMinMaxByKey(final int id, final int indexBegin, final int _min, final int _max){
        return updateIndexBeginWithMinMaxByKey(id, indexBegin, _min, _max, TABLENAME);
    }

    public int updateIndexBeginWithMinMaxByKey(final int id, final int indexBegin, final int _min, final int _max, final String TABLENAME2){
        if( indexBegin < 0 ) {
            return updateIndexBeginWithMinByKey(id, indexBegin, _min, TABLENAME2);
        } else {
            return updateIndexBeginWithMaxByKey(id, indexBegin, _max, TABLENAME2);
        }
    }

    public int updateIndexBeginWithMinMaxInKeys(final List<Integer> keys, final int indexBegin, final int _min, final int _max){
        return updateIndexBeginWithMinMaxInKeys(keys, indexBegin, _min, _max, TABLENAME);
    }

    public int updateIndexBeginWithMinMaxInKeys(final List<Integer> keys, final int indexBegin, final int _min, final int _max, final String TABLENAME2){
        if( indexBegin < 0 ) {
            return updateIndexBeginWithMinInKeys(keys, indexBegin, _min, TABLENAME2);
        } else {
            return updateIndexBeginWithMaxInKeys(keys, indexBegin, _max, TABLENAME2);
        }
    }

    public int updateIndexEndByKey(final int indexEnd, final int id){
        return updateIndexEndByKey(indexEnd, id, TABLENAME);
    }

    public int updateIndexEndByKey(final int indexEnd, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexEnd=indexEnd+:indexEnd WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("indexEnd", indexEnd);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexEndWithMinByKey(final int id, final int indexEnd, final int _min){
        return updateIndexEndWithMinByKey(id, indexEnd, _min, TABLENAME);
    }

    public int updateIndexEndWithMinByKey(final int id, final int indexEnd, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexEnd = (select case when indexEnd+:indexEnd<=:_min then :_min else indexEnd+:indexEnd end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("indexEnd", indexEnd);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexEndWithMinInKeys(final List<Integer> keys, final int indexEnd, final int _min){
        return updateIndexEndWithMinInKeys(keys, indexEnd, _min, TABLENAME);
    }

    public int updateIndexEndWithMinInKeys(final List<Integer> keys, final int indexEnd, final int _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexEnd = (select case when indexEnd+:indexEnd<=:_min then :_min else indexEnd+:indexEnd end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("indexEnd", indexEnd);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexEndWithMaxByKey(final int id, final int indexEnd, final int _max){
        return updateIndexEndWithMaxByKey(id, indexEnd, _max, TABLENAME);
    }

    public int updateIndexEndWithMaxByKey(final int id, final int indexEnd, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexEnd = (select case when indexEnd+:indexEnd>=:_max then :_max else indexEnd+:indexEnd end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("indexEnd", indexEnd);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexEndWithMaxInKeys(final List<Integer> keys, final int indexEnd, final int _max){
        return updateIndexEndWithMaxInKeys(keys, indexEnd, _max, TABLENAME);
    }

    public int updateIndexEndWithMaxInKeys(final List<Integer> keys, final int indexEnd, final int _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexEnd = (select case when indexEnd+:indexEnd>=:_max then :_max else indexEnd+:indexEnd end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("indexEnd", indexEnd);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexEndWithMinMaxByKey(final int id, final int indexEnd, final int _min, final int _max){
        return updateIndexEndWithMinMaxByKey(id, indexEnd, _min, _max, TABLENAME);
    }

    public int updateIndexEndWithMinMaxByKey(final int id, final int indexEnd, final int _min, final int _max, final String TABLENAME2){
        if( indexEnd < 0 ) {
            return updateIndexEndWithMinByKey(id, indexEnd, _min, TABLENAME2);
        } else {
            return updateIndexEndWithMaxByKey(id, indexEnd, _max, TABLENAME2);
        }
    }

    public int updateIndexEndWithMinMaxInKeys(final List<Integer> keys, final int indexEnd, final int _min, final int _max){
        return updateIndexEndWithMinMaxInKeys(keys, indexEnd, _min, _max, TABLENAME);
    }

    public int updateIndexEndWithMinMaxInKeys(final List<Integer> keys, final int indexEnd, final int _min, final int _max, final String TABLENAME2){
        if( indexEnd < 0 ) {
            return updateIndexEndWithMinInKeys(keys, indexEnd, _min, TABLENAME2);
        } else {
            return updateIndexEndWithMaxInKeys(keys, indexEnd, _max, TABLENAME2);
        }
    }

    public int updateCreattimeByKey(final long creattime, final int id){
        return updateCreattimeByKey(creattime, id, TABLENAME);
    }

    public int updateCreattimeByKey(final long creattime, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET creattime=creattime+:creattime WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("creattime", creattime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreattimeWithMinByKey(final int id, final long creattime, final long _min){
        return updateCreattimeWithMinByKey(id, creattime, _min, TABLENAME);
    }

    public int updateCreattimeWithMinByKey(final int id, final long creattime, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET creattime = (select case when creattime+:creattime<=:_min then :_min else creattime+:creattime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("creattime", creattime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreattimeWithMinInKeys(final List<Integer> keys, final long creattime, final long _min){
        return updateCreattimeWithMinInKeys(keys, creattime, _min, TABLENAME);
    }

    public int updateCreattimeWithMinInKeys(final List<Integer> keys, final long creattime, final long _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET creattime = (select case when creattime+:creattime<=:_min then :_min else creattime+:creattime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("creattime", creattime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreattimeWithMaxByKey(final int id, final long creattime, final long _max){
        return updateCreattimeWithMaxByKey(id, creattime, _max, TABLENAME);
    }

    public int updateCreattimeWithMaxByKey(final int id, final long creattime, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET creattime = (select case when creattime+:creattime>=:_max then :_max else creattime+:creattime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("creattime", creattime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreattimeWithMaxInKeys(final List<Integer> keys, final long creattime, final long _max){
        return updateCreattimeWithMaxInKeys(keys, creattime, _max, TABLENAME);
    }

    public int updateCreattimeWithMaxInKeys(final List<Integer> keys, final long creattime, final long _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET creattime = (select case when creattime+:creattime>=:_max then :_max else creattime+:creattime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("creattime", creattime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCreattimeWithMinMaxByKey(final int id, final long creattime, final long _min, final long _max){
        return updateCreattimeWithMinMaxByKey(id, creattime, _min, _max, TABLENAME);
    }

    public int updateCreattimeWithMinMaxByKey(final int id, final long creattime, final long _min, final long _max, final String TABLENAME2){
        if( creattime < 0 ) {
            return updateCreattimeWithMinByKey(id, creattime, _min, TABLENAME2);
        } else {
            return updateCreattimeWithMaxByKey(id, creattime, _max, TABLENAME2);
        }
    }

    public int updateCreattimeWithMinMaxInKeys(final List<Integer> keys, final long creattime, final long _min, final long _max){
        return updateCreattimeWithMinMaxInKeys(keys, creattime, _min, _max, TABLENAME);
    }

    public int updateCreattimeWithMinMaxInKeys(final List<Integer> keys, final long creattime, final long _min, final long _max, final String TABLENAME2){
        if( creattime < 0 ) {
            return updateCreattimeWithMinInKeys(keys, creattime, _min, TABLENAME2);
        } else {
            return updateCreattimeWithMaxInKeys(keys, creattime, _max, TABLENAME2);
        }
    }

    public int updateValidtimeByKey(final long validtime, final int id){
        return updateValidtimeByKey(validtime, id, TABLENAME);
    }

    public int updateValidtimeByKey(final long validtime, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET validtime=validtime+:validtime WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("validtime", validtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValidtimeWithMinByKey(final int id, final long validtime, final long _min){
        return updateValidtimeWithMinByKey(id, validtime, _min, TABLENAME);
    }

    public int updateValidtimeWithMinByKey(final int id, final long validtime, final long _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET validtime = (select case when validtime+:validtime<=:_min then :_min else validtime+:validtime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("validtime", validtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValidtimeWithMinInKeys(final List<Integer> keys, final long validtime, final long _min){
        return updateValidtimeWithMinInKeys(keys, validtime, _min, TABLENAME);
    }

    public int updateValidtimeWithMinInKeys(final List<Integer> keys, final long validtime, final long _min, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET validtime = (select case when validtime+:validtime<=:_min then :_min else validtime+:validtime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("validtime", validtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValidtimeWithMaxByKey(final int id, final long validtime, final long _max){
        return updateValidtimeWithMaxByKey(id, validtime, _max, TABLENAME);
    }

    public int updateValidtimeWithMaxByKey(final int id, final long validtime, final long _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET validtime = (select case when validtime+:validtime>=:_max then :_max else validtime+:validtime end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("validtime", validtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValidtimeWithMaxInKeys(final List<Integer> keys, final long validtime, final long _max){
        return updateValidtimeWithMaxInKeys(keys, validtime, _max, TABLENAME);
    }

    public int updateValidtimeWithMaxInKeys(final List<Integer> keys, final long validtime, final long _max, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return 0;
            int size = keys.size();
            for (int i = 0; i < size; i ++) {
                sb.append(keys.get(i));
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET validtime = (select case when validtime+:validtime>=:_max then :_max else validtime+:validtime end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("validtime", validtime);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateValidtimeWithMinMaxByKey(final int id, final long validtime, final long _min, final long _max){
        return updateValidtimeWithMinMaxByKey(id, validtime, _min, _max, TABLENAME);
    }

    public int updateValidtimeWithMinMaxByKey(final int id, final long validtime, final long _min, final long _max, final String TABLENAME2){
        if( validtime < 0 ) {
            return updateValidtimeWithMinByKey(id, validtime, _min, TABLENAME2);
        } else {
            return updateValidtimeWithMaxByKey(id, validtime, _max, TABLENAME2);
        }
    }

    public int updateValidtimeWithMinMaxInKeys(final List<Integer> keys, final long validtime, final long _min, final long _max){
        return updateValidtimeWithMinMaxInKeys(keys, validtime, _min, _max, TABLENAME);
    }

    public int updateValidtimeWithMinMaxInKeys(final List<Integer> keys, final long validtime, final long _min, final long _max, final String TABLENAME2){
        if( validtime < 0 ) {
            return updateValidtimeWithMinInKeys(keys, validtime, _min, TABLENAME2);
        } else {
            return updateValidtimeWithMaxInKeys(keys, validtime, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Email4rnk> email4rnks) {
        return updateByKey(email4rnks, TABLENAME);
    }

    public int[] updateByKey (final List<Email4rnk> email4rnks, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(email4rnks == null || email4rnks.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexBegin=:indexBegin, indexEnd=:indexEnd, title=:title, content=:content, awardJson=:awardJson, creattime=:creattime, validtime=:validtime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), email4rnks);
        } catch(Exception e) {
            log.info(e2s(e));
            return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public void createTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`indexBegin`  INT(11) NOT NULL," +
                "	`indexEnd`  INT(1) NOT NULL," +
                "	`title`  TINYTEXT NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`awardJson`  TEXT NOT NULL," +
                "	`creattime`  BIGINT(20) NOT NULL," +
                "	`validtime`  BIGINT(20) NOT NULL," +
                "	PRIMARY KEY (`id`)" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";

            Map params = newMap();
            params.put("TABLENAME", TABLENAME2);
            sql  = EasyTemplate.make(sql, params);
            super.update(sql);
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public void createNoUniqueTable(final String TABLENAME2){
        try{
            String sql = "CREATE TABLE IF NOT EXISTS `${TABLENAME}` (" +
                "	`id`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`indexBegin`  INT(11) NOT NULL," +
                "	`indexEnd`  INT(1) NOT NULL," +
                "	`title`  TINYTEXT NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`awardJson`  TEXT NOT NULL," +
                "	`creattime`  BIGINT(20) NOT NULL," +
                "	`validtime`  BIGINT(20) NOT NULL," +
                "	PRIMARY KEY (`id`)" +
                ") ENGINE=MyISAM DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;";

            Map params = newMap();
            params.put("TABLENAME", TABLENAME2);
            sql  = EasyTemplate.make(sql, params);
            super.update(sql);
        } catch(Exception e) {
            log.info(e2s(e));
        }
    }

    public void truncate(){
        try {
            super.truncate(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void repair(){
        try {
            super.repair(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void optimize(){
        try {
            super.optimize(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    public void dropTable(){
        try {
            super.dropTable(TABLENAME);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

}
