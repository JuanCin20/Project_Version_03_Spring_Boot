package com.application.Project_Version_03_Spring_Boot.repository;

public interface BRepository {
    // ARepository Dependency
    public ARepository getAddition();

    public void setARepository(ARepository aRepository);

    // Functional Methods
    public int AdditionMultiplication(int a, int b, int Multiplication);

    public int Multiplication(int a, int b);
}