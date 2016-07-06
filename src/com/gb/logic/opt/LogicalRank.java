package com.gb.logic.opt;

import gen_b2g.web_disp.bean.NRank;
import gen_b2g.web_disp.bean.NRanks;
import gen_b2g.web_disp.bean.ReturnStatus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.logging.Log;

import com.bowlong.net.TcpChannel;
import com.bowlong.util.CalendarEx;
import com.bowlong.util.ListEx;
import com.gb.db.bean.Player;
import com.gb.db.bean.Rankscore;
import com.gb.db.bean.Rankstars;
import com.gb.db.bean.Ranksword;
import com.gb.db.bean.Rankwheel;
import com.gb.db.entity.PlayerEntity;
import com.gb.db.entity.RankscoreEntity;
import com.gb.db.entity.RankstarsEntity;
import com.gb.db.entity.RankswordEntity;

public class LogicalRank extends Logical {

	static Log log = getLog(LogicalRank.class);

	static NRank transform2RnkSword(NRank to, Ranksword fm) {
		if (fm == null)
			return to;
		if (to == null)
			to = new NRank();
		to.index = fm.getIndexs();
		to.pname = fm.getPname();
		to.sword = fm.getSword();
		to.type = 1;
		to.unqid = fm.getUnqid();
		return to;
	}

	static List<NRank> transform2RnkListSword(List<NRank> to, List<Ranksword> fm) {
		if (ListEx.isEmpty(fm))
			return to;
		if (to == null)
			to = new ArrayList<NRank>();
		int len = fm.size();
		for (int i = 0; i < len; i++) {
			Ranksword enFm = fm.get(i);
			if (enFm == null)
				continue;
			NRank enTo = null;
			enTo = transform2RnkSword(enTo, enFm);
			to.add(enTo);
		}
		return to;
	}

	static void transform2NRnksSword(NRanks to, List<Ranksword> fm) {
		List<NRank> toList = null;
		toList = transform2RnkListSword(toList, fm);
		if (ListEx.isEmpty(toList))
			return;
		to.list = toList;
	}

	static NRank transform2RnkWheel(NRank to, Rankwheel fm) {
		if (fm == null)
			return to;
		if (to == null)
			to = new NRank();
		to.index = fm.getIndexs();
		to.pname = fm.getPname();
		to.wheel = fm.getWheel();
		to.type = 4;
		to.unqid = fm.getUnqid();
		return to;
	}

	static List<NRank> transform2RnkListWheel(List<NRank> to, List<Rankwheel> fm) {
		if (ListEx.isEmpty(fm))
			return to;
		if (to == null)
			to = new ArrayList<NRank>();
		int len = fm.size();
		for (int i = 0; i < len; i++) {
			Rankwheel enFm = fm.get(i);
			if (enFm == null)
				continue;
			NRank enTo = null;
			enTo = transform2RnkWheel(enTo, enFm);
			to.add(enTo);
		}
		return to;
	}

	static void transform2NRnksWheel(NRanks to, List<Rankwheel> fm) {
		List<NRank> toList = null;
		toList = transform2RnkListWheel(toList, fm);
		if (ListEx.isEmpty(toList))
			return;
		to.list = toList;
	}

	static NRank transform2RnkScore(NRank to, Rankscore fm) {
		if (fm == null)
			return to;
		if (to == null)
			to = new NRank();
		to.index = fm.getIndexs();
		to.pname = fm.getPname();
		to.wheel = fm.getScore();
		to.type = 2;
		to.unqid = fm.getUnqid();
		return to;
	}

	static List<NRank> transform2RnkListScore(List<NRank> to, List<Rankscore> fm) {
		if (ListEx.isEmpty(fm))
			return to;
		if (to == null)
			to = new ArrayList<NRank>();
		int len = fm.size();
		for (int i = 0; i < len; i++) {
			Rankscore enFm = fm.get(i);
			if (enFm == null)
				continue;
			NRank enTo = null;
			enTo = transform2RnkScore(enTo, enFm);
			to.add(enTo);
		}
		return to;
	}

	static void transform2NRnksScore(NRanks to, List<Rankscore> fm) {
		List<NRank> toList = null;
		toList = transform2RnkListScore(toList, fm);
		if (ListEx.isEmpty(toList))
			return;
		to.list = toList;
	}

	static NRank transform2RnkStars(NRank to, Rankstars fm) {
		if (fm == null)
			return to;
		if (to == null)
			to = new NRank();
		to.index = fm.getIndexs();
		to.pname = fm.getPname();
		to.wheel = fm.getStars();
		to.type = 3;
		to.unqid = fm.getUnqid();
		return to;
	}

	static List<NRank> transform2RnkListStars(List<NRank> to, List<Rankstars> fm) {
		if (ListEx.isEmpty(fm))
			return to;
		if (to == null)
			to = new ArrayList<NRank>();
		int len = fm.size();
		for (int i = 0; i < len; i++) {
			Rankstars enFm = fm.get(i);
			if (enFm == null)
				continue;
			NRank enTo = null;
			enTo = transform2RnkStars(enTo, enFm);
			to.add(enTo);
		}
		return to;
	}

	static void transform2NRnksStars(NRanks to, List<Rankstars> fm) {
		List<NRank> toList = null;
		toList = transform2RnkListStars(toList, fm);
		if (ListEx.isEmpty(toList))
			return;
		to.list = toList;
	}

	static String getRnkDateStr() {
		// 定时执行排行时间(凌晨4点)
		int orderHour = 4;
		Calendar calendar = CalendarEx.nowCalendar();
		int curHour = CalendarEx.hour(calendar);
		if (curHour < orderHour) {
			calendar = CalendarEx.addDay(calendar, -1);
		}
		return CalendarEx.format(calendar, CalendarEx.fmt_yyyyMMdd);
	}

	static public void onGetNRanks(TcpChannel chn, String unqid, int type,
			NRank nrnkSelf, NRanks nrnks, ReturnStatus ret) throws Exception {
		String dateStr = getRnkDateStr();
		Player pl = PlayerEntity.getByUnqid(unqid);
		if (pl != null) {
			switch (type) {
			case 3:
				Rankstars enStar = RankstarsEntity.getEnBy(unqid, dateStr);
				transform2RnkStars(nrnkSelf, enStar);
				break;
			case 2:
				Rankscore en1 = RankscoreEntity.getEnBy(unqid, dateStr);
				transform2RnkScore(nrnkSelf, en1);
				break;
			default:
				Ranksword en = RankswordEntity.getEnBy(unqid, dateStr);
				transform2RnkSword(nrnkSelf, en);
				break;
			}
		}

		switch (type) {
		case 3:
			List<Rankstars> list3 = RankstarsEntity.getListBy(1, 100, dateStr);
			transform2NRnksStars(nrnks, list3);
			break;
		case 2:
			List<Rankscore> list2 = RankscoreEntity.getListBy(1, 100, dateStr);
			transform2NRnksScore(nrnks, list2);
			break;
		default:
			List<Ranksword> list1 = RankswordEntity.getListBy(1, 100, dateStr);
			transform2NRnksSword(nrnks, list1);
			break;
		}
	}
}
