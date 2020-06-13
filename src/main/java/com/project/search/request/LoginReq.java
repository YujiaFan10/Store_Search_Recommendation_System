package com.project.search.request;

import javax.validation.constraints.NotBlank;

public class LoginReq {

    @NotBlank(message = "telephone cannot be null")
    private String telephone;

    @NotBlank(message = "password cannot be null")
    private String password;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
