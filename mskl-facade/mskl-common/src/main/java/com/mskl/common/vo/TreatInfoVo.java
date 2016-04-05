package com.mskl.common.vo;

import java.io.Serializable;

public class TreatInfoVo implements Serializable{

    private String treatDate;

    private String isLost;

    public String getTreatDate() {
        return treatDate;
    }

    public void setTreatDate(String treatDate) {
        this.treatDate = treatDate;
    }

    public String getIsLost() {
        return isLost;
    }

    public void setIsLost(String isLost) {
        this.isLost = isLost;
    }
}
