package kr.co.dpm.system.script;

import kr.co.dpm.system.common.ResponseMessage;
import kr.co.dpm.system.common.StatusCode;
import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceServiceImpl;
import kr.co.dpm.system.management.ManagementServiceImpl;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.utility.Excel;
import org.apache.commons.io.FileUtils;
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
import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

@RestController
@RequestMapping("/scripts")
public class ScriptController {
    private static final Logger logger = LogManager.getLogger(ScriptController.class);

    @Value("${path}")
    private String path;

    @Value("${excelPath}")
    private String excelPath;

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

    @Autowired
    private Excel excel;

    /*  스크립트 측정 결과 목록 폼 */
    @GetMapping
    public ModelAndView getScripts() {
        ModelAndView mav = new ModelAndView("script/list");

        List<Measure> scriptMeasure = new ArrayList<>();

        List<Script> scripts = scriptService.getScripts(new Script());
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

    /* 스크립트 등록 폼 */
    @GetMapping("/form")
    public ModelAndView registerScript() {
        ModelAndView modelAndView = new ModelAndView("script/register");
        return modelAndView;
    }

    /* 스크립트 배포 */
    @PostMapping("/distribute")
    public ModelAndView distributeScript(
                        @RequestParam("sourceFile") MultipartFile sourceFile,
                        @RequestParam("classFile") MultipartFile classFile,
                        @RequestParam("name") String measureName,
                        Attach attach, Script script) {
        ModelAndView modelAndView = new ModelAndView(new RedirectView("/scripts/form"));

        if (!sourceFile.isEmpty() && !classFile.isEmpty()) {
            logger.debug("-------> 소스파일 클래스파일 존재");

            String sourceFileName = FilenameUtils.getBaseName((sourceFile.getOriginalFilename()));
            String classFileName = FilenameUtils.getBaseName((classFile.getOriginalFilename()));
            if (sourceFileName.equals(classFileName)) {
                logger.debug("-------> 소스파일 클래스파일 이름 일치");

                if (managementService.distributeScript(classFile)) {
                    logger.debug("-------> 스크립트 배포 성공");

                    String scriptName = FilenameUtils.getBaseName(
                                        sourceFile.getOriginalFilename());

                    script.setName(scriptName);

                    int scriptNo = scriptService.registerScript(script);
                    logger.debug("-------> 스크립트 등록 완료");

                    attach.setScriptNo(scriptNo);
                    attachService.registerAttach(sourceFile, classFile, attach);
                    logger.debug("-------> 첨부파일 등록 완료");

                    measureInfo.setName(measureName);
                    measureInfo.setScriptNo(scriptNo);

                    modelAndView = new ModelAndView(new RedirectView("/scripts"));
                }
            }
        }

        return modelAndView;
    }


    /* 스크립트 측정 결과 조회 */
    @GetMapping("/{no}")
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
        }

        mav.addObject("measures", measures);

        return mav;
    }

    /* 스크립트 다운로드 */
    @GetMapping("/file/{no}")
    public void downloadScript(Attach attach, HttpServletResponse response) {
        OutputStream outputStream = null;

        attach = attachService.getAttach(attach);

        try {
            if ("S".equals(attach.getDivision())) {
                attach.setName(attach.getName() + ".java");
            } else {
                attach.setName(attach.getName() + ".class");
            }

            byte[] file = FileUtils.readFileToByteArray(new File(path + File.separator + attach.getName()));

            String encodingName = new String(attach.getName().getBytes("UTF-8"), "ISO-8859-1");

            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodingName + "\"");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setContentType("application/octet-stream");
            response.setContentLength(file.length);

            outputStream = response.getOutputStream();
            outputStream.write(file);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* 스크립트 측정 결과 다운로드 */
    @GetMapping("/excel/{no}")
    public void downloadExcel(Script script, HttpServletResponse response) {
        OutputStream outputStream = null;
        String fileName = excel.create(script);

        try {
            byte[] file = FileUtils.readFileToByteArray(new File(excelPath + File.separator + fileName));

            String encodingName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");

            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodingName + "\"");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setContentType("application/octet-stream");
            response.setContentLength(file.length);

            outputStream = response.getOutputStream();
            outputStream.write(file);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();

                    excel.delete(fileName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* 측정 결과 수신 */
    @PostMapping(value = "/result")
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
}
