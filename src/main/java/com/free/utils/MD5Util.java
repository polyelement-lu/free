package com.free.utils;

import java.security.MessageDigest;
/**
 * 
 * @author      ludm  
 * @date        2018年2月9日下午1:59:12 
 * @description 加/解密 工具类(对称加密-不可逆，不需要秘钥)
 */
public class MD5Util {
	
	/**
	 * MD5加密 生成32位md5码
	 * @param inStr
	 * @return 返回32位md5码
	 * @throws Exception
	 */
	public static String encrypt(String inStr) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
//        char[] charArray = inStr.toCharArray();
//        byte[] byteArray = new byte[charArray.length];
//        for (int i = 0; i < charArray.length; i++){
//        	byteArray[i] = (byte) charArray[i];
//        }
        byte[] byteArray = inStr.getBytes("UTF-8");
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
	
	/**
	 * 加密解密算法, 执行一次加密，两次解密(不能单独解密MD5加密后的密文)
	 * @param str
	 * @return
	 */
    public static String decrypt(String str) {
        return md5Decrypt(md5Decrypt(str));
    }
	
    private static  String md5Decrypt(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }
}