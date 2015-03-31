package com.yanmo.weixin.domain;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
public class MsgDO implements Serializable{
    private static final long serialVerisonUID = 6750852852071535411L;
    private List<BaseKeyValuePairDO> properties;
    private long options;

    // 用来区分消息类型，kv -> MsgType:1;
    // 业务区分
    private String features;

    public List<BaseKeyValuePairDO> getProperties() {
        return properties;
    }

    public void setProperties(List<BaseKeyValuePairDO> properties) {
        this.properties = properties;
    }

    public long getOptions() {
        return options;
    }

    public void setOptions(long options) {
        this.options = options;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public boolean addProperty(BaseKeyValuePairDO kv) {
        if (kv == null) {
            return false;
        }
        if (kv.getKey() == null) {
            return false;
        }
        if (properties == null) {
            properties = Lists.newArrayList();
        }
        if (!properties.isEmpty()) {
            for (BaseKeyValuePairDO property : properties) {
                if (kv.getKey().equals(property.getKey())) {
                    property.setValue(kv.getValue());
                    return true;
                }
            }
        }
        properties.add(kv);
        return true;
    }

    @Override
    public String toString() {
        return "MsgDO{" +
                "properties=" + properties +
                ", options=" + options +
                ", features='" + features + '\'' +
                '}';
    }
}
