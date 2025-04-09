package com.application.Project_Version_03_Spring_Boot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping(path = "/api/authentication")
@PreAuthorize(value = "denyAll()")
public class TestAuthentication {

    @GetMapping(path = "/Test")
    @PreAuthorize(value = "permitAll()")
    public String Test() {
        return "Test Authentication Confirmed";
    }

    @GetMapping(path = "/Public")
    @PreAuthorize(value = "hasAuthority('READ')")
    public String Public() {
        return "Public Authentication Confirmed";
    }

    @PostMapping(path = "/Private")
    @PreAuthorize(value = "hasAuthority('CREATE')")
    public String Private() {
        return "Private Authentication Confirmed";
    }
}