package com.mservice.paygate.model;

import lombok.Builder;

public class RefundATMMoMoRequest extends PayATMRequest {


	public RefundATMMoMoRequest(String partnerCode, String orderId, String orderInfo, String accessKey, String amount, String signature, String extraData, String requestId, String notifyUrl, String returnUrl, String requestType, String bankCode, String transId) {
		super(partnerCode, orderId, orderInfo, accessKey, amount, signature, extraData, requestId, notifyUrl, returnUrl, requestType, bankCode);
		this.transId = transId;
	}

	String transId;

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}
}
