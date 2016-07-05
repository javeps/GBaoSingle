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

//gbosng_design - rankscore
@SuppressWarnings({"rawtypes",  "unchecked", "serial" })
public class Rankscore extends com.bowlong.sql.mysql.BeanSupport {

    public static final int _CID = 408464498; // com.gb.db.bean.Rankscore

    public static String TABLENAME = "rankscore";

    public static final String primary = "id";

    public static final class Col { public static final String id = "id"; public static final String indexs = "indexs"; public static final String unqid = "unqid"; public static final String pcid = "pcid"; public static final String pname = "pname"; public static final String score = "score";  }
    public static final class CEn { public static final String id = "id"; public static final String indexs = "indexs"; public static final String unqid = "unqid"; public static final String pcid = "pcid"; public static final String pname = "pname"; public static final String score = "score";  }
    public static final String[] carrays ={"id", "indexs", "unqid", "pcid", "pname", "score"};
    public static final String[] dbTypes ={"INT", "INT", "VARCHAR", "INT", "VARCHAR", "INT"};


    public int id;
    public int indexs;
    public String unqid;
    public int pcid;
    public String pname;
    public int score;

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

    public Rankscore setId(int id){
        this.id = id;
        return this;
    }

    public int getIndexs(){
        return indexs;
    }

    public Rankscore setIndexs(int indexs){
        int _old = this.indexs;
        this.indexs = indexs;
        changeIt(Col.indexs, _old, indexs);
        return this;
    }

    public Rankscore changeIndexs(int indexs){
        return setIndexs(this.indexs + indexs);
    }

    public Rankscore changeIndexsWithMin(int indexs, int _min){
        int _v2 = this.indexs + indexs;
        return setIndexs(_v2 < _min  ? _min : _v2);
    }

    public Rankscore changeIndexsWithMax(int indexs, int _max){
        int _v2 = this.indexs + indexs;
        return setIndexs(_v2 > _max  ? _max : _v2);
    }

    public Rankscore changeIndexsWithMinMax(int indexs, int _min, int _max){
        int _v2 = this.indexs + indexs;
        _v2 = _v2 > _max  ? _max : _v2;
        return setIndexs(_v2 < _min  ? _min : _v2);
    }

    public String getUnqid(){
        return unqid;
    }

    public Rankscore setUnqid(String unqid){
        String _old = this.unqid;
        this.unqid = unqid;
        changeIt(Col.unqid, _old, unqid);
        return this;
    }

    public int getPcid(){
        return pcid;
    }

    public Rankscore setPcid(int pcid){
        int _old = this.pcid;
        this.pcid = pcid;
        changeIt(Col.pcid, _old, pcid);
        return this;
    }

    public Rankscore changePcid(int pcid){
        return setPcid(this.pcid + pcid);
    }

