package kr.co.dpm.system.script;

import kr.co.dpm.system.common.ResponseMessage;
import kr.co.dpm.system.common.StatusCode;
import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceServiceImpl;
import kr.co.dpm.system.management.ManagementServiceImpl;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.util.ExcelUtil;
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
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

@RestController
@RequestMapping("/scripts")
public class ScriptController {
    private static final Logger logger = LogManager.getLogger(ScriptController.class);
    public static int distributeCount;

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
    private ExcelUtil excelUtil;

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
            if (measures.isEmpty()) {
                measure.setStatus("N");
                measure.setName(measureInfo.getName());

                scriptMeasure.add(measure);

                continue;
            }

            measure.setName(measures.get(0).getName());
            if (measure.getName() == null) {
                measure.setStatus("N");
                measure.setName(measureInfo.getName());
            } else {
                measure.setStatus("Y");
            }
            scriptMeasure.add(measure);
        }
        mav.addObject("scriptMeasure", scriptMeasure);

        return mav;
    }

    /*  스크립트 측정 결과 목록 조회 */
    @PostMapping
    public Map<String, Object> getScripts(@RequestBody Map<String, String> condition) {
        Map<String, Object> result = new HashMap<String, Object>();

        Script conditionScript = new Script();

        conditionScript.setName(condition.get("scriptName"));
        conditionScript.setUploadPoint(condition.get("uploadPoint"));

        List<Script> scripts = scriptService.getScripts(conditionScript);
        List<Measure> scriptMeasure = new ArrayList<>();

        Script firstScript = scripts.get(0);

        Measure measure = new Measure();

        if (distributeCount > 0) {
            measure.setStatus("N");
            measure.setName(measureInfo.getName());

            scriptMeasure.add(measure);

        } else {
            measure.setScriptNo(firstScript.getNo());

            List<Measure> measures = measureService.getMeasures(measure);


            measure.setName(measures.get(0).getName());
            if (measure.getName() != null) {
                measure.setStatus("Y");

                scriptMeasure.add(measure);
            }
        }

        for (int i = 1; i < scripts.size(); i++) {
            Script object = scripts.get(i);

            measure = new Measure();
            measure.setScriptNo(object.getNo());

            List<Measure> measures = measureService.getMeasures(measure);

            if (measures.isEmpty()) {
                scripts.remove(object);
                i--;

            } else {
                measure.setName(measures.get(0).getName());
                measure.setStatus("Y");
                scriptMeasure.add(measure);
            }
        }

        result.put("scripts", scripts);
        result.put("scriptMeasure", scriptMeasure);

        if (distributeCount > 0) {
            result.put("addButton", "N");
        } else {
            result.put("addButton", "Y");
        }

        return result;
    }

    /* 스크립트 등록 폼 */
    @GetMapping("/form")
    public ModelAndView registerScript() {
        return new ModelAndView("script/register");
    }

    /* 스크립트 배포 */
    @PostMapping("/distribute")
    public ModelAndView distributeScript(
            @RequestParam("sourceFile") MultipartFile sourceFile,
            @RequestParam("classFile") MultipartFile classFile,
            @RequestParam("name") String measureName) {
        measureInfo.setScriptNo(0);
        measureInfo.setName(measureName);

        ModelAndView mav = new ModelAndView(new RedirectView("/scripts/form"));
        Attach attach = new Attach();
        Script script = new Script();

        if (!sourceFile.isEmpty() && !classFile.isEmpty()) {
            String sourceFileExtension = FilenameUtils.getExtension(sourceFile.getOriginalFilename());
            String classFileExtension = FilenameUtils.getExtension(classFile.getOriginalFilename());
            if (!("java".equals(sourceFileExtension) || "class".equals(classFileExtension))) {
                mav = new ModelAndView("script/register");
                mav.addObject("extensionMiss", "스크립트 확장자를 확인하세요.");

                return mav;
            }

            String sourceFileName = FilenameUtils.getBaseName((sourceFile.getOriginalFilename()));
            String classFileName = FilenameUtils.getBaseName((classFile.getOriginalFilename()));
            if (sourceFileName.equals(classFileName)) {
                distributeCount = 0;

                if (managementService.distributeScript(classFile)) {
                    String scriptName = FilenameUtils.getBaseName(sourceFile.getOriginalFilename());
                    script.setName(scriptName);

                    mav = new ModelAndView(new RedirectView("/scripts"));

                }

                try {
                    Thread.sleep(4000);     //TODO 스크립트 송신 체크 시간
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (distributeCount == 0) {
                    mav = new ModelAndView("script/register");
                    mav.addObject("distributeFail", "배포된 디바이스가 없습니다.");
                } else {
                    scriptService.registerScript(script);

                    int scriptNo = script.getNo();
                    attach.setScriptNo(scriptNo);

                    measureInfo.setScriptNo(scriptNo);
                    attachService.registerAttach(sourceFile, classFile, attach);
                }
            }
        }

        return mav;
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

            byte[] file = FileUtils.readFileToByteArray(new File(path + File.separator + attach.getPhysicName()));
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
        File excelFile = excelUtil.createExcel(scriptService.getScript(script));
        String fileName = excelFile.getName();

        try {
            byte[] file = FileUtils.readFileToByteArray(excelFile);

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

                    excelUtil.deleteExcel(fileName);
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
        logger.debug("-------> 측정 결과 수신 " + measure.toString());

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

        if (measure.getDeviceId() != null) {
            measure.setName(measureInfo.getName());

            while (true) {
                if (measureInfo.getScriptNo() == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }

            measure.setScriptNo(measureInfo.getScriptNo());

            logger.debug("-------> 등록 측정 결과 정보 " + measure);

            measureService.registerMeasure(measure);

            logger.debug("-------> 배포중 디바이스 개수 : " + --distributeCount);

        } else {
            responseData.put("message", "수신 데이터가 존재하지 않습니다.");
        }

        return responseData;
    }
}
