package com.mservice.paygate.model;

public class Environment {

    private String momoEndpoint;
    private PartnerInfo partnerInfo;
    private String target;

    public Environment(String momoEndpoint, PartnerInfo partnerInfo, String target) {
        this.momoEndpoint = momoEndpoint;
        this.partnerInfo = partnerInfo;
        this.target = target;
    }

    public String getMomoEndpoint() {
        return momoEndpoint;
    }

    public void setMomoEndpoint(String momoEndpoint) {
        this.momoEndpoint = momoEndpoint;
    }

    public PartnerInfo getPartnerInfo() {
        return partnerInfo;
    }

    public void setPartnerInfo(PartnerInfo partnerInfo) {
        this.partnerInfo = partnerInfo;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
