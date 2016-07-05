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

//gbosng_design - recode4error
@SuppressWarnings({"rawtypes", "unchecked"})
public class Recode4errorDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Recode4errorDAO.class);

    public static final String TABLE = "recode4error";
    public static String TABLENAME = "recode4error";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "uuid", "device", "error", "createtime"};
    public static String coulmns = "id, uuid, device, error, createtime";
    public static String coulmns2 = "uuid, device, error, createtime";

    public Recode4errorDAO(Connection conn) {
        super(conn);
    }

    public Recode4errorDAO(DataSource ds) {
        super(ds);
    }

    public Recode4errorDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Recode4error recode4error) {
        return insert(recode4error, TABLENAME);
    }

    public int insert(final Recode4error recode4error, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            recode4error.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (uuid, device, error, createtime) VALUES (:uuid, :device, :error, :createtime)");
            Map map = super.insert(sql.toString(), recode4error);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Recode4error recode4error) {
        return asyncInsert(recode4error, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Recode4error recode4error, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(recode4error, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Recode4error recode4error) {
        return asyncInsert2(recode4error, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Recode4error recode4error, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(recode4error, TABLENAME2);
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

    public int insert2(final Recode4error recode4error) {
        return insert2(recode4error, TABLENAME);
    }

    public int insert2(final Recode4error recode4error, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            recode4error.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, uuid, device, error, createtime) VALUES (:id, :uuid, :device, :error, :createtime)");
            Map map = super.insert(sql.toString(), recode4error);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Recode4error> recode4errors) {
        return insert(recode4errors, TABLENAME);
    }

    public int[] insert(final List<Recode4error> recode4errors, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(recode4errors == null || recode4errors.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (uuid, device, error, createtime) VALUES (:uuid, :device, :error, :createtime)");
            return super.batchInsert(sql.toString(), recode4errors);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final long id) {
        return deleteByKey(id, TABLENAME);
    }

    public int deleteByKey(final long id, final String TABLENAME2) {
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

    public Future<Integer> asyncDeleteByKey(final long id) {
        return asyncDeleteByKey(id, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final long id, final String TABLENAME2) {
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

    public int[] deleteByKey(final long[] ids) {
        return deleteByKey(ids, TABLENAME);
    }

    public int[] deleteByKey(final long[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE id=:id");
            List list = newList();
            for (long id : keys) {
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

    public int deleteInKeys(final List<Long> keys) {
        return deleteInKeys(keys, TABLENAME);
    }

    public int deleteInKeys(final List<Long> keys, final String TABLENAME2) {
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

    public int deleteInBeans(final List<Recode4error> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Recode4error> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Recode4error recode4error = beans.get(i);
                long id = recode4error.id;
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

    public List<Recode4error> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Recode4error> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, uuid, device, error, createtime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Recode4error.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Long> selectPKs() {
        return selectPKs(TABLENAME);
    }

    public List<Long> selectPKs(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Long> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getLong(map, "id") );
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

    public List<Recode4error> selectIn(final List<Long> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Recode4error> selectIn(final List<Long> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, uuid, device, error, createtime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Recode4error.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recode4error> selectIn2(final List<Long> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Recode4error> selectIn2(final List<Long> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, uuid, device, error, createtime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Recode4error.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Long> selectInPKs(final List<Long> keys) {
        return selectInPKs(keys, TABLENAME);
    }

    public List<Long> selectInPKs(final List<Long> keys, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.isEmpty()) return newList();
            List<Long> result = newList();
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
                result.add( getLong(map, "id") );
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

    public List<Recode4error> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Recode4error> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, uuid, device, error, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Recode4error.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Long> selectLastPKs(final int num) {
        return selectLastPKs(num, TABLENAME);
    }

    public List<Long> selectLastPKs(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Long> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getLong(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Recode4error last() {
        return last(TABLENAME);
    }

    public Recode4error last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, uuid, device, error, createtime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Recode4error.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recode4error> selectGtKeyNum(final long id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Recode4error> selectGtKeyNum(final long id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, uuid, device, error, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Recode4error.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Recode4error> selectGtKey(final long id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Recode4error> selectGtKey(final long id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, uuid, device, error, createtime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Recode4error.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Long> selectGtKeyPKs(final long id) {
        return selectGtKeyPKs(id, TABLENAME);
    }

    public List<Long> selectGtKeyPKs(final long id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Long> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getLong(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Recode4error selectByKey(final long id) {
        return selectByKey(id, TABLENAME);
    }

    public Recode4error selectByKey(final long id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, uuid, device, error, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Recode4error.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public long maxId() {
        return maxId(TABLENAME);
    }

    public long maxId(final String TABLENAME2) {
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

    public Recode4error selectById(final Long id) {
        return selectById(id, TABLENAME);
    }

    public Recode4error selectById(final Long id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, uuid, device, error, createtime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Recode4error.class);
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

    public List<Recode4error> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Recode4error> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, uuid, device, error, createtime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Recode4error.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Long> selectByPagePKs(final int begin, final int num) {
        return selectByPagePKs(begin, num, TABLENAME);
    }

    public List<Long> selectByPagePKs(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Long> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getLong(map, "id") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Recode4error recode4error) {
        return updateByKey(recode4error, TABLENAME);
    }

    public int updateByKey(final Recode4error recode4error, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = recode4error.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), recode4error);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Recode4error recode4error) {
        return asyncUpdate(recode4error, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Recode4error recode4error, final String TABLENAME2) {
        try {

            String _ustr = recode4error.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, recode4error);
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

    public int[] updateByKey (final List<Recode4error> recode4errors) {
        return updateByKey(recode4errors, TABLENAME);
    }

    public int[] updateByKey (final List<Recode4error> recode4errors, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(recode4errors == null || recode4errors.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET uuid=:uuid, device=:device, error=:error, createtime=:createtime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), recode4errors);
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
                "	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT," +
                "	`uuid`  VARCHAR(128) NOT NULL," +
                "	`device`  TINYTEXT NOT NULL," +
                "	`error`  TEXT NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
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
                "	`id`  BIGINT(20) NOT NULL AUTO_INCREMENT," +
                "	`uuid`  VARCHAR(128) NOT NULL," +
                "	`device`  TINYTEXT NOT NULL," +
                "	`error`  TEXT NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
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
