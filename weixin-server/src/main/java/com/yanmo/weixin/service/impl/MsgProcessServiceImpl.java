package com.yanmo.weixin.service.impl;

import com.yanmo.weixin.domain.BaseKeyValuePairDO;
import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.msg.processor.BaseMsgProcessor;
import com.yanmo.weixin.service.MsgProcessService;
import com.yanmo.weixin.utils.XmlUtils;

import java.util.List;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class MsgProcessServiceImpl implements MsgProcessService {

    private List<BaseMsgProcessor> msgProcessors;

    public List<BaseMsgProcessor> getMsgProcessors() {
        return msgProcessors;
    }

    public void setMsgProcessors(List<BaseMsgProcessor> msgProcessors) {
        this.msgProcessors = msgProcessors;
    }

    @Override
    public String processXmlMsg(String xml) {
        MsgDO msgDO = XmlUtils.fromXml2Msg(xml);
        List<BaseKeyValuePairDO> properties = msgDO.getProperties();
        for (BaseKeyValuePairDO property : properties) {
            if (property.getKey() != null && "MsgType".equals(property.getKey())) {
                String msgType = property.getValue();
                if (msgType == null || msgType.isEmpty()) {
                    // 不处理
                    return null;
                }
                for (BaseMsgProcessor processor : msgProcessors) {
                    if (processor.getMsgType().equals(msgType)) {
                        return XmlUtils.fromMsg2Xml(processor.process(msgDO));
                    }
                }
                // TODO 没找到对应的processor处理，日志记录
            }
        }
        return null;
    }
}
