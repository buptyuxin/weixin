package com.yanmo.weixin.domain;

import java.io.Serializable;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
public class BaseKeyValuePairDO implements Serializable {
    private static final long serialVerisonUID = 6750854764071535411L;

    private String key;
    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
