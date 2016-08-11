package com.gb.logic.chn.qihoo360;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.net.http.HttpBaseEx;
import com.bowlong.util.MapEx;
import com.gb.logic.chn.qihoo360.msdk.Pay;
import com.gb.logic.opt.model.LogicalRecordOrders;

public class LogicalQihoo360 implements Serializable {
	static Log log = LogFactory.getLog(LogicalQihoo360.class);

	private static final long serialVersionUID = 1L;

	static public String handler(Map<String, String> map) {
		Pay qihooPay = new Pay();
		String state = qihooPay.processRequest((HashMap<String, String>) map);
		String cont = HttpBaseEx.buildQuery(map, "UTF-8");
		if ("ok".equals(state)) {
			String app_order_id = MapEx.getString(map, "app_order_id");
			if (StrEx.isEmptyTrim(app_order_id)) {
				state = "verify failed";
			} else {
				int stateInt = LogicalRecordOrders.recordOrder(app_order_id,
						cont, "qihoo360");
				if (stateInt != 1) {
					state = "verify failed";
				}
			}
		}

		if (!"ok".equals(state)) {
			log.error("qihoo360 fail cont=[" + cont + "]");
		}
		return state;
	}

	public static void main(String[] args) {
		String query = "sign=NLE208%2B95AXY0g4ALRddOCEmnld4ppOKn7LtTjF0Crx9ewOKc2VevS6tzZsppFu4PLJbFmatml7Tn660E6188TNlUBaKzocWT47uCIbxCIJf0QUaK%2BuTOAYQhIKkmrw9M2%2BgHGnAh4PEXiBSFD9vb8fQFrjaFADBugVGwxUpMfM%3D&close_time=20160810164832&api_key=9878589B73FB4F70A849BD7506A6696B&create_time=20160810164819&out_order_no=a3edb676e8fb0eef46dfa5b33c02bdba&user_id=null&pay_channel=101&submit_time=20160810164817&deal_price=0.10";
		Map<String, String> map = HttpBaseEx.buildMapByQuery(query);
		handler(map);
	}
}
