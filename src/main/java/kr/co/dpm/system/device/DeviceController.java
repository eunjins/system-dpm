package kr.co.dpm.system.device;

import kr.co.dpm.system.util.StatusCode;
import kr.co.dpm.system.management.ManagementService;
import kr.co.dpm.system.util.Navigator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/devices")
public class DeviceController {
    private static final Logger logger = LogManager.getLogger(DeviceController.class);

    @Autowired
    private StatusCode statusCode;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private Navigator navigator;

    @GetMapping
    public ModelAndView getDevices() {
        ModelAndView mav = new ModelAndView("device/list");

        return mav;
    }

    @PostMapping
    public Map<String, Object> getDevices(@RequestBody Map<String, String> inputCondition) {
        Map<String, String> condition = new HashMap<>();
        Map<String, Object> result = new HashMap<>();

        condition.put("name", inputCondition.get("name"));
        condition.put("insertDate", inputCondition.get("insertDate"));
        condition.put("status", inputCondition.get("status"));

        int deviceCount = deviceService.getDevices(condition).size();

        Integer pageNo = Integer.valueOf(inputCondition.get("pageNo"));
        condition.put("pageNo", String.valueOf(pageNo * 10));

        List<Device> devices = deviceService.getDevices(condition);
        result.put("devices", devices);

        String navigatorHtml = navigator.getNavigator(deviceCount, pageNo);
        result.put("navigator", navigatorHtml);

        return result;
    }

    @GetMapping("/{id}")
    public ModelAndView getDevice(Device device) {
        ModelAndView mav = new ModelAndView("device/view");
        mav.addObject("device", deviceService.getDevice(device));

        return mav;
    }

    @GetMapping("/{id}/form")
    public ModelAndView editDeviceForm(Device device) {
        ModelAndView mav = new ModelAndView("device/edit");
        mav.addObject("device", deviceService.getDevice(device));

        return mav;
    }

    @PutMapping("/{id}")
    public ModelAndView editDevice(Device device) {
        ModelAndView mav = null;

        if (deviceService.getDevice(device) == null) {
            mav = new ModelAndView(new RedirectView("/devices"));

            return mav;
        }

        device.setName((device.getName()).trim());

        try {
            deviceService.editDevice(device);
        } catch(Exception e) {
            logger.error("중복된 이름으로 수정");
        }

        mav = new ModelAndView(new RedirectView("/devices/" + device.getId()));

        return mav;
    }

    @PostMapping("/data")
    public Map<String, String> receiveDevice(
            @RequestBody Device device, HttpServletResponse httpServletResponse) {
        logger.debug("-------> 디바이스 정보 수신" + device.toString());
        Map<String, String> responseData = new HashMap<>();

        int code = httpServletResponse.getStatus();
        String message = statusCode.getStatusRepository().get(code);

        responseData.put("code", String.valueOf(code));

        if (message != null) {
            responseData.put("message", message);
        } else {
            responseData.put("message", null);
        }

        if (device.getId() != null && device.getHostName() != null
                && device.getIpAddress() != null && device.getJdkVersion() != null) {
            managementService.receiveDevice(device);
        } else {
            responseData.put("message", "수신 데이터가 존재하지 않습니다.");
        }

        return responseData;
    }
}