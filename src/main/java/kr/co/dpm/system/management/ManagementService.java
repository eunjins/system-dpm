package kr.co.dpm.system.management;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.script.Script;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ManagementService {
    public void receiveDevice(Device device);

    public boolean distributeScript(MultipartFile classFile);

//    public boolean receiveMeasure(Measure measure);
}
