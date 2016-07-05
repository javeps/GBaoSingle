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

import static com.gb.db.bean.Player.Col;

//gbosng_design - player
@SuppressWarnings({"rawtypes", "unchecked", "static-access"})
public abstract class PlayerInternal extends InternalSupport{
    static Log log = LogFactory.getLog(PlayerInternal.class);
    public static CacheModel cacheModel = NO_CACHE;

    // public static int LASTID = 0;
    private static AtomicInteger LASTID = new AtomicInteger();

    public PlayerInternal(){}

    public static PlayerDAO DAO(){
        return PlayerEntity.PlayerDAO();
    }


    private static int MAX = 0;
    public static void setFixedMAX(int num) {
        MAX = num;
        FIXED = new Player[MAX];
    }
    private static Player[] FIXED = new Player[MAX];
    public static final Map<Integer, Player> vars = newSortedMap();
    public static final Map<String, Integer> varsByUnqid = newSortedMap();
    public static final Map<String, Set<Integer>> varsByChnSub = newSortedMap();
    public static final Map<String, Integer> varsByPname = newSortedMap();
    public static final Map<String, Set<Integer>> varsByChn = newSortedMap();
    public static final Map<String, Set<Integer>> varsByUuidMCode = newSortedMap();

    private static final List<Player> fixedCache = newList();

