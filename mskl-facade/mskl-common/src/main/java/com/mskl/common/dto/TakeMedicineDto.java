package com.mskl.common.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class TakeMedicineDto implements Serializable{

    @NotEmpty
    private String MsklTreatlogId;

    public String getMsklTreatlogId() {
        return MsklTreatlogId;
    }

    public void setMsklTreatlogId(String msklTreatlogId) {
        MsklTreatlogId = msklTreatlogId;
    }
}
