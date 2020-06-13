package com.project.search.impl;

import com.project.search.common.BusinessErrorEnum;
import com.project.search.common.BusinessException;
import com.project.search.common.CommonRes;
import com.project.search.dao.UserModelMapper;
import com.project.search.model.UserModel;
import com.project.search.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserModelMapper userModelMapper;

    @Override
    public UserModel getUser(Integer id) {
        return userModelMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional
    public UserModel register(UserModel registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {
        //encode password by Md5
        registerUser.setPassword(encodeByMd5(registerUser.getPassword()));
        registerUser.setCreatedAt(new Date());
        registerUser.setUpdatedAt(new Date());

        //Unique Index
        try{
            userModelMapper.insertSelective(registerUser);
        } catch (DuplicateKeyException e){
            throw new BusinessException(BusinessErrorEnum.DUPLICATE_KEY_ERROR);
        }

        return getUser(registerUser.getId());
    }

    private String encodeByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        //operator
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return base64Encoder.encode(messageDigest.digest(str.getBytes("utf-8")));
    }

    @Override
    public UserModel login(String telephone, String password)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException {
        UserModel userModel = userModelMapper.selectByTelephoneAndPassword(telephone, encodeByMd5(password));
        if(userModel == null){
            throw new BusinessException(BusinessErrorEnum.LOGIN_FAIL);
        }
        return userModel;
    }

    @Override
    public Integer countAllUser() {
        return userModelMapper.countAllUser();
    }

}
