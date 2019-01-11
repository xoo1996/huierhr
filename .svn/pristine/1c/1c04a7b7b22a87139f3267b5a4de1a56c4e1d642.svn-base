/*

 说明:
 This Class need JCE, download here:
 http://java.sun.com/security/index.html

 */
package org.radf.plat.commons.safe;

import java.math.BigInteger;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.sun.crypto.provider.SunJCE;

public class DesCrypt {
    public DesCrypt() {
    }

    private static String Algorithm = "DES"; // 定义加密算法,可用 DES,DESede,Blowfish

    static boolean debug = false;

    public final static String PUBLICKEY = "topcheer";

    static {
        Security.addProvider(new SunJCE());
        // KeyGenerator generator = KeyGenerator.getInstance("DES", "SunJCE");

    }

    // 加密
    public byte[] encode(byte[] input, byte[] key) throws Exception {
        SecretKey deskey = new SecretKeySpec(key, Algorithm);
        if (debug) {
            System.out.println("加密前的二进串:" + byte2hex(input));
            System.out.println("加密前的字符串:" + new String(input, "ISO-8859-1"));
        }
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] cipherByte = c1.doFinal(input);
        if (debug) {
            System.out.println("加密后的二进串:" + byte2hex(cipherByte));
            System.out.println("加密后的字符串:"
                    + new String(cipherByte, "ISO-8859-1"));
        }
        return cipherByte;
    }

    // 解密
    public byte[] decode(byte[] input, byte[] key) throws Exception {
        SecretKey deskey = new SecretKeySpec(key, Algorithm);
        if (debug) {
            System.out.println("解密前的二进串:" + byte2hex(input));
            System.out.println("解密前的字符串:" + new String(input, "ISO-8859-1"));
        }
        Cipher c1 = Cipher.getInstance(Algorithm);
        c1.init(Cipher.DECRYPT_MODE, deskey);
        byte[] clearByte = c1.doFinal(input);
        if (debug) {
            System.out.println("解密后的二进串:" + byte2hex(clearByte));
            System.out.println("解密后的字符串:"
                    + (new String(clearByte, "ISO-8859-1")));
        }
        return clearByte;
    }

    // 字节码转换成16进制字符串
    public String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
            if (n < b.length - 1)
                hs = hs + ":";
        }
        return hs.toUpperCase();
    }

    // 生成密钥, 注意此步骤时间比较长
    /*
     * public byte[] getKey() throws Exception{ KeyGenerator keygen =
     * KeyGenerator.getInstance(Algorithm); SecretKey deskey =
     * keygen.generateKey(); if (debug)
     * System.out.println("生成密钥:"+byte2hex(deskey.getEncoded())); return
     * deskey.getEncoded(); }
     */
    public String encrypt(String value) throws Exception {
        return encrypt(value,PUBLICKEY);
    }
    public String encrypt(String value,String key) throws Exception {
        byte[] temp = encode(value.getBytes("GBK"), key.getBytes());
        BigInteger bigInt = new BigInteger(temp);
        return new String(bigInt.toString());
    }
    public String decrypt(String value) throws Exception {
        return decrypt(value,PUBLICKEY);
    }
    public String decrypt(String value,String key) throws Exception {
        BigInteger bigInt = new BigInteger(value);
        byte b[] = bigInt.toByteArray();
        byte[] temp = decode(b, key.getBytes());
        return new String(temp, "GBK");
    }
    public static void main(String[] args) throws Exception {
        StringBuffer sb = new StringBuffer(10000);
        for(int i=0;i<1000;i++){
            StringBuffer s = new StringBuffer(10);
            for(int j=0;j<10;j++){
                s.append(j);
            }
            sb.append(s);
        }

        String key1 = "topcheer";
        DesCrypt app = new DesCrypt();
        String msg = sb.toString();     

        System.out.println("===============================");
        System.out.println("加密前的字符串("+msg.length()+")：" + msg);
        try{
            String enc = app.encrypt(msg,key1);
            System.out.println("加密后的字符串("+enc.length()+")：" + enc);
            String ret = app.decrypt(enc,key1);
            System.out.println("解密后的字符串("+ret.length()+")：" + ret);
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("===============================");

        /*
         * debug = true; byte[] key = PUBLICKEY.getBytes(); byte[]
         * temp=app.encode(msg.getBytes("GBK"),key);
         * System.out.println("===============================");
         * System.out.println(temp.length);
         * System.out.println("二进制："+app.byte2hex(temp)); String sss=new
         * String(temp,"ISO-8859-1"); System.out.println("字符串："+sss);
         * System.out.println(sss.length()); byte[]
         * newtemp=sss.getBytes("ISO-8859-1");
         * System.out.println(newtemp.length);
         * System.out.println("二进制："+app.byte2hex(newtemp)); String ddd=new
         * String(newtemp,"ISO-8859-1"); System.out.println("字符串："+ddd);
         * System.out.println(ddd.length());
         * System.out.println(sss.equals(ddd));
         * System.out.println("===============================");
         * app.decode(temp,key);
         */

    }

}