package com.yanmo.weixin.utils;

import com.google.common.collect.Lists;
import com.yanmo.weixin.domain.BaseKeyValuePairDO;
import com.yanmo.weixin.domain.MsgDO;
import com.yanmo.weixin.log.WxLog;
import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;

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
                BaseKeyValuePairDO kv = new BaseKeyValuePairDO();
                parseElement(element, kv);
                msgDO.addProperty(kv);
            }
            return msgDO;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void parseElement(Element element, BaseKeyValuePairDO kv) {
        List<Element> childs = element.elements();
        List<BaseKeyValuePairDO> subPairs = kv.getSubPairs();
        if (!kv.hasSubPairs()) {
            subPairs = Lists.newArrayList();
            kv.setSubPairs(subPairs);
        }
        if (childs != null && !childs.isEmpty()) {
            for (Element child : childs) {
                BaseKeyValuePairDO childKv = new BaseKeyValuePairDO();
                parseElement(child, childKv);
                subPairs.add(childKv);
            }
        }
        String key = element.getName();
        String value = element.getText();
        kv.setKey(key);
        kv.setValue(value);
    }

    public static String fromMsg2Xml(MsgDO msgDO) {
        Document doc = DocumentHelper.createDocument();
        Element root = doc.addElement("xml");

        for (BaseKeyValuePairDO kv : msgDO.getProperties()) {
            parseKv(root, kv);
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

    private static void parseKv(Element element, BaseKeyValuePairDO kv) {
        Element child = element.addElement(kv.getKey());
        if (kv.hasSubPairs()) {
            for (BaseKeyValuePairDO childKv : kv.getSubPairs()) {
                parseKv(child, childKv);
            }
        } else {
            child.addCDATA(kv.getValue());
        }
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
        String xml2 = "<xml>\n" +
                "<ToUserName><![CDATA[toUser]]></ToUserName>\n" +
                "<FromUserName><![CDATA[fromUser]]></FromUserName>\n" +
                "<CreateTime>12345678</CreateTime>\n" +
                "<MsgType><![CDATA[news]]></MsgType>\n" +
                "<ArticleCount>2</ArticleCount>\n" +
                "<Articles>\n" +
                "<item>\n" +
                "<Title><![CDATA[title1]]></Title> \n" +
                "<Description><![CDATA[description1]]></Description>\n" +
                "<PicUrl><![CDATA[picurl]]></PicUrl>\n" +
                "<Url><![CDATA[url]]></Url>\n" +
                "</item>\n" +
                "<item>\n" +
                "<Title><![CDATA[title]]></Title>\n" +
                "<Description><![CDATA[description]]></Description>\n" +
                "<PicUrl><![CDATA[picurl]]></PicUrl>\n" +
                "<Url><![CDATA[url]]></Url>\n" +
                "</item>\n" +
                "</Articles>\n" +
                "</xml> ";
        MsgDO msgDO = fromXml2Msg(xml2);
        System.out.println(fromMsg2Xml(msgDO));
    }
}
