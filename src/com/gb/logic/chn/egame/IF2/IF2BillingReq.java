package com.gb.logic.chn.egame.IF2;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bowlong.security.MD5;
import com.gb.logic.chn.egame.LogicalEgame;

/**
 * 
 * @author Canyon
 * @version createtime：2016年8月15日 下午10:18:04
 */
@XmlRootElement(name = "sms_pay_check_resp")
public class IF2BillingReq implements Serializable {
	private static final long serialVersionUID = -5719870901076921628L;

	String appKey = LogicalEgame.AppKey;

	// CP业务流水号，由CP在游戏中支付发起时生成，CP应保证每次支付此流水号唯一不重复，
	// 并能依据此流水号区分支付产生的游戏、道具、价格、用户等相关信息 ，长度32位以内
	private String cp_order_id;

	private String correlator;// 爱游戏平台流水号（32位以内）

	private String result_code;// 00为扣费成功，其他状态码均为扣费不成功请勿发放道具，详见附录

	private int fee; // 计费金额，单位：元，服务器端请务必自行校验订购金额和计费金额是否一致

	private String pay_type; // 计费类型，smsPay：短代；alipay：支付宝；ipay：爱贝

	private String method;// 固定值“callback”

	private String sign;// MD5(cp_order_id+correlator+result_code+fee+paytype+method+appKey)

	private String version;// 回调接口版本号：当前为1

	public String getCp_order_id() {
		return cp_order_id;
	}

	@XmlElement(name = "cp_order_id")
	public void setCp_order_id(String cp_order_id) {
		this.cp_order_id = cp_order_id;
	}

	public String getCorrelator() {
		return correlator;
	}

	@XmlElement(name = "correlator")
	public void setCorrelator(String correlator) {
		this.correlator = correlator;
	}

	public String getResult_code() {
		return result_code;
	}

	@XmlElement(name = "result_code")
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}

	public int getFee() {
		return fee;
	}

	@XmlElement(name = "fee")
	public void setFee(int fee) {
		this.fee = fee;
	}

	public String getPay_type() {
		return pay_type;
	}

	@XmlElement(name = "pay_type")
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	public String getMethod() {
		return method;
	}

	@XmlElement(name = "method")
	public void setMethod(String method) {
		this.method = method;
	}

	public String getSign() {
		return sign;
	}

	@XmlElement(name = "sign")
	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getVersion() {
		return version;
	}

	@XmlElement(name = "version")
	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isSign() {
		String sign2 = MD5.toMD5(this.cp_order_id + this.correlator
				+ this.result_code + this.fee + this.pay_type + this.method
				+ this.appKey);
		return this.sign.equals(sign2);
	}
}
