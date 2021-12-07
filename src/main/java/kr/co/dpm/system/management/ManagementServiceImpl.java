package kr.co.dpm.system.management;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceService;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.measure.MeasureService;
import kr.co.dpm.system.script.ScriptFileRepository;
import kr.co.dpm.system.util.Cryptogram;
import kr.co.dpm.system.util.DistributeUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ManagementServiceImpl implements ManagementService {
    private static final Logger logger = LogManager.getLogger(Cryptogram.class);

    @Value("${path}")
    private String path;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private MeasureService measureService;

    @Autowired
    private ScriptFileRepository scriptFileRepository;

    @Override
    public void receiveDevice(Device device) {
        device.setId(device.getId());

        if (deviceService.getDevice(device) != null) {
            deviceService.editDevice(device);
        } else {
            String nowDate = String.valueOf(LocalDate.now());

            device.setName(device.getId());
            device.setInsertDate(nowDate);

            deviceService.registerDevice(device);
        }
    }

    @Override
    public boolean distributeScript(MultipartFile classFile, Measure measure) throws Exception {
        List<Device> devices = deviceService.getDevices(new HashMap<>());

        File convertFile = new File(path + File.separator + classFile.getOriginalFilename());

        FileOutputStream fileOutputStream = new FileOutputStream(convertFile);
        fileOutputStream.write(classFile.getBytes());
        fileOutputStream.close();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (Device device : devices) {
            if (device.getStatus().equals("Y")) {
                DistributeUtil distributeUtil = new DistributeUtil();
                distributeUtil.setDevice(device);
                distributeUtil.setClassFile(convertFile);
                distributeUtil.setScriptFileRepository(scriptFileRepository);
                distributeUtil.setMeasureService(measureService);

                distributeUtil.setMeasure(measure);

                executorService.submit(distributeUtil);
            }
        }

        return true;
    }
}
