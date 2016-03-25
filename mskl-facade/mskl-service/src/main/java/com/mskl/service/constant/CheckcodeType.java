package com.mskl.service.constant;

public enum CheckcodeType {
    REGISTER("1", "注册"),
    GETLOGINPSW("2","找回登陆密码"),
    MODIFYMOBILE("3","修改手机号码"),
    SETTRADEPSW("4","设置交易密码"),
    FINDTRADEPSW("5","找回交易密码"),
    RESETSECRETPSW("6","重置密保问题"),
    TAKESPRESENTLY("7","取现");


    private String code;

    private String desc;

    CheckcodeType(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    }
