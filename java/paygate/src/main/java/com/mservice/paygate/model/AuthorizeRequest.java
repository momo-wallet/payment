package com.mservice.paygate.model;

public class AuthorizeRequest extends Request{

    private PartnerClientInfo partnerClientInfo;

    public PartnerClientInfo getPartnerClientInfo() {
        return partnerClientInfo;
    }

    public void setPartnerClientInfo(PartnerClientInfo partnerClientInfo) {
        this.partnerClientInfo = partnerClientInfo;
    }
}
