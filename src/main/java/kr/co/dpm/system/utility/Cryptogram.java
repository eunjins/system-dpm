package kr.co.dpm.system.utility;

public interface Cryptogram {
    public String encrypt(Object word) throws Exception;

    public String decrypt(Object word) throws Exception;
}
