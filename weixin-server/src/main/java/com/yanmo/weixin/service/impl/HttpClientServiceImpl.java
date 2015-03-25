package com.yanmo.weixin.service.impl;

import com.yanmo.weixin.service.HttpClientService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class HttpClientServiceImpl implements HttpClientService {

    private CloseableHttpClient httpClient;

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public CloseableHttpResponse httpGet(HttpGet httpGet) {
        CloseableHttpResponse res = null;
        try {
            res = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
