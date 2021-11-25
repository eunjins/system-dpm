package kr.co.dpm.system.script;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

public class ScriptController {
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

    // 스크립트 배포
    public Map<String, String> distributeScript(Script script, MultipartFile file) {
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

}
