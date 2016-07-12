package gen_b2g.web_disp.bean;

import java.io.*;
import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings("all")
public class NBool extends com.bowlong.net.proto.NSupport {
    public static final int _CID = 74111064;

    public boolean val;

    public void setNBool(boolean val){
        this.val = val;
    }
    public static NBool newNBool(boolean val){
        NBool r = new NBool();
        r.val = val;
        return r;
    }

    public Map toMap() {
        Map r = new HashMap();
        r.put("val", val);
        return r;
    }


    public static NBool parse(NewMap map2) {
        if(map2 == null) return null;

        NBool r = new NBool();
        r.val = map2.getBoolean("val");
        return r;
    }

    public String toString() {
        return "NBool[val=" + val + "]";
    }

    public static NBool parse(byte[] buf) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2Helper.toMap(buf);
        return parse(map2);
    }

    public static NBool parse(InputStream in) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2InputStream.readMap(in);
        return parse(map2);
    }

    public byte[] toByteArray() throws Exception {
        return com.bowlong.bio2.B2Helper.toBytes(toMap());
    }

}
