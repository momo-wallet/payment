package com.mservice.paygate.model;

public class NotifyInternalResponse extends Response{
    private String subscribeId;

    /**
     * For some services pay by subscribe command
     * @return
     */
    public String getSubscribeId() {
        return subscribeId;
    }

    public void setSubscribeId(String subscribeId) {
        this.subscribeId = subscribeId;
    }
}
