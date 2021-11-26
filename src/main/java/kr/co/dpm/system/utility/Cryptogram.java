package kr.co.dpm.system.utility;

public interface Cryptogram {
    public String encryption(Object word) throws Exception;

    public String decryption(Object word) throws Exception;
}
