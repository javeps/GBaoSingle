package com.gb.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.gb.db.bean.*;
import com.gb.db.dao.*;
import com.gb.db.internal.*;
import com.gb.content.AppContext;

//gbosng_design - cop4fee
@SuppressWarnings({ "static-access" })
public class Cop4feeEntity extends Cop4feeInternal{
    static Log log = LogFactory.getLog(Cop4feeEntity.class);

    public static final Cop4feeEntity my = new Cop4feeEntity();

    static Cop4feeDAO Cop4feeDAO = null;
    public static Cop4feeDAO Cop4feeDAO() {
        if( Cop4feeDAO == null)
            Cop4feeDAO = new Cop4feeDAO(AppContext.dsData());
        return Cop4feeDAO;
    }


    public static void insertMmTry(final Cop4fee cop4fee) {
        Cop4feeDAO DAO = Cop4feeDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(cop4fee, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }


    // types begin
    // types end

}

