package com.g1335333249.utils;

import org.springframework.util.Base64Utils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacSha1Util {
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    public static String Base64HmacSHA1Encrypt(String encryptText, String encryptKey) {
        try {
            byte[] data = encryptKey.getBytes(ENCODING);
            // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
            // 生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance(MAC_NAME);
            // 用给定密钥初始化 Mac 对象
            mac.init(secretKey);

            byte[] text = encryptText.getBytes(ENCODING);
            // 完成 Mac 操作
            return Base64Utils.encodeToString(mac.doFinal(text));
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return "";
    }
}
