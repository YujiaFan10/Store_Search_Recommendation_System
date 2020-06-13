package com.project.search.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RegisterReq {

    @NotBlank(message = "telephone cannot be null")
    private String telephone;

    @NotBlank(message = "password cannot be null")
    private String password;

    @NotBlank(message = "nickName cannot be null")
    private String nickName;

    @NotNull(message = "gender cannot be null")
    private Integer gender;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
