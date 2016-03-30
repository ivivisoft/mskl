package com.mskl.common.dto;

public class OverseerDto {

    private String overseer;

    private String overseerMobile;

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

    @Override
    public String toString() {
        return "overseer='" + overseer + "'&overseerMobile='" + overseerMobile + "'&userMobile='" + userMobile + "'";
    }
}
