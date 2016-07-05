package gen_b2g.web_disp.bean;

import java.io.*;
import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings("all")
public class NEmails extends com.bowlong.net.proto.NSupport {
    public static final int _CID = -1710087611;

    public List<NEmail> list = new NewList(); 

    public void setNEmails(List list){
        this.list = list;
    }
    public static NEmails newNEmails(List list){
        NEmails r = new NEmails();
        r.list = list;
        return r;
    }
    public List<Map> list_maps() {
        List<Map> r = new NewList<Map>();
        if(list == null) return r;
        for(NEmail _e : list) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NEmail> maps_list(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NEmail e = NEmail.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new HashMap();
        r.put("-1", _CID);
        r.put("list", list_maps());
        return r;
    }


    public static NEmails parse(NewMap map2) {
        if(map2 == null) return null;

        NEmails r = new NEmails();
        r.list = maps_list( map2.getList("list") );
        return r;
    }

    public String toString() {
        return "NEmails[list=" + list + "]";
    }

    public static NEmails parse(byte[] buf) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2Helper.toMap(buf);
        return parse(map2);
    }

    public static NEmails parse(InputStream in) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2InputStream.readMap(in);
        return parse(map2);
    }

    public byte[] toByteArray() throws Exception {
        return com.bowlong.bio2.B2Helper.toBytes(toMap());
    }

}
