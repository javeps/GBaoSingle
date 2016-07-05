package gen_b2g.web_disp.bean;

import java.io.*;
import java.util.*;

import com.bowlong.util.*;

@SuppressWarnings("all")
public class NRanks extends com.bowlong.net.proto.NSupport {
    public static final int _CID = -1983165927;

    public List<NRank> list = new NewList(); 

    public void setNRanks(List list){
        this.list = list;
    }
    public static NRanks newNRanks(List list){
        NRanks r = new NRanks();
        r.list = list;
        return r;
    }
    public List<Map> list_maps() {
        List<Map> r = new NewList<Map>();
        if(list == null) return r;
        for(NRank _e : list) {
            Map e = _e.toMap();
            if(e == null) continue;
            r.add(e);
        }
        return r;
    }

    public static List<NRank> maps_list(List<NewMap> maps) {
        List r = new NewList();
        for(NewMap _e : maps) {
            NRank e = NRank.parse(_e);
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


    public static NRanks parse(NewMap map2) {
        if(map2 == null) return null;

        NRanks r = new NRanks();
        r.list = maps_list( map2.getList("list") );
        return r;
    }

    public String toString() {
        return "NRanks[list=" + list + "]";
    }

    public static NRanks parse(byte[] buf) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2Helper.toMap(buf);
        return parse(map2);
    }

    public static NRanks parse(InputStream in) throws Exception {
        NewMap map2 = com.bowlong.bio2.B2InputStream.readMap(in);
        return parse(map2);
    }

    public byte[] toByteArray() throws Exception {
        return com.bowlong.bio2.B2Helper.toBytes(toMap());
    }

}
