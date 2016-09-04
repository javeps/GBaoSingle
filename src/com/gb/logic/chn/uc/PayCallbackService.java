package com.gb.logic.chn.uc;

import java.util.Map;

import cn.uc.g.sdk.cp.model.PayCallback;
import cn.uc.g.sdk.cp.protocol.CpRequestHelper;
import cn.uc.g.sdk.cp.util.BeanToMapUtil;

import com.bowlong.util.MapEx;

/**
 * 支付回调接口示范类 <br>
 */
@SuppressWarnings("unchecked")
public class PayCallbackService {
	static String apiKey = "119ef28841a2cb44b67a695db000c2d2";

	public static String doVerifyPayCallback(Map<String, Object> resPars) {
		String respSign = MapEx.getString(resPars, "sign");
		Map<String, Object> data = (Map<String, Object>) resPars.get("data");
		String sign = CpRequestHelper.createMD5Sign(data, "", apiKey);
		try {
			if (sign != null && respSign != null && respSign.equals(sign)) {
				PayCallback payModel = BeanToMapUtil.convertMap(
						PayCallback.class, data);
				if ("S".equals(payModel.getOrderStatus())) {
					// 验证金额
					// if (promAmountSet.contains(payModel.getAmount())) {
					// }
					return "SUCCESS";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "FAILURE";
	}

}
