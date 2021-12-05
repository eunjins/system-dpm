package kr.co.dpm.system.management;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceService;
import kr.co.dpm.system.script.ScriptFileRepository;
import kr.co.dpm.system.util.Cryptogram;
import kr.co.dpm.system.util.DistributeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class ManagementServiceImpl implements ManagementService {
    private static final Logger logger = LogManager.getLogger(Cryptogram.class);

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ScriptFileRepository scriptFileRepository;

    @Override
    public void receiveDevice(Device device) {
        device.setId(device.getId());

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
    public boolean distributeScript(MultipartFile classFile) {
        List<Device> devices = deviceService.getDevices(new HashMap<String, String>());

        for (Device device : devices) {
            if (device.getStatus().equals("Y")) {
                DistributeUtil distributeUtil = new DistributeUtil();
                distributeUtil.setDevice(device);
                distributeUtil.setClassFile(classFile);
                distributeUtil.setScriptFileRepository(scriptFileRepository);

                Thread thread = new Thread(distributeUtil);

                thread.start();
            }
        }
        return true;
    }
}
