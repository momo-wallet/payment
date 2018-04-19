package com.mservice.paygate.model;

public class AuthorizeResponse extends PaymentResponse{
    private String hash;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
