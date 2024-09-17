package com.etiya.academy.controller;

import com.etiya.academy.entity.Product;
import com.etiya.academy.service.ProductService;
import com.etiya.academy.service.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor // -> final olarak işaretlenen tüm arg. içeren ctor.
public class ProductsController
{
   private final ProductService productService;
   //5 temel operasyon (CRUD)

   @GetMapping()
   public List<Product> getAll() {
     // ... -> Veritabanındaki (in memory) tüm verileri listele..
     return productService.getAll();
   }
   @PostMapping
   public void add(@RequestBody Product product)
   {
     productService.add(product);
   }

   // ProductController'daki 5 temel operasyonu kodlamak
   // getall -> 200
   // getbyid -> eğer id ile bir veriye rastlanmaz ise, status code 404 olsun.
   // update-delete -> 200
   // add -> eğer başarılı ise status code 201 dönsün.
}
