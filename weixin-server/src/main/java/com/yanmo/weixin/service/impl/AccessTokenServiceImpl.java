package com.yanmo.weixin.service.impl;

import com.yanmo.weixin.domain.AccessTokenDO;
import com.yanmo.weixin.domain.BaseJsonDO;
import com.yanmo.weixin.domain.ResultDO;
import com.yanmo.weixin.parser.JsonParser;
import com.yanmo.weixin.service.AccessTokenService;
import com.yanmo.weixin.service.HttpClientService;
import com.yanmo.weixin.utils.EnvUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class AccessTokenServiceImpl implements AccessTokenService {

    private JsonParser jsonParser;

    private HttpClientService httpClientService;

    public JsonParser getJsonParser() {
        return jsonParser;
    }

    public void setJsonParser(JsonParser jsonParser) {
        this.jsonParser = jsonParser;
    }

    public HttpClientService getHttpClientService() {
        return httpClientService;
    }

    public void setHttpClientService(HttpClientService httpClientService) {
        this.httpClientService = httpClientService;
    }

    @Override
    public String queryAccessToken() {
        String json = httpClientService.doGet(EnvUtils.ACCESS_TOKEN_GET_URI);
        ResultDO<BaseJsonDO> result = jsonParser.parseJson(json);
        if (result.isSuccess()) {
            return ((AccessTokenDO)result.getModule()).getAccessToken();
        }
        return "";
    }
}
