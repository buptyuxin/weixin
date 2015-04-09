package com.yanmo.weixin.msg.processor;

import com.yanmo.weixin.domain.BaseKeyValuePairDO;
import com.yanmo.weixin.domain.Errors;
import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.domain.ResultDO;
import com.yanmo.weixin.service.MsgSendService;

/**
 * Created by yanmo.yx on 2015/4/1.
 */
public class TextMsgProcessor extends BaseMsgProcessor {

    private MsgSendService msgSendService;

    public void setMsgSendService(MsgSendService msgSendService) {
        this.msgSendService = msgSendService;
    }

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
        msgType.setKey("Content");
        for (BaseKeyValuePairDO kv : recvMsg.getProperties()) {
            if (!"Content".equals(kv.getKey())) {
                continue;
            }
            content.setValue(kv.getValue());
            replyMsg.addProperty(content);
            result.setModule(replyMsg);
            return result;
        }
        result.addError(Errors.PARSE_XML_ERROR);
        return result;
    }

    @Override
    public String getMsgType() {
        return "text";
    }
}
