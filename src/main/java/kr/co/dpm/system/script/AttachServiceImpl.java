package kr.co.dpm.system.script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachServiceImpl implements AttachService {
    @Autowired
    private AttachRepository attachRepository;
    
    /* 첨부 파일 조회 */
    @Override
    public Attach getAttach(Attach attach) {
        return attachRepository.select(attach);
    }
    
    /* 첨부 파일 등록 */
    @Override
    public void registerAttach(Attach attach) {
        attachRepository.insert(attach);
    }
}
