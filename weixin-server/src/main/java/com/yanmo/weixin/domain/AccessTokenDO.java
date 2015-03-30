package com.yanmo.weixin.domain;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class AccessTokenDO extends BaseJsonDO {
    private String accessToken;
    private Long expiresIn;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
