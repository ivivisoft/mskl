package com.mskl.dao.model;

import java.util.Date;

public class MsklPromotionInfo {
    private Long id;

    private String promotionName;

    private String infoChannel;

    private String infoTitle;

    private String bannerUrl;

    private String contentUrl;

    private Short sort;

    private Date createDatetime;

    private String infoContent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName == null ? null : promotionName.trim();
    }

    public String getInfoChannel() {
        return infoChannel;
    }

    public void setInfoChannel(String infoChannel) {
        this.infoChannel = infoChannel == null ? null : infoChannel.trim();
    }

    public String getInfoTitle() {
        return infoTitle;
    }

    public void setInfoTitle(String infoTitle) {
        this.infoTitle = infoTitle == null ? null : infoTitle.trim();
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl == null ? null : contentUrl.trim();
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    public String getInfoContent() {
        return infoContent;
    }

    public void setInfoContent(String infoContent) {
        this.infoContent = infoContent == null ? null : infoContent.trim();
    }
}