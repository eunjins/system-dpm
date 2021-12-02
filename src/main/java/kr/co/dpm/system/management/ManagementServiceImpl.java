package kr.co.dpm.system.management;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceServiceImpl;
import kr.co.dpm.system.script.ScriptFileRepositoryImpl;
import kr.co.dpm.system.util.CryptogramImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ManagementServiceImpl implements ManagementService {
    private static final Logger logger = LogManager.getLogger(CryptogramImpl.class);

    @Autowired
    private DeviceServiceImpl deviceService;

    @Autowired
    private ScriptFileRepositoryImpl scriptFileRepository;

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
        List<Boolean> flags = new ArrayList<Boolean>();

        List<Device> devices = deviceService.getDevices(new Device());
        try {
            for (Device device : devices) {
                if (device.getStatus().equals("N")) {
                    continue;
                } else {
                    CryptogramImpl cryptogram = new CryptogramImpl(device.getId());
                    String encryptResult = cryptogram.encryption(device.getId());

                    flags.add(scriptFileRepository.distribute(classFile, encryptResult, device.getIpAddress()));
                }
            }

            for (boolean flag : flags) {
                if (flag != false) {

                    return true;
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
