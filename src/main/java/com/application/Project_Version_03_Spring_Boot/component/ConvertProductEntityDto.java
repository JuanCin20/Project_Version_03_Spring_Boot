package com.application.Project_Version_03_Spring_Boot.component;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import com.application.Project_Version_03_Spring_Boot.entity.ProductEntity;
import com.application.Project_Version_03_Spring_Boot.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;

@Component
public class ConvertProductEntityDto implements Converter<ProductEntity, ProductDto> {

    private final ConvertCategoryEntityDto convertCategoryEntityDto;
    private final ConvertBrandEntityDto convertBrandEntityDto;

    @Autowired
    public ConvertProductEntityDto(ConvertCategoryEntityDto convertCategoryEntityDto, ConvertBrandEntityDto convertBrandEntityDto) {
        this.convertCategoryEntityDto = convertCategoryEntityDto;
        this.convertBrandEntityDto = convertBrandEntityDto;
    }

    @Override
    public ProductDto convert(ProductEntity source) {
        ProductDto productDto = new ProductDto(source.getProductId(), source.getCategoryEntity() != null ? this.convertCategoryEntityDto.convert(source.getCategoryEntity()) : null, source.getBrandEntity() != null ? this.convertBrandEntityDto.convert(source.getBrandEntity()) : null, source.getProductName(), source.getProductStock(), source.getProductPrice(), source.getProductDescription(), source.getProductState(), source.getProductRegister());
        return productDto;
    }
}