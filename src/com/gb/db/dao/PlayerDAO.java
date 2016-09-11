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

//gbosng_design - player
@SuppressWarnings({"rawtypes", "unchecked"})
public class PlayerDAO extends JdbcTemplate {
    static Log log = LogFactory.getLog(PlayerDAO.class);

    public static final String TABLE = "player";
    public static String TABLENAME = "player";

    public static String TABLEYY() {
        return TABLE + DateEx.nowStr(DateEx.fmt_yyyy);
    }

    public static String TABLEMM() {
        return TABLE + DateEx.nowStrYM();
    }

    public static String TABLEDD() {
        return TABLE + DateEx.nowStrYMD();
    }

    public static String[] carrays ={"pcid", "unqid", "uuidMCode", "pname", "sword", "wheel", "btPl", "btHero", "btPart", "btProp", "btNpc", "btEmail", "phone", "createtime", "lasttime", "statusActivity", "score4Endless", "chn", "chnSub", "fight4hero", "fight4part", "npcStars"};
    public static String coulmns = "pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars";
    public static String coulmns2 = "unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars";

    public PlayerDAO(Connection conn) {
        super(conn);
    }

    public PlayerDAO(DataSource ds) {
        super(ds);
    }

    public PlayerDAO(DataSource ds_r, DataSource ds_w) {
        super(ds_r, ds_w);
    }

    public int insert(final Player player) {
        return insert(player, TABLENAME);
    }

    public int insert(final Player player, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            player.reset();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars) VALUES (:unqid, :uuidMCode, :pname, :sword, :wheel, :btPl, :btHero, :btPart, :btProp, :btNpc, :btEmail, :phone, :createtime, :lasttime, :statusActivity, :score4Endless, :chn, :chnSub, :fight4hero, :fight4part, :npcStars)");
            Map map = super.insert(sql.toString(), player);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer>  asyncInsert(final Player player) {
        return asyncInsert(player, TABLENAME);
    }

