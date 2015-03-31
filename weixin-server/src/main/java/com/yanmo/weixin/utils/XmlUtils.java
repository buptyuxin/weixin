package com.yanmo.weixin.utils;

import com.yanmo.weixin.domain.BaseKeyValuePairDO;
import com.yanmo.weixin.domain.MsgDO;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * Created by yanmo.yx on 2015/3/30.
 */
public class XmlUtils {
    public static MsgDO fromXml2Msg(String xml) {
        SAXReader saxReader = new SAXReader();
        MsgDO msgDO = new MsgDO();
        try {
            // 自己做的解析
            Document doc = saxReader.read(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
            Element root = doc.getRootElement();
            Iterator it = root.elementIterator();
            while (it.hasNext()) {
                Element element = (Element) it.next();
                String key = element.getName();
                String value = element.getText();
                BaseKeyValuePairDO kv = new BaseKeyValuePairDO();
                kv.setKey(key);
                kv.setValue(value);
                msgDO.addProperty(kv);
            }
            return msgDO;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String fromMsg2Xml(MsgDO msgDO) {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("xml");
        for (BaseKeyValuePairDO kv : msgDO.getProperties()) {
            Element element = root.addElement(kv.getKey());
            element.addCDATA(kv.getValue());
//            element.setText(kv.getValue());
        }
        StringWriter sw = new StringWriter();
        XMLWriter xmlWriter = new XMLWriter(sw, OutputFormat.createPrettyPrint());
        try {
            xmlWriter.write(doc);
            sw.close();
            xmlWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sw.toString();
    }

    public static void main(String[] args) {
        String xml = "<xml>" +
                " <ToUserName><![CDATA[toUser]]></ToUserName>" +
                " <FromUserName><![CDATA[fromUser]]></FromUserName>" +
                " <CreateTime>1348831860</CreateTime>" +
                " <MsgType><![CDATA[text]]></MsgType>" +
                " <Content><![CDATA[this is a test]]></Content>" +
                " <MsgId>1234567890123456</MsgId>" +
                " </xml>";
        MsgDO msgDO = fromXml2Msg(xml);
        System.out.println(fromMsg2Xml(msgDO));
    }
}
