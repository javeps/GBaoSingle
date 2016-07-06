package com.gb.logic.opt;

import gen_b2g.result.bean.ResultStatus;
import gen_b2g.web_disp.bean.NBool;
import gen_b2g.web_disp.bean.NBytes;
import gen_b2g.web_disp.bean.NChat;
import gen_b2g.web_disp.bean.NChats;
import gen_b2g.web_disp.bean.NStr;
import gen_b2g.web_disp.bean.ReturnStatus;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;

import com.bowlong.bio2.B2Helper;
import com.bowlong.bio2.B2InputStream;
import com.bowlong.io.ByteInStream;
import com.bowlong.lang.ByteEx;
import com.bowlong.lang.PStr;
import com.bowlong.lang.RndEx;
import com.bowlong.lang.StrEx;
import com.bowlong.net.TcpChannel;
import com.bowlong.tool.TkitValidateCheck;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.NewMap;
import com.gb.content.Svc;
import com.gb.db.bean.Player;
import com.gb.db.bean.Recode4error;
import com.gb.db.entity.PlayerEntity;
import com.gb.logic.cache.ProChats;
import com.gb.toolkits.NameRandom;

@SuppressWarnings("rawtypes")
public class Logical extends Svc {

	static Log log = getLog(Logical.class);

	static public void onExcept(TcpChannel chn, String method, Exception e,
			ReturnStatus ret) throws Exception {
		ret.succ = ResultStatus.R_Error;
		ret.msg = e2s(e);
		log.error(ret.msg);
		chn.close();
	}

	static public Player getPlByName(String name) {
		return PlayerEntity.getByPname(name);
	}

	static protected Player getPl(String unqid) {
		return PlayerEntity.getByUnqid(unqid);
	}

	static public boolean resetActivity(String unqid, String pname) {
		boolean isEmptyUnqid = StrEx.isEmptyTrim(unqid);
		boolean isEmpytPname = StrEx.isEmptyTrim(pname);
		if (isEmptyUnqid && isEmpytPname)
			return false;

		Player pl = null;
		if (!isEmptyUnqid) {
			pl = getPl(unqid);
		}
		if (!isEmpytPname && pl == null) {
			pl = getPlByName(pname);
		}
		if (pl == null)
			return false;
		pl.setStatusActivity(0);
		pl.update();
		return true;
	}

	static protected Player getPl(String unqid, String uuidMCode, String phone,
			String chn, String chnSub) {
		Player pl = getPl(unqid);
		if (phone == null) {
			phone = "";
		} else {
			if (phone.length() > 32) {
				phone = phone.substring(0, 32);
			}
		}

		if (!StrEx.isEmpty(phone)) {
			if (!TkitValidateCheck.isMobile(phone)) {
				phone = "";
			}
		}

		boolean isNullChn = StrEx.isEmpty(chn);
		boolean isNullChnSub = StrEx.isEmpty(chnSub);

		if (pl == null) {
			if (StrEx.isEmpty(uuidMCode))
				uuidMCode = unqid;
			String uuid = RndEx.randomUUID();
			if (uuid.length() > 12) {
				uuid = uuid.substring(0, 12);
			}
			byte[] btZero = new byte[0];

			Date createtime = DateEx.nowDate();
			if (isNullChn) {
				chn = "none";
			}

			if (isNullChnSub) {
				chnSub = "none";
			}

			pl = Player.newPlayer(0, unqid, uuidMCode, "pl" + uuid, 0, 0,
					btZero, btZero, btZero, btZero, btZero, btZero, phone,
					createtime, createtime, 0, 0, chn, chnSub, 0, 0, 0);

			pl = pl.insert();
		} else {
			if (!StrEx.isEmpty(phone)) {
				pl.setPhone(phone);
				pl.update();
			}
			if (!isNullChn || !isNullChnSub) {
				if (!isNullChn) {
					pl.setChn(chn);
				}
				if (!isNullChnSub) {
					pl.setChnSub(chnSub);
				}
				pl.update();
			}
		}
		return pl;
	}

	static public void onIsInitSngByHttp(TcpChannel chn, String unqid,
			String uuid, NBool nbl, NStr nname, ReturnStatus ret)
			throws Exception {
		Player pl = getPl(unqid, uuid, "", "", "");
		String pname = "";
		if (pl != null) {
			pname = pl.getPname();
		}
		boolean isRename = !(pname.length() == 14 && pname.indexOf("pl") == 0);
		nbl.val = isRename;
		if (isRename) {
			nname.val = pname;
		}
	}

