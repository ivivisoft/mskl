package com.mskl.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class MsklTreatPlan {
    private Long msklTreatplanId;

    private Long msklMedicineId;

    private String medicalName;

    private String normalName;

    private String medicineUnit;

    private BigDecimal packageAmount;

    private BigDecimal takenAmount;

    private BigDecimal dose;

    private Short dailyTimes;

    private Date morningAlarm;

    private Date noonAlarm;

    private Date nightAlarm;

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

    public BigDecimal getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(BigDecimal packageAmount) {
        this.packageAmount = packageAmount;
    }

    public BigDecimal getTakenAmount() {
        return takenAmount;
    }

    public void setTakenAmount(BigDecimal takenAmount) {
        this.takenAmount = takenAmount;
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

    public Date getMorningAlarm() {
        return morningAlarm;
    }

    public void setMorningAlarm(Date morningAlarm) {
        this.morningAlarm = morningAlarm;
    }

    public Date getNoonAlarm() {
        return noonAlarm;
    }

    public void setNoonAlarm(Date noonAlarm) {
        this.noonAlarm = noonAlarm;
    }

    public Date getNightAlarm() {
        return nightAlarm;
    }

    public void setNightAlarm(Date nightAlarm) {
        this.nightAlarm = nightAlarm;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}