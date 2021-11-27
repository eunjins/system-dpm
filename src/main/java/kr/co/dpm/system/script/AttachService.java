package kr.co.dpm.system.script;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AttachService {
    public Attach getAttach(Attach attach);

    public void registerAttach(MultipartFile sourceFile,
                               MultipartFile classFile, Attach attach);
}
