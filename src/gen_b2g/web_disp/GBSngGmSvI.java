package gen_b2g.web_disp;

import java.io.*;
import java.util.*;

import com.bowlong.io.*;
import com.bowlong.util.*;
import com.bowlong.net.*;
import com.bowlong.util.ExceptionEx;

import gen_b2g.web_disp.bean.*;

@SuppressWarnings("all")
public abstract class GBSngGmSvI extends com.bowlong.net.proto.NSupport {



    // //////////////////////////////////////////////
    // 逻辑分发
    // //////////////////////////////////////////////

    public String disp(TcpChannel chn, NewMap map) throws Exception {
        if(chn == null) return "";
        String cmd = map.getString("cmd");
        return disp(chn, cmd, map);
    }
    public String disp(TcpChannel chn, String cmd, NewMap map) throws Exception {
        if(chn == null) return "";
        switch (cmd) {
            case "error4Lua": { //  提交错误问题
                __error4Lua(chn, map);
                return "error4Lua";
            }
            case "getChatsByHttp": { //  取得聊天内容集合
                __getChatsByHttp(chn, map);
                return "getChatsByHttp";
            }
            case "getNEmals": { //  取得邮件列表
                __getNEmals(chn, map);
                return "getNEmals";
            }
            case "getNRanks": { //  取得排行榜列表
                __getNRanks(chn, map);
                return "getNRanks";
            }
            case "isInitSngByHttp": { //  判断是否初始化
                __isInitSngByHttp(chn, map);
                return "isInitSngByHttp";
            }
            case "recordPhone": { //  记录电话号码
                __recordPhone(chn, map);
                return "recordPhone";
            }
            case "rndPnameByHttp": { //  随机取聊天名
                __rndPnameByHttp(chn, map);
                return "rndPnameByHttp";
            }
            case "sendChatByHttp": { //  发送聊天
                __sendChatByHttp(chn, map);
                return "sendChatByHttp";
            }
            case "setPnameByHttp": { //  设置聊天名
                __setPnameByHttp(chn, map);
                return "setPnameByHttp";
            }
            case "sync2Game": { //  同步数据到服务器
                __sync2Game(chn, map);
                return "sync2Game";
            }
            case "sync2Local": { //  同步数据到本地
                __sync2Local(chn, map);
                return "sync2Local";
            }
            case "verifySign4GuoQin": { //  国庆7天签到的验证
                __verifySign4GuoQin(chn, map);
                return "verifySign4GuoQin";
            }
            case "verifySytTime": { //  判断是否是当天
                __verifySytTime(chn, map);
                return "verifySytTime";
            }
        }
        throw new Exception(" cmd: " + cmd + ":" + map + " not found processor.");
    }

    // //////////////////////////////////////////////
    // 解析参数
    // //////////////////////////////////////////////

