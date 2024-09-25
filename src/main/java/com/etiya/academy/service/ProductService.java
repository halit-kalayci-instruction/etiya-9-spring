package com.etiya.academy.service;

import com.etiya.academy.dto.product.CreateProductDto;
import com.etiya.academy.dto.product.ListProductDto;
import com.etiya.academy.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService
{
   List<ListProductDto> getAll();
   List<ListProductDto> getByName(String name);
   List<ListProductDto> getByNameAndUnitPrice(String name, BigDecimal unitPrice);
   void add(CreateProductDto createProductDto);
}
