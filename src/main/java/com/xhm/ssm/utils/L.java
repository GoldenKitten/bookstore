package com.xhm.ssm.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @program: bookstore
 * @description: 日志工具类的简单封装
 * @author: GoldenKitten
 * @date: 2018-05-22 13:20
 * @version: 1.0
 */
public class L {
    private static final Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static void t(Object o){
            logger.trace(o);
    }
    public static void d(Object o){

            logger.debug(o);
    }
    public static void i(Object o){
            logger.info(o);
    }
    public static void w(Object o){
            logger.warn(o);
    }
    public static void e(Object o){
            logger.error(o);
    }
    public static void f(Object o){
            logger.fatal(o);
    }

}
