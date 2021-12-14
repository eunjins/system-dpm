package kr.co.dpm.system.management;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.measure.Measure;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface ManagementService {
    public void receiveDevice(Device device);

    public boolean distributeScript(List<Device> devices, MultipartFile classFile, Measure measure) throws Exception;
}
