package com.owl.baselib.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Encoder {
	
	public static String toMd5(byte[] bytes) {

        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(bytes);
            return toHexString(algorithm.digest());

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);

            // 05-20 09:42:13.697: ERROR/hjhjh(256):
            // 5d5c87e61211ab7a4847f7408f48ac
        }

    }
    
    public static String toHexString(byte[] bytes) {
    	
        StringBuilder hexString = new StringBuilder();
        
        for (byte b : bytes) {
            hexString.append(Integer.toHexString((0xF0 & b) >> 4)).append(Integer.toHexString(0x0F & b));
        }

        return hexString.toString();

    }

}
