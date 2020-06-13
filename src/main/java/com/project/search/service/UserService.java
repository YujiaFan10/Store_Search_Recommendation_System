package com.project.search.service;

import com.project.search.common.BusinessException;
import com.project.search.model.UserModel;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface UserService {

    UserModel getUser(Integer id);

    UserModel register(UserModel registerUser) throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException;

    UserModel login(String telephone, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException, BusinessException;

    Integer countAllUser();
}
