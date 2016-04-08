package com.mskl.common.dto;


import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class TreatLogDto implements Serializable{

    @NotEmpty
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
