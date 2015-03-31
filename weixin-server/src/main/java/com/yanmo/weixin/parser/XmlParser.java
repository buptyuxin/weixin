package com.yanmo.weixin.parser;

import com.yanmo.weixin.domain.Errors;
import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.domain.ResultDO;
import com.yanmo.weixin.utils.FeatureUtils;
import com.yanmo.weixin.utils.XmlUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
public class XmlParser extends BaseParser {
    // key words  ->  msg type
    private Map<Integer, List<String>> xmlMaps;

    public Map<Integer, List<String>> getXmlMaps() {
        return xmlMaps;
    }

    public void setXmlMaps(Map<Integer, List<String>> xmlMaps) {
        this.xmlMaps = xmlMaps;
    }

    @Deprecated
    public ResultDO<MsgDO> parseXml(String xml) {
        ResultDO result = new ResultDO();
        if (StringUtils.isEmpty(xml)) {
            return result;
        }

        for (Integer msgType : xmlMaps.keySet()) {
            if (matchKeyWords(xml, xmlMaps.get(msgType))) {
                MsgDO msgDO = XmlUtils.fromXml2Msg(xml);
                if (msgDO != null) {
                    FeatureUtils.setMsgType(msgDO, msgType);
                }
                result.setModule(msgDO);
                return result;
            }
        }
        result.addError(Errors.PARSE_XML_ERROR);
        return result;
    }

    public ResultDO<MsgDO> parseXmlMsg(String xml) {
        ResultDO result = new ResultDO();
        if (StringUtils.isEmpty(xml)) {
            return result;
        }
        MsgDO msgDO = XmlUtils.fromXml2Msg(xml);
        result.setModule(msgDO);
        return result;
    }
}
