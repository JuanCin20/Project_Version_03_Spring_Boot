package com.application.Project_Version_03_Spring_Boot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.application.Project_Version_03_Spring_Boot.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import java.util.Objects;
import java.util.Set;
import com.application.Project_Version_03_Spring_Boot.entity.RoleEntity;
import java.util.HashSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Random;
import com.application.Project_Version_03_Spring_Boot.component.DataInitializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.AuthenticationException;
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

    private String generateUserPassword() {
        int leftLimit = 48; // Numeral "0"
        int rightLimit = 122; // Letter "z"
        int targetStringLength = 10;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength).collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
    }

    private Boolean sequentialSearchUserDni(Integer UserDni) {
        if (!DataInitializer.userEntities.isEmpty()) {
            for (int i = 0; i < DataInitializer.userEntities.size(); i++) {
                if (Objects.equals(DataInitializer.userEntities.get(i).getUserDni(), UserDni)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean sequentialSearchUserEmail(String UserEmail) {
        if (!DataInitializer.userEntities.isEmpty()) {
            for (int i = 0; i < DataInitializer.userEntities.size(); i++) {
                if (Objects.equals(DataInitializer.userEntities.get(i).getUserEmail(), UserEmail)) {
                    return true;
                }
            }
        }
        return false;
    }

    private Boolean sequentialSearchUserPhone(Integer UserPhone) {
        if (!DataInitializer.userEntities.isEmpty()) {
            for (int i = 0; i < DataInitializer.userEntities.size(); i++) {
                if (Objects.equals(DataInitializer.userEntities.get(i).getUserPhone(), UserPhone)) {
                    return true;
                }
            }
        }
        return false;
    }

    @PostMapping(path = "/log_in")
    public String log_in(@RequestParam(value = "UserEmail") String UserEmail, @RequestParam(value = "UserPassword") String UserPassword, Model model) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(UserEmail, UserPassword));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return "redirect:/ProjectVersion03SpringBootApplication/index";
        } catch (AuthenticationException authenticationException) {
            model.addAttribute("error", "<i class='fa-solid fa-triangle-exclamation'></i>&nbsp;<label>Incorrect data, Please Verify your Credentials.</label><button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
            return "Hello World!";
        }
    }

    @PostMapping(path = "/sign_up")
    public ModelAndView sign_up(@Valid @ModelAttribute(value = "userEntity") UserEntity userEntity, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        roleEntities.clear();
        RoleEntity roleEntity = new RoleEntity(4L, null, null, "");
        roleEntities.add(roleEntity);
        userEntity.setRoleEntities(roleEntities);
        userEntity.setUserPassword("@20" + this.generateUserPassword());
        userEntity.setUserState(true);
        userEntity.setUserRegister(register);
        userEntity.setUserNotAccountExpired(true);
        userEntity.setUserNotAccountBlocked(true);
        userEntity.setUserCredentialNotExpired(true);

        ModelAndView modelAndView = new ModelAndView("user/sign_up");

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("title", "Project_Version_03_Sign_Up");
            return modelAndView;
        } else {
            if (this.sequentialSearchUserDni(userEntity.getUserDni())) {
                modelAndView.addObject("title", "Project_Version_03_Sign_Up");
                modelAndView.addObject("error", "<i class='fa-solid fa-triangle-exclamation'></i>&nbsp;<label>Repeat: UserDni</label><button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
                return modelAndView;
            } else {
                if (this.sequentialSearchUserEmail(userEntity.getUserEmail())) {
                    modelAndView.addObject("title", "Project_Version_03_Sign_Up");
                    modelAndView.addObject("error", "<i class='fa-solid fa-triangle-exclamation'></i>&nbsp;<label>Repeat: UserEmail</label><button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
                    return modelAndView;
                } else {
                    if (this.sequentialSearchUserPhone(userEntity.getUserPhone())) {
                        modelAndView.addObject("title", "Project_Version_03_Sign_Up");
                        modelAndView.addObject("error", "<i class='fa-solid fa-triangle-exclamation'></i>&nbsp;<label>Repeat: UserPhone</label><button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
                        return modelAndView;
                    } else {
                        try {
                            userService.save(userEntity);
                            redirectAttributes.addFlashAttribute("success", "<i class=\"fa-solid fa-circle-check\"></i>&nbsp;<label>Registration Successful</label><button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
                            return new ModelAndView("redirect:/user/log_in");
                        } catch (Exception Obj_Exception) {
                            modelAndView.addObject("title", "Project_Version_03_Sign_Up");
                            modelAndView.addObject("error", "<i class='fa-solid fa-triangle-exclamation'></i>&nbsp;<label>" + Obj_Exception.getMessage() + "</label><button type='button' class='btn-close' data-bs-dismiss='alert' aria-label='Close'></button>");
                            return modelAndView;
                        }
                    }
                }
            }
        }
    }
}