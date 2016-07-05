package com.gb.logic.cache;

import gen_b2g.web_disp.bean.NChat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bowlong.util.DateEx;
import com.bowlong.util.NewCpWrList;
import com.gb.db.bean.Player;

/**
 * 数据库所有聊天信息数据
 * 
 * @author zhangwen
 * 
 */
public class ProChats {

	static NewCpWrList<NChat> listLev = new NewCpWrList<NChat>();
	static Map<String, Long> maptime = new HashMap<String, Long>();
	static int LenChat = 50;

	/*** 设置聊天数据 **/
	static public void setNChat2List(Player pl, String content) {
		long creattime = DateEx.now();
		String ctStr = DateEx.format(creattime, DateEx.fmt_HH_mm_ss);
		NChat nchat = NChat.newNChat(1, pl.getPcid(), pl.getPname(), content,
				creattime, ctStr);
		listLev.add(nchat);
		int len = listLev.size();
		int diff = len - LenChat;
		if (diff > 0) {
			for (int i = 0; i < diff; i++) {
				listLev = (NewCpWrList<NChat>) listLev.subList(diff, len);
			}
		}
	}

	/*** 得到聊天数据 **/
	static public List<NChat> getChats(Player pl) {
		List<NChat> result = new ArrayList<NChat>();
		long nowtime = DateEx.now();
		String unqid = pl.getUnqid();
		long lasttime = 0l;
		if (maptime.containsKey(unqid)) {
			lasttime = maptime.get(unqid);
		}

		int len = listLev.size();
		for (int i = 0; i < len; i++) {
			NChat en = listLev.get(i);
			if (en.creattime > lasttime) {
				result.add(en);
			}
		}
		maptime.put(unqid, nowtime);
		return result;
	}
}
