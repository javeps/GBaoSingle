package com.gb.logic.chn.huawei.pay.callback.demo.servlet;

import java.io.Serializable;
import java.util.Map;

import com.gb.logic.chn.huawei.pay.callback.demo.util.CommonUtil;

public class CallbackVerify implements Serializable {

	private static final long serialVersionUID = 1L;

	// 支付公钥，CP需要替换成自己的
	public static final String devPubKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBALdSZjN8wPEkmSFhrlEmEEj9DxLhp4hwGodFk/YJdrrXnxS+dSptD5U0vzQ1dffDAtdyWbC5+M9bBpgAGYGreD0CAwEAAQ==";

	static public int doVerify(Map<String, Object> map) {
		String sign = (String) map.get("sign");

		// 验签成功
		if (CommonUtil.rsaDoCheck(map, sign, devPubKey)) {
			return 0;
		} else { // 验签失败
			return 1;
		}
	}
}
