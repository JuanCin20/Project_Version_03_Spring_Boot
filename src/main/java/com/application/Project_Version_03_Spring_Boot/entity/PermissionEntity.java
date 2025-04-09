package com.application.Project_Version_03_Spring_Boot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "PermissionTable")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PermissionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PermissionId")
    private Long PermissionId;

    @Column(name = "PermissionName", length = 50, unique = true, nullable = false, updatable = false)
    @NotBlank(message = "NotBlank: PermissionName")
    @NotEmpty(message = "NotEmpty: PermissionName")
    @NotNull(message = "NotNull: PermissionName")
    @Size(min = 4, max = 50, message = "Size: PermissionName")
    private String PermissionName;
}