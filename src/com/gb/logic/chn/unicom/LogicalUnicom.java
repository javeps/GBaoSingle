package com.gb.logic.chn.unicom;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bowlong.net.http.HttpBaseEx;
import com.bowlong.util.MapEx;
import com.gb.logic.chn.unicom.commons.UnicomApp;
import com.gb.logic.chn.unicom.util.DateFomatter;
import com.gb.logic.chn.unicom.util.MD5;
import com.gb.logic.chn.unicom.util.PayBeanUtils;
import com.gb.logic.opt.model.LogicalRecordOrders;

/**
 * Unicom 联通SDK
 * @author Canyon
 * @version createtime：2016年8月18日 下午2:38:37
 */
public class LogicalUnicom implements Serializable {

	private static final long serialVersionUID = 1L;

	static Log log = LogFactory.getLog(LogicalUnicom.class);

	static public String handler(Map<String, String> map) throws Exception {

		String serviceid = MapEx.getString(map, "serviceid");

		// 根据serviceid，判断服务含义
		if ("validateorderid".equals(serviceid)) {
			map.remove("serviceid");
			// 校验订单
			return processValidateOrderId(map);
		} else if (serviceid == null || serviceid.isEmpty()) {

			// 支付结果通知
			return processPayNotify(map);
		}
		return "";
	}

	/**
	 * 处理校验订单请求
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	static private String processValidateOrderId(Map<String, String> params)
			throws IOException {
		Map<String, Object> validateResponse = new LinkedHashMap<String, Object>();

		// cp订单号
		String orderid = params.get("orderid");
		// 签名 MD5(orderid=XXX&Key=XXX)
		String signMsg = params.get("signMsg");
		// String usercode = params.get("usercode");
		// String provinceid = params.get("provinceid");
		// String cityid = params.get("cityid");

		// 校验签名是否正确
		String sign = String
				.format("orderid=%s&Key=%s", orderid, UnicomApp.KEY);
		String mySign = MD5.MD5Encode(sign);
		if (mySign.equalsIgnoreCase(signMsg)) {
			// TODO: start 填写校验订单逻辑，需要开发者完成

			/*
			 * 如果通过校验，将ifpasswd设置为true 通过校验的含义为，待校验的订单在开发者系统中为有效订单，可以继续支付
			 */
			boolean ifpasswd = true;

			// TODO: end

			if (ifpasswd) {
				// TODO: 通过订单获取必要信息，需开发者完成
				String serviceid = "";
				String feename = "";
				Integer payfee = 0;
				Date ordertime = new Date();
				String gameaccount = "";
				String macaddress = "";
				String ipaddress = "";
				String imei = "";
				String appversion = "";
				//

				// 0-验证成功 1-验证失败，必填
				validateResponse.put("checkOrderIdRsp", 0);

				// 应用名称，必填
				validateResponse.put("appName", UnicomApp.APP_NAME);

				// 计费点名称
				validateResponse.put("feename", feename);

				// 计费点金额，单位分
				validateResponse.put("payfee", payfee);

				// 应用开发商名称，必填
				validateResponse.put("appdeveloper", UnicomApp.APP_DEVELOPER);

				// 游戏账号，长度<=64，联网支付必填
				validateResponse.put("gameaccount", gameaccount);

				// MAC地址去掉冒号，联网支付必填，单机尽量上报
				validateResponse.put("macaddress", macaddress);

				// 沃商店应用id，必填
				validateResponse.put("appid", UnicomApp.APP_ID);

				// IP地址，去掉点号，补零到每地址段3位, 如：192168000001，联网必填，单机尽量
				validateResponse.put("ipaddress", ipaddress);

				// 沃商店计费点，必填
				validateResponse.put("serviceid", serviceid);

				// 渠道ID，必填
				validateResponse.put("channelid", UnicomApp.CHANNEL_ID);

				// 沃商店CPID，必填
				validateResponse.put("cpid", UnicomApp.CPID);

				// 订单时间戳，14位时间格式，联网必填，单机尽量
				validateResponse.put("ordertime",
						DateFomatter.format(ordertime));

				// 设备标识，联网必填，单机尽量上报
				validateResponse.put("imei", imei);

				// 应用版本号，必填
				validateResponse.put("appversion", appversion);
			} else {
				validateResponse.put("checkOrderIdRsp", 1);
			}
		} else {
			validateResponse.put("checkOrderIdRsp", 1);
		}

		// 将结果返回
		return PayBeanUtils.toValidateOrderidResponse(validateResponse);
	}

	/**
	 * 处理支付结果通知
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	static private String processPayNotify(Map<String, String> params)
			throws IOException {

		// 订单是否被处理
		boolean fullyProcessed = false;

		// cp订单号
		String orderid = params.get("orderid");
		// 订单时间
		String ordertime = params.get("ordertime");
		// 沃商店cpid
		String cpid = params.get("cpid");
		// 应用ID
		String appid = params.get("appid");
		// 渠道ID
		String fid = params.get("fid");
		// 计费点ID
		String consumeCode = params.get("consumeCode");
		// 支付金额，单位分
		String payfee = params.get("payfee");
		// 0-沃支付，1-支付宝，2-VAC支付，3-神州付 ...
		String payType = params.get("payType");
		// 支付结果，0代表成功，其他代表失败
		String hRet = params.get("hRet");
		// 状态码
		String status = params.get("status");
		// 签名
		// MD5(orderid=XXX&ordertime=XXX&cpid=XXX&appid=XXX&fid=XXX&consumeCode=XXX&payfee=XXX&payType=XXX&hRet=XXX&status=XXX&Key=XXX)
		String signMsg = params.get("signMsg");

		// 校验签名是否正确
		String signPattern = "orderid=%s&ordertime=%s&cpid=%s&appid=%s&fid=%s&consumeCode=%s&payfee=%s&payType=%s&hRet=%s&status=%s&Key=%s";
		String sign = String.format(signPattern, orderid, ordertime, cpid,
				appid, fid, consumeCode, payfee, payType, hRet, status,
				UnicomApp.KEY);
		String mySign = MD5.MD5Encode(sign);

		String cont = HttpBaseEx.buildQuery(params, "UTF-8");
		boolean isLog = false;

		if (mySign.equalsIgnoreCase(signMsg)) {
			// TODO： start 开发者处理逻辑, 处理完成后，将fullyProcessed设为ture
			if ("0".equals(hRet)) {
				// TODO: 添加支付成功逻辑
				int stateInt = LogicalRecordOrders.recordOrder(orderid, cont,
						"unicom");
				isLog = stateInt != 1;
			} else {
				// TODO: 添加支付失败逻辑
				isLog = true;
			}
			fullyProcessed = true;
		}

		if (isLog) {
			log.error("unicom fail cont=[" + cont + "]");
		}

		return PayBeanUtils.toPaynotifyResponse(fullyProcessed ? 1 : 0);
	}
}
