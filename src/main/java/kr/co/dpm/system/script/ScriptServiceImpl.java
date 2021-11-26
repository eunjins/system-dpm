package kr.co.dpm.system.script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScriptServiceImpl implements ScriptService {
    @Autowired
    private ScriptRepository scriptRepository;

    /* 목록 조회  */
    @Override
    public List<Script> getScripts(Script script) {
        return scriptRepository.selectAll(script);
    }

    /* 상세 조회 */
    @Override
    public Script getScript(Script script) {
        return scriptRepository.select(script);
    }

    /* 등록 */
    @Override
    public void registerScript(Script script) {
        scriptRepository.insert(script);
    }
}
