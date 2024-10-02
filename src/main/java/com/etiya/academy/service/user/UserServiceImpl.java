package com.etiya.academy.service.user;

import com.etiya.academy.dto.user.CreateUserRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{

  @Override
  public void create(CreateUserRequest createUserRequest) {
    // ...
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    // HashingHelper..
    // Hashing
    String hashedPassword = passwordEncoder.encode(createUserRequest.getPassword());
    // 10:45
    String x = "";
  }
}
