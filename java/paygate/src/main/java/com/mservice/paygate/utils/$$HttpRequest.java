package com.mservice.paygate.utils;

public class $$HttpRequest {
	
    private String method;
    private String data;
    private String url;
    private Headers headers;

    public $$HttpRequest() {
    }

    public $$HttpRequest(String method, String data, String url, Headers headers) {
        this.method = method;
        this.data = data;
        this.url = url;
        this.headers = headers;
    }

    public String getMethod() {
        return method;
    }

    public $$HttpRequest setMethod(String method) {
        this.method = method;
        return this;
    }

    public String getData() {
        return data;
    }

    public $$HttpRequest setData(String data) {
        this.data = data;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public $$HttpRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public Headers getHeaders() {
        return headers;
    }

    public $$HttpRequest setHeaders(Headers headers) {
        this.headers = headers;
        return this;
    }
}
