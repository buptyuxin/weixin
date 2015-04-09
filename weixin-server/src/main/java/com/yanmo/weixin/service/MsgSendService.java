package com.yanmo.weixin.service;

import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.domain.MsgSendDO;

/**
 * Created by yanmo.yx on 2015/4/1.
 */
public interface MsgSendService {
    public void sendMsg(MsgDO msgDO, MsgSendDO msgSendDO);
}
