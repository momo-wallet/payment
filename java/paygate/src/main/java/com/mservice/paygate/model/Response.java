package com.mservice.paygate.model;

import com.google.gson.Gson;

import java.util.Date;

public class Response extends Request {

	public Response(String partnerCode, String orderId, String orderInfo, String accessKey, String amount, String signature, String extraData, String requestId, String notifyUrl, String returnUrl, String requestType, int errorCode, String message, String localMessage, String transId, String orderType, String payType, Date responseDate) {
		super(partnerCode, orderId, orderInfo, accessKey, amount, signature, extraData, requestId, notifyUrl, returnUrl, requestType);
		this.errorCode = errorCode;
		this.message = message;
		this.localMessage = localMessage;
		this.transId = transId;
		this.orderType = orderType;
		this.payType = payType;
		this.responseDate = responseDate;
	}

	private int errorCode;
	private String message;
	private String localMessage;

	private String transId;
	private String orderType;

	private String payType;

	private Date responseDate;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getLocalMessage() {
		return localMessage;
	}

	public void setLocalMessage(String localMessage) {
		this.localMessage = localMessage;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Date getResponseDate() {
		return responseDate;
	}

	public void setResponseDate(Date responseDate) {
		this.responseDate = responseDate;
	}

	public String getJsonObject() {
		return new Gson().toJson(this);
	}
}
