package kr.co.dpm.system.management;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceServiceImpl;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.script.Script;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ManagementServiceImpl implements ManagementService {
    @Autowired
    private DeviceServiceImpl deviceService;

    @Override
    public void receiveDevice(Device device) {

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
        //비밀 키 생성

        //암호화 진행
        return null;
    }

    @Override
    public boolean distributeScript(Script script) {
        // 디바이스 정보 목록 조회
        List<Device> devices = deviceService.getDevices(new Device());
        for (Device device : devices) {
            String id = device.getId();
            String result = encryption(id);
        }

        return false;
    }
}
