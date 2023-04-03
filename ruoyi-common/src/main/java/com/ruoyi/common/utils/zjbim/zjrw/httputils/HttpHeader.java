package com.ruoyi.common.utils.zjbim.zjrw.httputils;

import java.util.HashMap;
import java.util.Map;

public class HttpHeader {
    private Map<String, String> params = new HashMap<String, String>();

    public HttpHeader addParam(String name, String value) {
        this.params.put(name, value);
        return this;
    }

    public Map<String, String> getParams() {
        return this.params;
    }
}
