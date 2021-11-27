package kr.co.dpm.system.script;

import kr.co.dpm.system.common.ResponseMessage;
import kr.co.dpm.system.common.StatusCode;
import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceServiceImpl;
import kr.co.dpm.system.management.ManagementServiceImpl;
import kr.co.dpm.system.measure.Measure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import kr.co.dpm.system.common.ResponseMessage;
import kr.co.dpm.system.common.StatusCode;
import kr.co.dpm.system.measure.MeasureServiceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

@RestController
public class ScriptController {
    private static final Logger logger = LogManager.getLogger(ScriptController.class);

    @Value("{$path}")
    private String path;

    private Measure measureInfo = new Measure();

    @Autowired
    private MeasureServiceImpl measureService;

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private ScriptServiceImpl scriptService;

    @Autowired
    private AttachServiceImpl attachService;

    @Autowired
    private ManagementServiceImpl managementService;

    @Autowired
    private StatusCode statusCode;

    @Autowired
    private ResponseMessage responseMessage;

    //  스크립트 측정 결과 목록 폼
    @GetMapping("/scripts")
    public ModelAndView getScripts() {
        ModelAndView mav = new ModelAndView("script/list");
        List<Measure> scriptMeasure = new ArrayList<>();

        List<Script> scripts = scriptService.getScripts(null);
        mav.addObject("scripts", scripts);

        for (Script object : scripts) {
            Measure measure = new Measure();
            measure.setScriptNo(object.getNo());

            List<Measure> measures = measureService.getMeasures(measure);

            measure.setName(measures.get(0).getName());

            if (measure.getName() == null) {
                measure.setStatus("N");
                measure.setName("...");
            } else {
                measure.setStatus("Y");
            }

            scriptMeasure.add(measure);
        }

        mav.addObject("scriptMeasure", scriptMeasure);

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

    // 스크립트 배포
    @PostMapping("/script")
    public Map<String, String> distributeScript(
                @RequestParam("sourceFile") MultipartFile sourceFile,
                    @RequestParam("classFile") MultipartFile classFile,
                        Script script) {
        // 입력 값이 존재 하는가?
        if (!sourceFile.isEmpty() && !classFile.isEmpty()) {
            managementService.distributeScript(classFile);
        } else {
            return null;
        }

        return null;
    }

    // 스크립트 측정 결과 조회
    @GetMapping("/scripts/{no}")
    public ModelAndView getScript(Script script) {
        ModelAndView mav = new ModelAndView("script/view");

        mav.addObject("script", scriptService.getScript(script));

        Attach attach = new Attach();
        attach.setScriptNo(script.getNo());
        mav.addObject("attaches", attachService.getAttaches(attach));

        Measure measure = new Measure();
        measure.setScriptNo(script.getNo());
        List<Measure> measures = measureService.getMeasures(measure);
        for (int i = 0; i < measures.size(); i++) {
            Device device = new Device();
            device.setId(measures.get(i).getDeviceId());

            measures.get(i).setDeviceName(deviceService.getDevice(device).getName());

            logger.debug("-------> 측정 결과: " + i + measures.get(i).getName());
        }

        mav.addObject("measures", measures);

        return mav;
    }

    //스크립트 측정 결과 다운로드
    public void downloadScript(Script script) {

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
        measureInfo.setName("테스트");
    }
}
