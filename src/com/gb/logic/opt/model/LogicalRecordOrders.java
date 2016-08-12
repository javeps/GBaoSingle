package com.gb.logic.opt.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.util.DateEx;
import com.gb.db.bean.Recode4orders;
import com.gb.db.entity.Recode4ordersEntity;

public class LogicalRecordOrders implements Serializable {
	static Log log = LogFactory.getLog(LogicalRecordOrders.class);

	private static final long serialVersionUID = 1L;

	static public int recordOrder(String unqkey, String content, String chnStr) {
		Recode4orders order = Recode4ordersEntity.getByUnqkey(unqkey);
		if (order != null) {
			return -1;
		}

		Date createtime = DateEx.nowDate();
		Date lasttime = createtime;
		order = Recode4orders.newRecode4orders(0, unqkey, 0, content, chnStr,
				chnStr, createtime, lasttime);
		order.insert();
		return 1;
	}

	static public boolean useOrder(String unqkey, String payChn) {
		Recode4orders order = Recode4ordersEntity.getByUnqkey(unqkey);
		if (order != null) {
			if (order.getUsestate() == 0) {
				order.setUsestate(1);
				order.setChnPay(payChn);
				order.setLasttime(DateEx.nowDate());
				order.update();
				return true;
			}
		}
		return false;
	}
}
