package com.saroi.backend.util;

import java.security.MessageDigest;
import java.util.HexFormat;

public class HashUtil {

    public static String sha256(byte[] data) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return HexFormat.of().formatHex(md.digest(data));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
