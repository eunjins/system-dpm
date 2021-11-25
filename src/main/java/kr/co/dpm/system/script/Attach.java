package kr.co.dpm.system.script;

import java.io.Serializable;

public class Attach implements Serializable {
    private int no;
    private String division;
    private String name;
    private String physicName;
    private String scriptNO;

    public Attach() {
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhysicName() {
        return physicName;
    }

    public void setPhysicName(String physicName) {
        this.physicName = physicName;
    }

    public String getScriptNO() {
        return scriptNO;
    }

    public void setScriptNO(String scriptNO) {
        this.scriptNO = scriptNO;
    }
}
