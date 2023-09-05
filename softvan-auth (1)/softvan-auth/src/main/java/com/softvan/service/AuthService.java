package com.softvan.service;

import com.softvan.dto.request.LoginRequestDto;

public interface AuthService {

    String authenticate(LoginRequestDto loginRequestDto);

}
