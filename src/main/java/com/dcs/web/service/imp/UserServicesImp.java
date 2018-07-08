package com.dcs.web.service.imp;

import com.dcs.web.constant.WebConstant;
import com.dcs.web.dao.UserDAO;
import com.dcs.web.exception.UserException;
import com.dcs.web.pojo.po.UserPO;
import com.dcs.web.service.UserServices;
import com.dcs.web.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

@Service
public class UserServicesImp implements UserServices {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void addUser(UserPO user) throws UserException {
        UserPO oldUser = userDAO.getByUsername(user.getUsername());
        if (oldUser != null){
            throw new UserException("用户名已存在");
        }
        userDAO.saveAndFlush(user);
    }

    @Override
    public UserPO getUser(Long uid) throws UserException {
        UserPO user = userDAO.getOne(uid);
        if (user == null){
            throw new UserException("用户不存在");
        }
        return user;
    }

    @Override
    public UserPO getUser(String username) throws UserException {
        UserPO user = userDAO.getByUsername(username);
        if (user == null){
            throw new UserException("用户不存在");
        }
        return user;
    }

    @Override
    public void setUser(UserPO user) throws UserException {
        UserPO oldUser = userDAO.getByUsername(user.getUsername());
        if (oldUser == null){
            throw new UserException("用户不存在");
        }
        user.setUid(oldUser.getUid());
        userDAO.saveAndFlush(user);
    }

    @Override
    public void login(String username, String password, HttpServletRequest request) throws UserException {
        UserPO user = getUser(username);
        if (MD5Utils.contrastString(password, user.getPassword())){
            request.getSession().setAttribute(WebConstant.USER_SESSION_KEY, user);
        }else {
            throw new UserException("用户名或密码不正确");
        }
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
