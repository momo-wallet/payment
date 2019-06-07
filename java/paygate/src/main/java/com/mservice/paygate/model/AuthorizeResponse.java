package com.mservice.paygate.model;

import java.util.Date;

public class AuthorizeResponse extends PaymentResponse {

	public AuthorizeResponse(String partnerCode, String orderId, String orderInfo, String accessKey, String amount, String signature, String extraData, String requestId, String notifyUrl, String returnUrl, String requestType, int errorCode, String message, String localMessage, String transId, String orderType, String payType, Date responseDate, String payUrl, String deeplink, String deeplinkWebInApp, String qrCodeUrl, String hash) {
		super(partnerCode, orderId, orderInfo, accessKey, amount, signature, extraData, requestId, notifyUrl, returnUrl, requestType, errorCode, message, localMessage, transId, orderType, payType, responseDate, payUrl, deeplink, deeplinkWebInApp, qrCodeUrl);
		this.hash = hash;
	}

	private String hash;

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}
}
