package com.application.Project_Version_03_Spring_Boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
@RequestMapping(path = "/ProjectVersion03SpringBootApplication")
public class MainControllerView {

    @GetMapping(path = "/index")
    public String index(Model model) {
        model.addAttribute("title", "Project_Version_03_Index");
        return "index";
    }
}