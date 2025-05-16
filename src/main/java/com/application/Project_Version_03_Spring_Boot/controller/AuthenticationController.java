package com.application.Project_Version_03_Spring_Boot.controller;

import com.application.Project_Version_03_Spring_Boot.component.ConvertUserEntityDto;
import com.application.Project_Version_03_Spring_Boot.entity.EnumRole;
import com.application.Project_Version_03_Spring_Boot.entity.PermissionEntity;
import com.application.Project_Version_03_Spring_Boot.entity.RoleEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.application.Project_Version_03_Spring_Boot.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.application.Project_Version_03_Spring_Boot.entity.UserEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;
// import com.application.Project_Version_03_Spring_Boot.dto.UserDto;

@RestController
@RequestMapping(path = "/api/authentication")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    Set<RoleEntity> roleEntities = new HashSet<>();

    @Autowired
    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    /**/

    @PostMapping(path = "/sign_up")
    public ModelAndView sign_up(@Valid @ModelAttribute(value = "userEntity") UserEntity userEntity, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        RoleEntity roleEntity = new RoleEntity(4L, null, null, "");
        roleEntities.add(roleEntity);
        userEntity.setRoleEntities(roleEntities);
        userEntity.setUserState(true);
        userEntity.setUserNotAccountExpired(true);
        userEntity.setUserNotAccountBlocked(true);
        userEntity.setUserCredentialNotExpired(true);

        System.out.println(userEntity.toString());

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("user/sign_up");
            modelAndView.addObject("title", "Project_Version_03_Sign_Up");
            return modelAndView;
        }

        try {
            userService.save(userEntity);
            redirectAttributes.addFlashAttribute("success", "Registration Successful");
            return new ModelAndView("redirect:/user/log_in");
        } catch (Exception Obj_Exception) {
            ModelAndView modelAndView = new ModelAndView("user/sign_up");
            modelAndView.addObject("title", "Project_Version_03_Sign_Up");
            modelAndView.addObject("error", Obj_Exception.getMessage());
            return modelAndView;
        }
    }
}