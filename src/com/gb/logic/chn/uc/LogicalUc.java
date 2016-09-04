package com.gb.logic.chn.uc;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.net.http.HttpBaseEx;
import com.bowlong.third.FastJSON;
import com.bowlong.util.MapEx;
import com.gb.logic.opt.model.LogicalRecordOrders;

/**
 * 九游UC
 * 
 * @author Canyon
 * @version createtime：2016年9月4日 下午9:32:32
 */
public class LogicalUc implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicalUc.class);

	@SuppressWarnings("rawtypes")
	static public String handler(Map<String, String> map) {
		String cont = HttpBaseEx.buildQuery(map, "UTF-8");
		String state = PayCallbackService.doVerifyPayCallback(MapEx.toMap(map));
		if ("SUCCESS".equals(state)) {
			try {
				String dataStr = MapEx.getString(map, "data");
				Map mapData = FastJSON.parseMap(dataStr);
				String storeOrder = MapEx.getString(mapData, "cpOrderId");
				if (StrEx.isEmptyTrim(storeOrder)) {
					storeOrder = MapEx.getString(mapData, "callbackInfo");
				}
				if (StrEx.isEmptyTrim(storeOrder)) {
					state = "FAILURE";
				} else {
					int stateInt = LogicalRecordOrders.recordOrder(storeOrder,
							cont, "uc");
					if (stateInt != 1) {
						state = "FAILURE";
					}
				}
			} catch (Exception e) {
				state = "FAILURE";
			}
		}

		if (!"SUCCESS".equals(state)) {
			log.error("uc fail cont=[" + cont + "]");
		}

		return state;
	}

	public static void main(String[] args) {
		String query = "sign=NLE208%2B95AXY0g4ALRddOCEmnld4ppOKn7LtTjF0Crx9ewOKc2VevS6tzZsppFu4PLJbFmatml7Tn660E6188TNlUBaKzocWT47uCIbxCIJf0QUaK%2BuTOAYQhIKkmrw9M2%2BgHGnAh4PEXiBSFD9vb8fQFrjaFADBugVGwxUpMfM%3D&close_time=20160810164832&api_key=9878589B73FB4F70A849BD7506A6696B&create_time=20160810164819&out_order_no=a3edb676e8fb0eef46dfa5b33c02bdba&user_id=null&pay_channel=101&submit_time=20160810164817&deal_price=0.10";
		Map<String, String> map = HttpBaseEx.buildMapByQuery(query);
		handler(map);
	}
}
