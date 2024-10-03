package com.etiya.academy.service.auth;

import com.etiya.academy.core.exception.type.BusinessException;
import com.etiya.academy.core.services.JwtService;
import com.etiya.academy.dto.auth.LoginRequest;
import com.etiya.academy.dto.user.CreateUserRequest;
import com.etiya.academy.entity.User;
import com.etiya.academy.mapper.UserMapper;
import com.etiya.academy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService
{
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final UserService userService;
  private final UserMapper userMapper;
  @Override
  public String login(LoginRequest loginRequest) {
    UserDetails user = userService.loadUserByUsername(loginRequest.getEmail());
    boolean passwordMatching = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
    if(!passwordMatching)
      throw new BusinessException("E-posta veya şifre hatalı.");

    // ... JWT ÜRET
    return jwtService.generateToken(user.getUsername());
  }


  @Override
  public String register(CreateUserRequest createUserRequest) {
    User userToAdd = userMapper.userFromCreateRequest(createUserRequest);
    User user = userService.create(userToAdd);
    return jwtService.generateToken(user.getUsername());
  }
}
