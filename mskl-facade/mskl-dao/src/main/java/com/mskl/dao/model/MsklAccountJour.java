package com.mskl.dao.model;

import java.util.Date;

public class MsklAccountJour {
    private Long id;

    private Long msklAccountId;

    private Long userId;

    private Date transDatetime;

    private String accountBizType;

    private Long transAmount;

    private Long preAmount;

    private Long postAmount;

    private String seqFlag;

    private String refSerialNo;

    private String remark;

    private String workDate;

    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getTransDatetime() {
        return transDatetime;
    }

    public void setTransDatetime(Date transDatetime) {
        this.transDatetime = transDatetime;
    }

    public String getAccountBizType() {
        return accountBizType;
    }

    public void setAccountBizType(String accountBizType) {
        this.accountBizType = accountBizType == null ? null : accountBizType.trim();
    }

    public Long getTransAmount() {
        return transAmount;
    }

    public void setTransAmount(Long transAmount) {
        this.transAmount = transAmount;
    }

    public Long getPreAmount() {
        return preAmount;
    }

    public void setPreAmount(Long preAmount) {
        this.preAmount = preAmount;
    }

    public Long getPostAmount() {
        return postAmount;
    }

    public void setPostAmount(Long postAmount) {
        this.postAmount = postAmount;
    }

    public String getSeqFlag() {
        return seqFlag;
    }

    public void setSeqFlag(String seqFlag) {
        this.seqFlag = seqFlag == null ? null : seqFlag.trim();
    }

    public String getRefSerialNo() {
        return refSerialNo;
    }

    public void setRefSerialNo(String refSerialNo) {
        this.refSerialNo = refSerialNo == null ? null : refSerialNo.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getWorkDate() {
        return workDate;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate == null ? null : workDate.trim();
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}