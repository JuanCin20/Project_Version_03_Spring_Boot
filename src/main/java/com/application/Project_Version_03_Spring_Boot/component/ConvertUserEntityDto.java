package com.application.Project_Version_03_Spring_Boot.component;

import org.springframework.stereotype.Component;
import org.springframework.core.convert.converter.Converter;
import com.application.Project_Version_03_Spring_Boot.entity.UserEntity;
import com.application.Project_Version_03_Spring_Boot.dto.UserDto;

@Component
public class ConvertUserEntityDto implements Converter<UserEntity, UserDto> {

    @Override
    public UserDto convert(UserEntity source) {
        UserDto userDto = new UserDto(source.getUserId(), source.getRoleEntities(), source.getUserDni(), source.getUserEmail(), source.getUserPassword(), source.getUserName(), source.getUserLastName(), source.getUserPhone(), source.getUserAddress(), source.getUserBirth(), source.getUserState(), source.getUserRegister(), source.getUserNotAccountExpired(), source.getUserNotAccountBlocked(), source.getUserCredentialNotExpired());
        return userDto;
    }
}