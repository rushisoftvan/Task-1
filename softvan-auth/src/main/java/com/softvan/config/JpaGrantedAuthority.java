package com.softvan.config;

import com.softvan.entity.RoleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public class JpaGrantedAuthority implements GrantedAuthority {

    private final RoleEntity roleEntity;
    @Override
    public String getAuthority() {
      return this.roleEntity.getName();
    }
}
