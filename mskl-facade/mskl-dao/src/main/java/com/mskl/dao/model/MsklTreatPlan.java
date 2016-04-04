package com.mskl.dao.model;

import java.util.Date;

public class MsklTreatPlan {
    private Long msklTreatplanId;

    private Long msklMedicineId;

    private String medicalName;

    private String normalName;

    private String medicineUnit;

    private Integer packageAmount;

    private Long userId;

    private Double takenAmount;

    private Double dose;

    private Integer dailyTimes;

    private String morningAlarm;

    private String noonAlarm;

    private String nightAlarm;

    private Date updateDatetime;

    public Long getMsklTreatplanId() {
        return msklTreatplanId;
    }

    public void setMsklTreatplanId(Long msklTreatplanId) {
        this.msklTreatplanId = msklTreatplanId;
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

    public String getMedicineUnit() {
        return medicineUnit;
    }

    public void setMedicineUnit(String medicineUnit) {
        this.medicineUnit = medicineUnit == null ? null : medicineUnit.trim();
    }

    public Integer getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(Integer packageAmount) {
        this.packageAmount = packageAmount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getTakenAmount() {
        return takenAmount;
    }

    public void setTakenAmount(Double takenAmount) {
        this.takenAmount = takenAmount;
    }

    public Double getDose() {
        return dose;
    }

    public void setDose(Double dose) {
        this.dose = dose;
    }

    public Integer getDailyTimes() {
        return dailyTimes;
    }

    public void setDailyTimes(Integer dailyTimes) {
        this.dailyTimes = dailyTimes;
    }

    public String getMorningAlarm() {
        return morningAlarm;
    }

    public void setMorningAlarm(String morningAlarm) {
        this.morningAlarm = morningAlarm == null ? null : morningAlarm.trim();
    }

    public String getNoonAlarm() {
        return noonAlarm;
    }

    public void setNoonAlarm(String noonAlarm) {
        this.noonAlarm = noonAlarm == null ? null : noonAlarm.trim();
    }

    public String getNightAlarm() {
        return nightAlarm;
    }

    public void setNightAlarm(String nightAlarm) {
        this.nightAlarm = nightAlarm == null ? null : nightAlarm.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}