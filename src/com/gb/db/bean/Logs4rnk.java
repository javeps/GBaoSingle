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

//gbosng_design - logs4rnk
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Logs4rnk extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 1925034782; // com.gb.db.bean.Logs4rnk

    public static String TABLENAME = "logs4rnk";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String topindex = "topindex"; public static final String unqid = "unqid"; public static final String title = "title"; public static final String content = "content"; public static final String awardJson = "awardJson"; public static final String creattime = "creattime";  }
    public static final class CEn { public static final String id = "id"; public static final String topindex = "topindex"; public static final String unqid = "unqid"; public static final String title = "title"; public static final String content = "content"; public static final String awardJson = "awardJson"; public static final String creattime = "creattime";  }
    public static final String[] carrays ={"id", "topindex", "unqid", "title", "content", "awardJson", "creattime"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "TINYTEXT", "TEXT", "TEXT", "BIGINT"};


    public int id;
    public int topindex;
    public String unqid;
    public String title;
    public String content;
    public String awardJson;
    public long creattime;

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

    public Logs4rnk setId(int id){
        this.id = id;
        return this;
    }

    public int getTopindex(){
        return topindex;
    }

    public Logs4rnk setTopindex(int topindex){
        int _old = this.topindex;
        this.topindex = topindex;
        changeIt(Col.topindex, _old, topindex);
        return this;
    }

    public Logs4rnk changeTopindex(int topindex){
        return setTopindex(this.topindex + topindex);
    }

    public Logs4rnk changeTopindexWithMin(int topindex, int _min){
        int _v2 = this.topindex + topindex;
        return setTopindex(_v2 < _min  ? _min : _v2);
    }

    public Logs4rnk changeTopindexWithMax(int topindex, int _max){
        int _v2 = this.topindex + topindex;
        return setTopindex(_v2 > _max  ? _max : _v2);
    }

    public Logs4rnk changeTopindexWithMinMax(int topindex, int _min, int _max){
        int _v2 = this.topindex + topindex;
        _v2 = _v2 > _max  ? _max : _v2;
        return setTopindex(_v2 < _min  ? _min : _v2);
    }

    public String getUnqid(){
        return unqid;
    }

    public Logs4rnk setUnqid(String unqid){
        String _old = this.unqid;
        this.unqid = unqid;
        changeIt(Col.unqid, _old, unqid);
        return this;
    }

    public String getTitle(){
        return title;
    }

    public Logs4rnk setTitle(String title){
        String _old = this.title;
        this.title = title;
        changeIt(Col.title, _old, title);
        return this;
    }

    public String getContent(){
        return content;
    }

    public Logs4rnk setContent(String content){
        String _old = this.content;
        this.content = content;
        changeIt(Col.content, _old, content);
        return this;
    }

    public String getAwardJson(){
        return awardJson;
    }

    public Logs4rnk setAwardJson(String awardJson){
        String _old = this.awardJson;
        this.awardJson = awardJson;
        changeIt(Col.awardJson, _old, awardJson);
        return this;
    }

    public long getCreattime(){
        return creattime;
    }

    public Logs4rnk setCreattime(long creattime){
        long _old = this.creattime;
        this.creattime = creattime;
        changeIt(Col.creattime, _old, creattime);
        return this;
    }

    public Logs4rnk changeCreattime(long creattime){
        return setCreattime(this.creattime + creattime);
    }

    public Logs4rnk changeCreattimeWithMin(long creattime, long _min){
        long _v2 = this.creattime + creattime;
        return setCreattime(_v2 < _min  ? _min : _v2);
    }

    public Logs4rnk changeCreattimeWithMax(long creattime, long _max){
        long _v2 = this.creattime + creattime;
        return setCreattime(_v2 > _max  ? _max : _v2);
    }

    public Logs4rnk changeCreattimeWithMinMax(long creattime, long _min, long _max){
        long _v2 = this.creattime + creattime;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCreattime(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Logs4rnk v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Logs4rnk v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Logs4rnk newLogs4rnk(Integer id, Integer topindex, String unqid, String title, String content, String awardJson, Long creattime) {
        Logs4rnk result = new Logs4rnk();
        result.id = id;
        result.topindex = topindex;
        result.unqid = unqid;
        result.title = title;
        result.content = content;
        result.awardJson = awardJson;
        result.creattime = creattime;
        return result;
    }

    public static Logs4rnk newLogs4rnk(Logs4rnk logs4rnk) {
        Logs4rnk result = new Logs4rnk();
        result.id = logs4rnk.id;
        result.topindex = logs4rnk.topindex;
        result.unqid = logs4rnk.unqid;
        result.title = logs4rnk.title;
        result.content = logs4rnk.content;
        result.awardJson = logs4rnk.awardJson;
        result.creattime = logs4rnk.creattime;
        return result;
    }

    public Logs4rnk createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getLogs4rnk(){
        Logs4rnk logs4rnk = null; // logs4rnk
        { // new and insert Logs4rnk (logs4rnk)
            int id = 0; 	// id
            int topindex = 0; 	// topindex
            String unqid = ""; 	// unqid
            String title = ""; 	// title
            String content = ""; 	// content
            String awardJson = ""; 	// awardJson
            long creattime = 0; 	// creattime
            logs4rnk = Logs4rnk.newLogs4rnk(id, topindex, unqid, title, content, awardJson, creattime);
        }
        logs4rnk = logs4rnk.insert();

        int id = logs4rnk.getId(); 	// id
        int topindex = logs4rnk.getTopindex(); 	// topindex
        String unqid = logs4rnk.getUnqid(); 	// unqid
        String title = logs4rnk.getTitle(); 	// title
        String content = logs4rnk.getContent(); 	// content
        String awardJson = logs4rnk.getAwardJson(); 	// awardJson
        long creattime = logs4rnk.getCreattime(); 	// creattime
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
        case CEn.topindex:
            return topindex;
        }
        return 0;
    }

    public Logs4rnk setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Logs4rnk setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.topindex:
            return setTopindex(value2);
        }
        return this;
    }

    public Logs4rnk changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Logs4rnk changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.topindex:
            return changeTopindex(value2);
        }
        return this;
    }

    public Logs4rnk changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Logs4rnk changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.topindex:
            return changeTopindexWithMin(value2, _min);
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

    public Logs4rnk setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Logs4rnk setDouble(String fieldEn, double value2) {
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
        case CEn.topindex:
            return topindex;
        case CEn.unqid:
            return unqid;
        case CEn.title:
            return title;
        case CEn.content:
            return content;
        case CEn.awardJson:
            return awardJson;
        case CEn.creattime:
            return creattime;
        }
        return null;
    }

    public Logs4rnk setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Logs4rnk setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Logs4rnk setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Logs4rnk setStr(String fieldEn, String value2) {
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

    public Logs4rnk setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Logs4rnk setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.gb.db.bean.Logs4rnk");
        result.put("id", id);
        result.put("topindex", topindex);
        result.put("unqid", unqid);
        result.put("title", title);
        result.put("content", content);
        result.put("awardJson", awardJson);
        result.put("creattime", creattime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("topindex", topindex);
        result.put("unqid", unqid);
        result.put("title", title);
        result.put("content", content);
        result.put("awardJson", awardJson);
        result.put("creattime", creattime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = toBasicMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Logs4rnk");
        return result;
    }

    public Logs4rnk mapToThis(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer topindex = MapEx.getInt(e, "topindex");
        String unqid = MapEx.getString(e, "unqid");
        String title = MapEx.getString(e, "title");
        String content = MapEx.getString(e, "content");
        String awardJson = MapEx.getString(e, "awardJson");
        Long creattime = MapEx.getLong(e, "creattime");

        if(unqid == null) unqid = "";
        if(title == null) title = "";
        if(content == null) content = "";
        if(awardJson == null) awardJson = "";

        setId(id);
        setTopindex(topindex);
        setUnqid(unqid);
        setTitle(title);
        setContent(content);
        setAwardJson(awardJson);
        setCreattime(creattime);

        return this;
    }

    public static final Logs4rnk mapTo(Map e){
        Logs4rnk result = new Logs4rnk();
        result.mapToThis(e);
        return result;
    }

    public static final Logs4rnk originalTo(Map e){
        Logs4rnk result = new Logs4rnk();

        Integer id = MapEx.getInt(e, "id");
        Integer topindex = MapEx.getInt(e, "topindex");
        String unqid = MapEx.getString(e, "unqid");
        String title = MapEx.getString(e, "title");
        String content = MapEx.getString(e, "content");
        String awardJson = MapEx.getString(e, "awardJson");
        Long creattime = MapEx.getLong(e, "creattime");

        if(unqid == null) unqid = "";
        if(title == null) title = "";
        if(content == null) content = "";
        if(awardJson == null) awardJson = "";

        result.id = id;
        result.topindex = topindex;
        result.unqid = unqid;
        result.title = title;
        result.content = content;
        result.awardJson = awardJson;
        result.creattime = creattime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Logs4rnk createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 7);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "topindex", topindex);
            writeMapEntry(out, "unqid", unqid);
            writeMapEntry(out, "title", title);
            writeMapEntry(out, "content", content);
            writeMapEntry(out, "awardJson", awardJson);
            writeMapEntry(out, "creattime", creattime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Logs4rnk createFor(byte[] b) throws Exception {
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
    public static final Logs4rnk loadByKey(int id) {
        return Logs4rnkEntity.getByKey(id);
    }

    public static final Future<Logs4rnk> asyncByKey(int id) {
        return Logs4rnkEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Logs4rnk insert() {
        Logs4rnk result = Logs4rnkEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Logs4rnk asyncInsert() {
        Logs4rnk result = Logs4rnkEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Logs4rnk insert2() {
        return Logs4rnkEntity.insert2(this);
    }

    public final Logs4rnk asyncInsert2() {
        Logs4rnk result = Logs4rnkEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Logs4rnk update() {
        return Logs4rnkEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Logs4rnkEntity.asyncUpdate( this );
        return true;
    }

    public final Logs4rnk insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Logs4rnkEntity.delete(id);
    }

    public final void asyncDelete() {
        Logs4rnkEntity.asyncDelete(id);
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

    public Logs4rnk clone2() {
        try{
            return (Logs4rnk) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
