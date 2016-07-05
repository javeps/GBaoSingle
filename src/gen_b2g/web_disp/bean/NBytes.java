package gen_b2g.web_disp.bean;

import java.io.*;
import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings("all")
public class NBytes extends com.bowlong.net.proto.NSupport {
    public static final int _CID = -1997221699;

    public byte[] buff; 

    public void setNBytes(byte[] buff){
        this.buff = buff;
    }
    public static NBytes newNBytes(byte[] buff){
        NBytes r = new NBytes();
        r.buff = buff;
        return r;
    }

    public Map toMap() {
        Map r = new HashMap();
        r.put("-1", _CID);
        r.put("buff", buff);
        return r;
    }


    public static NBytes parse(NewMap map2) {
        if(map2 == null) return null;

        NBytes r = new NBytes();
        r.buff = map2.getBytes("buff");
        return r;
    }

    public String toString() {
        return "NBytes[buff=" + buff + "]";
    }

    public static NBytes parse(byte[] buf) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2Helper.toMap(buf);
        return parse(map2);
    }

    public static NBytes parse(InputStream in) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2InputStream.readMap(in);
        return parse(map2);
    }

    public byte[] toByteArray() throws Exception {
        return com.bowlong.bio2.B2Helper.toBytes(toMap());
    }

}
