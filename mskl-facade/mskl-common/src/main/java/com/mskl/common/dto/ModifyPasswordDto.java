package com.mskl.common.dto;

import java.io.Serializable;

public class ModifyPasswordDto implements Serializable {

    private String userName;

    private String password;

    private String newPassword;

    private String userPwdStrength;

    public String getUserPwdStrength() {
        return userPwdStrength;
    }

    public void setUserPwdStrength(String userPwdStrength) {
        this.userPwdStrength = userPwdStrength;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
