package com.project.search.common;


import com.project.search.controller.admin.AdminController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Aspect
@Configuration
public class ControllerAspect {

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private HttpServletResponse httpServletResponse;

    @Around("execution(* com.project.search.controller.admin.*.*(..)) && " +
            "@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
        AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);
        if(adminPermission == null){
            //No Annotation: public method, don't need to login
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
        //Check if admin login or not
        String email = (String) httpServletRequest.getSession().getAttribute(AdminController.CURRENT_ADMIN_SESSION);
        if(email == null){
            if(adminPermission.produceType().equals("text/html")) {
                httpServletResponse.sendRedirect("/admin/admin/loginpage");
                return null;
            } else {
                CommonError commonError = new CommonError(BusinessErrorEnum.ADMIN_SHOULD_LOGIN);
                return CommonRes.create(commonError, "fail");
            }
        } else{
            //already login
            Object resultObject = joinPoint.proceed();
            return resultObject;
        }
    }
}
