package com.application.Project_Version_03_Spring_Boot.component;

import com.application.Project_Version_03_Spring_Boot.dto.UserDto;
import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import com.application.Project_Version_03_Spring_Boot.entity.RoleEntity;
import com.application.Project_Version_03_Spring_Boot.dto.RoleDto;

@Component
public class ConvertRoleEntityDto implements Converter<RoleEntity, RoleDto> {

    @Override
    public RoleDto convert(RoleEntity source) {
        RoleDto roleDto = new RoleDto(source.getRoleId(), source.getEnumRole(), source.getRoleRegister());
        return roleDto;
    }
}