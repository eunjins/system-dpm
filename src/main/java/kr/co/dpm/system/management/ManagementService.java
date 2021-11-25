package kr.co.dpm.system.management;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.script.Script;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public interface ManagementService {
    public void receiveDevice(Device device);

    public String encryption(String id);

    public boolean distributeScript(Script script);

    public boolean receiveMeasure(Measure measure);
}
