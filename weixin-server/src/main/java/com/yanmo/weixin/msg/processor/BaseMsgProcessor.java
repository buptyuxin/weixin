package com.yanmo.weixin.msg.processor;

import com.yanmo.weixin.domain.BaseKeyValuePairDO;
import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.domain.ResultDO;
import com.yanmo.weixin.msg.rule.ReplyRuleDO;

import java.util.Map;

/**
 * Created by yanmo.yx on 2015/4/1.
 */
public abstract class BaseMsgProcessor {

    private ReplyRuleDO replyRuleDO;

    public void setReplyRuleDO(ReplyRuleDO replyRuleDO) {
        this.replyRuleDO = replyRuleDO;
    }

    public MsgDO process(MsgDO recvMsg) {
        if (recvMsg == null) {
            // TODO
            return null;
        }

        ResultDO<MsgDO> result = processMsg(recvMsg);
        if (!result.isSuccess()) {
            // TODO 处理失败，日志记录
        }
        MsgDO replyMsg = result.getModule();

        // 填充发送方、接收方、创建时间等公共属性
        fillReplyMsg(recvMsg, replyMsg);
        return replyMsg;
    }

    private void fillReplyMsg(MsgDO recvMsg, MsgDO replyMsg) {
        // 对应回复规则，主要是发送、接收方
        Map<String, String> replyRule = replyRuleDO.getRuleMap();
        for (String key : replyRule.keySet()) {
            BaseKeyValuePairDO replyProperty = new BaseKeyValuePairDO();
            for (BaseKeyValuePairDO kv : recvMsg.getProperties()) {
                if (key.equals(kv.getKey())) {
                    replyProperty.setKey(replyRule.get(key));
                    replyProperty.setValue(kv.getValue());
                    replyMsg.addProperty(replyProperty);
                    break;
                }
            }
        }

        // 回复消息时间
        BaseKeyValuePairDO createTime = new BaseKeyValuePairDO();
        createTime.setKey("CreateTime");
        createTime.setValue(String.valueOf(System.currentTimeMillis()));
        replyMsg.addProperty(createTime);
    }

    public abstract ResultDO<MsgDO> processMsg(MsgDO msgDO);

    public abstract String getMsgType();
}
