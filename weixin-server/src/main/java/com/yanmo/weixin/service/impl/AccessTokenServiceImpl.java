package com.yanmo.weixin.service.impl;

import com.yanmo.weixin.service.AccessTokenService;
import com.yanmo.weixin.service.HttpClientService;
import com.yanmo.weixin.utils.EnvUtils;
import com.yanmo.weixin.utils.JsonUtils;
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

    private HttpClientService httpClientService;

    @Override
    public String queryAccessToken() {
        HttpGet getAccessToken = new HttpGet(EnvUtils.ACCESS_TOKEN_GET_URI);
        CloseableHttpResponse res = httpClientService.httpGet(getAccessToken);
        HttpEntity entity = res.getEntity();
        try {
            InputStream in = entity.getContent();
            StringBuffer sb = new StringBuffer();
            String line = null;
            try {
                while ((line=new BufferedReader(new InputStreamReader(in, "UTF-8")).readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String json = sb.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
