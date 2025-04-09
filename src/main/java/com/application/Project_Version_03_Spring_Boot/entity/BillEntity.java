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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "BillTable")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BillId")
    private Long BillId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserId", referencedColumnName = "UserId")
    private UserEntity userEntity;

    @Column(name = "BillAmount", length = 10, unique = false, nullable = false)
    @DecimalMin(value = "00.00", message = "DecimalMin: BillAmount")
    @DecimalMax(value = "10000000.00", message = "DecimalMax: BillAmount")
    @NotNull(message = "NotNull: BillAmount")
    private Double BillAmount;

    @Column(name = "BillRegister", length = 10, unique = false, nullable = false)
    @NotBlank(message = "NotBlank: BillRegister")
    @NotEmpty(message = "NotEmpty: BillRegister")
    @NotNull(message = "NotNull: BillRegister")
    @Size(min = 10, max = 10, message = "Size: BillRegister")
    private String BillRegister;

    @OneToMany(mappedBy = "billEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BillDetailEntity> billDetailEntities = new ArrayList<>();
}