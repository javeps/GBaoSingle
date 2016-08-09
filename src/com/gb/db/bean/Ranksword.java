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

//gbosng_design - ranksword
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Ranksword extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 409060317; // com.gb.db.bean.Ranksword

    public static String TABLENAME = "ranksword";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String indexs = "indexs"; public static final String unqid = "unqid"; public static final String pcid = "pcid"; public static final String pname = "pname"; public static final String sword = "sword";  }
    public static final class CEn { public static final String id = "id"; public static final String indexs = "indexs"; public static final String unqid = "unqid"; public static final String pcid = "pcid"; public static final String pname = "pname"; public static final String sword = "sword";  }
    public static final String[] carrays ={"id", "indexs", "unqid", "pcid", "pname", "sword"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "INT", "VARCHAR", "INT"};


    public int id;
    public int indexs;
    public String unqid;
    public int pcid;
    public String pname;
    public int sword;

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

    public Ranksword setId(int id){
        this.id = id;
        return this;
    }

    public int getIndexs(){
        return indexs;
    }

    public Ranksword setIndexs(int indexs){
        int _old = this.indexs;
        this.indexs = indexs;
        changeIt(Col.indexs, _old, indexs);
        return this;
    }

    public Ranksword changeIndexs(int indexs){
        return setIndexs(this.indexs + indexs);
    }

    public Ranksword changeIndexsWithMin(int indexs, int _min){
        int _v2 = this.indexs + indexs;
        return setIndexs(_v2 < _min  ? _min : _v2);
    }

    public Ranksword changeIndexsWithMax(int indexs, int _max){
        int _v2 = this.indexs + indexs;
        return setIndexs(_v2 > _max  ? _max : _v2);
    }

    public Ranksword changeIndexsWithMinMax(int indexs, int _min, int _max){
        int _v2 = this.indexs + indexs;
        _v2 = _v2 > _max  ? _max : _v2;
        return setIndexs(_v2 < _min  ? _min : _v2);
    }

    public String getUnqid(){
        return unqid;
    }

    public Ranksword setUnqid(String unqid){
        String _old = this.unqid;
        this.unqid = unqid;
        changeIt(Col.unqid, _old, unqid);
        return this;
    }

    public int getPcid(){
        return pcid;
    }

    public Ranksword setPcid(int pcid){
        int _old = this.pcid;
        this.pcid = pcid;
        changeIt(Col.pcid, _old, pcid);
        return this;
    }

    public Ranksword changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Ranksword changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Ranksword changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Ranksword changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPname(){
        return pname;
    }

    public Ranksword setPname(String pname){
        String _old = this.pname;
        this.pname = pname;
        changeIt(Col.pname, _old, pname);
        return this;
    }

    public int getSword(){
        return sword;
    }

    public Ranksword setSword(int sword){
        int _old = this.sword;
        this.sword = sword;
        changeIt(Col.sword, _old, sword);
        return this;
    }

    public Ranksword changeSword(int sword){
        return setSword(this.sword + sword);
    }

    public Ranksword changeSwordWithMin(int sword, int _min){
        int _v2 = this.sword + sword;
        return setSword(_v2 < _min  ? _min : _v2);
    }

    public Ranksword changeSwordWithMax(int sword, int _max){
        int _v2 = this.sword + sword;
        return setSword(_v2 > _max  ? _max : _v2);
    }

    public Ranksword changeSwordWithMinMax(int sword, int _min, int _max){
        int _v2 = this.sword + sword;
        _v2 = _v2 > _max  ? _max : _v2;
        return setSword(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Ranksword v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Ranksword v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Ranksword newRanksword(Integer id, Integer indexs, String unqid, Integer pcid, String pname, Integer sword) {
        Ranksword result = new Ranksword();
        result.id = id;
        result.indexs = indexs;
        result.unqid = unqid;
        result.pcid = pcid;
        result.pname = pname;
        result.sword = sword;
        return result;
    }

    public static Ranksword newRanksword(Ranksword ranksword) {
        Ranksword result = new Ranksword();
        result.id = ranksword.id;
        result.indexs = ranksword.indexs;
        result.unqid = ranksword.unqid;
        result.pcid = ranksword.pcid;
        result.pname = ranksword.pname;
        result.sword = ranksword.sword;
        return result;
    }

    public Ranksword createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRanksword(){
        Ranksword ranksword = null; // ranksword
        { // new and insert Ranksword (ranksword)
            int id = 0; 	// id
            int indexs = 0; 	// indexs
            String unqid = ""; 	// unqid
            int pcid = 0; 	// pcid
            String pname = ""; 	// pname
            int sword = 0; 	// sword
            ranksword = Ranksword.newRanksword(id, indexs, unqid, pcid, pname, sword);
        }
        ranksword = ranksword.insert();

        int id = ranksword.getId(); 	// id
        int indexs = ranksword.getIndexs(); 	// indexs
        String unqid = ranksword.getUnqid(); 	// unqid
        int pcid = ranksword.getPcid(); 	// pcid
        String pname = ranksword.getPname(); 	// pname
        int sword = ranksword.getSword(); 	// sword
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
        case CEn.sword:
            return sword;
        }
        return 0;
    }

    public Ranksword setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Ranksword setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.indexs:
            return setIndexs(value2);
        case CEn.pcid:
            return setPcid(value2);
        case CEn.sword:
            return setSword(value2);
        }
        return this;
    }

    public Ranksword changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Ranksword changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.indexs:
            return changeIndexs(value2);
        case CEn.pcid:
            return changePcid(value2);
        case CEn.sword:
            return changeSword(value2);
        }
        return this;
    }

    public Ranksword changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Ranksword changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.indexs:
            return changeIndexsWithMin(value2, _min);
        case CEn.pcid:
            return changePcidWithMin(value2, _min);
        case CEn.sword:
            return changeSwordWithMin(value2, _min);
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

    public Ranksword setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Ranksword setDouble(String fieldEn, double value2) {
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
        case CEn.sword:
            return sword;
        }
        return null;
    }

    public Ranksword setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Ranksword setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Ranksword setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Ranksword setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.unqid:
            return setUnqid(value2);
        case CEn.pname:
            return setPname(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Ranksword setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Ranksword setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.gb.db.bean.Ranksword");
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("unqid", unqid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("sword", sword);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("unqid", unqid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("sword", sword);
        return result;
    }

    public Map toOriginalMap(){
        Map result = toBasicMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Ranksword");
        return result;
    }

    public Ranksword mapToThis(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        String unqid = MapEx.getString(e, "unqid");
        Integer pcid = MapEx.getInt(e, "pcid");
        String pname = MapEx.getString(e, "pname");
        Integer sword = MapEx.getInt(e, "sword");

        if(unqid == null) unqid = "";
        if(pname == null) pname = "";

        setId(id);
        setIndexs(indexs);
        setUnqid(unqid);
        setPcid(pcid);
        setPname(pname);
        setSword(sword);

        return this;
    }

    public static final Ranksword mapTo(Map e){
        Ranksword result = new Ranksword();
        result.mapToThis(e);
        return result;
    }

    public static final Ranksword originalTo(Map e){
        Ranksword result = new Ranksword();

        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        String unqid = MapEx.getString(e, "unqid");
        Integer pcid = MapEx.getInt(e, "pcid");
        String pname = MapEx.getString(e, "pname");
        Integer sword = MapEx.getInt(e, "sword");

        if(unqid == null) unqid = "";
        if(pname == null) pname = "";

        result.id = id;
        result.indexs = indexs;
        result.unqid = unqid;
        result.pcid = pcid;
        result.pname = pname;
        result.sword = sword;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Ranksword createFor(String text) throws Exception {
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
            writeMapEntry(out, "sword", sword);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Ranksword createFor(byte[] b) throws Exception {
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
    public static final Ranksword loadByKey(int id) {
        return RankswordEntity.getByKey(id);
    }

    public static final Future<Ranksword> asyncByKey(int id) {
        return RankswordEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Ranksword insert() {
        Ranksword result = RankswordEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Ranksword asyncInsert() {
        Ranksword result = RankswordEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Ranksword insert2() {
        return RankswordEntity.insert2(this);
    }

    public final Ranksword asyncInsert2() {
        Ranksword result = RankswordEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Ranksword update() {
        return RankswordEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        RankswordEntity.asyncUpdate( this );
        return true;
    }

    public final Ranksword insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return RankswordEntity.delete(id);
    }

    public final void asyncDelete() {
        RankswordEntity.asyncDelete(id);
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

    public Ranksword clone2() {
        try{
            return (Ranksword) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
