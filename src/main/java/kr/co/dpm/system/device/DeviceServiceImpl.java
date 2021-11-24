package kr.co.dpm.system.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<Device> getDevices(Device device) {
        return deviceRepository.selectAll(device);
    }

    @Override
    public Device getDevice(Device device) {
        return deviceRepository.select(device);
    }

    @Override
    public void registerDevice(Device device) {
        deviceRepository.insert(device);
    }

    @Override
    public void editDevice(Device device) {
        deviceRepository.update(device);
    }
}
