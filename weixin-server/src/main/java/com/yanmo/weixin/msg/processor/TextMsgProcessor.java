package com.yanmo.weixin.msg.processor;

import com.yanmo.weixin.domain.BaseKeyValuePairDO;
import com.yanmo.weixin.domain.Errors;
import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.domain.ResultDO;
import com.yanmo.weixin.log.WxLog;
import com.yanmo.weixin.service.MsgSendService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by yanmo.yx on 2015/4/1.
 */
public class TextMsgProcessor extends BaseMsgProcessor {

    private MsgSendService msgSendService;
    private List<String> jokes;

    public void setMsgSendService(MsgSendService msgSendService) {
        this.msgSendService = msgSendService;
    }

    public void setJokes(List<String> jokes) {
        this.jokes = jokes;
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
        content.setKey("Content");
        for (BaseKeyValuePairDO kv : recvMsg.getProperties()) {
            if (!"Content".equals(kv.getKey())) {
                continue;
            }
            int x = (new Random()).nextInt(jokes.size());
            SimpleDateFormat today = new SimpleDateFormat("yyyy-MM-dd");
            String text = today.format(new Date()) + "\n\n";
            content.setValue(text+jokes.get(x).replaceAll("\r\n", "\n"));
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
