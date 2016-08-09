/**
 * @author kimi
 * @dateTime 2013-4-28 下午4:09:13
 */
package com.gb.logic.chn.mmand;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Canyon
 * @version createtime：2016年8月9日 下午9:23:18
 */
@XmlRootElement(name = "request")
public class BillingResp implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message; // CP响应的消息，比如“Successful”或是合作方自定义的失败原因

	private Integer hRet; // 0-通知成功；其它-其他错误

	public String getMessage() {
		return message;
	}
	@XmlElement(name = "Version")
	public void setMessage(String message) {
		this.message = message;
	}

	public Integer gethRet() {
		return hRet;
	}
	@XmlElement(name = "hRet")
	public void sethRet(Integer hRet) {
		this.hRet = hRet;
	}
}
