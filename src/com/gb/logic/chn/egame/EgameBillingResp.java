/**
 * @author kimi
 * @dateTime 2013-4-28 下午4:09:13
 */
package com.gb.logic.chn.egame;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Canyon
 * @version createtime：2016年8月15日 下午10:23:22
 */
@XmlRootElement(name = "request")
public class EgameBillingResp implements Serializable {

	private static final long serialVersionUID = 1L;

	private String cp_order_id; // CP流水号

	private Integer h_ret; // 0-通知成功；其它-其他错误

	public String getCp_order_id() {
		return cp_order_id;
	}

	@XmlElement(name = "cp_order_id")
	public void setCp_order_id(String cp_order_id) {
		this.cp_order_id = cp_order_id;
	}

	public Integer getH_ret() {
		return h_ret;
	}

	@XmlElement(name = "h_ret")
	public void setH_ret(Integer h_ret) {
		this.h_ret = h_ret;
	}
}
