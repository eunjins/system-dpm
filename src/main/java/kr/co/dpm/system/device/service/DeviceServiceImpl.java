package kr.co.dpm.system.device.service;

import kr.co.dpm.system.model.Device;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService {
    // 디바이스 목록 조회 (검색)
    @Override
    public List<Device> getDevices(Device device) {
        return null;
    }
    // 디바이스 조회 (상세 조회)
    @Override
    public Device getDevice(Device device) {
        return null;
    }
    // 디바이스 등록
    @Override
    public void registerDevice(Device device) {
    }
    // 디바이스 수정
    @Override
    public void editDevice(Device device) {
    }
    // 비밀번호 생성
    @Override
    public String createPassword(String id) {
        StringBuffer password = new StringBuffer();

        for (int i = 0; i < id.length(); i++) {
            int oneLetter = id.charAt(i) + i;

            password.append((char) oneLetter);
        }

        return password.toString();
    }
}
