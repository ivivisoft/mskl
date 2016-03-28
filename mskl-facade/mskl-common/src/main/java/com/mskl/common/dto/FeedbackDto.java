package com.mskl.common.dto;

import java.io.Serializable;
import java.util.Date;

public class FeedbackDto implements Serializable {

    private String feedbackMsg;

    private String userMobile;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getFeedbackMsg() {
        return feedbackMsg;
    }

    public void setFeedbackMsg(String feedbackMsg) {
        this.feedbackMsg = feedbackMsg;
    }

    @Override
    public String toString() {
        return "feedbackMsg='" + feedbackMsg + "'&userMobile='" + userMobile + "'&userName='" + userName + "'";
    }


}
