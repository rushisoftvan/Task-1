package com.softvan.service;

import com.softvan.Repository.UserRepsitory;
import com.softvan.config.JpaGrantedAuthority;
import com.softvan.config.MyUserDetails;
import com.softvan.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepsitory userRepsitory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepsitory.getUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("user does not exixts"));

        JpaGrantedAuthority jpaGrantedAuthority = new JpaGrantedAuthority(userEntity.getRole());
        log.info("loadUserByUsername() >>>>>");
        return new MyUserDetails(userEntity, List.of(jpaGrantedAuthority));
    }


    @GetMapping("/test")
    public ResponseEntity<String> test(){
       return  ResponseEntity.ok("test");
    }
}
