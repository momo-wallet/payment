package com.mservice.paygate.model;

public class PartnerInfo {
	
    private String accessKey;
    private String partnerCode;
    private String secretKey;

    public PartnerInfo(String partnerCode, String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.partnerCode = partnerCode;
        this.secretKey = secretKey;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}
