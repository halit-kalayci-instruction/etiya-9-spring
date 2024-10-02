package com.etiya.academy.controller;

import com.etiya.academy.dto.product.CreateProductDto;
import com.etiya.academy.dto.product.ListProductDto;
import com.etiya.academy.service.product.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
  @GetMapping("{name}")
  public ResponseEntity<List<ListProductDto>> getByName(@PathVariable String name)
  {
    return ResponseEntity.ok(productService.getByName(name));
  }
  @GetMapping("name-price")
  public ResponseEntity<List<ListProductDto>> getByNameAndPrice(@RequestParam String name, @RequestParam BigDecimal unitPrice)
  {
    return ResponseEntity.ok(productService.getByNameAndUnitPrice(name,unitPrice));
  }
  @GetMapping("id")
  public ResponseEntity<ListProductDto> getById(@RequestParam int id)
  {
    return ResponseEntity.ok(productService.getById(id));
  }

}

// DTO -> Data Transfer Object