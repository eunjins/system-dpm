package kr.co.dpm.system.script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachServiceImpl implements AttachService {
    @Autowired
    private AttachRepository attachRepository;
    
    /* 스크립트별 첨부 파일 조회 */
    @Override
    public List<Attach> getAttaches(Attach attach) {
        return attachRepository.selectAll(attach);
    }

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
