package com.gb.db.bean;

import java.util.*;
import java.sql.*;
import java.util.concurrent.*;
import com.bowlong.io.*;
import com.bowlong.sql.*;
import com.bowlong.pinyin.*;
import com.bowlong.bio2.*;
import com.bowlong.lang.*;
import com.bowlong.util.*;
import com.bowlong.json.MyJson;
import com.gb.db.entity.*;

//gbosng_design - player
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Player extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1369442261; // com.gb.db.bean.Player

    public static String TABLENAME = "player";

    public static final String primary = "pcid";

    public static final class Col { public static final String pcid = "pcid"; public static final String unqid = "unqid"; public static final String uuidMCode = "uuidMCode"; public static final String pname = "pname"; public static final String sword = "sword"; public static final String wheel = "wheel"; public static final String btPl = "btPl"; public static final String btHero = "btHero"; public static final String btPart = "btPart"; public static final String btProp = "btProp"; public static final String btNpc = "btNpc"; public static final String btEmail = "btEmail"; public static final String phone = "phone"; public static final String createtime = "createtime"; public static final String lasttime = "lasttime"; public static final String statusActivity = "statusActivity"; public static final String score4Endless = "score4Endless"; public static final String chn = "chn"; public static final String chnSub = "chnSub"; public static final String fight4hero = "fight4hero"; public static final String fight4part = "fight4part";  }
    public static final class CEn { public static final String pcid = "pcid"; public static final String unqid = "unqid"; public static final String uuidMCode = "uuidMCode"; public static final String pname = "pname"; public static final String sword = "sword"; public static final String wheel = "wheel"; public static final String btPl = "btPl"; public static final String btHero = "btHero"; public static final String btPart = "btPart"; public static final String btProp = "btProp"; public static final String btNpc = "btNpc"; public static final String btEmail = "btEmail"; public static final String phone = "phone"; public static final String createtime = "createtime"; public static final String lasttime = "lasttime"; public static final String statusActivity = "statusActivity"; public static final String score4Endless = "score4Endless"; public static final String chn = "chn"; public static final String chnSub = "chnSub"; public static final String fight4hero = "fight4hero"; public static final String fight4part = "fight4part";  }
    public static final String[] carrays ={"pcid", "unqid", "uuidMCode", "pname", "sword", "wheel", "btPl", "btHero", "btPart", "btProp", "btNpc", "btEmail", "phone", "createtime", "lasttime", "statusActivity", "score4Endless", "chn", "chnSub", "fight4hero", "fight4part"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "VARCHAR", "VARCHAR", "INT", "INT", "BLOB", "BLOB", "BLOB", "BLOB", "BLOB", "BLOB", "VARCHAR", "DATETIME", "DATETIME", "INT", "INT", "VARCHAR", "VARCHAR", "INT", "INT"};


    public int pcid;
    public String unqid;
    public String uuidMCode;
    public String pname;
    public int sword;
    public int wheel;
    public byte[] btPl;
    public byte[] btHero;
    public byte[] btPart;
    public byte[] btProp;
    public byte[] btNpc;
    public byte[] btEmail;
    public String phone;
    public java.util.Date createtime;
    public java.util.Date lasttime;
    public int statusActivity;
    public int score4Endless;
    public String chn;
    public String chnSub;
    public int fight4hero;
    public int fight4part;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return pcid;
    }

    public static String _key(int pcid) {
        return PStr.b(TABLENAME).a("-").e(pcid);
    }

    public int getPcid(){
        return pcid;
    }

    public Player setPcid(int pcid){
        this.pcid = pcid;
        return this;
    }

    public String getUnqid(){
        return unqid;
    }

    public Player setUnqid(String unqid){
        String _old = this.unqid;
        this.unqid = unqid;
        changeIt(Col.unqid, _old, unqid);
        return this;
    }

    public String getUuidMCode(){
        return uuidMCode;
    }

    public Player setUuidMCode(String uuidMCode){
        String _old = this.uuidMCode;
        this.uuidMCode = uuidMCode;
        changeIt(Col.uuidMCode, _old, uuidMCode);
        return this;
    }

    public String getPname(){
        return pname;
    }

    public Player setPname(String pname){
        String _old = this.pname;
        this.pname = pname;
        changeIt(Col.pname, _old, pname);
        return this;
    }

    public int getSword(){
        return sword;
    }

    public Player setSword(int sword){
        int _old = this.sword;
        this.sword = sword;
        changeIt(Col.sword, _old, sword);
        return this;
    }

    public Player changeSword(int sword){
        return setSword(this.sword + sword);
    }

    public Player changeSwordWithMin(int sword, int _min){
        int _v2 = this.sword + sword;
        return setSword(_v2 < _min  ? _min : _v2);
    }

    public Player changeSwordWithMax(int sword, int _max){
        int _v2 = this.sword + sword;
        return setSword(_v2 > _max  ? _max : _v2);
    }

    public Player changeSwordWithMinMax(int sword, int _min, int _max){
        int _v2 = this.sword + sword;
        _v2 = _v2 > _max  ? _max : _v2;
        return setSword(_v2 < _min  ? _min : _v2);
    }

    public int getWheel(){
        return wheel;
    }

    public Player setWheel(int wheel){
        int _old = this.wheel;
        this.wheel = wheel;
        changeIt(Col.wheel, _old, wheel);
        return this;
    }

    public Player changeWheel(int wheel){
        return setWheel(this.wheel + wheel);
    }

    public Player changeWheelWithMin(int wheel, int _min){
        int _v2 = this.wheel + wheel;
        return setWheel(_v2 < _min  ? _min : _v2);
    }

    public Player changeWheelWithMax(int wheel, int _max){
        int _v2 = this.wheel + wheel;
        return setWheel(_v2 > _max  ? _max : _v2);
    }

    public Player changeWheelWithMinMax(int wheel, int _min, int _max){
        int _v2 = this.wheel + wheel;
        _v2 = _v2 > _max  ? _max : _v2;
        return setWheel(_v2 < _min  ? _min : _v2);
    }

    public byte[] getBtPl(){
        return btPl;
    }

    public Player setBtPl(byte[] btPl){
        byte[] _old = this.btPl;
        this.btPl = btPl;
        changeIt(Col.btPl, _old, btPl);
        return this;
    }

    public byte[] getBtHero(){
        return btHero;
    }

    public Player setBtHero(byte[] btHero){
        byte[] _old = this.btHero;
        this.btHero = btHero;
        changeIt(Col.btHero, _old, btHero);
        return this;
    }

    public byte[] getBtPart(){
        return btPart;
    }

    public Player setBtPart(byte[] btPart){
        byte[] _old = this.btPart;
        this.btPart = btPart;
        changeIt(Col.btPart, _old, btPart);
        return this;
    }

    public byte[] getBtProp(){
        return btProp;
    }

    public Player setBtProp(byte[] btProp){
        byte[] _old = this.btProp;
        this.btProp = btProp;
        changeIt(Col.btProp, _old, btProp);
        return this;
    }

    public byte[] getBtNpc(){
        return btNpc;
    }

    public Player setBtNpc(byte[] btNpc){
        byte[] _old = this.btNpc;
        this.btNpc = btNpc;
        changeIt(Col.btNpc, _old, btNpc);
        return this;
    }

    public byte[] getBtEmail(){
        return btEmail;
    }

    public Player setBtEmail(byte[] btEmail){
        byte[] _old = this.btEmail;
        this.btEmail = btEmail;
        changeIt(Col.btEmail, _old, btEmail);
        return this;
    }

    public String getPhone(){
        return phone;
    }

    public Player setPhone(String phone){
        String _old = this.phone;
        this.phone = phone;
        changeIt(Col.phone, _old, phone);
        return this;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Player setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public java.util.Date getLasttime(){
        return lasttime;
    }

    public Player setLasttime(java.util.Date lasttime){
        java.util.Date _old = this.lasttime;
        this.lasttime = lasttime;
        changeIt(Col.lasttime, _old, lasttime);
        return this;
    }

    public int getStatusActivity(){
        return statusActivity;
    }

    public Player setStatusActivity(int statusActivity){
        int _old = this.statusActivity;
        this.statusActivity = statusActivity;
        changeIt(Col.statusActivity, _old, statusActivity);
        return this;
    }

    public Player changeStatusActivity(int statusActivity){
        return setStatusActivity(this.statusActivity + statusActivity);
    }

    public Player changeStatusActivityWithMin(int statusActivity, int _min){
        int _v2 = this.statusActivity + statusActivity;
        return setStatusActivity(_v2 < _min  ? _min : _v2);
    }

    public Player changeStatusActivityWithMax(int statusActivity, int _max){
        int _v2 = this.statusActivity + statusActivity;
        return setStatusActivity(_v2 > _max  ? _max : _v2);
    }

    public Player changeStatusActivityWithMinMax(int statusActivity, int _min, int _max){
        int _v2 = this.statusActivity + statusActivity;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStatusActivity(_v2 < _min  ? _min : _v2);
    }

    public int getScore4Endless(){
        return score4Endless;
    }

    public Player setScore4Endless(int score4Endless){
        int _old = this.score4Endless;
        this.score4Endless = score4Endless;
        changeIt(Col.score4Endless, _old, score4Endless);
        return this;
    }

    public Player changeScore4Endless(int score4Endless){
        return setScore4Endless(this.score4Endless + score4Endless);
    }

    public Player changeScore4EndlessWithMin(int score4Endless, int _min){
        int _v2 = this.score4Endless + score4Endless;
        return setScore4Endless(_v2 < _min  ? _min : _v2);
    }

    public Player changeScore4EndlessWithMax(int score4Endless, int _max){
        int _v2 = this.score4Endless + score4Endless;
        return setScore4Endless(_v2 > _max  ? _max : _v2);
    }

    public Player changeScore4EndlessWithMinMax(int score4Endless, int _min, int _max){
        int _v2 = this.score4Endless + score4Endless;
        _v2 = _v2 > _max  ? _max : _v2;
        return setScore4Endless(_v2 < _min  ? _min : _v2);
    }

    public String getChn(){
        return chn;
    }

    public Player setChn(String chn){
        String _old = this.chn;
        this.chn = chn;
        changeIt(Col.chn, _old, chn);
        return this;
    }

    public String getChnSub(){
        return chnSub;
    }

    public Player setChnSub(String chnSub){
        String _old = this.chnSub;
        this.chnSub = chnSub;
        changeIt(Col.chnSub, _old, chnSub);
        return this;
    }

    public int getFight4hero(){
        return fight4hero;
    }

    public Player setFight4hero(int fight4hero){
        int _old = this.fight4hero;
        this.fight4hero = fight4hero;
        changeIt(Col.fight4hero, _old, fight4hero);
        return this;
    }

    public Player changeFight4hero(int fight4hero){
        return setFight4hero(this.fight4hero + fight4hero);
    }

    public Player changeFight4heroWithMin(int fight4hero, int _min){
        int _v2 = this.fight4hero + fight4hero;
        return setFight4hero(_v2 < _min  ? _min : _v2);
    }

    public Player changeFight4heroWithMax(int fight4hero, int _max){
        int _v2 = this.fight4hero + fight4hero;
        return setFight4hero(_v2 > _max  ? _max : _v2);
    }

    public Player changeFight4heroWithMinMax(int fight4hero, int _min, int _max){
        int _v2 = this.fight4hero + fight4hero;
        _v2 = _v2 > _max  ? _max : _v2;
        return setFight4hero(_v2 < _min  ? _min : _v2);
    }

    public int getFight4part(){
        return fight4part;
    }

    public Player setFight4part(int fight4part){
        int _old = this.fight4part;
        this.fight4part = fight4part;
        changeIt(Col.fight4part, _old, fight4part);
        return this;
    }

    public Player changeFight4part(int fight4part){
        return setFight4part(this.fight4part + fight4part);
    }

    public Player changeFight4partWithMin(int fight4part, int _min){
        int _v2 = this.fight4part + fight4part;
        return setFight4part(_v2 < _min  ? _min : _v2);
    }

    public Player changeFight4partWithMax(int fight4part, int _max){
        int _v2 = this.fight4part + fight4part;
        return setFight4part(_v2 > _max  ? _max : _v2);
    }

    public Player changeFight4partWithMinMax(int fight4part, int _min, int _max){
        int _v2 = this.fight4part + fight4part;
        _v2 = _v2 > _max  ? _max : _v2;
        return setFight4part(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Player v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Player v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Player newPlayer(Integer pcid, String unqid, String uuidMCode, String pname, Integer sword, Integer wheel, byte[] btPl, byte[] btHero, byte[] btPart, byte[] btProp, byte[] btNpc, byte[] btEmail, String phone, java.util.Date createtime, java.util.Date lasttime, Integer statusActivity, Integer score4Endless, String chn, String chnSub, Integer fight4hero, Integer fight4part) {
        Player result = new Player();
        result.pcid = pcid;
        result.unqid = unqid;
        result.uuidMCode = uuidMCode;
        result.pname = pname;
        result.sword = sword;
        result.wheel = wheel;
        result.btPl = btPl;
        result.btHero = btHero;
        result.btPart = btPart;
        result.btProp = btProp;
        result.btNpc = btNpc;
        result.btEmail = btEmail;
        result.phone = phone;
        result.createtime = createtime;
        result.lasttime = lasttime;
        result.statusActivity = statusActivity;
        result.score4Endless = score4Endless;
        result.chn = chn;
        result.chnSub = chnSub;
        result.fight4hero = fight4hero;
        result.fight4part = fight4part;
        return result;
    }

    public static Player newPlayer(Player player) {
        Player result = new Player();
        result.pcid = player.pcid;
        result.unqid = player.unqid;
        result.uuidMCode = player.uuidMCode;
        result.pname = player.pname;
        result.sword = player.sword;
        result.wheel = player.wheel;
        result.btPl = player.btPl;
        result.btHero = player.btHero;
        result.btPart = player.btPart;
        result.btProp = player.btProp;
        result.btNpc = player.btNpc;
        result.btEmail = player.btEmail;
        result.phone = player.phone;
        result.createtime = player.createtime;
        result.lasttime = player.lasttime;
        result.statusActivity = player.statusActivity;
        result.score4Endless = player.score4Endless;
        result.chn = player.chn;
        result.chnSub = player.chnSub;
        result.fight4hero = player.fight4hero;
        result.fight4part = player.fight4part;
        return result;
    }

    public Player createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getPlayer(){
        Player player = null; // player
        { // new and insert Player (player)
            int pcid = 0; 	// pcid
            String unqid = ""; 	// unqid
            String uuidMCode = ""; 	// uuidMCode
            String pname = ""; 	// pname
            int sword = 0; 	// sword
            int wheel = 0; 	// wheel
            byte[] btPl = new byte[0]; 	// btPl
            byte[] btHero = new byte[0]; 	// btHero
            byte[] btPart = new byte[0]; 	// btPart
            byte[] btProp = new byte[0]; 	// btProp
            byte[] btNpc = new byte[0]; 	// btNpc
            byte[] btEmail = new byte[0]; 	// btEmail
            String phone = ""; 	// phone
            Date createtime = new Date(); 	// createtime
            Date lasttime = new Date(); 	// lasttime
            int statusActivity = 0; 	// statusActivity
            int score4Endless = 0; 	// score4Endless
            String chn = ""; 	// chn
            String chnSub = ""; 	// chnSub
            int fight4hero = 0; 	// fight4hero
            int fight4part = 0; 	// fight4part
            player = Player.newPlayer(pcid, unqid, uuidMCode, pname, sword, wheel, btPl, btHero, btPart, btProp, btNpc, btEmail, phone, createtime, lasttime, statusActivity, score4Endless, chn, chnSub, fight4hero, fight4part);
        }
        player = player.insert();

        int pcid = player.getPcid(); 	// pcid
        String unqid = player.getUnqid(); 	// unqid
        String uuidMCode = player.getUuidMCode(); 	// uuidMCode
        String pname = player.getPname(); 	// pname
        int sword = player.getSword(); 	// sword
        int wheel = player.getWheel(); 	// wheel
        byte[] btPl = player.getBtPl(); 	// btPl
        byte[] btHero = player.getBtHero(); 	// btHero
        byte[] btPart = player.getBtPart(); 	// btPart
        byte[] btProp = player.getBtProp(); 	// btProp
        byte[] btNpc = player.getBtNpc(); 	// btNpc
        byte[] btEmail = player.getBtEmail(); 	// btEmail
        String phone = player.getPhone(); 	// phone
        Date createtime = player.getCreatetime(); 	// createtime
        Date lasttime = player.getLasttime(); 	// lasttime
        int statusActivity = player.getStatusActivity(); 	// statusActivity
        int score4Endless = player.getScore4Endless(); 	// score4Endless
        String chn = player.getChn(); 	// chn
        String chnSub = player.getChnSub(); 	// chnSub
        int fight4hero = player.getFight4hero(); 	// fight4hero
        int fight4part = player.getFight4part(); 	// fight4part
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case CEn.pcid:
            return pcid;
        case CEn.sword:
            return sword;
        case CEn.wheel:
            return wheel;
        case CEn.statusActivity:
            return statusActivity;
        case CEn.score4Endless:
            return score4Endless;
        case CEn.fight4hero:
            return fight4hero;
        case CEn.fight4part:
            return fight4part;
        }
        return 0;
    }

    public Player setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Player setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.pcid:
            return setPcid(value2);
        case CEn.sword:
            return setSword(value2);
        case CEn.wheel:
            return setWheel(value2);
        case CEn.statusActivity:
            return setStatusActivity(value2);
        case CEn.score4Endless:
            return setScore4Endless(value2);
        case CEn.fight4hero:
            return setFight4hero(value2);
        case CEn.fight4part:
            return setFight4part(value2);
        }
        return this;
    }

    public Player changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Player changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.sword:
            return changeSword(value2);
        case CEn.wheel:
            return changeWheel(value2);
        case CEn.statusActivity:
            return changeStatusActivity(value2);
        case CEn.score4Endless:
            return changeScore4Endless(value2);
        case CEn.fight4hero:
            return changeFight4hero(value2);
        case CEn.fight4part:
            return changeFight4part(value2);
        }
        return this;
    }

    public Player changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Player changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.sword:
            return changeSwordWithMin(value2, _min);
        case CEn.wheel:
            return changeWheelWithMin(value2, _min);
        case CEn.statusActivity:
            return changeStatusActivityWithMin(value2, _min);
        case CEn.score4Endless:
            return changeScore4EndlessWithMin(value2, _min);
        case CEn.fight4hero:
            return changeFight4heroWithMin(value2, _min);
        case CEn.fight4part:
            return changeFight4partWithMin(value2, _min);
        }
        return this;
    }

    public double valueZhDouble(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueDouble(fieldEn);
    }

    public double valueDouble(String fieldEn){
        switch(fieldEn) {
        }
        return 0;
    }

    public Player setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Player setDouble(String fieldEn, double value2) {
        switch(fieldEn) {
        }
        return this;
    }

    public ListForInt valueZhListForInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueListForInt(fieldEn);
    }

    public ListForInt valueListForInt(String fieldEn){
        String str = valueStr(fieldEn);
        return ListForInt.create(str);
    }

    public String valueZhStr(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueStr(fieldEn);
    }

    public String valueStr(String fieldEn){
        switch(fieldEn){
        case CEn.unqid: 
            return unqid;
        case CEn.uuidMCode: 
            return uuidMCode;
        case CEn.pname: 
            return pname;
        case CEn.phone: 
            return phone;
        case CEn.chn: 
            return chn;
        case CEn.chnSub: 
            return chnSub;
        }
        return "";
    }

    @Override
    public <T> T vZh(String fieldZh) {
        return (T) valueZh(fieldZh);
    }

    public Object valueZh(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return value(fieldEn);
    }

    public <T> T v(String fieldEn){
        return (T) value(fieldEn);
    }

    public Object value(String fieldEn){
        switch(fieldEn){
        case CEn.pcid:
            return pcid;
        case CEn.unqid:
            return unqid;
        case CEn.uuidMCode:
            return uuidMCode;
        case CEn.pname:
            return pname;
        case CEn.sword:
            return sword;
        case CEn.wheel:
            return wheel;
        case CEn.btPl:
            return btPl;
        case CEn.btHero:
            return btHero;
        case CEn.btPart:
            return btPart;
        case CEn.btProp:
            return btProp;
        case CEn.btNpc:
            return btNpc;
        case CEn.btEmail:
            return btEmail;
        case CEn.phone:
            return phone;
        case CEn.createtime:
            return createtime;
        case CEn.lasttime:
            return lasttime;
        case CEn.statusActivity:
            return statusActivity;
        case CEn.score4Endless:
            return score4Endless;
        case CEn.chn:
            return chn;
        case CEn.chnSub:
            return chnSub;
        case CEn.fight4hero:
            return fight4hero;
        case CEn.fight4part:
            return fight4part;
        }
        return null;
    }

    public Player setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Player setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Player setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Player setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.unqid:
            return setUnqid(value2);
        case CEn.uuidMCode:
            return setUuidMCode(value2);
        case CEn.pname:
            return setPname(value2);
        case CEn.phone:
            return setPhone(value2);
        case CEn.chn:
            return setChn(value2);
        case CEn.chnSub:
            return setChnSub(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Player setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Player setJson(String fieldEn, Object o2) {
        try {
            String value2 = MyJson.toJSONString(o2);
            return setStr(fieldEn, value2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Map toMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Player");
        result.put("pcid", pcid);
        result.put("unqid", unqid);
        result.put("uuidMCode", uuidMCode);
        result.put("pname", pname);
        result.put("sword", sword);
        result.put("wheel", wheel);
        result.put("btPl", btPl);
        result.put("btHero", btHero);
        result.put("btPart", btPart);
        result.put("btProp", btProp);
        result.put("btNpc", btNpc);
        result.put("btEmail", btEmail);
        result.put("phone", phone);
        result.put("createtime", createtime);
        result.put("lasttime", lasttime);
        result.put("statusActivity", statusActivity);
        result.put("score4Endless", score4Endless);
        result.put("chn", chn);
        result.put("chnSub", chnSub);
        result.put("fight4hero", fight4hero);
        result.put("fight4part", fight4part);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("pcid", pcid);
        result.put("unqid", unqid);
        result.put("uuidMCode", uuidMCode);
        result.put("pname", pname);
        result.put("sword", sword);
        result.put("wheel", wheel);
        result.put("btPl", btPl);
        result.put("btHero", btHero);
        result.put("btPart", btPart);
        result.put("btProp", btProp);
        result.put("btNpc", btNpc);
        result.put("btEmail", btEmail);
        result.put("phone", phone);
        result.put("createtime", createtime);
        result.put("lasttime", lasttime);
        result.put("statusActivity", statusActivity);
        result.put("score4Endless", score4Endless);
        result.put("chn", chn);
        result.put("chnSub", chnSub);
        result.put("fight4hero", fight4hero);
        result.put("fight4part", fight4part);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Player");
        result.put("pcid", pcid);
        result.put("unqid", unqid);
        result.put("uuidMCode", uuidMCode);
        result.put("pname", pname);
        result.put("sword", sword);
        result.put("wheel", wheel);
        result.put("btPl", btPl);
        result.put("btHero", btHero);
        result.put("btPart", btPart);
        result.put("btProp", btProp);
        result.put("btNpc", btNpc);
        result.put("btEmail", btEmail);
        result.put("phone", phone);
        result.put("createtime", createtime);
        result.put("lasttime", lasttime);
        result.put("statusActivity", statusActivity);
        result.put("score4Endless", score4Endless);
        result.put("chn", chn);
        result.put("chnSub", chnSub);
        result.put("fight4hero", fight4hero);
        result.put("fight4part", fight4part);
        return result;
    }

    public Player mapToObject(Map e){
        Integer pcid = MapEx.getInt(e, "pcid");
        String unqid = MapEx.getString(e, "unqid");
        String uuidMCode = MapEx.getString(e, "uuidMCode");
        String pname = MapEx.getString(e, "pname");
        Integer sword = MapEx.getInt(e, "sword");
        Integer wheel = MapEx.getInt(e, "wheel");
        byte[] btPl = MapEx.getByteArray(e, "btPl");
        byte[] btHero = MapEx.getByteArray(e, "btHero");
        byte[] btPart = MapEx.getByteArray(e, "btPart");
        byte[] btProp = MapEx.getByteArray(e, "btProp");
        byte[] btNpc = MapEx.getByteArray(e, "btNpc");
        byte[] btEmail = MapEx.getByteArray(e, "btEmail");
        String phone = MapEx.getString(e, "phone");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");
        Integer statusActivity = MapEx.getInt(e, "statusActivity");
        Integer score4Endless = MapEx.getInt(e, "score4Endless");
        String chn = MapEx.getString(e, "chn");
        String chnSub = MapEx.getString(e, "chnSub");
        Integer fight4hero = MapEx.getInt(e, "fight4hero");
        Integer fight4part = MapEx.getInt(e, "fight4part");

        if(pcid == null) pcid = 0;
        if(unqid == null) unqid = "";
        if(uuidMCode == null) uuidMCode = "";
        if(pname == null) pname = "";
        if(sword == null) sword = 0;
        if(wheel == null) wheel = 0;
        if(btPl == null) btPl = new byte[0];
        if(btHero == null) btHero = new byte[0];
        if(btPart == null) btPart = new byte[0];
        if(btProp == null) btProp = new byte[0];
        if(btNpc == null) btNpc = new byte[0];
        if(btEmail == null) btEmail = new byte[0];
        if(phone == null) phone = "";
        if(createtime == null) createtime = new java.util.Date();
        if(lasttime == null) lasttime = new java.util.Date();
        if(statusActivity == null) statusActivity = 0;
        if(score4Endless == null) score4Endless = 0;
        if(chn == null) chn = "";
        if(chnSub == null) chnSub = "";
        if(fight4hero == null) fight4hero = 0;
        if(fight4part == null) fight4part = 0;

        setPcid(pcid);
        setUnqid(unqid);
        setUuidMCode(uuidMCode);
        setPname(pname);
        setSword(sword);
        setWheel(wheel);
        setBtPl(btPl);
        setBtHero(btHero);
        setBtPart(btPart);
        setBtProp(btProp);
        setBtNpc(btNpc);
        setBtEmail(btEmail);
        setPhone(phone);
        setCreatetime(createtime);
        setLasttime(lasttime);
        setStatusActivity(statusActivity);
        setScore4Endless(score4Endless);
        setChn(chn);
        setChnSub(chnSub);
        setFight4hero(fight4hero);
        setFight4part(fight4part);

        return this;
    }

    public static final Player mapTo(Map e){
        Player result = new Player();

        Integer pcid = MapEx.getInt(e, "pcid");
        String unqid = MapEx.getString(e, "unqid");
        String uuidMCode = MapEx.getString(e, "uuidMCode");
        String pname = MapEx.getString(e, "pname");
        Integer sword = MapEx.getInt(e, "sword");
        Integer wheel = MapEx.getInt(e, "wheel");
        byte[] btPl = MapEx.getByteArray(e, "btPl");
        byte[] btHero = MapEx.getByteArray(e, "btHero");
        byte[] btPart = MapEx.getByteArray(e, "btPart");
        byte[] btProp = MapEx.getByteArray(e, "btProp");
        byte[] btNpc = MapEx.getByteArray(e, "btNpc");
        byte[] btEmail = MapEx.getByteArray(e, "btEmail");
        String phone = MapEx.getString(e, "phone");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");
        Integer statusActivity = MapEx.getInt(e, "statusActivity");
        Integer score4Endless = MapEx.getInt(e, "score4Endless");
        String chn = MapEx.getString(e, "chn");
        String chnSub = MapEx.getString(e, "chnSub");
        Integer fight4hero = MapEx.getInt(e, "fight4hero");
        Integer fight4part = MapEx.getInt(e, "fight4part");

        if(pcid == null) pcid = 0;
        if(unqid == null) unqid = "";
        if(uuidMCode == null) uuidMCode = "";
        if(pname == null) pname = "";
        if(sword == null) sword = 0;
        if(wheel == null) wheel = 0;
        if(btPl == null) btPl = new byte[0];
        if(btHero == null) btHero = new byte[0];
        if(btPart == null) btPart = new byte[0];
        if(btProp == null) btProp = new byte[0];
        if(btNpc == null) btNpc = new byte[0];
        if(btEmail == null) btEmail = new byte[0];
        if(phone == null) phone = "";
        if(createtime == null) createtime = new java.util.Date();
        if(lasttime == null) lasttime = new java.util.Date();
        if(statusActivity == null) statusActivity = 0;
        if(score4Endless == null) score4Endless = 0;
        if(chn == null) chn = "";
        if(chnSub == null) chnSub = "";
        if(fight4hero == null) fight4hero = 0;
        if(fight4part == null) fight4part = 0;

        result.pcid = pcid;
        result.unqid = unqid;
        result.uuidMCode = uuidMCode;
        result.pname = pname;
        result.sword = sword;
        result.wheel = wheel;
        result.btPl = btPl;
        result.btHero = btHero;
        result.btPart = btPart;
        result.btProp = btProp;
        result.btNpc = btNpc;
        result.btEmail = btEmail;
        result.phone = phone;
        result.createtime = createtime;
        result.lasttime = lasttime;
        result.statusActivity = statusActivity;
        result.score4Endless = score4Endless;
        result.chn = chn;
        result.chnSub = chnSub;
        result.fight4hero = fight4hero;
        result.fight4part = fight4part;

        return result;
    }

    public static final Player originalTo(Map e){
        Player result = new Player();

        Integer pcid = MapEx.getInt(e, "pcid");
        String unqid = MapEx.getString(e, "unqid");
        String uuidMCode = MapEx.getString(e, "uuidMCode");
        String pname = MapEx.getString(e, "pname");
        Integer sword = MapEx.getInt(e, "sword");
        Integer wheel = MapEx.getInt(e, "wheel");
        byte[] btPl = MapEx.getByteArray(e, "btPl");
        byte[] btHero = MapEx.getByteArray(e, "btHero");
        byte[] btPart = MapEx.getByteArray(e, "btPart");
        byte[] btProp = MapEx.getByteArray(e, "btProp");
        byte[] btNpc = MapEx.getByteArray(e, "btNpc");
        byte[] btEmail = MapEx.getByteArray(e, "btEmail");
        String phone = MapEx.getString(e, "phone");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");
        Integer statusActivity = MapEx.getInt(e, "statusActivity");
        Integer score4Endless = MapEx.getInt(e, "score4Endless");
        String chn = MapEx.getString(e, "chn");
        String chnSub = MapEx.getString(e, "chnSub");
        Integer fight4hero = MapEx.getInt(e, "fight4hero");
        Integer fight4part = MapEx.getInt(e, "fight4part");

        if(pcid == null) pcid = 0;
        if(unqid == null) unqid = "";
        if(uuidMCode == null) uuidMCode = "";
        if(pname == null) pname = "";
        if(sword == null) sword = 0;
        if(wheel == null) wheel = 0;
        if(btPl == null) btPl = new byte[0];
        if(btHero == null) btHero = new byte[0];
        if(btPart == null) btPart = new byte[0];
        if(btProp == null) btProp = new byte[0];
        if(btNpc == null) btNpc = new byte[0];
        if(btEmail == null) btEmail = new byte[0];
        if(phone == null) phone = "";
        if(createtime == null) createtime = new java.util.Date();
        if(lasttime == null) lasttime = new java.util.Date();
        if(statusActivity == null) statusActivity = 0;
        if(score4Endless == null) score4Endless = 0;
        if(chn == null) chn = "";
        if(chnSub == null) chnSub = "";
        if(fight4hero == null) fight4hero = 0;
        if(fight4part == null) fight4part = 0;

        result.pcid = pcid;
        result.unqid = unqid;
        result.uuidMCode = uuidMCode;
        result.pname = pname;
        result.sword = sword;
        result.wheel = wheel;
        result.btPl = btPl;
        result.btHero = btHero;
        result.btPart = btPart;
        result.btProp = btProp;
        result.btNpc = btNpc;
        result.btEmail = btEmail;
        result.phone = phone;
        result.createtime = createtime;
        result.lasttime = lasttime;
        result.statusActivity = statusActivity;
        result.score4Endless = score4Endless;
        result.chn = chn;
        result.chnSub = chnSub;
        result.fight4hero = fight4hero;
        result.fight4part = fight4part;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Player createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 21);
            writeMapEntry(out, "pcid", pcid);
            writeMapEntry(out, "unqid", unqid);
            writeMapEntry(out, "uuidMCode", uuidMCode);
            writeMapEntry(out, "pname", pname);
            writeMapEntry(out, "sword", sword);
            writeMapEntry(out, "wheel", wheel);
            writeMapEntry(out, "btPl", btPl);
            writeMapEntry(out, "btHero", btHero);
            writeMapEntry(out, "btPart", btPart);
            writeMapEntry(out, "btProp", btProp);
            writeMapEntry(out, "btNpc", btNpc);
            writeMapEntry(out, "btEmail", btEmail);
            writeMapEntry(out, "phone", phone);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "lasttime", lasttime);
            writeMapEntry(out, "statusActivity", statusActivity);
            writeMapEntry(out, "score4Endless", score4Endless);
            writeMapEntry(out, "chn", chn);
            writeMapEntry(out, "chnSub", chnSub);
            writeMapEntry(out, "fight4hero", fight4hero);
            writeMapEntry(out, "fight4part", fight4part);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Player createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }

    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = PStr.b(TABLENAME).e(pcid);
        return s.hashCode();
    }
    public static final Player loadByKey(int pcid) {
        return PlayerEntity.getByKey(pcid);
    }

    public static final Future<Player> asyncByKey(int pcid) {
        return PlayerEntity.asyncGetByKey(pcid);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Player insert() {
        Player result = PlayerEntity.insert(this);
        if(result == null) return null;
        // pcid = result.pcid;
        return result;
    }

    public final Player asyncInsert() {
        Player result = PlayerEntity.asyncInsert(this);
        // pcid = result.pcid;
        return result;
    }

    public final Player insert2() {
        return PlayerEntity.insert2(this);
    }

    public final Player asyncInsert2() {
        Player result = PlayerEntity.asyncInsert2(this);
        // pcid = result.pcid;
        return result;
    }

    public final Player update() {
        return PlayerEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(pcid <= 0) return false;
        PlayerEntity.asyncUpdate( this );
        return true;
    }

    public final Player insertOrUpdate() {
        if(pcid <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return PlayerEntity.delete(pcid);
    }

    public final void asyncDelete() {
        PlayerEntity.asyncDelete(pcid);
    }

    @Override
    public boolean isThis(String str) {
        return this.getClass().getName().equals(str);
    }

    @Override
    public boolean isThis(Map originalMap) {
        String str = MapEx.getString(originalMap, "_CLASSNAME");
        return isThis(str);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Player clone2() {
        try{
            return (Player) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
