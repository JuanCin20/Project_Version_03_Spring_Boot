package com.application.Project_Version_03_Spring_Boot.service;

import com.application.Project_Version_03_Spring_Boot.repository.CRepository;

public class DService implements CRepository {

    @Override
    public boolean Subtraction(int a, int b) {
        int c = a - b;
        if (c > 0) {
            return true;
        } else {
            return false;
        }
    }
}