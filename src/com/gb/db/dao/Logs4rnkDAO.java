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

//gbosng_design - logs4rnk
@SuppressWarnings({"rawtypes", "unchecked"})
public class Logs4rnkDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Logs4rnkDAO.class);

    public static final String TABLE = "logs4rnk";
    public static String TABLENAME = "logs4rnk";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "topindex", "unqid", "title", "content", "awardJson", "creattime"};
    public static String coulmns = "id, topindex, unqid, title, content, awardJson, creattime";
    public static String coulmns2 = "topindex, unqid, title, content, awardJson, creattime";

    public Logs4rnkDAO(Connection conn) {
        super(conn);
    }

    public Logs4rnkDAO(DataSource ds) {
        super(ds);
    }

    public Logs4rnkDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Logs4rnk logs4rnk) {
        return insert(logs4rnk, TABLENAME);
    }

    public int insert(final Logs4rnk logs4rnk, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            logs4rnk.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (topindex, unqid, title, content, awardJson, creattime) VALUES (:topindex, :unqid, :title, :content, :awardJson, :creattime)");
            Map map = super.insert(sql.toString(), logs4rnk);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Logs4rnk logs4rnk) {
        return asyncInsert(logs4rnk, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Logs4rnk logs4rnk, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(logs4rnk, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Logs4rnk logs4rnk) {
        return asyncInsert2(logs4rnk, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Logs4rnk logs4rnk, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(logs4rnk, TABLENAME2);
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

    public int insert2(final Logs4rnk logs4rnk) {
        return insert2(logs4rnk, TABLENAME);
    }

    public int insert2(final Logs4rnk logs4rnk, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            logs4rnk.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, topindex, unqid, title, content, awardJson, creattime) VALUES (:id, :topindex, :unqid, :title, :content, :awardJson, :creattime)");
            Map map = super.insert(sql.toString(), logs4rnk);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Logs4rnk> logs4rnks) {
        return insert(logs4rnks, TABLENAME);
    }

    public int[] insert(final List<Logs4rnk> logs4rnks, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(logs4rnks == null || logs4rnks.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (topindex, unqid, title, content, awardJson, creattime) VALUES (:topindex, :unqid, :title, :content, :awardJson, :creattime)");
            return super.batchInsert(sql.toString(), logs4rnks);
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

    public int deleteInBeans(final List<Logs4rnk> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Logs4rnk> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Logs4rnk logs4rnk = beans.get(i);
                int id = logs4rnk.id;
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

    public List<Logs4rnk> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Logs4rnk> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, topindex, unqid, title, content, awardJson, creattime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Logs4rnk.class);
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

    public List<Logs4rnk> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Logs4rnk> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, topindex, unqid, title, content, awardJson, creattime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Logs4rnk.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Logs4rnk> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Logs4rnk> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, topindex, unqid, title, content, awardJson, creattime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Logs4rnk.class);
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

    public List<Logs4rnk> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Logs4rnk> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, topindex, unqid, title, content, awardJson, creattime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Logs4rnk.class);
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

    public Logs4rnk last() {
        return last(TABLENAME);
    }

    public Logs4rnk last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, topindex, unqid, title, content, awardJson, creattime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Logs4rnk.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Logs4rnk> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Logs4rnk> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, topindex, unqid, title, content, awardJson, creattime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Logs4rnk.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Logs4rnk> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Logs4rnk> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, topindex, unqid, title, content, awardJson, creattime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Logs4rnk.class);
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

    public Logs4rnk selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Logs4rnk selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, topindex, unqid, title, content, awardJson, creattime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Logs4rnk.class);
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

    public Logs4rnk selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Logs4rnk selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, topindex, unqid, title, content, awardJson, creattime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Logs4rnk.class);
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

    public List<Logs4rnk> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Logs4rnk> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, topindex, unqid, title, content, awardJson, creattime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Logs4rnk.class);
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

    public int updateByKey(final Logs4rnk logs4rnk) {
        return updateByKey(logs4rnk, TABLENAME);
    }

    public int updateByKey(final Logs4rnk logs4rnk, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = logs4rnk.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), logs4rnk);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Logs4rnk logs4rnk) {
        return asyncUpdate(logs4rnk, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Logs4rnk logs4rnk, final String TABLENAME2) {
        try {

            String _ustr = logs4rnk.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, logs4rnk);
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

    public int updateTopindexByKey(final int topindex, final int id){
        return updateTopindexByKey(topindex, id, TABLENAME);
    }

    public int updateTopindexByKey(final int topindex, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET topindex=topindex+:topindex WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("topindex", topindex);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTopindexWithMinByKey(final int id, final int topindex, final int _min){
        return updateTopindexWithMinByKey(id, topindex, _min, TABLENAME);
    }

    public int updateTopindexWithMinByKey(final int id, final int topindex, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET topindex = (select case when topindex+:topindex<=:_min then :_min else topindex+:topindex end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("topindex", topindex);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTopindexWithMinInKeys(final List<Integer> keys, final int topindex, final int _min){
        return updateTopindexWithMinInKeys(keys, topindex, _min, TABLENAME);
    }

    public int updateTopindexWithMinInKeys(final List<Integer> keys, final int topindex, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET topindex = (select case when topindex+:topindex<=:_min then :_min else topindex+:topindex end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("topindex", topindex);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTopindexWithMaxByKey(final int id, final int topindex, final int _max){
        return updateTopindexWithMaxByKey(id, topindex, _max, TABLENAME);
    }

    public int updateTopindexWithMaxByKey(final int id, final int topindex, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET topindex = (select case when topindex+:topindex>=:_max then :_max else topindex+:topindex end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("topindex", topindex);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTopindexWithMaxInKeys(final List<Integer> keys, final int topindex, final int _max){
        return updateTopindexWithMaxInKeys(keys, topindex, _max, TABLENAME);
    }

    public int updateTopindexWithMaxInKeys(final List<Integer> keys, final int topindex, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET topindex = (select case when topindex+:topindex>=:_max then :_max else topindex+:topindex end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("topindex", topindex);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateTopindexWithMinMaxByKey(final int id, final int topindex, final int _min, final int _max){
        return updateTopindexWithMinMaxByKey(id, topindex, _min, _max, TABLENAME);
    }

    public int updateTopindexWithMinMaxByKey(final int id, final int topindex, final int _min, final int _max, final String TABLENAME2){
        if( topindex < 0 ) {
            return updateTopindexWithMinByKey(id, topindex, _min, TABLENAME2);
        } else {
            return updateTopindexWithMaxByKey(id, topindex, _max, TABLENAME2);
        }
    }

    public int updateTopindexWithMinMaxInKeys(final List<Integer> keys, final int topindex, final int _min, final int _max){
        return updateTopindexWithMinMaxInKeys(keys, topindex, _min, _max, TABLENAME);
    }

    public int updateTopindexWithMinMaxInKeys(final List<Integer> keys, final int topindex, final int _min, final int _max, final String TABLENAME2){
        if( topindex < 0 ) {
            return updateTopindexWithMinInKeys(keys, topindex, _min, TABLENAME2);
        } else {
            return updateTopindexWithMaxInKeys(keys, topindex, _max, TABLENAME2);
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

    public int[] updateByKey (final List<Logs4rnk> logs4rnks) {
        return updateByKey(logs4rnks, TABLENAME);
    }

    public int[] updateByKey (final List<Logs4rnk> logs4rnks, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(logs4rnks == null || logs4rnks.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET topindex=:topindex, unqid=:unqid, title=:title, content=:content, awardJson=:awardJson, creattime=:creattime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), logs4rnks);
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
                "	`topindex`  INT(11) NOT NULL," +
                "	`unqid`  VARCHAR(128) NOT NULL," +
                "	`title`  TINYTEXT NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`awardJson`  TEXT NOT NULL," +
                "	`creattime`  BIGINT(20) NOT NULL," +
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
                "	`topindex`  INT(11) NOT NULL," +
                "	`unqid`  VARCHAR(128) NOT NULL," +
                "	`title`  TINYTEXT NOT NULL," +
                "	`content`  TEXT NOT NULL," +
                "	`awardJson`  TEXT NOT NULL," +
                "	`creattime`  BIGINT(20) NOT NULL," +
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
