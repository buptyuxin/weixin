package com.yanmo.weixin.task;

import com.yanmo.weixin.utils.EnvUtils;

import java.util.TimerTask;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class AccessTokenTask extends TimerTask {
    @Override
    public void run() {
        // 使用httpclient获取access_token
        // TODO
        String access_token = "";
        EnvUtils.ACCESS_TOKEN = access_token;
    }
}
