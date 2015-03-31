package com.yanmo.weixin.service;

import com.yanmo.weixin.domain.MsgDO;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public interface RecvMsgService {
    public MsgDO receiveMsg(String msg);
}
