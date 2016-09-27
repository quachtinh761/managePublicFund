package common.function;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.security.*;

/**
 * Created by Nguyen Van Tinh on 25/09/2016.
 */
public class encryptMd5 {
    private static MessageDigest md;
    
    public static String getStringMd5(String str){
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (Exception e) {
            System.err.println(e);
            return getStringMd5("");
            
        }
    }

    @SuppressWarnings("resource")
    public static String getFileMd5(String filePath){
        try {
            md = MessageDigest.getInstance("MD5");

            FileInputStream file = new FileInputStream(new File(filePath));
            byte[] dataBytes = new byte[1024];

            int nread;
            while ((nread = file.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            byte[] array = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        }catch(NoSuchAlgorithmException | IOException ex){
            return getStringMd5(filePath);
        }
    }

    public static String getObjectMd5(Object ob){
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            md = MessageDigest.getInstance("MD5");
            byte[] array;

            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject(ob);
            out.flush();
            array = md.digest(bos.toByteArray());

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();

        } catch (NoSuchAlgorithmException | IOException e) {
            return getObjectMd5(null);
        }
    }

}
