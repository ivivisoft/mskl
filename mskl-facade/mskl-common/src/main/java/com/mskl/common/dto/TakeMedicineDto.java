package com.mskl.common.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class TakeMedicineDto implements Serializable{

    @NotEmpty
    private String msklTreatlogId;

    public String getMsklTreatlogId() {
        return msklTreatlogId;
    }

    public void setMsklTreatlogId(String msklTreatlogId) {
        this.msklTreatlogId = msklTreatlogId;
    }
}
