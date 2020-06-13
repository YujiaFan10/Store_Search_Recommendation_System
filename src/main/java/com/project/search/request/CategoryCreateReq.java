package com.project.search.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CategoryCreateReq {

    @NotBlank(message = "Category Name cannot be null")
    private String name;

    @NotBlank(message = "Category iconUrl cannot be null")
    private String iconUrl;

    @NotNull(message = "Category sort weight cannot be null")
    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
