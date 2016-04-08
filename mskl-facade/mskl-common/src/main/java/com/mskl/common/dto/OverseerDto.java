package com.mskl.common.dto;

import org.hibernate.validator.constraints.NotEmpty;

public class OverseerDto {

    @NotEmpty
    private String overseer;
    @NotEmpty
    private String overseerMobile;
    @NotEmpty
    private String userMobile;


    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getOverseer() {
        return overseer;
    }

    public void setOverseer(String overseer) {
        this.overseer = overseer;
    }

    public String getOverseerMobile() {
        return overseerMobile;
    }

    public void setOverseerMobile(String overseerMobile) {
        this.overseerMobile = overseerMobile;
    }

}
