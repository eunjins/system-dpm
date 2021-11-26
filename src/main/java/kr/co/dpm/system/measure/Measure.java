package kr.co.dpm.system.measure;

import java.io.Serializable;

public class Measure implements Serializable {
    private int no;
    private String name;
    private String id;
    private int scriptNo;
    private int execTime;
    private String status;
    private String deviceId;

    public Measure() {
    }

    public Measure(String name, String id, int scriptNo, int execTime, String status) {
        this.name = name;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
