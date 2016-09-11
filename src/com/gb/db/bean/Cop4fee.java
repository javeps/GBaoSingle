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

//gbosng_design - cop4fee
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Cop4fee extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 948076514; // com.gb.db.bean.Cop4fee

    public static String TABLENAME = "cop4fee";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String unqkey = "unqkey"; public static final String chn = "chn"; public static final String version = "version"; public static final String copfee = "copfee"; public static final String createtime = "createtime"; public static final String lasttime = "lasttime"; public static final String validBegtime = "validBegtime"; public static final String validEndtime = "validEndtime";  }
    public static final class CEn { public static final String id = "id"; public static final String unqkey = "unqkey"; public static final String chn = "chn"; public static final String version = "version"; public static final String copfee = "copfee"; public static final String createtime = "createtime"; public static final String lasttime = "lasttime"; public static final String validBegtime = "validBegtime"; public static final String validEndtime = "validEndtime";  }
    public static final String[] carrays ={"id", "unqkey", "chn", "version", "copfee", "createtime", "lasttime", "validBegtime", "validEndtime"};
    public static final String[] dbTypes ={"INT", "VARCHAR", "VARCHAR", "VARCHAR", "INT", "DATETIME", "DATETIME", "DATETIME", "DATETIME"};


    public int id;
    public String unqkey;
    public String chn;
    public String version;
    public int copfee;
    public java.util.Date createtime;
    public java.util.Date lasttime;
    public java.util.Date validBegtime;
    public java.util.Date validEndtime;

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

    public Cop4fee setId(int id){
        this.id = id;
        return this;
    }

    public String getUnqkey(){
        return unqkey;
    }

    public Cop4fee setUnqkey(String unqkey){
        String _old = this.unqkey;
        this.unqkey = unqkey;
        changeIt(Col.unqkey, _old, unqkey);
        return this;
    }

    public String getChn(){
        return chn;
    }

    public Cop4fee setChn(String chn){
        String _old = this.chn;
        this.chn = chn;
        changeIt(Col.chn, _old, chn);
        return this;
    }

    public String getVersion(){
        return version;
    }

    public Cop4fee setVersion(String version){
        String _old = this.version;
        this.version = version;
        changeIt(Col.version, _old, version);
        return this;
    }

    public int getCopfee(){
        return copfee;
    }

    public Cop4fee setCopfee(int copfee){
        int _old = this.copfee;
        this.copfee = copfee;
        changeIt(Col.copfee, _old, copfee);
        return this;
    }

    public Cop4fee changeCopfee(int copfee){
        return setCopfee(this.copfee + copfee);
    }

    public Cop4fee changeCopfeeWithMin(int copfee, int _min){
        int _v2 = this.copfee + copfee;
        return setCopfee(_v2 < _min  ? _min : _v2);
    }

    public Cop4fee changeCopfeeWithMax(int copfee, int _max){
        int _v2 = this.copfee + copfee;
        return setCopfee(_v2 > _max  ? _max : _v2);
    }

    public Cop4fee changeCopfeeWithMinMax(int copfee, int _min, int _max){
        int _v2 = this.copfee + copfee;
        _v2 = _v2 > _max  ? _max : _v2;
        return setCopfee(_v2 < _min  ? _min : _v2);
    }

    public java.util.Date getCreatetime(){
        return createtime;
    }

    public Cop4fee setCreatetime(java.util.Date createtime){
        java.util.Date _old = this.createtime;
        this.createtime = createtime;
        changeIt(Col.createtime, _old, createtime);
        return this;
    }

    public java.util.Date getLasttime(){
        return lasttime;
    }

    public Cop4fee setLasttime(java.util.Date lasttime){
        java.util.Date _old = this.lasttime;
        this.lasttime = lasttime;
        changeIt(Col.lasttime, _old, lasttime);
        return this;
    }

    public java.util.Date getValidBegtime(){
        return validBegtime;
    }

    public Cop4fee setValidBegtime(java.util.Date validBegtime){
        java.util.Date _old = this.validBegtime;
        this.validBegtime = validBegtime;
        changeIt(Col.validBegtime, _old, validBegtime);
        return this;
    }

    public java.util.Date getValidEndtime(){
        return validEndtime;
    }

    public Cop4fee setValidEndtime(java.util.Date validEndtime){
        java.util.Date _old = this.validEndtime;
        this.validEndtime = validEndtime;
        changeIt(Col.validEndtime, _old, validEndtime);
        return this;
    }

    public int compareTo(Cop4fee v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Cop4fee v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Cop4fee newCop4fee(Integer id, String unqkey, String chn, String version, Integer copfee, java.util.Date createtime, java.util.Date lasttime, java.util.Date validBegtime, java.util.Date validEndtime) {
        Cop4fee result = new Cop4fee();
        result.id = id;
        result.unqkey = unqkey;
        result.chn = chn;
        result.version = version;
        result.copfee = copfee;
        result.createtime = createtime;
        result.lasttime = lasttime;
        result.validBegtime = validBegtime;
        result.validEndtime = validEndtime;
        return result;
    }

    public static Cop4fee newCop4fee(Cop4fee cop4fee) {
        Cop4fee result = new Cop4fee();
        result.id = cop4fee.id;
        result.unqkey = cop4fee.unqkey;
        result.chn = cop4fee.chn;
        result.version = cop4fee.version;
        result.copfee = cop4fee.copfee;
        result.createtime = cop4fee.createtime;
        result.lasttime = cop4fee.lasttime;
        result.validBegtime = cop4fee.validBegtime;
        result.validEndtime = cop4fee.validEndtime;
        return result;
    }

    public Cop4fee createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getCop4fee(){
        Cop4fee cop4fee = null; // cop4fee
        { // new and insert Cop4fee (cop4fee)
            int id = 0; 	// id
            String unqkey = ""; 	// unqkey
            String chn = ""; 	// chn
            String version = ""; 	// version
            int copfee = 0; 	// copfee
            Date createtime = new Date(); 	// createtime
            Date lasttime = new Date(); 	// lasttime
            Date validBegtime = new Date(); 	// validBegtime
            Date validEndtime = new Date(); 	// validEndtime
            cop4fee = Cop4fee.newCop4fee(id, unqkey, chn, version, copfee, createtime, lasttime, validBegtime, validEndtime);
        }
        cop4fee = cop4fee.insert();

        int id = cop4fee.getId(); 	// id
        String unqkey = cop4fee.getUnqkey(); 	// unqkey
        String chn = cop4fee.getChn(); 	// chn
        String version = cop4fee.getVersion(); 	// version
        int copfee = cop4fee.getCopfee(); 	// copfee
        Date createtime = cop4fee.getCreatetime(); 	// createtime
        Date lasttime = cop4fee.getLasttime(); 	// lasttime
        Date validBegtime = cop4fee.getValidBegtime(); 	// validBegtime
        Date validEndtime = cop4fee.getValidEndtime(); 	// validEndtime
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
        case CEn.copfee:
            return copfee;
        }
        return 0;
    }

    public Cop4fee setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Cop4fee setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.copfee:
            return setCopfee(value2);
        }
        return this;
    }

    public Cop4fee changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Cop4fee changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.copfee:
            return changeCopfee(value2);
        }
        return this;
    }

    public Cop4fee changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Cop4fee changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.copfee:
            return changeCopfeeWithMin(value2, _min);
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

    public Cop4fee setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Cop4fee setDouble(String fieldEn, double value2) {
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
        case CEn.chn: 
            return chn;
        case CEn.version: 
            return version;
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
        case CEn.chn:
            return chn;
        case CEn.version:
            return version;
        case CEn.copfee:
            return copfee;
        case CEn.createtime:
            return createtime;
        case CEn.lasttime:
            return lasttime;
        case CEn.validBegtime:
            return validBegtime;
        case CEn.validEndtime:
            return validEndtime;
        }
        return null;
    }

    public Cop4fee setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Cop4fee setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Cop4fee setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Cop4fee setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.unqkey:
            return setUnqkey(value2);
        case CEn.chn:
            return setChn(value2);
        case CEn.version:
            return setVersion(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Cop4fee setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Cop4fee setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.gb.db.bean.Cop4fee");
        result.put("id", id);
        result.put("unqkey", unqkey);
        result.put("chn", chn);
        result.put("version", version);
        result.put("copfee", copfee);
        result.put("createtime", createtime);
        result.put("lasttime", lasttime);
        result.put("validBegtime", validBegtime);
        result.put("validEndtime", validEndtime);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("unqkey", unqkey);
        result.put("chn", chn);
        result.put("version", version);
        result.put("copfee", copfee);
        result.put("createtime", createtime);
        result.put("lasttime", lasttime);
        result.put("validBegtime", validBegtime);
        result.put("validEndtime", validEndtime);
        return result;
    }

    public Map toOriginalMap(){
        Map result = toBasicMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Cop4fee");
        return result;
    }

    public Cop4fee mapToThis(Map e){
        Integer id = MapEx.getInt(e, "id");
        String unqkey = MapEx.getString(e, "unqkey");
        String chn = MapEx.getString(e, "chn");
        String version = MapEx.getString(e, "version");
        Integer copfee = MapEx.getInt(e, "copfee");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");
        java.util.Date validBegtime = MapEx.getDate(e, "validBegtime");
        java.util.Date validEndtime = MapEx.getDate(e, "validEndtime");

        if(unqkey == null) unqkey = "";
        if(chn == null) chn = "";
        if(version == null) version = "";
        if(createtime == null) createtime = new java.util.Date();
        if(lasttime == null) lasttime = new java.util.Date();
        if(validBegtime == null) validBegtime = new java.util.Date();
        if(validEndtime == null) validEndtime = new java.util.Date();

        setId(id);
        setUnqkey(unqkey);
        setChn(chn);
        setVersion(version);
        setCopfee(copfee);
        setCreatetime(createtime);
        setLasttime(lasttime);
        setValidBegtime(validBegtime);
        setValidEndtime(validEndtime);

        return this;
    }

    public static final Cop4fee mapTo(Map e){
        Cop4fee result = new Cop4fee();
        result.mapToThis(e);
        return result;
    }

    public static final Cop4fee originalTo(Map e){
        Cop4fee result = new Cop4fee();

        Integer id = MapEx.getInt(e, "id");
        String unqkey = MapEx.getString(e, "unqkey");
        String chn = MapEx.getString(e, "chn");
        String version = MapEx.getString(e, "version");
        Integer copfee = MapEx.getInt(e, "copfee");
        java.util.Date createtime = MapEx.getDate(e, "createtime");
        java.util.Date lasttime = MapEx.getDate(e, "lasttime");
        java.util.Date validBegtime = MapEx.getDate(e, "validBegtime");
        java.util.Date validEndtime = MapEx.getDate(e, "validEndtime");

        if(unqkey == null) unqkey = "";
        if(chn == null) chn = "";
        if(version == null) version = "";
        if(createtime == null) createtime = new java.util.Date();
        if(lasttime == null) lasttime = new java.util.Date();
        if(validBegtime == null) validBegtime = new java.util.Date();
        if(validEndtime == null) validEndtime = new java.util.Date();

        result.id = id;
        result.unqkey = unqkey;
        result.chn = chn;
        result.version = version;
        result.copfee = copfee;
        result.createtime = createtime;
        result.lasttime = lasttime;
        result.validBegtime = validBegtime;
        result.validEndtime = validEndtime;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Cop4fee createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 9);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "unqkey", unqkey);
            writeMapEntry(out, "chn", chn);
            writeMapEntry(out, "version", version);
            writeMapEntry(out, "copfee", copfee);
            writeMapEntry(out, "createtime", createtime);
            writeMapEntry(out, "lasttime", lasttime);
            writeMapEntry(out, "validBegtime", validBegtime);
            writeMapEntry(out, "validEndtime", validEndtime);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Cop4fee createFor(byte[] b) throws Exception {
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
    public static final Cop4fee loadByKey(int id) {
        return Cop4feeEntity.getByKey(id);
    }

    public static final Future<Cop4fee> asyncByKey(int id) {
        return Cop4feeEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Cop4fee insert() {
        Cop4fee result = Cop4feeEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Cop4fee asyncInsert() {
        Cop4fee result = Cop4feeEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Cop4fee insert2() {
        return Cop4feeEntity.insert2(this);
    }

    public final Cop4fee asyncInsert2() {
        Cop4fee result = Cop4feeEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Cop4fee update() {
        return Cop4feeEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        Cop4feeEntity.asyncUpdate( this );
        return true;
    }

    public final Cop4fee insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return Cop4feeEntity.delete(id);
    }

    public final void asyncDelete() {
        Cop4feeEntity.asyncDelete(id);
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

    public Cop4fee clone2() {
        try{
            return (Cop4fee) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
