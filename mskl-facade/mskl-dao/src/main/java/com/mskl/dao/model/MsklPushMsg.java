package com.mskl.dao.model;

import java.util.Date;

public class MsklPushMsg {
    private Long pushMsgId;

    private String pushModel;

    private String pushType;

    private String pushMsgKind;

    private String pushMsgTitle;

    private String pushMsgDigest;

    private String pushExtend;

    private Long msgFrom;

    private String recvUserId;

    private String htmlUrl;

    private Date createDatetime;

    private String isRead;

    private String businessType;

    public Long getPushMsgId() {
        return pushMsgId;
    }

    public void setPushMsgId(Long pushMsgId) {
        this.pushMsgId = pushMsgId;
    }

    public String getPushModel() {
        return pushModel;
    }

    public void setPushModel(String pushModel) {
        this.pushModel = pushModel == null ? null : pushModel.trim();
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType == null ? null : pushType.trim();
    }

    public String getPushMsgKind() {
        return pushMsgKind;
    }

    public void setPushMsgKind(String pushMsgKind) {
        this.pushMsgKind = pushMsgKind == null ? null : pushMsgKind.trim();
    }

    public String getPushMsgTitle() {
        return pushMsgTitle;
    }

    public void setPushMsgTitle(String pushMsgTitle) {
        this.pushMsgTitle = pushMsgTitle == null ? null : pushMsgTitle.trim();
    }

    public String getPushMsgDigest() {
        return pushMsgDigest;
    }

    public void setPushMsgDigest(String pushMsgDigest) {
        this.pushMsgDigest = pushMsgDigest == null ? null : pushMsgDigest.trim();
    }

    public String getPushExtend() {
        return pushExtend;
    }

    public void setPushExtend(String pushExtend) {
        this.pushExtend = pushExtend == null ? null : pushExtend.trim();
    }

    public Long getMsgFrom() {
        return msgFrom;
    }

    public void setMsgFrom(Long msgFrom) {
        this.msgFrom = msgFrom;
    }

    public String getRecvUserId() {
        return recvUserId;
    }

    public void setRecvUserId(String recvUserId) {
        this.recvUserId = recvUserId == null ? null : recvUserId.trim();
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl == null ? null : htmlUrl.trim();
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead == null ? null : isRead.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }
}