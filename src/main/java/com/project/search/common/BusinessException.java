package com.project.search.common;

public class BusinessException extends Exception {

    private CommonError commonError;

    public BusinessException(BusinessErrorEnum errorEnum){
        super();
        this.commonError = new CommonError(errorEnum);
    }

    public BusinessException(BusinessErrorEnum errorEnum, String message){
        super();
        this.commonError = new CommonError(errorEnum);
        this.commonError.setErrMsg(message);

    }

    public CommonError getCommonError() {
        return commonError;
    }

    public void setCommonError(CommonError commonError) {
        this.commonError = commonError;
    }
}