	/*** 记录电话号码 **/
	static public void onRecordPhone(TcpChannel chn, String unqid, String uuid,
			String phone, ReturnStatus ret) throws Exception {
		getPl(unqid, uuid, phone, "", "");
	}

	/*** 随机取名 **/
	static public void onRndPnameByHttp(TcpChannel chn, NStr nrndName,
			ReturnStatus ret) throws Exception {
		Player pl = null;
		String rndname = null;
		do {
			rndname = NameRandom.getInstance().newNameCn();
			pl = PlayerEntity.getByPname(rndname);
		} while (pl != null);

		nrndName.val = rndname;
	}

	/*** 设置pname(只有第一次进入游戏,玩家可自己设置) **/
	static public void onSetPnameByHttp(TcpChannel chn, String unqid,
			String newName, ReturnStatus ret) throws Exception {
		Player pl = PlayerEntity.getByUnqid(unqid);
		if (pl == null) {
			resetReturnStatus(ret, ResultStatus.R_Error, "系统错误! ");
			return;
		}

		newName = trim(newName);
		if (newName.length() < 2 || newName.length() > 8) {
			resetReturnStatus(ret, ResultStatus.R_NameLenthError, "角色名长度错误! ");
			return;
		}

		Player pl2 = PlayerEntity.getByPname(newName);
		if (pl2 != null) {
			resetReturnStatus(ret, ResultStatus.R_NameIsHas, "角色名已存在! ");
			return;
		}

		pl.setPname(newName);
		PlayerEntity.update(pl);
	}

	/*** 发送聊天 **/
	static public void onSendChatByHttp(TcpChannel chn, String unqid,
			String cont, NChats nchats, ReturnStatus ret) throws Exception {
		Player pl = PlayerEntity.getByUnqid(unqid);
		if (pl == null) {
			resetReturnStatus(ret, ResultStatus.R_Error, "系统错误! ");
			System.out.println(" cuowu");
			return;
		}
		ProChats.setNChat2List(pl, cont);
		List<NChat> list = ProChats.getChats(pl);
		nchats.setNChats(list);
		// nchats.list = list;
	}

	/*** 取得聊天内容集合 **/
	static public void onGetChatsByHttp(TcpChannel chn, String unqid,
			NChats nchats, ReturnStatus ret) throws Exception {
		Player pl = getPl(unqid, "", "", "", "");
		if (pl == null) {
			resetReturnStatus(ret, ResultStatus.R_Error, "系统错误! ");
			System.out.println("错误! ");
			return;
		}

		List<NChat> list = ProChats.getChats(pl);
		nchats.setNChats(list);
		System.out.println("nchats = " + nchats);
	}

	/*** 同步到服务器 **/
	static public void onSync2Game(TcpChannel chn, String unqid, String uuid,
			byte[] btPl, byte[] btHero, byte[] btPart, byte[] btProp,
			byte[] btNpc, byte[] btEmail, String chnStr, String chnSub,
			int fight4hero, int fight4part, int npcStars, ReturnStatus ret)
			throws Exception {
		Player pl = getPl(unqid, uuid, "", chnStr, chnSub);
		if (pl == null) {
			return;
		}

		if (!ByteEx.isEmpty(btHero)) {
			pl.setBtHero(btHero);
		}
		if (!ByteEx.isEmpty(btNpc)) {
			pl.setBtNpc(btNpc);
		}
		if (!ByteEx.isEmpty(btPart)) {
			pl.setBtPart(btPart);
		}
		if (!ByteEx.isEmpty(btPl)) {
			pl.setBtPl(btPl);
		}
		if (!ByteEx.isEmpty(btProp)) {
			pl.setBtProp(btProp);
		}
		// 取消邮件同步
		// if (!ByteEx.isEmpty(btEmail)) {
		// pl.setBtEmail(btEmail);
		// }

		Date lasttime = DateEx.nowDate();
		pl.setLasttime(lasttime);

		NewMap map = B2Helper.toMap(btPl);
		if (!MapEx.isEmpty(map)) {
			byte[] btsAllSword = MapEx.getByteArray(map, "allSword");
			if (!ByteEx.isEmpty(btsAllSword)) {
				ByteInStream btSword = ByteInStream.create(btsAllSword);
				int allSword = B2InputStream.readInt(btSword);
				pl.setSword(allSword);
			}

			byte[] btsMaxLayerid = MapEx.getByteArray(map, "maxLayerid");
			if (!ByteEx.isEmpty(btsMaxLayerid)) {
				ByteInStream btWheel = ByteInStream.create(btsMaxLayerid);
				int maxLayerid = B2InputStream.readInt(btWheel);
				pl.setWheel(maxLayerid);
			}

			byte[] bts4ScoreEndless = MapEx.getByteArray(map, "score4Endless");
			if (!ByteEx.isEmpty(bts4ScoreEndless)) {
				ByteInStream btEndless = ByteInStream.create(bts4ScoreEndless);
				int score4Endless = B2InputStream.readInt(btEndless);
				pl.setScore4Endless(score4Endless);
			}
		}

		pl.setFight4hero(fight4hero);
		pl.setFight4part(fight4part);
		pl.setNpcStars(npcStars);
		
		pl.update();
	}

