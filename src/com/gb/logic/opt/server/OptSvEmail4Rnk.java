package com.gb.logic.opt.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.bowlong.lang.NumEx;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.gb.db.bean.Email;
import com.gb.db.bean.Email4rnk;
import com.gb.db.bean.Player;
import com.gb.db.bean.Rankscore;
import com.gb.db.entity.Email4rnkEntity;
import com.gb.db.entity.PlayerEntity;
import com.gb.db.entity.RankscoreEntity;

public class OptSvEmail4Rnk {

	static public String dataStr4CreatTable = "20150831";// 20150831
	static public String dataStr4GetTable = "20150831";// 20150831
	static public int statusActivity = 0;
	static String conStr = "恭喜您在无尽模式中获得${1}名！这里的超级大奖是属于您的，奖励内容:\n";
	static public boolean isCanSend = false;

	// 复活石
	static public int prop_fuhuo = 141;
	// 合体石
	static public int prop_heti = 139;
	// 血瓶
	static public int prop_hp = 1;
	// 蓝瓶
	static public int prop_mp = 6;

	static public final int TypeGet_Gold = 1;
	static public final int TypeGet_Gems = 2;
	static public final int TypeGet_Prop = 3;
	static public final int TypeGet_Part = 4;
	static public final int TypeGet_Hero = 5;
	static public final int TypeGet_Fashion = 6;

	/*** time:yyyyMMdd **/
	static public void setDataStr4Create(String time) {
		if (StrEx.isEmptyTrim(time)) {
			return;
		}

		boolean isTimeComapar = StrEx.isSame(time, dataStr4CreatTable);
		if (isTimeComapar)
			return;

		String nowStr = DateEx.nowStrYMD();
		isTimeComapar = StrEx.isBefore(time, nowStr);
		if (isTimeComapar)
			return;

		statusActivity = 1;
		isCanSend = false;
		dataStr4CreatTable = time;
		dataStr4GetTable = time;
	}

	/*** time:yyyyMMdd 取得排行榜 **/
	static public void setDataStr4Get(String time, boolean isSend) {
		isCanSend = isSend;
		if (StrEx.isEmptyTrim(time)) {
			return;
		}

		boolean isSame = StrEx.isSame(time, dataStr4GetTable);
		if (isSame)
			return;

		if (!DateEx.isTime(time, DateEx.fmt_yyyyMMdd))
			return;
		dataStr4GetTable = time;
	}

	/*
	 * 奖励[{tpGet,tpId,tpVal}] [string] (道具, 时装是道具ID, 主角/小伙伴是GID)
	 * tpGet[1:gold,2:gems,3:prop,4:partner,5:hero,6:hero_fashion]
	 * tpId[prop:xlsId,role(part,hero):GID,hero_fashion:prop_xls_id]
	 */

	static Email4rnk makeEmail4Top1() {
		String title = "无尽模式排名奖励";
		String content = conStr + "梨花诗，复活*80，果宝大合体*80，蓝瓶*80，钻*1000";
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> award1 = new HashMap<String, Object>();
		award1.put("tpGet", TypeGet_Hero);
		award1.put("tpId", 79);
		award1.put("tpVal", 1);
		list.add(award1);

		Map<String, Object> award2 = new HashMap<String, Object>();
		award2.put("tpGet", TypeGet_Prop);
		award2.put("tpId", prop_fuhuo);
		award2.put("tpVal", 80);
		list.add(award2);

		Map<String, Object> award3 = new HashMap<String, Object>();
		award3.put("tpGet", TypeGet_Prop);
		award3.put("tpId", prop_mp);
		award3.put("tpVal", 80);
		list.add(award3);

		Map<String, Object> award4 = new HashMap<String, Object>();
		award4.put("tpGet", TypeGet_Gems);
		award4.put("tpId", 0);
		award4.put("tpVal", 1000);
		list.add(award4);

		String awardJson = JSON.toJSONString(list);
		long creattime = DateEx.now();
		long validtime = creattime + DateEx.TIME_DAY * 30;
		return Email4rnk.newEmail4rnk(0, 1, 1, title, content, awardJson,
				creattime, validtime);
	}

