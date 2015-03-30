package com.yanmo.weixin.utils;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
@Deprecated
public class XmlUtils {
    public static <T> T fromXml(String xml, Class<T> clazz) {
        try {
            T obj = clazz.newInstance();
            return obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
