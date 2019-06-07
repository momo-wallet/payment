package com.mservice.paygate.model;

import lombok.Builder;

import java.util.Date;

public class CaptureMoMoResponse extends Response {

	public CaptureMoMoResponse(String partnerCode, String orderId, String orderInfo, String accessKey, String amount, String signature, String extraData, String requestId, String notifyUrl, String returnUrl, String requestType, int errorCode, String message, String localMessage, String transId, String orderType, String payType, Date responseDate, String payUrl, String deeplink, String qrCodeUrl, String deeplinkWebInApp) {
		super(partnerCode, orderId, orderInfo, accessKey, amount, signature, extraData, requestId, notifyUrl, returnUrl, requestType, errorCode, message, localMessage, transId, orderType, payType, responseDate);
		this.payUrl = payUrl;
		this.deeplink = deeplink;
		this.qrCodeUrl = qrCodeUrl;
		this.deeplinkWebInApp = deeplinkWebInApp;
	}


	private String payUrl;

	private String deeplink;

	private String qrCodeUrl;

	private String deeplinkWebInApp;

	public String getDeeplink() {
		return deeplink;
	}

	public void setDeeplink(String deeplink) {
		this.deeplink = deeplink;
	}

	public String getQrCodeUrl() {
		return qrCodeUrl;
	}

	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}

	public String getPayUrl() {
		return payUrl;
	}

	public void setPayUrl(String payUrl) {
		this.payUrl = payUrl;
	}

	public String getDeeplinkWebInApp() {
		return deeplinkWebInApp;
	}

	public void setDeeplinkWebInApp(String deeplinkWebInApp) {
		this.deeplinkWebInApp = deeplinkWebInApp;
	}

}
