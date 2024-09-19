package com.etiya.academy.controller;

import com.etiya.academy.dto.product.CreateProductDto;
import com.etiya.academy.dto.product.ListProductDto;
import com.etiya.academy.entity.Product;
import com.etiya.academy.service.ProductService;
import com.etiya.academy.service.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor // -> final olarak işaretlenen tüm arg. içeren ctor.
public class ProductsController
{
   private final ProductService productService;
   //5 temel operasyon (CRUD)

   @GetMapping()
   public ResponseEntity<List<ListProductDto>> getAll() {
     // ... -> Veritabanındaki (in memory) tüm verileri listele..
     // Product -> ProductListDto
     return ResponseEntity.ok(productService.getAll());
   }
   @PostMapping
   public ResponseEntity<Void> add(@RequestBody @Valid CreateProductDto createProductDto)
   {
     // CreateProductDto -> Product
     productService.add(createProductDto);
     return new ResponseEntity<Void>(HttpStatus.CREATED);
   }
   // Default status code => 200 (BAŞARILI)
}

// DTO -> Data Transfer Object