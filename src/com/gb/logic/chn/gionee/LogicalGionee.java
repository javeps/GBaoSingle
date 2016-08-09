package com.gb.logic.chn.gionee;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.net.http.HttpBaseEx;
import com.bowlong.util.MapEx;
import com.gb.logic.opt.model.LogicalRecordOrders;

/**
 * <pre>
 * <p>Title: OrderNotify.java</p>
 * <p>Description:商户接收到金立支付系统notify通知后，处理通知内容</p>
 * <p>成功响应 :"success"</p>
 * <p>失败响应 :"fail"</p>
 * <p>Copyright: Copyright (c) 2012</p>
 * <p>Company:金立通信设备有限公司</p>
 * </pre>
 * 
 * @author tianxb
 * @date 2012-11-30 下午3:06:07
 * @version V2.0
 */
public class LogicalGionee implements Serializable{

	static Log log = LogFactory.getLog(LogicalGionee.class);
	
	private static final long serialVersionUID = -1134956680120198016L;
	
	private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQClChTCgP57OUmOPcB2q+Q2AK+IOic+HkwiUeCTO3Rgw271KaMpwqz/mwmWYiWpK9dRerlmSBTkNByTkbPkUsZk/QsMENEnw+dxwvC1gmTw9qHk/u6zRB+acjBJn8zTYxUHB3AIjKkFkKNDTIPypfc+pemTxgsZU5noQJBF6so/NQIDAQAB";

	static public String handler(Map<String, String> map) {
		String state = valida(map);
		String cont = HttpBaseEx.buildQuery(map, "UTF-8");
		if("success".equals(state)){
			String out_order_no = MapEx.getString(map, "out_order_no");
			if(StrEx.isEmptyTrim(out_order_no)){
				state = "fail";
			}else{
				int stateInt = LogicalRecordOrders.recordOrder(out_order_no, cont);
				if (stateInt != 1) {
					state = "fail";
				}
			}
		}
		
		if("fail".equals(state)){
			log.debug(cont);
		}
		
		return state;
	}
	
	static String valida(Map<String, String> map) {

		String sign = MapEx.getString(map, "sign");

		/*************************************** 组装重排序参数 *********************************************/

		StringBuilder contentBuffer = new StringBuilder();
		String[] signParamArray = (String[]) map.keySet().toArray();
		Arrays.sort(signParamArray);
		int lens = signParamArray.length;
		String key, val;
		for (int i = 0; i < lens; i++) {
			key = signParamArray[i];
			val = MapEx.getString(map, key);
			if (!"sign".equals(key) && !"msg".equals(key)) {// sign和msg不参与签名
				contentBuffer.append(key + "=" + val);
				if (i < lens - 1) {
					contentBuffer.append("&");
				}
			}

		}

		String content = contentBuffer.toString();

		if (StrEx.isEmptyTrim(sign)) {
			return "fail";
		}

		/****************************** 签名验证 *******************************************/
		boolean isValid = false;
		try {
			isValid = RSASignature.doCheck(content, sign, PUBLIC_KEY,"");
		} catch (Exception e) {
			return "fail";
		}

		if (isValid) {
			return "success";
		}

		return "fail";
	}
}
