package com.application.Project_Version_03_Spring_Boot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.application.Project_Version_03_Spring_Boot.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import java.util.Set;
import com.application.Project_Version_03_Spring_Boot.entity.RoleEntity;
import java.util.HashSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Random;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.application.Project_Version_03_Spring_Boot.entity.UserEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping(path = "/api/authentication")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    Set<RoleEntity> roleEntities = new HashSet<>();

    LocalDateTime localDateTime = LocalDateTime.now();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    String register = localDateTime.format(dateTimeFormatter);

    @Autowired
    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    private String GenerateUserPassword() {
        int leftLimit = 48; // Numeral "0"
        int rightLimit = 122; // Letter "z"
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    /**/

    @PostMapping(path = "/sign_up")
    public ModelAndView sign_up(@Valid @ModelAttribute(value = "userEntity") UserEntity userEntity, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        roleEntities.clear();
        RoleEntity roleEntity = new RoleEntity(4L, null, null, "");
        roleEntities.add(roleEntity);
        userEntity.setRoleEntities(roleEntities);
        userEntity.setUserPassword("@20" + this.GenerateUserPassword());
        userEntity.setUserState(true);
        userEntity.setUserRegister(register);
        userEntity.setUserNotAccountExpired(true);
        userEntity.setUserNotAccountBlocked(true);
        userEntity.setUserCredentialNotExpired(true);

        if (bindingResult.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("user/sign_up");
            modelAndView.addObject("title", "Project_Version_03_Sign_Up");
            return modelAndView;
        } else {
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
}