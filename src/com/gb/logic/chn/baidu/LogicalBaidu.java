package com.gb.logic.chn.baidu;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.net.http.HttpBaseEx;
import com.bowlong.third.FastJSON;
import com.bowlong.util.MapEx;
import com.bowlong.util.Ref;
import com.gb.logic.chn.baidu.sdk.VerifyCall;
import com.gb.logic.opt.model.LogicalRecordOrders;

/**
 * 百度 充值回调
 * 
 * @author Canyon
 * @version createtime：2016年9月4日 下午10:28:25
 */
public class LogicalBaidu implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicalBaidu.class);

	@SuppressWarnings("rawtypes")
	static public String handler(Map<String, String> map) {
		String cont = HttpBaseEx.buildQuery(map, "UTF-8");
		Ref<Integer> refState = new Ref<Integer>();
		String result = VerifyCall.doVerify(map, refState);
		if (refState.val == 1) {
			try {
				String storeOrder = MapEx.getString(map,
						"CooperatorOrderSerial");
				if (StrEx.isEmptyTrim(storeOrder)) {
					String dataStr = MapEx.getString(map, "Content");
					Map mapData = FastJSON.parseMap(dataStr);
					storeOrder = MapEx.getString(mapData, "ExtInfo");
				}

				if (StrEx.isEmptyTrim(storeOrder)) {
					refState.val = 0;
				} else {
					int stateInt = LogicalRecordOrders.recordOrder(storeOrder,
							cont, "baidu");
					if (stateInt != 1) {
						refState.val = 0;
					}
				}
			} catch (Exception e) {
				refState.val = 0;
			}
		}

		if (refState.val != 1) {
			log.error("baidu fail cont=[" + cont + "]");
		}

		return result;
	}

	public static void main(String[] args) {
		String query = "sign=NLE208%2B95AXY0g4ALRddOCEmnld4ppOKn7LtTjF0Crx9ewOKc2VevS6tzZsppFu4PLJbFmatml7Tn660E6188TNlUBaKzocWT47baiduIbxCIJf0QUaK%2BuTOAYQhIKkmrw9M2%2BgHGnAh4PEXiBSFD9vb8fQFrjaFADBugVGwxUpMfM%3D&close_time=20160810164832&api_key=9878589B73FB4F70A849BD7506A6696B&create_time=20160810164819&out_order_no=a3edb676e8fb0eef46dfa5b33c02bdba&user_id=null&pay_channel=101&submit_time=20160810164817&deal_price=0.10";
		Map<String, String> map = HttpBaseEx.buildMapByQuery(query);
		handler(map);
	}
}
