package com.yanmo.weixin.service.impl;

import com.yanmo.weixin.domain.ResultDO;
import com.yanmo.weixin.domain.json.AccessTokenDO;
import com.yanmo.weixin.domain.json.BaseJsonDO;
import com.yanmo.weixin.log.WxLog;
import com.yanmo.weixin.parser.JsonParser;
import com.yanmo.weixin.service.AccessTokenService;
import com.yanmo.weixin.service.HttpClientService;
import com.yanmo.weixin.utils.EnvUtils;

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
        } else {
            WxLog.log(result.getErrorList().get(0).getName());
        }
        return "";
    }
}
