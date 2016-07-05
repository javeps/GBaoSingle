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

//gbosng_design - email4rnk
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Email4rnk extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = -1143310045; // com.gb.db.bean.Email4rnk

    public static String TABLENAME = "email4rnk";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String indexBegin = "indexBegin"; public static final String indexEnd = "indexEnd"; public static final String title = "title"; public static final String content = "content"; public static final String awardJson = "awardJson"; public static final String creattime = "creattime"; public static final String validtime = "validtime";  }
    public static final class CEn { public static final String id = "id"; public static final String indexBegin = "indexBegin"; public static final String indexEnd = "indexEnd"; public static final String title = "title"; public static final String content = "content"; public static final String awardJson = "awardJson"; public static final String creattime = "creattime"; public static final String validtime = "validtime";  }
    public static final String[] carrays ={"id", "indexBegin", "indexEnd", "title", "content", "awardJson", "creattime", "validtime"};
    public static final String[] dbTypes ={"INT", "INT", "INT", "TINYTEXT", "TEXT", "TEXT", "BIGINT", "BIGINT"};


    public int id;
    public int indexBegin;
    public int indexEnd;
    public String title;
    public String content;
    public String awardJson;
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

    public Email4rnk setId(int id){
        this.id = id;
        return this;
    }

    public int getIndexBegin(){
        return indexBegin;
    }

    public Email4rnk setIndexBegin(int indexBegin){
        int _old = this.indexBegin;
        this.indexBegin = indexBegin;
        changeIt(Col.indexBegin, _old, indexBegin);
        return this;
    }

    public Email4rnk changeIndexBegin(int indexBegin){
        return setIndexBegin(this.indexBegin + indexBegin);
    }

    public Email4rnk changeIndexBeginWithMin(int indexBegin, int _min){
        int _v2 = this.indexBegin + indexBegin;
        return setIndexBegin(_v2 < _min  ? _min : _v2);
    }

    public Email4rnk changeIndexBeginWithMax(int indexBegin, int _max){
        int _v2 = this.indexBegin + indexBegin;
        return setIndexBegin(_v2 > _max  ? _max : _v2);
    }

    public Email4rnk changeIndexBeginWithMinMax(int indexBegin, int _min, int _max){
        int _v2 = this.indexBegin + indexBegin;
        _v2 = _v2 > _max  ? _max : _v2;
        return setIndexBegin(_v2 < _min  ? _min : _v2);
    }

    public int getIndexEnd(){
        return indexEnd;
    }

    public Email4rnk setIndexEnd(int indexEnd){
        int _old = this.indexEnd;
        this.indexEnd = indexEnd;
        changeIt(Col.indexEnd, _old, indexEnd);
        return this;
    }

    public Email4rnk changeIndexEnd(int indexEnd){
        return setIndexEnd(this.indexEnd + indexEnd);
    }

    public Email4rnk changeIndexEndWithMin(int indexEnd, int _min){
        int _v2 = this.indexEnd + indexEnd;
        return setIndexEnd(_v2 < _min  ? _min : _v2);
    }

    public Email4rnk changeIndexEndWithMax(int indexEnd, int _max){
        int _v2 = this.indexEnd + indexEnd;
        return setIndexEnd(_v2 > _max  ? _max : _v2);
    }

    public Email4rnk changeIndexEndWithMinMax(int indexEnd, int _min, int _max){
        int _v2 = this.indexEnd + indexEnd;
        _v2 = _v2 > _max  ? _max : _v2;
        return setIndexEnd(_v2 < _min  ? _min : _v2);
    }

    public String getTitle(){
        return title;
    }

    public Email4rnk setTitle(String title){
        String _old = this.title;
        this.title = title;
        changeIt(Col.title, _old, title);
        return this;
    }

    public String getContent(){
        return content;
    }

    public Email4rnk setContent(String content){
        String _old = this.content;
        this.content = content;
        changeIt(Col.content, _old, content);
        return this;
    }

    public String getAwardJson(){
        return awardJson;
    }

    public Email4rnk setAwardJson(String awardJson){
        String _old = this.awardJson;
        this.awardJson = awardJson;
        changeIt(Col.awardJson, _old, awardJson);
        return this;
    }

    public long getCreattime(){
        return creattime;
    }

    public Email4rnk setCreattime(long creattime){
        long _old = this.creattime;
        this.creattime = creattime;
        changeIt(Col.creattime, _old, creattime);
        return this;
    }

    public Email4rnk changeCreattime(long creattime){
        return setCreattime(this.creattime + creattime);
    }

    public Email4rnk changeCreattimeWithMin(long creattime, long _min){
        long _v2 = this.creattime + creattime;
        return setCreattime(_v2 < _min  ? _min : _v2);
    }

    public Email4rnk changeCreattimeWithMax(long creattime, long _max){
        long _v2 = this.creattime + creattime;
        return setCreattime(_v2 > _max  ? _max : _v2);
    }

    public Email4rnk changeCreattimeWithMinMax(long creattime, long _min, long _max){
        long _v2 = this.creattime + creattime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCreattime(_v2 < _min  ? _min : _v2);
    }

    public long getValidtime(){
        return validtime;
    }

    public Email4rnk setValidtime(long validtime){
        long _old = this.validtime;
        this.validtime = validtime;
        changeIt(Col.validtime, _old, validtime);
        return this;
    }

    public Email4rnk changeValidtime(long validtime){
        return setValidtime(this.validtime + validtime);
    }

    public Email4rnk changeValidtimeWithMin(long validtime, long _min){
        long _v2 = this.validtime + validtime;
        return setValidtime(_v2 < _min  ? _min : _v2);
    }

    public Email4rnk changeValidtimeWithMax(long validtime, long _max){
        long _v2 = this.validtime + validtime;
        return setValidtime(_v2 > _max  ? _max : _v2);
    }

    public Email4rnk changeValidtimeWithMinMax(long validtime, long _min, long _max){
        long _v2 = this.validtime + validtime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setValidtime(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Email4rnk v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Email4rnk v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Email4rnk newEmail4rnk(Integer id, Integer indexBegin, Integer indexEnd, String title, String content, String awardJson, Long creattime, Long validtime) {
        Email4rnk result = new Email4rnk();
        result.id = id;
        result.indexBegin = indexBegin;
        result.indexEnd = indexEnd;
        result.title = title;
        result.content = content;
        result.awardJson = awardJson;
        result.creattime = creattime;
        result.validtime = validtime;
        return result;
    }

    public static Email4rnk newEmail4rnk(Email4rnk email4rnk) {
        Email4rnk result = new Email4rnk();
        result.id = email4rnk.id;
        result.indexBegin = email4rnk.indexBegin;
        result.indexEnd = email4rnk.indexEnd;
        result.title = email4rnk.title;
        result.content = email4rnk.content;
        result.awardJson = email4rnk.awardJson;
        result.creattime = email4rnk.creattime;
        result.validtime = email4rnk.validtime;
        return result;
    }

    public Email4rnk createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getEmail4rnk(){
        Email4rnk email4rnk = null; // email4rnk
        { // new and insert Email4rnk (email4rnk)
            int id = 0; 	// id
            int indexBegin = 0; 	// indexBegin
            int indexEnd = 0; 	// indexEnd
            String title = ""; 	// title
            String content = ""; 	// content
            String awardJson = ""; 	// awardJson
            long creattime = 0; 	// creattime
            long validtime = 0; 	// validtime
            email4rnk = Email4rnk.newEmail4rnk(id, indexBegin, indexEnd, title, content, awardJson, creattime, validtime);
        }
        email4rnk = email4rnk.insert();

        int id = email4rnk.getId(); 	// id
        int indexBegin = email4rnk.getIndexBegin(); 	// indexBegin
        int indexEnd = email4rnk.getIndexEnd(); 	// indexEnd
        String title = email4rnk.getTitle(); 	// title
        String content = email4rnk.getContent(); 	// content
        String awardJson = email4rnk.getAwardJson(); 	// awardJson
        long creattime = email4rnk.getCreattime(); 	// creattime
        long validtime = email4rnk.getValidtime(); 	// validtime
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
        case CEn.indexBegin:
            return indexBegin;
        case CEn.indexEnd:
            return indexEnd;
        }
        return 0;
    }

    public Email4rnk setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Email4rnk setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.indexBegin:
            return setIndexBegin(value2);
        case CEn.indexEnd:
            return setIndexEnd(value2);
        }
        return this;
    }

    public Email4rnk changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Email4rnk changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.indexBegin:
            return changeIndexBegin(value2);
        case CEn.indexEnd:
            return changeIndexEnd(value2);
        }
        return this;
    }

    public Email4rnk changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Email4rnk changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.indexBegin:
            return changeIndexBeginWithMin(value2, _min);
        case CEn.indexEnd:
            return changeIndexEndWithMin(value2, _min);
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

    public Email4rnk setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Email4rnk setDouble(String fieldEn, double value2) {
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
        case CEn.indexBegin:
            return indexBegin;
        case CEn.indexEnd:
            return indexEnd;
        case CEn.title:
            return title;
        case CEn.content:
            return content;
        case CEn.awardJson:
            return awardJson;
        case CEn.creattime:
            return creattime;
        case CEn.validtime:
            return validtime;
        }
        return null;
    }

    public Email4rnk setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Email4rnk setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Email4rnk setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Email4rnk setStr(String fieldEn, String value2) {
        switch(fieldEn) {
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

    public Email4rnk setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Email4rnk setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.gb.db.bean.Email4rnk");
        result.put("id", id);
        result.put("indexBegin", indexBegin);
        result.put("indexEnd", indexEnd);
        result.put("title", title);
        result.put("content", content);
        result.put("awardJson", awardJson);
        result.put("creattime", creattime);
        result.put("validtime", validtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("indexBegin", indexBegin);
        result.put("indexEnd", indexEnd);
        result.put("title", title);
        result.put("content", content);
        result.put("awardJson", awardJson);
        result.put("creattime", creattime);
        result.put("validtime", validtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Email4rnk");
        result.put("id", id);
        result.put("indexBegin", indexBegin);
        result.put("indexEnd", indexEnd);
        result.put("title", title);
        result.put("content", content);
        result.put("awardJson", awardJson);
        result.put("creattime", creattime);
        result.put("validtime", validtime);
        return result;
    }

    public Email4rnk mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer indexBegin = MapEx.getInt(e, "indexBegin");
        Integer indexEnd = MapEx.getInt(e, "indexEnd");
        String title = MapEx.getString(e, "title");
        String content = MapEx.getString(e, "content");
        String awardJson = MapEx.getString(e, "awardJson");
        Long creattime = MapEx.getLong(e, "creattime");
        Long validtime = MapEx.getLong(e, "validtime");

        if(id == null) id = 0;
        if(indexBegin == null) indexBegin = 0;
        if(indexEnd == null) indexEnd = 0;
        if(title == null) title = "";
        if(content == null) content = "";
        if(awardJson == null) awardJson = "";
        if(creattime == null) creattime = 0L;
        if(validtime == null) validtime = 0L;

        setId(id);
        setIndexBegin(indexBegin);
        setIndexEnd(indexEnd);
        setTitle(title);
        setContent(content);
        setAwardJson(awardJson);
        setCreattime(creattime);
        setValidtime(validtime);

        return this;
    }

    public static final Email4rnk mapTo(Map e){
        Email4rnk result = new Email4rnk();

        Integer id = MapEx.getInt(e, "id");
        Integer indexBegin = MapEx.getInt(e, "indexBegin");
        Integer indexEnd = MapEx.getInt(e, "indexEnd");
        String title = MapEx.getString(e, "title");
        String content = MapEx.getString(e, "content");
        String awardJson = MapEx.getString(e, "awardJson");
        Long creattime = MapEx.getLong(e, "creattime");
        Long validtime = MapEx.getLong(e, "validtime");

        if(id == null) id = 0;
        if(indexBegin == null) indexBegin = 0;
        if(indexEnd == null) indexEnd = 0;
        if(title == null) title = "";
        if(content == null) content = "";
        if(awardJson == null) awardJson = "";
        if(creattime == null) creattime = 0L;
        if(validtime == null) validtime = 0L;

        result.id = id;
        result.indexBegin = indexBegin;
        result.indexEnd = indexEnd;
        result.title = title;
        result.content = content;
        result.awardJson = awardJson;
        result.creattime = creattime;
        result.validtime = validtime;

        return result;
    }

    public static final Email4rnk originalTo(Map e){
        Email4rnk result = new Email4rnk();

        Integer id = MapEx.getInt(e, "id");
        Integer indexBegin = MapEx.getInt(e, "indexBegin");
        Integer indexEnd = MapEx.getInt(e, "indexEnd");
        String title = MapEx.getString(e, "title");
        String content = MapEx.getString(e, "content");
        String awardJson = MapEx.getString(e, "awardJson");
        Long creattime = MapEx.getLong(e, "creattime");
        Long validtime = MapEx.getLong(e, "validtime");

        if(id == null) id = 0;
        if(indexBegin == null) indexBegin = 0;
        if(indexEnd == null) indexEnd = 0;
        if(title == null) title = "";
        if(content == null) content = "";
        if(awardJson == null) awardJson = "";
        if(creattime == null) creattime = 0L;
        if(validtime == null) validtime = 0L;

        result.id = id;
        result.indexBegin = indexBegin;
        result.indexEnd = indexEnd;
        result.title = title;
        result.content = content;
        result.awardJson = awardJson;
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

    public static final Email4rnk createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 8);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "indexBegin", indexBegin);
            writeMapEntry(out, "indexEnd", indexEnd);
            writeMapEntry(out, "title", title);
            writeMapEntry(out, "content", content);
            writeMapEntry(out, "awardJson", awardJson);
            writeMapEntry(out, "creattime", creattime);
            writeMapEntry(out, "validtime", validtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Email4rnk createFor(byte[] b) throws Exception {
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
    public static final Email4rnk loadByKey(int id) {
        return Email4rnkEntity.getByKey(id);
    }

    public static final Future<Email4rnk> asyncByKey(int id) {
        return Email4rnkEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Email4rnk insert() {
        Email4rnk result = Email4rnkEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Email4rnk asyncInsert() {
        Email4rnk result = Email4rnkEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Email4rnk insert2() {
        return Email4rnkEntity.insert2(this);
    }

    public final Email4rnk asyncInsert2() {
        Email4rnk result = Email4rnkEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Email4rnk update() {
        return Email4rnkEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Email4rnkEntity.asyncUpdate( this );
        return true;
    }

    public final Email4rnk insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Email4rnkEntity.delete(id);
    }

    public final void asyncDelete() {
        Email4rnkEntity.asyncDelete(id);
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

    public Email4rnk clone2() {
        try{
            return (Email4rnk) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
