package com.gb.logic.opt;

import java.util.Map;

import com.bowlong.io.FileRw;
import com.bowlong.lang.StrEx;
import com.bowlong.third.FastJSON;
import com.gb.db.bean.Cop4fee;
import com.gb.db.entity.Cop4feeEntity;

public class LogicalCop extends Logical {
	static public String getCop(String chn) {
		Cop4fee entity = Cop4feeEntity.getByChn(chn);
		String json = "{\"copfee\":1}";
		if (entity != null) {
			json = FastJSON.toJSONString(entity.toBasicMap());
		}
		return json;
	}

	static public String getCopHtml() {
		String html = FileRw.readStr("html/copfee.html");
		String cell = FileRw.readStr("html/copfee_item.txt");
		String chnJson = FileRw.readStr("files/chns.json");
		String cellContent = "";
		if(!StrEx.isEmpty(chnJson)){
			Map map = FastJSON.parseMap(chnJson);
			String action = Logical.getActionUrl("upCopFee");
			cellContent = StrEx.fmt(cell, action);
		}
		String ret = StrEx.fmt(html, cellContent);
		return ret;
	}

}
