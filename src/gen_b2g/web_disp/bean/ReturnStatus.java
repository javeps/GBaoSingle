package gen_b2g.web_disp.bean;

import java.io.*;
import java.util.*;

import com.bowlong.util.*;
import com.bowlong.net.proto.gen.Bio2GJava4LuaEncode;

@SuppressWarnings("all")
public class ReturnStatus extends com.bowlong.net.proto.NSupport {
    public static final int _CID = 991275362;

    public int succ; 
    public String msg = ""; 

    public void setReturnStatus(int succ,String msg){
        this.succ = succ;
        this.msg = msg;
    }
    public static ReturnStatus newReturnStatus(int succ,String msg){
        ReturnStatus r = new ReturnStatus();
        r.succ = succ;
        r.msg = msg;
        return r;
    }

    public Map toMap() {
        Map r = new HashMap();
        r.put("-1", _CID);
        r.put("succ", Bio2GJava4LuaEncode.encode(succ));
        r.put("msg", msg);
        return r;
    }


    public static ReturnStatus parse(NewMap map2) {
        if(map2 == null) return null;

        ReturnStatus r = new ReturnStatus();
        try {
        r.succ = Bio2GJava4LuaEncode.decode(map2.get("succ"),Integer.class);
        r.msg = map2.getString("msg");
        } catch (Exception e) {
        }
        return r;
    }

    public String toString() {
        return "ReturnStatus[succ=" + succ + ", msg=" + msg + "]";
    }

    public static ReturnStatus parse(byte[] buf) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2Helper.toMap(buf);
        return parse(map2);
    }

    public static ReturnStatus parse(InputStream in) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2InputStream.readMap(in);
        return parse(map2);
    }

    public byte[] toByteArray() throws Exception {
        return com.bowlong.bio2.B2Helper.toBytes(toMap());
    }

}
