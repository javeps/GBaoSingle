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
import com.gb.content.Svc;
import com.gb.db.bean.Player;
import com.gb.logic.chn.baidu.LogicalBaidu;
import com.gb.logic.chn.egame.LogicalEgame;
import com.gb.logic.chn.gionee.LogicalGionee;
import com.gb.logic.chn.huawei.LogicalHuaWei;
import com.gb.logic.chn.mi.LogicalXiaoMi;
import com.gb.logic.chn.mmand.LogicalMMAnd;
import com.gb.logic.chn.qihoo360.LogicalQihoo360;
import com.gb.logic.chn.uc.LogicalUc;
import com.gb.logic.chn.unicom.LogicalUnicom;
import com.gb.logic.chn.vivo.LogicalVivo;
import com.gb.logic.chn.wdj.LogicalWdj;
import com.gb.logic.impl.GBSngGmImpl;
import com.gb.logic.opt.Logical;
import com.gb.logic.opt.LogicalCop;
import com.gb.logic.opt.model.LogicalRecordOrders;
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
					String info = "error path is null,uri=[" + req.getUri()
							+ "]";
					log.error(info);
					return Out_Error;
				}

				if (path.indexOf("/css/") >= 0) {
					css(chn, msg);
					return path;
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
				case "/upHtml":
					upHtml(chn, msg);
					break;
				case "/upFile":
					upFile(chn, msg);
					break;
				case "/getCop":
					getCop(chn, msg);
					break;
				case "/copHtml":
					copHtml(chn, msg);
					break;
				case "/upCopFee":
					upCopFee(chn, msg);
					break;
				case "/emailHtml":
					emailHtml(chn, msg);
					break;
				case "/mmandBilling":
					mmandBilling(chn, msg);
					break;
				case "/validaBilling":
					validaBilling(chn, msg);
					break;
				case "/gioneeBilling":
					gioneeBilling(chn, msg);
					break;
				case "/qihoo360Billing":
					qihoo360Billing(chn, msg);
					break;
				case "/egameBilling":
					egameBilling(chn, msg);
					break;
				case "/oppoBilling":
					oppoBilling(chn, msg);
					break;
				case "/unicomBilling":
					unicomBilling(chn, msg);
					break;
				case "/vivoBilling":
					vivoBilling(chn, msg);
					break;
				case "/miBilling":
					miBilling(chn, msg);
					break;
				case "/huaweiBilling":
					huaweiBilling(chn, msg);
					break;
				case "/wdjBilling":
					wdjBilling(chn, msg);
					break;
				case "/ucBilling":
					ucBilling(chn, msg);
					break;
				case "/baiduBilling":
					baiduBilling(chn, msg);
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
				String info = Svc.e2s(e);
				N4HttpResponse.send(chn, info);
				log.error(info);
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

	void longToTimeStr(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String strLong = MapEx.getString(map, "time");
		long sub = Long.parseLong(strLong);
		Date d = new Date(sub);
		String t = Svc.tFmt(d);
		N4HttpResponse.send(chn, "时间:" + t);
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

	void upHtml(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String saveFileName = MapEx.getString(map, "fileName");
		String t = FileRw.readStr("html/upFile.html");
		String action = Logical.getActionUrl("upFile");
		t = StrEx.fmt(t, action, saveFileName);
		N4HttpResponse.send(chn, t);
	}

	void upFile(Channel chn, Object msg) throws Exception {
		FileUpload fup = N4HttpResp.getFileByMsg(msg);
		String fileName = fup.getFilename();
		String suffix = StrEx.right(fileName, ".");

		String saveName = "";
		saveName = StrEx.left(fileName, ".");
		if (StrEx.isEmpty(saveName)) {
			saveName = DateEx.nowStr4();
		}

		String newFilePath = "files/" + saveName + "." + suffix;
		File f = FileRw.getFile(newFilePath);
		fup.renameTo(f);
		N4HttpResponse.send(chn, fileName + " is up successed,newFileName="
				+ newFilePath);
	}

	void getCop(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String strChn = MapEx.getString(map, "chn");
		String version = MapEx.getString(map, "version");
		String json = LogicalCop.getCop(strChn, version);
		// N4HttpResponse.sendJson(chn, json);
		N4HttpResponse.send(chn, json);
	}

	void copHtml(Channel chn, Object msg) throws Exception {
		// Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		// String saveFileName = MapEx.getString(map, "fileName");
		String t = LogicalCop.getCopHtml();
		N4HttpResponse.send(chn, t);
	}

	void upCopFee(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		// String unqkey = MapEx.getString(map, "unqkey");
		String chnStr = MapEx.getString(map, "chn");
		String version = MapEx.getString(map, "version");
		int copfee = MapEx.getInt(map, "copfee");
		System.out.println(map);
		LogicalCop.changeCopfee(chnStr, version, copfee);
		N4HttpResponse.send(chn, "成功！");
	}

	void css(Channel chn, Object msg) throws Exception {
		HttpRequest req = (HttpRequest) msg;
		URI uri = new URI(req.getUri());
		String path = uri.getPath();
		String fileName = PStr.b("html").a(path).e();
		String fTxt = FileRw.readStr(fileName);
		N4HttpResponse.sendCss(chn, fTxt);
	}

	void emailHtml(Channel chn, Object msg) throws Exception {
		String t = FileRw.readStr("html/sendMail.html");
		// http://112.124.56.63:6002/createEmail
		String action = Logical.getActionUrl("createEmail");
		t = StrEx.fmt(t, action);
		N4HttpResponse.send(chn, t);
	}

	// 验证充值状态
	private void validaBilling(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String unqkey = MapEx.getString(map, "unqkey");
		String chnPay = MapEx.getString(map, "chnPay");
		boolean isState = LogicalRecordOrders.useOrder(unqkey, chnPay);
		N4HttpResponse.send(chn, isState + "");
	}

	// mmand 充值回调
	void mmandBilling(Channel chn, Object msg) throws Exception {
		String xml = N4HttpResp.getStrContByMsg(msg, "UTF-8");
		String ret = LogicalMMAnd.handler(xml);
		N4HttpResponse.sendTxt(chn, ret);
	}

	// 金立充值回调
	void gioneeBilling(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapByPostDecoderBody(msg);
		String state = LogicalGionee.handler(map);
		N4HttpResponse.send(chn, state);
	}

	// qihoo360 充值回调
	void qihoo360Billing(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String ret = LogicalQihoo360.handler(map);
		N4HttpResponse.sendTxt(chn, ret);
	}

	// egame 充值回调
	void egameBilling(Channel chn, Object msg) throws Exception {
		String xml = N4HttpResp.getStrContByMsg(msg, "UTF-8");
		String ret = LogicalEgame.handler(xml);
		N4HttpResponse.sendTxt(chn, ret);
	}

	// oppo充值回调
	void oppoBilling(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String state = LogicalGionee.handler(map);
		N4HttpResponse.send(chn, state);
	}

	// unicom(联通) 充值回调
	void unicomBilling(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String state = LogicalUnicom.handler(map);
		N4HttpResponse.send(chn, state);
	}

	// vivo 充值回调
	void vivoBilling(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String state = LogicalVivo.handler(map);
		N4HttpResponse.send(chn, state);
	}

	// xiaomi 充值回调
	void miBilling(Channel chn, Object msg) throws Exception {
		// Map<String, String> map = N4HttpResp.getMapByPostDecoderBody(msg);
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String state = LogicalXiaoMi.handler(map);
		N4HttpResponse.sendJson(chn, state);
	}

	// huawei 充值回调
	void huaweiBilling(Channel chn, Object msg) throws Exception {
		// Map<String, String> map = N4HttpResp.getMapByPostDecoderBody(msg);
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String state = LogicalHuaWei.handler(map);
		N4HttpResponse.sendJson(chn, state);
	}

	// 豌豆荚充值回调
	void wdjBilling(Channel chn, Object msg) throws Exception {
		// Map<String, String> map = N4HttpResp.getMapByPostDecoderBody(msg);
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String state = LogicalWdj.handler(map);
		N4HttpResponse.send(chn, state);
	}

	// 九游UC充值回调
	void ucBilling(Channel chn, Object msg) throws Exception {
		Map<String, String> map = N4HttpResp.getMapByPostDecoderBody(msg);
		// Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String state = LogicalUc.handler(map);
		N4HttpResponse.send(chn, state);
	}

	// 百度充值回调
	void baiduBilling(Channel chn, Object msg) throws Exception {
		// Map<String, String> map = N4HttpResp.getMapByPostDecoderBody(msg);
		Map<String, String> map = N4HttpResp.getMapKVByMsg(msg);
		String state = LogicalBaidu.handler(map);
		N4HttpResponse.sendJson(chn, state);
	}
}
