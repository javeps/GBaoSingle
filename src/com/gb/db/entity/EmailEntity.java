package com.gb.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import java.util.List;

import org.apache.commons.logging.*;

import com.gb.db.bean.*;
import com.gb.db.dao.*;
import com.gb.db.internal.*;
//import com.gb.content.AppContext;

//gbosng_design - email
@SuppressWarnings({ "static-access" })
public class EmailEntity extends EmailInternal {
	static Log log = LogFactory.getLog(EmailEntity.class);

	public static final EmailEntity my = new EmailEntity();

	static EmailDAO EmailDAO = null;

	public static EmailDAO EmailDAO() {
		if (EmailDAO == null)
			EmailDAO = new EmailDAO(com.gb.content.AppContext.dsData());
		return EmailDAO;
	}

	public static void insertMmTry(final Email email) {
		EmailDAO DAO = EmailDAO();
		String TABLENAME2 = DAO.TABLEMM();
		try {
			boolean ew = DAO.exist_w(TABLENAME2);
			if (ew == false)
				createNoUniqueTable(DAO, TABLENAME2);
			DAO.asyncInsert(email, TABLENAME2);
		} catch (Exception e) {
			log.info(e2s(e));
		}
	}

	// types begin
	/*** 删除邮件 **/
	static public void deleteBy(String unqid) {
		EmailDAO DAO = EmailDAO();
		StringBuffer buff = new StringBuffer();
		buff.append("DELETE FROM ").append(DAO.TABLENAME);
		buff.append(" WHERE 1 = 1 ");
		buff.append(" AND unqid = '").append(unqid).append("'");
		String sql = buff.toString();
		try {
			DAO.update(sql);
		} catch (Exception e) {
		}
	}

	static public final List<Email> getEmails(String unqid, long lasttime) {
		EmailDAO DAO = EmailDAO();
		StringBuffer buff = new StringBuffer();
		buff.append("SELECT * FROM ").append(DAO.TABLENAME);
		buff.append(" WHERE 1 = 1 ");
		buff.append(" AND unqid = '").append(unqid).append("'");
		buff.append(" AND creattime >= ").append(lasttime).append("");
		String sql = buff.toString();
		try {
			return DAO.queryForList(sql, Email.class);
		} catch (Exception e) {
			return null;
		}
	}
	// types end

}
