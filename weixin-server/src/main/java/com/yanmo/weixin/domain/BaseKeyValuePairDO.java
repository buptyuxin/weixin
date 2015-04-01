package com.yanmo.weixin.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
public class BaseKeyValuePairDO implements Serializable {
    private static final long serialVerisonUID = 6750854764071535411L;

    private String key;
    private String value;
    private List<BaseKeyValuePairDO> subPairs;

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

    public List<BaseKeyValuePairDO> getSubPairs() {
        return subPairs;
    }

    public void setSubPairs(List<BaseKeyValuePairDO> subPairs) {
        this.subPairs = subPairs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseKeyValuePairDO that = (BaseKeyValuePairDO) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (subPairs != null ? !subPairs.equals(that.subPairs) : that.subPairs != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (subPairs != null ? subPairs.hashCode() : 0);
        return result;
    }

    public boolean hasSubPairs() {
        if (subPairs != null && !subPairs.isEmpty()) {
            return true;
        }
        return false;
    }
}
