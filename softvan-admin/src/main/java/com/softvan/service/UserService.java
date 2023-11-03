package com.softvan.service;

import com.softvan.Repository.RoleRepository;
import com.softvan.Repository.UserRepsitory;


import com.softvan.dto.request.LoginRequestDto;
import com.softvan.dto.request.UserRegisterRequest;
import com.softvan.entity.RoleEntity;
import com.softvan.entity.UserEntity;
import com.softvan.exception.UserAlreadyExistsException;
import com.softvan.exception.UserNotFoundException;
import com.softvan.jwt.JwtTokenProvider;
import com.softvan.softrepoexception.RoleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService
{


    private static final String PASSWORD_IS_NOT_CORRECT = "password is not correct";
    private final UserRepsitory userRepsitory;
       
    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    private final RoleRepository roleRepository;



  public String loginUser(LoginRequestDto userLoginRequest)  {

      UserEntity user = this.userRepsitory.getUserByUsername(userLoginRequest.getEmail()).orElseThrow(() -> new UserNotFoundException("User Not Found For This Username"));
      String role = user.getRole().getName();
      if (!passwordEncoder.matches(userLoginRequest.getPassword(), user.getPassword())) {
          throw new UserNotFoundException(PASSWORD_IS_NOT_CORRECT);
      }
          return  this.jwtTokenProvider.createToken(user.getEmail(),user.getId(),role);
 }

 public String registerUser(UserRegisterRequest userRegisterRequest){
     RoleEntity roleEntity = this.roleRepository.findById(userRegisterRequest.getRoleId()).orElseThrow(() -> new RoleNotFoundException("Role is not available for this id"));

     Optional<UserEntity> userByUsername = this.userRepsitory.getUserByUsername(userRegisterRequest.getEmail());

      if(userByUsername.isPresent()){
          throw new UserAlreadyExistsException("User already  exists");
      }
     UserEntity user = new UserEntity();
      user.setEmail(userRegisterRequest.getEmail());
      user.setFirstName(userRegisterRequest.getFirstName());
      user.setLastName(userRegisterRequest.getLastName());
      user.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
      user.setRole(roleEntity);
     UserEntity savedUser = this.userRepsitory.save(user);
     return savedUser.getEmail();
 }
 
}
