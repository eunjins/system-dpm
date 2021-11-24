package kr.co.dpm.system.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/devices")
public class DeviceController {
    @Autowired
    private DeviceServiceImpl deviceServiceImpl;

    // 수신
    @PostMapping("/data")
    public ModelAndView receiveDevice(Device device) {

        return null;
    }


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
    
    // 수정 폼
    @GetMapping("/{id}/form")
    public ModelAndView editDevice(Device device) {
        ModelAndView mav = new ModelAndView("device/edit");

        mav.addObject("device", deviceServiceImpl.getDevice(device));

        return mav;
    }

    // 수정
    @PutMapping("/{id}")
    public ModelAndView editDevice(@PathVariable("id") String id) {
        Device device = new Device();
        device.setId(id);

        deviceServiceImpl.editDevice(device);

        return new ModelAndView("/devices/" + id);
    }
}