package com.application.Project_Version_03_Spring_Boot.dto;

public record BrandDto(Long brandId, String brandName, Integer brandPhone, String brandEmail, String brandAddress,
                       Boolean brandState, String brandRegister, Integer numberOfProducts) {

}