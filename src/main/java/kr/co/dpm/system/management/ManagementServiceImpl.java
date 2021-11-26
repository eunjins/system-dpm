package kr.co.dpm.system.management;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceServiceImpl;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.script.Script;
import kr.co.dpm.system.script.ScriptFileRepositoryImpl;
import kr.co.dpm.system.utility.Cryptogram;
import kr.co.dpm.system.utility.CryptogramImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
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
        // TODO 목록에 있는 디바이스에 클래스 파일 배포
        //List<Device> devices = deviceService.getDevices(new Device());

        // TODO 단일 배포 테스트
        try {
            // TODO 단일 배포 성공시 리스트에 있는 디바이스 전부 배포하기 구현
            CryptogramImpl cryptogram = new CryptogramImpl("00312-95723-25321-AAOEN");
            String encryptResult = cryptogram.encryption("00312-95723-25321-AAOEN");

            logger.debug("암호화 후 " + encryptResult);

            scriptFileRepository.distribute(classFile, encryptResult);
        } catch(Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
