package com.yanmo.weixin.parser;

import com.yanmo.weixin.domain.BaseKeyValuePairDO;
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
public class JsonParser extends BaseParser {

    private Map<Class<? extends BaseJsonDO>, List<String>> jsonMaps;

    public Map<Class<? extends BaseJsonDO>, List<String>> getJsonMaps() {
        return jsonMaps;
    }

    public void setJsonMaps(Map<String, List<String>> jsonMaps) {
        for (String key : jsonMaps.keySet()) {
            if (key != null && !key.isEmpty()) {
                List<String> keyWords = jsonMaps.get(key);
                if (keyWords != null && !keyWords.isEmpty()) {
                    Class<?> clazz = null;
                    try {
                        clazz = Thread.currentThread().getContextClassLoader().loadClass(key);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        continue;
                    }
                    if (clazz != null && BaseJsonDO.class.isAssignableFrom(clazz)) {
                        this.jsonMaps.put((Class<? extends BaseJsonDO>)clazz, jsonMaps.get(key));
                        continue;
                    }
                    WxLog.log("类错误，加载类失败");
                    continue;
                }
                WxLog.log("关键词错误");
            }
            WxLog.log("类错误");
        }
    }

    public ResultDO<BaseJsonDO> parseJson(String json) {
        ResultDO result = new ResultDO();
        if (StringUtils.isEmpty(json)) {
            return result;
        }

        for (Class<? extends BaseJsonDO> clazz : jsonMaps.keySet()) {
            if (matchKeyWords(json, jsonMaps.get(clazz))) {
                BaseJsonDO res = JsonUtils.fromJson(json, clazz);
                result.setModule(res);
                return result;
            }
        }
        result.addError(Errors.PARSE_JSON_ERROR);
        return result;
    }
}
