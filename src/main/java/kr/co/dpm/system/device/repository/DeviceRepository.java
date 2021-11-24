package kr.co.dpm.system.device.repository;

import kr.co.dpm.system.model.Device;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeviceRepository {
    public List<Device> selectAll(Device device);

    public Device select(Device device);

    public void insert(Device device);

    public void update(Device device);
}
