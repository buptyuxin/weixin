package com.yanmo.weixin.utils;

import com.google.common.collect.Lists;
import com.yanmo.weixin.log.WxLog;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class SecurityUtils {
    public static boolean wxCheck(HttpServletRequest req) {
        String signature = req.getParameter("signature");
        String timestamp = req.getParameter("timestamp");
        String nonce = req.getParameter("nonce");

        List<String> list = Lists.newArrayList(EnvUtils.TOKEN, timestamp, nonce);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return -o1.compareTo(o2);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s);
        }
//        String validator = DigestUtils.sha1Hex(sb.toString());
//        if (validator != null && validator.equals(signature)) {
//            return true;
//        }
        return true;
    }
}
