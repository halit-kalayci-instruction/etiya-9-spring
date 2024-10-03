package com.etiya.academy.service.user;

import com.etiya.academy.dto.auth.LoginRequest;
import com.etiya.academy.dto.user.CreateUserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
  void create(CreateUserRequest createUserRequest);
  String login(LoginRequest loginRequest);
}