    public Future<Integer> asyncInsert(final Player player, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                       return insert(player, TABLENAME2);
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

    public Future<Integer> asyncInsert2(final Player player) {
        return asyncInsert2(player, TABLENAME);
    }

    public Future<Integer> asyncInsert2(final Player player, final String TABLENAME2) {
        try {
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() throws Exception {
                   try {
                        return insert2(player, TABLENAME2);
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

    public int insert2(final Player player) {
        return insert2(player, TABLENAME);
    }

    public int insert2(final Player player, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            player.ustr();
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars) VALUES (:pcid, :unqid, :uuidMCode, :pname, :sword, :wheel, :btPl, :btHero, :btPart, :btProp, :btNpc, :btEmail, :phone, :createtime, :lasttime, :statusActivity, :score4Endless, :chn, :chnSub, :fight4hero, :fight4part, :npcStars)");
            Map map = super.insert(sql.toString(), player);
            return getInt(map, "GENERATED_KEY");
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int[] insert(final List<Player> players) {
        return insert(players, TABLENAME);
    }

    public int[] insert(final List<Player> players, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try {
            if(players == null || players.isEmpty()) return new int[0];
            sql.append("INSERT INTO ").append(TABLENAME2).append(" (unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars) VALUES (:unqid, :uuidMCode, :pname, :sword, :wheel, :btPl, :btHero, :btPart, :btProp, :btNpc, :btEmail, :phone, :createtime, :lasttime, :statusActivity, :score4Endless, :chn, :chnSub, :fight4hero, :fight4part, :npcStars)");
            return super.batchInsert(sql.toString(), players);
         } catch (Exception e) {
             log.info(e2s(e));
             return new int[0];
        } finally {
            StringBufPool.returnObject(sql);
         }
    }

    public int deleteByKey(final int pcid) {
        return deleteByKey(pcid, TABLENAME);
    }

    public int deleteByKey(final int pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncDeleteByKey(final int pcid) {
        return asyncDeleteByKey(pcid, TABLENAME);
    }

    public Future<Integer> asyncDeleteByKey(final int pcid, final String TABLENAME2) {
        try{
            incrementAndGet();

            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return deleteByKey(pcid, TABLENAME2);
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

    public int[] deleteByKey(final int[] pcids) {
        return deleteByKey(pcids, TABLENAME);
    }

    public int[] deleteByKey(final int[] keys, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(keys == null || keys.length <= 0) return new int[0];
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE pcid=:pcid");
            List list = newList();
            for (int pcid : keys) {
                Map params = newMap();
                params.put("pcid", pcid);
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
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE pcid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int deleteInBeans(final List<Player> beans) {
        return deleteInBeans(beans, TABLENAME);
    }

    public int deleteInBeans(final List<Player> beans, final String TABLENAME2) {
        StringBuffer sb = StringBufPool.borrowObject();
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(beans == null || beans.isEmpty()) return 0;
            int size = beans.size();
            for (int i = 0; i < size; i ++) {
                Player player = beans.get(i);
                int pcid = player.pcid;
                sb.append(pcid);
                if(i < size - 1)
                    sb.append(", ");
            }
            String str = sb.toString();
            sql.append("DELETE FROM ").append(TABLENAME2).append(" WHERE pcid in (").append(str).append(" ) ");
            return super.update(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectAll() {
        return selectAll(TABLENAME);
    }

    public List<Player> selectAll(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" ORDER BY pcid");
            return super.queryForList(sql.toString(), Player.class);
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
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" ORDER BY pcid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
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
            sql.append("SELECT pcid, unqid, uuidMCode, pname, chn, chnSub FROM ").append(TABLENAME2).append(" ORDER BY pcid");
            return super.queryForList(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectIn(final List<Integer> keys) {
        return selectIn(keys, TABLENAME);
    }

    public List<Player> selectIn(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE pcid in (").append(str).append(" ) ORDER BY pcid");
            return super.queryForList(sql.toString(), Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectIn2(final List<Integer> keys) {
        return selectIn2(keys, TABLENAME);
    }

    public List<Player> selectIn2(final List<Integer> keys, final String TABLENAME2) {
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
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE pcid in ( :str ) ORDER BY pcid");
            Map params = newMap();
            params.put("str", str);
            return super.queryForList(sql.toString(), params, Player.class);
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
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE pcid in (").append(str).append(" ) ORDER BY pcid");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
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

    public List<Player> selectLast(final int num) {
        return selectLast(num, TABLENAME);
    }

    public List<Player> selectLast(final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" ORDER BY pcid DESC LIMIT ").append(num).append("");
            return super.queryForList(sql.toString(), Player.class);
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
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" ORDER BY pcid DESC LIMIT ").append(num).append("");
            List<Map> dbresult = super.queryForList(sql.toString());
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player last() {
        return last(TABLENAME);
    }

    public Player last(final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" ORDER BY pcid DESC LIMIT 1");
            return super.queryForObject(sql.toString(), Player.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectGtKeyNum(final int pcid, final int _num) {
        return selectGtKeyNum(pcid, _num, TABLENAME);
    }

    public List<Player> selectGtKeyNum(final int pcid, final int _num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE pcid > :pcid ORDER BY pcid LIMIT ").append(_num).append("");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectGtKey(final int pcid) {
        return selectGtKey(pcid, TABLENAME);
    }

    public List<Player> selectGtKey(final int pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE pcid > :pcid ORDER BY pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectGtKeyPKs(final int pcid) {
        return selectGtKeyPKs(pcid, TABLENAME);
    }

    public List<Integer> selectGtKeyPKs(final int pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE pcid > :pcid ORDER BY pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player selectByKey(final int pcid) {
        return selectByKey(pcid, TABLENAME);
    }

    public Player selectByKey(final int pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForObject(sql.toString(), params, Player.class);
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
            sql.append("SELECT MAX(pcid) FROM ").append(TABLENAME2);
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            // log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player selectByUnqid(final String unqid) {
        return selectByUnqid(unqid, TABLENAME);
    }

    public Player selectByUnqid(final String unqid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE unqid = :unqid");
            Map params = newMap();
            params.put("unqid", unqid);
            return super.queryForObject(sql.toString(), params, Player.class);
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

    public List<Player> selectLikeUnqid(final String unqid) {
        return selectLikeUnqid(unqid, TABLENAME);
    }

    public List<Player> selectLikeUnqid(final String unqid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE unqid LIKE '%").append(unqid).append("%' ORDER BY pcid ");
            return super.queryForList(sql.toString(), Player.class);
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
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE unqid LIKE '%").append(unqid).append("%' ORDER BY pcid ");
            Map params = newMap();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByChnSub(final String chnSub) {
        return countByChnSub(chnSub, TABLENAME);
    }

    public int countByChnSub(final String chnSub, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE chnSub = :chnSub ");
            Map params = newMap();
            params.put("chnSub", chnSub);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectByChnSub(final String chnSub) {
        return selectByChnSub(chnSub, TABLENAME);
    }

    public List<Player> selectByChnSub(final String chnSub, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE chnSub = :chnSub ORDER BY pcid ");
            Map params = newMap();
            params.put("chnSub", chnSub);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByChnSubPKs(final String chnSub) {
        return selectByChnSubPKs(chnSub, TABLENAME);
    }

    public List<Integer> selectByChnSubPKs(final String chnSub, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE chnSub = :chnSub ORDER BY pcid ");
            Map params = newMap();
            params.put("chnSub", chnSub);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectPageByChnSub(final String chnSub, final int begin, final int num) {
        return selectPageByChnSub(chnSub, begin, num, TABLENAME);
    }

    public List<Player> selectPageByChnSub(final String chnSub, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE chnSub = :chnSub ORDER BY pcid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("chnSub", chnSub);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByChnSubPKs(final String chnSub, final int begin, final int num) {
        return selectPageByChnSubPKs(chnSub, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByChnSubPKs(final String chnSub, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE chnSub = :chnSub ORDER BY pcid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("chnSub", chnSub);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeChnSub(final String chnSub) {
        return countLikeChnSub(chnSub, TABLENAME);
    }

    public int countLikeChnSub(final String chnSub, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE chnSub LIKE '%").append(chnSub).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectLikeChnSub(final String chnSub) {
        return selectLikeChnSub(chnSub, TABLENAME);
    }

    public List<Player> selectLikeChnSub(final String chnSub, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE chnSub LIKE '%").append(chnSub).append("%' ORDER BY pcid ");
            return super.queryForList(sql.toString(), Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeChnSubPKs(final String chnSub) {
        return selectLikeChnSubPKs(chnSub, TABLENAME);
    }

    public List<Integer> selectLikeChnSubPKs(final String chnSub, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE chnSub LIKE '%").append(chnSub).append("%' ORDER BY pcid ");
            Map params = newMap();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player selectByPcid(final Integer pcid) {
        return selectByPcid(pcid, TABLENAME);
    }

    public Player selectByPcid(final Integer pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE pcid = :pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            return super.queryForObject(sql.toString(), params, Player.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Player selectByPname(final String pname) {
        return selectByPname(pname, TABLENAME);
    }

    public Player selectByPname(final String pname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE pname = :pname");
            Map params = newMap();
            params.put("pname", pname);
            return super.queryForObject(sql.toString(), params, Player.class);
        } catch(Exception e) {
            // log.info(e2s(e));
            return null;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikePname(final String pname) {
        return countLikePname(pname, TABLENAME);
    }

    public int countLikePname(final String pname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE pname LIKE '%").append(pname).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectLikePname(final String pname) {
        return selectLikePname(pname, TABLENAME);
    }

    public List<Player> selectLikePname(final String pname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE pname LIKE '%").append(pname).append("%' ORDER BY pcid ");
            return super.queryForList(sql.toString(), Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikePnamePKs(final String pname) {
        return selectLikePnamePKs(pname, TABLENAME);
    }

    public List<Integer> selectLikePnamePKs(final String pname, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE pname LIKE '%").append(pname).append("%' ORDER BY pcid ");
            Map params = newMap();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByChn(final String chn) {
        return countByChn(chn, TABLENAME);
    }

    public int countByChn(final String chn, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE chn = :chn ");
            Map params = newMap();
            params.put("chn", chn);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectByChn(final String chn) {
        return selectByChn(chn, TABLENAME);
    }

    public List<Player> selectByChn(final String chn, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE chn = :chn ORDER BY pcid ");
            Map params = newMap();
            params.put("chn", chn);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByChnPKs(final String chn) {
        return selectByChnPKs(chn, TABLENAME);
    }

    public List<Integer> selectByChnPKs(final String chn, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE chn = :chn ORDER BY pcid ");
            Map params = newMap();
            params.put("chn", chn);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectPageByChn(final String chn, final int begin, final int num) {
        return selectPageByChn(chn, begin, num, TABLENAME);
    }

    public List<Player> selectPageByChn(final String chn, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE chn = :chn ORDER BY pcid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("chn", chn);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByChnPKs(final String chn, final int begin, final int num) {
        return selectPageByChnPKs(chn, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByChnPKs(final String chn, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE chn = :chn ORDER BY pcid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("chn", chn);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeChn(final String chn) {
        return countLikeChn(chn, TABLENAME);
    }

    public int countLikeChn(final String chn, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE chn LIKE '%").append(chn).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectLikeChn(final String chn) {
        return selectLikeChn(chn, TABLENAME);
    }

    public List<Player> selectLikeChn(final String chn, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE chn LIKE '%").append(chn).append("%' ORDER BY pcid ");
            return super.queryForList(sql.toString(), Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeChnPKs(final String chn) {
        return selectLikeChnPKs(chn, TABLENAME);
    }

    public List<Integer> selectLikeChnPKs(final String chn, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE chn LIKE '%").append(chn).append("%' ORDER BY pcid ");
            Map params = newMap();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countByUuidMCode(final String uuidMCode) {
        return countByUuidMCode(uuidMCode, TABLENAME);
    }

    public int countByUuidMCode(final String uuidMCode, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE uuidMCode = :uuidMCode ");
            Map params = newMap();
            params.put("uuidMCode", uuidMCode);
            return super.queryForInt(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectByUuidMCode(final String uuidMCode) {
        return selectByUuidMCode(uuidMCode, TABLENAME);
    }

    public List<Player> selectByUuidMCode(final String uuidMCode, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE uuidMCode = :uuidMCode ORDER BY pcid ");
            Map params = newMap();
            params.put("uuidMCode", uuidMCode);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectByUuidMCodePKs(final String uuidMCode) {
        return selectByUuidMCodePKs(uuidMCode, TABLENAME);
    }

    public List<Integer> selectByUuidMCodePKs(final String uuidMCode, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE uuidMCode = :uuidMCode ORDER BY pcid ");
            Map params = newMap();
            params.put("uuidMCode", uuidMCode);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add(getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectPageByUuidMCode(final String uuidMCode, final int begin, final int num) {
        return selectPageByUuidMCode(uuidMCode, begin, num, TABLENAME);
    }

    public List<Player> selectPageByUuidMCode(final String uuidMCode, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE uuidMCode = :uuidMCode ORDER BY pcid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("uuidMCode", uuidMCode);
            return super.queryForList(sql.toString(), params, Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectPageByUuidMCodePKs(final String uuidMCode, final int begin, final int num) {
        return selectPageByUuidMCodePKs(uuidMCode, begin, num, TABLENAME);
    }

    public List<Integer> selectPageByUuidMCodePKs(final String uuidMCode, final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE uuidMCode = :uuidMCode ORDER BY pcid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = newMap();
            params.put("uuidMCode", uuidMCode);
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int countLikeUuidMCode(final String uuidMCode) {
        return countLikeUuidMCode(uuidMCode, TABLENAME);
    }

    public int countLikeUuidMCode(final String uuidMCode, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT COUNT(*) FROM ").append(TABLENAME2).append(" WHERE uuidMCode LIKE '%").append(uuidMCode).append("%' ");
            return super.queryForInt(sql.toString());
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Player> selectLikeUuidMCode(final String uuidMCode) {
        return selectLikeUuidMCode(uuidMCode, TABLENAME);
    }

    public List<Player> selectLikeUuidMCode(final String uuidMCode, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" WHERE uuidMCode LIKE '%").append(uuidMCode).append("%' ORDER BY pcid ");
            return super.queryForList(sql.toString(), Player.class);
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public List<Integer> selectLikeUuidMCodePKs(final String uuidMCode) {
        return selectLikeUuidMCodePKs(uuidMCode, TABLENAME);
    }

    public List<Integer> selectLikeUuidMCodePKs(final String uuidMCode, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            List<Integer> result = newList();
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" WHERE uuidMCode LIKE '%").append(uuidMCode).append("%' ORDER BY pcid ");
            Map params = newMap();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
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

    public List<Player> selectByPage(final int begin, final int num) {
        return selectByPage(begin, num, TABLENAME);
    }

    public List<Player> selectByPage(final int begin, final int num, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("SELECT pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part, npcStars FROM ").append(TABLENAME2).append(" ORDER BY pcid LIMIT ").append(begin).append(", ").append(num).append("");
            return super.queryForList(sql.toString(), Player.class);
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
            sql.append("SELECT pcid FROM ").append(TABLENAME2).append(" ORDER BY pcid LIMIT ").append(begin).append(", ").append(num).append("");
            Map params = new Hashtable();
            List<Map> dbresult = super.queryForList(sql.toString(), params);
            for(Map map : dbresult){
                result.add( getInt(map, "pcid") );
            }
            return result;
        } catch(Exception e) {
            log.info(e2s(e));
            return newList();
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateByKey(final Player player) {
        return updateByKey(player, TABLENAME);
    }

    public int updateByKey(final Player player, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            String _ustr = player.ustr();
            if( _ustr.length() <= 0 )
                return -1;
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE pcid=:pcid");
            return super.update(sql.toString(), player);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public Future<Integer> asyncUpdate(final Player player) {
        return asyncUpdate(player, TABLENAME);
    }

    public Future<Integer> asyncUpdate(final Player player, final String TABLENAME2) {
        try {

            String _ustr = player.ustr();
            if( _ustr.length() <= 0 ) return null;

            StringBuffer sql = StringBufPool.borrowObject();
            sql.append("UPDATE ").append(TABLENAME2).append(" SET ").append(_ustr).append(" WHERE pcid=:pcid");
            final String szSql = sql.toString();
            StringBufPool.returnObject(sql);
            incrementAndGet();
            Future<Integer> f = executor(TABLENAME2).submit(new Callable<Integer>() {
                public Integer call() {
                    try {
                        return update(szSql, player);
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

    public int updateSwordByKey(final int sword, final int pcid){
        return updateSwordByKey(sword, pcid, TABLENAME);
    }

    public int updateSwordByKey(final int sword, final int pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET sword=sword+:sword WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("sword", sword);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateSwordWithMinByKey(final int pcid, final int sword, final int _min){
        return updateSwordWithMinByKey(pcid, sword, _min, TABLENAME);
    }

    public int updateSwordWithMinByKey(final int pcid, final int sword, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET sword = (select case when sword+:sword<=:_min then :_min else sword+:sword end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET sword = (select case when sword+:sword<=:_min then :_min else sword+:sword end) WHERE pcid in (").append(str).append(")");
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

    public int updateSwordWithMaxByKey(final int pcid, final int sword, final int _max){
        return updateSwordWithMaxByKey(pcid, sword, _max, TABLENAME);
    }

    public int updateSwordWithMaxByKey(final int pcid, final int sword, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET sword = (select case when sword+:sword>=:_max then :_max else sword+:sword end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET sword = (select case when sword+:sword>=:_max then :_max else sword+:sword end) WHERE pcid in (").append(str).append(")");
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

    public int updateSwordWithMinMaxByKey(final int pcid, final int sword, final int _min, final int _max){
        return updateSwordWithMinMaxByKey(pcid, sword, _min, _max, TABLENAME);
    }

    public int updateSwordWithMinMaxByKey(final int pcid, final int sword, final int _min, final int _max, final String TABLENAME2){
        if( sword < 0 ) {
            return updateSwordWithMinByKey(pcid, sword, _min, TABLENAME2);
        } else {
            return updateSwordWithMaxByKey(pcid, sword, _max, TABLENAME2);
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

    public int updateWheelByKey(final int wheel, final int pcid){
        return updateWheelByKey(wheel, pcid, TABLENAME);
    }

    public int updateWheelByKey(final int wheel, final int pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wheel=wheel+:wheel WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("wheel", wheel);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWheelWithMinByKey(final int pcid, final int wheel, final int _min){
        return updateWheelWithMinByKey(pcid, wheel, _min, TABLENAME);
    }

    public int updateWheelWithMinByKey(final int pcid, final int wheel, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wheel = (select case when wheel+:wheel<=:_min then :_min else wheel+:wheel end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_min", _min);
            params.put("wheel", wheel);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWheelWithMinInKeys(final List<Integer> keys, final int wheel, final int _min){
        return updateWheelWithMinInKeys(keys, wheel, _min, TABLENAME);
    }

    public int updateWheelWithMinInKeys(final List<Integer> keys, final int wheel, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wheel = (select case when wheel+:wheel<=:_min then :_min else wheel+:wheel end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("wheel", wheel);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWheelWithMaxByKey(final int pcid, final int wheel, final int _max){
        return updateWheelWithMaxByKey(pcid, wheel, _max, TABLENAME);
    }

    public int updateWheelWithMaxByKey(final int pcid, final int wheel, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wheel = (select case when wheel+:wheel>=:_max then :_max else wheel+:wheel end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_max", _max);
            params.put("wheel", wheel);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWheelWithMaxInKeys(final List<Integer> keys, final int wheel, final int _max){
        return updateWheelWithMaxInKeys(keys, wheel, _max, TABLENAME);
    }

    public int updateWheelWithMaxInKeys(final List<Integer> keys, final int wheel, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET wheel = (select case when wheel+:wheel>=:_max then :_max else wheel+:wheel end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("wheel", wheel);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateWheelWithMinMaxByKey(final int pcid, final int wheel, final int _min, final int _max){
        return updateWheelWithMinMaxByKey(pcid, wheel, _min, _max, TABLENAME);
    }

    public int updateWheelWithMinMaxByKey(final int pcid, final int wheel, final int _min, final int _max, final String TABLENAME2){
        if( wheel < 0 ) {
            return updateWheelWithMinByKey(pcid, wheel, _min, TABLENAME2);
        } else {
            return updateWheelWithMaxByKey(pcid, wheel, _max, TABLENAME2);
        }
    }

    public int updateWheelWithMinMaxInKeys(final List<Integer> keys, final int wheel, final int _min, final int _max){
        return updateWheelWithMinMaxInKeys(keys, wheel, _min, _max, TABLENAME);
    }

    public int updateWheelWithMinMaxInKeys(final List<Integer> keys, final int wheel, final int _min, final int _max, final String TABLENAME2){
        if( wheel < 0 ) {
            return updateWheelWithMinInKeys(keys, wheel, _min, TABLENAME2);
        } else {
            return updateWheelWithMaxInKeys(keys, wheel, _max, TABLENAME2);
        }
    }

    public int updateStatusActivityByKey(final int statusActivity, final int pcid){
        return updateStatusActivityByKey(statusActivity, pcid, TABLENAME);
    }

    public int updateStatusActivityByKey(final int statusActivity, final int pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusActivity=statusActivity+:statusActivity WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("statusActivity", statusActivity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusActivityWithMinByKey(final int pcid, final int statusActivity, final int _min){
        return updateStatusActivityWithMinByKey(pcid, statusActivity, _min, TABLENAME);
    }

    public int updateStatusActivityWithMinByKey(final int pcid, final int statusActivity, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusActivity = (select case when statusActivity+:statusActivity<=:_min then :_min else statusActivity+:statusActivity end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_min", _min);
            params.put("statusActivity", statusActivity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusActivityWithMinInKeys(final List<Integer> keys, final int statusActivity, final int _min){
        return updateStatusActivityWithMinInKeys(keys, statusActivity, _min, TABLENAME);
    }

    public int updateStatusActivityWithMinInKeys(final List<Integer> keys, final int statusActivity, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusActivity = (select case when statusActivity+:statusActivity<=:_min then :_min else statusActivity+:statusActivity end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("statusActivity", statusActivity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusActivityWithMaxByKey(final int pcid, final int statusActivity, final int _max){
        return updateStatusActivityWithMaxByKey(pcid, statusActivity, _max, TABLENAME);
    }

    public int updateStatusActivityWithMaxByKey(final int pcid, final int statusActivity, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusActivity = (select case when statusActivity+:statusActivity>=:_max then :_max else statusActivity+:statusActivity end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_max", _max);
            params.put("statusActivity", statusActivity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusActivityWithMaxInKeys(final List<Integer> keys, final int statusActivity, final int _max){
        return updateStatusActivityWithMaxInKeys(keys, statusActivity, _max, TABLENAME);
    }

    public int updateStatusActivityWithMaxInKeys(final List<Integer> keys, final int statusActivity, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET statusActivity = (select case when statusActivity+:statusActivity>=:_max then :_max else statusActivity+:statusActivity end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("statusActivity", statusActivity);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateStatusActivityWithMinMaxByKey(final int pcid, final int statusActivity, final int _min, final int _max){
        return updateStatusActivityWithMinMaxByKey(pcid, statusActivity, _min, _max, TABLENAME);
    }

    public int updateStatusActivityWithMinMaxByKey(final int pcid, final int statusActivity, final int _min, final int _max, final String TABLENAME2){
        if( statusActivity < 0 ) {
            return updateStatusActivityWithMinByKey(pcid, statusActivity, _min, TABLENAME2);
        } else {
            return updateStatusActivityWithMaxByKey(pcid, statusActivity, _max, TABLENAME2);
        }
    }

    public int updateStatusActivityWithMinMaxInKeys(final List<Integer> keys, final int statusActivity, final int _min, final int _max){
        return updateStatusActivityWithMinMaxInKeys(keys, statusActivity, _min, _max, TABLENAME);
    }

    public int updateStatusActivityWithMinMaxInKeys(final List<Integer> keys, final int statusActivity, final int _min, final int _max, final String TABLENAME2){
        if( statusActivity < 0 ) {
            return updateStatusActivityWithMinInKeys(keys, statusActivity, _min, TABLENAME2);
        } else {
            return updateStatusActivityWithMaxInKeys(keys, statusActivity, _max, TABLENAME2);
        }
    }

    public int updateScore4EndlessByKey(final int score4Endless, final int pcid){
        return updateScore4EndlessByKey(score4Endless, pcid, TABLENAME);
    }

    public int updateScore4EndlessByKey(final int score4Endless, final int pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET score4Endless=score4Endless+:score4Endless WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("score4Endless", score4Endless);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateScore4EndlessWithMinByKey(final int pcid, final int score4Endless, final int _min){
        return updateScore4EndlessWithMinByKey(pcid, score4Endless, _min, TABLENAME);
    }

    public int updateScore4EndlessWithMinByKey(final int pcid, final int score4Endless, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET score4Endless = (select case when score4Endless+:score4Endless<=:_min then :_min else score4Endless+:score4Endless end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_min", _min);
            params.put("score4Endless", score4Endless);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateScore4EndlessWithMinInKeys(final List<Integer> keys, final int score4Endless, final int _min){
        return updateScore4EndlessWithMinInKeys(keys, score4Endless, _min, TABLENAME);
    }

    public int updateScore4EndlessWithMinInKeys(final List<Integer> keys, final int score4Endless, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET score4Endless = (select case when score4Endless+:score4Endless<=:_min then :_min else score4Endless+:score4Endless end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("score4Endless", score4Endless);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateScore4EndlessWithMaxByKey(final int pcid, final int score4Endless, final int _max){
        return updateScore4EndlessWithMaxByKey(pcid, score4Endless, _max, TABLENAME);
    }

    public int updateScore4EndlessWithMaxByKey(final int pcid, final int score4Endless, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET score4Endless = (select case when score4Endless+:score4Endless>=:_max then :_max else score4Endless+:score4Endless end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_max", _max);
            params.put("score4Endless", score4Endless);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateScore4EndlessWithMaxInKeys(final List<Integer> keys, final int score4Endless, final int _max){
        return updateScore4EndlessWithMaxInKeys(keys, score4Endless, _max, TABLENAME);
    }

    public int updateScore4EndlessWithMaxInKeys(final List<Integer> keys, final int score4Endless, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET score4Endless = (select case when score4Endless+:score4Endless>=:_max then :_max else score4Endless+:score4Endless end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("score4Endless", score4Endless);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateScore4EndlessWithMinMaxByKey(final int pcid, final int score4Endless, final int _min, final int _max){
        return updateScore4EndlessWithMinMaxByKey(pcid, score4Endless, _min, _max, TABLENAME);
    }

    public int updateScore4EndlessWithMinMaxByKey(final int pcid, final int score4Endless, final int _min, final int _max, final String TABLENAME2){
        if( score4Endless < 0 ) {
            return updateScore4EndlessWithMinByKey(pcid, score4Endless, _min, TABLENAME2);
        } else {
            return updateScore4EndlessWithMaxByKey(pcid, score4Endless, _max, TABLENAME2);
        }
    }

    public int updateScore4EndlessWithMinMaxInKeys(final List<Integer> keys, final int score4Endless, final int _min, final int _max){
        return updateScore4EndlessWithMinMaxInKeys(keys, score4Endless, _min, _max, TABLENAME);
    }

    public int updateScore4EndlessWithMinMaxInKeys(final List<Integer> keys, final int score4Endless, final int _min, final int _max, final String TABLENAME2){
        if( score4Endless < 0 ) {
            return updateScore4EndlessWithMinInKeys(keys, score4Endless, _min, TABLENAME2);
        } else {
            return updateScore4EndlessWithMaxInKeys(keys, score4Endless, _max, TABLENAME2);
        }
    }

    public int updateFight4heroByKey(final int fight4hero, final int pcid){
        return updateFight4heroByKey(fight4hero, pcid, TABLENAME);
    }

    public int updateFight4heroByKey(final int fight4hero, final int pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fight4hero=fight4hero+:fight4hero WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("fight4hero", fight4hero);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFight4heroWithMinByKey(final int pcid, final int fight4hero, final int _min){
        return updateFight4heroWithMinByKey(pcid, fight4hero, _min, TABLENAME);
    }

    public int updateFight4heroWithMinByKey(final int pcid, final int fight4hero, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fight4hero = (select case when fight4hero+:fight4hero<=:_min then :_min else fight4hero+:fight4hero end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_min", _min);
            params.put("fight4hero", fight4hero);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFight4heroWithMinInKeys(final List<Integer> keys, final int fight4hero, final int _min){
        return updateFight4heroWithMinInKeys(keys, fight4hero, _min, TABLENAME);
    }

    public int updateFight4heroWithMinInKeys(final List<Integer> keys, final int fight4hero, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fight4hero = (select case when fight4hero+:fight4hero<=:_min then :_min else fight4hero+:fight4hero end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("fight4hero", fight4hero);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFight4heroWithMaxByKey(final int pcid, final int fight4hero, final int _max){
        return updateFight4heroWithMaxByKey(pcid, fight4hero, _max, TABLENAME);
    }

    public int updateFight4heroWithMaxByKey(final int pcid, final int fight4hero, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fight4hero = (select case when fight4hero+:fight4hero>=:_max then :_max else fight4hero+:fight4hero end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_max", _max);
            params.put("fight4hero", fight4hero);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFight4heroWithMaxInKeys(final List<Integer> keys, final int fight4hero, final int _max){
        return updateFight4heroWithMaxInKeys(keys, fight4hero, _max, TABLENAME);
    }

    public int updateFight4heroWithMaxInKeys(final List<Integer> keys, final int fight4hero, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fight4hero = (select case when fight4hero+:fight4hero>=:_max then :_max else fight4hero+:fight4hero end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("fight4hero", fight4hero);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFight4heroWithMinMaxByKey(final int pcid, final int fight4hero, final int _min, final int _max){
        return updateFight4heroWithMinMaxByKey(pcid, fight4hero, _min, _max, TABLENAME);
    }

    public int updateFight4heroWithMinMaxByKey(final int pcid, final int fight4hero, final int _min, final int _max, final String TABLENAME2){
        if( fight4hero < 0 ) {
            return updateFight4heroWithMinByKey(pcid, fight4hero, _min, TABLENAME2);
        } else {
            return updateFight4heroWithMaxByKey(pcid, fight4hero, _max, TABLENAME2);
        }
    }

    public int updateFight4heroWithMinMaxInKeys(final List<Integer> keys, final int fight4hero, final int _min, final int _max){
        return updateFight4heroWithMinMaxInKeys(keys, fight4hero, _min, _max, TABLENAME);
    }

    public int updateFight4heroWithMinMaxInKeys(final List<Integer> keys, final int fight4hero, final int _min, final int _max, final String TABLENAME2){
        if( fight4hero < 0 ) {
            return updateFight4heroWithMinInKeys(keys, fight4hero, _min, TABLENAME2);
        } else {
            return updateFight4heroWithMaxInKeys(keys, fight4hero, _max, TABLENAME2);
        }
    }

    public int updateFight4partByKey(final int fight4part, final int pcid){
        return updateFight4partByKey(fight4part, pcid, TABLENAME);
    }

    public int updateFight4partByKey(final int fight4part, final int pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fight4part=fight4part+:fight4part WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("fight4part", fight4part);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFight4partWithMinByKey(final int pcid, final int fight4part, final int _min){
        return updateFight4partWithMinByKey(pcid, fight4part, _min, TABLENAME);
    }

    public int updateFight4partWithMinByKey(final int pcid, final int fight4part, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fight4part = (select case when fight4part+:fight4part<=:_min then :_min else fight4part+:fight4part end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_min", _min);
            params.put("fight4part", fight4part);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFight4partWithMinInKeys(final List<Integer> keys, final int fight4part, final int _min){
        return updateFight4partWithMinInKeys(keys, fight4part, _min, TABLENAME);
    }

    public int updateFight4partWithMinInKeys(final List<Integer> keys, final int fight4part, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fight4part = (select case when fight4part+:fight4part<=:_min then :_min else fight4part+:fight4part end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("fight4part", fight4part);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFight4partWithMaxByKey(final int pcid, final int fight4part, final int _max){
        return updateFight4partWithMaxByKey(pcid, fight4part, _max, TABLENAME);
    }

    public int updateFight4partWithMaxByKey(final int pcid, final int fight4part, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fight4part = (select case when fight4part+:fight4part>=:_max then :_max else fight4part+:fight4part end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_max", _max);
            params.put("fight4part", fight4part);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFight4partWithMaxInKeys(final List<Integer> keys, final int fight4part, final int _max){
        return updateFight4partWithMaxInKeys(keys, fight4part, _max, TABLENAME);
    }

    public int updateFight4partWithMaxInKeys(final List<Integer> keys, final int fight4part, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET fight4part = (select case when fight4part+:fight4part>=:_max then :_max else fight4part+:fight4part end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("fight4part", fight4part);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateFight4partWithMinMaxByKey(final int pcid, final int fight4part, final int _min, final int _max){
        return updateFight4partWithMinMaxByKey(pcid, fight4part, _min, _max, TABLENAME);
    }

    public int updateFight4partWithMinMaxByKey(final int pcid, final int fight4part, final int _min, final int _max, final String TABLENAME2){
        if( fight4part < 0 ) {
            return updateFight4partWithMinByKey(pcid, fight4part, _min, TABLENAME2);
        } else {
            return updateFight4partWithMaxByKey(pcid, fight4part, _max, TABLENAME2);
        }
    }

    public int updateFight4partWithMinMaxInKeys(final List<Integer> keys, final int fight4part, final int _min, final int _max){
        return updateFight4partWithMinMaxInKeys(keys, fight4part, _min, _max, TABLENAME);
    }

    public int updateFight4partWithMinMaxInKeys(final List<Integer> keys, final int fight4part, final int _min, final int _max, final String TABLENAME2){
        if( fight4part < 0 ) {
            return updateFight4partWithMinInKeys(keys, fight4part, _min, TABLENAME2);
        } else {
            return updateFight4partWithMaxInKeys(keys, fight4part, _max, TABLENAME2);
        }
    }

    public int updateNpcStarsByKey(final int npcStars, final int pcid){
        return updateNpcStarsByKey(npcStars, pcid, TABLENAME);
    }

    public int updateNpcStarsByKey(final int npcStars, final int pcid, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET npcStars=npcStars+:npcStars WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("npcStars", npcStars);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNpcStarsWithMinByKey(final int pcid, final int npcStars, final int _min){
        return updateNpcStarsWithMinByKey(pcid, npcStars, _min, TABLENAME);
    }

    public int updateNpcStarsWithMinByKey(final int pcid, final int npcStars, final int _min, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET npcStars = (select case when npcStars+:npcStars<=:_min then :_min else npcStars+:npcStars end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_min", _min);
            params.put("npcStars", npcStars);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNpcStarsWithMinInKeys(final List<Integer> keys, final int npcStars, final int _min){
        return updateNpcStarsWithMinInKeys(keys, npcStars, _min, TABLENAME);
    }

    public int updateNpcStarsWithMinInKeys(final List<Integer> keys, final int npcStars, final int _min, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET npcStars = (select case when npcStars+:npcStars<=:_min then :_min else npcStars+:npcStars end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_min", _min);
            params.put("npcStars", npcStars);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNpcStarsWithMaxByKey(final int pcid, final int npcStars, final int _max){
        return updateNpcStarsWithMaxByKey(pcid, npcStars, _max, TABLENAME);
    }

    public int updateNpcStarsWithMaxByKey(final int pcid, final int npcStars, final int _max, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            sql.append("UPDATE ").append(TABLENAME2).append(" SET npcStars = (select case when npcStars+:npcStars>=:_max then :_max else npcStars+:npcStars end) WHERE pcid=:pcid");
            Map params = newMap();
            params.put("pcid", pcid);
            params.put("_max", _max);
            params.put("npcStars", npcStars);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNpcStarsWithMaxInKeys(final List<Integer> keys, final int npcStars, final int _max){
        return updateNpcStarsWithMaxInKeys(keys, npcStars, _max, TABLENAME);
    }

    public int updateNpcStarsWithMaxInKeys(final List<Integer> keys, final int npcStars, final int _max, final String TABLENAME2) {
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
            sql.append("UPDATE ").append(TABLENAME2).append(" SET npcStars = (select case when npcStars+:npcStars>=:_max then :_max else npcStars+:npcStars end) WHERE pcid in (").append(str).append(")");
            Map params = newMap();
            params.put("_max", _max);
            params.put("npcStars", npcStars);
            return super.update(sql.toString(), params);
        } catch(Exception e) {
            log.info(e2s(e));
            return 0;
        } finally {
            StringBufPool.returnObject(sb);
            StringBufPool.returnObject(sql);
        }
    }

    public int updateNpcStarsWithMinMaxByKey(final int pcid, final int npcStars, final int _min, final int _max){
        return updateNpcStarsWithMinMaxByKey(pcid, npcStars, _min, _max, TABLENAME);
    }

    public int updateNpcStarsWithMinMaxByKey(final int pcid, final int npcStars, final int _min, final int _max, final String TABLENAME2){
        if( npcStars < 0 ) {
            return updateNpcStarsWithMinByKey(pcid, npcStars, _min, TABLENAME2);
        } else {
            return updateNpcStarsWithMaxByKey(pcid, npcStars, _max, TABLENAME2);
        }
    }

    public int updateNpcStarsWithMinMaxInKeys(final List<Integer> keys, final int npcStars, final int _min, final int _max){
        return updateNpcStarsWithMinMaxInKeys(keys, npcStars, _min, _max, TABLENAME);
    }

    public int updateNpcStarsWithMinMaxInKeys(final List<Integer> keys, final int npcStars, final int _min, final int _max, final String TABLENAME2){
        if( npcStars < 0 ) {
            return updateNpcStarsWithMinInKeys(keys, npcStars, _min, TABLENAME2);
        } else {
            return updateNpcStarsWithMaxInKeys(keys, npcStars, _max, TABLENAME2);
        }
    }

    public int[] updateByKey (final List<Player> players) {
        return updateByKey(players, TABLENAME);
    }

    public int[] updateByKey (final List<Player> players, final String TABLENAME2) {
        StringBuffer sql = StringBufPool.borrowObject();
        try{
            if(players == null || players.isEmpty()) return new int[0];
            sql.append("UPDATE ").append(TABLENAME2).append(" SET unqid=:unqid, uuidMCode=:uuidMCode, pname=:pname, sword=:sword, wheel=:wheel, btPl=:btPl, btHero=:btHero, btPart=:btPart, btProp=:btProp, btNpc=:btNpc, btEmail=:btEmail, phone=:phone, createtime=:createtime, lasttime=:lasttime, statusActivity=:statusActivity, score4Endless=:score4Endless, chn=:chn, chnSub=:chnSub, fight4hero=:fight4hero, fight4part=:fight4part, npcStars=:npcStars WHERE pcid=:pcid");
            return super.batchUpdate2(sql.toString(), players);
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
                "	`pcid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`unqid`  VARCHAR(128) NOT NULL," +
                "	`uuidMCode`  VARCHAR(128) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`sword`  INT(11) NOT NULL," +
                "	`wheel`  INT(11) NOT NULL," +
                "	`btPl`  BLOB NOT NULL," +
                "	`btHero`  BLOB NOT NULL," +
                "	`btPart`  BLOB NOT NULL," +
                "	`btProp`  BLOB NOT NULL," +
                "	`btNpc`  BLOB NOT NULL," +
                "	`btEmail`  BLOB NOT NULL," +
                "	`phone`  VARCHAR(32) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	`statusActivity`  INT(11) NOT NULL," +
                "	`score4Endless`  INT(11) NOT NULL," +
                "	`chn`  VARCHAR(32) NOT NULL," +
                "	`chnSub`  VARCHAR(32) NOT NULL," +
                "	`fight4hero`  INT(11) NOT NULL," +
                "	`fight4part`  INT(11) NOT NULL," +
                "	`npcStars`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`pcid`)," +
                "	UNIQUE KEY `unqid` (`unqid`)," +
                "	UNIQUE KEY `pname` (`pname`)," +
                "	KEY `uuidMCode` (`uuidMCode`)," +
                "	KEY `chn` (`chn`)," +
                "	KEY `chnSub` (`chnSub`)" +
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
                "	`pcid`  INT(11) NOT NULL AUTO_INCREMENT," +
                "	`unqid`  VARCHAR(128) NOT NULL," +
                "	`uuidMCode`  VARCHAR(128) NOT NULL," +
                "	`pname`  VARCHAR(32) NOT NULL," +
                "	`sword`  INT(11) NOT NULL," +
                "	`wheel`  INT(11) NOT NULL," +
                "	`btPl`  BLOB NOT NULL," +
                "	`btHero`  BLOB NOT NULL," +
                "	`btPart`  BLOB NOT NULL," +
                "	`btProp`  BLOB NOT NULL," +
                "	`btNpc`  BLOB NOT NULL," +
                "	`btEmail`  BLOB NOT NULL," +
                "	`phone`  VARCHAR(32) NOT NULL," +
                "	`createtime`  DATETIME NOT NULL," +
                "	`lasttime`  DATETIME NOT NULL," +
                "	`statusActivity`  INT(11) NOT NULL," +
                "	`score4Endless`  INT(11) NOT NULL," +
                "	`chn`  VARCHAR(32) NOT NULL," +
                "	`chnSub`  VARCHAR(32) NOT NULL," +
                "	`fight4hero`  INT(11) NOT NULL," +
                "	`fight4part`  INT(11) NOT NULL," +
                "	`npcStars`  INT(11) NOT NULL," +
                "	PRIMARY KEY (`pcid`)," +
                "	KEY `unqid` (`unqid`)," +
                "	KEY `pname` (`pname`)," +
                "	KEY `uuidMCode` (`uuidMCode`)," +
                "	KEY `chn` (`chn`)," +
                "	KEY `chnSub` (`chnSub`)" +
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
