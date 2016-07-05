package com.gb.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.gb.db.bean.Rankwheel;
import com.gb.db.dao.RankwheelDAO;
import com.gb.db.internal.RankwheelInternal;
//import com.gb.content.AppContext;

//gbosng_design - rankwheel
@SuppressWarnings({ "static-access" })
public class RankwheelEntity extends RankwheelInternal {
	static Log log = LogFactory.getLog(RankwheelEntity.class);

	public static final RankwheelEntity my = new RankwheelEntity();

	static RankwheelDAO RankwheelDAO = null;

	public static RankwheelDAO RankwheelDAO() {
		if (RankwheelDAO == null)
			RankwheelDAO = new RankwheelDAO(com.gb.content.AppContext.dsData());
		return RankwheelDAO;
	}

	public static void insertMmTry(final Rankwheel rankwheel) {
		RankwheelDAO DAO = RankwheelDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(rankwheel, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/***
	 * 执行 储存过程 process
	 * 
	 * @throws Exception
	 **/
	static public void exceProcess() throws Exception {
		RankwheelDAO dao = RankwheelDAO();
		String sql = "call pro_rnkWheel();";
		dao.call(sql);
	}

	/*** 日志数据原 **/
	static RankwheelDAO logDao = null;

	public static RankwheelDAO logDao() {
		if (logDao == null)
			logDao = new RankwheelDAO(com.gb.content.AppContext.dsLog());
		return logDao;
	}

	/*** 取得某个人的排名对象 **/
	static public Rankwheel getEnBy(String unqid) {
		return getEnBy(unqid, "");
	}

	/*** 取得某个人的排名对象 **/
	static public Rankwheel getEnBy(String unqid, String dataStr) {
		RankwheelDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStr5();
		String TABLENAME2 = logDao.TABLENAME + dataStr;
		return logDao.selectByUnqid(unqid, TABLENAME2);
	}

	/*** 取得排名列表 **/
	static public List<Rankwheel> getListBy(int page, int size, String dataStr) {
		RankwheelDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStr5();
		String TABLENAME2 = logDao.TABLENAME + dataStr;
		int begin = (page - 1) * size;
		begin = begin < 0 ? 0 : begin;
		return logDao.selectByPage(begin, size, TABLENAME2);
	}

	/*** 取得排名列表 **/
	static public List<Rankwheel> getListBy(int page, int size) {
		return getListBy(page, size, "");
	}
	// types end

}
