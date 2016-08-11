/**
 * 
 */

package com.gb.logic.chn.qihoo360.msdk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

import com.alibaba.fastjson.JSONObject;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Pay {

	private static final String VERIFY_URL = "http://msdk.mobilem.360.cn/pay/order_verify.json";
	private static final String VERIFIED = "verified";

	String _appId = "203171261";
	
	// TODO::需要修改为应用自身的app_key
	private String _appKey = "ae04cc21fc8d49bb61ff8bca28669cfd";
	
	// TODO::需要修改为应用自身的app_secret(服务器之间通讯使用)
	private String _appSecret = "7b25a9067805bb12e0a57c354a24686b";
	
	// private PayAppInterface _payApp;

	private String _errorMsg = "";

	// public Pay(PayAppInterface payApp) {
	// this._payApp = payApp;
	// this._appKey = payApp.getAppKey();
	// this._appSecret = payApp.getAppSecret();
	// }

	/**
	 * 处理从360过来的支付订单通知请求
	 * 
	 * @param params
	 * @return
	 */
	public String processRequest(HashMap<String, String> params) {

		if (!this._isValidRequest(params)) {
			if (!this._errorMsg.isEmpty()) {
				return this._errorMsg;
			}
			return "invalid request ";
		}

		if (!this._verifyOrder(params)) {
			if (!this._errorMsg.isEmpty()) {
				return this._errorMsg;
			}
			return "verify failed";
		}

		// if (this._payApp.isValidOrder(params)) {
		// this._payApp.processOrder(params);
		// }
		return "ok";
	}

	/**
	 * 向360服务器发起请求验证订单是否有效
	 * 
	 * @param params
	 * @return Boolean 是否有效
	 */
	private Boolean _verifyOrder(HashMap<String, String> params) {
		String url = VERIFY_URL;
		HashMap<String, String> requestParams = new HashMap();

		String field;
		Iterator<String> iterator = params.keySet().iterator();
		while (iterator.hasNext()) {
			field = iterator.next();
			if (field.equals("sign") || field.equals("sign_return")) {
				continue;
			}
			requestParams.put(field, params.get(field));
		}
		requestParams.put("sign", Util.getSign(requestParams, this._appSecret));

		String ret;
		try {
			ret = Util.requestUrl(url, requestParams);
		} catch (IOException e) {
			this._errorMsg = e.toString();
			return false;
		} catch (Exception e1) {
			this._errorMsg = e1.toString();
			return false;
		}

		try {
			JSONObject obj = JSONObject.parseObject(ret);

			Boolean verified = obj.get("ret").equals(VERIFIED);
			if (!verified) {
				this._errorMsg = obj.get("ret").toString();
			}
			return verified;
		} catch (Exception e) {
			this._errorMsg = e.toString();
			return false;
		}
	}

	/**
	 * 检查request完整性
	 * 
	 * @param params
	 * @return Boolean
	 */
	private Boolean _isValidRequest(HashMap params) {

		String arrFields[] = { "app_key", "product_id", "app_uid", "order_id",
				"sign_type", "gateway_flag", "sign", "sign_return", "amount" };
		ArrayList fields = new ArrayList(Arrays.asList(arrFields));

		String key;
		String value;
		Iterator iterator = fields.iterator();
		while (iterator.hasNext()) {
			key = (String) iterator.next();
			value = (String) params.get(key);
			if (value == null || value.equals("")) {
				return false;
			}
		}

		if (!params.get("app_key").equals(this._appKey)) {
			this._errorMsg = "not my order";
			return false;
		}

		String sign = Util.getSign(params, this._appSecret);
		String paramSign = (String) params.get("sign");
		return sign.equals(paramSign);
	}
}
