package com.gb.timer;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;

import com.bowlong.util.DateEx;
import com.gb.content.Svc;
import com.gb.toolkits.UtileTools;

/**
 * 定时保存数据
 * 
 * @author Administrator
 * 
 */
public class TimerSave extends Svc implements Runnable {

	static Log log = getLog(TimerSave.class);

	static ScheduledExecutorService SES = newScheduledThreadPool("TimerSave.Gbo", 2);

	static TimerSave _self;

	private TimerSave() {
	}

	public static TimerSave getInstance() {
		if (_self == null) {
			_self = new TimerSave();
		}
		return _self;
	}

	static public void startTimer() {
		TimerSave t = getInstance();
		// 定时执行
		long Time_Day = DateEx.TIME_DAY;
		long Time_Hour = DateEx.TIME_HOUR;
		long Time_Minute = DateEx.TIME_MINUTE;
		long Time_Second = DateEx.TIME_SECOND;

		Date now_date = new Date();

		int h = DateEx.hour(now_date);
		int m = DateEx.minute(now_date);
		int s = DateEx.second(now_date);

		long curTime = h * Time_Hour + m * Time_Minute + s * Time_Second;

		long nextH = 0 * Time_Day + 0 * Time_Hour + 10 * Time_Second;

		long initialDelay = nextH - curTime;
		long delay = nextH;

		while (initialDelay <= 0) {
			initialDelay += delay;
		}

		scheduled8FixedRate(SES, t, initialDelay, delay);
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
		System.out.println("==== save now ======");
//		DbSave.saveAll();
	}
}
