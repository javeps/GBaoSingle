package com.gb.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.gb.content.AppContext;
import com.gb.db.bean.Rankstars;
import com.gb.db.dao.RankstarsDAO;
import com.gb.db.internal.RankstarsInternal;

//gbosng_design - rankstars
@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
public class RankstarsEntity extends RankstarsInternal{
    static Log log = LogFactory.getLog(RankstarsEntity.class);

    public static final RankstarsEntity my = new RankstarsEntity();

    static RankstarsDAO RankstarsDAO = null;
    public static RankstarsDAO RankstarsDAO() {
        if( RankstarsDAO == null)
            RankstarsDAO = new RankstarsDAO(AppContext.dsData());
        return RankstarsDAO;
    }


    public static void insertMmTry(final Rankstars rankstars) {
        RankstarsDAO DAO = RankstarsDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(rankstars, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
	/*** 执行 储存过程 process **/
	static public void exceProcessByChn(String chn) throws Exception {
		RankstarsDAO dao = RankstarsDAO();
		String sql = "";
		
		sql = "call pro_rnkStars_Chn(:chn);";
		Map params = new HashMap();
		params.put("chn", chn);
		dao.call(sql, params);
		
		// sql = "call pro_rnkStars_Chn(?);";
		// dao.call(sql, chn);
	}

	/*** 日志数据原 **/
	static RankstarsDAO logDao = null;

	public static RankstarsDAO logDao() {
		if (logDao == null)
			logDao = new RankstarsDAO(com.gb.content.AppContext.dsLog());
		return logDao;
	}

	/*** 取得某个人的排名对象 **/
	static public Rankstars getEnBy(String unqid) {
		return getEnBy(unqid, "");
	}

	/*** 取得某个人的排名对象 **/
	static public Rankstars getEnBy(String unqid, String dataStr) {
		RankstarsDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStrYMD();
		String TABLENAME2 = logDao.TABLENAME + dataStr;
		return logDao.selectByUnqid(unqid, TABLENAME2);
	}

	/*** 取得排名列表 **/
	static public List<Rankstars> getListBy(int page, int size, String dataStr) {
		RankstarsDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStrYMD();
		String TABLENAME2 = logDao.TABLENAME + dataStr;
		int begin = (page - 1) * size;
		begin = begin < 0 ? 0 : begin;
		return logDao.selectByPage(begin, size, TABLENAME2);
	}

	/*** 取得排名列表 **/
	static public List<Rankstars> getListBy(int page, int size) {
		return getListBy(page, size, "");
	}
    // types end

}

