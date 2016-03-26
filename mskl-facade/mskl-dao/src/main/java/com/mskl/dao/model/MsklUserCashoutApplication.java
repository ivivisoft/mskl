package com.mskl.dao.model;

import java.util.Date;

public class MsklUserCashoutApplication {
    private Long id;

    private Long userId;

    private String bankNo;

    private String bankName;

    private Long amount;

    private String bankAddrNo;

    private Date applicationDatetime;

    private String reviewStatus;

    private Date payDatetime;

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

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getBankAddrNo() {
        return bankAddrNo;
    }

    public void setBankAddrNo(String bankAddrNo) {
        this.bankAddrNo = bankAddrNo == null ? null : bankAddrNo.trim();
    }

    public Date getApplicationDatetime() {
        return applicationDatetime;
    }

    public void setApplicationDatetime(Date applicationDatetime) {
        this.applicationDatetime = applicationDatetime;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus == null ? null : reviewStatus.trim();
    }

    public Date getPayDatetime() {
        return payDatetime;
    }

    public void setPayDatetime(Date payDatetime) {
        this.payDatetime = payDatetime;
    }
}