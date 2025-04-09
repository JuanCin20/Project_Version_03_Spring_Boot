package com.application.Project_Version_03_Spring_Boot.service;

import org.springframework.stereotype.Service;
import com.application.Project_Version_03_Spring_Boot.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.application.Project_Version_03_Spring_Boot.entity.BrandEntity;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepository brandRepository;

    @Autowired
    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public List<BrandEntity> findAll() {
        try {
            return brandRepository.findAll();
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: findAll - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<BrandEntity> findById(Long BrandId) {
        try {
            return brandRepository.findById(BrandId);
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: findById - " + Obj_Exception.getMessage() + ".");
        }
    }

    public BrandEntity save(BrandEntity brandEntity) {
        try {
            return brandRepository.save(brandEntity);
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: save - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<BrandEntity> update(Long BrandId, BrandEntity brandEntity) {
        try {
            Optional<BrandEntity> optionalBrandEntity = this.findById(BrandId);
            if (optionalBrandEntity.isPresent()) {
                BrandEntity brandEntity1 = optionalBrandEntity.get();
                brandEntity1.setBrandName(brandEntity.getBrandName());
                brandEntity1.setBrandPhone(brandEntity.getBrandPhone());
                brandEntity1.setBrandEmail(brandEntity.getBrandEmail());
                brandEntity1.setBrandAddress(brandEntity.getBrandAddress());
                brandEntity1.setBrandState(brandEntity.getBrandState());
                BrandEntity brandEntity2 = brandRepository.save(brandEntity1);
                return Optional.of(brandEntity2);
            } else {
                return Optional.empty();
            }
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: update - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<BrandEntity> delete(Long BrandId) {
        Optional<BrandEntity> optionalBrandEntity = this.findById(BrandId);
        if (optionalBrandEntity.isPresent()) {
            BrandEntity brandEntity = optionalBrandEntity.get();
            brandRepository.delete(brandEntity);
            return Optional.of(brandEntity);
        }
        return Optional.empty();
    }
}