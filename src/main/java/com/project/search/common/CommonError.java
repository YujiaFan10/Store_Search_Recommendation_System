package com.project.search.common;

public class CommonError {

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

    public CommonError(Integer errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public CommonError(BusinessErrorEnum errorEnum){
        this.errCode = errorEnum.getErrCode();
        this.errMsg = errorEnum.getErrMsg();
    }

}
