package com.project.search.controller;

import com.project.search.common.BusinessErrorEnum;
import com.project.search.common.BusinessException;
import com.project.search.common.CommonRes;
import com.project.search.common.CommonUtil;
import com.project.search.model.UserModel;
import com.project.search.request.LoginReq;
import com.project.search.request.RegisterReq;
import com.project.search.service.UserService;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Controller("/user")
@RequestMapping("/user")
public class UserController {

    private final String CURRENT_USER_SESSION = "currentUserSession";

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        return "hello";
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        String s = "yujia";
        ModelAndView modelAndView = new ModelAndView("/index.html");
        modelAndView.addObject("name", s);
        return modelAndView;
    }


    @RequestMapping("/get")
    @ResponseBody
    public CommonRes getUser(@RequestParam(name="id")Integer id) throws BusinessException{
        UserModel userModel = userService.getUser(id);
        if(userModel == null){
            throw new BusinessException(BusinessErrorEnum.OBJECT_NOT_FOUND);
        } else {
            return CommonRes.create(userModel);
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public CommonRes register(@Valid @RequestBody RegisterReq registerReq, BindingResult bindingResult)
            throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }

        UserModel registerUser = new UserModel();
        registerUser.setTelephone(registerReq.getTelephone());
        registerUser.setPassword(registerReq.getPassword());
        registerUser.setNickName(registerReq.getNickName());
        registerUser.setGender(registerReq.getGender());

        UserModel responseUser = userService.register(registerUser);
        return CommonRes.create(responseUser);
    }

    @RequestMapping("/login")
    @ResponseBody
    public CommonRes login(@Valid @RequestBody LoginReq loginReq, BindingResult bindingResult)
            throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(BusinessErrorEnum.PARAMETER_VALIDATION_ERROR,
                    CommonUtil.processErrorString(bindingResult));
        }
        UserModel userModel = userService.login(loginReq.getTelephone(), loginReq.getPassword());
        httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION, userModel);
        return CommonRes.create(userModel);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public CommonRes logout(){
        httpServletRequest.getSession().invalidate();
        return CommonRes.create(null);
    }

    @RequestMapping("/getcurrentuser")
    @ResponseBody
    public CommonRes getcurrentuser(){
        UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
        return CommonRes.create(userModel);
    }

}
