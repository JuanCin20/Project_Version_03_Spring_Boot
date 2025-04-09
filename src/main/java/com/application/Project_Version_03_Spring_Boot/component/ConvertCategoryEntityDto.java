package com.application.Project_Version_03_Spring_Boot.component;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import com.application.Project_Version_03_Spring_Boot.entity.CategoryEntity;
import com.application.Project_Version_03_Spring_Boot.dto.CategoryDto;

@Component
public class ConvertCategoryEntityDto implements Converter<CategoryEntity, CategoryDto> {

    @Override
    public CategoryDto convert(CategoryEntity source) {
        CategoryDto categoryDto = new CategoryDto(source.getCategoryId(), source.getCategoryName(), source.getCategoryDescription(), source.getCategoryState(), source.getCategoryRegister(), source.getNumberOfProducts());
        return categoryDto;
    }
}