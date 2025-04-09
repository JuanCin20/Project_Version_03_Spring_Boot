package com.application.Project_Version_03_Spring_Boot.component;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import com.application.Project_Version_03_Spring_Boot.entity.BrandEntity;
import com.application.Project_Version_03_Spring_Boot.dto.BrandDto;

@Component
public class ConvertBrandEntityDto implements Converter<BrandEntity, BrandDto> {

    @Override
    public BrandDto convert(BrandEntity source) {
        BrandDto brandDto = new BrandDto(source.getBrandId(), source.getBrandName(), source.getBrandPhone(), source.getBrandEmail(), source.getBrandAddress(), source.getBrandState(), source.getBrandRegister(), source.getNumberOfProducts());
        return brandDto;
    }
}