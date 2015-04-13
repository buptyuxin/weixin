package com.yanmo.weixin.msg.processor;

import com.yanmo.weixin.domain.BaseKeyValuePairDO;
import com.yanmo.weixin.domain.Errors;
import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.domain.ResultDO;

import java.io.UnsupportedEncodingException;

/**
 * Created by yanmo.yx on 2015/4/3.
 */
public class EventMsgProcessor extends BaseMsgProcessor {
    @Override
    public ResultDO<MsgDO> processMsg(MsgDO recvMsg) {
        ResultDO<MsgDO> result = new ResultDO<MsgDO>();
        MsgDO replyMsg = new MsgDO();

        // 回复消息
        BaseKeyValuePairDO msgType = new BaseKeyValuePairDO();
        msgType.setKey("MsgType");
        msgType.setValue("text");
        replyMsg.addProperty(msgType);

        BaseKeyValuePairDO content = new BaseKeyValuePairDO();
        content.setKey("Content");
        for (BaseKeyValuePairDO kv : recvMsg.getProperties()) {
            if (!"Event".equals(kv.getKey())) {
                continue;
            }
            if ("subscribe".equals(kv.getValue())) {
                content.setValue("感谢你关注鱼子酱的订阅号");
            } else if ("unsubscribe".equals(kv.getValue())) {
                content.setValue(" ");
            }
            replyMsg.addProperty(content);
            result.setModule(replyMsg);
            return result;
        }
        result.addError(Errors.PARSE_XML_ERROR);
        return result;
    }

    @Override
    public String getMsgType() {
        return "event";
    }
}
