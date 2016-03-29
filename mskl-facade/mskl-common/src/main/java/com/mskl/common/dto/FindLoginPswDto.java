package com.mskl.common.dto;


import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class FindLoginPswDto implements Serializable {
    @NotEmpty
    private String mobile;
    @NotEmpty
    private String verificationCode;
    @NotEmpty
    private String newPassword;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
