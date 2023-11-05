package com.softvan.Repository;


import com.softvan.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

@EnableJpaRepositories
public interface UserRepsitory extends JpaRepository<UserEntity,Integer> {


    Optional<UserEntity> findByEmailIgnoreCase(String username);

    @Query("SELECT ue FROM UserEntity ue join fetch ue.role WHERE ue.email = :email")
    Optional<UserEntity> getUserByUsername(@Param("email") String username);

}
