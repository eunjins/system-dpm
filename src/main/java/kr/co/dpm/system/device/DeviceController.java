package kr.co.dpm.system.device;

import kr.co.dpm.system.common.ResponseMessage;
import kr.co.dpm.system.common.StatusCode;
import kr.co.dpm.system.management.ManagementService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private DeviceService deviceService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private ResponseMessage responseMessage;

    @Autowired
    private StatusCode statusCode;

    /* 디바이스 목록 폼 */
    @GetMapping
    public ModelAndView getDevices() {
        ModelAndView mav = new ModelAndView("device/list");
        mav.addObject("devices", deviceService.getDevices(new Device()));

        return mav;
    }

    /* 디바이스 목록 조회 */
    @PostMapping
    public List<Device> getDevices(@RequestBody Device device) {
        return deviceService.getDevices(device);
    }

    /* 디바이스 상세 조회 */
    @GetMapping({"/{id}"})
    public ModelAndView getDevice(Device device) {
        ModelAndView mav = new ModelAndView("device/view");

        mav.addObject("device", deviceService.getDevice(device));

        return mav;
    }

    /* 디바이스 수정 폼 */
    @GetMapping("/{id}/form")
    public ModelAndView editDeviceForm(Device device) {
        ModelAndView mav = new ModelAndView("device/edit");

        mav.addObject("device", deviceService.getDevice(device));

        return mav;
    }

    /* 디바이스 수정 */
    @PutMapping("/{id}")
    public ModelAndView editDevice(Device device) {
        deviceService.editDevice(device);

        return new ModelAndView(new RedirectView("/devices/" + device.getId()));
    }

    /* 디바이스 정보 수신 */
    @PostMapping("/data")
    public Map<String, String> receiveDevice(
            @RequestBody Device device, HttpServletResponse httpServletResponse) {
        logger.debug("-------> 디바이스 정보 수신" + device.toString());
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

        if (device.getId() != null && device.getHostName() != null
                && device.getIpAddress() != null && device.getJdkVersion() != null) {
            managementService.receiveDevice(device);
        } else {
            responseData.put("message", "수신 데이터가 존재하지 않습니다.");
        }

        return responseData;
    }
}