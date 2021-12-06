package kr.co.dpm.system.util;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.measure.MeasureService;
import kr.co.dpm.system.script.ScriptController;
import kr.co.dpm.system.script.ScriptFileRepository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class DistributeUtil implements Runnable{
    private Device device;
    private File classFile;
    private ScriptFileRepository scriptFileRepository;
    private MeasureService measureService;
    private Measure measure;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public File getClassFile() {
        return classFile;
    }

    public void setClassFile(File classFile) {
        this.classFile = classFile;
    }

    public void setScriptFileRepository(ScriptFileRepository scriptFileRepository) {
        this.scriptFileRepository = scriptFileRepository;
    }

    public void setMeasureService(MeasureService measureService) {
        this.measureService = measureService;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    @Override
    public void run() {
        try {
            Cryptogram cryptogram = new Cryptogram(device.getId());
            String encryptResult = cryptogram.encryption(device.getId());

            if (scriptFileRepository.distribute(classFile, encryptResult, device.getIpAddress())) {
                ScriptController.distributeCount++;

            } else {
                Measure measure = new Measure();

                measure.setScriptNo(this.measure.getScriptNo());
                measure.setName(this.measure.getName());
                measure.setDistributeStatus("N");
                measure.setStatus("N");
                measure.setExecTime(0);
                measure.setDeviceId(device.getId());
                measure.setDeviceName(device.getName());

                measureService.registerMeasure(measure);
            }

        } catch (Exception e) {
            e.printStackTrace();

            measure.setDistributeStatus("N");
            measure.setStatus("N");
            measure.setExecTime(0);
            measure.setDeviceId(device.getId());
            measure.setDeviceName(device.getName());

            measureService.registerMeasure(measure);
        }
    }
}
