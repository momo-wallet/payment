package com.mservice.paygate.model;

import java.util.HashMap;

public class Extra {
    private HashMap<String, String> data;

    public Extra() {
        this.data = new HashMap<>();
    }

    public void putParam(String key, String value) {
        data.put(key, value);
    }

    public String getValue(String key, String defualt) {

        return data.get(key) == null ? defualt : data.get(key);

    }

    public String getValue(String key) {

        return data.get(key) == null ? "" : data.get(key);
    }

    public void setValue(String key, String value) {
        data.put(key, value);
    }

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
}
