package com.application.Project_Version_03_Spring_Boot.dto;

public record CategoryDto(Long categoryId, String categoryName, String categoryDescription, Boolean categoryState,
                          String categoryRegister, Integer numberOfProducts) {

}