package com.yanmo.weixin.service.impl;

import com.yanmo.weixin.service.HttpClientService;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
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
                return EntityUtils.toString(res.getEntity(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String doPost(String url, Map<String, String> parameters, String data) {
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setHost(url);
        if (parameters != null && !parameters.isEmpty()) {
            for (String key : parameters.keySet()) {
                uriBuilder.setParameter(key, parameters.get(key));
            }
        }
        URI uri = null;
        try {
            uri = uriBuilder.build();
        } catch (URISyntaxException e) {
            return null;
        }
        HttpPost httpPost = new HttpPost(uri);
        try {
            StringEntity entity = new StringEntity(data, StandardCharsets.UTF_8);
            entity.setContentType("text/xml");
            entity.setContentEncoding(StandardCharsets.UTF_8.toString());
            httpPost.setEntity(entity);
            CloseableHttpResponse res = new DefaultHttpClient().execute(httpPost);
            if (res != null && res.getStatusLine().getStatusCode() == 200) {
                return EntityUtils.toString(res.getEntity(), StandardCharsets.UTF_8);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
