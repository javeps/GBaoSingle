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

//gbosng_design - ranksword
@SuppressWarnings({"rawtypes", "unchecked"})
public class RankswordDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(RankswordDAO.class);

    public static final String TABLE = "ranksword";
    public static String TABLENAME = "ranksword";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStrYM();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStrYMD();
    }

    public static String[] carrays ={"id", "indexs", "unqid", "pcid", "pname", "sword"};
    public static String coulmns = "id, indexs, unqid, pcid, pname, sword";
    public static String coulmns2 = "indexs, unqid, pcid, pname, sword";

    public RankswordDAO(Connection conn) {
        super(conn);
    }

    public RankswordDAO(DataSource ds) {
        super(ds);
    }

    public RankswordDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Ranksword ranksword) {
        return insert(ranksword, TABLENAME);
    }

    public int insert(final Ranksword ranksword, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            ranksword.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (indexs, unqid, pcid, pname, sword) VALUES (:indexs, :unqid, :pcid, :pname, :sword)");
            Map map = super.insert(sql.toString(), ranksword);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Ranksword ranksword) {
        return asyncInsert(ranksword, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Ranksword ranksword, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(ranksword, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Ranksword ranksword) {
        return asyncInsert2(ranksword, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Ranksword ranksword, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(ranksword, TABLENAME2);
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

    public int insert2(final Ranksword ranksword) {
        return insert2(ranksword, TABLENAME);
    }

    public int insert2(final Ranksword ranksword, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            ranksword.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (id, indexs, unqid, pcid, pname, sword) VALUES (:id, :indexs, :unqid, :pcid, :pname, :sword)");
            Map map = super.insert(sql.toString(), ranksword);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Ranksword> rankswords) {
        return insert(rankswords, TABLENAME);
    }

    public int[] insert(final List<Ranksword> rankswords, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(rankswords == null || rankswords.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (indexs, unqid, pcid, pname, sword) VALUES (:indexs, :unqid, :pcid, :pname, :sword)");
            return super.batchInsert(sql.toString(), rankswords);
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

    public int deleteInBeans(final List<Ranksword> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Ranksword> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Ranksword ranksword = beans.get(i);
                int id = ranksword.id;
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

    public List<Ranksword> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Ranksword> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString(), Ranksword.class);
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
            sql.append("SELECT id, unqid FROM ").append(TABLENAME2).append(" ORDER BY id");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Ranksword> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Ranksword> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" WHERE id in (").append(str).append(" ) ORDER BY id");
            return super.queryForList(sql.toString(), Ranksword.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Ranksword> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Ranksword> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" WHERE id in ( :str ) ORDER BY id");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Ranksword.class);
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

    public List<Ranksword> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Ranksword> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Ranksword.class);
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

    public Ranksword last() {
        return last(TABLENAME);
    }

    public Ranksword last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" ORDER BY id DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Ranksword.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Ranksword> selectGtKeyNum(final int id, final int _num) {
        return selectGtKeyNum(id, _num, TABLENAME);
    }

    public List<Ranksword> selectGtKeyNum(final int id, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Ranksword.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Ranksword> selectGtKey(final int id) {
        return selectGtKey(id, TABLENAME);
    }

    public List<Ranksword> selectGtKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" WHERE id > :id ORDER BY id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForList(sql.toString(), params, Ranksword.class);
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

    public Ranksword selectByKey(final int id) {
        return selectByKey(id, TABLENAME);
    }

    public Ranksword selectByKey(final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Ranksword.class);
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

    public Ranksword selectByUnqid(final String unqid) {
        return selectByUnqid(unqid, TABLENAME);
    }

    public Ranksword selectByUnqid(final String unqid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" WHERE unqid = :unqid");
            Map params = newMap();
            params.put("unqid", unqid);
            return super.queryForObject(sql.toString(), params, Ranksword.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeUnqid(final String unqid) {
        return countLikeUnqid(unqid, TABLENAME);
    }

    public int countLikeUnqid(final String unqid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE unqid LIKE '%").append(unqid).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Ranksword> selectLikeUnqid(final String unqid) {
        return selectLikeUnqid(unqid, TABLENAME);
    }

    public List<Ranksword> selectLikeUnqid(final String unqid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" WHERE unqid LIKE '%").append(unqid).append("%' ORDER BY id ");
            return super.queryForList(sql.toString(), Ranksword.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeUnqidPKs(final String unqid) {
        return selectLikeUnqidPKs(unqid, TABLENAME);
    }

    public List<Integer> selectLikeUnqidPKs(final String unqid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT id FROM ").append(TABLENAME2).append(" WHERE unqid LIKE '%").append(unqid).append("%' ORDER BY id ");
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

    public Ranksword selectById(final Integer id) {
        return selectById(id, TABLENAME);
    }

    public Ranksword selectById(final Integer id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" WHERE id = :id");
            Map params = newMap();
            params.put("id", id);
            return super.queryForObject(sql.toString(), params, Ranksword.class);
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

    public List<Ranksword> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Ranksword> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT id, indexs, unqid, pcid, pname, sword FROM ").append(TABLENAME2).append(" ORDER BY id LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Ranksword.class);
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

    public int updateByKey(final Ranksword ranksword) {
        return updateByKey(ranksword, TABLENAME);
    }

    public int updateByKey(final Ranksword ranksword, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = ranksword.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            return super.update(sql.toString(), ranksword);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Ranksword ranksword) {
        return asyncUpdate(ranksword, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Ranksword ranksword, final String TABLENAME2) {
        try {

            String _ustr = ranksword.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE id=:id");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, ranksword);
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

    public int updateIndexsByKey(final int indexs, final int id){
        return updateIndexsByKey(indexs, id, TABLENAME);
    }

    public int updateIndexsByKey(final int indexs, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs=indexs+:indexs WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("indexs", indexs);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexsWithMinByKey(final int id, final int indexs, final int _min){
        return updateIndexsWithMinByKey(id, indexs, _min, TABLENAME);
    }

    public int updateIndexsWithMinByKey(final int id, final int indexs, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs = (select case when indexs+:indexs<=:_min then :_min else indexs+:indexs end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("indexs", indexs);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexsWithMinInKeys(final List<Integer> keys, final int indexs, final int _min){
        return updateIndexsWithMinInKeys(keys, indexs, _min, TABLENAME);
    }

    public int updateIndexsWithMinInKeys(final List<Integer> keys, final int indexs, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs = (select case when indexs+:indexs<=:_min then :_min else indexs+:indexs end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("indexs", indexs);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexsWithMaxByKey(final int id, final int indexs, final int _max){
        return updateIndexsWithMaxByKey(id, indexs, _max, TABLENAME);
    }

    public int updateIndexsWithMaxByKey(final int id, final int indexs, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs = (select case when indexs+:indexs>=:_max then :_max else indexs+:indexs end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("indexs", indexs);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexsWithMaxInKeys(final List<Integer> keys, final int indexs, final int _max){
        return updateIndexsWithMaxInKeys(keys, indexs, _max, TABLENAME);
    }

    public int updateIndexsWithMaxInKeys(final List<Integer> keys, final int indexs, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs = (select case when indexs+:indexs>=:_max then :_max else indexs+:indexs end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("indexs", indexs);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateIndexsWithMinMaxByKey(final int id, final int indexs, final int _min, final int _max){
        return updateIndexsWithMinMaxByKey(id, indexs, _min, _max, TABLENAME);
    }

    public int updateIndexsWithMinMaxByKey(final int id, final int indexs, final int _min, final int _max, final String TABLENAME2){
        if( indexs < 0 ) {
            return updateIndexsWithMinByKey(id, indexs, _min, TABLENAME2);
        } else {
            return updateIndexsWithMaxByKey(id, indexs, _max, TABLENAME2);
        }
    }

    public int updateIndexsWithMinMaxInKeys(final List<Integer> keys, final int indexs, final int _min, final int _max){
        return updateIndexsWithMinMaxInKeys(keys, indexs, _min, _max, TABLENAME);
    }

    public int updateIndexsWithMinMaxInKeys(final List<Integer> keys, final int indexs, final int _min, final int _max, final String TABLENAME2){
        if( indexs < 0 ) {
            return updateIndexsWithMinInKeys(keys, indexs, _min, TABLENAME2);
        } else {
            return updateIndexsWithMaxInKeys(keys, indexs, _max, TABLENAME2);
        }
    }

    public int updatePcidByKey(final int pcid, final int id){
        return updatePcidByKey(pcid, id, TABLENAME);
    }

    public int updatePcidByKey(final int pcid, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid=pcid+:pcid WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMinByKey(final int id, final int pcid, final int _min){
        return updatePcidWithMinByKey(id, pcid, _min, TABLENAME);
    }

    public int updatePcidWithMinByKey(final int id, final int pcid, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid<=:_min then :_min else pcid+:pcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMinInKeys(final List<Integer> keys, final int pcid, final int _min){
        return updatePcidWithMinInKeys(keys, pcid, _min, TABLENAME);
    }

    public int updatePcidWithMinInKeys(final List<Integer> keys, final int pcid, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid<=:_min then :_min else pcid+:pcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMaxByKey(final int id, final int pcid, final int _max){
        return updatePcidWithMaxByKey(id, pcid, _max, TABLENAME);
    }

    public int updatePcidWithMaxByKey(final int id, final int pcid, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid>=:_max then :_max else pcid+:pcid end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMaxInKeys(final List<Integer> keys, final int pcid, final int _max){
        return updatePcidWithMaxInKeys(keys, pcid, _max, TABLENAME);
    }

    public int updatePcidWithMaxInKeys(final List<Integer> keys, final int pcid, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET pcid = (select case when pcid+:pcid>=:_max then :_max else pcid+:pcid end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updatePcidWithMinMaxByKey(final int id, final int pcid, final int _min, final int _max){
        return updatePcidWithMinMaxByKey(id, pcid, _min, _max, TABLENAME);
    }

    public int updatePcidWithMinMaxByKey(final int id, final int pcid, final int _min, final int _max, final String TABLENAME2){
        if( pcid < 0 ) {
            return updatePcidWithMinByKey(id, pcid, _min, TABLENAME2);
        } else {
            return updatePcidWithMaxByKey(id, pcid, _max, TABLENAME2);
        }
    }

    public int updatePcidWithMinMaxInKeys(final List<Integer> keys, final int pcid, final int _min, final int _max){
        return updatePcidWithMinMaxInKeys(keys, pcid, _min, _max, TABLENAME);
    }

    public int updatePcidWithMinMaxInKeys(final List<Integer> keys, final int pcid, final int _min, final int _max, final String TABLENAME2){
        if( pcid < 0 ) {
            return updatePcidWithMinInKeys(keys, pcid, _min, TABLENAME2);
        } else {
            return updatePcidWithMaxInKeys(keys, pcid, _max, TABLENAME2);
        }
    }

    public int updateSwordByKey(final int sword, final int id){
        return updateSwordByKey(sword, id, TABLENAME);
    }

    public int updateSwordByKey(final int sword, final int id, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET sword=sword+:sword WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("sword", sword);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSwordWithMinByKey(final int id, final int sword, final int _min){
        return updateSwordWithMinByKey(id, sword, _min, TABLENAME);
    }

    public int updateSwordWithMinByKey(final int id, final int sword, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET sword = (select case when sword+:sword<=:_min then :_min else sword+:sword end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_min", _min);
            params.put("sword", sword);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSwordWithMinInKeys(final List<Integer> keys, final int sword, final int _min){
        return updateSwordWithMinInKeys(keys, sword, _min, TABLENAME);
    }

    public int updateSwordWithMinInKeys(final List<Integer> keys, final int sword, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET sword = (select case when sword+:sword<=:_min then :_min else sword+:sword end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("sword", sword);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSwordWithMaxByKey(final int id, final int sword, final int _max){
        return updateSwordWithMaxByKey(id, sword, _max, TABLENAME);
    }

    public int updateSwordWithMaxByKey(final int id, final int sword, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET sword = (select case when sword+:sword>=:_max then :_max else sword+:sword end) WHERE id=:id");
            Map params = newMap();
            params.put("id", id);
            params.put("_max", _max);
            params.put("sword", sword);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSwordWithMaxInKeys(final List<Integer> keys, final int sword, final int _max){
        return updateSwordWithMaxInKeys(keys, sword, _max, TABLENAME);
    }

    public int updateSwordWithMaxInKeys(final List<Integer> keys, final int sword, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET sword = (select case when sword+:sword>=:_max then :_max else sword+:sword end) WHERE id in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("sword", sword);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSwordWithMinMaxByKey(final int id, final int sword, final int _min, final int _max){
        return updateSwordWithMinMaxByKey(id, sword, _min, _max, TABLENAME);
    }

    public int updateSwordWithMinMaxByKey(final int id, final int sword, final int _min, final int _max, final String TABLENAME2){
        if( sword < 0 ) {
            return updateSwordWithMinByKey(id, sword, _min, TABLENAME2);
        } else {
            return updateSwordWithMaxByKey(id, sword, _max, TABLENAME2);
        }
    }

    public int updateSwordWithMinMaxInKeys(final List<Integer> keys, final int sword, final int _min, final int _max){
        return updateSwordWithMinMaxInKeys(keys, sword, _min, _max, TABLENAME);
    }

    public int updateSwordWithMinMaxInKeys(final List<Integer> keys, final int sword, final int _min, final int _max, final String TABLENAME2){
        if( sword < 0 ) {
            return updateSwordWithMinInKeys(keys, sword, _min, TABLENAME2);
        } else {
            return updateSwordWithMaxInKeys(keys, sword, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Ranksword> rankswords) {
        return updateByKey(rankswords, TABLENAME);
    }

    public int[] updateByKey (final List<Ranksword> rankswords, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(rankswords == null || rankswords.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET indexs=:indexs, unqid=:unqid, pcid=:pcid, pname=:pname, sword=:sword WHERE id=:id");
            return super.batchUpdate2(sql.toString(), rankswords);
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
                "	`indexs`  INT(11) NOT NULL," +
                "	`unqid`  VARCHAR(128) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`sword`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	UNIQUE KEY `unqid` (`unqid`)" +
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
                "	`indexs`  INT(11) NOT NULL," +
                "	`unqid`  VARCHAR(128) NOT NULL," +
                "	`pcid`  INT(11) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`sword`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`id`)," +
                "	KEY `unqid` (`unqid`)" +
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
