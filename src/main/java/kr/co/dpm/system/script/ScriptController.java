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

    @Value("${path}")
    private String path;

    @Value("${excelPath}")
    private String excelPath;

    private Measure measureInfo = new Measure();
    private boolean measureStatus = false;

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

    /*  스크립트 측정 결과 목록 검색 */
    @PostMapping
    public Map<String, Object> searchScripts(@RequestBody Map<String, String> condition) {
        Map<String, Object> result = new HashMap<String, Object>();

        Script conditionScript = new Script();

        conditionScript.setName(condition.get("scriptName"));
        conditionScript.setUploadPoint(condition.get("uploadPoint"));

        List<Script> scripts = scriptService.getScripts(conditionScript);
        List<Measure> scriptMeasure = new ArrayList<>();

        Script firstScript = scripts.get(0);

        Measure measure = new Measure();

        if (measureStatus) {
            measure.setStatus("N");
            measure.setName(measureInfo.getName());

            scriptMeasure.add(measure);

        } else {
            measure.setScriptNo(firstScript.getNo());

            logger.debug("---------> 첫번째 스크립트 : " + firstScript);   //debug

            List<Measure> measures = measureService.getMeasures(measure);

            logger.debug("---------> 첫번째 스크립트에 해당하는 결과 : " + measures); //debug

            measure.setName(measures.get(0).getName());
            if (measure.getName() != null) {
                measure.setStatus("Y");

                scriptMeasure.add(measure);
            }
        }

        logger.debug(scriptMeasure);        //Debug

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

        logger.debug("--------> 목록 검색 결과 : " + scripts + scriptMeasure);

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
        ModelAndView mav = new ModelAndView(new RedirectView("/scripts/form"));

        if (!sourceFile.isEmpty() && !classFile.isEmpty()) {
            String sourceFileName = FilenameUtils.getBaseName((sourceFile.getOriginalFilename()));
            String classFileName = FilenameUtils.getBaseName((classFile.getOriginalFilename()));
            if (sourceFileName.equals(classFileName)) {
                if (managementService.distributeScript(classFile)) {
                    Script script = new Script();
                    String scriptName = FilenameUtils.getBaseName(sourceFile.getOriginalFilename());
                    script.setName(scriptName);

                    scriptService.registerScript(script);

                    Attach attach = new Attach();
                    int scriptNo = script.getNo();
                    attach.setScriptNo(scriptNo);

                    attachService.registerAttach(sourceFile, classFile, attach);

                    measureInfo.setName(measureName);
                    measureInfo.setScriptNo(scriptNo);
                    measureStatus = true;

                    mav = new ModelAndView(new RedirectView("/scripts"));
                } else {
                    mav = new ModelAndView("script/register");
                    mav.addObject("distributeFail", "배포된 디바이스가 없습니다.");
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
        String fileName = excelUtil.createExcel(scriptService.getScript(script));

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
            measure.setScriptNo(measureInfo.getScriptNo());
            measureService.registerMeasure(measure);
        } else {
            responseData.put("message", "수신 데이터가 존재하지 않습니다.");
        }

        measureStatus = false;

        return responseData;
    }
}
