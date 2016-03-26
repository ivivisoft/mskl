package com.mskl.dao.model;

public class MsklUserTrade {
    private Long id;

    private Long userId;

    private Integer errorCount;

    private String userTradePwd;

    private String userTradePwdStrength;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public String getUserTradePwd() {
        return userTradePwd;
    }

    public void setUserTradePwd(String userTradePwd) {
        this.userTradePwd = userTradePwd == null ? null : userTradePwd.trim();
    }

    public String getUserTradePwdStrength() {
        return userTradePwdStrength;
    }

    public void setUserTradePwdStrength(String userTradePwdStrength) {
        this.userTradePwdStrength = userTradePwdStrength == null ? null : userTradePwdStrength.trim();
    }
}