	static Email4rnk makeEmail4Top2() {
		String title = "无尽模式排名奖励";
		String content = conStr + "复活*80，果宝大合体*80，蓝瓶*80，钻*500";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> award1 = new HashMap<String, Object>();
		award1.put("tpGet", TypeGet_Prop);
		award1.put("tpId", prop_heti);
		award1.put("tpVal", 80);
		list.add(award1);

		Map<String, Object> award2 = new HashMap<String, Object>();
		award2.put("tpGet", TypeGet_Prop);
		award2.put("tpId", prop_fuhuo);
		award2.put("tpVal", 80);
		list.add(award2);

		Map<String, Object> award3 = new HashMap<String, Object>();
		award3.put("tpGet", TypeGet_Prop);
		award3.put("tpId", prop_mp);
		award3.put("tpVal", 80);
		list.add(award3);

		Map<String, Object> award4 = new HashMap<String, Object>();
		award4.put("tpGet", TypeGet_Gems);
		award4.put("tpId", 0);
		award4.put("tpVal", 500);
		list.add(award4);

		String awardJson = JSON.toJSONString(list);

		long creattime = DateEx.now();
		long validtime = creattime + DateEx.TIME_DAY * 30;
		return Email4rnk.newEmail4rnk(0, 2, 2, title, content, awardJson,
				creattime, validtime);
	}

	static Email4rnk makeEmail4Top3() {
		String title = "无尽模式排名奖励";
		String content = conStr + "复活*50，果宝大合体*50，蓝瓶*50，钻*300";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> award1 = new HashMap<String, Object>();
		award1.put("tpGet", TypeGet_Prop);
		award1.put("tpId", prop_heti);
		award1.put("tpVal", 50);
		list.add(award1);

		Map<String, Object> award2 = new HashMap<String, Object>();
		award2.put("tpGet", TypeGet_Prop);
		award2.put("tpId", prop_fuhuo);
		award2.put("tpVal", 50);
		list.add(award2);

		Map<String, Object> award3 = new HashMap<String, Object>();
		award3.put("tpGet", TypeGet_Prop);
		award3.put("tpId", prop_mp);
		award3.put("tpVal", 50);
		list.add(award3);

		Map<String, Object> award4 = new HashMap<String, Object>();
		award4.put("tpGet", TypeGet_Gems);
		award4.put("tpId", 0);
		award4.put("tpVal", 300);
		list.add(award4);

		String awardJson = JSON.toJSONString(list);

		long creattime = DateEx.now();
		long validtime = creattime + DateEx.TIME_DAY * 30;
		return Email4rnk.newEmail4rnk(0, 3, 3, title, content, awardJson,
				creattime, validtime);
	}

	static Email4rnk makeEmail4Top4_10() {
		String title = "无尽模式排名奖励";
		String content = conStr + "复活*30，果宝大合体*30，蓝瓶*30，钻*100";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> award1 = new HashMap<String, Object>();
		award1.put("tpGet", TypeGet_Prop);
		award1.put("tpId", prop_heti);
		award1.put("tpVal", 30);
		list.add(award1);

		Map<String, Object> award2 = new HashMap<String, Object>();
		award2.put("tpGet", TypeGet_Prop);
		award2.put("tpId", prop_fuhuo);
		award2.put("tpVal", 30);
		list.add(award2);

		Map<String, Object> award3 = new HashMap<String, Object>();
		award3.put("tpGet", TypeGet_Prop);
		award3.put("tpId", prop_mp);
		award3.put("tpVal", 30);
		list.add(award3);

		Map<String, Object> award4 = new HashMap<String, Object>();
		award4.put("tpGet", TypeGet_Gems);
		award4.put("tpId", 0);
		award4.put("tpVal", 100);
		list.add(award4);

		String awardJson = JSON.toJSONString(list);

		long creattime = DateEx.now();
		long validtime = creattime + DateEx.TIME_DAY * 30;
		return Email4rnk.newEmail4rnk(0, 4, 10, title, content, awardJson,
				creattime, validtime);
	}

