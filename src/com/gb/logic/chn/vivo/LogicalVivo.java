package com.gb.logic.chn.vivo;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.net.http.HttpBaseEx;
import com.bowlong.util.MapEx;
import com.gb.logic.opt.model.LogicalRecordOrders;

/**
 * vivo
 * 
 * @author Canyon
 * @version createtime：2016年8月18日 下午3:06:50
 */
public class LogicalVivo implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicalVivo.class);

	static public String handler(Map<String, String> map) {
		boolean state = VivoSignUtils.verifySignature(map,
				VivoSignUtils.SECURITY_KEY);
		String cont = HttpBaseEx.buildQuery(map, "UTF-8");
		if (state) {
			String storeOrder = MapEx.getString(map, "storeOrder");
			if (StrEx.isEmptyTrim(storeOrder)) {
				state = false;
			} else {
				int stateInt = LogicalRecordOrders.recordOrder(storeOrder,
						cont, "vivo");
				if (stateInt != 1) {
					state = false;
				}
			}
		}

		if (!state) {
			log.error("vivo fail cont=[" + cont + "]");
		}

		return state ? "success" : "fail";
	}

	public static void main(String[] args) {
		String query = "sign=NLE208%2B95AXY0g4ALRddOCEmnld4ppOKn7LtTjF0Crx9ewOKc2VevS6tzZsppFu4PLJbFmatml7Tn660E6188TNlUBaKzocWT47uCIbxCIJf0QUaK%2BuTOAYQhIKkmrw9M2%2BgHGnAh4PEXiBSFD9vb8fQFrjaFADBugVGwxUpMfM%3D&close_time=20160810164832&api_key=9878589B73FB4F70A849BD7506A6696B&create_time=20160810164819&out_order_no=a3edb676e8fb0eef46dfa5b33c02bdba&user_id=null&pay_channel=101&submit_time=20160810164817&deal_price=0.10";
		Map<String, String> map = HttpBaseEx.buildMapByQuery(query);
		handler(map);
	}
}
