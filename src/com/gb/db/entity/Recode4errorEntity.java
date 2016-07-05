package com.gb.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.gb.db.bean.*;
import com.gb.db.dao.*;
import com.gb.db.internal.*;
//import com.gb.content.AppContext;

//gbosng_design - recode4error
@SuppressWarnings({ "static-access" })
public class Recode4errorEntity extends Recode4errorInternal{
    static Log log = LogFactory.getLog(Recode4errorEntity.class);

    public static final Recode4errorEntity my = new Recode4errorEntity();

    static Recode4errorDAO Recode4errorDAO = null;
    public static Recode4errorDAO Recode4errorDAO() {
        if( Recode4errorDAO == null)
            Recode4errorDAO = new Recode4errorDAO(com.gb.content.AppContext.dsData());
        return Recode4errorDAO;
    }


    public static void insertMmTry(final Recode4error recode4error) {
        Recode4errorDAO DAO = Recode4errorDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(recode4error, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

