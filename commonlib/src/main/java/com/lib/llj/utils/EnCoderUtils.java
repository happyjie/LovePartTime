package com.lib.llj.utils;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by llj on 2016/4/13.
 */
public class EnCoderUtils {
    private static int LENGTH = 1;
    public static String KL(String inStr, String checkCode) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ checkCode.charAt(i % checkCode.length()));
        }
        String s = new String(a);
        return s;
    }


    public static String generateCheckCode() {
        // 定义验证码的字符表
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuffer rands = new StringBuffer();
        for (int i = 0; i < LENGTH; i++) {
            int rand = (int) (Math.random() * chars.length());
            rands.append(chars.charAt(rand));
        }

        return rands.toString();
    }

    /**
     * base64加密
     * @param str
     * @return
     */
    public static String Base64EnCoder(String str){
        return Base64.encodeToString(str.getBytes(), Base64.DEFAULT);
    }

    /**
     * base64解密
     * @param str
     * @return
     */
    public static String Base64DeCoder(String str){
        return new String(Base64.decode(str.getBytes(), Base64.DEFAULT));
    }


    public static String stringToMD5(String string) {
        byte[] hash;

        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }

        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }

        return hex.toString().substring(8, 24);
    }
}
