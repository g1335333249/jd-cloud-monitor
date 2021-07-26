package com.g1335333249.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String getMD5Str(String str) {
        byte[] digest;
        StringBuilder result = new StringBuilder();
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            md5.update(str.getBytes(StandardCharsets.UTF_8));
            digest = md5.digest();
            for (byte b : digest) {
                result.append(Integer.toHexString((0x000000FF & b) | 0xFFFFFF00).substring(6));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result.toString();
        //16是表示转换为16进制数
//        return new BigInteger(1, digest).toString(16);
    }
}
