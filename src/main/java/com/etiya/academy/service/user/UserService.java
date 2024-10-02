package com.etiya.academy.service.user;

import com.etiya.academy.dto.auth.LoginRequest;
import com.etiya.academy.dto.user.CreateUserRequest;

public interface UserService {
  void create(CreateUserRequest createUserRequest);
  String login(LoginRequest loginRequest);
}
