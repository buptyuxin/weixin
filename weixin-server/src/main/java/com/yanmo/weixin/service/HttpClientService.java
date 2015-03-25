package com.yanmo.weixin.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public interface HttpClientService {
    public CloseableHttpResponse httpGet(HttpGet httpGet);
}
