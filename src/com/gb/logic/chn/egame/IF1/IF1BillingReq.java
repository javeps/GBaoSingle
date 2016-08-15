package com.gb.logic.chn.egame.IF1;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.bowlong.security.MD5;

/**
 * 
 * @author Canyon
 * @version createtime：2016年8月15日 下午10:18:04
 */
@XmlRootElement(name = "sms_pay_check_resp")
public class IF1BillingReq implements Serializable {

	private static final long serialVersionUID = -5719870901076921628L;

	String appKey = "";

	// CP业务流水号，由CP在游戏中支付发起时生成，CP应保证每次支付此流水号唯一不重复，
	// 并能依据此流水号区分支付产生的游戏、道具、价格、用户等相关信息 ，长度32位以内
	private String cp_order_id;

	private String correlator;// 爱游戏平台流水号（32位以内）

	private String order_time;// 订单时间戳，14位时间格式(yyyyMMddHHmmss)

	private String method;// 固定值“check”

	private String sign;// MD5(cp_order_id+correlator+order_time+method+appKey)

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

	public String getOrder_time() {
		return order_time;
	}

	@XmlElement(name = "order_time")
	public void setOrder_time(String order_time) {
		this.order_time = order_time;
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
				+ this.order_time + this.method + this.appKey);
		return this.sign.equals(sign2);
	}

}
