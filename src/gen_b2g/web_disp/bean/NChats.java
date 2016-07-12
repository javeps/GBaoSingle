package gen_b2g.web_disp.bean;

import java.io.*;
import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings("all")
public class NChats extends com.bowlong.net.proto.NSupport {
    public static final int _CID = -1996822419;

    public List<NChat> list = new NewList();

    public void setNChats(List list){
        this.list = list;
    }
    public static NChats newNChats(List list){
        NChats r = new NChats();
        r.list = list;
        return r;
    }
    public List<Map> list_maps() {
        List<Map> r = new NewList<Map>();
        if(list == null) return r;
        for(NChat _e : list) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NChat> maps_list(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NChat e = NChat.parse(_e);
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }


    public Map toMap() {
        Map r = new HashMap();
        r.put("list", list_maps());
        return r;
    }


    public static NChats parse(NewMap map2) {
        if(map2 == null) return null;

        NChats r = new NChats();
        r.list = maps_list( map2.getList("list") );
        return r;
    }

    public String toString() {
        return "NChats[list=" + list + "]";
    }

    public static NChats parse(byte[] buf) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2Helper.toMap(buf);
        return parse(map2);
    }

    public static NChats parse(InputStream in) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2InputStream.readMap(in);
        return parse(map2);
    }

    public byte[] toByteArray() throws Exception {
        return com.bowlong.bio2.B2Helper.toBytes(toMap());
    }

}
