package kr.co.dpm.system.script;

import kr.co.dpm.system.device.Device;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface ScriptFileRepository {
    // 스크립트 배포 ( -> 에이전트 )
    public boolean distribute(MultipartFile classFile, String encryptResult, String url);
}
