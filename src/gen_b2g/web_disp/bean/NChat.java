package gen_b2g.web_disp.bean;

import java.io.*;
import java.util.*;

import com.bowlong.util.*;
import com.bowlong.net.proto.gen.lua.LuaEncode;

@SuppressWarnings("all")
public class NChat extends com.bowlong.net.proto.NSupport {
    public static final int _CID = 74133702;

    public int type;// 聊天类型
    public int fpcid;// 说话人标识
    public String fpname = "";// 说话人名称
    public String content = "";// 说话内容
    public long creattime;// 创建时间long
    public String creattimeStr = "";// 创建时间Str

    public void setNChat(int type,int fpcid,String fpname,String content,long creattime,String creattimeStr){
        this.type = type;
        this.fpcid = fpcid;
        this.fpname = fpname;
        this.content = content;
        this.creattime = creattime;
        this.creattimeStr = creattimeStr;
    }
    public static NChat newNChat(int type,int fpcid,String fpname,String content,long creattime,String creattimeStr){
        NChat r = new NChat();
        r.type = type;
        r.fpcid = fpcid;
        r.fpname = fpname;
        r.content = content;
        r.creattime = creattime;
        r.creattimeStr = creattimeStr;
        return r;
    }

    public Map toMap() {
        Map r = new HashMap();
        r.put("type", LuaEncode.encode(type));
        r.put("fpcid", LuaEncode.encode(fpcid));
        r.put("fpname", fpname);
        r.put("content", content);
        r.put("creattime", LuaEncode.encode(creattime));
        r.put("creattimeStr", creattimeStr);
        return r;
    }


    public static NChat parse(NewMap map2) {
        if(map2 == null) return null;

        NChat r = new NChat();
        try {
        r.type = LuaEncode.decode(map2.get("type"),Integer.class);
        r.fpcid = LuaEncode.decode(map2.get("fpcid"),Integer.class);
        r.fpname = map2.getString("fpname");
        r.content = map2.getString("content");
        r.creattime = LuaEncode.decode(map2.get("creattime"),Long.class);
        r.creattimeStr = map2.getString("creattimeStr");
        } catch (Exception e) {
        }
        return r;
    }

    public String toString() {
        return "NChat[type=" + type + ", fpcid=" + fpcid + ", fpname=" + fpname + ", content=" + content + ", creattime=" + creattime + ", creattimeStr=" + creattimeStr + "]";
    }

    public static NChat parse(byte[] buf) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2Helper.toMap(buf);
        return parse(map2);
    }

    public static NChat parse(InputStream in) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2InputStream.readMap(in);
        return parse(map2);
    }

    public byte[] toByteArray() throws Exception {
        return com.bowlong.bio2.B2Helper.toBytes(toMap());
    }

}
