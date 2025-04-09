package com.application.Project_Version_03_Spring_Boot.dto;

public record ProductDto(Long productId, CategoryDto categoryDto, BrandDto brandDto, String productName,
                         Integer productStock, Double productPrice, String productDescription, Boolean productState,
                         String productRegister) {

}