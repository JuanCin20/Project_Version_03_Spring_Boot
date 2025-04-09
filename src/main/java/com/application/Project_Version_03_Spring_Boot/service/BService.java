package com.application.Project_Version_03_Spring_Boot.service;

import com.application.Project_Version_03_Spring_Boot.repository.BRepository;
import com.application.Project_Version_03_Spring_Boot.repository.ARepository;

public class BService implements BRepository {

    // For Dependency Injection
    private ARepository aRepository;

    @Override
    public ARepository getAddition() {
        return aRepository;
    }

    @Override
    public void setARepository(ARepository aRepository) {
        this.aRepository = aRepository;
    }

    @Override
    public int AdditionMultiplication(int a, int b, int Multiplication) {
        return aRepository.Addition(a, b) * Multiplication;
    }

    @Override
    public int Multiplication(int a, int b) {
        return a * b;
    }
}