package kr.co.dpm.system.script;

import org.springframework.web.multipart.MultipartFile;

public interface ScriptFileRepository {
    // 스크립트 배포 ( -> 에이전트 )
    public boolean distribute(MultipartFile classFile, String encryptResult);
}
