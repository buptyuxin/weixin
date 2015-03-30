package com.yanmo.weixin.parser;

import com.yanmo.weixin.domain.Errors;
import com.yanmo.weixin.domain.ResultDO;
import com.yanmo.weixin.domain.json.BaseJsonDO;
import com.yanmo.weixin.domain.xml.BaseXmlDO;
import com.yanmo.weixin.log.WxLog;
import com.yanmo.weixin.utils.JsonUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
public class XmlParser {
    private Map<List<String>, Class<? extends BaseXmlDO>> xmlMaps;

    public Map<List<String>, Class<? extends BaseXmlDO>> getXmlMaps() {
        return xmlMaps;
    }

    public void setXmlMaps(Map<List<String>, String> xmlMaps) {
        for (List<String> keys : xmlMaps.keySet()) {
            if (keys != null && !keys.isEmpty()) {
                try {
                    Class.forName("");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Class<?> clazz = null;
                try {
                    clazz = Thread.currentThread().getContextClassLoader().loadClass(xmlMaps.get(keys));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    continue;
                }
                if (clazz != null && BaseJsonDO.class.isAssignableFrom(clazz)) {
                    this.xmlMaps.put(keys, (Class<? extends BaseXmlDO>) clazz);
                    continue;
                }
                WxLog.log("类错误");
                continue;
            }
            WxLog.log("关键词错误");
        }
    }

    public ResultDO<BaseXmlDO> parseXml(String xml) {
        ResultDO result = new ResultDO();
        if (StringUtils.isEmpty(xml)) {
            return result;
        }

        Class<? extends BaseXmlDO> clazz = null;
        boolean isSucc = true;
        for (List<String> keys : xmlMaps.keySet()) {
            isSucc = true;
            for (String key : keys) {
                // 必须包含所有的关键词
                if (!xml.contains(key)) {
                    isSucc = false;
                    break;
                }
            }
            if (!isSucc) {
                continue;
            }
            clazz = xmlMaps.get(keys);
            break;
        }

        if (clazz != null) {
            BaseXmlDO res = JsonUtils.fromJson(xml, clazz);
            result.setModule(res);
        } else {
            result.addError(Errors.PARSE_ACCESS_TOKEN_ERROR);
        }
        return result;
    }
}
