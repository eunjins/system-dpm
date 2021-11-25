package org.mybatis.encrypt;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;

public class CryptogramImpl implements Cryptogram {
    private static final Logger logger = LogManager.getLogger(CryptogramImpl.class);

    private String iv;
    private Key secretKeySpec;

    public CryptogramImpl(String key) {
        // 0번째 index ~ 16번째 index 자르기
        this.iv = key.substring(0, 16);

        byte[] keyBytes = new byte[16];
        byte[] utf8KeyBytes = null;

        try {
            // 암호화에 사용할 키를 바이트 배열로 변환하여 utf8KeyBytes에 할당
            utf8KeyBytes = key.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 바이트 배열로 변환한 키의 바이트 길이 할당
        int len = utf8KeyBytes.length;

        // 바이트 배열로 변환한 키의 바이트 길이가 16바이트 보다 크다면
        if (len > keyBytes.length) {
            // 16바이트로 맞춰준다.
            len = keyBytes.length;
        }

        // len(16바이트)만큼 utf8KeyBytes의 0번째부터 데이터를 읽어서 keyBytes에 0번째부터 복사
        System.arraycopy(utf8KeyBytes, 0, keyBytes, 0 , len);

        // 복사된 keyBytes 배열에서 비밀 키 생성 (알고리즘 "AES")
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

        this.secretKeySpec = secretKeySpec;
    }

    // 암호화
    @Override
    // Object 타입의 파라미터로 모든 값을 받는다.
    public String encrypt(Object word) throws Exception {
        if (word == null) { //인자값이 존재하지 않는다면 null을 반환한다.
            return null;
        }

        // 인자값이 존재한다면 Object 타입의 인자값을 String 타입으로 변환하여 암호화를 진행한다.
        return encrypt(String.valueOf(word));
    }

    // 복호화
    @Override
    public String decrypt(Object word) throws Exception {
        if (word == null) {
            return null;
        }

        return decrypt(String.valueOf(word));
    }

    private String encrypt(String word) throws Exception {
        // word가 존재하지 않을 때(공백은 존재한다고 판단) "" 반환
        if (StringUtils.isEmpty(word)) {
            return "";
        }

        /* Cipher => 암호화, 복호화 기능 제공 클래스 */
        // Cipher 객체 인스턴스화 => AES/CBC/PKCSs5Padding 변환 => 암호화 전에 XOR 연산
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCs5Padding");

        // 암호화 Cipher 초기화 (작동방식=>암호화, 16바이트 비밀키, iv의 바이트를 벡터로 사용하여 객체 생성)
        cipher.init(Cipher.ENCRYPT_MODE, this.secretKeySpec, new IvParameterSpec(this.iv.getBytes()));

        //암호화 완료
        byte[] encrypted = cipher.doFinal(word.getBytes("UTF-8"));  // 암호화 된것은 바이너리이다.
        String encrypWord = new String(Base64.encodeBase64(encrypted)); // 바이너리를 base64로 인코딩

        logger.debug("#################################################");
        logger.debug(" Encrypt : " + word + " ---> " + encrypted + " " + encrypWord);
        logger.debug("#################################################");

        return encrypWord;  //인코딩 처리한 암호화 문자 반환

    }

    private String decrypt(String word) throws Exception {
        if (StringUtils.isEmpty(word)) {
            return "";
        }

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, this.secretKeySpec, new IvParameterSpec(this.iv.getBytes("UTF-8")));

        byte[] decrypted = Base64.decodeBase64(word.getBytes());

        String decryptWord = new String(cipher.doFinal(decrypted), "UTF-8");

        logger.debug("#####################################################");
        logger.debug(" Decrypt : " + word + " ---> " +  decrypted + " " + decryptWord);
        logger.debug("#####################################################");

        return decryptWord;
    }

    public static void main(String[] args) throws Exception {
        //TODO: 실행 코드 작성
        String loginidx = "MEM201605090243"; //암호화할 문자
        String key = "aes256-test-key!!"; //암호화에 사용할 키 (16byte)

        Cryptogram cryptogram = new CryptogramImpl(key);

        String encryptWord = cryptogram.encrypt(loginidx);

        String decryptWord = cryptogram.decrypt(encryptWord);
    }
}
