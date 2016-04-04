package com.mskl.common.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class TakeMedicineDto implements Serializable {

    @NotEmpty
    private String msklTreatlogId;

    private String takenMood;

    private String takenWords;


    public String getTakenMood() {
        return takenMood;
    }

    public void setTakenMood(String takenMood) {
        this.takenMood = takenMood;
    }

    public String getTakenWords() {
        return takenWords;
    }

    public void setTakenWords(String takenWords) {
        this.takenWords = takenWords;
    }

    public String getMsklTreatlogId() {
        return msklTreatlogId;
    }

    public void setMsklTreatlogId(String msklTreatlogId) {
        this.msklTreatlogId = msklTreatlogId;
    }
}
