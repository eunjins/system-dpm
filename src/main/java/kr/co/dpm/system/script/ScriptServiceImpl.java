package kr.co.dpm.system.script;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class ScriptServiceImpl implements ScriptService {
    private static final Logger logger = LogManager.getLogger(ScriptController.class);

    @Autowired
    private ScriptRepository scriptRepository;

    /* 스크립트 정보 목록 조회  */
    @Override
    public List<Script> getScripts(Script script) {
        return scriptRepository.selectAll(script);
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
