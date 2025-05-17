package com.application.Project_Version_03_Spring_Boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.application.Project_Version_03_Spring_Boot.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping(path = "/user")
public class UserControllerView {

    UserEntity userEntity = new UserEntity();

    @GetMapping(path = "/log_in")
    public String log_in(Model model) {
        model.addAttribute("title", "Project_Version_03_Log_In");
        return "user/log_in";
    }

    @GetMapping(path = "/sign_up")
    public String sign_up(Model model) {
        model.addAttribute("title", "Project_Version_03_Sign_Up");
        model.addAttribute("userEntity", userEntity);
        return "user/sign_up";
    }
}