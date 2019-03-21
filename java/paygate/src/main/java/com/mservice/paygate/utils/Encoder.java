/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mservice.paygate.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Formatter;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author khangdoan
 */
@SuppressWarnings("restriction")
public class Encoder {

    private static final char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
//    private static final String ENCODING = "UTF-8";

    private static final String HMAC_SHA256 = "HmacSHA256";

    @SuppressWarnings("resource")
	private static String toHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return sb.toString();
    }

    public static String signHmacSHA256(String data, String secretKey) throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), HMAC_SHA256);
        Mac mac = Mac.getInstance(HMAC_SHA256);
        mac.init(secretKeySpec);
        byte[] rawHmac = mac.doFinal(data.getBytes("UTF-8"));
        return toHexString(rawHmac);
    }

    public static String getSHA(String data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA");
        md.update(data.getBytes("UTF8"));
        byte[] ba = md.digest();
        StringBuilder sb = new StringBuilder(ba.length * 2);

        for (int i = 0; i < ba.length; i++) {
            sb.append(HEX_CHARS[(((int) ba[i] & 0xFF) / 16) & 0x0F]);
            sb.append(HEX_CHARS[((int) ba[i] & 0xFF) % 16]);
        }
        return sb.toString();
    }

    public static String decode64(String s) {
        try {
            byte[] valueDecoded= Base64.decodeBase64(s.getBytes());
            return new String(valueDecoded);
        } catch (Exception e) {
            return "";
        }
    }
    public static String encode64(String s) {
        // encode data on your side using BASE64
        byte[]   bytesEncoded = Base64.encodeBase64(s.getBytes());
        return new String(bytesEncoded );
    }

    public static String hashSHA256(String input) throws Exception {
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            sha.update(input.getBytes());
            BigInteger dis = new BigInteger(1, sha.digest());
            String result = dis.toString(16);
            if (!result.startsWith("0") && result.length() < 64) {
                result = "0" + result;
            }
            return result.toUpperCase();
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex);
        }
    }

    public static String hmacSha1(String value, String key) throws Exception {
        // Get an hmac_sha1 key from the raw key bytes
        byte[] keyBytes = key.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

        // Get an hmac_sha1 Mac instance and initialize with the signing key
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(signingKey);

        // Compute the hmac on input data bytes
        byte[] rawHmac = mac.doFinal(value.getBytes());
        return Base64.encodeBase64String(rawHmac);
    }
    
	public static String encryptRSA(byte[] dataBytes, String publicKey) throws Exception {
		// Note: You can use java.util.Base64 instead of sun.misc.*
    	PublicKey pubk;
        BASE64Decoder decoder = new BASE64Decoder();
        BASE64Encoder encoder = new BASE64Encoder();
//    	java.util.Base64.Decoder decoder = java.util.Base64.getDecoder();
//    	java.util.Base64.Encoder encoder = java.util.Base64.getEncoder();
        
//        byte[] publicKeyBytes = decoder.decode(publicKey);
        byte[] publicKeyBytes = decoder.decodeBuffer(publicKey);
        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec (publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        pubk = keyFactory.generatePublic(publicKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubk);
//        return new String(encoder.encodeToString(cipher.doFinal(dataBytes)).replace("\r", ""));
        return new String(encoder.encode(cipher.doFinal(dataBytes)).replace("\r", ""));
    }
}
