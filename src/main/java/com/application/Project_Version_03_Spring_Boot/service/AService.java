package com.application.Project_Version_03_Spring_Boot.service;

import com.application.Project_Version_03_Spring_Boot.repository.ARepository;

public class AService implements ARepository {

    @Override
    public int Addition(int a, int b) {
        return a + b;
    }
}