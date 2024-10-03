package com.etiya.academy.core.filters;

import com.etiya.academy.core.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  @Override
  protected void doFilterInternal(HttpServletRequest request,
                                  HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String jwtHeader = request.getHeader("Authorization");

    if(jwtHeader != null)
    {
      // JWT'i doğrula
      String jwt = jwtHeader.substring(7); // bearer abc

      Boolean isTokenValid = jwtService.validateToken(jwt);

      if(isTokenValid)
      {
         // Spring Security'e giriş yapıldığını haber ver.

        // TODO: roller
        // - Spring Security Boilerplate
        UsernamePasswordAuthenticationToken token = new
                UsernamePasswordAuthenticationToken(jwtService.extractUsername(jwt), null, null);

        token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(token);
        // -
      }
    }

    filterChain.doFilter(request,response); // görevi sonraki zincir halkasına aktar.
  }
}
