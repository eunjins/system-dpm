package kr.co.dpm.system.management;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceServiceImpl;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.script.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ManagementServiceImpl implements ManagementService {
    @Autowired
    private DeviceServiceImpl deviceService;

    @Override
    public void receiveDevice(Device device) {
        device.setId(device.getDeviceId());
        if (deviceService.getDevice(device) != null) {
            deviceService.editDevice(device);
        } else {
            String nowDate = String.valueOf(LocalDate.now());
            device.setName(device.getHostName());
            device.setInsertDate(nowDate);
            deviceService.registerDevice(device);
        }
    }

    @Override
    public String encryption(String id) {
        return null;
    }

    @Override
    public boolean distributeScript(Script script) {
        return false;
    }

    @Override
    public boolean receiveMeasure(Measure measure) {
        return false;
    }
}
