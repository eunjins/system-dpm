package kr.co.dpm.system.device.controller;

import kr.co.dpm.system.device.service.DeviceServiceImpl;
import kr.co.dpm.system.model.Device;
import kr.co.dpm.system.status.ResponseMessage;
import kr.co.dpm.system.status.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@RestController
public class DeviceController {
    @Autowired
    private ResponseMessage responseMessage;

    @Autowired
    private StatusCode statusCode;

    @Autowired
    private DeviceServiceImpl deviceService;

    //디바이스 목록 조회
    @GetMapping
    public ModelAndView getDevices(Device device) {
        return null;
    }

    //디바이스 정보 조회
    @GetMapping("/device/{deviceId}")
    public ModelAndView getDevice(Device device) {
        return null;
    }

    //디바이스 수정 폼
    @GetMapping("/device/{deviceId}/form")
    public ModelAndView editDevice(String string) {
        return null;
    }

    //디바이스 정보 수정
    @PutMapping("/device")
    public ModelAndView editDevice(Device device) {
        return null;
    }

    //디바이스 정보 수신
    @PostMapping("/device/data")
    public ModelAndView receiveDevice(Device device) {
        return null;
    }

    //디바이스 아이디 수신
    @PostMapping("/id/check")
    public Map<String, String> receiveDeviceId(
                                @RequestBody Map<String, String> deviceId,
                                    HttpServletResponse httpServletResponse) {
        Map<String, String> responseData = new HashMap<>();

        Map<Integer, String> statusRepository = new HashMap<>();
        statusRepository.put(statusCode.OK, responseMessage.OK_MSG);
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
            responseData.put("message", message);
        }

        if (deviceId != null) {
            String password = deviceService.createPassword("deviceId");
            responseData.put("password", password);
        }

        return responseData;
    }
}
