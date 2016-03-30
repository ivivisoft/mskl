package com.mskl.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class MsklMedbox {
    private Long id;

    private Long userId;

    private String userMobile;

    private String userRealName;

    private Long msklMedicineId;

    private String medicalName;

    private String normalName;

    private BigDecimal totalAmount;

    private BigDecimal takenAmount;

    private BigDecimal remainingAmount;

    private BigDecimal dose;

    private Short dailyTimes;

    private Integer startDay;

    private Integer finishDay;

    private Date updateDatetime;

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

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public String getUserRealName() {
        return userRealName;
    }

    public void setUserRealName(String userRealName) {
        this.userRealName = userRealName == null ? null : userRealName.trim();
    }

    public Long getMsklMedicineId() {
        return msklMedicineId;
    }

    public void setMsklMedicineId(Long msklMedicineId) {
        this.msklMedicineId = msklMedicineId;
    }

    public String getMedicalName() {
        return medicalName;
    }

    public void setMedicalName(String medicalName) {
        this.medicalName = medicalName == null ? null : medicalName.trim();
    }

    public String getNormalName() {
        return normalName;
    }

    public void setNormalName(String normalName) {
        this.normalName = normalName == null ? null : normalName.trim();
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getTakenAmount() {
        return takenAmount;
    }

    public void setTakenAmount(BigDecimal takenAmount) {
        this.takenAmount = takenAmount;
    }

    public BigDecimal getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(BigDecimal remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public BigDecimal getDose() {
        return dose;
    }

    public void setDose(BigDecimal dose) {
        this.dose = dose;
    }

    public Short getDailyTimes() {
        return dailyTimes;
    }

    public void setDailyTimes(Short dailyTimes) {
        this.dailyTimes = dailyTimes;
    }

    public Integer getStartDay() {
        return startDay;
    }

    public void setStartDay(Integer startDay) {
        this.startDay = startDay;
    }

    public Integer getFinishDay() {
        return finishDay;
    }

    public void setFinishDay(Integer finishDay) {
        this.finishDay = finishDay;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}