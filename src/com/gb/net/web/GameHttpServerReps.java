package com.gb.net.web;

import gen_b2g.web_disp.GBSngGmSvI;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.multipart.FileUpload;

import java.io.File;
import java.io.Serializable;
import java.net.URI;
import java.util.Date;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.bio2.B2Helper;
import com.bowlong.io.FileRw;
import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.third.FastJSON;
import com.bowlong.third.netty4.httphand.N4HttpResp;
import com.bowlong.third.netty4.httphand.N4HttpResponse;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.NewMap;
import com.gb.content.AppContext;
import com.gb.content.Svc;
import com.gb.db.bean.Player;
import com.gb.logic.impl.GBSngGmImpl;
import com.gb.logic.opt.Logical;
import com.gb.logic.opt.server.IAPIOSRecharge;
import com.gb.logic.opt.server.OptSvEmail4Rnk;
import com.gb.timer.TimerNight;

@SuppressWarnings({ "rawtypes" })
public class GameHttpServerReps implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(GameHttpServerReps.class);

	private GameHttpServerReps() {
	}

	static private GameHttpServerReps self;

	static public GameHttpServerReps getInstance() {
		if (self == null)
			self = new GameHttpServerReps();
		return self;
	}

	static final String Key_Chn = "chl";

	static final String Out_Error = "error";

	public String dispIssue(Channel chn, Object msg) {
		try {
			if (msg instanceof HttpRequest) {
				HttpRequest req = (HttpRequest) msg;
				URI uri = new URI(req.getUri());
				String path = uri.getPath();
				if (StrEx.isEmpty(path)) {
					N4HttpResponse.send(chn, Out_Error);
					return Out_Error;
				}

				switch (path) {
				case "/timeToStr":
					longToTimeStr(chn, msg);
					break;
				case "/rnk":
					rnkPlayer(chn, msg);
					break;
				case "/rnkReset4Pl":
					rnkReset4Pl(chn, msg);
					break;
				case "/rnkAward4Send":
					rnkAward4Send(chn, msg);
					break;
				case "/createEmail4RnkByJsonTxt":
					createEmail4RnkByJsonTxt(chn, msg);
					break;
				case "/createEmail":
					createEmail(chn, msg);
					break;
				case "/rnk4Chn":
					rnk4Chn(chn, msg);
					break;
				case "/isClearLog":
					isClearLog(chn, msg);
					break;
				case "/verify_ios_pay":
					verify_ios_pay(chn, msg);
					break;
				case "/gameHttp":
					gameHttp(chn, msg);
					break;
				case "/seePlByName":
					seePlByName(chn, msg);
					break;
				case "/upJson":
					upJson(chn, msg);
					break;
				case "/upFile":
					upFile(chn, msg);
					break;
				default:
					N4HttpResponse.send(chn, Out_Error + ",该方法名字有误:" + path);
					break;
				}
				return path;
			}
			return msg.getClass().getName();
		} catch (Exception e) {
			try {
				N4HttpResponse.send(chn, Svc.e2s(e));
			} catch (Exception ee) {
			}
			return Out_Error;
		} finally {
			try {
				chn.disconnect();
				chn.close();
			} catch (Exception e) {
			}
		}
	}

	void longToTimeStr(Channel chn, Object msg) {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String strLong = MapEx.getString(map, "time");
		try {
			long sub = Long.parseLong(strLong);
			Date d = new Date(sub);
			String t = Svc.tFmt(d);
			N4HttpResponse.send(chn, "时间:" + t);
		} catch (Exception e) {
			try {
				N4HttpResponse.send(chn, Svc.e2s(e));
			} catch (Exception ee) {
			}
		}
	}

	void rnkPlayer(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		// 是否重新生成当天的排行榜
		boolean isRnk = MapEx.getBoolean(map, "isRnk");
		// 生成排行奖励邮件时间
		String str = MapEx.getString(map, "time");
		// 是否生成排行奖励邮件
		boolean isEmail = MapEx.getBoolean(map, "isEmail");
		TimerNight.getInstance().exce4Pub(isRnk, str, isEmail);
		N4HttpResponse.send(chn, "重新排行生成!");
	}

	void rnkReset4Pl(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String unqid = MapEx.getString(map, "unqid");
		String name = MapEx.getString(map, "name");
		boolean isOkey = Logical.resetActivity(unqid, name);
		if (isOkey) {
			N4HttpResponse.send(chn, "重新设置角色的活动状态，成功!");
		} else {
			N4HttpResponse.send(chn, "重新设置角色的活动状态，失败!");
		}
	}

	void rnkAward4Send(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String str = MapEx.getString(map, "time");
		boolean isSend = MapEx.getBoolean(map, "isSend");
		OptSvEmail4Rnk.setDataStr4Get(str, isSend);
		N4HttpResponse.send(chn, "重新设置取得排行榜数据!取得表的时间:" + str + ",是否可以取得:"
				+ isSend);
	}

	/*** 创建排名奖励邮件 **/
	void createEmail4RnkByJsonTxt(Channel chn, Object msg) throws Exception {
		// FileUpload fup = N4HttpResp.getFileByMsg(msg);
		// byte[] buf = N4B2ByteBuf.readBuff(fup.content());
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String str = MapEx.getString(map, "json");
		Map mapU_Ind = FastJSON.parseMap(str);
		OptSvEmail4Rnk.createEmails4RnkByMap(mapU_Ind);
		N4HttpResponse.send(chn, "成功！");
	}

	/*** 创建邮件 **/
	void createEmail(Channel chn, Object msg) throws Exception {
		// FileUpload fup = N4HttpResp.getFileByMsg(msg);
		// byte[] buf = N4B2ByteBuf.readBuff(fup.content());

		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String unqid = MapEx.getString(map, "unqid");
		String title = MapEx.getString(map, "title");
		String cont = MapEx.getString(map, "cont");
		String awardJson = MapEx.getString(map, "awardJson");
		OptSvEmail4Rnk.createEmail4Pl(unqid, title, cont, awardJson);
		N4HttpResponse.send(chn, "成功！");
	}

	/*** 根据渠道排行 **/
	void rnk4Chn(Channel chn, Object msg) throws Exception {
		// FileUpload fup = N4HttpResp.getFileByMsg(msg);
		// byte[] buf = N4B2ByteBuf.readBuff(fup.content());
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String chnStr = MapEx.getString(map, "chn");
		int type = MapEx.getInt(map, "type");
		int parsType = MapEx.getInt(map, "parsType");
		TimerNight.getInstance().exce4Chn(chnStr, type, parsType);
		N4HttpResponse.send(chn, chnStr + "成功！");
	}

	void seePlByName(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String strName = MapEx.getString(map, "name");
		Player pl = Logical.getPlByName(strName);
		if (pl != null) {
			N4HttpResponse.send(chn, pl.toJson(true));
		} else {
			N4HttpResponse.send(chn, "play is null,name = " + strName);
		}
	}

	/*** 是否清除日志 **/
	void isClearLog(Channel chn, Object msg) throws Exception {
		// FileUpload fup = N4HttpResp.getFileByMsg(msg);
		// byte[] buf = N4B2ByteBuf.readBuff(fup.content());

		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		boolean isClear = MapEx.getBoolean(map, "isClear");
		TimerNight.isClearLog = isClear;
		N4HttpResponse.send(chn, "成功！");
	}

	/*** IOS_充值回调验证 **/
	void verify_ios_pay(Channel chn, Object msg) throws Exception {
		Map<String, String> mapData = N4HttpResp.getMapKVByMsg(msg);
		String sign = MapEx.getString(mapData, "sign");
		boolean ret = IAPIOSRecharge.checkPay(mapData, sign);
		// byte[] bts = ByteEx.putBoolean(ret);
		N4HttpResponse.send(chn, String.valueOf(ret));
	}

	/*** 游戏请求方法 **/
	static final GBSngGmSvI gmSv = new GBSngGmImpl();

	/*** 游戏的HTTP请求 **/
	void gameHttp(Channel chn, Object msg) throws Exception {
		long t1 = System.currentTimeMillis();

		byte[] bytes = N4HttpResp.getBytesContByMsg(msg);
		NewMap map = B2Helper.toMap(bytes);
		GameHttpServerRepsChn gmchnreps = new GameHttpServerRepsChn(chn, map);
		gmSv.disp(gmchnreps, map);

		long t2 = System.currentTimeMillis();
		long dt = t2 - t1;
		String strStarUp = "";
		if (dt > DateEx.TIME_SECOND) {
			dt = (long) Math.ceil((double) dt / DateEx.TIME_SECOND);
			strStarUp = PStr.str(dt, " 秒(s)");
		} else {
			strStarUp = PStr.str(dt, " 毫秒(ms)");
		}

		String pv = gmSv.pv_params(map);
		String info = PStr.str("GameHttpServerReps.gameHttp=====time : ",
				strStarUp, ",pv : ", pv);

		System.out.println(info);
		// log.info(info);
	}

	void upJson(Channel chn, Object msg) throws Exception {
		String t = FileRw.readStr("html/upFile.html");
		StringBuilder builder = new StringBuilder("http://");
		String host = AppContext.getGateHost();
		if (StrEx.isEmpty(host)) {
			// host = "112.124.56.63";
			host = "127.0.0.1";
		}
		builder.append(host);
		builder.append(":");
		builder.append(AppContext.getGamePortWeb());
		builder.append("/upFile");
		String action = builder.toString();
		t = StrEx.fmt(t, action);
		N4HttpResponse.send(chn, t);
	}

	void upFile(Channel chn, Object msg) throws Exception {
		FileUpload fup = N4HttpResp.getFileByMsg(msg);
		String fileName = fup.getFilename();
		String suffix = StrEx.right(fileName, ".");
		String newFilePath = "files/" + DateEx.nowStr4() + "." + suffix;
		File f = FileRw.getFile(newFilePath);
		fup.renameTo(f);
	}
}
