package com.yanmo.weixin.manager.impl;

import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.manager.MsgReplyManager;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by yanmo.yx on 2015/4/13.
 */
public class MsgReplyManagerImpl implements MsgReplyManager,InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        
    }

    @Override
    public MsgDO replyTextMsgDO(String content) {
        return null;
    }

    @Override
    public MsgDO replyEventMsgDO(String event) {
        return null;
    }
}
