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

//gbosng_design - cop4fee
@SuppressWarnings({"rawtypes", "unchecked"})
public class Cop4feeDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(Cop4feeDAO.class);

    public static final String TABLE = "cop4fee";
    public static String TABLENAME = "cop4fee";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStr6();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStr5();
    }

    public static String[] carrays ={"id", "unqkey", "chn", "version", "copfee", "createtime", "lasttime"};
    public static String coulmns = "id, unqkey, chn, version, copfee, createtime, lasttime";
    public static String coulmns2 = "unqkey, chn, version, copfee, createtime, lasttime";

    public Cop4feeDAO(Connection conn) {
        super(conn);
    }

    public Cop4feeDAO(DataSource ds) {
        super(ds);
    }

    public Cop4feeDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Cop4fee cop4fee) {
        return insert(cop4fee, TABLENAME);
    }

    public int insert(final Cop4fee cop4fee, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            cop4fee.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (unqkey, chn, version, copfee, createtime, lasttime) VALUES (:unqkey, :chn, :version, :copfee, :createtime, :lasttime)");
            Map map = super.insert(sql.toString(), cop4fee);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Cop4fee cop4fee) {
        return asyncInsert(cop4fee, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Cop4fee cop4fee, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(cop4fee, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Cop4fee cop4fee) {
        return asyncInsert2(cop4fee, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Cop4fee cop4fee, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(cop4fee, TABLENAME2);
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

    public int insert2(final Cop4fee cop4fee) {
        return insert2(cop4fee, TABLENAME);
    }

    public int insert2(final Cop4fee cop4fee, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            cop4fee.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, unqkey, chn, version, copfee, createtime, lasttime) VALUES (:id, :unqkey, :chn, :version, :copfee, :createtime, :lasttime)");
            Map map = super.insert(sql.toString(), cop4fee);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Cop4fee> cop4fees) {
        return insert(cop4fees, TABLENAME);
    }

    public int[] insert(final List<Cop4fee> cop4fees, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(cop4fees == null || cop4fees.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (unqkey, chn, version, copfee, createtime, lasttime) VALUES (:unqkey, :chn, :version, :copfee, :createtime, :lasttime)");
            return super.batchInsert(sql.toString(), cop4fees);
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

    public int deleteInBeans(final List<Cop4fee> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Cop4fee> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Cop4fee cop4fee = beans.get(i);
                int id = cop4fee.id;
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

    public List<Cop4fee> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Cop4fee> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Cop4fee.class);
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
            sql.append("SELECT id, unqkey, chn, version FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Cop4fee> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Cop4fee> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Cop4fee.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Cop4fee> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Cop4fee> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Cop4fee.class);
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

    public List<Cop4fee> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Cop4fee> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Cop4fee.class);
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

    public Cop4fee last() {
        return last(TABLENAME);
    }

    public Cop4fee last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Cop4fee.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Cop4fee> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Cop4fee> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Cop4fee.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Cop4fee> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Cop4fee> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Cop4fee.class);
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

    public Cop4fee selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Cop4fee selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Cop4fee.class);
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

    public Cop4fee selectByUnqkey(final String unqkey) {
        return selectByUnqkey(unqkey, TABLENAME);
    }

    public Cop4fee selectByUnqkey(final String unqkey, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" WHERE unqkey = :unqkey");
            Map params = newMap();
            params.put("unqkey", unqkey);
            return super.queryForObject(sql.toString(), params, Cop4fee.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeUnqkey(final String unqkey) {
        return countLikeUnqkey(unqkey, TABLENAME);
    }

    public int countLikeUnqkey(final String unqkey, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE unqkey LIKE '%").append(unqkey).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Cop4fee> selectLikeUnqkey(final String unqkey) {
        return selectLikeUnqkey(unqkey, TABLENAME);
    }

    public List<Cop4fee> selectLikeUnqkey(final String unqkey, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" WHERE unqkey LIKE '%").append(unqkey).append("%' ORDER BY id ");
            return super.queryForList(sql.toString(), Cop4fee.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeUnqkeyPKs(final String unqkey) {
        return selectLikeUnqkeyPKs(unqkey, TABLENAME);
    }

    public List<Integer> selectLikeUnqkeyPKs(final String unqkey, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE unqkey LIKE '%").append(unqkey).append("%' ORDER BY id ");
            Map params = newMap();
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

    public Cop4fee selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Cop4fee selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Cop4fee.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Cop4fee selectByChnVersion(final String chn, String version) {
        return selectByChnVersion(chn, version, TABLENAME);
    }

    public Cop4fee selectByChnVersion(final String chn, String version, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" WHERE chn=:chn AND version=:version");
            Map params = newMap();
            params.put("chn", chn);
            params.put("version", version);
            return super.queryForObject(sql.toString(), params, Cop4fee.class);
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

    public List<Cop4fee> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Cop4fee> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, unqkey, chn, version, copfee, createtime, lasttime FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Cop4fee.class);
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

    public int updateByKey(final Cop4fee cop4fee) {
        return updateByKey(cop4fee, TABLENAME);
    }

    public int updateByKey(final Cop4fee cop4fee, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = cop4fee.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), cop4fee);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Cop4fee cop4fee) {
        return asyncUpdate(cop4fee, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Cop4fee cop4fee, final String TABLENAME2) {
        try {

            String _ustr = cop4fee.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, cop4fee);
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

    public int updateCopfeeByKey(final int copfee, final int id){
        return updateCopfeeByKey(copfee, id, TABLENAME);
    }

    public int updateCopfeeByKey(final int copfee, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET copfee=copfee+:copfee WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("copfee", copfee);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCopfeeWithMinByKey(final int id, final int copfee, final int _min){
        return updateCopfeeWithMinByKey(id, copfee, _min, TABLENAME);
    }

    public int updateCopfeeWithMinByKey(final int id, final int copfee, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET copfee = (select case when copfee+:copfee<=:_min then :_min else copfee+:copfee end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("copfee", copfee);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCopfeeWithMinInKeys(final List<Integer> keys, final int copfee, final int _min){
        return updateCopfeeWithMinInKeys(keys, copfee, _min, TABLENAME);
    }

    public int updateCopfeeWithMinInKeys(final List<Integer> keys, final int copfee, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET copfee = (select case when copfee+:copfee<=:_min then :_min else copfee+:copfee end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("copfee", copfee);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCopfeeWithMaxByKey(final int id, final int copfee, final int _max){
        return updateCopfeeWithMaxByKey(id, copfee, _max, TABLENAME);
    }

    public int updateCopfeeWithMaxByKey(final int id, final int copfee, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET copfee = (select case when copfee+:copfee>=:_max then :_max else copfee+:copfee end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("copfee", copfee);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCopfeeWithMaxInKeys(final List<Integer> keys, final int copfee, final int _max){
        return updateCopfeeWithMaxInKeys(keys, copfee, _max, TABLENAME);
    }

    public int updateCopfeeWithMaxInKeys(final List<Integer> keys, final int copfee, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET copfee = (select case when copfee+:copfee>=:_max then :_max else copfee+:copfee end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("copfee", copfee);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateCopfeeWithMinMaxByKey(final int id, final int copfee, final int _min, final int _max){
        return updateCopfeeWithMinMaxByKey(id, copfee, _min, _max, TABLENAME);
    }

    public int updateCopfeeWithMinMaxByKey(final int id, final int copfee, final int _min, final int _max, final String TABLENAME2){
        if( copfee < 0 ) {
            return updateCopfeeWithMinByKey(id, copfee, _min, TABLENAME2);
        } else {
            return updateCopfeeWithMaxByKey(id, copfee, _max, TABLENAME2);
        }
    }

    public int updateCopfeeWithMinMaxInKeys(final List<Integer> keys, final int copfee, final int _min, final int _max){
        return updateCopfeeWithMinMaxInKeys(keys, copfee, _min, _max, TABLENAME);
    }

    public int updateCopfeeWithMinMaxInKeys(final List<Integer> keys, final int copfee, final int _min, final int _max, final String TABLENAME2){
        if( copfee < 0 ) {
            return updateCopfeeWithMinInKeys(keys, copfee, _min, TABLENAME2);
        } else {
            return updateCopfeeWithMaxInKeys(keys, copfee, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Cop4fee> cop4fees) {
        return updateByKey(cop4fees, TABLENAME);
    }

    public int[] updateByKey (final List<Cop4fee> cop4fees, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(cop4fees == null || cop4fees.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET unqkey=:unqkey, chn=:chn, version=:version, copfee=:copfee, createtime=:createtime, lasttime=:lasttime WHERE id=:id");
            return super.batchUpdate2(sql.toString(), cop4fees);
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
                "	`unqkey`  VARCHAR(64) NOT NULL," +
                "	`chn`  VARCHAR(128) NOT NULL," +
                "	`version`  VARCHAR(64) NOT NULL," +
                "	`copfee`  INT(4) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `unqkey` (`unqkey`)," +
                "	UNIQUE KEY `chn` (`chn`, `version`)" +
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
                "	`unqkey`  VARCHAR(64) NOT NULL," +
                "	`chn`  VARCHAR(128) NOT NULL," +
                "	`version`  VARCHAR(64) NOT NULL," +
                "	`copfee`  INT(4) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `unqkey` (`unqkey`)," +
                "	KEY `chn` (`chn`, `version`)" +
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
