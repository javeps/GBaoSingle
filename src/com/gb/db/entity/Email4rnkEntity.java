package com.gb.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.gb.db.bean.Email4rnk;
import com.gb.db.dao.Email4rnkDAO;
import com.gb.db.internal.Email4rnkInternal;
//import com.gb.content.AppContext;

//gbosng_design - email4rnk
@SuppressWarnings({ "static-access" })
public class Email4rnkEntity extends Email4rnkInternal {
	static Log log = LogFactory.getLog(Email4rnkEntity.class);

	public static final Email4rnkEntity my = new Email4rnkEntity();

	static Email4rnkDAO Email4rnkDAO = null;

	public static Email4rnkDAO Email4rnkDAO() {
		if (Email4rnkDAO == null)
			Email4rnkDAO = new Email4rnkDAO(com.gb.content.AppContext.dsData());
		return Email4rnkDAO;
	}

	public static void insertMmTry(final Email4rnk email4rnk) {
		Email4rnkDAO DAO = Email4rnkDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(email4rnk, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	static Email4rnkDAO logDao = null;

	public static Email4rnkDAO logDao() {
		if (logDao == null)
			logDao = new Email4rnkDAO(com.gb.content.AppContext.dsLog());
		return logDao;
	}

	static public void insertLog(String dataStr, List<Email4rnk> list) {
		if (ListEx.isEmpty(list))
			return;
		Email4rnkDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStrYMD();

		String TABLENAME2 = logDao.TABLENAME + dataStr;
		boolean ew = logDao.exist_w(TABLENAME2);
		if (ew)
			return;
		createNoUniqueTable(logDao, TABLENAME2);

		logDao.insert(list, TABLENAME2);
	}

	static public Email4rnk getEnity(String dataStr, int indexBeg, int indexEnd) {
		if (indexBeg < 0)
			return null;
		if (indexEnd > 0 && indexBeg > indexEnd)
			return null;

		Email4rnkDAO logDao = logDao();
		if (StrEx.isEmptyTrim(dataStr))
			dataStr = DateEx.nowStrYMD();
		StringBuffer buff = new StringBuffer("SELECT * FROM ").append(
				logDao.TABLENAME).append(dataStr);
		buff.append(" WHERE 1 = 1 ");
		buff.append(" AND indexBegin >= ").append(indexBeg);
		if (indexEnd > 0) {
			buff.append(" AND indexEnd <= ").append(indexEnd);
		}
		String sql = buff.toString();
		try {
			return logDao.queryForObject(sql, Email4rnk.class);
		} catch (Exception e) {
		}
		return null;
	}
	// types end

}
