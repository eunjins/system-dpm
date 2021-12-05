package kr.co.dpm.system.script;

import kr.co.dpm.system.common.StatusCode;
import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceService;
import kr.co.dpm.system.management.ManagementService;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.measure.MeasureService;
import kr.co.dpm.system.util.ExcelUtil;
import kr.co.dpm.system.util.Navigator;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    private MeasureService measureService;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ScriptService scriptService;

    @Autowired
    private AttachService attachService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private Navigator navigator;

    @Autowired
    private ExcelUtil excelUtil;

    @Autowired
    private StatusCode statusCode;

    @GetMapping
    public ModelAndView getScripts() {
        ModelAndView mav = new ModelAndView("script/list");
        return mav;
    }

    @PostMapping
    public Map<String, Object> getScripts(@RequestBody Map<String, String> inputCondition) {
        Map<String, String> condition = new HashMap<>();

        condition.put("name", inputCondition.get("scriptName"));
        condition.put("uploadPoint", inputCondition.get("uploadPoint"));

        int scriptsCount = scriptService.getScripts(condition).size();

        Integer pageNo = Integer.valueOf(inputCondition.get("pageNo")) * 10;
        condition.put("pageNo", (pageNo.toString()));

        List<Script> scripts = scriptService.getScripts(condition);

        List<Measure> scriptMeasure = new ArrayList<Measure>();

        Script firstScript = scripts.get(0);

        Measure measure = new Measure();

        String conditionMeasureName = inputCondition.get("measureName") != null ?
                inputCondition.get("measureName").trim()
                : "";

        int scriptStartNo = 0;
        if (distributeCount > 0) {
            if ((measureInfo.getName()).indexOf(conditionMeasureName) != -1
                    || "".equals(conditionMeasureName)) {
                measure.setStatus("N");
                measure.setName(measureInfo.getName());
                scriptMeasure.add(measure);
                scriptStartNo = 1;
            }

        } else {
            measure.setScriptNo(firstScript.getNo());

            List<Measure> measures = measureService.getMeasures(measure);

            if ((measures.get(0).getName()).indexOf(conditionMeasureName) != -1
                    || "".equals(conditionMeasureName)) {
                measure.setName(measures.get(0).getName());
                if (measure.getName() != null) {
                    measure.setStatus("Y");

                    scriptMeasure.add(measure);
                    scriptStartNo = 1;
                }
            }
        }

        for (int i = scriptStartNo; i < scripts.size(); i++) {
            Script object = scripts.get(i);

            measure = new Measure();
            measure.setScriptNo(object.getNo());
            measure.setName(conditionMeasureName);

            List<Measure> measures = measureService.getMeasures(measure);

            if (measures.isEmpty()) {
                scripts.remove(i--);

            } else {
                measure.setName(measures.get(0).getName());
                measure.setStatus("Y");
                scriptMeasure.add(measure);
            }
        }

        Map<String, Object> result = new HashMap<>();

        result.put("scripts", scripts);
        result.put("scriptMeasure", scriptMeasure);

        if (distributeCount > 0) {
            result.put("addButton", "N");
        } else {
            result.put("addButton", "Y");
        }

        String navigatorHtml = navigator.getNavigator(scriptsCount, pageNo / 10);
        result.put("navigator", navigatorHtml);

        return result;
    }

    @GetMapping("/form")
    public ModelAndView registerScript() {
        return new ModelAndView("script/register");
    }

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
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (distributeCount == 0) {
                    mav = new ModelAndView("script/register");
                    mav.addObject("distributeFail", "배포된 디바이스가 없습니다");

                } else {
                    scriptService.registerScript(script);

                    int scriptNo = script.getNo();
                    attach.setScriptNo(scriptNo);

                    try {
                        measureInfo.setScriptNo(scriptNo);
                        attachService.registerAttach(sourceFile, classFile, attach);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return mav;
    }

    @GetMapping("/{no}")
    public ModelAndView getScript(Script script) {
        ModelAndView mav = new ModelAndView("script/view");

        if (script.getNo() < 1) {
            mav = new ModelAndView(new RedirectView("/scripts"));
        }

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

    @GetMapping("/excel/{no}")
    public void downloadExcel(Script script, HttpServletResponse response) {
        if (script.getNo() < 1) {
            return;
        }

        File excelFile = null;
        String fileName = null;
        OutputStream outputStream = null;

        try {
            excelFile = excelUtil.createExcel(scriptService.getScript(script));
            fileName = excelFile.getName();

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

    @PostMapping(value = "/result")
    public Map<String, String> receiveScript(
            @RequestBody Measure measure, HttpServletResponse httpServletResponse) {
        logger.debug("-------> 측정 결과 수신 " + measure.toString());

        Map<String, String> responseData = new HashMap<>();

        int code = httpServletResponse.getStatus();
        String message = statusCode.getStatusRepository().get(code);

        responseData.put("code", String.valueOf(code));

        if (message != null) {
            responseData.put("message", message);

        } else {
            responseData.put("message", null);
        }

        if (measure.getDeviceId() != null) {
            if (measureService.getMeasure(measure) != null) {
                measure.setName(measureInfo.getName());

                try {
                    while (true) {
                        if (measureInfo.getScriptNo() == 0) {
                            Thread.sleep(1000);

                        } else {
                            break;
                        }
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                measure.setScriptNo(measureInfo.getScriptNo());
                measureService.registerMeasure(measure);
            }

        } else {
            responseData.put("message", "수신 데이터가 존재하지 않습니다.");
        }

        return responseData;
    }
}
