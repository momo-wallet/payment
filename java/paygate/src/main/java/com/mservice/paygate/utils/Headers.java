package com.mservice.paygate.utils;

import java.util.HashMap;
import java.util.Map;

public class Headers {
    public Map<String, String> headers = new HashMap<>();

    public Headers put(String key, String value){
        headers.put(key, value);
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }
}
