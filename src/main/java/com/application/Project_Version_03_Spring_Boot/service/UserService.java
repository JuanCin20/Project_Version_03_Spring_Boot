package com.application.Project_Version_03_Spring_Boot.service;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.application.Project_Version_03_Spring_Boot.repository.UserRepository;
import com.application.Project_Version_03_Spring_Boot.repository.RoleRepository;
import java.util.Set;
import com.application.Project_Version_03_Spring_Boot.entity.RoleEntity;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.application.Project_Version_03_Spring_Boot.entity.UserEntity;
import com.application.Project_Version_03_Spring_Boot.exception.NotFound;
import java.util.List;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import java.util.ArrayList;
import org.springframework.security.core.userdetails.User;
import java.util.Optional;
import java.util.Iterator;

import com.application.Project_Version_03_Spring_Boot.encryption.Encryption;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    Set<RoleEntity> roleEntities = new HashSet<>();

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    // BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String UserEmail) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserByEmail(UserEmail).orElseThrow(() -> new NotFound("NOT_FOUND"));

        List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
        userEntity.getRoleEntities().forEach(userRole -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority("ROLE_".concat(userRole.getEnumRole().name()))));
        userEntity.getRoleEntities().stream().flatMap(userRole -> (userRole.getPermissionEntities()).stream()).forEach(permissionEntity -> simpleGrantedAuthorities.add(new SimpleGrantedAuthority(permissionEntity.getPermissionName())));
        return new User(userEntity.getUserEmail(), userEntity.getUserPassword(), userEntity.getUserState(), userEntity.getUserNotAccountExpired(), userEntity.getUserNotAccountBlocked(), userEntity.getUserCredentialNotExpired(), simpleGrantedAuthorities);
    }

    public List<UserEntity> findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: findAll - " + Obj_Exception.getMessage() + ".");
        }
    }

    public List<RoleEntity> findAllRoleEntities() {
        try {
            return roleRepository.findAll();
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: findAll - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<UserEntity> findById(Long UserId) {
        try {
            return userRepository.findById(UserId);
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: findById - " + Obj_Exception.getMessage() + ".");
        }
    }

    public UserEntity save(UserEntity userEntity) {
        try {
            roleEntities.clear();
            Set<RoleEntity> roleEntitySet = userEntity.getRoleEntities();
            Iterator<RoleEntity> roleEntityIterator = roleEntitySet.iterator();
            while (roleEntityIterator.hasNext()) {
                RoleEntity roleEntity = roleEntityIterator.next();
                Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(roleEntity.getRoleId());
                if (optionalRoleEntity.isPresent()) {
                    RoleEntity roleEntity1 = optionalRoleEntity.get();
                    roleEntities.add(roleEntity1);
                    userEntity.setRoleEntities(roleEntities);
                }
                roleEntityIterator.remove();
            }
            String UserPassword = Encryption.encryptUserPassword(userEntity.getUserPassword());
            userEntity.setUserPassword(UserPassword);
            return userRepository.save(userEntity);
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: save - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<UserEntity> update(Long UserId, UserEntity userEntity) {
        try {
            Optional<UserEntity> optionalUserEntity = this.findById(UserId);
            if (optionalUserEntity.isPresent()) {
                UserEntity userEntity1 = optionalUserEntity.get();

                roleEntities.clear();
                Set<RoleEntity> roleEntitySet = userEntity.getRoleEntities();
                Iterator<RoleEntity> roleEntityIterator = roleEntitySet.iterator();
                while (roleEntityIterator.hasNext()) {
                    RoleEntity roleEntity = roleEntityIterator.next();
                    Optional<RoleEntity> optionalRoleEntity = roleRepository.findById(roleEntity.getRoleId());
                    if (optionalRoleEntity.isPresent()) {
                        RoleEntity roleEntity1 = optionalRoleEntity.get();
                        roleEntities.add(roleEntity1);
                        userEntity1.setRoleEntities(roleEntities);
                    }
                    roleEntityIterator.remove();
                }

                userEntity1.setUserDni(userEntity.getUserDni());
                userEntity1.setUserEmail(userEntity.getUserEmail());
                userEntity1.setUserPassword(Encryption.encryptUserPassword(userEntity.getUserPassword()));
                userEntity1.setUserName(userEntity.getUserName());
                userEntity1.setUserLastName(userEntity.getUserLastName());
                userEntity1.setUserPhone(userEntity.getUserPhone());
                userEntity1.setUserAddress(userEntity.getUserAddress());
                userEntity1.setUserBirth(userEntity.getUserBirth());
                userEntity1.setUserState(userEntity.getUserState());
                UserEntity userEntity2 = userRepository.save(userEntity1);
                return Optional.of(userEntity2);
            } else {
                return Optional.empty();
            }
        } catch (Exception Obj_Exception) {
            // Handle Exception or log the Error
            throw new RuntimeException("Error: update - " + Obj_Exception.getMessage() + ".");
        }
    }

    public Optional<UserEntity> delete(Long UserId) {
        Optional<UserEntity> optionalUserEntity = this.findById(UserId);
        if (optionalUserEntity.isPresent()) {
            UserEntity userEntity = optionalUserEntity.get();
            userRepository.deleteUserRoleById(UserId);
            userRepository.deleteUserById(UserId);
            return Optional.of(userEntity);
        }
        return Optional.empty();
    }
}