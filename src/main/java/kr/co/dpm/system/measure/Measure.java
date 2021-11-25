package kr.co.dpm.system.measure;

import java.io.Serializable;

public class Measure implements Serializable {
    private int no;
    private String name;
    private String deviceId;
    private int scriptNo;
    private String execTime;
    private String status;

    public Measure() {
    }

    public Measure(int no, String name, String deviceId, int scriptNo, String execTime, String status) {
        this.no = no;
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

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getScriptNo() {
        return scriptNo;
    }

    public void setScriptNo(int scriptNo) {
        this.scriptNo = scriptNo;
    }

    public String getExecTime() {
        return execTime;
    }

    public void setExecTime(String execTime) {
        this.execTime = execTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
