package kr.co.dpm.system.management;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceRepository;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.measure.MeasureRepository;
import kr.co.dpm.system.script.ScriptController;
import kr.co.dpm.system.script.ScriptFileRepository;
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
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ManagementServiceImpl implements ManagementService {
    private static final Logger logger = LogManager.getLogger(ManagementServiceImpl.class);

    @Value("${scriptPath}")
    private String path;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private MeasureRepository measureRepository;

    @Autowired
    private ScriptFileRepository scriptFileRepository;

    @Override
    public void receiveDevice(Device device) {
        device.setId(device.getId());

        if (deviceRepository.select(device) != null) {
            deviceRepository.update(device);
        } else {
            String nowDate = String.valueOf(LocalDate.now());
            device.setName(device.getId());
            device.setInsertDate(nowDate);

            deviceRepository.insert(device);
        }
    }

    @Override
    public boolean distributeScript(List<Device> devices, MultipartFile classFile, Measure measure) throws Exception {
        File directory = new File(path);
        if (!directory.isDirectory()) {
            directory.mkdir();
        }

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
                distributeUtil.setMeasureRepository(measureRepository);
                distributeUtil.setMeasure(measure);

                executorService.submit(distributeUtil);
            }
        }

        Thread.sleep(4000);
        convertFile.delete();

        if (ScriptController.distributeCount == 0) {
            logger.info("                      CONNECTED DEVICE IS NULL                         ");
            return false;
        } else {
            return true;
        }
    }
}
