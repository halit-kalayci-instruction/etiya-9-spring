package com.etiya.academy;

import org.springframework.web.bind.annotation.*;

// Aspect - Attribute
// Annotation
@RestController
@RequestMapping("/api/products")
public class ProductsController
{
  // Controller => İstek-Cevap konfigürasyonunu kontrol eder.
  @GetMapping("{name}")
  public String hello(@PathVariable String name) {
    return "Hello " + name;
  }
  @PostMapping
  public String goodBye() {
    // Console
    return "Good Bye";
  }

}
// API nedir? Restful api nedir?
// XML nedir?
// JSON Nedir?

// Bir medium yazısı hazırlayınız.