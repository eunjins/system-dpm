package kr.co.dpm.system.device;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface DeviceService {
    public List<Device> getDevices(Map<String, Object> condition);

    public Device getDevice(Device device);

    public void registerDevice(Device device);

    public void editDevice(Device device);
}
