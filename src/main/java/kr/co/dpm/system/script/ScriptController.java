package kr.co.dpm.system.script;

import kr.co.dpm.system.management.ManagementServiceImpl;
import kr.co.dpm.system.measure.Measure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

public class ScriptController {
    @Autowired
    private ScriptServiceImpl scriptService;

    @Autowired
    private ManagementServiceImpl managementService;

    //  스크립트 측정 결과 폼
    public ModelAndView getScripts() {
        return null;
    }

    // 스크립트 측정 결과 목록 조회
    public List<Script> getScript() {
        return null;
    }

    // 스크립트 등록 폼
    public ModelAndView registerScript(Script script) {
        return null;
    }

    // 스크립트 배포 (배포 클릭시)
    public Map<String, String> distributeScript(Script script, MultipartFile file) {
        // 입력 값이 존재 하는가?
        if (script != null) {
            managementService.distributeScript(script);
        } else {
            return null;
        }
        // 디바이스 정보 목록을 조회한다.
        // 스크립트를 배포한다.
        // 배포 성공 여부를 반환한다.
        return null;
    }

    //스크립트 배포 확인
    public Map<String, String> checkScript(Script script) {
        return null;
    }

    // 스크립트 측정 결과 조회
    public ModelAndView getScript(Script script) {
        return null;
    }

    //스크립트 측정 결과 다운로드
    public void downloadScript(Script script) {

    }

    //측정 결과 수신
    public Map<String, String> receiveScript(Measure measure) {
        return null;
    }
}