    // 提交错误问题
    private void __error4Lua(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String uuid = map2.getString("uuid");
        String device = map2.getString("device");
        String error = map2.getString("error");

        ReturnStatus rst = new ReturnStatus();
        try {
            onError4Lua(chn, uuid, device, error, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"uuid:\"", uuid, "\"device:\"", device, "\"error:\"", error, };
            onExcept(chn, "error4Lua", rethrow(e, "error4Lua", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 2);
            writeMapEntry(result, "cmd", "error4Lua");
            writeMapEntry(result, "1", rst.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 取得聊天内容集合
    private void __getChatsByHttp(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String unqid = map2.getString("unqid");
        NChats nchats = new NChats();

        ReturnStatus rst = new ReturnStatus();
        try {
            onGetChatsByHttp(chn, unqid, nchats, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"unqid:\"", unqid, "\"nchats:\"", nchats, };
            onExcept(chn, "getChatsByHttp", rethrow(e, "getChatsByHttp", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 3);
            writeMapEntry(result, "cmd", "getChatsByHttp");
            writeMapEntry(result, "1", rst.toMap());
            writeMapEntry(result, "nchats", nchats.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 取得邮件列表
    private void __getNEmals(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String unqid = map2.getString("unqid");
        long lasttime = (long) map2.getDouble("lasttime");
        NEmails nemails = new NEmails();

        ReturnStatus rst = new ReturnStatus();
        try {
            onGetNEmals(chn, unqid, lasttime, nemails, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"unqid:\"", unqid, "\"lasttime:\"", lasttime, "\"nemails:\"", nemails, };
            onExcept(chn, "getNEmals", rethrow(e, "getNEmals", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 3);
            writeMapEntry(result, "cmd", "getNEmals");
            writeMapEntry(result, "1", rst.toMap());
            writeMapEntry(result, "nemails", nemails.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 取得排行榜列表
    private void __getNRanks(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String unqid = map2.getString("unqid");
        int type = (int) map2.getDouble("type");
        NRank nrnkSelf = new NRank();
        NRanks nrnks = new NRanks();

        ReturnStatus rst = new ReturnStatus();
        try {
            onGetNRanks(chn, unqid, type, nrnkSelf, nrnks, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"unqid:\"", unqid, "\"type:\"", type, "\"nrnkSelf:\"", nrnkSelf, "\"nrnks:\"", nrnks, };
            onExcept(chn, "getNRanks", rethrow(e, "getNRanks", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 4);
            writeMapEntry(result, "cmd", "getNRanks");
            writeMapEntry(result, "1", rst.toMap());
            writeMapEntry(result, "nrnkSelf", nrnkSelf.toMap());
            writeMapEntry(result, "nrnks", nrnks.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 判断是否初始化
    private void __isInitSngByHttp(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String unqid = map2.getString("unqid");
        String uuid = map2.getString("uuid");
        NBool nbl = new NBool();
        NStr nname = new NStr();

        ReturnStatus rst = new ReturnStatus();
        try {
            onIsInitSngByHttp(chn, unqid, uuid, nbl, nname, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"unqid:\"", unqid, "\"uuid:\"", uuid, "\"nbl:\"", nbl, "\"nname:\"", nname, };
            onExcept(chn, "isInitSngByHttp", rethrow(e, "isInitSngByHttp", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 4);
            writeMapEntry(result, "cmd", "isInitSngByHttp");
            writeMapEntry(result, "1", rst.toMap());
            writeMapEntry(result, "nbl", nbl.toMap());
            writeMapEntry(result, "nname", nname.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 记录电话号码
    private void __recordPhone(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String unqid = map2.getString("unqid");
        String uuid = map2.getString("uuid");
        String phone = map2.getString("phone");

        ReturnStatus rst = new ReturnStatus();
        try {
            onRecordPhone(chn, unqid, uuid, phone, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"unqid:\"", unqid, "\"uuid:\"", uuid, "\"phone:\"", phone, };
            onExcept(chn, "recordPhone", rethrow(e, "recordPhone", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 2);
            writeMapEntry(result, "cmd", "recordPhone");
            writeMapEntry(result, "1", rst.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 随机取聊天名
    private void __rndPnameByHttp(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        NStr nrndName = new NStr();

        ReturnStatus rst = new ReturnStatus();
        try {
            onRndPnameByHttp(chn, nrndName, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"nrndName:\"", nrndName, };
            onExcept(chn, "rndPnameByHttp", rethrow(e, "rndPnameByHttp", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 3);
            writeMapEntry(result, "cmd", "rndPnameByHttp");
            writeMapEntry(result, "1", rst.toMap());
            writeMapEntry(result, "nrndName", nrndName.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 发送聊天
    private void __sendChatByHttp(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String unqid = map2.getString("unqid");
        String cont = map2.getString("cont");
        NChats nchats = new NChats();

        ReturnStatus rst = new ReturnStatus();
        try {
            onSendChatByHttp(chn, unqid, cont, nchats, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"unqid:\"", unqid, "\"cont:\"", cont, "\"nchats:\"", nchats, };
            onExcept(chn, "sendChatByHttp", rethrow(e, "sendChatByHttp", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 3);
            writeMapEntry(result, "cmd", "sendChatByHttp");
            writeMapEntry(result, "1", rst.toMap());
            writeMapEntry(result, "nchats", nchats.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 设置聊天名
    private void __setPnameByHttp(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String unqid = map2.getString("unqid");
        String newName = map2.getString("newName");

        ReturnStatus rst = new ReturnStatus();
        try {
            onSetPnameByHttp(chn, unqid, newName, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"unqid:\"", unqid, "\"newName:\"", newName, };
            onExcept(chn, "setPnameByHttp", rethrow(e, "setPnameByHttp", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 2);
            writeMapEntry(result, "cmd", "setPnameByHttp");
            writeMapEntry(result, "1", rst.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 同步数据到服务器
    private void __sync2Game(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String unqid = map2.getString("unqid");
        String uuid = map2.getString("uuid");
        byte[] btPl = map2.getBytes("btPl");
        byte[] btHero = map2.getBytes("btHero");
        byte[] btPart = map2.getBytes("btPart");
        byte[] btProp = map2.getBytes("btProp");
        byte[] btNpc = map2.getBytes("btNpc");
        byte[] btEmail = map2.getBytes("btEmail");
        String chnStr = map2.getString("chnStr");
        String chnSub = map2.getString("chnSub");
        int fight4hero = (int) map2.getDouble("fight4hero");
        int fight4part = (int) map2.getDouble("fight4part");
        int npcStars = (int) map2.getDouble("npcStars");

        ReturnStatus rst = new ReturnStatus();
        try {
            onSync2Game(chn, unqid, uuid, btPl, btHero, btPart, btProp, btNpc, btEmail, chnStr, chnSub, fight4hero, fight4part, npcStars, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"unqid:\"", unqid, "\"uuid:\"", uuid, "\"btPl:\"", btPl, "\"btHero:\"", btHero, "\"btPart:\"", btPart, "\"btProp:\"", btProp, "\"btNpc:\"", btNpc, "\"btEmail:\"", btEmail, "\"chnStr:\"", chnStr, "\"chnSub:\"", chnSub, "\"fight4hero:\"", fight4hero, "\"fight4part:\"", fight4part, "\"npcStars:\"", npcStars, };
            onExcept(chn, "sync2Game", rethrow(e, "sync2Game", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 2);
            writeMapEntry(result, "cmd", "sync2Game");
            writeMapEntry(result, "1", rst.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 同步数据到本地
    private void __sync2Local(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String unqid = map2.getString("unqid");
        String uuid = map2.getString("uuid");
        NBytes nbtPl = new NBytes();
        NBytes nbtHero = new NBytes();
        NBytes nbtPart = new NBytes();
        NBytes nbtProp = new NBytes();
        NBytes nbtNpc = new NBytes();
        NBytes nbtEmail = new NBytes();

        ReturnStatus rst = new ReturnStatus();
        try {
            onSync2Local(chn, unqid, uuid, nbtPl, nbtHero, nbtPart, nbtProp, nbtNpc, nbtEmail, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"unqid:\"", unqid, "\"uuid:\"", uuid, "\"nbtPl:\"", nbtPl, "\"nbtHero:\"", nbtHero, "\"nbtPart:\"", nbtPart, "\"nbtProp:\"", nbtProp, "\"nbtNpc:\"", nbtNpc, "\"nbtEmail:\"", nbtEmail, };
            onExcept(chn, "sync2Local", rethrow(e, "sync2Local", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 8);
            writeMapEntry(result, "cmd", "sync2Local");
            writeMapEntry(result, "1", rst.toMap());
            writeMapEntry(result, "nbtPl", nbtPl.toMap());
            writeMapEntry(result, "nbtHero", nbtHero.toMap());
            writeMapEntry(result, "nbtPart", nbtPart.toMap());
            writeMapEntry(result, "nbtProp", nbtProp.toMap());
            writeMapEntry(result, "nbtNpc", nbtNpc.toMap());
            writeMapEntry(result, "nbtEmail", nbtEmail.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 国庆7天签到的验证
    private void __verifySign4GuoQin(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        int dayIn = (int) map2.getDouble("dayIn");
        NBool nbl = new NBool();

        ReturnStatus rst = new ReturnStatus();
        try {
            onVerifySign4GuoQin(chn, dayIn, nbl, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"dayIn:\"", dayIn, "\"nbl:\"", nbl, };
            onExcept(chn, "verifySign4GuoQin", rethrow(e, "verifySign4GuoQin", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 3);
            writeMapEntry(result, "cmd", "verifySign4GuoQin");
            writeMapEntry(result, "1", rst.toMap());
            writeMapEntry(result, "nbl", nbl.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }

    // 判断是否是当天
    private void __verifySytTime(TcpChannel chn, NewMap map2) throws Exception {
        if(chn == null) return;
        String timeStr = map2.getString("timeStr");
        NBool nbl = new NBool();

        ReturnStatus rst = new ReturnStatus();
        try {
            onVerifySytTime(chn, timeStr, nbl, rst);
        } catch ( Exception e ) {
            Object[] othrows = {"\"timeStr:\"", timeStr, "\"nbl:\"", nbl, };
            onExcept(chn, "verifySytTime", rethrow(e, "verifySytTime", othrows), rst);
        }
        try ( ByteOutStream result = getStream();) {
            writeMapTag(result, 3);
            writeMapEntry(result, "cmd", "verifySytTime");
            writeMapEntry(result, "1", rst.toMap());
            writeMapEntry(result, "nbl", nbl.toMap());
            chn.send(result.toByteArray());
        } catch (Exception e) {
            throw e;
        }
    }


    public static Exception rethrow(Exception cause, String method, Object... params) {
        String causeMessage = ExceptionEx.e2s(cause);
        if (causeMessage == null) {
            causeMessage = "";
        }
        StringBuffer msg = new StringBuffer(causeMessage);
        msg.append("\r\n");
        msg.append(method);
        msg.append(" Parameters: ");
        msg.append("\r\n");
        if (params == null) {
            msg.append("[]");
        } else {
            msg.append(Arrays.deepToString(params));
        }
        msg.append("\r\n");
        Exception e = new Exception(msg.toString(), cause);
        return e;
    }


    // //////////////////////////////////////////////
    // 需要实现的接口
    // //////////////////////////////////////////////

    public abstract void onExcept(TcpChannel chn, String method, Exception e, ReturnStatus ret) throws Exception;
    // 提交错误问题
    public abstract void onError4Lua(TcpChannel chn , String uuid, String device, String error, ReturnStatus ret) throws Exception;
    // 取得聊天内容集合
    public abstract void onGetChatsByHttp(TcpChannel chn , String unqid, NChats nchats, ReturnStatus ret) throws Exception;
    // 取得邮件列表
    public abstract void onGetNEmals(TcpChannel chn , String unqid, long lasttime, NEmails nemails, ReturnStatus ret) throws Exception;
    // 取得排行榜列表
    public abstract void onGetNRanks(TcpChannel chn , String unqid, int type, NRank nrnkSelf, NRanks nrnks, ReturnStatus ret) throws Exception;
    // 判断是否初始化
    public abstract void onIsInitSngByHttp(TcpChannel chn , String unqid, String uuid, NBool nbl, NStr nname, ReturnStatus ret) throws Exception;
    // 记录电话号码
    public abstract void onRecordPhone(TcpChannel chn , String unqid, String uuid, String phone, ReturnStatus ret) throws Exception;
    // 随机取聊天名
    public abstract void onRndPnameByHttp(TcpChannel chn , NStr nrndName, ReturnStatus ret) throws Exception;
    // 发送聊天
    public abstract void onSendChatByHttp(TcpChannel chn , String unqid, String cont, NChats nchats, ReturnStatus ret) throws Exception;
    // 设置聊天名
    public abstract void onSetPnameByHttp(TcpChannel chn , String unqid, String newName, ReturnStatus ret) throws Exception;
    // 同步数据到服务器
    public abstract void onSync2Game(TcpChannel chn , String unqid, String uuid, byte[] btPl, byte[] btHero, byte[] btPart, byte[] btProp, byte[] btNpc, byte[] btEmail, String chnStr, String chnSub, int fight4hero, int fight4part, int npcStars, ReturnStatus ret) throws Exception;
    // 同步数据到本地
    public abstract void onSync2Local(TcpChannel chn , String unqid, String uuid, NBytes nbtPl, NBytes nbtHero, NBytes nbtPart, NBytes nbtProp, NBytes nbtNpc, NBytes nbtEmail, ReturnStatus ret) throws Exception;
    // 国庆7天签到的验证
    public abstract void onVerifySign4GuoQin(TcpChannel chn , int dayIn, NBool nbl, ReturnStatus ret) throws Exception;
    // 判断是否是当天
    public abstract void onVerifySytTime(TcpChannel chn , String timeStr, NBool nbl, ReturnStatus ret) throws Exception;


    // //////////////////////////////////////////////
    // PV  请求  参数 逻辑分发  
    // //////////////////////////////////////////////

    public String pv_params(NewMap map) throws Exception {
        String cmd = map.getString("cmd");
        return pv_params(cmd, map);
    }
    public String pv_params(String cmd, NewMap map) throws Exception {
        switch (cmd) {
            case "error4Lua": { //  提交错误问题
                return pv_error4Lua_params(map);
            }
            case "getChatsByHttp": { //  取得聊天内容集合
                return pv_getChatsByHttp_params(map);
            }
            case "getNEmals": { //  取得邮件列表
                return pv_getNEmals_params(map);
            }
            case "getNRanks": { //  取得排行榜列表
                return pv_getNRanks_params(map);
            }
            case "isInitSngByHttp": { //  判断是否初始化
                return pv_isInitSngByHttp_params(map);
            }
            case "recordPhone": { //  记录电话号码
                return pv_recordPhone_params(map);
            }
            case "rndPnameByHttp": { //  随机取聊天名
                return pv_rndPnameByHttp_params(map);
            }
            case "sendChatByHttp": { //  发送聊天
                return pv_sendChatByHttp_params(map);
            }
            case "setPnameByHttp": { //  设置聊天名
                return pv_setPnameByHttp_params(map);
            }
            case "sync2Game": { //  同步数据到服务器
                return pv_sync2Game_params(map);
            }
            case "sync2Local": { //  同步数据到本地
                return pv_sync2Local_params(map);
            }
            case "verifySign4GuoQin": { //  国庆7天签到的验证
                return pv_verifySign4GuoQin_params(map);
            }
            case "verifySytTime": { //  判断是否是当天
                return pv_verifySytTime_params(map);
            }
        }
        return (" cmd: " + cmd + ":" + map + " not found processor.");
    }

    // //////////////////////////////////////////////
    // PV 请求  解析参数 
    // //////////////////////////////////////////////

    // 提交错误问题
    private String pv_error4Lua_params(NewMap map2) throws Exception {
        String uuid = map2.getString("uuid");
        String device = map2.getString("device");
        String error = map2.getString("error");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("error4Lua(");
            sb.append("\"uuid\":").append(uuid).append(",");
            sb.append("\"device\":").append(device).append(",");
            sb.append("\"error\":").append(error).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 取得聊天内容集合
    private String pv_getChatsByHttp_params(NewMap map2) throws Exception {
        String unqid = map2.getString("unqid");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getChatsByHttp(");
            sb.append("\"unqid\":").append(unqid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 取得邮件列表
    private String pv_getNEmals_params(NewMap map2) throws Exception {
        String unqid = map2.getString("unqid");
        long lasttime = map2.getLong("lasttime");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getNEmals(");
            sb.append("\"unqid\":").append(unqid).append(",");
            sb.append("\"lasttime\":").append(lasttime).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 取得排行榜列表
    private String pv_getNRanks_params(NewMap map2) throws Exception {
        String unqid = map2.getString("unqid");
        int type = map2.getInt("type");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("getNRanks(");
            sb.append("\"unqid\":").append(unqid).append(",");
            sb.append("\"type\":").append(type).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 判断是否初始化
    private String pv_isInitSngByHttp_params(NewMap map2) throws Exception {
        String unqid = map2.getString("unqid");
        String uuid = map2.getString("uuid");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("isInitSngByHttp(");
            sb.append("\"unqid\":").append(unqid).append(",");
            sb.append("\"uuid\":").append(uuid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 记录电话号码
    private String pv_recordPhone_params(NewMap map2) throws Exception {
        String unqid = map2.getString("unqid");
        String uuid = map2.getString("uuid");
        String phone = map2.getString("phone");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("recordPhone(");
            sb.append("\"unqid\":").append(unqid).append(",");
            sb.append("\"uuid\":").append(uuid).append(",");
            sb.append("\"phone\":").append(phone).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 随机取聊天名
    private String pv_rndPnameByHttp_params(NewMap map2) throws Exception {
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("rndPnameByHttp(");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 发送聊天
    private String pv_sendChatByHttp_params(NewMap map2) throws Exception {
        String unqid = map2.getString("unqid");
        String cont = map2.getString("cont");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("sendChatByHttp(");
            sb.append("\"unqid\":").append(unqid).append(",");
            sb.append("\"cont\":").append(cont).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 设置聊天名
    private String pv_setPnameByHttp_params(NewMap map2) throws Exception {
        String unqid = map2.getString("unqid");
        String newName = map2.getString("newName");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("setPnameByHttp(");
            sb.append("\"unqid\":").append(unqid).append(",");
            sb.append("\"newName\":").append(newName).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 同步数据到服务器
    private String pv_sync2Game_params(NewMap map2) throws Exception {
        String unqid = map2.getString("unqid");
        String uuid = map2.getString("uuid");
        byte[] btPl = map2.getBytes("btPl");
        byte[] btHero = map2.getBytes("btHero");
        byte[] btPart = map2.getBytes("btPart");
        byte[] btProp = map2.getBytes("btProp");
        byte[] btNpc = map2.getBytes("btNpc");
        byte[] btEmail = map2.getBytes("btEmail");
        String chnStr = map2.getString("chnStr");
        String chnSub = map2.getString("chnSub");
        int fight4hero = map2.getInt("fight4hero");
        int fight4part = map2.getInt("fight4part");
        int npcStars = map2.getInt("npcStars");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("sync2Game(");
            sb.append("\"unqid\":").append(unqid).append(",");
            sb.append("\"uuid\":").append(uuid).append(",");
            sb.append("\"btPl\":").append(btPl).append(",");
            sb.append("\"btHero\":").append(btHero).append(",");
            sb.append("\"btPart\":").append(btPart).append(",");
            sb.append("\"btProp\":").append(btProp).append(",");
            sb.append("\"btNpc\":").append(btNpc).append(",");
            sb.append("\"btEmail\":").append(btEmail).append(",");
            sb.append("\"chnStr\":").append(chnStr).append(",");
            sb.append("\"chnSub\":").append(chnSub).append(",");
            sb.append("\"fight4hero\":").append(fight4hero).append(",");
            sb.append("\"fight4part\":").append(fight4part).append(",");
            sb.append("\"npcStars\":").append(npcStars).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 同步数据到本地
    private String pv_sync2Local_params(NewMap map2) throws Exception {
        String unqid = map2.getString("unqid");
        String uuid = map2.getString("uuid");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("sync2Local(");
            sb.append("\"unqid\":").append(unqid).append(",");
            sb.append("\"uuid\":").append(uuid).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 国庆7天签到的验证
    private String pv_verifySign4GuoQin_params(NewMap map2) throws Exception {
        int dayIn = map2.getInt("dayIn");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("verifySign4GuoQin(");
            sb.append("\"dayIn\":").append(dayIn).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
    // 判断是否是当天
    private String pv_verifySytTime_params(NewMap map2) throws Exception {
        String timeStr = map2.getString("timeStr");
        StringBuffer sb = com.bowlong.objpool.StringBufPool.borrowObject();
        try {
            sb.append("verifySytTime(");
            sb.append("\"timeStr\":").append(timeStr).append(",");
            sb.append(")");
            return sb.toString();
        } finally {
            com.bowlong.objpool.StringBufPool.returnObject(sb);
        }

    }
}
