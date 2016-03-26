package com.mskl.dao.model;

import java.util.Date;

public class MsklOverseer {
    private Long id;

    private Long userId;

    private String userMobile;

    private String overseer;

    private String overseerMobile;

    private Date updateDatetime;

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

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public String getOverseer() {
        return overseer;
    }

    public void setOverseer(String overseer) {
        this.overseer = overseer == null ? null : overseer.trim();
    }

    public String getOverseerMobile() {
        return overseerMobile;
    }

    public void setOverseerMobile(String overseerMobile) {
        this.overseerMobile = overseerMobile == null ? null : overseerMobile.trim();
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}