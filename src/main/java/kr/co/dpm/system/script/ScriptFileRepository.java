package kr.co.dpm.system.script;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Repository
public interface ScriptFileRepository {
    public boolean distribute(File classFile, String encryptResult, String url) throws Exception;
}
