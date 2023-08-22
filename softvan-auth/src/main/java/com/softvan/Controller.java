package com.softvan;

import com.softvan.Repository.UserRepsitory;
import com.softvan.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@Slf4j
public  class Controller {

    @Autowired
    private UserRepsitory userRepsitory;

   @GetMapping("users/{name}")
    public void m1(@PathVariable("name") String name){
        Optional<UserEntity> userByUsername = this.userRepsitory.getUserByUsername(name);
        if(userByUsername.isPresent()){
            log.info("email {}",userByUsername.get().getEmail());
        }
    }
}
