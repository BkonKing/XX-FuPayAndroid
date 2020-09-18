package com.fuiou.fupay.http;


import com.fuiou.fupay.FyLogUtils;

import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;


public class FyRSAUtils {

    /**
     * 加密算法RSA
     */
    public static final String KEY_ALGORITHM = "RSA";

    /**
     * 加解密编码
     */
    public static final String CHARSET_NAME = "GBK";


    /**********************************聚合rsa加解密-start***********************************/


    /**
     * rsa公钥加密
     *
     * @param src
     * @param base64PubRsa
     * @return
     */
    public static String encryptByRsaPub(String src, String base64PubRsa) {
        try {
            PublicKey publicKey = genPublicKey(base64PubRsa);
            byte[] encryptBytes = encryptByKey(src.getBytes(CHARSET_NAME), publicKey);
            return Base64.encode(encryptBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * rsa私钥解密
     *
     * @param base64Src
     * @param base64PriRsa
     * @return
     */
    public static String decryptByRsaPri(String base64Src, String base64PriRsa) {
        try {
            PrivateKey privateKey = genPrivateKey(base64PriRsa);
            return new String(decryptKey(Base64.decode(base64Src), privateKey), CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**********************************聚合rsa加解密-end***********************************/

    /**
     * 获取rsa私钥
     *
     * @param base64Rsa
     * @return
     */
    private static PrivateKey genPrivateKey(String base64Rsa) throws Exception {
        KeyFactory  kf = null;
        //todo 解决某些机型报错（例如vivo），这里兼容处理
        try {
            kf = KeyFactory.getInstance(KEY_ALGORITHM, "BC");
        } catch (Exception e) {
            e.printStackTrace();
            kf = KeyFactory.getInstance(KEY_ALGORITHM);
        }

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(base64Rsa));
        return kf.generatePrivate(keySpec);
    }


    /**
     * 获取rsa公钥
     *
     * @param base64Rsa
     * @return
     * @throws Exception
     */
    private static PublicKey genPublicKey(String base64Rsa) throws Exception {
        KeyFactory kf = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.decode(base64Rsa));
        return kf.generatePublic(keySpec);

    }

    /**
     * 分段加密
     *
     * @param srcData
     * @param publicKey
     * @return
     */
    private static byte[] encryptByKey(byte[] srcData, Key publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        // 分段加密
        int blockSize = cipher.getBlockSize();
        FyLogUtils.i("cipher.getBlockSize()=" + cipher.getBlockSize());
        byte[] encryptedData = null;
        for (int i = 0; i < srcData.length; i += blockSize) {
            // 分段加密具体逻辑
            byte[] doFinal = cipher.doFinal(subarray(srcData, i, i + blockSize));
            encryptedData = addAll(encryptedData, doFinal);
        }
        return encryptedData;
    }

    /**
     * 分段解密
     *
     * @param srcData
     * @param key
     * @return
     */
    private static byte[] decryptKey(byte[] srcData, Key key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, key);


        int blockSize = cipher.getBlockSize();
        FyLogUtils.i("cipher.getBlockSize()=" + cipher.getBlockSize());
        byte[] decryptData = null;

        for (int i = 0; i < srcData.length; i += blockSize) {
            byte[] doFinal = cipher.doFinal(subarray(srcData, i, i + blockSize));

            decryptData = addAll(decryptData, doFinal);
        }
        return decryptData;

    }

    /**
     * 截取数组
     *
     * @param array
     * @param startIndexInclusive
     * @param endIndexExclusive
     * @return
     */
    private static byte[] subarray(byte[] array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        if (endIndexExclusive > array.length) {
            endIndexExclusive = array.length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;

        if (newSize <= 0) {
            return new byte[0];
        }

        byte[] subarray = new byte[newSize];

        System.arraycopy(array, startIndexInclusive, subarray, 0, newSize);

        return subarray;
    }


    private static byte[] addAll(byte[] array1, byte[] array2) {
        if (array1 == null) {
            return clone(array2);
        } else if (array2 == null) {
            return clone(array1);
        }
        byte[] joinedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, joinedArray, 0, array1.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        return joinedArray;
    }


    public static byte[] clone(byte[] array) {
        if (array == null) {
            return null;
        }
        return (byte[]) array.clone();
    }

}
