package com.etiya.academy.service.user;

import com.etiya.academy.core.exception.type.BusinessException;
import com.etiya.academy.core.services.JwtService;
import com.etiya.academy.dto.auth.LoginRequest;
import com.etiya.academy.dto.user.CreateUserRequest;
import com.etiya.academy.entity.User;
import com.etiya.academy.mapper.UserMapper;
import com.etiya.academy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{
  private final UserRepository userRepository;
  @Override
  public User create(User user) {
    userRepository.save(user);
    return user;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return userRepository.findByEmailIgnoreCase(username)
            .orElseThrow(() -> new UsernameNotFoundException(""));
  }
}
