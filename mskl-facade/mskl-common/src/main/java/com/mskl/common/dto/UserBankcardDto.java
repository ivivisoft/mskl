package com.mskl.common.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class UserBankcardDto implements Serializable{

    @NotEmpty
    private String bankNo;
    @NotEmpty
    private String bankName;
    @NotEmpty
    private String isDefault;
    @NotEmpty
    private String bankAddrNo;
    @NotEmpty
    private String cardNo;

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getBankAddrNo() {
        return bankAddrNo;
    }

    public void setBankAddrNo(String bankAddrNo) {
        this.bankAddrNo = bankAddrNo;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
