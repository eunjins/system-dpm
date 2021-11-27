package kr.co.dpm.system.script;

import kr.co.dpm.system.common.ResponseMessage;
import kr.co.dpm.system.common.StatusCode;
import kr.co.dpm.system.management.ManagementServiceImpl;
import kr.co.dpm.system.measure.Measure;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import kr.co.dpm.system.measure.MeasureServiceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ScriptController {
    private static final Logger logger = LogManager.getLogger(ScriptController.class);

    @Value("{$path}")
    private String path;

    private Measure measureInfo = new Measure();

    @Autowired
    private MeasureServiceImpl measureService;

    @Autowired
    private ScriptServiceImpl scriptService;

    @Autowired
    private ManagementServiceImpl managementService;

    @Autowired
    private StatusCode statusCode;

    @Autowired
    private ResponseMessage responseMessage;

    @Autowired
    private AttachServiceImpl attachService;

    //  스크립트 측정 결과 목록 폼
    @GetMapping("/scripts")
    public ModelAndView getScripts() {
        ModelAndView mav = new ModelAndView("script/list");

        List<Script> scripts = scriptService.getScripts(null);
        mav.addObject("scripts", scripts);

        for (Script object : scripts) {
            Measure measure = new Measure();

            List<Measure> measures = measureService.getMeasures(measure);

            // TODO: 측정 결과 목록
        }

        return mav;
    }

    // 스크립트 측정 결과 목록 조회
    @PostMapping("/scripts")
    @ResponseBody
    public Map<Script, List<Measure>> getScripts(@RequestBody Script script) {
        return null;
    }

    // 스크립트 등록 폼
    @GetMapping("/script/form")
    public ModelAndView registerScript() {
        ModelAndView modelAndView = new ModelAndView("script/upload");
        return modelAndView;
    }
    // TODO : 프로그램 목록 수정
    // 스크립트 배포
    @PostMapping("/script")
    public ModelAndView distributeScript(
                @RequestParam("sourceFile") MultipartFile sourceFile,
                    @RequestParam("classFile") MultipartFile classFile,
                        @RequestParam("name") String measureName,
                            Attach attach, Script script) {
        ModelAndView modelAndView = null;
        logger.debug("### sciprt 배포 진입");

        if (!sourceFile.isEmpty() && !classFile.isEmpty()) {
            logger.debug("### 소스파일 클래스파일 존재");

            String sourceFileName = FilenameUtils.getBaseName((sourceFile.getOriginalFilename()));
            String classFileName = FilenameUtils.getBaseName((classFile.getOriginalFilename()));
            if (sourceFileName.equals(classFileName)) {
                logger.debug("### 소스파일 클래스파일 동일, 스크립트 배포 시작");

                if (managementService.distributeScript(classFile)) {
                    String scriptName = FilenameUtils.getBaseName(
                            sourceFile.getOriginalFilename());

                    script.setName(scriptName);
                    logger.debug("### sciprt 등록 합니다");

                    int scriptNo = scriptService.registerScript(script);
                    logger.debug("### sciprt 등록 완료");

                    attach.setScriptNo(scriptNo);
                    logger.debug("### sciprt 첨부파일 등록합니다");

                    attachService.registerAttach(sourceFile, classFile, attach);
                    logger.debug("### sciprt 첨부파일 등록 완료");

                    measureInfo.setName(measureName);
                    measureInfo.setScriptNo(scriptNo);
                }
            }
        } else {
            modelAndView = new ModelAndView(new RedirectView("/script/form"));
        }

        return modelAndView;
    }

    // 스크립트 측정 결과 조회
    public ModelAndView getScript(Script script) {
        return null;
    }

    //스크립트 측정 결과 다운로드
    public void downloadScript(Script script) {

    }

    /* 측정 결과 등록 테스트 */
    @GetMapping("/test")
    public ModelAndView measureInsertTest() {
        ModelAndView mav = new ModelAndView("test");

        setting();
        Measure measure = new Measure("등록 테스트"
                                    , "00325-96018-79885-AAOEM"
                                    , 1
                                    , 123123
                                    , "Y");

//        logger.debug("------------->" + measure);
        System.out.println("------------->" + measure);

        measureService.registerMeasure(measure);

        return mav;
    }

    /* 측정 결과 수신 */
    @PostMapping(value = "/scripts/result")
    @ResponseBody
    public Map<String, String> receiveScript(
            @RequestBody Measure measure, HttpServletResponse httpServletResponse) {
        Map<String, String> responseData = new HashMap<>();

        Map<Integer, String> statusRepository = new HashMap<>();
        statusRepository.put(statusCode.NOT_MODIFIED, responseMessage.NOT_MODIFIED_MSG);
        statusRepository.put(statusCode.BAD_REQUEST, responseMessage.BAD_REQUEST_MSG);
        statusRepository.put(statusCode.NOT_FOUND, responseMessage.NOT_FOUND_MSG);
        statusRepository.put(statusCode.INTERNAL_SERVER_ERROR, responseMessage.INTERNAL_SERVER_ERROR_MSG);

        int code = httpServletResponse.getStatus();
        String message = statusRepository.get(code);

        if (message != null) {
            responseData.put("code", String.valueOf(code));
            responseData.put("message", message);
        } else {
            responseData.put("code", String.valueOf(code));
            responseData.put("message", null);
        }

        // 입력 값을 검증한다.
        if (measure.getDeviceId() != null) {
            // 측정 결과 명, 스크립트 일련번호를 메모리에서 가져와 지정한다.
            setting();
            measure.setName(measureInfo.getName());
            measure.setScriptNo(measureInfo.getScriptNo());

            // 측정 결과를 등록한다.
            measureService.registerMeasure(measure);
            logger.debug("-------------> " + measure.getName());
        } else {
            responseData.put("message", "수신 데이터가 존재하지 않습니다.");
        }

        return responseData;
    }

    // TODO: 스크립트 배포 시 메모리에 저장
    public void setting() {
        measureInfo.setScriptNo(1);
        measureInfo.setName("반복문 측정");
    }
}
