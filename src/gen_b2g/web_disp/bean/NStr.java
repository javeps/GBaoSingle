package gen_b2g.web_disp.bean;

import java.io.*;
import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings("all")
public class NStr extends com.bowlong.net.proto.NSupport {
    public static final int _CID = 2407171;

    public String val = "";

    public void setNStr(String val){
        this.val = val;
    }
    public static NStr newNStr(String val){
        NStr r = new NStr();
        r.val = val;
        return r;
    }

    public Map toMap() {
        Map r = new HashMap();
        r.put("val", val);
        return r;
    }


    public static NStr parse(NewMap map2) {
        if(map2 == null) return null;

        NStr r = new NStr();
        r.val = map2.getString("val");
        return r;
    }

    public String toString() {
        return "NStr[val=" + val + "]";
    }

    public static NStr parse(byte[] buf) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2Helper.toMap(buf);
        return parse(map2);
    }

    public static NStr parse(InputStream in) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2InputStream.readMap(in);
        return parse(map2);
    }

    public byte[] toByteArray() throws Exception {
        return com.bowlong.bio2.B2Helper.toBytes(toMap());
    }

}
