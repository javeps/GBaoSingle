package com.gb.logic.opt.server;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.lang.StrEx;
import com.bowlong.net.http.urlcon.HttpUrlConEx;
import com.bowlong.third.FastJSON;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.gb.toolkits.UtileTools;

/**
 * IOS充值验证
 * 
 * @author Canyon
 * @version createtime：2015年9月28日下午6:16:21
 */
@SuppressWarnings("rawtypes")
public class IAPIOSRecharge {

	static Log log = LogFactory.getLog(IAPIOSRecharge.class);

	static final String hostBuy = "https://buy.itunes.apple.com/verifyReceipt";
	static final String hostSandBox = "https://sandbox.itunes.apple.com/verifyReceipt";
	static final long Diff_Time = DateEx.TIME_MINUTE * 60 * 3;

	public static boolean checkPayBuyBox(Map<String, String> mapData,
			String base64ViCode) {
		return checkPay(hostBuy, mapData, base64ViCode);
	}

	public static boolean checkPaySandBox(Map<String, String> mapData,
			String base64ViCode) {
		return checkPay(hostSandBox, mapData, base64ViCode);
	}

	public static boolean checkPay(String strQuery, String base64ViCode) {
		boolean isHasLimit = IAPVerify.isLimitVerify(base64ViCode);
		if (isHasLimit)
			return false;
		Map<String, String> mapData = HttpUrlConEx.buildMapByQuery(strQuery);
		boolean rbuy = checkPayBuyBox(mapData, base64ViCode);
		if (!rbuy) {
			// log.info("========= ios 验证正式模式为 false 此时验证 shandBox===========");
			rbuy = checkPaySandBox(mapData, base64ViCode);
		}
		return rbuy;
	}

	public static boolean checkPay(Map<String, String> mapData,
			String base64ViCode) {
		boolean isHasLimit = IAPVerify.isLimitVerify(base64ViCode);
		if (isHasLimit)
			return false;
		boolean rbuy = checkPayBuyBox(mapData, base64ViCode);
		if (!rbuy) {
			log.info("========= ios 验证正式模式为 false 此时验证 shandBox===========");
			rbuy = checkPaySandBox(mapData, base64ViCode);
		}
		return rbuy;
	}

	private static boolean checkPay(String host, Map<String, String> mapDate,
			String base64ViCode) {
		boolean r = false;
		try {
			if (StrEx.isEmptyTrim(base64ViCode)) {
				log.info("base64 === null");
				return r;
			}

			Map<String, String> parames = new HashMap<String, String>();
			parames.put("receipt-data", base64ViCode);

			InputStream inStream = HttpUrlConEx.postParams4Json(host, parames,
					"utf-8");

			String strVerify = HttpUrlConEx.inps2Str4Stream(inStream, "utf-8");
			Map veriMap = FastJSON.parseMap(strVerify);

			String strData = FastJSON.toJSONString(mapDate);

			boolean isStatus = veriMap.containsKey("status");
			if (!isStatus) {
				log.info("验证状态不正确验证返回值:" + strData);
				log.info("===验证得值:\n" + strVerify);
				return r;
			}

			int statusVerify = MapEx.getInt(veriMap, "status");
			if (statusVerify != 0) {
				log.info("验证状态不正确验证status:" + strData);
				log.info("===验证得值:\n" + strVerify);
				return r;
			}

			Map productInfo = MapEx.getMap(veriMap, "receipt");

			long original_purchase_date_ms = MapEx.getLong(productInfo,
					"original_purchase_date_ms");
			long now_time = System.currentTimeMillis();
			long min = now_time - Diff_Time;
			long max = now_time + Diff_Time;
			if (original_purchase_date_ms < min
					|| original_purchase_date_ms > max) {
				log.info("验证订单号purchase时间不正确:" + strData);
				log.info("===验证得值:\n" + strVerify);
				return r;
			}

			String original_transaction_id = MapEx.getString(productInfo,
					"original_transaction_id");
			String tranid = MapEx.getString(mapDate, "tranid");
			int index_train_id = tranid.indexOf(original_transaction_id);
			if (index_train_id == -1) {
				log.info("验证订单号订单号不正确:" + strData);
				log.info("===验证得值:\n" + strVerify);
				return r;
			}

			// String vpro_id = "com.coolape.islandwar";
			// String product_id = MapEx.getString(productInfo, "product_id");
			// int index = product_id.indexOf(vpro_id);
			// if (index == -1) {
			// log.info("验证订单号不正确product_id:" + strData);
			// return r;
			// }

			r = true;
		} catch (Exception e) {
			log.error(UtileTools.ex2s(e));
		}
		return r;
	}
}
