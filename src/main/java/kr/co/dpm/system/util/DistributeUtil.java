package kr.co.dpm.system.util;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.script.ScriptController;
import kr.co.dpm.system.script.ScriptFileRepository;
import org.springframework.web.multipart.MultipartFile;

public class DistributeUtil implements Runnable{
    private Device device;
    private MultipartFile classFile;
    private ScriptFileRepository scriptFileRepository;

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public MultipartFile getClassFile() {
        return classFile;
    }

    public void setClassFile(MultipartFile classFile) {
        this.classFile = classFile;
    }

    public void setScriptFileRepository(ScriptFileRepository scriptFileRepository) {
        this.scriptFileRepository = scriptFileRepository;
    }

    @Override
    public void run() {
        try {
            Cryptogram cryptogram = new Cryptogram(device.getId());
            String encryptResult = cryptogram.encryption(device.getId());

            if (scriptFileRepository.distribute(classFile, encryptResult, device.getIpAddress())) {
                ScriptController.distributeCount ++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
