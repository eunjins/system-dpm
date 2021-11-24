package kr.co.dpm.system.device.controller;

import kr.co.dpm.system.device.service.DeviceServiceImpl;
import kr.co.dpm.system.model.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceServiceImpl deviceServiceImpl;

    // 목록 조회
    @GetMapping
    public ModelAndView getDevices(Device device) {
        ModelAndView mav = new ModelAndView("device/list");

        mav.addObject("devices", deviceServiceImpl.getDevices(device));

        return mav;
    }
    
    // 조회
    @GetMapping({"/{id}"})
    public ModelAndView getDevice(Device device) {
        ModelAndView mav = new ModelAndView("device/view");

        mav.addObject("device", deviceServiceImpl.getDevice(device));

        return mav;
    }
    
    // 수정
    @GetMapping("/{id}")
    public ModelAndView editDevice(@RequestParam String id) {

        return null;
    }

    // 삭제
    public ModelAndView editDevice(Device device) {
        return null;
    }
}