package gen_b2g.web_disp.bean;

import java.io.*;
import java.util.*;

import com.bowlong.util.*;
import com.bowlong.net.proto.gen.Bio2GJava4LuaEncode;

@SuppressWarnings("all")
public class NRank extends com.bowlong.net.proto.NSupport {
    public static final int _CID = 74574234;

    public int index; // 排名
    public int type; // 1sword,2wheel
    public String unqid = ""; // 登录唯一标识
    public String pname = ""; // 角色名
    public int sword; // 战斗力
    public int wheel; // 无尽循环最大次数

    public void setNRank(int index,int type,String unqid,String pname,int sword,int wheel){
        this.index = index;
        this.type = type;
        this.unqid = unqid;
        this.pname = pname;
        this.sword = sword;
        this.wheel = wheel;
    }
    public static NRank newNRank(int index,int type,String unqid,String pname,int sword,int wheel){
        NRank r = new NRank();
        r.index = index;
        r.type = type;
        r.unqid = unqid;
        r.pname = pname;
        r.sword = sword;
        r.wheel = wheel;
        return r;
    }

    public Map toMap() {
        Map r = new HashMap();
        r.put("-1", _CID);
        r.put("index", Bio2GJava4LuaEncode.encode(index));
        r.put("type", Bio2GJava4LuaEncode.encode(type));
        r.put("unqid", unqid);
        r.put("pname", pname);
        r.put("sword", Bio2GJava4LuaEncode.encode(sword));
        r.put("wheel", Bio2GJava4LuaEncode.encode(wheel));
        return r;
    }


    public static NRank parse(NewMap map2) {
        if(map2 == null) return null;

        NRank r = new NRank();
        try {
        r.index = Bio2GJava4LuaEncode.decode(map2.get("index"),Integer.class);
        r.type = Bio2GJava4LuaEncode.decode(map2.get("type"),Integer.class);
        r.unqid = map2.getString("unqid");
        r.pname = map2.getString("pname");
        r.sword = Bio2GJava4LuaEncode.decode(map2.get("sword"),Integer.class);
        r.wheel = Bio2GJava4LuaEncode.decode(map2.get("wheel"),Integer.class);
        } catch (Exception e) {
        }
        return r;
    }

    public String toString() {
        return "NRank[index=" + index + ", type=" + type + ", unqid=" + unqid + ", pname=" + pname + ", sword=" + sword + ", wheel=" + wheel + "]";
    }

    public static NRank parse(byte[] buf) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2Helper.toMap(buf);
        return parse(map2);
    }

    public static NRank parse(InputStream in) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2InputStream.readMap(in);
        return parse(map2);
    }

    public byte[] toByteArray() throws Exception {
        return com.bowlong.bio2.B2Helper.toBytes(toMap());
    }

}
