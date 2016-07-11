package com.gb.logic.opt;

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
}
