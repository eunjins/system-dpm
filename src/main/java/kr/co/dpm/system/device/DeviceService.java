package kr.co.dpm.system.device;

import kr.co.dpm.system.device.Device;

import java.util.List;

public interface DeviceService {
    public List<Device> getDevices(Device device);

    public Device getDevice(Device device);

    public void registerDevice(Device device);

    public void editDevice(Device device);
}
