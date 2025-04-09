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
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "CategoryTable")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private Long CategoryId;

    @Column(name = "CategoryName", length = 50, unique = true, nullable = false)
    @NotBlank(message = "NotBlank: CategoryName")
    @NotEmpty(message = "NotEmpty: CategoryName")
    @NotNull(message = "NotNull: CategoryName")
    @Size(min = 5, max = 50, message = "Size: CategoryName")
    private String CategoryName;

    @Column(name = "CategoryDescription", length = 260, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: CategoryDescription")
    @NotEmpty(message = "NotEmpty: CategoryDescription")
    @NotNull(message = "NotNull: CategoryDescription")
    @Size(min = 15, max = 260, message = "Size: CategoryDescription")
    private String CategoryDescription;

    @Column(name = "CategoryState", unique = false, nullable = false)
    private Boolean CategoryState;

    @Column(name = "CategoryRegister", length = 10, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: CategoryRegister")
    @NotEmpty(message = "NotEmpty: CategoryRegister")
    @NotNull(message = "NotNull: CategoryRegister")
    @Size(min = 10, max = 10, message = "Size: CategoryRegister")
    private String CategoryRegister;

    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductEntity> productEntities = new ArrayList<>();

    public Integer getNumberOfProducts() {
        return this.productEntities.size();
    }
}