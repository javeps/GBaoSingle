package com.gb.db.entity;

//import java.util.*;
//import com.bowlong.sql.*;
//import com.bowlong.lang.*;
import org.apache.commons.logging.*;
import com.gb.db.bean.*;
import com.gb.db.dao.*;
import com.gb.db.internal.*;
//import com.gb.content.AppContext;

//gbosng_design - player
@SuppressWarnings({ "static-access" })
public class PlayerEntity extends PlayerInternal{
    static Log log = LogFactory.getLog(PlayerEntity.class);

    public static final PlayerEntity my = new PlayerEntity();

    static PlayerDAO PlayerDAO = null;
    public static PlayerDAO PlayerDAO() {
        if( PlayerDAO == null)
            PlayerDAO = new PlayerDAO(com.gb.content.AppContext.dsData());
        return PlayerDAO;
    }


    public static void insertMmTry(final Player player) {
        PlayerDAO DAO = PlayerDAO();
        String TABLENAME2 = DAO.TABLEMM();
        try {
            boolean ew = DAO.exist_w(TABLENAME2);
            if(ew == false) createNoUniqueTable(DAO, TABLENAME2);
            DAO.asyncInsert(player, TABLENAME2);
        } catch (Exception e) {
            log.info(e2s(e));
        }
    }

    // public void loadLinked(final Player player) {
        // if(player == null) return;
        // List<Chat> chats = player.getChatsFkFpcid(); // chat
    // }

    // types begin
    // types end

}

