package kr.co.dpm.system.script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class ScriptServiceImpl implements ScriptService {
    @Autowired
    private ScriptRepository scriptRepository;

    /* 스크립트 정보 목록 조회  */
    @Override
    public List<Script> getScripts(Map<String, String> condition) {
        return scriptRepository.selectAll(condition);
    }

    /* 스크립트 정보 조회 */
    @Override
    public Script getScript(Script script) {
        return scriptRepository.select(script);
    }

    /* 스크립트 정보 등록 */
    @Override
    public void registerScript(Script script) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String dateTime = format.format(System.currentTimeMillis());

        script.setUploadPoint(dateTime);

        scriptRepository.insert(script);
    }
}
