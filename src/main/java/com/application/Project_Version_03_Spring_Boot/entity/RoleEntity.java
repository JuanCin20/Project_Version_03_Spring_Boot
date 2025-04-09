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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "RoleTable")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoleId")
    private Long RoleId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "RolePermission", joinColumns = @JoinColumn(name = "RoleId"), inverseJoinColumns = @JoinColumn(name = "PermissionId"))
    private Set<PermissionEntity> permissionEntities = new HashSet<>();

    @Column(name = "RoleName")
    @Enumerated(EnumType.STRING)
    private EnumRole enumRole;

    @Column(name = "RoleRegister", length = 10, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: RoleRegister")
    @NotEmpty(message = "NotEmpty: RoleRegister")
    @NotNull(message = "NotNull: RoleRegister")
    @Size(min = 10, max = 10, message = "Size: RoleRegister")
    private String RoleRegister;
}