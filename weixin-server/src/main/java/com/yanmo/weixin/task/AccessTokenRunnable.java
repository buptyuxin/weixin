package com.yanmo.weixin.task;

import com.yanmo.weixin.service.AccessTokenService;
import com.yanmo.weixin.utils.EnvUtils;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class AccessTokenRunnable implements Runnable {

    AccessTokenService accessTokenService;

    public AccessTokenService getAccessTokenService() {
        return accessTokenService;
    }

    public void setAccessTokenService(AccessTokenService accessTokenService) {
        this.accessTokenService = accessTokenService;
    }

    @Override
    public void run() {
        // 使用httpclient获取access_token
        String access_token = accessTokenService.queryAccessToken();
        EnvUtils.ACCESS_TOKEN = access_token;
    }
}
