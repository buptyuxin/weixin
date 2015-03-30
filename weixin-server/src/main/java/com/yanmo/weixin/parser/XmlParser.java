package com.yanmo.weixin.parser;

import com.yanmo.weixin.domain.Errors;
import com.yanmo.weixin.domain.MsgDO;
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
    // key words  ->  msg type
    private Map<List<String>, Integer> xmlMaps;

    public Map<List<String>, Integer> getXmlMaps() {
        return xmlMaps;
    }

    public void setXmlMaps(Map<List<String>, Integer> xmlMaps) {
        this.xmlMaps = xmlMaps;
    }

    public ResultDO<MsgDO> parseXml(String xml) {
        ResultDO result = new ResultDO();
        if (StringUtils.isEmpty(xml)) {
            return result;
        }

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
