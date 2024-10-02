package com.etiya.academy.mapper;

import com.etiya.academy.dto.user.CreateUserRequest;
import com.etiya.academy.entity.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper // bağımlılık enjeksiyonu
{
  @Autowired
  protected PasswordEncoder passwordEncoder;

  // TODO: Check auto hash
  @Mapping(target="password", source = "password", qualifiedByName = "hashPassword")
  public abstract User userFromCreateRequest(CreateUserRequest request);

  @Named("hashPassword")
  protected String hashPassword(String password)
  {
    return passwordEncoder.encode(password);
  }

}
