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

//gbosng_design - rankstars
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Rankstars extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 408957505; // com.gb.db.bean.Rankstars

    public static String TABLENAME = "rankstars";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String indexs = "indexs"; public static final String unqid = "unqid"; public static final String pcid = "pcid"; public static final String pname = "pname"; public static final String stars = "stars";  }
    public static final class CEn { public static final String id = "id"; public static final String indexs = "indexs"; public static final String unqid = "unqid"; public static final String pcid = "pcid"; public static final String pname = "pname"; public static final String stars = "stars";  }
    public static final String[] carrays ={"id", "indexs", "unqid", "pcid", "pname", "stars"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "INT", "VARCHAR", "INT"};


    public int id;
    public int indexs;
    public String unqid;
    public int pcid;
    public String pname;
    public int stars;

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

    public Rankstars setId(int id){
        this.id = id;
        return this;
    }

    public int getIndexs(){
        return indexs;
    }

    public Rankstars setIndexs(int indexs){
        int _old = this.indexs;
        this.indexs = indexs;
        changeIt(Col.indexs, _old, indexs);
        return this;
    }

    public Rankstars changeIndexs(int indexs){
        return setIndexs(this.indexs + indexs);
    }

    public Rankstars changeIndexsWithMin(int indexs, int _min){
        int _v2 = this.indexs + indexs;
        return setIndexs(_v2 < _min  ? _min : _v2);
    }

    public Rankstars changeIndexsWithMax(int indexs, int _max){
        int _v2 = this.indexs + indexs;
        return setIndexs(_v2 > _max  ? _max : _v2);
    }

    public Rankstars changeIndexsWithMinMax(int indexs, int _min, int _max){
        int _v2 = this.indexs + indexs;
        _v2 = _v2 > _max  ? _max : _v2;
        return setIndexs(_v2 < _min  ? _min : _v2);
    }

    public String getUnqid(){
        return unqid;
    }

    public Rankstars setUnqid(String unqid){
        String _old = this.unqid;
        this.unqid = unqid;
        changeIt(Col.unqid, _old, unqid);
        return this;
    }

    public int getPcid(){
        return pcid;
    }

    public Rankstars setPcid(int pcid){
        int _old = this.pcid;
        this.pcid = pcid;
        changeIt(Col.pcid, _old, pcid);
        return this;
    }

    public Rankstars changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Rankstars changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Rankstars changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Rankstars changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPname(){
        return pname;
    }

    public Rankstars setPname(String pname){
        String _old = this.pname;
        this.pname = pname;
        changeIt(Col.pname, _old, pname);
        return this;
    }

    public int getStars(){
        return stars;
    }

    public Rankstars setStars(int stars){
        int _old = this.stars;
        this.stars = stars;
        changeIt(Col.stars, _old, stars);
        return this;
    }

    public Rankstars changeStars(int stars){
        return setStars(this.stars + stars);
    }

    public Rankstars changeStarsWithMin(int stars, int _min){
        int _v2 = this.stars + stars;
        return setStars(_v2 < _min  ? _min : _v2);
    }

    public Rankstars changeStarsWithMax(int stars, int _max){
        int _v2 = this.stars + stars;
        return setStars(_v2 > _max  ? _max : _v2);
    }

    public Rankstars changeStarsWithMinMax(int stars, int _min, int _max){
        int _v2 = this.stars + stars;
        _v2 = _v2 > _max  ? _max : _v2;
        return setStars(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Rankstars v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Rankstars v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Rankstars newRankstars(Integer id, Integer indexs, String unqid, Integer pcid, String pname, Integer stars) {
        Rankstars result = new Rankstars();
        result.id = id;
        result.indexs = indexs;
        result.unqid = unqid;
        result.pcid = pcid;
        result.pname = pname;
        result.stars = stars;
        return result;
    }

    public static Rankstars newRankstars(Rankstars rankstars) {
        Rankstars result = new Rankstars();
        result.id = rankstars.id;
        result.indexs = rankstars.indexs;
        result.unqid = rankstars.unqid;
        result.pcid = rankstars.pcid;
        result.pname = rankstars.pname;
        result.stars = rankstars.stars;
        return result;
    }

    public Rankstars createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRankstars(){
        Rankstars rankstars = null; // rankstars
        { // new and insert Rankstars (rankstars)
            int id = 0; 	// id
            int indexs = 0; 	// indexs
            String unqid = ""; 	// unqid
            int pcid = 0; 	// pcid
            String pname = ""; 	// pname
            int stars = 0; 	// stars
            rankstars = Rankstars.newRankstars(id, indexs, unqid, pcid, pname, stars);
        }
        rankstars = rankstars.insert();

        int id = rankstars.getId(); 	// id
        int indexs = rankstars.getIndexs(); 	// indexs
        String unqid = rankstars.getUnqid(); 	// unqid
        int pcid = rankstars.getPcid(); 	// pcid
        String pname = rankstars.getPname(); 	// pname
        int stars = rankstars.getStars(); 	// stars
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
        case CEn.stars:
            return stars;
        }
        return 0;
    }

    public Rankstars setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Rankstars setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.indexs:
            return setIndexs(value2);
        case CEn.pcid:
            return setPcid(value2);
        case CEn.stars:
            return setStars(value2);
        }
        return this;
    }

    public Rankstars changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Rankstars changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.indexs:
            return changeIndexs(value2);
        case CEn.pcid:
            return changePcid(value2);
        case CEn.stars:
            return changeStars(value2);
        }
        return this;
    }

    public Rankstars changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Rankstars changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.indexs:
            return changeIndexsWithMin(value2, _min);
        case CEn.pcid:
            return changePcidWithMin(value2, _min);
        case CEn.stars:
            return changeStarsWithMin(value2, _min);
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

    public Rankstars setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Rankstars setDouble(String fieldEn, double value2) {
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
        case CEn.stars:
            return stars;
        }
        return null;
    }

    public Rankstars setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Rankstars setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Rankstars setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Rankstars setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.unqid:
            return setUnqid(value2);
        case CEn.pname:
            return setPname(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Rankstars setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Rankstars setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.gb.db.bean.Rankstars");
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("unqid", unqid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("stars", stars);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("unqid", unqid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("stars", stars);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Rankstars");
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("unqid", unqid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("stars", stars);
        return result;
    }

    public Rankstars mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        String unqid = MapEx.getString(e, "unqid");
        Integer pcid = MapEx.getInt(e, "pcid");
        String pname = MapEx.getString(e, "pname");
        Integer stars = MapEx.getInt(e, "stars");

        if(id == null) id = 0;
        if(indexs == null) indexs = 0;
        if(unqid == null) unqid = "";
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(stars == null) stars = 0;

        setId(id);
        setIndexs(indexs);
        setUnqid(unqid);
        setPcid(pcid);
        setPname(pname);
        setStars(stars);

        return this;
    }

    public static final Rankstars mapTo(Map e){
        Rankstars result = new Rankstars();

        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        String unqid = MapEx.getString(e, "unqid");
        Integer pcid = MapEx.getInt(e, "pcid");
        String pname = MapEx.getString(e, "pname");
        Integer stars = MapEx.getInt(e, "stars");

        if(id == null) id = 0;
        if(indexs == null) indexs = 0;
        if(unqid == null) unqid = "";
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(stars == null) stars = 0;

        result.id = id;
        result.indexs = indexs;
        result.unqid = unqid;
        result.pcid = pcid;
        result.pname = pname;
        result.stars = stars;

        return result;
    }

    public static final Rankstars originalTo(Map e){
        Rankstars result = new Rankstars();

        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        String unqid = MapEx.getString(e, "unqid");
        Integer pcid = MapEx.getInt(e, "pcid");
        String pname = MapEx.getString(e, "pname");
        Integer stars = MapEx.getInt(e, "stars");

        if(id == null) id = 0;
        if(indexs == null) indexs = 0;
        if(unqid == null) unqid = "";
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(stars == null) stars = 0;

        result.id = id;
        result.indexs = indexs;
        result.unqid = unqid;
        result.pcid = pcid;
        result.pname = pname;
        result.stars = stars;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Rankstars createFor(String text) throws Exception {
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
            writeMapEntry(out, "stars", stars);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Rankstars createFor(byte[] b) throws Exception {
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
    public static final Rankstars loadByKey(int id) {
        return RankstarsEntity.getByKey(id);
    }

    public static final Future<Rankstars> asyncByKey(int id) {
        return RankstarsEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Rankstars insert() {
        Rankstars result = RankstarsEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Rankstars asyncInsert() {
        Rankstars result = RankstarsEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Rankstars insert2() {
        return RankstarsEntity.insert2(this);
    }

    public final Rankstars asyncInsert2() {
        Rankstars result = RankstarsEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Rankstars update() {
        return RankstarsEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        RankstarsEntity.asyncUpdate( this );
        return true;
    }

    public final Rankstars insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return RankstarsEntity.delete(id);
    }

    public final void asyncDelete() {
        RankstarsEntity.asyncDelete(id);
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

    public Rankstars clone2() {
        try{
            return (Rankstars) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
