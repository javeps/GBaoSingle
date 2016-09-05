package com.gb.logic.opt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	static public String getCop(String chn, String version) {
		Cop4fee entity = Cop4feeEntity.getByChnVersion(chn, version);
		String json = "{\"copfee\":1}";
		if (entity != null) {
			Map map = entity.toBasicMap();
			map.clear();
			map.put("copfee", entity.getCopfee());
			json = FastJSON.toJSONString(map);
		}
		return json;
	}

	// 拥有赛选的参数
	static public Map<String, String> filterMap = new HashMap<String, String>();

	static public String getCopHtml() {
		String html = FileRw.readStr("html/copfee.html");
		String cell = FileRw.readStr("html/copfee_item.txt");
		String chnJson = FileRw.readStr("files/chns.json");
		String cellContent = "";
		if (!StrEx.isEmpty(chnJson)) {
			List list = FastJSON.parseList(chnJson);
			if (!ListEx.isEmpty(list)) {
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
				Map map;
				Map mapChn;
				Cop4fee entity;
				for (Object obj : list) {
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
						css_class = " class=\"bg_00" + entity.getCopfee()
								+ "\"";
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
							css_class);
					buffer.append(cellContent);
				}
				cellContent = buffer.toString();
				buffer.setLength(0);
				buffer = null;
			}
		}
		String ret = StrEx.fmt(html, cellContent);
		return ret;
	}

	static public void changeCopfee(String chn, String version, int copfee) {
		Cop4fee entity = Cop4feeEntity.getByChnVersion(chn, version);
		Date lasttime = DateEx.nowDate();
		if (entity != null) {
			entity.setCopfee(copfee);
			entity.setLasttime(lasttime);
			entity.update();
		} else {
			Date createtime = lasttime;
			String unqid = MD5.MD5UUIDStime(lasttime.getTime());
			entity = Cop4fee.newCop4fee(0, unqid, chn, version, copfee,
					createtime, lasttime);
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

}
