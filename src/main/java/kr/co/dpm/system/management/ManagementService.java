package kr.co.dpm.system.management;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.script.Script;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public interface ManagementService {
    public void receiveId(String id);

    public String createPassword();

    public boolean receiveDevice(Device device);

    public boolean distributeScript(Script script);

    public File encryption(Script script);

    public boolean receiveMeasure(Measure measure);
}
