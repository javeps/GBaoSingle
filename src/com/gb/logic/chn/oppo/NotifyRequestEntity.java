package com.gb.logic.chn.oppo;

import java.io.Serializable;

public class NotifyRequestEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private String notifyId;
	private String partnerOrder;
	private String productName;
	private String productDesc;
	private int price;
	private int count;
	private String attach;
	private String sign;

	public String getNotifyId() {
		return notifyId == null ? "" : notifyId;
	}

	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}

	public String getPartnerOrder() {
		return partnerOrder == null ? "" : partnerOrder;
	}

	public void setPartnerOrder(String partnerOrder) {
		this.partnerOrder = partnerOrder;
	}

	public String getProductName() {
		return productName == null ? "" : productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDesc() {
		return productDesc == null ? "" : productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getAttach() {
		return attach == null ? "" : attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getSign() {
		return sign == null ? "" : sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
