package kr.co.dpm.system.access;

import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String password;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
