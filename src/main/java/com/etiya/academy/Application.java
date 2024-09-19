package com.etiya.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestControllerAdvice
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  // Fallback method
  // Exception handler -> Uygulamada belirlediğim türdeki ex. nerede fırlarsa fırlasın. Bu methoda gelsin.
  // Ex. Handler methodların 1. parametresi oto. olarak fırlayan ex. doldurulur.
  @ExceptionHandler({ MethodArgumentNotValidException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String,String> handleValidationException(MethodArgumentNotValidException exception) {
    Map<String,String> errors = new HashMap<>();

    for (FieldError error:
         exception.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }
    // stock, must be greater than or equal to 0
    // name, must not be empty
    return errors;
  }
}
