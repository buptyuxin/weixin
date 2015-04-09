package com.yanmo.weixin.msg.creator;

import com.yanmo.weixin.domain.BaseKeyValuePairDO;
import com.yanmo.weixin.domain.MsgDO;

/**
 * Created by yanmo.yx on 2015/4/2.
 */
public abstract class BaseMsgCreator {
    public MsgDO createMsg(String toUserName, String fromUserName, String msgType, BaseKeyValuePairDO property) {

        MsgDO msgDO = new MsgDO();

        BaseKeyValuePairDO toUserNameKv = new BaseKeyValuePairDO();
        toUserNameKv.setKey("toUserName");
        toUserNameKv.setValue(toUserName);
        msgDO.addProperty(toUserNameKv);

        BaseKeyValuePairDO fromUserNameKv = new BaseKeyValuePairDO();
        fromUserNameKv.setKey("fromUserName");
        fromUserNameKv.setValue(fromUserName);
        msgDO.addProperty(fromUserNameKv);

        BaseKeyValuePairDO createTimeKv = new BaseKeyValuePairDO();
        createTimeKv.setKey("createTime");
        createTimeKv.setValue(String.valueOf(System.currentTimeMillis()));
        msgDO.addProperty(createTimeKv);

        BaseKeyValuePairDO msgTypeKv = new BaseKeyValuePairDO();
        msgTypeKv.setKey("msgType");
        msgTypeKv.setValue(msgType);
        msgDO.addProperty(msgTypeKv);

        msgDO.addProperty(property);

        return msgDO;
    }
}
