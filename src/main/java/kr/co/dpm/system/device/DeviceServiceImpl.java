package kr.co.dpm.system.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;

    /* 목록 조회 */
    @Override
    public List<Device> getDevices(Device device) {
        return deviceRepository.selectAll(device);
    }

    /* 한 건 조회 */
    @Override
    public Device getDevice(Device device) {
        return deviceRepository.select(device);
    }

    /* 등록 */
    @Override
    public void registerDevice(Device device) {
        deviceRepository.insert(device);
    }

    /* 수정 */
    @Override
    public void editDevice(Device device) {
        deviceRepository.update(device);
    }
}