	/*** 从服务器同步到本地 **/
	static public void onSync2Local(TcpChannel chn, String unqid, String uuid,
			NBytes nbtPl, NBytes nbtHero, NBytes nbtPart, NBytes nbtProp,
			NBytes nbtNpc, NBytes nbtEmail, ReturnStatus ret) throws Exception {
		Player pl = getPl(unqid, uuid, "", "", "");
		nbtHero.buff = pl.getBtHero();
		nbtNpc.buff = pl.getBtNpc();
		nbtPart.buff = pl.getBtPart();
		nbtPl.buff = pl.getBtPl();
		nbtProp.buff = pl.getBtProp();
		nbtEmail.buff = pl.getBtEmail();
	}

	/*** 记录错误信息 **/
	static public void onError4Lua(TcpChannel chn, String uuid, String device,
			String error, ReturnStatus ret) throws Exception {

		// 排除一些错误日志
		String outPc = "Object reference not set to an instance of an object";
		int index = error.indexOf(outPc);
		// 不给记录数据了
		index = 0;
		if (index >= 0) {
			return;
		}

		Date createtime = DateEx.nowDate();
		Recode4error record = Recode4error.newRecode4error(0l, uuid, device,
				error, createtime);
		record.insert();
	}

	/*** 验证时间 timeStr:yyyyMMdd **/
	static public void onVerifySytTime(TcpChannel chn, String timeStr,
			NBool nbl, ReturnStatus ret) throws Exception {
		String nowStr = DateEx.nowStr5();
		boolean isSame = StrEx.isSame(timeStr, nowStr);
		nbl.val = isSame;
	}

	/*** 国庆7天签到的验证 **/
	static public void onVerifySign4GuoQin(TcpChannel chn, int dayIn,
			NBool nbl, ReturnStatus ret) throws Exception {

		Date now_date = DateEx.nowDate();
		int yyyy = DateEx.year(now_date);
		String beg = PStr.str(yyyy, "-10-01");
		String end = PStr.str(yyyy, "-10-08");
		String cur = PStr.str(yyyy, "-10-", (dayIn < 10 ? "0" + dayIn : dayIn));

		nbl.val = false;

		if (StrEx.isNotBefore(cur, beg) && StrEx.isBefore(cur, end)) {
			int day = DateEx.day(now_date);
			if (day >= dayIn) {
				boolean isAfterBeg = DateEx.isNotBefore(now_date, beg,
						DateEx.fmt_yyyy_MM_dd);
				boolean isBeforeEnd = DateEx.isBefore(now_date, end,
						DateEx.fmt_yyyy_MM_dd);
				if (isAfterBeg && isBeforeEnd) {
					nbl.val = true;
				}
			}
		}
	}

	// ===================== 方法封装 ==========================

	/*** 重新设置返回状态 **/
	static public void resetReturnStatus(ReturnStatus ret, int code, String msg) {
		if (ret == null)
			return;
		if (msg == null)
			msg = "";
		ret.succ = code;
		ret.msg = msg;
		if (!StrEx.isEmpty(msg)) {
			System.out.println("gate tcp msg:" + msg);
		}
	}
}
