package app.orchis.utils;

import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
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
    
    public static String desencriptarPublic(byte[] array) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException{
        SecretKey sKey = null;
        String missatge;
        try {              
            byte[] data;
            data = Integer.toString(56).getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(data);
            byte[] key = Arrays.copyOf(hash, 128/8);
            sKey = new SecretKeySpec(key, "AES");      
        } catch (Exception ex) {
            System.err.println("Error generant la clau:" + ex);  
        }
            
      
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, sKey);
        byte[] decrypted = cipher.doFinal(array);
        return missatge = new String(decrypted);
    }     
    
}