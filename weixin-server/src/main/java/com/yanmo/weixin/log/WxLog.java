package com.yanmo.weixin.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yanmo.yx on 2015/3/25.
 */
public class WxLog {
    private static Logger logger = LoggerFactory.getLogger("SxJingDong");

    public static void log(String s) {
        logger.info(s);
    }
}
