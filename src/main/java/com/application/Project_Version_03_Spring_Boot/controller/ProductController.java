package com.application.Project_Version_03_Spring_Boot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.application.Project_Version_03_Spring_Boot.service.ProductService;
import com.application.Project_Version_03_Spring_Boot.component.ConvertProductEntityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.application.Project_Version_03_Spring_Boot.dto.ProductDto;
import org.springframework.http.HttpHeaders;
import com.application.Project_Version_03_Spring_Boot.entity.ProductEntity;
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
@RequestMapping(path = "/api/rest/product")
public class ProductController {

    private final ProductService productService;

    private final ConvertProductEntityDto convertProductEntityDto;

    @Autowired
    public ProductController(ProductService productService, ConvertProductEntityDto convertProductEntityDto) {
        this.productService = productService;
        this.convertProductEntityDto = convertProductEntityDto;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public ResponseEntity<List<ProductDto>> products() {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        List<ProductEntity> productEntities = productService.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        if (productEntities.isEmpty()) {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        } else {
            for (int i = 0; i < productEntities.size(); i++) {
                ProductDto productDto = convertProductEntityDto.convert(productEntities.get(i));
                productDtos.add(productDto);
            }
            return new ResponseEntity<List<ProductDto>>(productDtos, Obj_HttpHeaders, HttpStatus.FOUND);
        }
    }

    @GetMapping(path = "/{ProductId}")
    @ResponseBody
    public ResponseEntity<ProductDto> product(@PathVariable(value = "ProductId") Long ProductId) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<ProductEntity> optionalProductEntity = productService.findById(ProductId);
        if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity = optionalProductEntity.get();
            ProductDto productDto = convertProductEntityDto.convert(productEntity);
            return new ResponseEntity<ProductDto>(productDto, Obj_HttpHeaders, HttpStatus.FOUND);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/save/{CategoryId}/{BrandId}")
    @ResponseBody
    public ResponseEntity<ProductDto> save(@RequestBody ProductEntity productEntity, @PathVariable(value = "CategoryId") Long CategoryId, @PathVariable(value = "BrandId") Long BrandId) {
        ProductEntity productEntity1 = productService.save(productEntity, CategoryId, BrandId);
        ProductDto productDto = convertProductEntityDto.convert(productEntity1);
        return ResponseEntity.status(HttpStatus.CREATED).header("headerName", "headerValue").body(productDto);
    }

    @PutMapping(path = "/update/{ProductId}")
    @ResponseBody
    public ResponseEntity<ProductDto> update(@PathVariable(value = "ProductId") Long ProductId, @RequestBody ProductEntity productEntity, @RequestParam(value = "CategoryId") Long CategoryId, @RequestParam(value = "BrandId") Long BrandId) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<ProductEntity> optionalProductEntity = productService.update(ProductId, productEntity, CategoryId, BrandId);
        if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity1 = optionalProductEntity.get();
            ProductDto productDto = convertProductEntityDto.convert(productEntity1);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete/{ProductId}")
    @ResponseBody
    public ResponseEntity<ProductDto> delete(@PathVariable(value = "ProductId") Long ProductId) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<ProductEntity> optionalProductEntity = productService.delete(ProductId);
        if (optionalProductEntity.isPresent()) {
            ProductEntity productEntity = optionalProductEntity.get();
            ProductDto productDto = convertProductEntityDto.convert(productEntity);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }
}