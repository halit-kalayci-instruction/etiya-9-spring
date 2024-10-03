package com.etiya.academy.service.auth;

import com.etiya.academy.dto.auth.LoginRequest;
import com.etiya.academy.dto.user.CreateUserRequest;

public interface AuthService {
  String login(LoginRequest loginRequest);
  String register(CreateUserRequest createUserRequest);
}
