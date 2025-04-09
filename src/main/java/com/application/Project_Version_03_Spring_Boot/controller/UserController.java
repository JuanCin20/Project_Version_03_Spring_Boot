package com.application.Project_Version_03_Spring_Boot.controller;

// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.application.Project_Version_03_Spring_Boot.service.UserService;
import com.application.Project_Version_03_Spring_Boot.component.ConvertUserEntityDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.application.Project_Version_03_Spring_Boot.dto.UserDto;
import org.springframework.http.HttpHeaders;
import com.application.Project_Version_03_Spring_Boot.entity.UserEntity;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;

// @CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/rest/user")
public class UserController {

    private final UserService userService;
    private final ConvertUserEntityDto convertUserEntityDto;

    @Autowired
    public UserController(UserService userService, ConvertUserEntityDto convertUserEntityDto) {
        this.userService = userService;
        this.convertUserEntityDto = convertUserEntityDto;
    }

    @GetMapping(path = "/all")
    @ResponseBody
    public ResponseEntity<List<UserDto>> users() {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        List<UserEntity> userEntities = userService.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        if (userEntities.isEmpty()) {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        } else {
            for (int i = 0; i < userEntities.size(); i++) {
                UserDto userDto = convertUserEntityDto.convert(userEntities.get(i));
                userDtos.add(userDto);
            }
            return new ResponseEntity<List<UserDto>>(userDtos, Obj_HttpHeaders, HttpStatus.OK);
        }
    }

    @GetMapping(path = "/{UserId}")
    @ResponseBody
    public ResponseEntity<UserDto> user(@PathVariable(value = "UserId") Long UserId) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<UserEntity> optionalUserEntity = userService.findById(UserId);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            UserDto userDto = convertUserEntityDto.convert(userEntity);
            return new ResponseEntity<UserDto>(userDto, Obj_HttpHeaders, HttpStatus.FOUND);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path = "/save")
    @ResponseBody
    public ResponseEntity<UserDto> save(@RequestBody UserEntity userEntity) {
        UserEntity userEntity1 = userService.save(userEntity);
        UserDto userDto = convertUserEntityDto.convert(userEntity1);
        return ResponseEntity.status(HttpStatus.OK).header("headerName", "headerValue").body(userDto);
    }

    @PutMapping(path = "/update")
    @ResponseBody
    public ResponseEntity<UserDto> update(@RequestParam(value = "UserId") Long UserId, @RequestBody UserEntity userEntity) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<UserEntity> optionalUserEntity = userService.update(UserId, userEntity);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity1 = optionalUserEntity.get();
            UserDto userDto = convertUserEntityDto.convert(userEntity1);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delete/{UserId}")
    @ResponseBody
    public ResponseEntity<UserDto> delete(@PathVariable(value = "UserId") Long UserId) {
        HttpHeaders Obj_HttpHeaders = new HttpHeaders();
        Obj_HttpHeaders.add("headerName", "headerValue");
        Optional<UserEntity> optionalUserEntity = userService.delete(UserId);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            UserDto userDto = convertUserEntityDto.convert(userEntity);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } else {
            return new ResponseEntity("NOT_FOUND", Obj_HttpHeaders, HttpStatus.NOT_FOUND);
        }
    }
}