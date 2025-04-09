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
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;

@Entity
@Table(name = "BillDetailTable")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillDetailId")
    private Long BillDetailId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "BillId", referencedColumnName = "BillId")
    private BillEntity billEntity;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ProductId", referencedColumnName = "ProductId")
    private ProductEntity productEntity;

    @Column(name = "ProductPrice", length = 10, unique = false, nullable = false)
    @DecimalMin(value = "00.00", message = "DecimalMin: ProductPrice")
    @DecimalMax(value = "10000000.00", message = "DecimalMax: ProductPrice")
    @NotNull(message = "NotNull: ProductPrice")
    private Double ProductPrice;

    @Column(name = "ProductQuantity", length = 10, unique = false, nullable = false)
    @Min(value = 1, message = "Min: ProductQuantity")
    @Max(value = 10, message = "Max: ProductQuantity")
    @NotNull(message = "NotNull: ProductQuantity")
    private Integer ProductQuantity;

    @Column(name = "BillDetailSubAmount", length = 10, unique = false, nullable = false)
    @DecimalMin(value = "00.00", message = "DecimalMin: BillDetailSubAmount")
    @DecimalMax(value = "10000000.00", message = "DecimalMax: BillDetailSubAmount")
    @NotNull(message = "NotNull: BillDetailSubAmount")
    private Double BillDetailSubAmount;

    @Column(name = "BillDetailDiscount", length = 10, unique = false, nullable = false)
    @DecimalMin(value = "00.00", message = "DecimalMin: BillDetailDiscount")
    @DecimalMax(value = "10000000.00", message = "DecimalMax: BillDetailDiscount")
    @NotNull(message = "NotNull: BillDetailDiscount")
    private Double BillDetailDiscount;

    @Column(name = "BillDetailIGV", length = 10, unique = false, nullable = false)
    @DecimalMin(value = "00.00", message = "DecimalMin: BillDetailIGV")
    @DecimalMax(value = "10000000.00", message = "DecimalMax: BillDetailIGV")
    @NotNull(message = "NotNull: BillDetailIGV")
    private Double BillDetailIGV;

    @Column(name = "BillDetailAmount", length = 10, unique = false, nullable = false)
    @DecimalMin(value = "00.00", message = "DecimalMin: BillDetailAmount")
    @DecimalMax(value = "10000000.00", message = "DecimalMax: BillDetailAmount")
    @NotNull(message = "NotNull: BillDetailAmount")
    private Double BillDetailAmount;
}