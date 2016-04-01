package com.mskl.dao.model;

import java.util.Date;

public class MsklTreatLog {
    private Long msklTreatlogId;

    private Long userId;

    private String userMobile;

    private Long msklMedicineId;

    private String medicalName;

    private String normalName;

    private Date setAlarm;

    private Date finishAt;

    private Integer takenStatus;

    private Integer takenMood;

    private String takenWords;

    private Double dose;

    private Date updateDatetime;

    public Long getMsklTreatlogId() {
        return msklTreatlogId;
    }

    public void setMsklTreatlogId(Long msklTreatlogId) {
        this.msklTreatlogId = msklTreatlogId;
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

    public Date getSetAlarm() {
        return setAlarm;
    }

    public void setSetAlarm(Date setAlarm) {
        this.setAlarm = setAlarm;
    }

    public Date getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(Date finishAt) {
        this.finishAt = finishAt;
    }

    public Integer getTakenStatus() {
        return takenStatus;
    }

    public void setTakenStatus(Integer takenStatus) {
        this.takenStatus = takenStatus;
    }

    public Integer getTakenMood() {
        return takenMood;
    }

    public void setTakenMood(Integer takenMood) {
        this.takenMood = takenMood;
    }

    public String getTakenWords() {
        return takenWords;
    }

    public void setTakenWords(String takenWords) {
        this.takenWords = takenWords == null ? null : takenWords.trim();
    }

    public Double getDose() {
        return dose;
    }

    public void setDose(Double dose) {
        this.dose = dose;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}