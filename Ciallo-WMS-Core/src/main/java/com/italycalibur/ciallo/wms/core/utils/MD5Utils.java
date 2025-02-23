package com.italycalibur.ciallo.wms.core.utils;

import com.italycalibur.ciallo.wms.core.constants.CommonConstants;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @description: MD5工具类
 * @author dhr
 * @date 2025-02-11 11:50:18
 * @version 1.0
 */ 
public class MD5Utils {

    /**
     * md5加密，自定义盐
     * @param source 待加密字符串
     * @param salt 盐
     * @return 加密后的字符串
     */
    public static String md5Encode(CharSequence source, String salt) {
        return DigestUtils.md5DigestAsHex((source + salt).getBytes(StandardCharsets.UTF_8));
    }

    /**
     * md5加密，默认盐
     * @param source 待加密字符串
     * @return 加密后的字符串
     */
    public static String md5Encode(CharSequence source) {
        return md5Encode(source, CommonConstants.MD5_SALT);
    }

    /**
     * md5"解密"，自定义盐
     * @param source 待解密字符串
     * @param salt 盐
     * @param encode 待比较字符串
     * @return 解密结果
     */
    public static boolean md5Decode(CharSequence source, String salt, String encode) {
        return md5Encode(source, salt).equals(encode);
    }

    /**
     * md5"解密"，默认盐
     * @param source 待解密字符串
     * @param encode 待比较字符串
     * @return 解密结果
     */
    public static boolean md5Decode(CharSequence source, String encode) {
        return md5Decode(source, CommonConstants.MD5_SALT, encode);
    }

    // 测试加密解密
    public static void main(String[] args) {
        // 查看加密效果
        System.out.println(md5Encode("abc1234."));
        // result: false
        System.out.println(md5Decode("abc1234.", "4d627b911c60b0b48ccee6560d23eb1c"));
        // result: true
        System.out.println(md5Decode("abc1234.", md5Encode("abc1234.")));
    }
}
