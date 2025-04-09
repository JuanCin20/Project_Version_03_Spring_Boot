package com.application.Project_Version_03_Spring_Boot.service;

import org.springframework.stereotype.Service;
import com.application.Project_Version_03_Spring_Boot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.application.Project_Version_03_Spring_Boot.entity.CategoryEntity;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryEntity> findAll() {
        try {
            return categoryRepository.findAll();
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: findAll - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<CategoryEntity> findById(Long CategoryId) {
        try {
            return categoryRepository.findById(CategoryId);
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: findById - " + Obj_Exception.getMessage() + ".");
        }
    }

    public CategoryEntity save(CategoryEntity categoryEntity) {
        try {
            return categoryRepository.save(categoryEntity);
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: save - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<CategoryEntity> update(Long CategoryId, CategoryEntity categoryEntity) {
        try {
            Optional<CategoryEntity> optionalCategoryEntity = this.findById(CategoryId);
            if (optionalCategoryEntity.isPresent()) {
                CategoryEntity categoryEntity1 = optionalCategoryEntity.get();
                categoryEntity1.setCategoryName(categoryEntity.getCategoryName());
                categoryEntity1.setCategoryDescription(categoryEntity.getCategoryDescription());
                categoryEntity1.setCategoryState(categoryEntity.getCategoryState());
                CategoryEntity categoryEntity2 = categoryRepository.save(categoryEntity1);
                return Optional.of(categoryEntity2);
            } else {
                return Optional.empty();
            }
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: update - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<CategoryEntity> delete(Long CategoryId) {
        Optional<CategoryEntity> optionalCategoryEntity = this.findById(CategoryId);
        if (optionalCategoryEntity.isPresent()) {
            CategoryEntity categoryEntity = optionalCategoryEntity.get();
            categoryRepository.delete(categoryEntity);
            return Optional.of(categoryEntity);
        }
        return Optional.empty();
    }
}