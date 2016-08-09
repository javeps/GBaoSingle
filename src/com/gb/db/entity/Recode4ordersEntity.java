package com.gb.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.gb.db.bean.*;
import com.gb.db.dao.*;
import com.gb.db.internal.*;
import com.gb.content.AppContext;

//gbosng_design - recode4orders
@SuppressWarnings({ "static-access" })
public class Recode4ordersEntity extends Recode4ordersInternal{
    static Log log = LogFactory.getLog(Recode4ordersEntity.class);

    public static final Recode4ordersEntity my = new Recode4ordersEntity();

    static Recode4ordersDAO Recode4ordersDAO = null;
    public static Recode4ordersDAO Recode4ordersDAO() {
        if( Recode4ordersDAO == null)
            Recode4ordersDAO = new Recode4ordersDAO(AppContext.dsData());
        return Recode4ordersDAO;
    }


    public static void insertMmTry(final Recode4orders recode4orders) {
        Recode4ordersDAO DAO = Recode4ordersDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(recode4orders, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