	static Email4rnk makeEmail4Top11_20() {
		String title = "无尽模式排名奖励";
		String content = conStr + "复活*15，果宝大合体*15，蓝瓶*15";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> award1 = new HashMap<String, Object>();
		award1.put("tpGet", TypeGet_Prop);
		award1.put("tpId", prop_heti);
		award1.put("tpVal", 15);
		list.add(award1);

		Map<String, Object> award2 = new HashMap<String, Object>();
		award2.put("tpGet", TypeGet_Prop);
		award2.put("tpId", prop_fuhuo);
		award2.put("tpVal", 15);
		list.add(award2);

		Map<String, Object> award3 = new HashMap<String, Object>();
		award3.put("tpGet", TypeGet_Prop);
		award3.put("tpId", prop_mp);
		award3.put("tpVal", 15);
		list.add(award3);

		String awardJson = JSON.toJSONString(list);

		long creattime = DateEx.now();
		long validtime = creattime + DateEx.TIME_DAY * 30;
		return Email4rnk.newEmail4rnk(0, 11, 20, title, content, awardJson,
				creattime, validtime);
	}

	static Email4rnk makeEmail4Top21_50() {
		String title = "无尽模式排名奖励";
		String content = conStr + "复活*10，果宝大合体*10，蓝瓶*10";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> award1 = new HashMap<String, Object>();
		award1.put("tpGet", TypeGet_Prop);
		award1.put("tpId", prop_heti);
		award1.put("tpVal", 10);
		list.add(award1);

		Map<String, Object> award2 = new HashMap<String, Object>();
		award2.put("tpGet", TypeGet_Prop);
		award2.put("tpId", prop_fuhuo);
		award2.put("tpVal", 10);
		list.add(award2);

		Map<String, Object> award3 = new HashMap<String, Object>();
		award3.put("tpGet", TypeGet_Prop);
		award3.put("tpId", prop_mp);
		award3.put("tpVal", 10);
		list.add(award3);

		String awardJson = JSON.toJSONString(list);

		long creattime = DateEx.now();
		long validtime = creattime + DateEx.TIME_DAY * 30;
		return Email4rnk.newEmail4rnk(0, 21, 50, title, content, awardJson,
				creattime, validtime);
	}

	static Email4rnk makeEmail4Top51_100() {
		String title = "无尽模式排名奖励";
		String content = conStr + "复活*5，果宝大合体*5，蓝瓶*5";

		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

		Map<String, Object> award1 = new HashMap<String, Object>();
		award1.put("tpGet", TypeGet_Prop);
		award1.put("tpId", prop_heti);
		award1.put("tpVal", 5);
		list.add(award1);

		Map<String, Object> award2 = new HashMap<String, Object>();
		award2.put("tpGet", TypeGet_Prop);
		award2.put("tpId", prop_fuhuo);
		award2.put("tpVal", 5);
		list.add(award2);

		Map<String, Object> award3 = new HashMap<String, Object>();
		award3.put("tpGet", TypeGet_Prop);
		award3.put("tpId", prop_mp);
		award3.put("tpVal", 5);
		list.add(award3);

		String awardJson = JSON.toJSONString(list);

		long creattime = DateEx.now();
		long validtime = creattime + DateEx.TIME_DAY * 30;
		return Email4rnk.newEmail4rnk(0, 51, 100, title, content, awardJson,
				creattime, validtime);
	}

	static public void createInserts() {
		if (statusActivity == 0) {
			statusActivity = 1;
		}

		if (statusActivity != 1)
			return;
		String nowStr = DateEx.nowStrYMD();
		if (StrEx.isBefore(nowStr, dataStr4CreatTable)) {
			return;
		}

		List<Email4rnk> list = new ArrayList<Email4rnk>();
		Email4rnk en1 = makeEmail4Top1();
		Email4rnk en2 = makeEmail4Top2();
		Email4rnk en3 = makeEmail4Top3();
		Email4rnk en4 = makeEmail4Top4_10();
		Email4rnk en5 = makeEmail4Top11_20();
		Email4rnk en6 = makeEmail4Top21_50();
		Email4rnk en7 = makeEmail4Top51_100();
		list.add(en1);
		list.add(en2);
		list.add(en3);
		list.add(en4);
		list.add(en5);
		list.add(en6);
		list.add(en7);
		Email4rnkEntity.insertLog(dataStr4CreatTable, list);
		statusActivity = 2;
	}

