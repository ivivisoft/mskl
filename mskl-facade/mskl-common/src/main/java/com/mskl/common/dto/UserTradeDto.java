package com.mskl.common.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class UserTradeDto implements Serializable{

    @NotEmpty
    private String userTradePwd;
    @NotEmpty
    private String newUserTradePwd;
    @NotEmpty
    private String userTradePwdStrength;

    public String getUserTradePwd() {
        return userTradePwd;
    }

    public void setUserTradePwd(String userTradePwd) {
        this.userTradePwd = userTradePwd;
    }

    public String getUserTradePwdStrength() {
        return userTradePwdStrength;
    }

    public void setUserTradePwdStrength(String userTradePwdStrength) {
        this.userTradePwdStrength = userTradePwdStrength;
    }

    public String getNewUserTradePwd() {
        return newUserTradePwd;
    }

    public void setNewUserTradePwd(String newUserTradePwd) {
        this.newUserTradePwd = newUserTradePwd;
    }
}
