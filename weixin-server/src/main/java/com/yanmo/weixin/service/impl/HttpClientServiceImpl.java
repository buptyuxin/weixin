package com.yanmo.weixin.service.impl;

import com.google.common.collect.Lists;
import com.yanmo.weixin.service.HttpClientService;
import com.yanmo.weixin.utils.EnvUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class HttpClientServiceImpl implements HttpClientService {

    @Override
    public String doGet(String url) {
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse res = new DefaultHttpClient().execute(httpGet);
            if (res != null && res.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(res.getEntity(), EnvUtils.CHARSET);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String doPost(String url, Map<String, String> parameters) {
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> params = Lists.newArrayList();
        for (String key : parameters.keySet()) {
            params.add(new BasicNameValuePair(key, parameters.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params, EnvUtils.CHARSET));
            CloseableHttpResponse res = new DefaultHttpClient().execute(httpPost);
            if (res != null && res.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(res.getEntity(), EnvUtils.CHARSET);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
