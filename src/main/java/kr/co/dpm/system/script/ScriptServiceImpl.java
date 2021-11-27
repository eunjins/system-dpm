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
    public int registerScript(Script script) {
        // 등록 일시 추가
        SimpleDateFormat format = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");

        String dateTime = format.format(System.currentTimeMillis());

        script.setUploadPoint(dateTime);

        return scriptRepository.insert(script);
    }
}
