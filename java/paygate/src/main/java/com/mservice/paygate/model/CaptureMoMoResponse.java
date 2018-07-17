package com.mservice.paygate.model;

public class CaptureMoMoResponse extends Response {

	private String payUrl;

	private String deeplink;

	private String qrCodeUrl;

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
}
