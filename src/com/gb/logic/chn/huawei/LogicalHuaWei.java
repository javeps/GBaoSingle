package com.gb.logic.chn.huawei;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.bowlong.lang.StrEx;
import com.bowlong.net.http.HttpBaseEx;
import com.bowlong.util.MapEx;
import com.gb.logic.chn.huawei.pay.callback.demo.servlet.CallbackVerify;
import com.gb.logic.opt.model.LogicalRecordOrders;

/***
 * 华为
 * 
 * @author Canyon
 * @version createtime：2016年9月3日 下午4:07:07
 */
public class LogicalHuaWei implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicalHuaWei.class);

	static public String handler(Map<String, String> map) {
		Map<String, Object> mapPars = MapEx.toMap(map);
		String cont = HttpBaseEx.buildQuery(map, "UTF-8");
		int state = CallbackVerify.doVerify(mapPars);
		if (state == 0) {
			String storeOrder = MapEx.getString(map, "cpOrderId");
			if (StrEx.isEmptyTrim(storeOrder)) {
				state = 1;
			} else {
				int stateInt = LogicalRecordOrders.recordOrder(storeOrder,
						cont, "huawei");
				if (stateInt != 1) {
					state = 1;
				}
			}
		}

		if (state != 0) {
			log.error("huawei fail cont=[" + cont + "]");
		}

		JSONObject obj = new JSONObject();

		return obj.toJSONString();
	}

	public static void main(String[] args) {
		String query = "sign=NLE208%2B95AXY0g4ALRddOCEmnld4ppOKn7LtTjF0Crx9ewOKc2VevS6tzZsppFu4PLJbFmatml7Tn660E6188TNlUBaKzocWT47uCIbxCIJf0QUaK%2BuTOAYQhIKkmrw9M2%2BgHGnAh4PEXiBSFD9vb8fQFrjaFADBugVGwxUpMfM%3D&close_time=20160810164832&api_key=9878589B73FB4F70A849BD7506A6696B&create_time=20160810164819&out_order_no=a3edb676e8fb0eef46dfa5b33c02bdba&user_id=null&pay_channel=101&submit_time=20160810164817&deal_price=0.10";
		Map<String, String> map = HttpBaseEx.buildMapByQuery(query);
		handler(map);
	}
}
