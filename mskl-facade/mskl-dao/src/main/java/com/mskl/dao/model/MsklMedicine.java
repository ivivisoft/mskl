package com.mskl.dao.model;

import java.math.BigDecimal;
import java.util.Date;

public class MsklMedicine {
    private Long msklMedicineId;

    private String medicalName;

    private String normalName;

    private String manufacturer;

    private String medicineUnit;

    private BigDecimal packageAmount;

    private BigDecimal dose;

    private Short dailyTimes;

    private String medCode;

    private String barCode;

    private Date updateDatetime;

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer == null ? null : manufacturer.trim();
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

    public String getMedCode() {
        return medCode;
    }

    public void setMedCode(String medCode) {
        this.medCode = medCode == null ? null : medCode.trim();
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode == null ? null : barCode.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}