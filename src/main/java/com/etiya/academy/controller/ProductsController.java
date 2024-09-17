package com.etiya.academy.controller;

import com.etiya.academy.entity.Product;
import com.etiya.academy.service.ProductService;
import com.etiya.academy.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController
{
  private ProductService productService;

  public ProductsController(ProductService productService) {
    this.productService = productService;
  }

  //5 temel operasyon (CRUD)
   public List<Product> getAll() {
     // ... -> Veritabanındaki (in memory) tüm verileri listele..
     return productService.getAll();
   }

}
