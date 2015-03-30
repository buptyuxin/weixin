package com.yanmo.weixin.parser;

import com.yanmo.weixin.domain.json.BaseJsonDO;
import com.yanmo.weixin.domain.Errors;
import com.yanmo.weixin.domain.ResultDO;
import com.yanmo.weixin.log.WxLog;
import com.yanmo.weixin.utils.JsonUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
public class JsonParser {

    private Map<List<String>, Class<? extends BaseJsonDO>> jsonMaps;

    public Map<List<String>, Class<? extends BaseJsonDO>> getJsonMaps() {
        return jsonMaps;
    }

    public void setJsonMaps(Map<List<String>, String> jsonMaps) {
        for (List<String> keys : jsonMaps.keySet()) {
            if (keys != null && !keys.isEmpty()) {
                Class<?> clazz = null;
                try {
                    clazz = Thread.currentThread().getContextClassLoader().loadClass(jsonMaps.get(keys));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    continue;
                }
                if (clazz != null && BaseJsonDO.class.isAssignableFrom(clazz)) {
                    this.jsonMaps.put(keys, (Class<? extends BaseJsonDO>) clazz);
                    continue;
                }
                WxLog.log("类错误");
                continue;
            }
            WxLog.log("关键词错误");
        }
    }

    public ResultDO<BaseJsonDO> parseJson(String json) {
        ResultDO result = new ResultDO();
        if (StringUtils.isEmpty(json)) {
            return result;
        }

        Class<? extends BaseJsonDO> clazz = null;
        boolean isSucc = true;
        for (List<String> keys : jsonMaps.keySet()) {
            isSucc = true;
            for (String key : keys) {
                // 必须包含所有的关键词
                if (!json.contains(key)) {
                    isSucc = false;
                    break;
                }
            }
            if (!isSucc) {
                continue;
            }
            clazz = jsonMaps.get(keys);
            break;
        }

        if (clazz != null) {
            BaseJsonDO res = JsonUtils.fromJson(json, clazz);
            result.setModule(res);
        } else {
            result.addError(Errors.PARSE_ACCESS_TOKEN_ERROR);
        }
        return result;
    }
}
