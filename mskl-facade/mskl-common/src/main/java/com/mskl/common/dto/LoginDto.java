package com.mskl.common.dto;



import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class LoginDto implements Serializable {
    @NotEmpty(message = "用户名或者密码错误!")
    private String username;
    @NotEmpty(message = "用户名或者密码错误!")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
