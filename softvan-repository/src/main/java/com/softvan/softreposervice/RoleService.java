package com.softvan.softreposervice;

import com.softvan.Repository.RoleRepository;
import com.softvan.entity.RoleEntity;
import com.softvan.softrepoexception.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleEntity getRoleById(Integer id){
       return this.roleRepository.findById(id).orElseThrow(()-> new RoleNotFoundException("ROLE IS NOT FOUND FOR THIS ROLE ID"));
    }
}
