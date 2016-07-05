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

//gbosng_design - recode4error
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Recode4error extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1254468328; // com.gb.db.bean.Recode4error

    public static String TABLENAME = "recode4error";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String uuid = "uuid"; public static final String device = "device"; public static final String error = "error"; public static final String createtime = "createtime";  }
    public static final class CEn { public static final String id = "id"; public static final String uuid = "uuid"; public static final String device = "device"; public static final String error = "error"; public static final String createtime = "createtime";  }
    public static final String[] carrays ={"id", "uuid", "device", "error", "createtime"};
    public static final String[] dbTypes ={"BIGINT", "VARCHAR", "TINYTEXT", "TEXT", "DATETIME"};


    public long id;
    public String uuid;
    public String device;
    public String error;
    public java.util.Date createtime;

    @Override
    public String _tableId() {
        return TABLENAME;
    }

    @Override
    public Object _primaryKey() {
        return id;
    }

    public static String _key(long id) {
        return PStr.b(TABLENAME).a("-").e(id);
    }

    public long getId(){
        return id;
    }

    public Recode4error setId(long id){
        this.id = id;
        return this;
    }

    public String getUuid(){
        return uuid;
    }

    public Recode4error setUuid(String uuid){
        String _old = this.uuid;
        this.uuid = uuid;
        changeIt(Col.uuid, _old, uuid);
        return this;
    }

    public String getDevice(){
        return device;
    }

    public Recode4error setDevice(String device){
        String _old = this.device;
        this.device = device;
        changeIt(Col.device, _old, device);
        return this;
    }

    public String getError(){
        return error;
    }

    public Recode4error setError(String error){
        String _old = this.error;
        this.error = error;
        changeIt(Col.error, _old, error);
        return this;
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Recode4error setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public int compareTo(Recode4error v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Recode4error v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Recode4error newRecode4error(Long id, String uuid, String device, String error, java.util.Date createtime) {
        Recode4error result = new Recode4error();
        result.id = id;
        result.uuid = uuid;
        result.device = device;
        result.error = error;
        result.createtime = createtime;
        return result;
    }

    public static Recode4error newRecode4error(Recode4error recode4error) {
        Recode4error result = new Recode4error();
        result.id = recode4error.id;
        result.uuid = recode4error.uuid;
        result.device = recode4error.device;
        result.error = recode4error.error;
        result.createtime = recode4error.createtime;
        return result;
    }

    public Recode4error createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRecode4error(){
        Recode4error recode4error = null; // recode4error
        { // new and insert Recode4error (recode4error)
            long id = 0; 	// id
            String uuid = ""; 	// uuid
            String device = ""; 	// device
            String error = ""; 	// error
            Date createtime = new Date(); 	// createtime
            recode4error = Recode4error.newRecode4error(id, uuid, device, error, createtime);
        }
        recode4error = recode4error.insert();

        long id = recode4error.getId(); 	// id
        String uuid = recode4error.getUuid(); 	// uuid
        String device = recode4error.getDevice(); 	// device
        String error = recode4error.getError(); 	// error
        Date createtime = recode4error.getCreatetime(); 	// createtime
    }
    */

    public int valueZhInt(String fieldZh){
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return valueInt(fieldEn);
    }

    public int valueInt(String fieldEn){
        switch(fieldEn){
        }
        return 0;
    }

    public Recode4error setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Recode4error setInt(String fieldEn, int value2) {
        switch(fieldEn){
        }
        return this;
    }

    public Recode4error changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Recode4error changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        }
        return this;
    }

    public Recode4error changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Recode4error changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
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

    public Recode4error setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Recode4error setDouble(String fieldEn, double value2) {
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
        case CEn.uuid: 
            return uuid;
        case CEn.device: 
            return device;
        case CEn.error: 
            return error;
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
        case CEn.uuid:
            return uuid;
        case CEn.device:
            return device;
        case CEn.error:
            return error;
        case CEn.createtime:
            return createtime;
        }
        return null;
    }

    public Recode4error setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Recode4error setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Recode4error setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Recode4error setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.uuid:
            return setUuid(value2);
        case CEn.device:
            return setDevice(value2);
        case CEn.error:
            return setError(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Recode4error setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Recode4error setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.gb.db.bean.Recode4error");
        result.put("id", id);
        result.put("uuid", uuid);
        result.put("device", device);
        result.put("error", error);
        result.put("createtime", createtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("uuid", uuid);
        result.put("device", device);
        result.put("error", error);
        result.put("createtime", createtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Recode4error");
        result.put("id", id);
        result.put("uuid", uuid);
        result.put("device", device);
        result.put("error", error);
        result.put("createtime", createtime);
        return result;
    }

    public Recode4error mapToObject(Map e){
        Long id = MapEx.getLong(e, "id");
        String uuid = MapEx.getString(e, "uuid");
        String device = MapEx.getString(e, "device");
        String error = MapEx.getString(e, "error");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0L;
        if(uuid == null) uuid = "";
        if(device == null) device = "";
        if(error == null) error = "";
        if(createtime == null) createtime = new java.util.Date();

        setId(id);
        setUuid(uuid);
        setDevice(device);
        setError(error);
        setCreatetime(createtime);

        return this;
    }

    public static final Recode4error mapTo(Map e){
        Recode4error result = new Recode4error();

        Long id = MapEx.getLong(e, "id");
        String uuid = MapEx.getString(e, "uuid");
        String device = MapEx.getString(e, "device");
        String error = MapEx.getString(e, "error");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0L;
        if(uuid == null) uuid = "";
        if(device == null) device = "";
        if(error == null) error = "";
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.uuid = uuid;
        result.device = device;
        result.error = error;
        result.createtime = createtime;

        return result;
    }

    public static final Recode4error originalTo(Map e){
        Recode4error result = new Recode4error();

        Long id = MapEx.getLong(e, "id");
        String uuid = MapEx.getString(e, "uuid");
        String device = MapEx.getString(e, "device");
        String error = MapEx.getString(e, "error");
        java.util.Date createtime = MapEx.getDate(e, "createtime");

        if(id == null) id = 0L;
        if(uuid == null) uuid = "";
        if(device == null) device = "";
        if(error == null) error = "";
        if(createtime == null) createtime = new java.util.Date();

        result.id = id;
        result.uuid = uuid;
        result.device = device;
        result.error = error;
        result.createtime = createtime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Recode4error createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 5);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "uuid", uuid);
            writeMapEntry(out, "device", device);
            writeMapEntry(out, "error", error);
            writeMapEntry(out, "createtime", createtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Recode4error createFor(byte[] b) throws Exception {
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
    public static final Recode4error loadByKey(long id) {
        return Recode4errorEntity.getByKey(id);
    }

    public static final Future<Recode4error> asyncByKey(long id) {
        return Recode4errorEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Recode4error insert() {
        Recode4error result = Recode4errorEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Recode4error asyncInsert() {
        Recode4error result = Recode4errorEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Recode4error insert2() {
        return Recode4errorEntity.insert2(this);
    }

    public final Recode4error asyncInsert2() {
        Recode4error result = Recode4errorEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Recode4error update() {
        return Recode4errorEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Recode4errorEntity.asyncUpdate( this );
        return true;
    }

    public final Recode4error insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Recode4errorEntity.delete(id);
    }

    public final void asyncDelete() {
        Recode4errorEntity.asyncDelete(id);
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

    public Recode4error clone2() {
        try{
            return (Recode4error) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
