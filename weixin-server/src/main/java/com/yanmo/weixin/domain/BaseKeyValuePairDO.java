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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseKeyValuePairDO that = (BaseKeyValuePairDO) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;
        return true;
    }

    @Override
    public String toString() {
        return "BaseKeyValuePairDO{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
