package org.lwt.test;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.apache.shiro.util.ByteSource;

public class ShiroEncryText {
    public static void main(String[] args) throws UnsupportedEncodingException {
       /* AesCipherService cipherService = new AesCipherService();
        cipherService.setKeySize(256);
        Key key = cipherService.generateNewKey();
        ByteSource byteSource = cipherService.encrypt("hello world".getBytes(), key.getEncoded());
        System.out.println(byteSource.getBytes());
        ByteSource deByteSource = cipherService.decrypt(byteSource.getBytes(), key.getEncoded());
        System.out.println(new String(deByteSource.getBytes()));*/
        /*String encodedPassword =
                new Sha512Hash("123456", "scott", 1).toBase64();*/
        String encodedPassword =
                new Md5Hash("123456", "scott", 1).toBase64();
        System.out.println(encodedPassword);
        
    }
}
