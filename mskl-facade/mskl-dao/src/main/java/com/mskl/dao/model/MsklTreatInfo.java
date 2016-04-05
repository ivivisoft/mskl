package com.mskl.dao.model;

import java.util.Date;

public class MsklTreatInfo {
    private Long id;

    private Long userId;

    private Long medicineId;

    private Date treatDate;

    private Integer dailyTimes;

    private Integer takenTimes;

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

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public Date getTreatDate() {
        return treatDate;
    }

    public void setTreatDate(Date treatDate) {
        this.treatDate = treatDate;
    }

    public Integer getDailyTimes() {
        return dailyTimes;
    }

    public void setDailyTimes(Integer dailyTimes) {
        this.dailyTimes = dailyTimes;
    }

    public Integer getTakenTimes() {
        return takenTimes;
    }

    public void setTakenTimes(Integer takenTimes) {
        this.takenTimes = takenTimes;
    }
}