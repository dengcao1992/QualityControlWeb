package com.dcs.web.service;

import com.dcs.web.exception.UserException;
import com.dcs.web.pojo.po.UserPO;
import javax.servlet.http.HttpServletRequest;


public interface UserServices {
    void addUser(UserPO user) throws UserException;
    UserPO getUser(Long uid) throws UserException;
    UserPO getUser(String username) throws UserException;
    void setUser(UserPO user) throws UserException;
    void login(String username, String password, HttpServletRequest request) throws UserException;
}
