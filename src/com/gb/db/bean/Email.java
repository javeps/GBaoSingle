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

//gbosng_design - email
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Email extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -104501208; // com.gb.db.bean.Email

    public static String TABLENAME = "email";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String unqid = "unqid"; public static final String title = "title"; public static final String content = "content"; public static final String awardJson = "awardJson"; public static final String isRead = "isRead"; public static final String isReceive = "isReceive"; public static final String creattime = "creattime"; public static final String validtime = "validtime";  }
    public static final class CEn { public static final String id = "id"; public static final String unqid = "unqid"; public static final String title = "title"; public static final String content = "content"; public static final String awardJson = "awardJson"; public static final String isRead = "isRead"; public static final String isReceive = "isReceive"; public static final String creattime = "creattime"; public static final String validtime = "validtime";  }
    public static final String[] carrays ={"id", "unqid", "title", "content", "awardJson", "isRead", "isReceive", "creattime", "validtime"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "TINYTEXT", "TEXT", "TEXT", "BIT", "BIT", "BIGINT", "BIGINT"};


    public int id;
    public String unqid;
    public String title;
    public String content;
    public String awardJson;
    public boolean isRead;
    public boolean isReceive;
    public long creattime;
    public long validtime;

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

    public Email setId(int id){
        this.id = id;
        return this;
    }

    public String getUnqid(){
        return unqid;
    }

    public Email setUnqid(String unqid){
        String _old = this.unqid;
        this.unqid = unqid;
        changeIt(Col.unqid, _old, unqid);
        return this;
    }

    public String getTitle(){
        return title;
    }

    public Email setTitle(String title){
        String _old = this.title;
        this.title = title;
        changeIt(Col.title, _old, title);
        return this;
    }

    public String getContent(){
        return content;
    }

    public Email setContent(String content){
        String _old = this.content;
        this.content = content;
        changeIt(Col.content, _old, content);
        return this;
    }

    public String getAwardJson(){
        return awardJson;
    }

    public Email setAwardJson(String awardJson){
        String _old = this.awardJson;
        this.awardJson = awardJson;
        changeIt(Col.awardJson, _old, awardJson);
        return this;
    }

    public boolean getIsRead(){
        return isRead;
    }

    public Email setIsRead(boolean isRead){
        boolean _old = this.isRead;
        this.isRead = isRead;
        changeIt(Col.isRead, _old, isRead);
        return this;
    }

    public boolean getIsReceive(){
        return isReceive;
    }

    public Email setIsReceive(boolean isReceive){
        boolean _old = this.isReceive;
        this.isReceive = isReceive;
        changeIt(Col.isReceive, _old, isReceive);
        return this;
    }

    public long getCreattime(){
        return creattime;
    }

    public Email setCreattime(long creattime){
        long _old = this.creattime;
        this.creattime = creattime;
        changeIt(Col.creattime, _old, creattime);
        return this;
    }

    public Email changeCreattime(long creattime){
        return setCreattime(this.creattime + creattime);
    }

    public Email changeCreattimeWithMin(long creattime, long _min){
        long _v2 = this.creattime + creattime;
        return setCreattime(_v2 < _min  ? _min : _v2);
    }

    public Email changeCreattimeWithMax(long creattime, long _max){
        long _v2 = this.creattime + creattime;
        return setCreattime(_v2 > _max  ? _max : _v2);
    }

    public Email changeCreattimeWithMinMax(long creattime, long _min, long _max){
        long _v2 = this.creattime + creattime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCreattime(_v2 < _min  ? _min : _v2);
    }

    public long getValidtime(){
        return validtime;
    }

    public Email setValidtime(long validtime){
        long _old = this.validtime;
        this.validtime = validtime;
        changeIt(Col.validtime, _old, validtime);
        return this;
    }

    public Email changeValidtime(long validtime){
        return setValidtime(this.validtime + validtime);
    }

    public Email changeValidtimeWithMin(long validtime, long _min){
        long _v2 = this.validtime + validtime;
        return setValidtime(_v2 < _min  ? _min : _v2);
    }

    public Email changeValidtimeWithMax(long validtime, long _max){
        long _v2 = this.validtime + validtime;
        return setValidtime(_v2 > _max  ? _max : _v2);
    }

    public Email changeValidtimeWithMinMax(long validtime, long _min, long _max){
        long _v2 = this.validtime + validtime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setValidtime(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Email v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Email v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Email newEmail(Integer id, String unqid, String title, String content, String awardJson, Boolean isRead, Boolean isReceive, Long creattime, Long validtime) {
        Email result = new Email();
        result.id = id;
        result.unqid = unqid;
        result.title = title;
        result.content = content;
        result.awardJson = awardJson;
        result.isRead = isRead;
        result.isReceive = isReceive;
        result.creattime = creattime;
        result.validtime = validtime;
        return result;
    }

    public static Email newEmail(Email email) {
        Email result = new Email();
        result.id = email.id;
        result.unqid = email.unqid;
        result.title = email.title;
        result.content = email.content;
        result.awardJson = email.awardJson;
        result.isRead = email.isRead;
        result.isReceive = email.isReceive;
        result.creattime = email.creattime;
        result.validtime = email.validtime;
        return result;
    }

    public Email createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getEmail(){
        Email email = null; // email
        { // new and insert Email (email)
            int id = 0; 	// id
            String unqid = ""; 	// unqid
            String title = ""; 	// title
            String content = ""; 	// content
            String awardJson = ""; 	// awardJson
            boolean isRead = true; 	// isRead
            boolean isReceive = true; 	// isReceive
            long creattime = 0; 	// creattime
            long validtime = 0; 	// validtime
            email = Email.newEmail(id, unqid, title, content, awardJson, isRead, isReceive, creattime, validtime);
        }
        email = email.insert();

        int id = email.getId(); 	// id
        String unqid = email.getUnqid(); 	// unqid
        String title = email.getTitle(); 	// title
        String content = email.getContent(); 	// content
        String awardJson = email.getAwardJson(); 	// awardJson
        boolean isRead = email.getIsRead(); 	// isRead
        boolean isReceive = email.getIsReceive(); 	// isReceive
        long creattime = email.getCreattime(); 	// creattime
        long validtime = email.getValidtime(); 	// validtime
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
        }
        return 0;
    }

    public Email setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Email setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        }
        return this;
    }

    public Email changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Email changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        }
        return this;
    }

    public Email changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Email changeIntWithMin(String fieldEn, int value2, int _min) {
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

    public Email setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Email setDouble(String fieldEn, double value2) {
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
        case CEn.title: 
            return title;
        case CEn.content: 
            return content;
        case CEn.awardJson: 
            return awardJson;
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
        case CEn.unqid:
            return unqid;
        case CEn.title:
            return title;
        case CEn.content:
            return content;
        case CEn.awardJson:
            return awardJson;
        case CEn.isRead:
            return isRead;
        case CEn.isReceive:
            return isReceive;
        case CEn.creattime:
            return creattime;
        case CEn.validtime:
            return validtime;
        }
        return null;
    }

    public Email setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Email setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Email setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Email setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.unqid:
            return setUnqid(value2);
        case CEn.title:
            return setTitle(value2);
        case CEn.content:
            return setContent(value2);
        case CEn.awardJson:
            return setAwardJson(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Email setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Email setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.gb.db.bean.Email");
        result.put("id", id);
        result.put("unqid", unqid);
        result.put("title", title);
        result.put("content", content);
        result.put("awardJson", awardJson);
        result.put("isRead", isRead);
        result.put("isReceive", isReceive);
        result.put("creattime", creattime);
        result.put("validtime", validtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("unqid", unqid);
        result.put("title", title);
        result.put("content", content);
        result.put("awardJson", awardJson);
        result.put("isRead", isRead);
        result.put("isReceive", isReceive);
        result.put("creattime", creattime);
        result.put("validtime", validtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Email");
        result.put("id", id);
        result.put("unqid", unqid);
        result.put("title", title);
        result.put("content", content);
        result.put("awardJson", awardJson);
        result.put("isRead", isRead);
        result.put("isReceive", isReceive);
        result.put("creattime", creattime);
        result.put("validtime", validtime);
        return result;
    }

    public Email mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        String unqid = MapEx.getString(e, "unqid");
        String title = MapEx.getString(e, "title");
        String content = MapEx.getString(e, "content");
        String awardJson = MapEx.getString(e, "awardJson");
        Boolean isRead = MapEx.getBoolean(e, "isRead");
        Boolean isReceive = MapEx.getBoolean(e, "isReceive");
        Long creattime = MapEx.getLong(e, "creattime");
        Long validtime = MapEx.getLong(e, "validtime");

        if(id == null) id = 0;
        if(unqid == null) unqid = "";
        if(title == null) title = "";
        if(content == null) content = "";
        if(awardJson == null) awardJson = "";
        if(isRead == null) isRead = false;
        if(isReceive == null) isReceive = false;
        if(creattime == null) creattime = 0L;
        if(validtime == null) validtime = 0L;

        setId(id);
        setUnqid(unqid);
        setTitle(title);
        setContent(content);
        setAwardJson(awardJson);
        setIsRead(isRead);
        setIsReceive(isReceive);
        setCreattime(creattime);
        setValidtime(validtime);

        return this;
    }

    public static final Email mapTo(Map e){
        Email result = new Email();

        Integer id = MapEx.getInt(e, "id");
        String unqid = MapEx.getString(e, "unqid");
        String title = MapEx.getString(e, "title");
        String content = MapEx.getString(e, "content");
        String awardJson = MapEx.getString(e, "awardJson");
        Boolean isRead = MapEx.getBoolean(e, "isRead");
        Boolean isReceive = MapEx.getBoolean(e, "isReceive");
        Long creattime = MapEx.getLong(e, "creattime");
        Long validtime = MapEx.getLong(e, "validtime");

        if(id == null) id = 0;
        if(unqid == null) unqid = "";
        if(title == null) title = "";
        if(content == null) content = "";
        if(awardJson == null) awardJson = "";
        if(isRead == null) isRead = false;
        if(isReceive == null) isReceive = false;
        if(creattime == null) creattime = 0L;
        if(validtime == null) validtime = 0L;

        result.id = id;
        result.unqid = unqid;
        result.title = title;
        result.content = content;
        result.awardJson = awardJson;
        result.isRead = isRead;
        result.isReceive = isReceive;
        result.creattime = creattime;
        result.validtime = validtime;

        return result;
    }

    public static final Email originalTo(Map e){
        Email result = new Email();

        Integer id = MapEx.getInt(e, "id");
        String unqid = MapEx.getString(e, "unqid");
        String title = MapEx.getString(e, "title");
        String content = MapEx.getString(e, "content");
        String awardJson = MapEx.getString(e, "awardJson");
        Boolean isRead = MapEx.getBoolean(e, "isRead");
        Boolean isReceive = MapEx.getBoolean(e, "isReceive");
        Long creattime = MapEx.getLong(e, "creattime");
        Long validtime = MapEx.getLong(e, "validtime");

        if(id == null) id = 0;
        if(unqid == null) unqid = "";
        if(title == null) title = "";
        if(content == null) content = "";
        if(awardJson == null) awardJson = "";
        if(isRead == null) isRead = false;
        if(isReceive == null) isReceive = false;
        if(creattime == null) creattime = 0L;
        if(validtime == null) validtime = 0L;

        result.id = id;
        result.unqid = unqid;
        result.title = title;
        result.content = content;
        result.awardJson = awardJson;
        result.isRead = isRead;
        result.isReceive = isReceive;
        result.creattime = creattime;
        result.validtime = validtime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Email createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 9);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "unqid", unqid);
            writeMapEntry(out, "title", title);
            writeMapEntry(out, "content", content);
            writeMapEntry(out, "awardJson", awardJson);
            writeMapEntry(out, "isRead", isRead);
            writeMapEntry(out, "isReceive", isReceive);
            writeMapEntry(out, "creattime", creattime);
            writeMapEntry(out, "validtime", validtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Email createFor(byte[] b) throws Exception {
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
    public static final Email loadByKey(int id) {
        return EmailEntity.getByKey(id);
    }

    public static final Future<Email> asyncByKey(int id) {
        return EmailEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Email insert() {
        Email result = EmailEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Email asyncInsert() {
        Email result = EmailEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Email insert2() {
        return EmailEntity.insert2(this);
    }

    public final Email asyncInsert2() {
        Email result = EmailEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Email update() {
        return EmailEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        EmailEntity.asyncUpdate( this );
        return true;
    }

    public final Email insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return EmailEntity.delete(id);
    }

    public final void asyncDelete() {
        EmailEntity.asyncDelete(id);
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

    public Email clone2() {
        try{
            return (Email) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
