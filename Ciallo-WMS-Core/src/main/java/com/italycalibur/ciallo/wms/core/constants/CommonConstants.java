package com.italycalibur.ciallo.wms.core.constants;

/**
 * @author dhr
 * @version 1.0
 * @date 2025-02-23 14:22:53
 * @description: 通用常量
 */
public class CommonConstants {
    //加密盐
    public static final String MD5_SALT = "italycalibur";
    //令牌黑名单
    public static final String BLACK_LIST_TOKEN = "black:list:token:";
    //令牌类型
    public static final String TOKEN_TYPE_ACCESS = "access";
    public static final String TOKEN_TYPE_REFRESH = "refresh";
    //响应对象常用头文件
    public static final String EXPORT_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    //默认编码
    public static final String DEFAULT_CHARSET_ENCODING = "utf-8";
}
