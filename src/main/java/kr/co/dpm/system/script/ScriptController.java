package kr.co.dpm.system.script;

import kr.co.dpm.system.management.ManagementServiceImpl;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.measure.MeasureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

public class ScriptController {
    private Measure measureInfo;

    @Autowired
    private MeasureServiceImpl measureService;

    @Autowired
    private ManagementServiceImpl managementService;

    /* 스크립트 배포 시 메모리에 저장 */
    public void distribute() {
        measureInfo.setScriptNo(2);
        measureInfo.setName("테스트");
    }
    
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

    @PostMapping(value = "/scripts/result")
    @ResponseBody
    public Map<String, String> receiveScript(@RequestBody Measure measure) {
        // 입력 값을 검증한다.
        boolean isEmpty = managementService.receiveMeasure(measure);

        // 입력 값이 존재하지 않는 경우 종료한다.
        if (isEmpty) {
            // TODO: 측정 결과 수신 비정상
            return null;
        }

        // 측정 결과 명, 스크립트 일련번호를 메모리에서 가져와 지정한다.
        measure.setName(measureInfo.getName());
        measure.setScriptNo(measureInfo.getScriptNo());

        // 측정 결과를 등록한다.
        measureService.registerMeasure(measure);

        // TODO: 측정 결과 수신 정상
        return null;
    }
}
