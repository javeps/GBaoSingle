package com.gb.logic.chn.oppo;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;

// 添加了一个jar包 commons-codec-1.10.jar
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.net.http.HttpBaseEx;
import com.bowlong.util.MapEx;
import com.gb.content.Svc;
import com.gb.logic.opt.model.LogicalRecordOrders;

public class LogicalOppo implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicalOppo.class);

	private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCmreYIkPwVovKR8rLHWlFVw7YDfm9uQOJKL89Smt6ypXGVdrAKKl0wNYc3/jecAoPi2ylChfa2iRu5gunJyNmpWZzlCNRIau55fxGW0XEu553IiprOZcaw5OuYGlf60ga8QT6qToP0/dpiL/ZbmNUO9kUhosIjEu22uFgR+5cYyQIDAQAB";
	private static final String RESULT_STR = "result=%s&resultMsg=%s";

	private static final String CALLBACK_OK = "OK";
	private static final String CALLBACK_FAIL = "FAIL";

	static public String handler(Map<String, String> map) throws Exception {

		NotifyRequestEntity notify = new NotifyRequestEntity();
		notify.setNotifyId(MapEx.getString(map, "notifyId"));
		notify.setPartnerOrder(MapEx.getString(map, "partnerOrder"));
		notify.setProductName(MapEx.getString(map, "productName"));
		notify.setProductDesc(MapEx.getString(map, "productDesc"));
		notify.setPrice(MapEx.getInt(map, "price"));
		notify.setCount(MapEx.getInt(map, "count"));
		notify.setAttach(MapEx.getString(map, "attach"));
		notify.setSign(MapEx.getString(map, "sign"));
		String baseString = getBaseString(notify);

		String cont = HttpBaseEx.buildQuery(map, "UTF-8");

		boolean check = false;
		try {
			check = doCheck(baseString, notify.getSign(), PUBLIC_KEY);
		} catch (Exception ex) {
			log.error(Svc.e2s(ex));
		}
		String result = CALLBACK_OK;
		String resultMsg = "回调成功";
		if (check) {
			String orderid = notify.getPartnerOrder();
			if (StrEx.isEmptyTrim(orderid)) {
				check = false;
			} else {
				int stateInt = LogicalRecordOrders.recordOrder(orderid, cont,
						"oppo");
				if (stateInt != 1) {
					check = false;
				}
			}
		}

		if (!check) {
			result = CALLBACK_FAIL;
			resultMsg = "验签失败";
			log.error("oppo fail cont=[" + cont + "]");
		}

		// return String.format(RESULT_STR, result,
		// URLEncoder.encode(resultMsg, "UTF-8"));

		return String.format(RESULT_STR, result, resultMsg);
	}

	static private String getBaseString(NotifyRequestEntity ne) {
		StringBuilder sb = new StringBuilder();
		sb.append("notifyId=").append(ne.getNotifyId());
		sb.append("&partnerOrder=").append(ne.getPartnerOrder());
		sb.append("&productName=").append(ne.getProductName());
		sb.append("&productDesc=").append(ne.getProductDesc());
		sb.append("&price=").append(ne.getPrice());
		sb.append("&count=").append(ne.getCount());
		sb.append("&attach=").append(ne.getAttach());
		return sb.toString();
	}

	static public boolean doCheck(String content, String sign, String publicKey)
			throws Exception {
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		byte[] encodedKey = Base64.decodeBase64(publicKey);
		PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(
				encodedKey));

		java.security.Signature signature = java.security.Signature
				.getInstance("SHA1WithRSA");

		signature.initVerify(pubKey);
		signature.update(content.getBytes("UTF-8"));
		boolean bverify = signature.verify(Base64.decodeBase64(sign));
		return bverify;
	}

}