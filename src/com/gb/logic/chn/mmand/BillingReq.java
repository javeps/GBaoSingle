
package com.gb.logic.chn.mmand;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author Canyon
 * @version createtime：2016年8月9日 下午8:57:07
 */
@XmlRootElement(name = "request")
public class BillingReq implements Serializable {
	private static final long serialVersionUID = -5719870901076921628L;

	@XmlElement(name="userId")
	private String userId;// 用户伪码

	@XmlElement(name="contentId")
	private String contentId;// 计费代码

	@XmlElement(name="consumeCode")
	private String consumeCode;// 道具计费代码

	@XmlElement(name="cpid")
	private String cpid;// 合作代码

	@XmlElement(name="hRet")
	private String hRet;// 平台计费结果（状态码外码）0-成功 其他-失败

	@XmlElement(name="status")
	private String status;// 返回状态（内码）

	@XmlElement(name="versionId")
	private String versionId;// 版本号2_0_0, 统一填写2_0_0

	@XmlElement(name="cpparam")
	private String cpparam;// CP透传参数
	
	@XmlElement(name="packageID")
	private String packageID;// 套餐包ID

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContentId() {
		return contentId;
	}

	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getConsumeCode() {
		return consumeCode;
	}

	public void setConsumeCode(String consumeCode) {
		this.consumeCode = consumeCode;
	}

	public String getCpid() {
		return cpid;
	}

	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public String gethRet() {
		return hRet;
	}

	public void sethRet(String hRet) {
		this.hRet = hRet;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersionId() {
		return versionId;
	}

	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getCpparam() {
		return cpparam;
	}

	public void setCpparam(String cpparam) {
		this.cpparam = cpparam;
	}

	public String getPackageID() {
		return packageID;
	}

	public void setPackageID(String packageID) {
		this.packageID = packageID;
	}
}
