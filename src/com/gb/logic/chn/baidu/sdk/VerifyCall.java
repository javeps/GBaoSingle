package com.gb.logic.chn.baidu.sdk;

import java.util.Map;

import com.bowlong.util.MapEx;
import com.bowlong.util.Ref;

public class VerifyCall {
	static public String doVerify(Map<String, String> map, Ref<Integer> state) {
		int resultCode = 1;
		String resultMsg = "";
		String appid = "";
		String orderSerial = "";
		String cooperatorOrderSerial = "";
		String content = "";
		String sign = "";
		Sdk sdk = new Sdk();

		appid = MapEx.getString(map, "AppID");
		orderSerial = MapEx.getString(map, "OrderSerial");
		cooperatorOrderSerial = MapEx.getString(map, "CooperatorOrderSerial");
		content = MapEx.getString(map, "Content");
		sign = MapEx.getString(map, "Sign");
		// 参数判断
		if (appid == null || orderSerial == null
				|| cooperatorOrderSerial == null || content == null
				|| sign == null) {
			resultCode = 4;
			resultMsg = "参数错误";
		} else {
			// 先对接收到的通知进行验证
			StringBuilder strSign = new StringBuilder();
			strSign.append(appid);
			strSign.append(orderSerial);
			strSign.append(cooperatorOrderSerial);
			strSign.append(content);
			strSign.append(sdk.getSecretKey());
			// appID验证
			if (!sdk.getAppID().equals(appid)) {
				resultCode = 2; // appid无效
				resultMsg = "AppID无效";
			}
			// 签名验证
			if (!sdk.md5(strSign.toString()).toLowerCase()
					.equals(sign.toLowerCase())) {
				resultCode = 3; // sign无效
				resultMsg = "Sign无效";
			}
		}

		String result = "{\"AppID\":" + sdk.getAppID() + ",\"ResultCode\":"
				+ resultCode + ",\"ResultMsg\":\"" + resultMsg
				+ "\",\"Sign\":\""
				+ sdk.md5(sdk.getAppID() + resultCode + sdk.getSecretKey())
				+ "\",\"Content\":\"\"}";

		state.val = resultCode;
		return result;
	}
}
