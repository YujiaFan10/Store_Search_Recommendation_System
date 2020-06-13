package com.project.search.common;

public enum BusinessErrorEnum {

    //General Error : starts with 1
    OBJECT_NOT_FOUND(10001, "Object Not Found"),
    HANDLER_NOT_FOUND(10002, "No Handler Found"),
    BIND_EXCEPTION_ERROR(10003, "Servlet Binding Exception"),
    UNKNOWN_ERROR(10004, "Unknown Error"),
    PARAMETER_VALIDATION_ERROR(10005, "Not valid parameters"),

    //User Error : starts with 2
    DUPLICATE_KEY_ERROR(20001, "Account already existed"),
    LOGIN_FAIL(20002, "Wrong telephone or password"),

    //Admin Error : starts with 3
    ADMIN_SHOULD_LOGIN(30001, "Admin should login"),

    //Category Error : starts with 4
    CATEGORY_NAME_DUPLICATE(40001, "Category name already exists"),

    ;

    private Integer errCode;
    private String errMsg;

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    BusinessErrorEnum(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
