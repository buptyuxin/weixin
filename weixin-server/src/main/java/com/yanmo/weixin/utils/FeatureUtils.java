package com.yanmo.weixin.utils;

import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.log.WxLog;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
public class FeatureUtils {
    public static Integer getMsgType(MsgDO msgDO) {
        String features = msgDO.getFeatures();
        String[] kvs = features.split(";");
        for (String kv : kvs) {
            if (kv.contains("MsgType")) {
                String[] msgTypes = kv.split(":");
                return Integer.valueOf(msgTypes[1]);
            }
        }
        WxLog.log("消息类型错误，MsgDO=" + msgDO.toString());
        return null;
    }
}
