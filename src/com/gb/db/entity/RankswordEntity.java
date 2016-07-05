package com.gb.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.gb.db.bean.Ranksword;
import com.gb.db.dao.RankswordDAO;
import com.gb.db.internal.RankswordInternal;
//import com.gb.content.AppContext;

//gbosng_design - ranksword
@SuppressWarnings({ "static-access", "unchecked", "rawtypes" })
public class RankswordEntity extends RankswordInternal {
	static Log log = LogFactory.getLog(RankswordEntity.class);

	public static final RankswordEntity my = new RankswordEntity();

	static RankswordDAO RankswordDAO = null;

	public static RankswordDAO RankswordDAO() {
		if (RankswordDAO == null)
			RankswordDAO = new RankswordDAO(com.gb.content.AppContext.dsData());
		return RankswordDAO;
	}

	public static void insertMmTry(final Ranksword ranksword) {
		RankswordDAO DAO = RankswordDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(ranksword, TABLENAME2);
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
		RankswordDAO dao = RankswordDAO();
		String sql = "call pro_rnkSword();";
		dao.call(sql);
	}

	/**
	 * 执行储存过程 -- 通用
	 * 
	 * @param parsType
	 *            [1:fight4hero 英雄战斗力,2:fight4part 小伙伴战斗力,其他:sword Pl.sword战斗力]
	 * @param chn
	 *            渠道[空:所有,其他:渠道标识]
	 * @throws Exception
	 */
	static public void exceProcessByChn(int parsType, String chn)
			throws Exception {
		RankswordDAO dao = RankswordDAO();
		String sql = "";

		sql = "call pro_rnkSword_Chn(:parsType,:chn);";
		Map params = new HashMap();
		params.put("parsType", parsType);
		params.put("chn", chn);
		dao.call(sql, params);

		// sql = "call pro_rnkScore_Chn(?,?);";
		// dao.call(sql,parsType, chn);
	}

	/*** 日志数据原 **/
	static RankswordDAO logDao = null;

	public static RankswordDAO logDao() {
		if (logDao == null)
			logDao = new RankswordDAO(com.gb.content.AppContext.dsLog());
		return logDao;
	}

	/*** 清空日志 **/
	static public void clearLog4Call() throws SQLException {
		RankswordDAO logDao = logDao();
		String sql = "call pro_rnkDel();";
		logDao.call(sql);
	}

	/*** 取得某个人的排名对象 **/
	static public Ranksword getEnBy(String unqid) {
		return getEnBy(unqid, "");
	}

	/*** 取得某个人的排名对象 **/
	static public Ranksword getEnBy(String unqid, String dataStr) {
		RankswordDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStr5();
		String TABLENAME2 = logDao.TABLENAME + dataStr;
		return logDao.selectByUnqid(unqid, TABLENAME2);
	}

	/*** 取得排名列表 **/
	static public List<Ranksword> getListBy(int page, int size) {
		return getListBy(page, size, "");
	}

	/*** 取得排名列表 **/
	static public List<Ranksword> getListBy(int page, int size, String dataStr) {
		RankswordDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStr5();
		String TABLENAME2 = logDao.TABLENAME + dataStr;
		int begin = (page - 1) * size;
		begin = begin < 0 ? 0 : begin;
		return logDao.selectByPage(begin, size, TABLENAME2);
	}
	// types end

}
