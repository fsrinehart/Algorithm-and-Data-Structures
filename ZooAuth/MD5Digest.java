/**
 * Author: SNHU
 * Edit By: Francis Rinehart
 * Email: Francis.rinehart@SNHU.EDU
 * Language: Java
 * Purpose: This is a MD5 Digest
 * 
 */

// import security for messageDigest and NoSUchAlgorithmException
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Digest {
    
    public MD5Digest(){
    }
    
    public String Hash(String s) throws NoSuchAlgorithmException { 

        MessageDigest md = MessageDigest.getInstance("MD5"); // MessageDigest gets MD5
        md.update(s.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b & 0xff)); //format retrieves %02x
        }

        return sb.toString();
    }

}