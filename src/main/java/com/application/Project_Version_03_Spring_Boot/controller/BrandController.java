package com.application.Project_Version_03_Spring_Boot.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.application.Project_Version_03_Spring_Boot.service.BrandService;
import com.application.Project_Version_03_Spring_Boot.component.ConvertBrandEntityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.application.Project_Version_03_Spring_Boot.dto.BrandDto;
import org.springframework.http.HttpHeaders;
import com.application.Project_Version_03_Spring_Boot.entity.BrandEntity;
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
@RequestMapping(path = "/api/rest/brand")
public class BrandController {

    private final BrandService brandService;

    private final ConvertBrandEntityDto convertBrandEntityDto;

    @Autowired
    public BrandController(BrandService brandService, ConvertBrandEntityDto convertBrandEntityDto) {
        this.brandService = brandService;
        this.convertBrandEntityDto = convertBrandEntityDto;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public ResponseEntity<List<BrandDto>> brands() {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        List<BrandEntity> brandEntities = brandService.findAll();
        List<BrandDto> brandDtos = new ArrayList<>();
        if (brandEntities.isEmpty()) {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        } else {
            for (int i = 0; i < brandEntities.size(); i++) {
                BrandDto brandDto = convertBrandEntityDto.convert(brandEntities.get(i));
                brandDtos.add(brandDto);
            }
            return new ResponseEntity<List<BrandDto>>(brandDtos, Obj_HttpHeaders, HttpStatus.FOUND);
        }
    }

    @GetMapping(path = "/{BrandId}")
    @ResponseBody
    public ResponseEntity<BrandDto> brand(@PathVariable(value = "BrandId") Long BrandId) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<BrandEntity> optionalBrandEntity = brandService.findById(BrandId);
        if (optionalBrandEntity.isPresent()) {
            BrandEntity brandEntity = optionalBrandEntity.get();
            BrandDto brandDto = convertBrandEntityDto.convert(brandEntity);
            return new ResponseEntity<BrandDto>(brandDto, Obj_HttpHeaders, HttpStatus.FOUND);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/save")
    @ResponseBody
    public ResponseEntity<BrandDto> save(@RequestBody BrandEntity brandEntity) {
        BrandEntity brandEntity1 = brandService.save(brandEntity);
        BrandDto brandDto = convertBrandEntityDto.convert(brandEntity1);
        return ResponseEntity.status(HttpStatus.CREATED).header("headerName", "headerValue").body(brandDto);
    }

    @PutMapping(path = "/update")
    @ResponseBody
    public ResponseEntity<BrandDto> update(@RequestParam(value = "BrandId") Long BrandId, @RequestBody BrandEntity brandEntity) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<BrandEntity> optionalBrandEntity = brandService.update(BrandId, brandEntity);
        if (optionalBrandEntity.isPresent()) {
            BrandEntity brandEntity1 = optionalBrandEntity.get();
            BrandDto brandDto = convertBrandEntityDto.convert(brandEntity1);
            return new ResponseEntity<>(brandDto, HttpStatus.OK);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete/{BrandId}")
    @ResponseBody
    public ResponseEntity<BrandDto> delete(@PathVariable(value = "BrandId") Long BrandId) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<BrandEntity> optionalBrandEntity = brandService.delete(BrandId);
        if (optionalBrandEntity.isPresent()) {
            BrandEntity brandEntity = optionalBrandEntity.get();
            BrandDto brandDto = convertBrandEntityDto.convert(brandEntity);
            return new ResponseEntity<>(brandDto, HttpStatus.OK);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }
}