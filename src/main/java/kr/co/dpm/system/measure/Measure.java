package kr.co.dpm.system.measure;

import java.io.Serializable;

public class Measure implements Serializable {
    private int no;
    private String name;
    private String deviceId;
    private int scriptNo;
    private int execTime;
    private String status;
    private String deviceId;

    public Measure() {
    }

    public Measure(String name, String deviceId, int scriptNo, int execTime, String status) {
        this.name = name;
        this.deviceId = deviceId;
        this.scriptNo = scriptNo;
        this.execTime = execTime;
        this.status = status;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getScriptNo() {
        return scriptNo;
    }

    public void setScriptNo(int scriptNo) {
        this.scriptNo = scriptNo;
    }

    public int getExecTime() {
        return execTime;
    }

    public void setExecTime(int execTime) {
        this.execTime = execTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
}
