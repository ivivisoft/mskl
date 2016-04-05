package com.mskl.common.dto;


import java.io.Serializable;

public class TreatLogDto implements Serializable{


    private String msklMedicineId;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMsklMedicineId() {
        return msklMedicineId;
    }

    public void setMsklMedicineId(String msklMedicineId) {
        this.msklMedicineId = msklMedicineId;
    }
}