    public Rankscore changePcidWithMin(int pcid, int _min){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public Rankscore changePcidWithMax(int pcid, int _max){
        int _v2 = this.pcid + pcid;
        return setPcid(_v2 > _max  ? _max : _v2);
    }

    public Rankscore changePcidWithMinMax(int pcid, int _min, int _max){
        int _v2 = this.pcid + pcid;
        _v2 = _v2 > _max  ? _max : _v2;
        return setPcid(_v2 < _min  ? _min : _v2);
    }

    public String getPname(){
        return pname;
    }

    public Rankscore setPname(String pname){
        String _old = this.pname;
        this.pname = pname;
        changeIt(Col.pname, _old, pname);
        return this;
    }

    public int getScore(){
        return score;
    }

    public Rankscore setScore(int score){
        int _old = this.score;
        this.score = score;
        changeIt(Col.score, _old, score);
        return this;
    }

    public Rankscore changeScore(int score){
        return setScore(this.score + score);
    }

    public Rankscore changeScoreWithMin(int score, int _min){
        int _v2 = this.score + score;
        return setScore(_v2 < _min  ? _min : _v2);
    }

    public Rankscore changeScoreWithMax(int score, int _max){
        int _v2 = this.score + score;
        return setScore(_v2 > _max  ? _max : _v2);
    }

    public Rankscore changeScoreWithMinMax(int score, int _min, int _max){
        int _v2 = this.score + score;
        _v2 = _v2 > _max  ? _max : _v2;
        return setScore(_v2 < _min  ? _min : _v2);
    }

    public int compareTo(Rankscore v2, String fieldZh) {
        Object o1 = this.value(fieldZh);
        Object o2 = v2.value(fieldZh);
        return compareTo(o1, o2);
    }

    public int compareZhTo(Rankscore v2, String fieldZh) {
        Object o1 = this.valueZh(fieldZh);
        Object o2 = v2.valueZh(fieldZh);
        return compareTo(o1, o2);
    }

    public static Rankscore newRankscore(Integer id, Integer indexs, String unqid, Integer pcid, String pname, Integer score) {
        Rankscore result = new Rankscore();
        result.id = id;
        result.indexs = indexs;
        result.unqid = unqid;
        result.pcid = pcid;
        result.pname = pname;
        result.score = score;
        return result;
    }

    public static Rankscore newRankscore(Rankscore rankscore) {
        Rankscore result = new Rankscore();
        result.id = rankscore.id;
        result.indexs = rankscore.indexs;
        result.unqid = rankscore.unqid;
        result.pcid = rankscore.pcid;
        result.pname = rankscore.pname;
        result.score = rankscore.score;
        return result;
    }

    public Rankscore createFor(ResultSet rs) throws SQLException {
        Map e = SqlEx.toMap(rs);
        return originalTo(e);
    }

    /*
    @SuppressWarnings("unused")
    public static void getRankscore(){
        Rankscore rankscore = null; // rankscore
        { // new and insert Rankscore (rankscore)
            int id = 0; 	// id
            int indexs = 0; 	// indexs
            String unqid = ""; 	// unqid
            int pcid = 0; 	// pcid
            String pname = ""; 	// pname
            int score = 0; 	// score
            rankscore = Rankscore.newRankscore(id, indexs, unqid, pcid, pname, score);
        }
        rankscore = rankscore.insert();

        int id = rankscore.getId(); 	// id
        int indexs = rankscore.getIndexs(); 	// indexs
        String unqid = rankscore.getUnqid(); 	// unqid
        int pcid = rankscore.getPcid(); 	// pcid
        String pname = rankscore.getPname(); 	// pname
        int score = rankscore.getScore(); 	// score
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
        case CEn.score:
            return score;
        }
        return 0;
    }

    public Rankscore setZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setInt(fieldEn, value2);
    }

    public Rankscore setInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.id:
            return setId(value2);
        case CEn.indexs:
            return setIndexs(value2);
        case CEn.pcid:
            return setPcid(value2);
        case CEn.score:
            return setScore(value2);
        }
        return this;
    }

    public Rankscore changeZhInt(String fieldZh, int value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeInt(fieldEn, value2);
    }

    public Rankscore changeInt(String fieldEn, int value2) {
        switch(fieldEn){
        case CEn.indexs:
            return changeIndexs(value2);
        case CEn.pcid:
            return changePcid(value2);
        case CEn.score:
            return changeScore(value2);
        }
        return this;
    }

    public Rankscore changeZhIntWithMin(String fieldZh, int value2, int _min) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return changeIntWithMin(fieldEn, value2, _min);
    }

    public Rankscore changeIntWithMin(String fieldEn, int value2, int _min) {
        switch(fieldEn) {
        case CEn.indexs:
            return changeIndexsWithMin(value2, _min);
        case CEn.pcid:
            return changePcidWithMin(value2, _min);
        case CEn.score:
            return changeScoreWithMin(value2, _min);
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

    public Rankscore setZhDouble(String fieldZh, double value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setDouble(fieldEn, value2);
    }

    public Rankscore setDouble(String fieldEn, double value2) {
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
        case CEn.score:
            return score;
        }
        return null;
    }

    public Rankscore setZhListForInt(String fieldZh, ListForInt value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setListForInt(fieldEn, value2);
    }

    public Rankscore setListForInt(String fieldEn, ListForInt value2) {
        String str2 = value2.toString();
        return setStr(fieldEn, str2);
    }

    public Rankscore setZhStr(String fieldZh, String value2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setStr(fieldEn, value2);
    }

    public Rankscore setStr(String fieldEn, String value2) {
        switch(fieldEn) {
        case CEn.unqid:
            return setUnqid(value2);
        case CEn.pname:
            return setPname(value2);
        }
        // throw new IOException("fieldEn:" + fieldEn + " Not Found.");
        return this;
    }

    public Rankscore setZhJson(String fieldZh, Object o2) {
        String fieldEn = PinYin.getShortPinYin(fieldZh);
        return setJson(fieldEn, o2);
    }

    public Rankscore setJson(String fieldEn, Object o2) {
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
        result.put("_CLASSNAME", "com.gb.db.bean.Rankscore");
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("unqid", unqid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("score", score);
        return result;
    }

    public Map toBasicMap(){
        Map result = new HashMap();
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("unqid", unqid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("score", score);
        return result;
    }

    public Map toOriginalMap(){
        Map result = new HashMap();
        result.put("_TABLENAME", TABLENAME);
        result.put("_CLASSNAME", "com.gb.db.bean.Rankscore");
        result.put("id", id);
        result.put("indexs", indexs);
        result.put("unqid", unqid);
        result.put("pcid", pcid);
        result.put("pname", pname);
        result.put("score", score);
        return result;
    }

    public Rankscore mapToObject(Map e){
        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        String unqid = MapEx.getString(e, "unqid");
        Integer pcid = MapEx.getInt(e, "pcid");
        String pname = MapEx.getString(e, "pname");
        Integer score = MapEx.getInt(e, "score");

        if(id == null) id = 0;
        if(indexs == null) indexs = 0;
        if(unqid == null) unqid = "";
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(score == null) score = 0;

        setId(id);
        setIndexs(indexs);
        setUnqid(unqid);
        setPcid(pcid);
        setPname(pname);
        setScore(score);

        return this;
    }

    public static final Rankscore mapTo(Map e){
        Rankscore result = new Rankscore();

        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        String unqid = MapEx.getString(e, "unqid");
        Integer pcid = MapEx.getInt(e, "pcid");
        String pname = MapEx.getString(e, "pname");
        Integer score = MapEx.getInt(e, "score");

        if(id == null) id = 0;
        if(indexs == null) indexs = 0;
        if(unqid == null) unqid = "";
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(score == null) score = 0;

        result.id = id;
        result.indexs = indexs;
        result.unqid = unqid;
        result.pcid = pcid;
        result.pname = pname;
        result.score = score;

        return result;
    }

    public static final Rankscore originalTo(Map e){
        Rankscore result = new Rankscore();

        Integer id = MapEx.getInt(e, "id");
        Integer indexs = MapEx.getInt(e, "indexs");
        String unqid = MapEx.getString(e, "unqid");
        Integer pcid = MapEx.getInt(e, "pcid");
        String pname = MapEx.getString(e, "pname");
        Integer score = MapEx.getInt(e, "score");

        if(id == null) id = 0;
        if(indexs == null) indexs = 0;
        if(unqid == null) unqid = "";
        if(pcid == null) pcid = 0;
        if(pname == null) pname = "";
        if(score == null) score = 0;

        result.id = id;
        result.indexs = indexs;
        result.unqid = unqid;
        result.pcid = pcid;
        result.pname = pname;
        result.score = score;

        return result;
    }

    public String toJson() throws Exception {
        return toJson(false);
    }

    public String toJson(boolean prettyFormat) throws Exception {
        return com.bowlong.third.FastJSON.toJSONString(toOriginalMap(), prettyFormat);
    }

    public static final Rankscore createFor(String text) throws Exception {
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
            writeMapEntry(out, "score", score);
            return out.toByteArray();
        } catch (Exception e) {
            throw e;
        }
    }

    public static final Rankscore createFor(byte[] b) throws Exception {
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
    public static final Rankscore loadByKey(int id) {
        return RankscoreEntity.getByKey(id);
    }

    public static final Future<Rankscore> asyncByKey(int id) {
        return RankscoreEntity.asyncGetByKey(id);
    }

    public void forced() {
        for (String str : carrays) {
            if(str.equals(primary))
                continue;
            changeIt(str, value(str));
        }
    }

    public final Rankscore insert() {
        Rankscore result = RankscoreEntity.insert(this);
        if(result == null) return null;
        // id = result.id;
        return result;
    }

    public final Rankscore asyncInsert() {
        Rankscore result = RankscoreEntity.asyncInsert(this);
        // id = result.id;
        return result;
    }

    public final Rankscore insert2() {
        return RankscoreEntity.insert2(this);
    }

    public final Rankscore asyncInsert2() {
        Rankscore result = RankscoreEntity.asyncInsert2(this);
        // id = result.id;
        return result;
    }

    public final Rankscore update() {
        return RankscoreEntity.update(this);
    }

    public boolean asyncUpdate() {
        if(id <= 0) return false;
        RankscoreEntity.asyncUpdate( this );
        return true;
    }

    public final Rankscore insertOrUpdate() {
        if(id <= 0)
            return insert();
        else
            return update();
    }

    public final int delete() {
        return RankscoreEntity.delete(id);
    }

    public final void asyncDelete() {
        RankscoreEntity.asyncDelete(id);
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

    public Rankscore clone2() {
        try{
            return (Rankscore) clone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
