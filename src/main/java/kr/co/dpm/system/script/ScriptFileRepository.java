package kr.co.dpm.system.script;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface ScriptFileRepository {
    public boolean distribute(MultipartFile classFile, String encryptResult, String url);
}
