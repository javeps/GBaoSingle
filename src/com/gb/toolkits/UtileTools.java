package com.gb.toolkits;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.json.MyJson;
import com.bowlong.lang.NumEx;
import com.bowlong.objpool.StringBufPool;
import com.bowlong.util.CalendarEx;
import com.bowlong.util.DateEx;
import com.gb.content.Svc;

@SuppressWarnings({ "rawtypes" })
public class UtileTools extends Svc implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(UtileTools.class);

	// 导出异常
	public static String ex2s(Exception e) {
		return e2s(e);
	}

	// str转换 map
	public static Map strToMap(String strVal) {
		Map map_ = MyJson.toMap(strVal);
		return map_;
	}

	// map转换str
	public static String mapToStr(Map map) {
		String r = "";
		try {
			r = MyJson.formatString(map);
		} catch (IOException e) {
			log.info(ex2s(e));
		}
		return r;
	}

	// str转换list
	public static List strToList(String strVal) {
		List list_ = MyJson.toList(strVal);
		return list_;
	}

	// list转换str
	public static String listToStr(List list) {
		String r = "";
		try {
			r = MyJson.formatString(list);
		} catch (IOException e) {
			log.info(ex2s(e));
		}
		return r;
	}

	// 随机数 不包含max
	public static int randIntNotContain(int maxCont) {
		return randInt(0, maxCont);
	}

	// 随机数 包含max
	public static int randIntContain(int maxCont) {
		return randIntContainMax(0, maxCont);
	}

	// 随机数 包含min max
	public static int randIntContainMax(int min, int maxCont) {
		maxCont++;
		return randInt(min, maxCont);
	}

	// 随机数 包含min,不保护max
	public static int randInt(int min, int max) {
		if (max <= min) {
			return min;
		}
		int r = NumEx.nextInt(min, max);
		return r;
	}

	// 随机数 1000
	public static int randIntK() {
		return randIntContainMax(1, 1000);
	}

	// 随机数 10000
	public static int randIntW() {
		return randIntContainMax(1, 10000);
	}

	// 返回时间字符串 20130201
	public static String getStrYYYYMMDDBy(int yyyy, int mm, int dd) {
		StringBuffer sb = StringBufPool.borrowObject();
		sb.append(yyyy);
		if (mm < 10)
			sb.append("0");
		sb.append(mm);
		if (dd < 10)
			sb.append("0");
		sb.append(dd);
		String r = sb.toString();
		StringBufPool.returnObject(sb);
		return r;
	}

	public static String getStrYYYYMMDDBy(Date date) {
		int yyyy = DateEx.year(date);
		int mm = DateEx.month(date);
		int day = DateEx.day(date);
		return getStrYYYYMMDDBy(yyyy, mm, day);
	}

	public static String getStrYYYYMMDDBy(long timeLong) {
		Date date = new Date(timeLong);
		int yyyy = DateEx.year(date);
		int mm = DateEx.month(date);
		int day = DateEx.day(date);
		return getStrYYYYMMDDBy(yyyy, mm, day);
	}

	public static List<String> getDelWeekTimerList(Date date) {
		List<String> r = getDelTimerList(date, 7);
		return r;
	}

	public static List<String> getDelTimerList(Date date, int dayDiff) {
		List<String> r = getDiffTimerListByDay(date, dayDiff);
		return r;
	}

	// 格式是20120203
	public static List<String> getDiffTimerListByDay(Date date, int diffDayNum) {
		if (date == null)
			date = new Date();

		List<String> r = new ArrayList<String>();
		int yyyy = DateEx.year(date);
		int mm = DateEx.month(date);
		int day = DateEx.day(date);

		int diffDay = day - diffDayNum;
		if (diffDay <= 0) {
			int premm = mm - 1;
			if (premm < 1) {
				yyyy -= 1;
				premm = 12;
			}
			int maxPreDay = CalendarEx.dayNum(yyyy, premm - 1);
			int remainDay = maxPreDay + diffDay;
			for (int i = 1; i <= remainDay; i++) {
				String val = getStrYYYYMMDDBy(yyyy, premm, i);
				r.add(val);
			}
		} else {
			for (int i = 1; i <= diffDay; i++) {
				String val = getStrYYYYMMDDBy(yyyy, mm, i);
				r.add(val);
			}
		}
		return r;
	}
}
