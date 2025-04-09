package com.application.Project_Version_03_Spring_Boot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.application.Project_Version_03_Spring_Boot.service.CategoryService;
import com.application.Project_Version_03_Spring_Boot.component.ConvertCategoryEntityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.application.Project_Version_03_Spring_Boot.dto.CategoryDto;
import org.springframework.http.HttpHeaders;
import com.application.Project_Version_03_Spring_Boot.entity.CategoryEntity;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping(path = "/api/rest/category")
public class CategoryController {

    private final CategoryService categoryService;

    private final ConvertCategoryEntityDto convertCategoryEntityDto;

    @Autowired
    public CategoryController(CategoryService categoryService, ConvertCategoryEntityDto convertCategoryEntityDto) {
        this.categoryService = categoryService;
        this.convertCategoryEntityDto = convertCategoryEntityDto;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public ResponseEntity<List<CategoryDto>> categories() {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        List<CategoryEntity> categoryEntities = categoryService.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        if (categoryEntities.isEmpty()) {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        } else {
            for (int i = 0; i < categoryEntities.size(); i++) {
                CategoryDto categoryDto = convertCategoryEntityDto.convert(categoryEntities.get(i));
                categoryDtos.add(categoryDto);
            }
            return new ResponseEntity<List<CategoryDto>>(categoryDtos, Obj_HttpHeaders, HttpStatus.FOUND);
        }
    }

    @GetMapping(path = "/{CategoryId}")
    @ResponseBody
    public ResponseEntity<CategoryDto> category(@PathVariable(value = "CategoryId") Long CategoryId) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<CategoryEntity> optionalCategoryEntity = categoryService.findById(CategoryId);
        if (optionalCategoryEntity.isPresent()) {
            CategoryEntity categoryEntity = optionalCategoryEntity.get();
            CategoryDto categoryDto = convertCategoryEntityDto.convert(categoryEntity);
            return new ResponseEntity<CategoryDto>(categoryDto, Obj_HttpHeaders, HttpStatus.FOUND);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/save")
    @ResponseBody
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryEntity categoryEntity) {
        CategoryEntity categoryEntity1 = categoryService.save(categoryEntity);
        CategoryDto categoryDto = convertCategoryEntityDto.convert(categoryEntity1);
        return ResponseEntity.status(HttpStatus.CREATED).header("headerName", "headerValue").body(categoryDto);
    }

    @PutMapping(path = "/update")
    @ResponseBody
    public ResponseEntity<CategoryDto> update(@RequestParam(value = "CategoryId") Long CategoryId, @RequestBody CategoryEntity categoryEntity) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<CategoryEntity> optionalCategoryEntity = categoryService.update(CategoryId, categoryEntity);
        if (optionalCategoryEntity.isPresent()) {
            CategoryEntity categoryEntity1 = optionalCategoryEntity.get();
            CategoryDto categoryDto = convertCategoryEntityDto.convert(categoryEntity1);
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete/{CategoryId}")
    @ResponseBody
    public ResponseEntity<CategoryDto> delete(@PathVariable(value = "CategoryId") Long CategoryId) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<CategoryEntity> optionalCategoryEntity = categoryService.delete(CategoryId);
        if (optionalCategoryEntity.isPresent()) {
            CategoryEntity categoryEntity = optionalCategoryEntity.get();
            CategoryDto categoryDto = convertCategoryEntityDto.convert(categoryEntity);
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }
}