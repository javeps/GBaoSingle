package com.gb;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.bowlong.io.FileRw;
import com.bowlong.lang.NumFmtEx;
import com.bowlong.lang.RndEx;
import com.bowlong.net.http.urlcon.HttpUrlConEx;
import com.bowlong.text.EncodingEx;
import com.bowlong.third.FastJSON;
import com.bowlong.tool.TkitValidateCheck;
import com.bowlong.util.CalendarEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.gb.content.Svc;
import com.gb.logic.opt.server.IAPIOSRecharge;

@SuppressWarnings({ "rawtypes" })
public class DemoGBaoSng {
	public static void main(String args[]) throws Exception {
		// String v = HttpReqWeb.getSpeed("www.baidu.com","gbk");
		// System.out.println(v);
		// testTimeStr();
		// testTimes();
		// testLimitTime();
		// testTimeByLong();
		// testMapList();
		// testDate();
		// testIsNum();
		// sendJson();
		// sendJsonByRead();
		// sendEmail();
		// testIosIAP();
		testIosIAP2();
	}

	static void test() {
		String[] addrs = { "www.baidu.com" };
		if (addrs.length < 1) {
			System.out.println("syntax Error!");
		} else {
			for (int i = 0; i < addrs.length; i++) {
				String line = null;
				try {
					Process pro = Runtime.getRuntime().exec(
							"ping " + addrs[i] + " -l 1000 -n 4");

					InputStream inStream = pro.getInputStream();
					BufferedReader buf = new BufferedReader(
							new InputStreamReader(inStream, "gbk"));
					int len = 0;
					String vEn = "Average";
					String vCn = "平均";
					while ((line = buf.readLine()) != null) {
						int position = line.indexOf(vEn);
						if (position == -1) {
							position = line.indexOf(vCn);
							len = vCn.length();
						} else {
							len = vEn.length();
						}
						if (position != -1) {
							System.out.println(line);
							String value = line.substring(position + len,
									line.lastIndexOf("ms"));
							value = value.replaceAll("=", "");
							value = value.trim();
							double speed = (1000d / Integer.parseInt(value));
							String v = NumFmtEx.formatDouble(speed);
							System.out.println("下载速度:" + v + "KB");
						}
					}
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		}
	}

	static void testTimeStr() {
		String bg = "10:30";
		int duration = 60;
		Calendar cal = CalendarEx.parse2Cal(bg, CalendarEx.fmt_HH_mm);
		cal = CalendarEx.addMinute(cal, duration);
		int h = CalendarEx.hour(cal);
		int m = CalendarEx.minute(cal);
		int s = CalendarEx.second(cal);
		StringBuffer buf = new StringBuffer();
		buf.append(h < 10 ? "0" + h : h).append(":");
		buf.append(m < 10 ? "0" + m : m).append(":");
		buf.append(s < 10 ? "0" + s : s);
		String ed = buf.toString();
		String ed2 = CalendarEx.format(cal, CalendarEx.fmt_HH_mm_ss);
		System.out.println("bg = " + bg);
		System.out.println("ed = " + ed);
		System.out.println("ed2 = " + ed2);
		System.out.println("====== 当前时间与开始，结束时间之间的比较 =======");
		String v2 = CalendarEx.nowStr_Hms();
		System.out.println("v2 = " + v2);
		System.out.println(Svc.compareTo(v2, bg));
		System.out.println(Svc.compareTo(v2, ed));
		System.out.println();

		System.out.println("====== 固定时间与开始，结束时间之间的比较  =======");
		String v3 = "12:59:00";
		System.out.println("v3 = " + v3);
		System.out.println(Svc.compareTo(v3, bg));
		System.out.println(Svc.compareTo(v3, ed));
	}

	static void testTimes() {
		Date d = DateEx.nowDate();
		Calendar cal = CalendarEx.parse2Cal(d);
		testDate(d);
		testCal(cal);
	}

	static void testDate(Date d) {
		int day = DateEx.day(d);
		System.out.println("today: " + day);
		int dayInMonth = DateEx.dayInMonth(d);
		int dayInYear = DateEx.dayInYear(d);
		int hour = DateEx.hour(d);
		int minute = DateEx.minute(d);
		int month = DateEx.month(d);
		int ms = DateEx.ms(d);
		int second = DateEx.second(d);
		int week = DateEx.week(d);
		int weekInMonth = DateEx.weekInMonth(d);
		int weekInYear = DateEx.weekInYear(d);
		int year = DateEx.year(d);
		System.out.println("==== data =====");
		System.out.println("day = " + day);
		System.out.println("dayInMonth = " + dayInMonth);
		System.out.println("dayInYear = " + dayInYear);
		System.out.println("hour = " + hour);
		System.out.println("minute = " + minute);
		System.out.println("month = " + month);
		System.out.println("ms = " + ms);
		System.out.println("second = " + second);
		System.out.println("week = " + week);
		System.out.println("weekInMonth = " + weekInMonth);
		System.out.println("weekInYear = " + weekInYear);
		System.out.println("year = " + year);
	}

	static void testCal(Calendar d) {
		int day = CalendarEx.day(d);
		int dayInMonth = CalendarEx.dayInMonth(d);
		int dayInYear = CalendarEx.dayInYear(d);
		int hour = CalendarEx.hour(d);
		int minute = CalendarEx.minute(d);
		int month = CalendarEx.month(d);
		int ms = CalendarEx.ms(d);
		int second = CalendarEx.second(d);
		int week = CalendarEx.week(d);
		int weekInMonth = CalendarEx.weekInMonth(d);
		int weekInYear = CalendarEx.weekInYear(d);
		int year = CalendarEx.year(d);
		int dayNumMonth = CalendarEx.dayNum(year, month);
		int dayNumYear = CalendarEx.dayNum(year);
		System.out.println("==== calendar =====");
		System.out.println("day = " + day);
		System.out.println("dayInMonth = " + dayInMonth);
		System.out.println("dayInYear = " + dayInYear);
		System.out.println("hour = " + hour);
		System.out.println("minute = " + minute);
		System.out.println("month = " + month);
		System.out.println("ms = " + ms);
		System.out.println("second = " + second);
		System.out.println("week = " + week);
		System.out.println("weekInMonth = " + weekInMonth);
		System.out.println("weekInYear = " + weekInYear);
		System.out.println("year = " + year);
		System.out.println("dayNumMonth = " + dayNumMonth);
		System.out.println("dayNumYear = " + dayNumYear);
	}

	static boolean testCompareTo(String bg, int duration) {
		System.out.println("====== bg ======" + bg);
		Calendar cal = CalendarEx.parse2Cal(bg, CalendarEx.fmt_HH_mm);
		cal = CalendarEx.addMinute(cal, duration);
		String ed = CalendarEx.format(cal, CalendarEx.fmt_HH_mm_ss);
		String nt = CalendarEx.nowStr_Hms();
		int vBg = Svc.compareTo(nt, bg);
		int vEd = Svc.compareTo(nt, ed);
		if (vBg > 0 && vEd < 0) {
			System.out.println("======= 活动进行中 ===== 开始时间  ==" + bg);
			return true;
		}
		return false;
	}

	static void testTimeByLong() {
		// 1419328713258,1419332373258,1419325180743
		long t = 0l;
		Calendar v = null;
		t = 1419307113258l;
		v = CalendarEx.parse2Cal(t);
		System.out.println("开始时间:" + CalendarEx.format_YMDHms(v));

		t = 1419310773258l;
		v = CalendarEx.parse2Cal(t);
		System.out.println("结束时间:" + CalendarEx.format_YMDHms(v));

		t = 1419325179585l;
		v = CalendarEx.parse2Cal(t);
		System.out.println("当时时间:" + CalendarEx.format_YMDHms(v));
	}

	static void testMapList() {
		Map map = MapEx.toMapByJson("{\"a\":123}");
		System.out.println(map);
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 50; i++) {
			list.add(i);
		}
		List<Integer> rlist = ListEx.rndListT(list);
		List<Integer> sublist = ListEx.subRndListT(list, 10);
		System.out.println(list);
		System.out.println(rlist);
		System.out.println(sublist);
		System.out.println(ListEx.have(list, 12));
		System.out.println(ListEx.have(list, 100));

		String s = "abc,123 , 测试文件, test123 ,test测试12";
		boolean isTrim = true;
		List<String> tolist = ListEx.toListByComma(s, isTrim);
		for (String str : tolist) {
			System.out.println(str);
		}
	}

	static void testSplit() {
		String idNum = "12,5,24,1,33,6,111,5,124,1,133,6,132,5,254,1,313,6";
		// idNum = "12,5,24,1,33";
		List<Integer> list = ListEx.toListInt(idNum);
		int len = list.size();
		int model = len % 2;
		if (model != 0)
			return;
		for (int i = 0; i < len / 2; i++) {
			int index = i * 2;
			System.out.println("=== id == " + list.get(index));
			System.out.println("=== num == " + list.get(index + 1));
		}
	}

	static void getPath() {
		String path1 = Svc.getAppRoot();
		System.out.println(path1);
		String path2 = DemoGBaoSng.class.getClassLoader().getResource("")
				.getPath();
		System.out.println(path2);
	}

	static void testRnd() {
		int num1 = 1;
		int num2 = 10;
		int rnd = 0;
		for (int i = 0; i < 10; i++) {
			rnd = RndEx.nextInt(num1, num2);
			System.out.print(rnd + " ");
		}
	}

	static void testDate() {
		Date da = new Date(0);
		String str = new SimpleDateFormat("yyyy-MM-dd").format(da);
		System.out.println("now time : " + str);
	}

	static void testIsNum() {
		String v = "201508018";
		System.out.println(TkitValidateCheck.isNumber(v));
	}

	static void sendJson() {
		Map<String, Integer> mapIndex = new HashMap<String, Integer>();
		mapIndex.put("mm_d5b5e73b96ef67ba42562c5594c2904f", 1);
		String url = "http://127.0.0.1:6002/createEmail4RnkByJsonTxt";
		url = "http://h005.ultralisk.cn:6002/createEmail4RnkByJsonTxt";
		Map<String, String> map = new HashMap<String, String>();
		map.put("json", JSON.toJSONString(mapIndex));
		InputStream inStream = HttpUrlConEx.postParams(url, map, "");
		System.out.println(HttpUrlConEx.inps2Str4Stream(inStream, "utf-8"));
	}

	static void sendJsonByRead() {
		String path = "";
		path = "json/reward.json";
		// path = "json/self.json";
		String vStr = FileRw.readStr(path, "UTF-8");
		Map mapJson = FastJSON.parseMap(vStr);
		String url = "";
		url = "http://127.0.0.1:6002/createEmail4RnkByJsonTxt";
		url = "http://h005.ultralisk.cn:6002/createEmail4RnkByJsonTxt";
		Map<String, String> map = new HashMap<String, String>();
		map.put("json", JSON.toJSONString(mapJson));
		InputStream inStream = HttpUrlConEx.postParams(url, map, "");
		System.out.println(HttpUrlConEx.inps2Str4Stream(inStream, "utf-8"));
	}

	static void sendEmail() {
		String url = "";
		url = "http://127.0.0.1:6002/createEmail";
		url = "http://h005.ultralisk.cn:6002/createEmail";
		Map<String, String> map = new HashMap<String, String>();
		map.put("unqid", "ctcc_32708c6597588f5cee71785a9efd448f");
		// map.put("unqid", "none_15ee405d7d99c6dfa4fca36c71e74729");
		map.put("title", "第一名奖励邮件");
		map.put("cont", "由于版本正在更新，请关注最近游戏动态，更新最新版本后，联系客服发放梨花诗等奖励。");
		InputStream inStream = HttpUrlConEx.postParams(url, map, "");
		System.out.println(HttpUrlConEx.inps2Str4Stream(inStream, "utf-8"));
	}

	static void testIosIAP() throws Exception {
		String base64Code = "ewoJInNpZ25hdHVyZSIgPSAiQXEzZkR3Y0JwOHlkUU9PS2E4Y05HRWNucGZ"
				+ "kTGo3bWhHeUMwb2psaHBzTlc1eDFKeUtLZWpsU2JYL0NsTEJrUHNTY3Z5R1BldGtYajVG"
				+ "YWpNbFIyK0xMOWhHcDQ5Q3NnZ0ZZZjhhMkJoNmRmZ2tGTDViY1JlRUQxVU5YL2hjb1RLc"
				+ "GZPclJJb2NmU0Q2eUdpRVk1RCtDMDhpT1NDR1AwT3AydndVNzhCeUFnK0FBQURWekNDQT"
				+ "FNd2dnSTdvQU1DQVFJQ0NHVVVrVTNaV0FTMU1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN"
				+ "6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURW"
				+ "UVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQ"
				+ "TFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1"
				+ "FYVjBhRzl5YVhSNU1CNFhEVEE1TURZeE5USXlNRFUxTmxvWERURTBNRFl4TkRJeU1EVTF"
				+ "ObG93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05o"
				+ "ZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVR"
				+ "UNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y0"
				+ "5BUUVCQlFBRGdZMEFNSUdKQW9HQkFNclJqRjJjdDRJclNkaVRDaGFJMGc4cHd2L2NtSHM"
				+ "4cC9Sd1YvcnQvOTFYS1ZoTmw0WElCaW1LalFRTmZnSHNEczZ5anUrK0RyS0pFN3VLc3Bo"
				+ "TWRkS1lmRkU1ckdYc0FkQkVqQndSSXhleFRldngzSExFRkdBdDFtb0t4NTA5ZGh4dGlJZ"
				+ "ERnSnYyWWFWczQ5QjB1SnZOZHk2U01xTk5MSHNETHpEUzlvWkhBZ01CQUFHamNqQndNQX"
				+ "dHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVOaDNvNHAyQzBnRVl0VEpyRHR"
				+ "kREM1RllRem93RGdZRFZSMFBBUUgvQkFRREFnZUFNQjBHQTFVZERnUVdCQlNwZzRQeUdV"
				+ "akZQaEpYQ0JUTXphTittVjhrOVRBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa"
				+ "2lHOXcwQkFRVUZBQU9DQVFFQUVhU2JQanRtTjRDL0lCM1FFcEszMlJ4YWNDRFhkVlhBZV"
				+ "ZSZVM1RmFaeGMrdDg4cFFQOTNCaUF4dmRXLzNlVFNNR1k1RmJlQVlMM2V0cVA1Z204d3J"
				+ "Gb2pYMGlreVZSU3RRKy9BUTBLRWp0cUIwN2tMczlRVWU4Y3pSOFVHZmRNMUV1bVYvVWd2"
				+ "RGQ0TndOWXhMUU1nNFdUUWZna1FRVnk4R1had1ZIZ2JFL1VDNlk3MDUzcEdYQms1MU5QT"
				+ "TN3b3hoZDNnU1JMdlhqK2xvSHNTdGNURXFlOXBCRHBtRzUrc2s0dHcrR0szR01lRU41Ly"
				+ "tlMVFUOW5wL0tsMW5qK2FCdzdDMHhzeTBiRm5hQWQxY1NTNnhkb3J5L0NVdk02Z3RLc21"
				+ "uT09kcVRlc2JwMGJzOHNuNldxczBDOWRnY3hSSHVPTVoydG04bnBMVW03YXJnT1N6UT09"
				+ "IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sT"
				+ "FdSaGRHVXRjSE4wSWlBOUlDSXlNREUwTFRBekxUSTRJREF6T2pFeU9qRTNJRUZ0WlhKcF"
				+ "kyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluVnVhWEYxWlMxcFpHVnVkR2xtYVdWeUlpQTl"
				+ "JQ0prTkRZeE5UVXlaREJpWkdSbE5qZ3pZVE01TkdKa01tTmpNamMyTTJGaE5qUXhNMkZq"
				+ "T1RKaElqc0tDU0p2Y21sbmFXNWhiQzEwY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd"
				+ "01EQXdNREV3TmpFeE1UTXlPQ0k3Q2draVluWnljeUlnUFNBaU1DNDVNU0k3Q2draWRISm"
				+ "hibk5oWTNScGIyNHRhV1FpSUQwZ0lqRXdNREF3TURBeE1EWXhNVEV6TWpnaU93b0pJbkY"
				+ "xWVc1MGFYUjVJaUE5SUNJeElqc0tDU0p2Y21sbmFXNWhiQzF3ZFhKamFHRnpaUzFrWVhS"
				+ "bExXMXpJaUE5SUNJeE16azJNREF4TlRNM01UQTRJanNLQ1NKMWJtbHhkV1V0ZG1WdVpHO"
				+ "XlMV2xrWlc1MGFXWnBaWElpSUQwZ0lqbEVOamhGTUROR0xUaEJNRVl0TkVJNU15MUNRel"
				+ "UyTFRaQ01ESkJORU5CTmpVMk9TSTdDZ2tpY0hKdlpIVmpkQzFwWkNJZ1BTQWlZMjl0TG1"
				+ "OdmIyeGhjR1V1YVhOc1lXNWtkMkZ5TG1jeElqc0tDU0pwZEdWdExXbGtJaUE5SUNJNE5E"
				+ "VTVPREkyT1RZaU93b0pJbUpwWkNJZ1BTQWlZMjl0TG1OdmIyeGhjR1V1YVhOc1lXNWtkM"
				+ "kZ5TG1kaGRDSTdDZ2tpY0hWeVkyaGhjMlV0WkdGMFpTMXRjeUlnUFNBaU1UTTVOakF3TV"
				+ "RVek56RXdPQ0k3Q2draWNIVnlZMmhoYzJVdFpHRjBaU0lnUFNBaU1qQXhOQzB3TXkweU9"
				+ "DQXhNRG94TWpveE55QkZkR012UjAxVUlqc0tDU0p3ZFhKamFHRnpaUzFrWVhSbExYQnpk"
				+ "Q0lnUFNBaU1qQXhOQzB3TXkweU9DQXdNem94TWpveE55QkJiV1Z5YVdOaEwweHZjMTlCY"
				+ "m1kbGJHVnpJanNLQ1NKdmNtbG5hVzVoYkMxd2RYSmphR0Z6WlMxa1lYUmxJaUE5SUNJeU"
				+ "1ERTBMVEF6TFRJNElERXdPakV5T2pFM0lFVjBZeTlIVFZRaU93cDkiOwoJImVudmlyb25"
				+ "tZW50IiA9ICJTYW5kYm94IjsKCSJwb2QiID0gIjEwMCI7Cgkic2lnbmluZy1zdGF0dXMi"
				+ "ID0gIjAiOwp9";
		// base64Code =
		// "ewoJInNpZ25hdHVyZSIgPSAiQXBkeEpkdE53UFUyckE1L2NuM2tJTzFPVGsyNWZlREthMGFhZ3l5UnZlV2xjRmxnbHY2UkY2em5raUJTM3VtOVVjN3BWb2IrUHFaUjJUOHd5VnJITnBsb2YzRFgzSXFET2xXcSs5MGE3WWwrcXJSN0E3ald3dml3NzA4UFMrNjdQeUhSbmhPL0c3YlZxZ1JwRXI2RXVGeWJpVTFGWEFpWEpjNmxzMVlBc3NReEFBQURWekNDQTFNd2dnSTdvQU1DQVFJQ0NHVVVrVTNaV0FTMU1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURWUVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQTFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1FYVjBhRzl5YVhSNU1CNFhEVEE1TURZeE5USXlNRFUxTmxvWERURTBNRFl4TkRJeU1EVTFObG93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05oZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVRUNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFNclJqRjJjdDRJclNkaVRDaGFJMGc4cHd2L2NtSHM4cC9Sd1YvcnQvOTFYS1ZoTmw0WElCaW1LalFRTmZnSHNEczZ5anUrK0RyS0pFN3VLc3BoTWRkS1lmRkU1ckdYc0FkQkVqQndSSXhleFRldngzSExFRkdBdDFtb0t4NTA5ZGh4dGlJZERnSnYyWWFWczQ5QjB1SnZOZHk2U01xTk5MSHNETHpEUzlvWkhBZ01CQUFHamNqQndNQXdHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVOaDNvNHAyQzBnRVl0VEpyRHRkREM1RllRem93RGdZRFZSMFBBUUgvQkFRREFnZUFNQjBHQTFVZERnUVdCQlNwZzRQeUdVakZQaEpYQ0JUTXphTittVjhrOVRBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa2lHOXcwQkFRVUZBQU9DQVFFQUVhU2JQanRtTjRDL0lCM1FFcEszMlJ4YWNDRFhkVlhBZVZSZVM1RmFaeGMrdDg4cFFQOTNCaUF4dmRXLzNlVFNNR1k1RmJlQVlMM2V0cVA1Z204d3JGb2pYMGlreVZSU3RRKy9BUTBLRWp0cUIwN2tMczlRVWU4Y3pSOFVHZmRNMUV1bVYvVWd2RGQ0TndOWXhMUU1nNFdUUWZna1FRVnk4R1had1ZIZ2JFL1VDNlk3MDUzcEdYQms1MU5QTTN3b3hoZDNnU1JMdlhqK2xvSHNTdGNURXFlOXBCRHBtRzUrc2s0dHcrR0szR01lRU41LytlMVFUOW5wL0tsMW5qK2FCdzdDMHhzeTBiRm5hQWQxY1NTNnhkb3J5L0NVdk02Z3RLc21uT09kcVRlc2JwMGJzOHNuNldxczBDOWRnY3hSSHVPTVoydG04bnBMVW03YXJnT1N6UT09IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREV5TFRBM0xURXlJREExT2pVME9qTTFJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakV6TkRJd09UYzJOelU0T0RJaU93b0pJbTl5YVdkcGJtRnNMWFJ5WVc1ellXTjBhVzl1TFdsa0lpQTlJQ0l4TnpBd01EQXdNamswTkRrME1qQWlPd29KSW1KMmNuTWlJRDBnSWpFdU5DSTdDZ2tpWVhCd0xXbDBaVzB0YVdRaUlEMGdJalExTURVME1qSXpNeUk3Q2draWRISmhibk5oWTNScGIyNHRhV1FpSUQwZ0lqRTNNREF3TURBeU9UUTBPVFF5TUNJN0Nna2ljWFZoYm5ScGRIa2lJRDBnSWpFaU93b0pJbTl5YVdkcGJtRnNMWEIxY21Ob1lYTmxMV1JoZEdVdGJYTWlJRDBnSWpFek5ESXdPVGMyTnpVNE9ESWlPd29KSW1sMFpXMHRhV1FpSUQwZ0lqVXpOREU0TlRBME1pSTdDZ2tpZG1WeWMybHZiaTFsZUhSbGNtNWhiQzFwWkdWdWRHbG1hV1Z5SWlBOUlDSTVNRFV4TWpNMklqc0tDU0p3Y205a2RXTjBMV2xrSWlBOUlDSmpiMjB1ZW1Wd2RHOXNZV0l1WTNSeVltOXVkWE11YzNWd1pYSndiM2RsY2pFaU93b0pJbkIxY21Ob1lYTmxMV1JoZEdVaUlEMGdJakl3TVRJdE1EY3RNVElnTVRJNk5UUTZNelVnUlhSakwwZE5WQ0k3Q2draWIzSnBaMmx1WVd3dGNIVnlZMmhoYzJVdFpHRjBaU0lnUFNBaU1qQXhNaTB3TnkweE1pQXhNam8xTkRvek5TQkZkR012UjAxVUlqc0tDU0ppYVdRaUlEMGdJbU52YlM1NlpYQjBiMnhoWWk1amRISmxlSEJsY21sdFpXNTBjeUk3Q2draWNIVnlZMmhoYzJVdFpHRjBaUzF3YzNRaUlEMGdJakl3TVRJdE1EY3RNVElnTURVNk5UUTZNelVnUVcxbGNtbGpZUzlNYjNOZlFXNW5aV3hsY3lJN0NuMD0iOwoJInBvZCIgPSAiMTciOwoJInNpZ25pbmctc3RhdHVzIiA9ICIwIjsKfQ==";
		// base64Code =
		// "ewoJInNpZ25hdHVyZSIgPSAiQWg5bXRWMnlNdTQ0UmF6M3RHVllOaXFxRlBtbTczaVRQV2FkMmZKWGhQejdVS2hjNmY5djR4c0lGY1ZkTEFLYVM2bHVlZ1FFRkYwZ0QxVm1DZzA0OXgzOStWc1JNdXRoMG1BajFIdUYvQUNScXVPVTR1RUlFT3JLU2xFV0Y5UmFFeS8rdEM1WGxPWEhzUnpULzVCRG1JeDlGdTh6RklkQlFpRUpCaUNkVDhsSUFBQURWekNDQTFNd2dnSTdvQU1DQVFJQ0NHVVVrVTNaV0FTMU1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURWUVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQTFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1FYVjBhRzl5YVhSNU1CNFhEVEE1TURZeE5USXlNRFUxTmxvWERURTBNRFl4TkRJeU1EVTFObG93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05oZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVRUNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFNclJqRjJjdDRJclNkaVRDaGFJMGc4cHd2L2NtSHM4cC9Sd1YvcnQvOTFYS1ZoTmw0WElCaW1LalFRTmZnSHNEczZ5anUrK0RyS0pFN3VLc3BoTWRkS1lmRkU1ckdYc0FkQkVqQndSSXhleFRldngzSExFRkdBdDFtb0t4NTA5ZGh4dGlJZERnSnYyWWFWczQ5QjB1SnZOZHk2U01xTk5MSHNETHpEUzlvWkhBZ01CQUFHamNqQndNQXdHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVOaDNvNHAyQzBnRVl0VEpyRHRkREM1RllRem93RGdZRFZSMFBBUUgvQkFRREFnZUFNQjBHQTFVZERnUVdCQlNwZzRQeUdVakZQaEpYQ0JUTXphTittVjhrOVRBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa2lHOXcwQkFRVUZBQU9DQVFFQUVhU2JQanRtTjRDL0lCM1FFcEszMlJ4YWNDRFhkVlhBZVZSZVM1RmFaeGMrdDg4cFFQOTNCaUF4dmRXLzNlVFNNR1k1RmJlQVlMM2V0cVA1Z204d3JGb2pYMGlreVZSU3RRKy9BUTBLRWp0cUIwN2tMczlRVWU4Y3pSOFVHZmRNMUV1bVYvVWd2RGQ0TndOWXhMUU1nNFdUUWZna1FRVnk4R1had1ZIZ2JFL1VDNlk3MDUzcEdYQms1MU5QTTN3b3hoZDNnU1JMdlhqK2xvSHNTdGNURXFlOXBCRHBtRzUrc2s0dHcrR0szR01lRU41LytlMVFUOW5wL0tsMW5qK2FCdzdDMHhzeTBiRm5hQWQxY1NTNnhkb3J5L0NVdk02Z3RLc21uT09kcVRlc2JwMGJzOHNuNldxczBDOWRnY3hSSHVPTVoydG04bnBMVW03YXJnT1N6UT09IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREUwTFRBMUxUSXlJREl4T2pJeE9qSXhJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakUwTURBNE1UZzRPREV4T1RFaU93b0pJblZ1YVhGMVpTMXBaR1Z1ZEdsbWFXVnlJaUE5SUNKa05EWXhOVFV5WkRCaVpHUmxOamd6WVRNNU5HSmtNbU5qTWpjMk0yRmhOalF4TTJGak9USmhJanNLQ1NKdmNtbG5hVzVoYkMxMGNtRnVjMkZqZEdsdmJpMXBaQ0lnUFNBaU1UQXdNREF3TURFeE1UYzVPVE01T0NJN0Nna2lZblp5Y3lJZ1BTQWlNUzR4SWpzS0NTSmhjSEF0YVhSbGJTMXBaQ0lnUFNBaU9EUTFPVEk0TWpVNUlqc0tDU0owY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd01EQXdNREV4TVRjNU9UTTVPQ0k3Q2draWNYVmhiblJwZEhraUlEMGdJakVpT3dvSkltOXlhV2RwYm1Gc0xYQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakUwTURBNE1UZzRPREV4T1RFaU93b0pJblZ1YVhGMVpTMTJaVzVrYjNJdGFXUmxiblJwWm1sbGNpSWdQU0FpTXpnd1JUQXlORUl0TlVNNU55MDBPRFF3TFRrM01ERXROREZFUkVSR05qUXdNakExSWpzS0NTSnBkR1Z0TFdsa0lpQTlJQ0k0TkRVNU9ESTJPVFlpT3dvSkluWmxjbk5wYjI0dFpYaDBaWEp1WVd3dGFXUmxiblJwWm1sbGNpSWdQU0FpTkRnME16WXlOalEzSWpzS0NTSndjbTlrZFdOMExXbGtJaUE5SUNKamIyMHVZMjl2YkdGd1pTNXBjMnhoYm1SM1lYSXVaekVpT3dvSkluQjFjbU5vWVhObExXUmhkR1VpSUQwZ0lqSXdNVFF0TURVdE1qTWdNRFE2TWpFNk1qRWdSWFJqTDBkTlZDSTdDZ2tpYjNKcFoybHVZV3d0Y0hWeVkyaGhjMlV0WkdGMFpTSWdQU0FpTWpBeE5DMHdOUzB5TXlBd05Eb3lNVG95TVNCRmRHTXZSMDFVSWpzS0NTSmlhV1FpSUQwZ0ltTnZiUzVqYjI5c1lYQmxMbWx6YkdGdVpIZGhjaTVuWVhRaU93b0pJbkIxY21Ob1lYTmxMV1JoZEdVdGNITjBJaUE5SUNJeU1ERTBMVEExTFRJeUlESXhPakl4T2pJeElFRnRaWEpwWTJFdlRHOXpYMEZ1WjJWc1pYTWlPd3A5IjsKCSJlbnZpcm9ubWVudCIgPSAiU2FuZGJveCI7CgkicG9kIiA9ICIxMDAiOwoJInNpZ25pbmctc3RhdHVzIiA9ICIwIjsKfQ==";
		String tran_id = "1000000111799398";// iosgat1000000111799398
		Map<String, String> parames = new HashMap<String, String>();
		parames.put("receipt-data", base64Code);
		String host = "https://sandbox.itunes.apple.com/verifyReceipt";
		String chaUTF = EncodingEx.UTF_8;
		String rJosn = "";
		InputStream inStream = HttpUrlConEx.postParams4Json(host, parames,
				chaUTF);
		rJosn = HttpUrlConEx.inps2Str4Stream(inStream, chaUTF);
		System.out.println("=============================");
		System.out.println(tran_id);
		System.out.println("=========== json sandbox back======");
		System.out.println(rJosn);
		System.out.println("=========== json sandbox back end======");

		host = "https://buy.itunes.apple.com/verifyReceipt";

		inStream = HttpUrlConEx.postParams4Json(host, parames, chaUTF);
		rJosn = HttpUrlConEx.inps2Str4Stream(inStream, chaUTF);
		System.out.println("=========== json back======");
		System.out.println(rJosn);
		System.out.println("=========== json back end======");
	}

	static void testIosIAP2() throws Exception {
		String base64Code = "ewoJInNpZ25hdHVyZSIgPSAiQXEzZkR3Y0JwOHlkUU9PS2E4Y05HRWNucGZ"
				+ "kTGo3bWhHeUMwb2psaHBzTlc1eDFKeUtLZWpsU2JYL0NsTEJrUHNTY3Z5R1BldGtYajVG"
				+ "YWpNbFIyK0xMOWhHcDQ5Q3NnZ0ZZZjhhMkJoNmRmZ2tGTDViY1JlRUQxVU5YL2hjb1RLc"
				+ "GZPclJJb2NmU0Q2eUdpRVk1RCtDMDhpT1NDR1AwT3AydndVNzhCeUFnK0FBQURWekNDQT"
				+ "FNd2dnSTdvQU1DQVFJQ0NHVVVrVTNaV0FTMU1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN"
				+ "6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURW"
				+ "UVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQ"
				+ "TFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1"
				+ "FYVjBhRzl5YVhSNU1CNFhEVEE1TURZeE5USXlNRFUxTmxvWERURTBNRFl4TkRJeU1EVTF"
				+ "ObG93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05o"
				+ "ZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVR"
				+ "UNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y0"
				+ "5BUUVCQlFBRGdZMEFNSUdKQW9HQkFNclJqRjJjdDRJclNkaVRDaGFJMGc4cHd2L2NtSHM"
				+ "4cC9Sd1YvcnQvOTFYS1ZoTmw0WElCaW1LalFRTmZnSHNEczZ5anUrK0RyS0pFN3VLc3Bo"
				+ "TWRkS1lmRkU1ckdYc0FkQkVqQndSSXhleFRldngzSExFRkdBdDFtb0t4NTA5ZGh4dGlJZ"
				+ "ERnSnYyWWFWczQ5QjB1SnZOZHk2U01xTk5MSHNETHpEUzlvWkhBZ01CQUFHamNqQndNQX"
				+ "dHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVOaDNvNHAyQzBnRVl0VEpyRHR"
				+ "kREM1RllRem93RGdZRFZSMFBBUUgvQkFRREFnZUFNQjBHQTFVZERnUVdCQlNwZzRQeUdV"
				+ "akZQaEpYQ0JUTXphTittVjhrOVRBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa"
				+ "2lHOXcwQkFRVUZBQU9DQVFFQUVhU2JQanRtTjRDL0lCM1FFcEszMlJ4YWNDRFhkVlhBZV"
				+ "ZSZVM1RmFaeGMrdDg4cFFQOTNCaUF4dmRXLzNlVFNNR1k1RmJlQVlMM2V0cVA1Z204d3J"
				+ "Gb2pYMGlreVZSU3RRKy9BUTBLRWp0cUIwN2tMczlRVWU4Y3pSOFVHZmRNMUV1bVYvVWd2"
				+ "RGQ0TndOWXhMUU1nNFdUUWZna1FRVnk4R1had1ZIZ2JFL1VDNlk3MDUzcEdYQms1MU5QT"
				+ "TN3b3hoZDNnU1JMdlhqK2xvSHNTdGNURXFlOXBCRHBtRzUrc2s0dHcrR0szR01lRU41Ly"
				+ "tlMVFUOW5wL0tsMW5qK2FCdzdDMHhzeTBiRm5hQWQxY1NTNnhkb3J5L0NVdk02Z3RLc21"
				+ "uT09kcVRlc2JwMGJzOHNuNldxczBDOWRnY3hSSHVPTVoydG04bnBMVW03YXJnT1N6UT09"
				+ "IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sT"
				+ "FdSaGRHVXRjSE4wSWlBOUlDSXlNREUwTFRBekxUSTRJREF6T2pFeU9qRTNJRUZ0WlhKcF"
				+ "kyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluVnVhWEYxWlMxcFpHVnVkR2xtYVdWeUlpQTl"
				+ "JQ0prTkRZeE5UVXlaREJpWkdSbE5qZ3pZVE01TkdKa01tTmpNamMyTTJGaE5qUXhNMkZq"
				+ "T1RKaElqc0tDU0p2Y21sbmFXNWhiQzEwY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd"
				+ "01EQXdNREV3TmpFeE1UTXlPQ0k3Q2draVluWnljeUlnUFNBaU1DNDVNU0k3Q2draWRISm"
				+ "hibk5oWTNScGIyNHRhV1FpSUQwZ0lqRXdNREF3TURBeE1EWXhNVEV6TWpnaU93b0pJbkY"
				+ "xWVc1MGFYUjVJaUE5SUNJeElqc0tDU0p2Y21sbmFXNWhiQzF3ZFhKamFHRnpaUzFrWVhS"
				+ "bExXMXpJaUE5SUNJeE16azJNREF4TlRNM01UQTRJanNLQ1NKMWJtbHhkV1V0ZG1WdVpHO"
				+ "XlMV2xrWlc1MGFXWnBaWElpSUQwZ0lqbEVOamhGTUROR0xUaEJNRVl0TkVJNU15MUNRel"
				+ "UyTFRaQ01ESkJORU5CTmpVMk9TSTdDZ2tpY0hKdlpIVmpkQzFwWkNJZ1BTQWlZMjl0TG1"
				+ "OdmIyeGhjR1V1YVhOc1lXNWtkMkZ5TG1jeElqc0tDU0pwZEdWdExXbGtJaUE5SUNJNE5E"
				+ "VTVPREkyT1RZaU93b0pJbUpwWkNJZ1BTQWlZMjl0TG1OdmIyeGhjR1V1YVhOc1lXNWtkM"
				+ "kZ5TG1kaGRDSTdDZ2tpY0hWeVkyaGhjMlV0WkdGMFpTMXRjeUlnUFNBaU1UTTVOakF3TV"
				+ "RVek56RXdPQ0k3Q2draWNIVnlZMmhoYzJVdFpHRjBaU0lnUFNBaU1qQXhOQzB3TXkweU9"
				+ "DQXhNRG94TWpveE55QkZkR012UjAxVUlqc0tDU0p3ZFhKamFHRnpaUzFrWVhSbExYQnpk"
				+ "Q0lnUFNBaU1qQXhOQzB3TXkweU9DQXdNem94TWpveE55QkJiV1Z5YVdOaEwweHZjMTlCY"
				+ "m1kbGJHVnpJanNLQ1NKdmNtbG5hVzVoYkMxd2RYSmphR0Z6WlMxa1lYUmxJaUE5SUNJeU"
				+ "1ERTBMVEF6TFRJNElERXdPakV5T2pFM0lFVjBZeTlIVFZRaU93cDkiOwoJImVudmlyb25"
				+ "tZW50IiA9ICJTYW5kYm94IjsKCSJwb2QiID0gIjEwMCI7Cgkic2lnbmluZy1zdGF0dXMi"
				+ "ID0gIjAiOwp9";
		base64Code = "ewoJInNpZ25hdHVyZSIgPSAiQXBkeEpkdE53UFUyckE1L2NuM2tJTzFPVGsyNWZlREthMGFhZ3l5UnZlV2xjRmxnbHY2UkY2em5raUJTM3VtOVVjN3BWb2IrUHFaUjJUOHd5VnJITnBsb2YzRFgzSXFET2xXcSs5MGE3WWwrcXJSN0E3ald3dml3NzA4UFMrNjdQeUhSbmhPL0c3YlZxZ1JwRXI2RXVGeWJpVTFGWEFpWEpjNmxzMVlBc3NReEFBQURWekNDQTFNd2dnSTdvQU1DQVFJQ0NHVVVrVTNaV0FTMU1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURWUVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQTFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1FYVjBhRzl5YVhSNU1CNFhEVEE1TURZeE5USXlNRFUxTmxvWERURTBNRFl4TkRJeU1EVTFObG93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05oZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVRUNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFNclJqRjJjdDRJclNkaVRDaGFJMGc4cHd2L2NtSHM4cC9Sd1YvcnQvOTFYS1ZoTmw0WElCaW1LalFRTmZnSHNEczZ5anUrK0RyS0pFN3VLc3BoTWRkS1lmRkU1ckdYc0FkQkVqQndSSXhleFRldngzSExFRkdBdDFtb0t4NTA5ZGh4dGlJZERnSnYyWWFWczQ5QjB1SnZOZHk2U01xTk5MSHNETHpEUzlvWkhBZ01CQUFHamNqQndNQXdHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVOaDNvNHAyQzBnRVl0VEpyRHRkREM1RllRem93RGdZRFZSMFBBUUgvQkFRREFnZUFNQjBHQTFVZERnUVdCQlNwZzRQeUdVakZQaEpYQ0JUTXphTittVjhrOVRBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa2lHOXcwQkFRVUZBQU9DQVFFQUVhU2JQanRtTjRDL0lCM1FFcEszMlJ4YWNDRFhkVlhBZVZSZVM1RmFaeGMrdDg4cFFQOTNCaUF4dmRXLzNlVFNNR1k1RmJlQVlMM2V0cVA1Z204d3JGb2pYMGlreVZSU3RRKy9BUTBLRWp0cUIwN2tMczlRVWU4Y3pSOFVHZmRNMUV1bVYvVWd2RGQ0TndOWXhMUU1nNFdUUWZna1FRVnk4R1had1ZIZ2JFL1VDNlk3MDUzcEdYQms1MU5QTTN3b3hoZDNnU1JMdlhqK2xvSHNTdGNURXFlOXBCRHBtRzUrc2s0dHcrR0szR01lRU41LytlMVFUOW5wL0tsMW5qK2FCdzdDMHhzeTBiRm5hQWQxY1NTNnhkb3J5L0NVdk02Z3RLc21uT09kcVRlc2JwMGJzOHNuNldxczBDOWRnY3hSSHVPTVoydG04bnBMVW03YXJnT1N6UT09IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREV5TFRBM0xURXlJREExT2pVME9qTTFJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakV6TkRJd09UYzJOelU0T0RJaU93b0pJbTl5YVdkcGJtRnNMWFJ5WVc1ellXTjBhVzl1TFdsa0lpQTlJQ0l4TnpBd01EQXdNamswTkRrME1qQWlPd29KSW1KMmNuTWlJRDBnSWpFdU5DSTdDZ2tpWVhCd0xXbDBaVzB0YVdRaUlEMGdJalExTURVME1qSXpNeUk3Q2draWRISmhibk5oWTNScGIyNHRhV1FpSUQwZ0lqRTNNREF3TURBeU9UUTBPVFF5TUNJN0Nna2ljWFZoYm5ScGRIa2lJRDBnSWpFaU93b0pJbTl5YVdkcGJtRnNMWEIxY21Ob1lYTmxMV1JoZEdVdGJYTWlJRDBnSWpFek5ESXdPVGMyTnpVNE9ESWlPd29KSW1sMFpXMHRhV1FpSUQwZ0lqVXpOREU0TlRBME1pSTdDZ2tpZG1WeWMybHZiaTFsZUhSbGNtNWhiQzFwWkdWdWRHbG1hV1Z5SWlBOUlDSTVNRFV4TWpNMklqc0tDU0p3Y205a2RXTjBMV2xrSWlBOUlDSmpiMjB1ZW1Wd2RHOXNZV0l1WTNSeVltOXVkWE11YzNWd1pYSndiM2RsY2pFaU93b0pJbkIxY21Ob1lYTmxMV1JoZEdVaUlEMGdJakl3TVRJdE1EY3RNVElnTVRJNk5UUTZNelVnUlhSakwwZE5WQ0k3Q2draWIzSnBaMmx1WVd3dGNIVnlZMmhoYzJVdFpHRjBaU0lnUFNBaU1qQXhNaTB3TnkweE1pQXhNam8xTkRvek5TQkZkR012UjAxVUlqc0tDU0ppYVdRaUlEMGdJbU52YlM1NlpYQjBiMnhoWWk1amRISmxlSEJsY21sdFpXNTBjeUk3Q2draWNIVnlZMmhoYzJVdFpHRjBaUzF3YzNRaUlEMGdJakl3TVRJdE1EY3RNVElnTURVNk5UUTZNelVnUVcxbGNtbGpZUzlNYjNOZlFXNW5aV3hsY3lJN0NuMD0iOwoJInBvZCIgPSAiMTciOwoJInNpZ25pbmctc3RhdHVzIiA9ICIwIjsKfQ==";
		base64Code = "ewoJInNpZ25hdHVyZSIgPSAiQWg5bXRWMnlNdTQ0UmF6M3RHVllOaXFxRlBtbTczaVRQV2FkMmZKWGhQejdVS2hjNmY5djR4c0lGY1ZkTEFLYVM2bHVlZ1FFRkYwZ0QxVm1DZzA0OXgzOStWc1JNdXRoMG1BajFIdUYvQUNScXVPVTR1RUlFT3JLU2xFV0Y5UmFFeS8rdEM1WGxPWEhzUnpULzVCRG1JeDlGdTh6RklkQlFpRUpCaUNkVDhsSUFBQURWekNDQTFNd2dnSTdvQU1DQVFJQ0NHVVVrVTNaV0FTMU1BMEdDU3FHU0liM0RRRUJCUVVBTUg4eEN6QUpCZ05WQkFZVEFsVlRNUk13RVFZRFZRUUtEQXBCY0hCc1pTQkpibU11TVNZd0pBWURWUVFMREIxQmNIQnNaU0JEWlhKMGFXWnBZMkYwYVc5dUlFRjFkR2h2Y21sMGVURXpNREVHQTFVRUF3d3FRWEJ3YkdVZ2FWUjFibVZ6SUZOMGIzSmxJRU5sY25ScFptbGpZWFJwYjI0Z1FYVjBhRzl5YVhSNU1CNFhEVEE1TURZeE5USXlNRFUxTmxvWERURTBNRFl4TkRJeU1EVTFObG93WkRFak1DRUdBMVVFQXd3YVVIVnlZMmhoYzJWU1pXTmxhWEIwUTJWeWRHbG1hV05oZEdVeEd6QVpCZ05WQkFzTUVrRndjR3hsSUdsVWRXNWxjeUJUZEc5eVpURVRNQkVHQTFVRUNnd0tRWEJ3YkdVZ1NXNWpMakVMTUFrR0ExVUVCaE1DVlZNd2daOHdEUVlKS29aSWh2Y05BUUVCQlFBRGdZMEFNSUdKQW9HQkFNclJqRjJjdDRJclNkaVRDaGFJMGc4cHd2L2NtSHM4cC9Sd1YvcnQvOTFYS1ZoTmw0WElCaW1LalFRTmZnSHNEczZ5anUrK0RyS0pFN3VLc3BoTWRkS1lmRkU1ckdYc0FkQkVqQndSSXhleFRldngzSExFRkdBdDFtb0t4NTA5ZGh4dGlJZERnSnYyWWFWczQ5QjB1SnZOZHk2U01xTk5MSHNETHpEUzlvWkhBZ01CQUFHamNqQndNQXdHQTFVZEV3RUIvd1FDTUFBd0h3WURWUjBqQkJnd0ZvQVVOaDNvNHAyQzBnRVl0VEpyRHRkREM1RllRem93RGdZRFZSMFBBUUgvQkFRREFnZUFNQjBHQTFVZERnUVdCQlNwZzRQeUdVakZQaEpYQ0JUTXphTittVjhrOVRBUUJnb3Foa2lHOTJOa0JnVUJCQUlGQURBTkJna3Foa2lHOXcwQkFRVUZBQU9DQVFFQUVhU2JQanRtTjRDL0lCM1FFcEszMlJ4YWNDRFhkVlhBZVZSZVM1RmFaeGMrdDg4cFFQOTNCaUF4dmRXLzNlVFNNR1k1RmJlQVlMM2V0cVA1Z204d3JGb2pYMGlreVZSU3RRKy9BUTBLRWp0cUIwN2tMczlRVWU4Y3pSOFVHZmRNMUV1bVYvVWd2RGQ0TndOWXhMUU1nNFdUUWZna1FRVnk4R1had1ZIZ2JFL1VDNlk3MDUzcEdYQms1MU5QTTN3b3hoZDNnU1JMdlhqK2xvSHNTdGNURXFlOXBCRHBtRzUrc2s0dHcrR0szR01lRU41LytlMVFUOW5wL0tsMW5qK2FCdzdDMHhzeTBiRm5hQWQxY1NTNnhkb3J5L0NVdk02Z3RLc21uT09kcVRlc2JwMGJzOHNuNldxczBDOWRnY3hSSHVPTVoydG04bnBMVW03YXJnT1N6UT09IjsKCSJwdXJjaGFzZS1pbmZvIiA9ICJld29KSW05eWFXZHBibUZzTFhCMWNtTm9ZWE5sTFdSaGRHVXRjSE4wSWlBOUlDSXlNREUwTFRBMUxUSXlJREl4T2pJeE9qSXhJRUZ0WlhKcFkyRXZURzl6WDBGdVoyVnNaWE1pT3dvSkluQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakUwTURBNE1UZzRPREV4T1RFaU93b0pJblZ1YVhGMVpTMXBaR1Z1ZEdsbWFXVnlJaUE5SUNKa05EWXhOVFV5WkRCaVpHUmxOamd6WVRNNU5HSmtNbU5qTWpjMk0yRmhOalF4TTJGak9USmhJanNLQ1NKdmNtbG5hVzVoYkMxMGNtRnVjMkZqZEdsdmJpMXBaQ0lnUFNBaU1UQXdNREF3TURFeE1UYzVPVE01T0NJN0Nna2lZblp5Y3lJZ1BTQWlNUzR4SWpzS0NTSmhjSEF0YVhSbGJTMXBaQ0lnUFNBaU9EUTFPVEk0TWpVNUlqc0tDU0owY21GdWMyRmpkR2x2YmkxcFpDSWdQU0FpTVRBd01EQXdNREV4TVRjNU9UTTVPQ0k3Q2draWNYVmhiblJwZEhraUlEMGdJakVpT3dvSkltOXlhV2RwYm1Gc0xYQjFjbU5vWVhObExXUmhkR1V0YlhNaUlEMGdJakUwTURBNE1UZzRPREV4T1RFaU93b0pJblZ1YVhGMVpTMTJaVzVrYjNJdGFXUmxiblJwWm1sbGNpSWdQU0FpTXpnd1JUQXlORUl0TlVNNU55MDBPRFF3TFRrM01ERXROREZFUkVSR05qUXdNakExSWpzS0NTSnBkR1Z0TFdsa0lpQTlJQ0k0TkRVNU9ESTJPVFlpT3dvSkluWmxjbk5wYjI0dFpYaDBaWEp1WVd3dGFXUmxiblJwWm1sbGNpSWdQU0FpTkRnME16WXlOalEzSWpzS0NTSndjbTlrZFdOMExXbGtJaUE5SUNKamIyMHVZMjl2YkdGd1pTNXBjMnhoYm1SM1lYSXVaekVpT3dvSkluQjFjbU5vWVhObExXUmhkR1VpSUQwZ0lqSXdNVFF0TURVdE1qTWdNRFE2TWpFNk1qRWdSWFJqTDBkTlZDSTdDZ2tpYjNKcFoybHVZV3d0Y0hWeVkyaGhjMlV0WkdGMFpTSWdQU0FpTWpBeE5DMHdOUzB5TXlBd05Eb3lNVG95TVNCRmRHTXZSMDFVSWpzS0NTSmlhV1FpSUQwZ0ltTnZiUzVqYjI5c1lYQmxMbWx6YkdGdVpIZGhjaTVuWVhRaU93b0pJbkIxY21Ob1lYTmxMV1JoZEdVdGNITjBJaUE5SUNJeU1ERTBMVEExTFRJeUlESXhPakl4T2pJeElFRnRaWEpwWTJFdlRHOXpYMEZ1WjJWc1pYTWlPd3A5IjsKCSJlbnZpcm9ubWVudCIgPSAiU2FuZGJveCI7CgkicG9kIiA9ICIxMDAiOwoJInNpZ25pbmctc3RhdHVzIiA9ICIwIjsKfQ==";
		String tran_id = "1000000111799398";// iosgat1000000111799398
		Map<String, String> parames = new HashMap<String, String>();
		parames.put("tranid", tran_id);
		boolean v = IAPIOSRecharge.checkPay(parames, base64Code);
		System.out.println(v);
	}
}
