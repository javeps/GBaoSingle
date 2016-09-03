package com.gb.logic.chn.wdj;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.bowlong.lang.StrEx;
import com.bowlong.net.http.HttpBaseEx;
import com.bowlong.third.FastJSON;
import com.bowlong.util.MapEx;
import com.bowlong.util.Ref;
import com.gb.logic.chn.wdj.rsatest.WandouRsa;
import com.gb.logic.opt.model.LogicalRecordOrders;

/**
 * 豌豆荚
 * 
 * @author Canyon
 * @version createtime：2016年9月3日 下午10:56:55
 */
public class LogicalWdj implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicalWdj.class);

	static public String handler(Map<String, String> map) {
		String cont = HttpBaseEx.buildQuery(map, "UTF-8");
		Ref<String> refOrderId = new Ref<String>();
		String state = valida(map, refOrderId);
		if ("success".equals(state)) {
			String storeOrder = refOrderId.val;
			if (StrEx.isEmptyTrim(storeOrder)) {
				state = "fail";
			} else {
				int stateInt = LogicalRecordOrders.recordOrder(storeOrder,
						cont, "wdj");
				if (stateInt != 1) {
					state = "fail";
				}
			}
		}

		if (!"success".equals(state)) {
			log.error("wdj fail cont=[" + cont + "]");
		}

		return state;
	}

	static String valida(Map<String, String> map, Ref<String> orderId) {
		String sign = MapEx.getString(map, "sign");
		String content = MapEx.getString(map, "content");
		boolean check = WandouRsa.doCheck(content, sign);
		if (check) {
			JSONObject objJson = FastJSON.parseObject(content);
			orderId.val = objJson.getString("out_trade_no");
			return "success";
		}
		return "fail";
	}

	public static void main(String[] args) {
		String query = "sign=NLE208%2B95AXY0g4ALRddOCEmnld4ppOKn7LtTjF0Crx9ewOKc2VevS6tzZsppFu4PLJbFmatml7Tn660E6188TNlUBaKzocWT47uCIbxCIJf0QUaK%2BuTOAYQhIKkmrw9M2%2BgHGnAh4PEXiBSFD9vb8fQFrjaFADBugVGwxUpMfM%3D&close_time=20160810164832&api_key=9878589B73FB4F70A849BD7506A6696B&create_time=20160810164819&out_order_no=a3edb676e8fb0eef46dfa5b33c02bdba&user_id=null&pay_channel=101&submit_time=20160810164817&deal_price=0.10";
		Map<String, String> map = HttpBaseEx.buildMapByQuery(query);
		handler(map);
	}
}
