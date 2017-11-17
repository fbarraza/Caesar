package app.orchis.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class CryptoHelper {

    public static String encripta(String s) {
        return DigestUtils.sha256Hex(s);
    }

    public static boolean testPassword (String bbdd, String encriptat) {

        if (encriptat.equals(bbdd))
            return true;
        else
            return false;
    }
    
}