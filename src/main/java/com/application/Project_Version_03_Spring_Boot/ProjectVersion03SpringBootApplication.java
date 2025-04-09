package com.application.Project_Version_03_Spring_Boot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;

// @SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@RestController
@RequestMapping(path = "/ProjectVersion03SpringBootApplication")
public class ProjectVersion03SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectVersion03SpringBootApplication.class, args);
    }

    @GetMapping(path = "/Initializer")
    public String Initializer() {
        return "<h1 style='color: rgb(0, 0, 255);'> Hello World, my Name is Juan Carlos! </h1>";
    }
}