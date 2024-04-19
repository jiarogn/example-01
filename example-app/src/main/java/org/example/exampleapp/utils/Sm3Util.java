package org.example.exampleapp.utils;

import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.Security;

// 定义一个Sm3Util工具类
public class Sm3Util {

    // 静态代码块，添加BouncyCastleProvider作为安全提供者
    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    // 计算SM3摘要
    public static byte[] sm3(byte[] srcData) {
        SM3Digest sm3Digest = new SM3Digest();
        sm3Digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[sm3Digest.getDigestSize()];
        sm3Digest.doFinal(hash, 0);
        return hash;
    }

    // 将SM3摘要转换为十六进制字符串
    public static String sm3Hex(byte[] srcData) {
        byte[] hash = sm3(srcData);
        String hexString = Hex.toHexString(hash);
        return hexString;
    }

    // 计算HMAC-SM3摘要
    public static byte[] hmacSm3(byte[] key, byte[] srcData) {
        KeyParameter keyParameter = new KeyParameter(key);
        SM3Digest digest = new SM3Digest();
        HMac mac = new HMac(digest);
        mac.init(keyParameter);
        mac.update(srcData, 0, srcData.length);
        byte[] hash = new byte[mac.getMacSize()];
        mac.doFinal(hash, 0);
        return hash;
    }

    // 将HMAC-SM3摘要转换为十六进制字符串
    public static String hmacSm3Hex(byte[] key, byte[] srcData) {
        byte[] hash = hmacSm3(key, srcData);
        String hexString = Hex.toHexString(hash);
        return hexString;
    }

    // 使用BouncyCastle实现的SM3摘要算法
    public static byte[] sm3bc(byte[] srcData) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance("SM3", "BC");
        byte[] digest = messageDigest.digest(srcData);
        return digest;
    }

    // 将BouncyCastle实现的SM3摘要转换为十六进制字符串
    public static String sm3bcHex(byte[] srcData) throws Exception {
        byte[] hash = sm3bc(srcData);
        String hexString = Hex.toHexString(hash);
        return hexString;
    }
}
