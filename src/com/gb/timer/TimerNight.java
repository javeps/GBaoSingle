package com.gb.timer;

import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;

import com.bowlong.lang.task.ThreadEx;
import com.bowlong.util.DateEx;
import com.gb.content.Svc;
import com.gb.db.entity.RankscoreEntity;
import com.gb.db.entity.RankswordEntity;
import com.gb.logic.opt.server.OptSvEmail4Rnk;
import com.gb.toolkits.UtileTools;

/**
 * 每天定时执行
 * 
 * @author Administrator
 * 
 */
public class TimerNight extends Svc implements Runnable {

	static Log log = getLog(TimerNight.class);

	static public boolean isClearLog = true;

	static ScheduledExecutorService SES = newScheduledThreadPool(
			"TimerNight.Gbo", 2);

	static TimerNight _self;

	private TimerNight() {
	}

	public static TimerNight getInstance() {
		if (_self == null) {
			_self = new TimerNight();
		}
		return _self;
	}

	static public void startTimer() {
		TimerNight t = getInstance();
		int hour = 3;
		int minute = 20;
		int sec = 0;
		scheduledEveryDay(SES, t, hour, minute, sec);
	}
	
	static public void startTimer2() {
		TimerNight t = getInstance();
		int hour = 3;
		int minute = 40;
		int sec = 0;
		scheduledEveryDay(SES, t, hour, minute, sec);
	}

	@Override
	public void run() {
		try {
			exceFun();
		} catch (Exception e) {
			log.info(UtileTools.ex2s(e));
		}
	}

	// ============ 执行函数 ========

	private void exceFun() throws Exception {
		if (isClearLog) {
			RankswordEntity.clearLog4Call();
		}

		RankswordEntity.exceProcessByChn(0, "");
		// RankwheelEntity.exceProcess();
		RankscoreEntity.exceProcess();
	}

	public void exce4Pub(boolean isReRnk, String time, boolean isRnkMail)
			throws Exception {
		if (isReRnk) {
			exceFun();
		}

		if (isRnkMail) {
			boolean isTime = DateEx.isTime(time, "");
			if (isTime) {
				// 执行排行榜奖励
				ThreadEx.Sleep(20000);
				OptSvEmail4Rnk.setDataStr4Create(time);
				OptSvEmail4Rnk.createInserts();
			}
		}
	}

	/***
	 * type[1:score,2sword,3score_sword] <br/>
	 * parsType[1:fight4hero 英雄战斗力,2:fight4part 小伙伴战斗力,其他:sword Pl.sword战斗力] <br/>
	 * chn渠道[空:所有,其他:渠道标识]
	 * **/
	public void exce4Chn(String chn, int type, int parsType) throws Exception {
		boolean isScore = type == 1 || type == 3;
		boolean isSword = type == 2 || type == 3;
		if (isScore) {
			RankscoreEntity.exceProcessByChn(chn);
		}

		if (type == 3) {
			ThreadEx.Sleep(5000);
		}

		if (isSword) {
			RankswordEntity.exceProcessByChn(parsType, chn);
		}
	}
}
