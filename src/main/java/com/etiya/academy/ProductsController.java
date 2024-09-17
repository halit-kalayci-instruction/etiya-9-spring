package com.etiya.academy;

import org.springframework.web.bind.annotation.*;

// Aspect - Attribute
// Annotation
@RestController
@RequestMapping("/api/products")
public class ProductsController
{
  // Controller => İstek-Cevap konfigürasyonunu kontrol eder.
  @GetMapping()
  public Product hello() {
    Product product = new Product(1, "Ürün 1", 500);
    return product;
  }
  @PostMapping
  public String goodBye(@RequestBody Product product) {
    // Console
    return "Gönderilen ürün idsi: " + product.getId();
  }
}

// JSON Parse -> Spring boot otomatize ediyor.

// API nedir? Restful api nedir?
// XML nedir?
// JSON Nedir?

// Bir medium yazısı hazırlayınız.