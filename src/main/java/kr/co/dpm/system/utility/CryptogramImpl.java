package kr.co.dpm.system.utility;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;

//@Component
public class CryptogramImpl implements Cryptogram {
    private String iv;
    private Key secretKeySpec;

    public CryptogramImpl(String key) {
        this.iv = key.substring(0, 16);

        byte[] keyBytes = new byte[16];
        byte[] utf8KeyBytes = null;

        try {
            utf8KeyBytes = key.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int len = utf8KeyBytes.length;

        if (len > keyBytes.length) {
            // 16바이트로 맞춰준다.
            len = keyBytes.length;
        }

        System.arraycopy(utf8KeyBytes, 0, keyBytes, 0 , len);

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        this.secretKeySpec = secretKeySpec;
    }

    // 암호화
    @Override
    public String encryption(Object word) throws Exception {
        if (word == null) {
            return null;
        }

        return encryption(String.valueOf(word));
    }

    @Override
    public String decryption(Object word) throws Exception {
        return null;
    }

    private String encryption(String word) throws Exception {
        if (StringUtils.isEmpty(word)) {
            return "";
        }

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCs5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, this.secretKeySpec, new IvParameterSpec(this.iv.getBytes()));

        //암호화 완료
        byte[] encrypted = cipher.doFinal(word.getBytes("UTF-8"));
        String encrypWord = new String(Base64.encodeBase64(encrypted));

        return encrypWord;

    }

}