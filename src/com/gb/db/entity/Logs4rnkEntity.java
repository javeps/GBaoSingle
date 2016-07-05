package com.gb.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.gb.db.bean.*;
import com.gb.db.dao.*;
import com.gb.db.internal.*;
//import com.gb.content.AppContext;

//gbosng_design - logs4rnk
@SuppressWarnings({ "static-access" })
public class Logs4rnkEntity extends Logs4rnkInternal{
    static Log log = LogFactory.getLog(Logs4rnkEntity.class);

    public static final Logs4rnkEntity my = new Logs4rnkEntity();

    static Logs4rnkDAO Logs4rnkDAO = null;
    public static Logs4rnkDAO Logs4rnkDAO() {
        if( Logs4rnkDAO == null)
            Logs4rnkDAO = new Logs4rnkDAO(com.gb.content.AppContext.dsData());
        return Logs4rnkDAO;
    }


    public static void insertMmTry(final Logs4rnk logs4rnk) {
        Logs4rnkDAO DAO = Logs4rnkDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(logs4rnk, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

