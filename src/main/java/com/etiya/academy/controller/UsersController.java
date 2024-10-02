package com.etiya.academy.controller;

import com.etiya.academy.dto.auth.LoginRequest;
import com.etiya.academy.dto.user.CreateUserRequest;
import com.etiya.academy.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
public class UsersController {
  private final UserService userService;


  @PostMapping()
  public ResponseEntity create(@RequestBody CreateUserRequest createUserRequest) {
    userService.create(createUserRequest);
    return ResponseEntity.ok("Kullanıcı eklendi.");
  }

  @PostMapping("login")
  public ResponseEntity login(@RequestBody LoginRequest loginRequest)
  {
    String token = userService.login(loginRequest);
    return ResponseEntity.ok(token);
  }
}
