package art.cipher581.commons.util;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;


public class StreamUtilities {

    public static Charset getDefaultCharset() {
        if (Charset.isSupported("UTF-8")) {
            return Charset.forName("UTF-8");
        } else {
            return Charset.defaultCharset();
        }
    }


    public static String getStreamAsString(InputStream is) throws IOException {
        return getStreamAsString(is, getDefaultCharset());
    }


    public static String getStreamAsString(InputStream is, Charset charset) throws IOException {
        return getStreamAsString(is, charset, 8192);
    }


    public static String getStreamAsString(InputStream is, Charset charset, int bufSize) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        byte[] buffer = new byte[bufSize];
        int bytes = 0;

        while ((bytes = is.read(buffer)) > 0) {
            String current = new String(buffer, 0, bytes, charset);

            stringBuilder.append(current);
        }

        return stringBuilder.toString();
    }


    public static String getMD5HashAsString(InputStream is) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] buffer = new byte[8192];
        int read = 0;
        while ((read = is.read(buffer)) >= 0) {
            md.update(buffer, 0, read);
        }

        byte[] digested = md.digest();

        String str = new HexBinaryAdapter().marshal(digested);

        return str;
    }
    
    
    public static byte[] getMD5Hash(InputStream is) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

       return getHash(is, md);
    }
   
    
    public static byte[] getHash(InputStream is, MessageDigest md) throws IOException {
        byte[] buffer = new byte[8192];
        int read = 0;
        while ((read = is.read(buffer)) >= 0) {
            md.update(buffer, 0, read);
        }

        byte[] digested = md.digest();

        

        return digested;
    }
    
    
    public static byte[] getSha256Hash(InputStream is) throws IOException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return getHash(is, md);
    }

}
