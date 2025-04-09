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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.persistence.OneToMany;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "UserTable")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Long UserId;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UserRole", joinColumns = @JoinColumn(name = "UserId"), inverseJoinColumns = @JoinColumn(name = "RoleId"))
    private Set<RoleEntity> roleEntities = new HashSet<>();

    @Column(name = "UserDni", length = 10, unique = true, nullable = false)
    @Min(value = 10000000, message = "Min: UserDni")
    @Max(value = 99999999, message = "Max: UserDni")
    @NotNull(message = "NotNull: UserDni")
    private Integer UserDni;

    @Column(name = "UserEmail", length = 50, unique = true, nullable = false)
    @NotBlank(message = "NotBlank: UserEmail")
    @NotEmpty(message = "NotEmpty: UserEmail")
    @NotNull(message = "NotNull: UserEmail")
    @Size(min = 10, max = 50, message = "Size: UserEmail")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Pattern: UserEmail")
    @Email(message = "Email: UserEmail")
    private String UserEmail;

    @Column(name = "UserPassword", length = 150, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: UserPassword")
    @NotEmpty(message = "NotEmpty: UserPassword")
    @NotNull(message = "NotNull: UserPassword")
    @Size(min = 5, max = 100, message = "Size: UserPassword")
    // @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$")
    private String UserPassword;

    @Column(name = "UserName", length = 50, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: UserName")
    @NotEmpty(message = "NotEmpty: UserName")
    @NotNull(message = "NotNull: UserName")
    @Size(min = 5, max = 50, message = "Size: UserName")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\u00E0-\\u00FC]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\u00E0-\\u00FC]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\u00E0-\\u00FC]+$", message = "Pattern: UserName")
    private String UserName;

    @Column(name = "UserLastName", length = 50, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: UserLastName")
    @NotEmpty(message = "NotEmpty: UserLastName")
    @NotNull(message = "NotNull: UserLastName")
    @Size(min = 5, max = 50, message = "Size: UserLastName")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\u00E0-\\u00FC]+(\\s*[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\u00E0-\\u00FC]*)*[a-zA-ZÀ-ÿ\\u00f1\\u00d1\\u00E0-\\u00FC]+$", message = "Pattern: UserLastName")
    private String UserLastName;

    @Column(name = "UserPhone", length = 10, unique = true, nullable = false)
    @Min(value = 900000000, message = "Min: UserPhone")
    @Max(value = 999999999, message = "Max: UserPhone")
    @NotNull(message = "NotNull: UserPhone")
    private Integer UserPhone;

    @Column(name = "UserAddress", length = 50, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: UserAddress")
    @NotEmpty(message = "NotEmpty: UserAddress")
    @NotNull(message = "NotNull: UserAddress")
    @Size(min = 5, max = 50, message = "Size: UserAddress")
    private String UserAddress;

    @Column(name = "UserBirth", length = 10, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: UserBirth")
    @NotEmpty(message = "NotEmpty: UserBirth")
    @NotNull(message = "NotNull: UserBirth")
    @Size(min = 10, max = 10, message = "Size: UserBirth")
    private String UserBirth;

    @Column(name = "UserState", unique = false, nullable = false)
    private Boolean UserState;

    @Column(name = "UserRegister", length = 10, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: UserRegister")
    @NotEmpty(message = "NotEmpty: UserRegister")
    @NotNull(message = "NotNull: UserRegister")
    @Size(min = 10, max = 10, message = "Size: UserRegister")
    private String UserRegister;

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillEntity> billEntities = new ArrayList<>();

    @Column(name = "UserNotAccountExpired", unique = false, nullable = false)
    private Boolean UserNotAccountExpired;

    @Column(name = "UserNotAccountBlocked", unique = false, nullable = false)
    private Boolean UserNotAccountBlocked;

    @Column(name = "UserCredentialNotExpired", unique = false, nullable = false)
    private Boolean UserCredentialNotExpired;
}