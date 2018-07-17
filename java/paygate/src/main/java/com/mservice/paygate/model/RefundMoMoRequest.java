package com.mservice.paygate.model;

public class RefundMoMoRequest extends Request {
	
	private String transId;

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}
}
