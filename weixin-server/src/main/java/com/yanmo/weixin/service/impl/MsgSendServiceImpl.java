package com.yanmo.weixin.service.impl;

import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.domain.MsgSendDO;
import com.yanmo.weixin.service.HttpClientService;
import com.yanmo.weixin.service.MsgSendService;
import com.yanmo.weixin.utils.XmlUtils;

/**
 * Created by yanmo.yx on 2015/4/1.
 */
public class MsgSendServiceImpl implements MsgSendService {

    private HttpClientService httpClientService;

    public HttpClientService getHttpClientService() {
        return httpClientService;
    }

    public void setHttpClientService(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Override
    public void sendMsg(MsgDO msgDO, MsgSendDO msgSendDO) {
        if (msgSendDO == null) {
            return;
        }
        Integer reqType = msgSendDO.getReqType();
        if (reqType != null) {
            if (reqType.intValue() == 2) {
                String xml = XmlUtils.fromMsg2Xml(msgDO);
                httpClientService.doPost(msgSendDO.getSendUrl(), msgSendDO.getParams(), xml);
            }
        }
    }
}
