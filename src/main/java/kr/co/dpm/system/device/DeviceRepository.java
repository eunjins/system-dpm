package kr.co.dpm.system.device;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DeviceRepository {
    public List<Device> selectAll(Map<String, Object> condition);

    public Device select(Device device);

    public void insert(Device device);

    public void update(Device device);
}
