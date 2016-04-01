package com.mskl.common.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class TreatPlanDto implements Serializable {

    /**
     * 药品id
     */
    @NotEmpty
    private String msklMedicineId;
    /**
     * 每天服用次数
     */
    @NotEmpty
    private String dailyTimes;
    /**
     * 每次服用剂量
     */
    @NotEmpty
    private String dose;
    /**
     * 上午提醒时间
     */
    private String morningAlarm;
    /**
     * 晚上提醒时间
     */
    private String nightAlarm;
    /**
     * 中午提醒时间
     */
    private String noonAlarm;
    /**
     * 已经服用
     */
    @NotEmpty
    private String takenAmount;
    /**
     * 单盒药量
     */
    @NotEmpty
    private String packageAmount;

    public String getMsklMedicineId() {
        return msklMedicineId;
    }

    public void setMsklMedicineId(String msklMedicineId) {
        this.msklMedicineId = msklMedicineId;
    }

    public String getDailyTimes() {
        return dailyTimes;
    }

    public void setDailyTimes(String dailyTimes) {
        this.dailyTimes = dailyTimes;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getMorningAlarm() {
        return morningAlarm;
    }

    public void setMorningAlarm(String morningAlarm) {
        this.morningAlarm = morningAlarm;
    }

    public String getNightAlarm() {
        return nightAlarm;
    }

    public void setNightAlarm(String nightAlarm) {
        this.nightAlarm = nightAlarm;
    }

    public String getNoonAlarm() {
        return noonAlarm;
    }

    public void setNoonAlarm(String noonAlarm) {
        this.noonAlarm = noonAlarm;
    }

    public String getTakenAmount() {
        return takenAmount;
    }

    public void setTakenAmount(String takenAmount) {
        this.takenAmount = takenAmount;
    }

    public String getPackageAmount() {
        return packageAmount;
    }

    public void setPackageAmount(String packageAmount) {
        this.packageAmount = packageAmount;
    }

}
