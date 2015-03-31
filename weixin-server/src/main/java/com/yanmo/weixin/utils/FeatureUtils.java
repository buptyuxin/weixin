package com.yanmo.weixin.utils;

import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.domain.constant.MsgConstants;
import com.yanmo.weixin.log.WxLog;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
public class FeatureUtils {
    public static Integer getMsgType(MsgDO msgDO) {
        String features = msgDO.getFeatures();
        String[] kvs = features.split(";");
        for (String kv : kvs) {
            if (kv.contains(MsgConstants.MSG_TYPE)) {
                String[] msgTypes = kv.split(":");
                return Integer.valueOf(msgTypes[1]);
            }
        }
        WxLog.log("消息类型错误，MsgDO=" + msgDO.toString());
        return null;
    }

    public static boolean setMsgType(MsgDO msgDO, Integer msgType) {
        if (msgType == null) {
            return false;
        }
        String features = msgDO.getFeatures();
        String[] kvs = features.split(";");
        StringBuilder sb = new StringBuilder();
        for (String kv : kvs) {
            if (kv.contains(MsgConstants.MSG_TYPE)) {
                // 老的消息类型直接废弃
                continue;
            }
            sb.append(kv);
            sb.append(";");
        }
        sb.append(MsgConstants.MSG_TYPE);
        sb.append(":");
        sb.append(msgType.toString());
        sb.append(";");
        features = sb.toString();
        msgDO.setFeatures(features);
        return true;
    }
}
