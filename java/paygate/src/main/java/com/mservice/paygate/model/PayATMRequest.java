package com.mservice.paygate.model;

public class PayATMRequest extends Request{
    private String bankCode;

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }
}
