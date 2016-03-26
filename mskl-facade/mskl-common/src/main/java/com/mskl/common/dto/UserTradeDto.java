package com.mskl.common.dto;

public class UserTradeDto {

    private String userId;

    private String userTradePwd;

    private String newUserTradePwd;

    private String userTradePwdStrength;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
