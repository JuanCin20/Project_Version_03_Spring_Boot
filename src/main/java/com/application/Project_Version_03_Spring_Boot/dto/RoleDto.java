package com.application.Project_Version_03_Spring_Boot.dto;

import com.application.Project_Version_03_Spring_Boot.entity.EnumRole;

public record RoleDto(Long roleId, EnumRole enumRole, String roleRegister) {

}