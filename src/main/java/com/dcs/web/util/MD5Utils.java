package com.dcs.web.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class MD5Utils {
    private static final int LENGTH_OF_SALT = 16;
    private static final int LENGTH_OF_RESULT = 48;
    private static final int MAX_OF_RANDOM = 99999999;
    private static final int LENGTH_OF_MD5 = 32;
    private static final String BLANK = "0";

    public static String stringEncode(String s){
        StringBuilder resultBuilder = new StringBuilder(LENGTH_OF_RESULT);
        StringBuilder saltBuilder = new StringBuilder(LENGTH_OF_SALT);
        Random random = new Random();
        saltBuilder.append(random.nextInt(MAX_OF_RANDOM)).append(MAX_OF_RANDOM);
        if (saltBuilder.length() < LENGTH_OF_SALT){
            for (int i = saltBuilder.length(); i <= LENGTH_OF_SALT; i++){
                //补位
                saltBuilder.append(BLANK);
            }
        }
        String md5Hex = md5Hex(s + saltBuilder.toString());
        //盐插入md5编码中，每隔两字符插入
        for (int i = 0; i < LENGTH_OF_RESULT; i++){
            if (i / 3 == 0){
                resultBuilder.append(saltBuilder.toString().charAt(i / 3));
                continue;
            }
            resultBuilder.append(md5Hex.charAt(((i / 3) * 2) + (i % 3)) - 1);
        }
        return resultBuilder.toString();
    }

    public static boolean contrastString(String s, String encode){
        StringBuilder saltBuilder = new StringBuilder(LENGTH_OF_SALT);
        StringBuilder md5HexBuilder = new StringBuilder(LENGTH_OF_MD5);
        for (int i = 0; i < LENGTH_OF_RESULT; i++){
            if (i / 3 == 0){
                saltBuilder.append(encode.charAt(i));
                continue;
            }
            md5HexBuilder.append(encode.charAt(i));
        }
        if (md5HexBuilder.toString().equals(stringEncode(s + saltBuilder.toString()))){
            return true;
        }
        return false;
    }

    private static String md5Hex(String s){
        MessageDigest messageDigest = null;
        StringBuilder result = new StringBuilder(LENGTH_OF_MD5);
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] resultBytes = messageDigest.digest(s.getBytes());
        for (byte oneByte : resultBytes){
            //补码转换
            String hexString = Integer.toHexString(0xff & oneByte);
            if (hexString.length() == 1){
                result.append(BLANK);
            }
            result.append(hexString);
        }
        return result.toString();
    }
}
