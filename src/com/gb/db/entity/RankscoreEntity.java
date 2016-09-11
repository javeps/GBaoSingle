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
import com.gb.db.bean.Rankscore;
import com.gb.db.dao.RankscoreDAO;
import com.gb.db.internal.RankscoreInternal;
//import com.gb.content.AppContext;

//gbosng_design - rankscore
@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
public class RankscoreEntity extends RankscoreInternal {
	static Log log = LogFactory.getLog(RankscoreEntity.class);

	public static final RankscoreEntity my = new RankscoreEntity();

	static RankscoreDAO RankscoreDAO = null;

	public static RankscoreDAO RankscoreDAO() {
		if (RankscoreDAO == null)
			RankscoreDAO = new RankscoreDAO(com.gb.content.AppContext.dsData());
		return RankscoreDAO;
	}

	public static void insertMmTry(final Rankscore rankscore) {
		RankscoreDAO DAO = RankscoreDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(rankscore, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/*** 执行 储存过程 process **/
	static public void exceProcessByChn(String chn) throws Exception {
		RankscoreDAO dao = RankscoreDAO();
		String sql = "";
		
		sql = "call pro_rnkScore_Chn(:chn);";
		Map params = new HashMap();
		params.put("chn", chn);
		dao.call(sql, params);
		
		// sql = "call pro_rnkScore_Chn(?);";
		// dao.call(sql, chn);
	}

	/*** 日志数据原 **/
	static RankscoreDAO logDao = null;

	public static RankscoreDAO logDao() {
		if (logDao == null)
			logDao = new RankscoreDAO(com.gb.content.AppContext.dsLog());
		return logDao;
	}

	/*** 取得某个人的排名对象 **/
	static public Rankscore getEnBy(String unqid) {
		return getEnBy(unqid, "");
	}

	/*** 取得某个人的排名对象 **/
	static public Rankscore getEnBy(String unqid, String dataStr) {
		RankscoreDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStrYMD();
		String TABLENAME2 = logDao.TABLENAME + dataStr;
		return logDao.selectByUnqid(unqid, TABLENAME2);
	}

	/*** 取得排名列表 **/
	static public List<Rankscore> getListBy(int page, int size, String dataStr) {
		RankscoreDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStrYMD();
		String TABLENAME2 = logDao.TABLENAME + dataStr;
		int begin = (page - 1) * size;
		begin = begin < 0 ? 0 : begin;
		return logDao.selectByPage(begin, size, TABLENAME2);
	}

	/*** 取得排名列表 **/
	static public List<Rankscore> getListBy(int page, int size) {
		return getListBy(page, size, "");
	}
	// types end

}
