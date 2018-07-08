package com.dcs.web.controller;

import com.dcs.web.constant.WebConstant;
import com.dcs.web.exception.UserException;
import com.dcs.web.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {
    @Autowired
    private UserServices userServicesImp;

    @GetMapping(value = "/login")
    public String getLogin(Model model) {
        return WebConstant.LOGIN_WEB;
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpServletRequest request, Model model) {
        try {
            userServicesImp.login(username, password, request);
        } catch (UserException e) {
            e.printStackTrace();
            model.addAttribute(WebConstant.ERROR_MODEL_KEY, e.getMessage());
            getLogin(model);
        }
        return WebConstant.USER_ADMIN_WEB;
    }

    public UserServices getUserServicesImp() {
        return userServicesImp;
    }

    public void setUserServicesImp(UserServices userServicesImp) {
        this.userServicesImp = userServicesImp;
    }
}
