package com.project.search.request;

import javax.validation.constraints.NotBlank;

public class SellerCreateReq {

    @NotBlank(message = "Seller name cannot be null")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
