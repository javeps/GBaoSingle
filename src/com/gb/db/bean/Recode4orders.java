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

//gbosng_design - recode4orders
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Recode4orders extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 519677445; // com.gb.db.bean.Recode4orders

    public static String TABLENAME = "recode4orders";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String unqkey = "unqkey"; public static final String usestate = "usestate"; public static final String content = "content"; public static final String chn = "chn"; public static final String createtime = "createtime"; public static final String lasttime = "lasttime";  }
    public static final class CEn { public static final String id = "id"; public static final String unqkey = "unqkey"; public static final String usestate = "usestate"; public static final String content = "content"; public static final String chn = "chn"; public static final String createtime = "createtime"; public static final String lasttime = "lasttime";  }
    public static final String[] carrays ={"id", "unqkey", "usestate", "content", "chn", "createtime", "lasttime"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "INT", "TEXT", "VARCHAR", "DATETIME", "DATETIME"};


    public int id;
    public String unqkey;
    public int usestate;
    public String content;
    public String chn;
    public java.util.Date createtime;
    public java.util.Date lasttime;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return id;
    }

    public static String _key(int id) {
        return PStr.b(TABLENAME).a("-").e(id);
    }

    public int getId(){
        return id;
    }

    public Recode4orders setId(int id){
        this.id = id;
        return this;
    }

    public String getUnqkey(){
        return unqkey;
    }

    public Recode4orders setUnqkey(String unqkey){
        String _old = this.unqkey;
        this.unqkey = unqkey;
        changeIt(Col.unqkey, _old, unqkey);
        return this;
    }

    public int getUsestate(){
        return usestate;
    }

    public Recode4orders setUsestate(int usestate){
        int _old = this.usestate;
        this.usestate = usestate;
        changeIt(Col.usestate, _old, usestate);
        return this;
    }

    public Recode4orders changeUsestate(int usestate){
        return setUsestate(this.usestate + usestate);
    }

    public Recode4orders changeUsestateWithMin(int usestate, int _min){
        int _v2 = this.usestate + usestate;
        return setUsestate(_v2 < _min  ? _min : _v2);
    }

    public Recode4orders changeUsestateWithMax(int usestate, int _max){
        int _v2 = this.usestate + usestate;
        return setUsestate(_v2 > _max  ? _max : _v2);
    }

    public Recode4orders changeUsestateWithMinMax(int usestate, int _min, int _max){
        int _v2 = this.usestate + usestate;
        _v2 = _v2 > _max  ? _max : _v2;
        return setUsestate(_v2 < _min  ? _min : _v2);
    }

    public String getContent(){
        return content;
    }

    public Recode4orders setContent(String content){
        String _old = this.content;
        this.content = content;
        changeIt(Col.content, _old, content);
        return this;
    }

    public String getChn(){
        return chn;
    }

    public Recode4orders setChn(String chn){
        String _old = this.chn;
        this.chn = chn;
        changeIt(Col.chn, _old, chn);
        return this;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Recode4orders setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public java.util.Date getLasttime(){
        return lasttime;
    }

    public Recode4orders setLasttime(java.util.Date lasttime){
        java.util.Date _old = this.lasttime;
        this.lasttime = lasttime;
        changeIt(Col.lasttime, _old, lasttime);
        return this;
    }

    public int compareTo(Recode4orders v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Recode4orders v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Recode4orders newRecode4orders(Integer id, String unqkey, Integer usestate, String content, String chn, java.util.Date createtime, java.util.Date lasttime) {
        Recode4orders result = new Recode4orders();
        result.id = id;
        result.unqkey = unqkey;
        result.usestate = usestate;
        result.content = content;
        result.chn = chn;
        result.createtime = createtime;
        result.lasttime = lasttime;
        return result;
    }

    public static Recode4orders newRecode4orders(Recode4orders recode4orders) {
        Recode4orders result = new Recode4orders();
        result.id = recode4orders.id;
        result.unqkey = recode4orders.unqkey;
        result.usestate = recode4orders.usestate;
        result.content = recode4orders.content;
        result.chn = recode4orders.chn;
        result.createtime = recode4orders.createtime;
        result.lasttime = recode4orders.lasttime;
        return result;
    }

    public Recode4orders createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRecode4orders(){
        Recode4orders recode4orders = null; // recode4orders
        { // new and insert Recode4orders (recode4orders)
            int id = 0; 	// id
            String unqkey = ""; 	// unqkey
            int usestate = 0; 	// usestate
            String content = ""; 	// content
            String chn = ""; 	// chn
            Date createtime = new Date(); 	// createtime
            Date lasttime = new Date(); 	// lasttime
            recode4orders = Recode4orders.newRecode4orders(id, unqkey, usestate, content, chn, createtime, lasttime);
        }
        recode4orders = recode4orders.insert();

        int id = recode4orders.getId(); 	// id
        String unqkey = recode4orders.getUnqkey(); 	// unqkey
        int usestate = recode4orders.getUsestate(); 	// usestate
        String content = recode4orders.getContent(); 	// content
        String chn = recode4orders.getChn(); 	// chn
        Date createtime = recode4orders.getCreatetime(); 	// createtime
        Date lasttime = recode4orders.getLasttime(); 	// lasttime
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        case CEn.id:
            return id;
        case CEn.usestate:
            return usestate;
        }
        return 0;
    }

    public Recode4orders setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Recode4orders setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.usestate:
            return setUsestate(value2);
        }
        return this;
    }

    public Recode4orders changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Recode4orders changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.usestate:
            return changeUsestate(value2);
        }
        return this;
    }

    public Recode4orders changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Recode4orders changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.usestate:
            return changeUsestateWithMin(value2, _min);
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

    public Recode4orders setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Recode4orders setDouble(String fieldEn, double value2) {
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
        case CEn.unqkey: 
            return unqkey;
        case CEn.content: 
            return content;
        case CEn.chn: 
            return chn;
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
        case CEn.id:
            return id;
        case CEn.unqkey:
            return unqkey;
        case CEn.usestate:
            return usestate;
        case CEn.content:
            return content;
        case CEn.chn:
            return chn;
        case CEn.createtime:
            return createtime;
        case CEn.lasttime:
            return lasttime;
        }
        return null;
    }

    public Recode4orders setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Recode4orders setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Recode4orders setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Recode4orders setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.unqkey:
            return setUnqkey(value2);
        case CEn.content:
            return setContent(value2);
        case CEn.chn:
            return setChn(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Recode4orders setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Recode4orders setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.gb.db.bean.Recode4orders");
        result.put("id", id);
        result.put("unqkey", unqkey);
        result.put("usestate", usestate);
        result.put("content", content);
        result.put("chn", chn);
        result.put("createtime", createtime);
        result.put("lasttime", lasttime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("unqkey", unqkey);
        result.put("usestate", usestate);
        result.put("content", content);
        result.put("chn", chn);
        result.put("createtime", createtime);
        result.put("lasttime", lasttime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = toBasicMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Recode4orders");
        return result;
    }

    public Recode4orders mapToThis(Map e){
        Integer id = MapEx.getInt(e, "id");
        String unqkey = MapEx.getString(e, "unqkey");
        Integer usestate = MapEx.getInt(e, "usestate");
        String content = MapEx.getString(e, "content");
        String chn = MapEx.getString(e, "chn");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");

        if(unqkey == null) unqkey = "";
        if(content == null) content = "";
        if(chn == null) chn = "";
        if(createtime == null) createtime = new java.util.Date();
        if(lasttime == null) lasttime = new java.util.Date();

        setId(id);
        setUnqkey(unqkey);
        setUsestate(usestate);
        setContent(content);
        setChn(chn);
        setCreatetime(createtime);
        setLasttime(lasttime);

        return this;
    }

    public static final Recode4orders mapTo(Map e){
        Recode4orders result = new Recode4orders();
        result.mapToThis(e);
        return result;
    }

    public static final Recode4orders originalTo(Map e){
        Recode4orders result = new Recode4orders();

        Integer id = MapEx.getInt(e, "id");
        String unqkey = MapEx.getString(e, "unqkey");
        Integer usestate = MapEx.getInt(e, "usestate");
        String content = MapEx.getString(e, "content");
        String chn = MapEx.getString(e, "chn");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");

        if(unqkey == null) unqkey = "";
        if(content == null) content = "";
        if(chn == null) chn = "";
        if(createtime == null) createtime = new java.util.Date();
        if(lasttime == null) lasttime = new java.util.Date();

        result.id = id;
        result.unqkey = unqkey;
        result.usestate = usestate;
        result.content = content;
        result.chn = chn;
        result.createtime = createtime;
        result.lasttime = lasttime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Recode4orders createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 7);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "unqkey", unqkey);
            writeMapEntry(out, "usestate", usestate);
            writeMapEntry(out, "content", content);
            writeMapEntry(out, "chn", chn);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "lasttime", lasttime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Recode4orders createFor(byte[] b) throws Exception {
        Map map = B2Helper.toMap(b);
        return originalTo(map);
    }

    public String toString(){
        return toOriginalMap().toString();
    }

    public int hashCode() {
        String s = PStr.b(TABLENAME).e(id);
        return s.hashCode();
    }
    public static final Recode4orders loadByKey(int id) {
        return Recode4ordersEntity.getByKey(id);
    }

    public static final Future<Recode4orders> asyncByKey(int id) {
        return Recode4ordersEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Recode4orders insert() {
        Recode4orders result = Recode4ordersEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Recode4orders asyncInsert() {
        Recode4orders result = Recode4ordersEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Recode4orders insert2() {
        return Recode4ordersEntity.insert2(this);
    }

    public final Recode4orders asyncInsert2() {
        Recode4orders result = Recode4ordersEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Recode4orders update() {
        return Recode4ordersEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Recode4ordersEntity.asyncUpdate( this );
        return true;
    }

    public final Recode4orders insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Recode4ordersEntity.delete(id);
    }

    public final void asyncDelete() {
        Recode4ordersEntity.asyncDelete(id);
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

    public Recode4orders clone2() {
        try{
            return (Recode4orders) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
