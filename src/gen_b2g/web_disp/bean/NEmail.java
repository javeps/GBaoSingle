package gen_b2g.web_disp.bean;

import java.io.*;
import java.util.*;

import com.bowlong.util.*;
import com.bowlong.net.proto.gen.Bio2GJava4LuaEncode;

@SuppressWarnings("all")
public class NEmail extends com.bowlong.net.proto.NSupport {
    public static final int _CID = -1994826770;

    public String title = ""; // 标题
    public String cont = ""; // 内容
    public boolean isRead; // 是否已读
    public boolean isReceive; // 是否已经领取奖励
    public String awardJson = ""; // 奖励[{tpGet,tpId,tpVal}]
    public long creattime; // 创建时间long
    public long validtime; // 有效时间long

    public void setNEmail(String title,String cont,boolean isRead,boolean isReceive,String awardJson,long creattime,long validtime){
        this.title = title;
        this.cont = cont;
        this.isRead = isRead;
        this.isReceive = isReceive;
        this.awardJson = awardJson;
        this.creattime = creattime;
        this.validtime = validtime;
    }
    public static NEmail newNEmail(String title,String cont,boolean isRead,boolean isReceive,String awardJson,long creattime,long validtime){
        NEmail r = new NEmail();
        r.title = title;
        r.cont = cont;
        r.isRead = isRead;
        r.isReceive = isReceive;
        r.awardJson = awardJson;
        r.creattime = creattime;
        r.validtime = validtime;
        return r;
    }

    public Map toMap() {
        Map r = new HashMap();
        r.put("-1", _CID);
        r.put("title", title);
        r.put("cont", cont);
        r.put("isRead", isRead);
        r.put("isReceive", isReceive);
        r.put("awardJson", awardJson);
        r.put("creattime", Bio2GJava4LuaEncode.encode(creattime));
        r.put("validtime", Bio2GJava4LuaEncode.encode(validtime));
        return r;
    }


    public static NEmail parse(NewMap map2) {
        if(map2 == null) return null;

        NEmail r = new NEmail();
        try {
        r.title = map2.getString("title");
        r.cont = map2.getString("cont");
        r.isRead = map2.getBoolean("isRead");
        r.isReceive = map2.getBoolean("isReceive");
        r.awardJson = map2.getString("awardJson");
        r.creattime = Bio2GJava4LuaEncode.decode(map2.get("creattime"),Long.class);
        r.validtime = Bio2GJava4LuaEncode.decode(map2.get("validtime"),Long.class);
        } catch (Exception e) {
        }
        return r;
    }

    public String toString() {
        return "NEmail[title=" + title + ", cont=" + cont + ", isRead=" + isRead + ", isReceive=" + isReceive + ", awardJson=" + awardJson + ", creattime=" + creattime + ", validtime=" + validtime + "]";
    }

    public static NEmail parse(byte[] buf) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2Helper.toMap(buf);
        return parse(map2);
    }

    public static NEmail parse(InputStream in) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2InputStream.readMap(in);
        return parse(map2);
    }

    public byte[] toByteArray() throws Exception {
        return com.bowlong.bio2.B2Helper.toBytes(toMap());
    }

}
