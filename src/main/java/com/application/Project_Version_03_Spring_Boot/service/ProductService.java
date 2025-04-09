package com.application.Project_Version_03_Spring_Boot.service;

import org.springframework.stereotype.Service;
import com.application.Project_Version_03_Spring_Boot.repository.CategoryRepository;
import com.application.Project_Version_03_Spring_Boot.repository.BrandRepository;
import com.application.Project_Version_03_Spring_Boot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.application.Project_Version_03_Spring_Boot.entity.ProductEntity;
import java.util.Optional;
import com.application.Project_Version_03_Spring_Boot.entity.CategoryEntity;
import com.application.Project_Version_03_Spring_Boot.entity.BrandEntity;

@Service
public class ProductService {

    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(CategoryRepository categoryRepository, BrandRepository brandRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    public List<ProductEntity> findAll() {
        try {
            return productRepository.findAll();
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: findAll - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<ProductEntity> findById(Long ProductId) {
        try {
            return productRepository.findById(ProductId);
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: findById - " + Obj_Exception.getMessage() + ".");
        }
    }

    public ProductEntity save(ProductEntity productEntity, Long CategoryId, Long BrandId) {
        try {
            Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(CategoryId);
            Optional<BrandEntity> optionalBrandEntity = brandRepository.findById(BrandId);

            if (optionalCategoryEntity.isPresent()) {
                CategoryEntity categoryEntity = optionalCategoryEntity.get();
                productEntity.setCategoryEntity(categoryEntity);
            }

            if (optionalBrandEntity.isPresent()) {
                BrandEntity brandEntity = optionalBrandEntity.get();
                productEntity.setBrandEntity(brandEntity);
            }

            return productRepository.save(productEntity);
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: save - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<ProductEntity> update(Long ProductId, ProductEntity productEntity, Long CategoryId, Long BrandId) {
        try {
            Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(CategoryId);
            Optional<BrandEntity> optionalBrandEntity = brandRepository.findById(BrandId);
            Optional<ProductEntity> optionalProductEntity = this.findById(ProductId);

            if (optionalProductEntity.isPresent()) {
                ProductEntity productEntity1 = optionalProductEntity.get();

                if (optionalCategoryEntity.isPresent()) {
                    CategoryEntity categoryEntity = optionalCategoryEntity.get();
                    productEntity1.setCategoryEntity(categoryEntity);
                }

                if (optionalBrandEntity.isPresent()) {
                    BrandEntity brandEntity = optionalBrandEntity.get();
                    productEntity1.setBrandEntity(brandEntity);
                }

                productEntity1.setProductName(productEntity.getProductName());
                productEntity1.setProductStock(productEntity.getProductStock());
                productEntity1.setProductPrice(productEntity.getProductPrice());
                productEntity1.setProductDescription(productEntity.getProductDescription());
                productEntity1.setProductState(productEntity.getProductState());
                ProductEntity productEntity2 = productRepository.save(productEntity1);
                return Optional.of(productEntity2);
            } else {
                return Optional.empty();
            }
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: update - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<ProductEntity> delete(Long ProductId) {
        Optional<ProductEntity> optionalProductEntity = this.findById(ProductId);
        if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity = optionalProductEntity.get();
            productRepository.delete(productEntity);
            return Optional.of(productEntity);
        }
        return Optional.empty();
    }
}