    public static void put(Player player, boolean force){
        if(player == null || player.pcid <= 0) return ;

        int pcid = player.pcid;
        if (cacheModel == STATIC_CACHE) {
            if (pcid > 0 && pcid <= FIXED.length) {
                if (FIXED[pcid - 1] == null || !FIXED[pcid - 1].equals(player))
                	FIXED[pcid - 1] = player;
                if (!fixedCache.contains(player))
                	fixedCache.add(player);
            }
        } else {
            vars.put(pcid, player);
        }

        { // 单-唯一索引 remove old index unqid
          Object ov = player.oldVal(Col.unqid);
          if(ov != null)
              varsByUnqid.remove(ov);
          if(ov != null || force){ // put new index
            String unqid = player.getUnqid();
            varsByUnqid.put(unqid, pcid);
          }
        }

        { // 单-非唯一索引 remove old index chnSub
          Object ov = player.oldVal(Col.chnSub);
          if(ov != null) {
              String _val = (String) ov;
              Set m1 = varsByChnSub.get(_val);
              if(m1 != null) {
                  m1.remove(pcid);
                  if(m1.isEmpty())
                      varsByChnSub.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            String chnSub = player.getChnSub();
            Set m1 = varsByChnSub.get(chnSub);
            if(m1 == null){
                m1 = newSortedSet();
                varsByChnSub.put(chnSub, m1);
            }
            m1.add(pcid);
          }
        }

        { // 单-唯一索引 remove old index pname
          Object ov = player.oldVal(Col.pname);
          if(ov != null)
              varsByPname.remove(ov);
          if(ov != null || force){ // put new index
            String pname = player.getPname();
            varsByPname.put(pname, pcid);
          }
        }

        { // 单-非唯一索引 remove old index chn
          Object ov = player.oldVal(Col.chn);
          if(ov != null) {
              String _val = (String) ov;
              Set m2 = varsByChn.get(_val);
              if(m2 != null) {
                  m2.remove(pcid);
                  if(m2.isEmpty())
                      varsByChn.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            String chn = player.getChn();
            Set m2 = varsByChn.get(chn);
            if(m2 == null){
                m2 = newSortedSet();
                varsByChn.put(chn, m2);
            }
            m2.add(pcid);
          }
        }

        { // 单-非唯一索引 remove old index uuidMCode
          Object ov = player.oldVal(Col.uuidMCode);
          if(ov != null) {
              String _val = (String) ov;
              Set m3 = varsByUuidMCode.get(_val);
              if(m3 != null) {
                  m3.remove(pcid);
                  if(m3.isEmpty())
                      varsByUuidMCode.remove(_val);
              }
          }
          if(ov != null || force){ // put new index
            String uuidMCode = player.getUuidMCode();
            Set m3 = varsByUuidMCode.get(uuidMCode);
            if(m3 == null){
                m3 = newSortedSet();
                varsByUuidMCode.put(uuidMCode, m3);
            }
            m3.add(pcid);
          }
        }

        // LASTID = pcid > LASTID ? pcid : LASTID;
        if (pcid > LASTID.get()) LASTID.set(pcid);
    }

    public static void clear(){
        varsByUnqid.clear();
        varsByChnSub.clear();
        varsByPname.clear();
        varsByChn.clear();
        varsByUuidMCode.clear();
        FIXED = new Player[MAX];
        fixedCache.clear();
        vars.clear();
        LASTID.set(0);
    }

    public static int count(){
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(String TABLENAME2){
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return count(DAO, TABLENAME2);
    }

    public static int count(PlayerDAO DAO){
        return count(DAO, DAO.TABLENAME);
    }

    public static int count(PlayerDAO DAO, String TABLENAME2){
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

    public static void relocate(PlayerDAO DAO, String TABLENAME2) {
        DAO().TABLENAME = TABLENAME2;
    }

    public static String createTableYy() {
        PlayerDAO DAO = DAO();
        return createTableYy(DAO);
    }

    public static String createTableYy(PlayerDAO DAO) {
        String TABLENAME2 = DAO.TABLEYY();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableMm() {
        PlayerDAO DAO = DAO();
        return createTableMm(DAO);
    }

    public static String createTableMm(PlayerDAO DAO) {
        String TABLENAME2 = DAO.TABLEMM();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static String createTableDd() {
        PlayerDAO DAO = DAO();
        return createTableDd(DAO);
    }

    public static String createTableDd(PlayerDAO DAO) {
        String TABLENAME2 = DAO.TABLEDD();
        createTable(DAO, TABLENAME2);
        return TABLENAME2;
    }

    public static void createTable(String TABLENAME2) {
        PlayerDAO DAO = DAO();
        DAO.createTable(TABLENAME2);
    }

    public static void createTable(PlayerDAO DAO) {
        DAO.createTable(DAO.TABLENAME);
    }

    public static void createTable(PlayerDAO DAO, String TABLENAME2) {
        DAO.createTable(TABLENAME2);
    }

    public static void createNoUniqueTable(String TABLENAME2) {
        PlayerDAO DAO = DAO();
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void createNoUniqueTable(PlayerDAO DAO) {
        DAO.createNoUniqueTable(DAO.TABLENAME);
    }

    public static void createNoUniqueTable(PlayerDAO DAO, String TABLENAME2) {
        DAO.createNoUniqueTable(TABLENAME2);
    }

    public static void loadAll() {
        PlayerDAO DAO = DAO();
        loadAll(DAO);
    }

    public static void loadAll(PlayerDAO DAO) {
        if( cacheModel == NO_CACHE )
            return;
        clear();
        if( cacheModel == STATIC_CACHE )
            if (FIXED == null || MAX <= 0)
                FIXED = new Player[maxId(DAO)];

        List<Player> players = DAO.selectAll();
        for (Player player : players) {
            player.reset();
            put(player, true);
        }
    }

    public static Map toMap(Player player){
        return player.toMap();
    }

    public static List<Map> toMap(List<Player> players){
        List<Map> ret = new Vector<Map>();
        for (Player player : players){
            Map e = player.toMap();
            ret.add(e);
        }
        return ret;
    }

    public static List<Player> sortZh(List<Player> players, final String key) {
        Collections.sort(players, new Comparator<Player>() {
            public int compare(Player o1, Player o2) {
                return o1.compareZhTo(o2, key);
            }
        });
        return players;
    }

    public static List<Player> sort(List<Player> players, final String key) {
        Collections.sort(players, new Comparator<Player>() {
            public int compare(Player o1, Player o2) {
                return o1.compareTo(o2, key);
            }
        });
        return players;
    }

    public static List<Player> sort(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.pcid;
                Object v2 = o2.pcid;
                return compareTo(v1, v2);
            }
        });
        return players;
    }

    public static List<Player> sortReverse(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.pcid;
                Object v2 = o2.pcid;
                return 0 - compareTo(v1, v2);
            }
        });
        return players;
    }

    public static List<Player> sortUnqid(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getUnqid();
                Object v2 = o2.getUnqid();
                return compareTo(v1, v2);
            }
        });
        return players;
    }

    public static List<Player> sortUnqidRo(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getUnqid();
                Object v2 = o2.getUnqid();
                return 0 - compareTo(v1, v2);
            };
        });
        return players;
    }

    public static List<Player> sortChnSub(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getChnSub();
                Object v2 = o2.getChnSub();
                return compareTo(v1, v2);
            }
        });
        return players;
    }

    public static List<Player> sortChnSubRo(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getChnSub();
                Object v2 = o2.getChnSub();
                return 0 - compareTo(v1, v2);
            };
        });
        return players;
    }

    public static List<Player> sortPname(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getPname();
                Object v2 = o2.getPname();
                return compareTo(v1, v2);
            }
        });
        return players;
    }

    public static List<Player> sortPnameRo(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getPname();
                Object v2 = o2.getPname();
                return 0 - compareTo(v1, v2);
            };
        });
        return players;
    }

    public static List<Player> sortChn(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getChn();
                Object v2 = o2.getChn();
                return compareTo(v1, v2);
            }
        });
        return players;
    }

    public static List<Player> sortChnRo(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getChn();
                Object v2 = o2.getChn();
                return 0 - compareTo(v1, v2);
            };
        });
        return players;
    }

    public static List<Player> sortUuidMCode(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getUuidMCode();
                Object v2 = o2.getUuidMCode();
                return compareTo(v1, v2);
            }
        });
        return players;
    }

    public static List<Player> sortUuidMCodeRo(List<Player> players){
        Collections.sort(players, new Comparator<Player>(){
            public int compare(Player o1, Player o2) {
                Object v1 = o1.getUuidMCode();
                Object v2 = o2.getUuidMCode();
                return 0 - compareTo(v1, v2);
            };
        });
        return players;
    }

    public static Player insert(Player player) {
        PlayerDAO DAO = DAO();
        return insert(DAO, player, DAO.TABLENAME);
    }

    public static Player insert(PlayerDAO DAO, Player player) {
        return insert(DAO, player, DAO.TABLENAME);
    }

    public static Player insert(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return insert(DAO, player, TABLENAME2);
    }

    public static Player insert(PlayerDAO DAO, Player player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }

        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert(player, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player.pcid = n;
        if(cacheModel != NO_CACHE) put(player, true);

        return player;
    }

    public static Player asyncInsert(Player player) {
        PlayerDAO DAO = DAO();
        return asyncInsert(DAO, player, DAO.TABLENAME);
    }
    public static Player asyncInsert(PlayerDAO DAO, Player player) {
        return asyncInsert(DAO, player, DAO.TABLENAME);
    }
    public static Player asyncInsert(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return asyncInsert(DAO, player, TABLENAME2);
    }
    public static Player asyncInsert(PlayerDAO DAO, Player player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert(player, TABLENAME2);
        }
        n = LASTID.incrementAndGet();
        player.pcid = n;
        if(cacheModel != NO_CACHE) put(player, true);
        return player;
    }
    public static Player insert2(Player player) {
        PlayerDAO DAO = DAO();
        return insert2(DAO, player, DAO.TABLENAME);
    }

    public static Player insert2(PlayerDAO DAO, Player player) {
        return insert2(DAO, player, DAO.TABLENAME);
    }

    public static Player insert2(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return insert2(DAO, player, TABLENAME2);
    }

    public static Player insert2(PlayerDAO DAO, Player player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = 0;
        if(cacheModel != FULL_MEMORY){
            n = DAO.insert2(player, TABLENAME2);
            if(n <= 0) return null;
        }else{
            n = LASTID.incrementAndGet();
            // n = LASTID + 1;
        }

        player.pcid = n;
        if(cacheModel != NO_CACHE) put(player, true);

        return player;
    }

    public static Player asyncInsert2(Player player) {
        PlayerDAO DAO = DAO();
        return asyncInsert2(DAO, player, DAO.TABLENAME);
    }
    public static Player asyncInsert2(PlayerDAO DAO, Player player) {
        return asyncInsert2(DAO, player, DAO.TABLENAME);
    }
    public static Player asyncInsert2(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return asyncInsert2(DAO, player, TABLENAME2);
    }
    public static Player asyncInsert2(PlayerDAO DAO, Player player, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        int n = LASTID.incrementAndGet();
        player.pcid = n;
        if(cacheModel != FULL_MEMORY) {
            DAO.asyncInsert2(player, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) put(player, true);
        return player;
    }
    public static int[] insert(List<Player> players) {
        PlayerDAO DAO = DAO();
        return insert(DAO, players, DAO.TABLENAME);
    }

    public static int[] insert(PlayerDAO DAO, List<Player> players) {
        return insert(DAO, players, DAO.TABLENAME);
    }

    public static int[] insert(List<Player> players, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return insert(DAO, players, TABLENAME2);
    }

    public static int[] insert(PlayerDAO DAO, List<Player> players, String TABLENAME2) {
        if (cacheModel == STATIC_CACHE && LASTID.intValue() >= MAX) {
            log.error("The cacheModel = STATIC_CACHE is Full.");
            return null;
        }
        if(cacheModel == NO_CACHE){
            int[] r2 = DAO.insert(players, TABLENAME2);
            int n = 0;
            for(Player player : players){
                player.pcid = r2[n++];
            }
            return r2;
        }

        int[] ret = new int[players.size()];
        int n = 0;
        for(Player player : players){
            player = insert(DAO, player, TABLENAME2);
            ret[n++] = (player == null) ? 0 : (int)player.pcid;
        }
        return ret;
    }

    public static int delete(Player player) {
        int pcid = player.pcid;
        PlayerDAO DAO = DAO();
        return delete(DAO, pcid, DAO.TABLENAME);
    }

    public static int delete(int pcid) {
        PlayerDAO DAO = DAO();
        return delete(DAO, pcid, DAO.TABLENAME);
    }

    public static int delete(PlayerDAO DAO, int pcid) {
        return delete(DAO, pcid, DAO.TABLENAME);
    }

    public static int delete(int pcid, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return delete(DAO, pcid, TABLENAME2);
    }

    public static int delete(PlayerDAO DAO, int pcid, String TABLENAME2) {
        int n = 1;
        if(cacheModel != FULL_MEMORY){
            n = DAO.deleteByKey(pcid, TABLENAME2);
            //if(n <= 0) return 0;
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(pcid);
        }
        return n;
    }

    public static void asyncDelete(Player player) {
        int pcid = player.pcid;
        PlayerDAO DAO = DAO();
        asyncDelete(DAO, pcid, DAO.TABLENAME);
    }

    public static void asyncDelete(int pcid) {
        PlayerDAO DAO = DAO();
        asyncDelete(DAO, pcid, DAO.TABLENAME);
    }

    public static void asyncDelete(PlayerDAO DAO, int pcid) {
        asyncDelete(DAO, pcid, DAO.TABLENAME);
    }

    public static void asyncDelete(int pcid, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        asyncDelete(DAO, pcid, TABLENAME2);
    }

    public static void asyncDelete(PlayerDAO DAO, int pcid, String TABLENAME2) {
        if(cacheModel != FULL_MEMORY){
            DAO.asyncDeleteByKey(pcid, TABLENAME2);
        }
        if(cacheModel != NO_CACHE) {
            deleteFromMemory(pcid);
        }
    }

    public static int[] delete(int[] pcids) {
        PlayerDAO DAO = DAO();
        return delete(DAO, pcids, DAO.TABLENAME);
    }

    public static int[] delete(PlayerDAO DAO, int[] pcids) {
        return delete(DAO, pcids, DAO.TABLENAME);
    }

    public static int[] delete(int[] pcids,String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return delete(DAO, pcids, TABLENAME2);
    }

    public static int[] delete(PlayerDAO DAO, int[] pcids,String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.deleteByKey(pcids, TABLENAME2);
        }
        int[] ret = new int[pcids.length];
        int n = 0;
        for(int pcid : pcids){
            ret[n++] = delete(DAO, pcid, TABLENAME2);
        }
        return ret;
    }

    public static int deleteIn(List<Integer> keys) {
        PlayerDAO DAO = DAO();
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, PlayerDAO DAO) {
        return deleteIn(keys, DAO, DAO.TABLENAME);
    }

    public static int deleteIn(List<Integer> keys, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return deleteIn(keys, DAO, TABLENAME2);
    }

    public static int deleteIn(final List<Integer> keys, final PlayerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return 0;
        int result = DAO.deleteInKeys(keys, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Integer pcid : keys){
                deleteFromMemory(pcid);
            }
        }
        return result;
    }

    public static int deleteWith(List<Player> beans) {
        PlayerDAO DAO = DAO();
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player> beans, PlayerDAO DAO) {
        return deleteWith(beans, DAO, DAO.TABLENAME);
    }

    public static int deleteWith(List<Player> beans, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return deleteWith(beans, DAO, TABLENAME2);
    }

    public static int deleteWith(final List<Player> beans, final PlayerDAO DAO, final String TABLENAME2) {
        if(beans == null || beans.isEmpty()) return 0;
        int result = DAO.deleteInBeans(beans, TABLENAME2);
        if(cacheModel != NO_CACHE) {
            for(Player player : beans){
                int pcid = player.pcid;
                deleteFromMemory(pcid);
            }
        }
        return result;
    }

    public static List<Player> getAll() {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player> getAll(PlayerDAO DAO) {
        return getAll(DAO, DAO.TABLENAME);
    }

    public static List<Player> getAll(String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getAll(DAO, TABLENAME2);
    }

    public static List<Player> getAll(final PlayerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player> result = getNoSortAll(DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player> getNoSortAll() {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player> getNoSortAll(PlayerDAO DAO) {
        return getNoSortAll(DAO, DAO.TABLENAME);
    }

    public static List<Player> getNoSortAll(String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortAll(DAO, TABLENAME2);
    }

    public static List<Player> getNoSortAll(final PlayerDAO DAO, final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectAll(TABLENAME2);
        } else if (cacheModel == STATIC_CACHE) {
            List<Player> result = newList();
            result.addAll(fixedCache);
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player> result = newList();
            result.addAll(vars.values());
            return result;
        }
    }

    public static Set<Integer> memoryKeys(){
        if (cacheModel == STATIC_CACHE) {
            Set<Integer> result = newSet();
            int max = FIXED.length;
            for (int i = 0; i < max; i++) {
                Player player = FIXED[i];
                if (player != null) result.add((int)(i + 1));
            }
            return result;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.keySet();
        }
    }

    public static Collection<Player> memoryValues(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return vars.values();
        }
    }

    public static List<Player> getAllNotCopy(){
        if (cacheModel == STATIC_CACHE) {
            return fixedCache;
        } else { // FULL_CACHE || FULL_MEMORY 
            return new Vector(vars.values());
        }
    }

    public static List<Integer> getPks() {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(PlayerDAO DAO) {
        return getPks(DAO, DAO.TABLENAME);
    }

    public static List<Integer> getPks(String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getPks(DAO, TABLENAME2);
    }

    public static List<Integer> getPks(final PlayerDAO DAO, final String TABLENAME2) {
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
        PlayerDAO DAO = DAO();
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(PlayerDAO DAO) {
        return getInIndex(DAO, DAO.TABLENAME);
    }

    public static List<Map> getInIndex(String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return getInIndex(DAO, TABLENAME2);
    }

    public static List<Map> getInIndex(final PlayerDAO DAO, final String TABLENAME2) {
        return DAO.selectInIndex(TABLENAME2);
    }

    public static List<Player> getIn(List<Integer> keys) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player> getIn(List<Integer> keys, PlayerDAO DAO) {
        return getIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player> getIn(List<Integer> keys, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getIn(keys, DAO, TABLENAME2);
    }

    public static List<Player> getIn(final List<Integer> keys, final PlayerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player> result = getNoSortIn(keys, DAO, TABLENAME2);
            return result;
        }
    }

    public static List<Player> getNoSortIn(List<Integer> keys) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player> getNoSortIn(List<Integer> keys, PlayerDAO DAO) {
        return getNoSortIn(keys, DAO, DAO.TABLENAME);
    }

    public static List<Player> getNoSortIn(List<Integer> keys, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getNoSortIn(keys, DAO, TABLENAME2);
    }

    public static List<Player> getNoSortIn(final List<Integer> keys, final PlayerDAO DAO, final String TABLENAME2) {
        if(keys == null || keys.isEmpty()) return newList();
        if( cacheModel == NO_CACHE ){
            return DAO.selectIn(keys, TABLENAME2);
        } else { // STATIC_CACHE || FULL_CACHE || FULL_MEMORY
            List<Player> result = newList();
            for (int key : keys) {
                Player player = getByKeyFromMemory(key);
                if( player == null ) continue;
                result.add(player);
            }
            return result;
        }
    }

    public static List<Player> getLast(int num) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player> getLast(PlayerDAO DAO, int num) {
        return getLast(DAO, num, DAO.TABLENAME);
    }

    public static List<Player> getLast(int num, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLast(DAO, num, TABLENAME2);
    }

    public static List<Player> getLast(final PlayerDAO DAO, final int num, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectLast(num, TABLENAME2);
        } else { // FULL_CACHE or FULL_MEMORY
            List<Player> result = newList();
            List<Player> mvars = getAll(DAO, TABLENAME2);
            if( mvars.size() > num ){
                result = mvars.subList(mvars.size() - num, mvars.size());
            }else{
                result.addAll(mvars);
            }
            return result;
        }
    }

    public static Player last() {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, DAO.TABLENAME);
    }

    public static Player last(PlayerDAO DAO) {
        return last(DAO, DAO.TABLENAME);
    }

    public static Player last(String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return last(DAO, TABLENAME2);
    }

    public static Player last(final PlayerDAO DAO, final String TABLENAME2) {
        Player result = null;
        if( cacheModel == NO_CACHE ){
            return DAO.last(TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            int id = LASTID.get();
            result = getByKey(DAO, id, TABLENAME2);
        }
        return result;
    }

    public static int maxId() {
        PlayerDAO DAO = DAO();
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(PlayerDAO DAO) {
        return maxId(DAO, DAO.TABLENAME);
    }

    public static int maxId(String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return maxId(DAO, TABLENAME2);
    }

    public static int maxId(final PlayerDAO DAO, final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.maxId(TABLENAME2);
        }
        // FULL_CACHE || FULL_MEMORY || STATIC_CACHE
        int id = LASTID.get();
        if(id > 0) return id;
        return DAO.maxId(TABLENAME2);
    }

    public static List<Player> getGtKey(int pcid) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Player> getGtKey(PlayerDAO DAO, int pcid) {
        return getGtKey(DAO, pcid, DAO.TABLENAME);
    }

    public static List<Player> getGtKey(int pcid, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getGtKey(DAO, pcid, TABLENAME2);
    }

    public static List<Player> getGtKey(final PlayerDAO DAO, final int pcid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectGtKey(pcid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player> result = newList();
            List<Player> players = getAll();
            for (Player player : players) {
                if(player.pcid <= pcid) continue;
                result.add(player);
            }
            return result;
        }
    }

    public static Player getByKey(int pcid) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, pcid, DAO.TABLENAME);
    }

    public static Future<Player> asyncGetByKey(final int pcid) {
        Future<Player> f = Async.exec(new Callable<Player>() {
            public Player call() throws Exception {
                return getByKey(pcid);
            }
        });
        return f;
    }

    public static Player getByKey(PlayerDAO DAO, int pcid) {
        return getByKey(DAO, pcid, DAO.TABLENAME);
    }

    public static Player getByKey(int pcid, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByKey(DAO, pcid, TABLENAME2);
    }

    public static Player getByKey(final PlayerDAO DAO, final int pcid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectByKey(pcid, TABLENAME2);
        }
        return getByKeyFromMemory(pcid);
    }

    public static Player getByKeyFromMemory(final int pcid) {
        if (cacheModel == STATIC_CACHE) {
            if (pcid < 1 || FIXED == null || FIXED.length < pcid) return null;
            return FIXED[pcid - 1];
        } else if (cacheModel == FULL_CACHE || cacheModel == FULL_MEMORY) {
            return vars.get(pcid);
        }
        return null;
    }

    public static Player deleteFromMemory(final int pcid) {
        Player player;
        if (cacheModel == STATIC_CACHE) {
            if (pcid < 1 || FIXED == null || FIXED.length < pcid || FIXED[pcid-1]==null) return null;
            player = FIXED[pcid - 1];
            FIXED[pcid - 1] = null;
            fixedCache.remove(player);
        } else {
            player = vars.remove(pcid);
        }
        if(player == null) return null;

        String unqid = player.getUnqid();
        varsByUnqid.remove(unqid);

        String chnSub = player.getChnSub();
        Set m1 = varsByChnSub.get(chnSub);
        if(m1 != null) {
            m1.remove(pcid);
            if(m1.isEmpty())
                varsByChnSub.remove(chnSub);
        }

        String pname = player.getPname();
        varsByPname.remove(pname);

        String chn = player.getChn();
        Set m2 = varsByChn.get(chn);
        if(m2 != null) {
            m2.remove(pcid);
            if(m2.isEmpty())
                varsByChn.remove(chn);
        }

        String uuidMCode = player.getUuidMCode();
        Set m3 = varsByUuidMCode.get(uuidMCode);
        if(m3 != null) {
            m3.remove(pcid);
            if(m3.isEmpty())
                varsByUuidMCode.remove(uuidMCode);
        }

        return player;
    }

    public static List<Player> getByPage(int page, int size) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player> getByPage(PlayerDAO DAO, int page, int size) {
        return getByPage(DAO, page, size, DAO.TABLENAME);
    }

    public static List<Player> getByPage(int page, int size, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPage(DAO, page, size, TABLENAME2);
    }

    public static List<Player> getByPage(final PlayerDAO DAO, final int page, final int size,final String TABLENAME2) {
        int begin = page * size;
        int num = size;
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPage(begin, num, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY 
            List<Player> result = newList();
            List<Player> v = getAll(DAO, TABLENAME2);
            result = SqlEx.getPage(v, page, size);
            return result;
        }
    }

    public static int pageCount(int size) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(PlayerDAO DAO, int size) {
        return pageCount(DAO, size, DAO.TABLENAME);
    }

    public static int pageCount(int size, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return pageCount(DAO, size, TABLENAME2);
    }

    public static int pageCount(final PlayerDAO DAO, final int size,final String TABLENAME2) {
        int v = 0;
        if( cacheModel == NO_CACHE ){
            v = DAO.count(TABLENAME2);
        }else{
            v = count(DAO, TABLENAME2);
        }
        return SqlEx.pageCount(v, size);
    }

    public static Player getByUnqid(String unqid) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static Player getByUnqid(PlayerDAO DAO, String unqid) {
        return getByUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static Player getByUnqid(String unqid, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUnqid(DAO, unqid, TABLENAME2);
    }

    public static Player getByUnqid(final PlayerDAO DAO, final String unqid,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUnqid(unqid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer pcid = varsByUnqid.get(unqid);
            if(pcid == null) return null;
            Player result = getByKey(DAO, pcid, TABLENAME2);
            if(result == null) return null;
            if(!result.getUnqid().equals(unqid)){
                varsByUnqid.remove(unqid);
                return null;
            }
            return result;
        }
    }

    public static int countLikeUnqid(String unqid) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static int countLikeUnqid(PlayerDAO DAO, String unqid) {
        return countLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static int countLikeUnqid(String unqid, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUnqid(DAO, unqid, TABLENAME2);
    }

    public static int countLikeUnqid(final PlayerDAO DAO, final String unqid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeUnqid(unqid, TABLENAME2);
        }
        List<Player> players = getLikeUnqid(DAO, unqid, TABLENAME2);
        return players.size();
    }

    public static List<Player> getLikeUnqid(String unqid) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static List<Player> getLikeUnqid(PlayerDAO DAO, String unqid) {
        return getLikeUnqid(DAO, unqid, DAO.TABLENAME);
    }

    public static List<Player> getLikeUnqid(String unqid, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUnqid(DAO, unqid, TABLENAME2);
    }

    public static List<Player> getLikeUnqid(final PlayerDAO DAO, final String unqid,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeUnqid(unqid, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player> result = newList();
            List<Player> players = getAll(DAO, TABLENAME2);
            for(Player e : players){
                String _var = e.getUnqid();
                if(_var.indexOf(unqid) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static int countByChnSub(String chnSub) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByChnSub(DAO, chnSub, DAO.TABLENAME);
    }

    public static int countByChnSub(PlayerDAO DAO, String chnSub) {
        return countByChnSub(DAO, chnSub, DAO.TABLENAME);
    }

    public static int countByChnSub(String chnSub, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByChnSub(DAO, chnSub, TABLENAME2);
    }

    public static int countByChnSub(final PlayerDAO DAO, final String chnSub,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByChnSub(chnSub, TABLENAME2);
        }
        List<Player> players = getByChnSub(DAO, chnSub, TABLENAME2);
        return players.size();
    }

    public static List<Player> getByChnSub(String chnSub) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByChnSub(DAO, chnSub, DAO.TABLENAME);
    }

    public static List<Player> getByChnSub(PlayerDAO DAO, String chnSub) {
        return getByChnSub(DAO, chnSub, DAO.TABLENAME);
    }

    public static List<Player> getByChnSub(String chnSub, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByChnSub(DAO, chnSub, TABLENAME2);
    }

    public static List<Player> getByChnSub(final PlayerDAO DAO, final String chnSub,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByChnSub(chnSub, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Player> result = newList();
            Set<Integer> m1 = varsByChnSub.get(chnSub);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Player e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                String _chnSub = e.getChnSub();
                if(!_chnSub.equals(chnSub)){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countLikeChnSub(String chnSub) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeChnSub(DAO, chnSub, DAO.TABLENAME);
    }

    public static int countLikeChnSub(PlayerDAO DAO, String chnSub) {
        return countLikeChnSub(DAO, chnSub, DAO.TABLENAME);
    }

    public static int countLikeChnSub(String chnSub, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeChnSub(DAO, chnSub, TABLENAME2);
    }

    public static int countLikeChnSub(final PlayerDAO DAO, final String chnSub,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeChnSub(chnSub, TABLENAME2);
        }
        List<Player> players = getLikeChnSub(DAO, chnSub, TABLENAME2);
        return players.size();
    }

    public static List<Player> getLikeChnSub(String chnSub) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeChnSub(DAO, chnSub, DAO.TABLENAME);
    }

    public static List<Player> getLikeChnSub(PlayerDAO DAO, String chnSub) {
        return getLikeChnSub(DAO, chnSub, DAO.TABLENAME);
    }

    public static List<Player> getLikeChnSub(String chnSub, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeChnSub(DAO, chnSub, TABLENAME2);
    }

    public static List<Player> getLikeChnSub(final PlayerDAO DAO, final String chnSub,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeChnSub(chnSub, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player> result = newList();
            List<Player> players = getAll(DAO, TABLENAME2);
            for(Player e : players){
                String _var = e.getChnSub();
                if(_var.indexOf(chnSub) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Player getByPname(String pname) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPname(DAO, pname, DAO.TABLENAME);
    }

    public static Player getByPname(PlayerDAO DAO, String pname) {
        return getByPname(DAO, pname, DAO.TABLENAME);
    }

    public static Player getByPname(String pname, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByPname(DAO, pname, TABLENAME2);
    }

    public static Player getByPname(final PlayerDAO DAO, final String pname,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByPname(pname, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            Integer pcid = varsByPname.get(pname);
            if(pcid == null) return null;
            Player result = getByKey(DAO, pcid, TABLENAME2);
            if(result == null) return null;
            if(!result.getPname().equals(pname)){
                varsByPname.remove(pname);
                return null;
            }
            return result;
        }
    }

    public static int countLikePname(String pname) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikePname(DAO, pname, DAO.TABLENAME);
    }

    public static int countLikePname(PlayerDAO DAO, String pname) {
        return countLikePname(DAO, pname, DAO.TABLENAME);
    }

    public static int countLikePname(String pname, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikePname(DAO, pname, TABLENAME2);
    }

    public static int countLikePname(final PlayerDAO DAO, final String pname,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikePname(pname, TABLENAME2);
        }
        List<Player> players = getLikePname(DAO, pname, TABLENAME2);
        return players.size();
    }

    public static List<Player> getLikePname(String pname) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikePname(DAO, pname, DAO.TABLENAME);
    }

    public static List<Player> getLikePname(PlayerDAO DAO, String pname) {
        return getLikePname(DAO, pname, DAO.TABLENAME);
    }

    public static List<Player> getLikePname(String pname, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikePname(DAO, pname, TABLENAME2);
    }

    public static List<Player> getLikePname(final PlayerDAO DAO, final String pname,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikePname(pname, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player> result = newList();
            List<Player> players = getAll(DAO, TABLENAME2);
            for(Player e : players){
                String _var = e.getPname();
                if(_var.indexOf(pname) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static int countByChn(String chn) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByChn(DAO, chn, DAO.TABLENAME);
    }

    public static int countByChn(PlayerDAO DAO, String chn) {
        return countByChn(DAO, chn, DAO.TABLENAME);
    }

    public static int countByChn(String chn, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByChn(DAO, chn, TABLENAME2);
    }

    public static int countByChn(final PlayerDAO DAO, final String chn,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByChn(chn, TABLENAME2);
        }
        List<Player> players = getByChn(DAO, chn, TABLENAME2);
        return players.size();
    }

    public static List<Player> getByChn(String chn) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByChn(DAO, chn, DAO.TABLENAME);
    }

    public static List<Player> getByChn(PlayerDAO DAO, String chn) {
        return getByChn(DAO, chn, DAO.TABLENAME);
    }

    public static List<Player> getByChn(String chn, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByChn(DAO, chn, TABLENAME2);
    }

    public static List<Player> getByChn(final PlayerDAO DAO, final String chn,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByChn(chn, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Player> result = newList();
            Set<Integer> m1 = varsByChn.get(chn);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Player e = getByKey(DAO, key, TABLENAME2);
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
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeChn(DAO, chn, DAO.TABLENAME);
    }

    public static int countLikeChn(PlayerDAO DAO, String chn) {
        return countLikeChn(DAO, chn, DAO.TABLENAME);
    }

    public static int countLikeChn(String chn, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeChn(DAO, chn, TABLENAME2);
    }

    public static int countLikeChn(final PlayerDAO DAO, final String chn,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeChn(chn, TABLENAME2);
        }
        List<Player> players = getLikeChn(DAO, chn, TABLENAME2);
        return players.size();
    }

    public static List<Player> getLikeChn(String chn) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeChn(DAO, chn, DAO.TABLENAME);
    }

    public static List<Player> getLikeChn(PlayerDAO DAO, String chn) {
        return getLikeChn(DAO, chn, DAO.TABLENAME);
    }

    public static List<Player> getLikeChn(String chn, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeChn(DAO, chn, TABLENAME2);
    }

    public static List<Player> getLikeChn(final PlayerDAO DAO, final String chn,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeChn(chn, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player> result = newList();
            List<Player> players = getAll(DAO, TABLENAME2);
            for(Player e : players){
                String _var = e.getChn();
                if(_var.indexOf(chn) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static int countByUuidMCode(String uuidMCode) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByUuidMCode(DAO, uuidMCode, DAO.TABLENAME);
    }

    public static int countByUuidMCode(PlayerDAO DAO, String uuidMCode) {
        return countByUuidMCode(DAO, uuidMCode, DAO.TABLENAME);
    }

    public static int countByUuidMCode(String uuidMCode, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countByUuidMCode(DAO, uuidMCode, TABLENAME2);
    }

    public static int countByUuidMCode(final PlayerDAO DAO, final String uuidMCode,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countByUuidMCode(uuidMCode, TABLENAME2);
        }
        List<Player> players = getByUuidMCode(DAO, uuidMCode, TABLENAME2);
        return players.size();
    }

    public static List<Player> getByUuidMCode(String uuidMCode) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUuidMCode(DAO, uuidMCode, DAO.TABLENAME);
    }

    public static List<Player> getByUuidMCode(PlayerDAO DAO, String uuidMCode) {
        return getByUuidMCode(DAO, uuidMCode, DAO.TABLENAME);
    }

    public static List<Player> getByUuidMCode(String uuidMCode, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getByUuidMCode(DAO, uuidMCode, TABLENAME2);
    }

    public static List<Player> getByUuidMCode(final PlayerDAO DAO, final String uuidMCode,final String TABLENAME2) {
        if( cacheModel == NO_CACHE ){
            return DAO.selectByUuidMCode(uuidMCode, TABLENAME2);
        } else { //FULL_CACHE || FULL_MEMORY {
            List<Player> result = newList();
            Set<Integer> m1 = varsByUuidMCode.get(uuidMCode);
            if (m1 == null || m1.isEmpty()) return result;
            List<Integer> list = new ArrayList(m1);
            for (int key : list) {;
                Player e = getByKey(DAO, key, TABLENAME2);
                if(e == null){
                    m1.remove(key); 
                    continue;
                }
                String _uuidMCode = e.getUuidMCode();
                if(!_uuidMCode.equals(uuidMCode)){ 
                    m1.remove(key);
                    continue;
                }
                result.add(e);
            }
            return result;
        }
    }

    public static int countLikeUuidMCode(String uuidMCode) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUuidMCode(DAO, uuidMCode, DAO.TABLENAME);
    }

    public static int countLikeUuidMCode(PlayerDAO DAO, String uuidMCode) {
        return countLikeUuidMCode(DAO, uuidMCode, DAO.TABLENAME);
    }

    public static int countLikeUuidMCode(String uuidMCode, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return countLikeUuidMCode(DAO, uuidMCode, TABLENAME2);
    }

    public static int countLikeUuidMCode(final PlayerDAO DAO, final String uuidMCode,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.countLikeUuidMCode(uuidMCode, TABLENAME2);
        }
        List<Player> players = getLikeUuidMCode(DAO, uuidMCode, TABLENAME2);
        return players.size();
    }

    public static List<Player> getLikeUuidMCode(String uuidMCode) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUuidMCode(DAO, uuidMCode, DAO.TABLENAME);
    }

    public static List<Player> getLikeUuidMCode(PlayerDAO DAO, String uuidMCode) {
        return getLikeUuidMCode(DAO, uuidMCode, DAO.TABLENAME);
    }

    public static List<Player> getLikeUuidMCode(String uuidMCode, String TABLENAME2) {
        PlayerDAO DAO = (cacheModel == NO_CACHE) ? DAO() : null;
        return getLikeUuidMCode(DAO, uuidMCode, TABLENAME2);
    }

    public static List<Player> getLikeUuidMCode(final PlayerDAO DAO, final String uuidMCode,final String TABLENAME2) {
        if(cacheModel == NO_CACHE){
            return DAO.selectLikeUuidMCode(uuidMCode, TABLENAME2);
        } else { // FULL_CACHE || FULL_MEMORY
            List<Player> result = newList();
            List<Player> players = getAll(DAO, TABLENAME2);
            for(Player e : players){
                String _var = e.getUuidMCode();
                if(_var.indexOf(uuidMCode) >= 0)
                    result.add(e);
            }
            return result;
        }
    }

    public static Player update(Player player) {
        PlayerDAO DAO = DAO();
        return update(DAO, player, DAO.TABLENAME);
    }

    public static Player update(PlayerDAO DAO, Player player) {
        return update(DAO, player, DAO.TABLENAME);
    }

    public static Player update(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return update(DAO, player, TABLENAME2);
    }

    public static Player update(final PlayerDAO DAO, final Player player,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(player, false);
        }
        if(cacheModel != FULL_MEMORY){
            int n = DAO.updateByKey(player, TABLENAME2);
            if(n == -1) 
                return player;
            else if(n <= 0) 
                return null;
        }
        return player;
    }

    public static Player asyncUpdate(Player player) {
        PlayerDAO DAO = DAO();
        return asyncUpdate(DAO, player, DAO.TABLENAME);
    }

    public static Player asyncUpdate(PlayerDAO DAO, Player player) {
        return asyncUpdate(DAO, player, DAO.TABLENAME);
    }

    public static Player asyncUpdate(Player player, String TABLENAME2) {
        PlayerDAO DAO = DAO();
        return asyncUpdate(DAO, player, TABLENAME2);
    }

    public static Player asyncUpdate(final PlayerDAO DAO, final Player player,final String TABLENAME2) {
        if(cacheModel != NO_CACHE){
            put(player, false);
        }
        if(cacheModel != FULL_MEMORY){
            DAO.asyncUpdate(player, TABLENAME2);
        }
        return player;
    }

    public static void truncate(){
        clear();
        DAO().truncate();
        DAO().repair();
        DAO().optimize();
    }

    public static int inMemFlush() {
        PlayerDAO DAO = DAO();
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(PlayerDAO DAO){
        return inMemFlush(DAO, DAO.TABLENAME);
    }

    public static int inMemFlush(String TABLENAME2) {
        return inMemFlush(DAO(), TABLENAME2);
    }

    public static int inMemFlush(final PlayerDAO DAO, final String TABLENAME2) {
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

        List<Player> players = getAll();
        for (Player player : players) {
            int n = DAO.insert2(player, TABLENAME2);
            if (n > 0) result++;
        }
        return result;
    }

}
