package com.yanmo.weixin.manager;

import com.yanmo.weixin.domain.MsgDO;

/**
 * Created by yanmo.yx on 2015/4/13.
 */
public interface MsgReplyManager {
    public MsgDO replyTextMsgDO(String content);

    public MsgDO replyEventMsgDO(String event);
}
