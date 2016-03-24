package com.mskl.dao.model;

import java.io.Serializable;
import java.util.Date;

public class MsklUser  implements Serializable{
    private Long userId;

    private String userName;

    private String userRealName;

    private String userNickName;

    private Short userGrade;

    private Integer userScore;

    private String userPwd;

    private String userPwdStrength;

    private String email;

    private String mobile;

    private String idcardKind;

    private String idcard;

    private String userAddress;

    private Date registerDatetime;

    private String registerIp;

    private Date lastLoginDatetime;

    private String lastLoginIp;

    private Integer loginCount;

    private String userStatus;

    private Integer errorCount;

    private String isTrust;

    private String userInit;

    private String userKind;

    private String salesChannel;

    private Long refUserId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName == null ? null : userRealName.trim();
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName == null ? null : userNickName.trim();
    }

    public Short getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(Short userGrade) {
        this.userGrade = userGrade;
    }

    public Integer getUserScore() {
        return userScore;
    }

    public void setUserScore(Integer userScore) {
        this.userScore = userScore;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd == null ? null : userPwd.trim();
    }

    public String getUserPwdStrength() {
        return userPwdStrength;
    }

    public void setUserPwdStrength(String userPwdStrength) {
        this.userPwdStrength = userPwdStrength == null ? null : userPwdStrength.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getIdcardKind() {
        return idcardKind;
    }

    public void setIdcardKind(String idcardKind) {
        this.idcardKind = idcardKind == null ? null : idcardKind.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress == null ? null : userAddress.trim();
    }

    public Date getRegisterDatetime() {
        return registerDatetime;
    }

    public void setRegisterDatetime(Date registerDatetime) {
        this.registerDatetime = registerDatetime;
    }

    public String getRegisterIp() {
        return registerIp;
    }

    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp == null ? null : registerIp.trim();
    }

    public Date getLastLoginDatetime() {
        return lastLoginDatetime;
    }

    public void setLastLoginDatetime(Date lastLoginDatetime) {
        this.lastLoginDatetime = lastLoginDatetime;
    }

    public String getLastLoginIp() {
        return lastLoginIp;
    }

    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    public Integer getLoginCount() {
        return loginCount;
    }

    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus == null ? null : userStatus.trim();
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public String getIsTrust() {
        return isTrust;
    }

    public void setIsTrust(String isTrust) {
        this.isTrust = isTrust == null ? null : isTrust.trim();
    }

    public String getUserInit() {
        return userInit;
    }

    public void setUserInit(String userInit) {
        this.userInit = userInit == null ? null : userInit.trim();
    }

    public String getUserKind() {
        return userKind;
    }

    public void setUserKind(String userKind) {
        this.userKind = userKind == null ? null : userKind.trim();
    }

    public String getSalesChannel() {
        return salesChannel;
    }

    public void setSalesChannel(String salesChannel) {
        this.salesChannel = salesChannel == null ? null : salesChannel.trim();
    }

    public Long getRefUserId() {
        return refUserId;
    }

    public void setRefUserId(Long refUserId) {
        this.refUserId = refUserId;
    }
}