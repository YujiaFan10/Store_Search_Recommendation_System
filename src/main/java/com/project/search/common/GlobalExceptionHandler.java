package com.project.search.common;

import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonRes doError(HttpServletRequest request, HttpServletResponse response, Exception e){
        if(e instanceof BusinessException){       //Business Exception: No object found
            return CommonRes.create(((BusinessException)e).getCommonError(), "fail");
        } else if(e instanceof NoHandlerFoundException){  //wrong url
            CommonError error = new CommonError(BusinessErrorEnum.HANDLER_NOT_FOUND);
            return CommonRes.create(error, "fail");
        } else if(e instanceof ServletRequestBindingException){  //wrong parameters in url
            CommonError error = new CommonError(BusinessErrorEnum.BIND_EXCEPTION_ERROR);
            return CommonRes.create(error, "fail");
        } else{
            CommonError error = new CommonError(BusinessErrorEnum.UNKNOWN_ERROR);
            return CommonRes.create(error, "fail");
        }
    }

}
