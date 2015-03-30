package com.yanmo.weixin.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import java.util.Map;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public interface HttpClientService {
    public String doGet(String url);

    public String doPost(String url, Map<String, String> parameters);
}
