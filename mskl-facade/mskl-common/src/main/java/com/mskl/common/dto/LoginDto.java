package com.mskl.common.dto;



import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

public class LoginDto implements Serializable {
    @NotEmpty
    private String username;
    @NotEmpty
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

    @Override
    public String toString() {
        return "password='" + password + "'&username='" + username + "'";
    }
}
