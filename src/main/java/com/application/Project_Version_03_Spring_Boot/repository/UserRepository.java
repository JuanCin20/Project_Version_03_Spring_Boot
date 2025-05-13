package com.application.Project_Version_03_Spring_Boot.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.application.Project_Version_03_Spring_Boot.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import jakarta.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // @Query(nativeQuery = true, value = "SELECT * FROM UserTable UT WHERE UT.UserEmail = :UserEmail")
    // Optional<UserEntity> findUserByEmail(@Param(value = "UserEmail") String UserEmail);

    // @Query(nativeQuery = true, value = "SELECT * FROM USER_TABLE UT WHERE UT.USER_EMAIL = :USER_EMAIL")
    // Optional<UserEntity> findUserByEmail(@Param(value = "USER_EMAIL") String USER_EMAIL);

    @Query(nativeQuery = true, value = "SELECT * FROM user_table ut WHERE ut.user_email = :user_email")
    Optional<UserEntity> findUserByEmail(@Param(value = "user_email") String user_email);

    /* @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE UserRole, UserTable FROM UserRole LEFT JOIN UserTable ON UserRole.UserId = UserTable.UserId WHERE UserRole.UserId = :UserId")
    void deleteUserRoleById(@Param(value = "UserId") Long UserId); */

    /* @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE USER_ROLE, USER_TABLE FROM USER_ROLE LEFT JOIN USER_TABLE ON USER_ROLE.USER_ID = USER_TABLE.USER_ID WHERE USER_ROLE.USER_ID = :USER_ID")
    void deleteUserRoleById(@Param(value = "USER_ID") Long USER_ID); */

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM user_role WHERE user_id = :user_id")
    void deleteUserRoleById(@Param(value = "user_id") Long user_id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM user_table WHERE user_id = :user_id")
    void deleteUserById(@Param(value = "user_id") Long user_id);
}