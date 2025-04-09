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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "BrandTable")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BrandId")
    private Long BrandId;

    @Column(name = "BrandName", length = 50, unique = true, nullable = false)
    @NotBlank(message = "NotBlank: BrandName")
    @NotEmpty(message = "NotEmpty: BrandName")
    @NotNull(message = "NotNull: BrandName")
    @Size(min = 2, max = 50, message = "Size: BrandName")
    private String BrandName;

    @Column(name = "BrandPhone", length = 10, unique = true, nullable = false)
    @Min(value = 900000000, message = "Min: BrandPhone")
    @Max(value = 999999999, message = "Max: BrandPhone")
    @NotNull(message = "NotNull: BrandPhone")
    private Integer BrandPhone;

    @Column(name = "BrandEmail", length = 50, unique = true, nullable = false)
    @NotBlank(message = "NotBlank: BrandEmail")
    @NotEmpty(message = "NotEmpty: BrandEmail")
    @NotNull(message = "NotNull: BrandEmail")
    @Size(min = 10, max = 50, message = "Size: BrandEmail")
    @Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Pattern: E_Mail_Usuario")
    @Email(message = "Email: BrandEmail")
    private String BrandEmail;

    @Column(name = "BrandAddress", length = 50, unique = true, nullable = false)
    @NotBlank(message = "NotBlank: BrandAddress")
    @NotEmpty(message = "NotEmpty: BrandAddress")
    @NotNull(message = "NotNull: BrandAddress")
    @Size(min = 3, max = 50, message = "Size: BrandAddress")
    private String BrandAddress;

    @Column(name = "BrandState", unique = false, nullable = false)
    private Boolean BrandState;

    @Column(name = "BrandRegister", length = 10, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: BrandRegister")
    @NotEmpty(message = "NotEmpty: BrandRegister")
    @NotNull(message = "NotNull: BrandRegister")
    @Size(min = 10, max = 10, message = "Size: BrandRegister")
    private String BrandRegister;

    @OneToMany(mappedBy = "brandEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> productEntities = new ArrayList<>();

    public Integer getNumberOfProducts() {
        return this.productEntities.size();
    }
}