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

	private String userId;// 用户伪码

	private String contentId;// 计费代码

	private String consumeCode;// 道具计费代码

	private String cpid;// 合作代码

	private String hRet;// 平台计费结果（状态码外码）0-成功 其他-失败

	private String status;// 返回状态（内码）

	private String versionId;// 版本号2_0_0, 统一填写2_0_0

	private String cpparam;// CP透传参数

	private String packageID;// 套餐包ID

	public String getUserId() {
		return userId;
	}

	@XmlElement(name = "userId")
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getContentId() {
		return contentId;
	}

	@XmlElement(name = "contentId")
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}

	public String getConsumeCode() {
		return consumeCode;
	}

	@XmlElement(name = "consumeCode")
	public void setConsumeCode(String consumeCode) {
		this.consumeCode = consumeCode;
	}

	public String getCpid() {
		return cpid;
	}

	@XmlElement(name = "cpid")
	public void setCpid(String cpid) {
		this.cpid = cpid;
	}

	public String gethRet() {
		return hRet;
	}

	@XmlElement(name = "hRet")
	public void sethRet(String hRet) {
		this.hRet = hRet;
	}

	public String getStatus() {
		return status;
	}

	@XmlElement(name = "status")
	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersionId() {
		return versionId;
	}

	@XmlElement(name = "versionId")
	public void setVersionId(String versionId) {
		this.versionId = versionId;
	}

	public String getCpparam() {
		return cpparam;
	}

	@XmlElement(name = "cpparam")
	public void setCpparam(String cpparam) {
		this.cpparam = cpparam;
	}

	public String getPackageID() {
		return packageID;
	}

	@XmlElement(name="packageID")
	public void setPackageID(String packageID) {
		this.packageID = packageID;
	}
}
