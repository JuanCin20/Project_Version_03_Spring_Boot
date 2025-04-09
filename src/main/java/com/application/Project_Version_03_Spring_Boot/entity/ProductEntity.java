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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "ProductTable")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private Long ProductId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CategoryId", referencedColumnName = "CategoryId")
    private CategoryEntity categoryEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BrandId", referencedColumnName = "BrandId")
    private BrandEntity brandEntity;

    @Column(name = "ProductName", length = 150, unique = true, nullable = false)
    @NotBlank(message = "NotBlank: ProductName")
    @NotEmpty(message = "NotEmpty: ProductName")
    @NotNull(message = "NotNull: ProductName")
    @Size(min = 5, max = 150, message = "Size: ProductName")
    private String ProductName;

    @Column(name = "ProductStock", length = 10, unique = false, nullable = false)
    @Min(value = 0, message = "Min: ProductStock")
    @Max(value = 10000, message = "Max: ProductStock")
    @NotNull(message = "NotNull: ProductStock")
    private Integer ProductStock;

    @Column(name = "ProductPrice", length = 10, unique = false, nullable = false)
    @DecimalMin(value = "00.00", message = "DecimalMin: ProductPrice")
    @DecimalMax(value = "10000000.00", message = "DecimalMax: ProductPrice")
    @NotNull(message = "NotNull: ProductPrice")
    private Double ProductPrice;

    @Column(name = "ProductDescription", length = 260, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: ProductDescription")
    @NotEmpty(message = "NotEmpty: ProductDescription")
    @NotNull(message = "NotNull: ProductDescription")
    @Size(min = 5, max = 260, message = "Size: ProductDescription")
    private String ProductDescription;

    @Column(name = "ProductState", unique = false, nullable = false)
    private Boolean ProductState;

    @Column(name = "ProductRegister", length = 10, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: ProductRegister")
    @NotEmpty(message = "NotEmpty: ProductRegister")
    @NotNull(message = "NotNull: ProductRegister")
    @Size(min = 10, max = 10, message = "Size: ProductRegister")
    private String ProductRegister;

    @OneToMany(mappedBy = "productEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillDetailEntity> billDetailEntities = new ArrayList<>();
}