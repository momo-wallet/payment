package com.mservice.paygate.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by Hai.Nguyen Date: 19-01-2018
 */
@Getter
@AllArgsConstructor
public class Request {
	
	private String partnerCode;
	private String orderId;
	private String orderInfo;
	private String accessKey;
	private String amount;
	private String signature;
	private String extraData;
	private String requestId;

	private String notifyUrl;
	private String returnUrl;
	private String requestType;

	public String getPartnerCode() {
		return partnerCode;
	}

	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getExtraData() {
		return extraData;
	}

	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	@Override
    public String toString() {
        return "Request{" +
                "partnerCode='" + partnerCode + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderInfo='" + orderInfo + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", amount='" + amount + '\'' +
                ", signature='" + signature + '\'' +
                ", extraData='" + extraData + '\'' +
                ", requestId='" + requestId + '\'' +
                ", notifyUrl='" + notifyUrl + '\'' +
                ", returnUrl='" + returnUrl + '\'' +
                ", requestType='" + requestType + '\'' +
                '}';
    }
}
