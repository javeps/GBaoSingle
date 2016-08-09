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

//gbosng_design - rankwheel
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Rankwheel extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 412297531; // com.gb.db.bean.Rankwheel

    public static String TABLENAME = "rankwheel";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String indexs = "indexs"; public static final String unqid = "unqid"; public static final String pcid = "pcid"; public static final String pname = "pname"; public static final String wheel = "wheel";  }
    public static final class CEn { public static final String id = "id"; public static final String indexs = "indexs"; public static final String unqid = "unqid"; public static final String pcid = "pcid"; public static final String pname = "pname"; public static final String wheel = "wheel";  }
    public static final String[] carrays ={"id", "indexs", "unqid", "pcid", "pname", "wheel"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "INT", "VARCHAR", "INT"};


    public int id;
    public int indexs;
    public String unqid;
    public int pcid;
    public String pname;
    public int wheel;

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

    public Rankwheel setId(int id){
        this.id = id;
        return this;
    }

    public int getIndexs(){
        return indexs;
    }

    public Rankwheel setIndexs(int indexs){
        int _old = this.indexs;
        this.indexs = indexs;
        changeIt(Col.indexs, _old, indexs);
        return this;
    }

    public Rankwheel changeIndexs(int indexs){
        return setIndexs(this.indexs + indexs);
    }

    public Rankwheel changeIndexsWithMin(int indexs, int _min){
        int _v2 = this.indexs + indexs;
        return setIndexs(_v2 < _min  ? _min : _v2);
    }

    public Rankwheel changeIndexsWithMax(int indexs, int _max){
        int _v2 = this.indexs + indexs;
        return setIndexs(_v2 > _max  ? _max : _v2);
    }

    public Rankwheel changeIndexsWithMinMax(int indexs, int _min, int _max){
        int _v2 = this.indexs + indexs;
        _v2 = _v2 > _max  ? _max : _v2;
        return setIndexs(_v2 < _min  ? _min : _v2);
    }

    public String getUnqid(){
        return unqid;
    }

    public Rankwheel setUnqid(String unqid){
        String _old = this.unqid;
        this.unqid = unqid;
        changeIt(Col.unqid, _old, unqid);
        return this;
    }

    public int getPcid(){
        return pcid;
    }

    public Rankwheel setPcid(int pcid){
        int _old = this.pcid;
        this.pcid = pcid;
        changeIt(Col.pcid, _old, pcid);
        return this;
    }

    public Rankwheel changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Rankwheel changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Rankwheel changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Rankwheel changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPname(){
        return pname;
    }

    public Rankwheel setPname(String pname){
        String _old = this.pname;
        this.pname = pname;
        changeIt(Col.pname, _old, pname);
        return this;
    }

    public int getWheel(){
        return wheel;
    }

    public Rankwheel setWheel(int wheel){
        int _old = this.wheel;
        this.wheel = wheel;
        changeIt(Col.wheel, _old, wheel);
        return this;
    }

    public Rankwheel changeWheel(int wheel){
        return setWheel(this.wheel + wheel);
    }

    public Rankwheel changeWheelWithMin(int wheel, int _min){
        int _v2 = this.wheel + wheel;
        return setWheel(_v2 < _min  ? _min : _v2);
    }

    public Rankwheel changeWheelWithMax(int wheel, int _max){
        int _v2 = this.wheel + wheel;
        return setWheel(_v2 > _max  ? _max : _v2);
    }

    public Rankwheel changeWheelWithMinMax(int wheel, int _min, int _max){
        int _v2 = this.wheel + wheel;
        _v2 = _v2 > _max  ? _max : _v2;
        return setWheel(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Rankwheel v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Rankwheel v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Rankwheel newRankwheel(Integer id, Integer indexs, String unqid, Integer pcid, String pname, Integer wheel) {
        Rankwheel result = new Rankwheel();
        result.id = id;
        result.indexs = indexs;
        result.unqid = unqid;
        result.pcid = pcid;
        result.pname = pname;
        result.wheel = wheel;
        return result;
    }

    public static Rankwheel newRankwheel(Rankwheel rankwheel) {
        Rankwheel result = new Rankwheel();
        result.id = rankwheel.id;
        result.indexs = rankwheel.indexs;
        result.unqid = rankwheel.unqid;
        result.pcid = rankwheel.pcid;
        result.pname = rankwheel.pname;
        result.wheel = rankwheel.wheel;
        return result;
    }

    public Rankwheel createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRankwheel(){
        Rankwheel rankwheel = null; // rankwheel
        { // new and insert Rankwheel (rankwheel)
            int id = 0; 	// id
            int indexs = 0; 	// indexs
            String unqid = ""; 	// unqid
            int pcid = 0; 	// pcid
            String pname = ""; 	// pname
            int wheel = 0; 	// wheel
            rankwheel = Rankwheel.newRankwheel(id, indexs, unqid, pcid, pname, wheel);
        }
        rankwheel = rankwheel.insert();

        int id = rankwheel.getId(); 	// id
        int indexs = rankwheel.getIndexs(); 	// indexs
        String unqid = rankwheel.getUnqid(); 	// unqid
        int pcid = rankwheel.getPcid(); 	// pcid
        String pname = rankwheel.getPname(); 	// pname
        int wheel = rankwheel.getWheel(); 	// wheel
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
        case CEn.indexs:
            return indexs;
        case CEn.pcid:
            return pcid;
        case CEn.wheel:
            return wheel;
        }
        return 0;
    }

    public Rankwheel setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Rankwheel setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.indexs:
            return setIndexs(value2);
        case CEn.pcid:
            return setPcid(value2);
        case CEn.wheel:
            return setWheel(value2);
        }
        return this;
    }

    public Rankwheel changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Rankwheel changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.indexs:
            return changeIndexs(value2);
        case CEn.pcid:
            return changePcid(value2);
        case CEn.wheel:
            return changeWheel(value2);
        }
        return this;
    }

    public Rankwheel changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Rankwheel changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.indexs:
            return changeIndexsWithMin(value2, _min);
        case CEn.pcid:
            return changePcidWithMin(value2, _min);
        case CEn.wheel:
            return changeWheelWithMin(value2, _min);
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

    public Rankwheel setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Rankwheel setDouble(String fieldEn, double value2) {
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
        case CEn.pname: 
            return pname;
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
        case CEn.indexs:
            return indexs;
        case CEn.unqid:
            return unqid;
        case CEn.pcid:
            return pcid;
        case CEn.pname:
            return pname;
        case CEn.wheel:
            return wheel;
        }
        return null;
    }

    public Rankwheel setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Rankwheel setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Rankwheel setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Rankwheel setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.unqid:
            return setUnqid(value2);
        case CEn.pname:
            return setPname(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Rankwheel setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Rankwheel setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.gb.db.bean.Rankwheel");
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("unqid", unqid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("wheel", wheel);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("unqid", unqid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("wheel", wheel);
        return result;
    }

    public Map toOriginalMap(){
        Map result = toBasicMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Rankwheel");
        return result;
    }

    public Rankwheel mapToThis(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        String unqid = MapEx.getString(e, "unqid");
        Integer pcid = MapEx.getInt(e, "pcid");
        String pname = MapEx.getString(e, "pname");
        Integer wheel = MapEx.getInt(e, "wheel");

        if(unqid == null) unqid = "";
        if(pname == null) pname = "";

        setId(id);
        setIndexs(indexs);
        setUnqid(unqid);
        setPcid(pcid);
        setPname(pname);
        setWheel(wheel);

        return this;
    }

    public static final Rankwheel mapTo(Map e){
        Rankwheel result = new Rankwheel();
        result.mapToThis(e);
        return result;
    }

    public static final Rankwheel originalTo(Map e){
        Rankwheel result = new Rankwheel();

        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        String unqid = MapEx.getString(e, "unqid");
        Integer pcid = MapEx.getInt(e, "pcid");
        String pname = MapEx.getString(e, "pname");
        Integer wheel = MapEx.getInt(e, "wheel");

        if(unqid == null) unqid = "";
        if(pname == null) pname = "";

        result.id = id;
        result.indexs = indexs;
        result.unqid = unqid;
        result.pcid = pcid;
        result.pname = pname;
        result.wheel = wheel;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Rankwheel createFor(String text) throws Exception {
        Map map = com.bowlong.third.FastJSON.parseMap(text);
        return originalTo(map);
    }

    public byte[] toBytes() throws Exception {
        try (ByteOutStream out = getStream();) {
            writeMapTag(out, 6);
            writeMapEntry(out, "id", id);
            writeMapEntry(out, "indexs", indexs);
            writeMapEntry(out, "unqid", unqid);
            writeMapEntry(out, "pcid", pcid);
            writeMapEntry(out, "pname", pname);
            writeMapEntry(out, "wheel", wheel);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Rankwheel createFor(byte[] b) throws Exception {
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
    public static final Rankwheel loadByKey(int id) {
        return RankwheelEntity.getByKey(id);
    }

    public static final Future<Rankwheel> asyncByKey(int id) {
        return RankwheelEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Rankwheel insert() {
        Rankwheel result = RankwheelEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Rankwheel asyncInsert() {
        Rankwheel result = RankwheelEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Rankwheel insert2() {
        return RankwheelEntity.insert2(this);
    }

    public final Rankwheel asyncInsert2() {
        Rankwheel result = RankwheelEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Rankwheel update() {
        return RankwheelEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        RankwheelEntity.asyncUpdate( this );
        return true;
    }

    public final Rankwheel insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return RankwheelEntity.delete(id);
    }

    public final void asyncDelete() {
        RankwheelEntity.asyncDelete(id);
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

    public Rankwheel clone2() {
        try{
            return (Rankwheel) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
