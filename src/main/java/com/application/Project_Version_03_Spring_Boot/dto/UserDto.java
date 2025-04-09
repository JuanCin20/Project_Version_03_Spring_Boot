package com.application.Project_Version_03_Spring_Boot.dto;

import java.util.Set;
import com.application.Project_Version_03_Spring_Boot.entity.RoleEntity;

public record UserDto(Long userId, Set<RoleEntity> roleEntities, Integer userDni,
                      String userEmail, String userPassword, String userName, String userLastName, Integer userPhone,
                      String userAddress, String userBirth, Boolean userState, String userRegister,
                      Boolean userNotAccountExpired, Boolean userNotAccountBlocked, Boolean userCredentialNotExpired) {

}