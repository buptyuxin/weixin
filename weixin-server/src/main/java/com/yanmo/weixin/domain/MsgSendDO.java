package com.yanmo.weixin.domain;

import java.util.Map;

/**
 * Created by yanmo.yx on 2015/4/1.
 */
public class MsgSendDO {
    private String sendUrl; // 请求的url地址
    private Map<String, String> params; // 请求的参数
    private Integer reqType; // 请求的方式，1->GET  2->POST

    public Integer getReqType() {
        return reqType;
    }

    public void setReqType(Integer reqType) {
        this.reqType = reqType;
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}
