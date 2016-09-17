package com.gb.logic.opt;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.bowlong.io.FileRw;
import com.bowlong.lang.StrEx;
import com.bowlong.security.MD5;
import com.bowlong.third.FastJSON;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.gb.db.bean.Cop4fee;
import com.gb.db.entity.Cop4feeEntity;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class LogicalCop extends Logical {

	static Map mapCopSpecial = null;
	static public boolean isCanResetMapCopSpecial = false;

	static public Map getBegEndTime(boolean isReset) {
		if (!isCanResetMapCopSpecial) {
			if (!isReset && mapCopSpecial != null) {
				return mapCopSpecial;
			}
		}
		isCanResetMapCopSpecial = false;

		String strJson = FileRw.readStr("files/cop.json");
		if (StrEx.isEmpty(strJson)) {
			mapCopSpecial = new HashMap();
		} else {
			mapCopSpecial = FastJSON.parseMap(strJson);
		}
		return mapCopSpecial;
	}

	static public String getCop(String chn, String version) {
		Cop4fee entity = Cop4feeEntity.getByChnVersion(chn, version);
		String json = "{\"copfee\":1}";
		JSONObject jsonObj = new JSONObject(json);
		if (entity != null) {
			Map map = entity.toBasicMap();
			map.clear();
			jsonObj.put("copfee", entity.getCopfee());
		}

		Map tmpMap = getBegEndTime(false);
		if (!MapEx.isEmpty(tmpMap)) {
			int begtime = MapEx.getInt(tmpMap, "begtime");
			int endtime = MapEx.getInt(tmpMap, "endtime");
			// if (begtime >= 0 && endtime > begtime) {
			jsonObj.put("begtime", begtime);
			jsonObj.put("endtime", endtime);
			// }
		}
		json = jsonObj.toString();
		return json;
	}

	// 拥有赛选的参数
	static Map<String, Object> filterMap = new HashMap<String, Object>();

	static String getFitlerTxt() {
		String html = FileRw.readStr("html/cop_fiter.txt");
		StringBuffer buff_chn = new StringBuffer(
				"<option value=\"-1\">全部</option>");
		StringBuffer buff_ver = new StringBuffer(
				"<option value=\"-1\">全部</option>");
		StringBuffer buff_fee = new StringBuffer(
				"<option value=\"-1\">全部</option>");

		Map map;
		Map mapChn;
		String strTmp = "";

		Map<String, Boolean> chnJugde = new HashMap<String, Boolean>();
		Map<String, Boolean> verJugde = new HashMap<String, Boolean>();

		List listTmp = getListChns(false);

		String fiterChn = MapEx.getString(filterMap, "chn");
		String fiterVer = MapEx.getString(filterMap, "ver");
		int fiterfee = MapEx.getInt(filterMap, "fee");
		for (Object obj : listTmp) {
			map = (Map) obj;
			mapChn = MapEx.getMap(map, "chn");
			if (MapEx.isEmpty(mapChn)) {
				continue;
			}

			strTmp = MapEx.getString(mapChn, "key");
			if (!chnJugde.containsKey(strTmp)) {
				buff_chn.append("<option value=\"" + strTmp + "\"");
				if (strTmp.equals(fiterChn)) {
					buff_chn.append(" selected=\"selected\"");
				}
				buff_chn.append(">").append(strTmp).append("</option>");
				chnJugde.put(strTmp, true);
			}

			strTmp = MapEx.getString(mapChn, "version");
			if (!verJugde.containsKey(strTmp)) {
				buff_ver.append("<option value=\"" + strTmp + "\"");
				if (strTmp.equals(fiterVer)) {
					buff_ver.append(" selected=\"selected\"");
				}
				buff_ver.append(">").append(strTmp).append("</option>");
				verJugde.put(strTmp, true);
			}
		}

		for (int i = 1; i < 5; i++) {
			buff_fee.append("<option value=\"" + i + "\"");
			if (fiterfee == i) {
				buff_fee.append(" selected=\"selected\"");
			}
			switch (i) {
			case 1:
				strTmp = "审核模式";
				break;
			case 2:
				strTmp = "清晰模式";
				break;
			case 3:
				strTmp = "模糊模式";
				break;
			case 4:
				strTmp = "特殊模式";
				break;
			}
			buff_fee.append(">").append(strTmp).append("</option>");
		}

		String chn = buff_chn.toString();
		String chnVer = buff_ver.toString();
		String fee = buff_fee.toString();
		String action = Logical.getActionUrl("upFilterCop");
		String ret = StrEx.fmt(html, action, chn, chnVer, fee);
		return ret;
	}

	static public boolean upFilterCop(String chn, String ver, int fee)
			throws Exception {
		filterMap.clear();
		filterMap.put("chn", chn);
		filterMap.put("ver", ver);
		filterMap.put("fee", fee);
		return true;
	}

	static public boolean isCanResetListChns = false;
	static List listChns;

	static List getListChns(boolean isReset) {
		if (!isCanResetListChns) {
			if (!isReset && listChns != null) {
				return listChns;
			}
		}
		isCanResetListChns = false;

		String chnJson = FileRw.readStr("files/chns.json");
		if (!StrEx.isEmpty(chnJson)) {
			listChns = FastJSON.parseList(chnJson);
		}
		return listChns;
	}

	static private List getFitlerListChns() {
		List listTmp = getListChns(false);
		Map map;
		Map mapChn;
		String chn = "", chnVer = "";

		String fiterChn = MapEx.getString(filterMap, "chn");
		String fiterVer = MapEx.getString(filterMap, "ver");
		// int fiterfee = MapEx.getInt(filterMap, "fee");

		List ret = new ArrayList();
		for (Object obj : listTmp) {
			map = (Map) obj;
			mapChn = MapEx.getMap(map, "chn");
			if (MapEx.isEmpty(mapChn)) {
				continue;
			}

			chn = MapEx.getString(mapChn, "key");
			chnVer = MapEx.getString(mapChn, "version");
			if (!StrEx.isEmpty(fiterChn) && !"-1".equals(fiterChn)
					&& !chn.equals(fiterChn)) {
				continue;
			}

			if (!StrEx.isEmpty(fiterVer) && !"-1".equals(fiterVer)
					&& !chnVer.equals(fiterVer)) {
				continue;
			}
			
			ret.add(map);
		}

		return ret;
	}

	static public String getCopHtml() {
		isCanResetListChns = true;
		List listTmp = getFitlerListChns();

		String html = FileRw.readStr("html/copfee.html");
		String cell = FileRw.readStr("html/copfee_item.txt");
		String cellContent = "";
		if (!ListEx.isEmpty(listTmp)) {
			StringBuffer buffer = new StringBuffer();
			String action = Logical.getActionUrl("upCopFee");
			String unqkey = "";
			String chn = "";
			String chnName = "";
			String chnVer = "";
			String checked1 = "";
			String checked2 = "";
			String checked3 = "";
			String checked4 = "";
			String css_class = "";
			String begtime = "";
			String endtime = "";
			Map map;
			Map mapChn;
			Cop4fee entity;
			for (Object obj : listTmp) {
				map = (Map) obj;
				mapChn = MapEx.getMap(map, "chn");
				if (MapEx.isEmpty(mapChn)) {
					continue;
				}

				chn = MapEx.getString(mapChn, "key");
				chnName = MapEx.getString(mapChn, "name");
				chnVer = MapEx.getString(mapChn, "version");
				entity = Cop4feeEntity.getByChnVersion(chn, chnVer);
				unqkey = chn;
				checked1 = "";
				checked2 = "";
				checked3 = "";
				checked4 = "";
				css_class = "";
				if (entity != null) {
					// unqkey = entity.getUnqkey();
					css_class = " class=\"bg_00" + entity.getCopfee() + "\"";
					switch (entity.getCopfee()) {
					case 2:
						checked2 = "selected=\"selected\"";
						break;
					case 3:
						checked3 = "selected=\"selected\"";
						break;
					case 4:
						checked4 = "selected=\"selected\"";
						break;
					default:
						checked1 = "selected=\"selected\"";
						css_class = "";
						break;
					}
				}
				cellContent = StrEx.fmt(cell, action, unqkey, chn, chnName,
						chnVer, checked1, checked2, checked3, checked4,
						css_class, begtime, endtime);
				buffer.append(cellContent);
			}
			cellContent = buffer.toString();
			buffer.setLength(0);
			buffer = null;
		}

		String midText = getFitlerTxt();
		String copText = getSetCopSpecailTxt();

		String ret = StrEx.fmt(html, copText, midText, cellContent);
		return ret;
	}

	static public void changeCopfee(String chn, String version, int copfee) {
		Cop4fee entity = Cop4feeEntity.getByChnVersion(chn, version);
		Date nowtime = DateEx.nowDate();
		if (entity != null) {
			entity.setCopfee(copfee);
			entity.setLasttime(nowtime);
			entity.update();
		} else {
			String unqid = MD5.MD5UUIDStime(nowtime.getTime());
			entity = Cop4fee.newCop4fee(0, unqid, chn, version, copfee,
					nowtime, nowtime, nowtime, nowtime);
			entity.insert();
		}
	}

	static public String getUpCopStatesHtml(boolean isOkey) {
		String html = FileRw.readStr("html/copfee_up_states.html");
		String strStates = "";
		if (isOkey) {
			strStates = "成功(success)!!!";
		} else {
			strStates = "失败(fail)!!!";
		}
		String ret = StrEx.fmt(html, strStates);
		return ret;
	}

	static String getSetCopSpecailTxt() {
		String html = FileRw.readStr("html/cop_special.txt");
		String action = Logical.getActionUrl("upCopSpecail");
		String beg = "";
		String end = "";
		int begInt = -1;
		int endInt = -1;
		Map tmpMap = getBegEndTime(false);
		if (!MapEx.isEmpty(tmpMap)) {
			begInt = MapEx.getInt(tmpMap, "begtime");
			endInt = MapEx.getInt(tmpMap, "endtime");
		}
		StringBuffer buff1 = new StringBuffer(
				"<option value=\"-1\">不设定</option>");
		StringBuffer buff2 = new StringBuffer(
				"<option value=\"-1\">不设定</option>");

		for (int i = 0; i < 24; i++) {
			buff1.append("<option value=\"" + i + "\"");
			if (begInt == i) {
				buff1.append(" selected=\"selected\"");
			}
			buff1.append(">").append(i).append("点").append("</option>");

			buff2.append("<option value=\"" + i + "\"");
			if (endInt == i) {
				buff2.append(" selected=\"selected\"");
			}
			buff2.append(">").append(i).append("点").append("</option>");
		}

		beg = buff1.toString();
		end = buff2.toString();
		buff1.setLength(0);
		buff2.setLength(0);
		buff1 = null;
		buff2 = null;

		String ret = StrEx.fmt(html, action, beg, end);
		return ret;
	}

	static public boolean upCopSpecail(int begtime, int endtime)
			throws Exception {
		Map tmpMap = getBegEndTime(false);
		tmpMap.put("begtime", begtime);
		tmpMap.put("endtime", endtime);
		String jsonStr = FastJSON.toJSONString(tmpMap);
		FileRw.write("files/cop.json", jsonStr);
		return true;
	}
}
