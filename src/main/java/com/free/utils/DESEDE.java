package com.free.utils;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加/解密 工具类(3DES对称加密-可逆，保密性依赖于密钥)
 */
public class DESEDE {
    
    // 加密/解密算法
	private static final String ALGORITHM = "DESede";
	
	// 字符编码(避免不同机器上设置不同的JDK语言环境时产生不同的加密/解密结果)
	private static final String ENCODE = "UTF-8";
	
	// (用于加密/解密的)密钥
	private static final byte[] keyBytes = {
	    0x11, 0x22, 0x4F, 0x58, (byte)0x88, 0x10, 0x40, 0x38, 
        0x28, 0x25, 0x79, 0x51, (byte)0xCB, (byte)0xDD, 0x55, 0x66, 
        0x77, 0x29, 0x74, (byte)0x98, 0x30, 0x40, 0x36, (byte)0xE2
    };
	private DESEDE(){
		
	}
    /**
     * 加密
     * 
     * @param sourceCode
     * @return
     */
    public static String encryptIt(String sourceCode) {
        try {
            byte[] src = sourceCode.getBytes(ENCODE);
            byte[] encoded = encrypt(keyBytes, src, Cipher.ENCRYPT_MODE);
            
            return byte2hex(encoded);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * 
     * @param encodedCode 密文
     * @return
     */
    public static String decryptIt(String encodedCode) {
        try {
            byte[] src = hex2byte(encodedCode);
            byte[] encoded = encrypt(keyBytes, src, Cipher.DECRYPT_MODE);
            
            return new String(encoded, ENCODE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

	/**
	 * 根据指定的操作模式（加密/解密）进行加密或解密
	 * 
	 * @param keybyte
	 * @param src
	 * @param opMode 操作模式（加密/解密）
	 */
	private static byte[] encrypt(byte[] keybyte, byte[] srcBytes, int opMode) {
	    try {
            SecretKey deskey = new SecretKeySpec(keybyte, ALGORITHM);
            Cipher c1 = Cipher.getInstance(ALGORITHM);
            c1.init(opMode, deskey);
            return c1.doFinal(srcBytes);
        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
	}

	
	private static String byte2hex(byte[] b) {
		StringBuilder sb = new StringBuilder();
		String stmp;
		for (int n = 0; n < b.length; n++) {
			stmp = java.lang.Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1) {
				sb.append("0");
			}
			sb.append(stmp);
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * 
	 * @param hex 十六进制的密文
	 * @return
	 * @throws IllegalArgumentException
	 */
	private static byte[] hex2byte(String hex) throws IllegalArgumentException {
	    
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}
	

	public static void main(String[] args) {
	    String mwen = "ldm-ly@12345qwe.com";
	    String miwen = encryptIt(mwen);
		String deMingwen = decryptIt(miwen);
		
        System.out.println("加密：" + miwen);
		System.out.println("解密：" + deMingwen);
	}
}