	static public Email4rnk getEmail4rnk(int beg, int end) {
		if (!isCanSend) {
			return null;
		}
		return Email4rnkEntity.getEnity(dataStr4GetTable, beg, end);
	}

	static public Email getEmailBy(Player pl) {
		if (pl == null)
			return null;
		int statusActivity = pl.getStatusActivity();
		if (statusActivity != 0) {
			return null;
		}
		String nowStr = DateEx.nowStrYMD();
		if (StrEx.isBefore(nowStr, dataStr4GetTable)) {
			return null;
		}

		Rankscore en = RankscoreEntity.getEnBy(pl.getUnqid(), dataStr4GetTable);
		if (en == null)
			return null;
		int index = en.getIndexs();

		if (index > 100)
			return null;

		int beg = 0;
		int end = 0;
		if (index == 1) {
			beg = 1;
			end = 1;
		} else if (index == 2) {
			beg = 2;
			end = 2;
		} else if (index == 3) {
			beg = 3;
			end = 3;
		} else if (index <= 10) {
			beg = 4;
			end = 10;
		} else if (index <= 20) {
			beg = 11;
			end = 20;
		} else if (index <= 50) {
			beg = 21;
			end = 50;
		} else {
			beg = 51;
			end = 100;
		}

		Email4rnk rnk = getEmail4rnk(beg, end);
		if (rnk == null)
			return null;
		Email em = Email.newEmail(0, pl.getUnqid(), "", "", "", false, false,
				rnk.getCreattime(), rnk.getValidtime());
		em.setAwardJson(rnk.getAwardJson());
		String cont = rnk.getContent();
		cont = StrEx.fmt(cont, index);
		em.setContent(cont);
		em.setTitle(rnk.getTitle());

		pl.setStatusActivity(1);
		pl.update();

		return em;
	}

	/*** 创建玩家排名邮件 **/
	@SuppressWarnings("rawtypes")
	static public void createEmails4RnkByMap(Map map) {
		if (MapEx.isEmpty(map))
			return;
		Map<String, String> mapKV = MapEx.toMapKV(map);
		for (Entry<String, String> entry : mapKV.entrySet()) {
			String unqid = entry.getKey();
			int index = NumEx.stringToInt(entry.getValue(), 0);
			createEmail4RnkBy(unqid, index);
		}
	}

	/*** 创建玩家排名邮件 **/
	static public void createEmail4RnkBy(String unqid, int index) {
		if (index <= 0 || index > 100)
			return;
		Player pl = PlayerEntity.getByUnqid(unqid);
		if (pl == null)
			return;

		Email4rnk rnk = null;
		if (index == 1) {
			rnk = makeEmail4Top1();
		} else if (index == 2) {
			rnk = makeEmail4Top2();
		} else if (index == 3) {
			rnk = makeEmail4Top3();
		} else if (index <= 10) {
			rnk = makeEmail4Top4_10();
		} else if (index <= 20) {
			rnk = makeEmail4Top11_20();
		} else if (index <= 50) {
			rnk = makeEmail4Top21_50();
		} else {
			rnk = makeEmail4Top51_100();
		}

		if (rnk == null)
			return;

		Email em = Email.newEmail(0, unqid, "", "", "", false, false,
				rnk.getCreattime(), rnk.getValidtime());
		em.setAwardJson(rnk.getAwardJson());
		String cont = rnk.getContent();
		cont = StrEx.fmt(cont, index);
		em.setContent(cont);
		em.setTitle(rnk.getTitle());
		em.insert();
	}

	/*** 创建玩家邮件 **/
	static public void createEmail4Pl(String unqid, String title, String cont,
			String awardJson) {
		long createtime = DateEx.now();
		long validtime = createtime + DateEx.TIME_DAY * 30;
		Email em = Email.newEmail(0, unqid, title, cont, awardJson, false,
				false, createtime, validtime);
		em.insert();
	}
}
