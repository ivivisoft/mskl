package com.mskl.dao.model;

import java.util.Date;

public class MsklAccount {
    private Long msklAccountId;

    private Long userId;

    private String userRealName;

    private String currencyType;

    private Long avalaibleAmount;

    private Long freezeAmount;

    private String accountStatus;

    private String md5Code;

    private String remark;

    private Date createDatetime;

    private Date updateDatetime;

    private Long version;

    private String hash;

    private String accountStatusReason;

    public Long getMsklAccountId() {
        return msklAccountId;
    }

    public void setMsklAccountId(Long msklAccountId) {
        this.msklAccountId = msklAccountId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName == null ? null : userRealName.trim();
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType == null ? null : currencyType.trim();
    }

    public Long getAvalaibleAmount() {
        return avalaibleAmount;
    }

    public void setAvalaibleAmount(Long avalaibleAmount) {
        this.avalaibleAmount = avalaibleAmount;
    }

    public Long getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(Long freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus == null ? null : accountStatus.trim();
    }

    public String getMd5Code() {
        return md5Code;
    }

    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code == null ? null : md5Code.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    public String getAccountStatusReason() {
        return accountStatusReason;
    }

    public void setAccountStatusReason(String accountStatusReason) {
        this.accountStatusReason = accountStatusReason == null ? null : accountStatusReason.trim();
    }
}