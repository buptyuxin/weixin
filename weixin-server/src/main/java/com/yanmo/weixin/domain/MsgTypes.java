package com.yanmo.weixin.domain;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
public enum MsgTypes {
    TEXT_MSG(1, "TEXT_MSG"),
    VOICE_MSG(2, "VOICE_MSG");

    private Integer type;
    private String name;

    MsgTypes(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
