package com.mskl.common.dto;



import com.mskl.common.annotation.LoginCheck;

import java.io.Serializable;

public class LoginDto implements Serializable {
    @LoginCheck
    private String username;
    @LoginCheck
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "password='" + password + "'&username='" + username + "'";
    }
}
