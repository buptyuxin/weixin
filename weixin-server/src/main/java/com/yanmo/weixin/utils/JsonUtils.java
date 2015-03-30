package com.yanmo.weixin.utils;

import com.google.gson.Gson;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class JsonUtils {
    private static final Gson gson = new Gson();

    public static String toJson(Object o) {
        return gson.toJson(o);
    }

    public static <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
