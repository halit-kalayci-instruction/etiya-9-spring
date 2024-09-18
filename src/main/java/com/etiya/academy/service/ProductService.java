package com.etiya.academy.service;

import com.etiya.academy.dto.product.CreateProductDto;
import com.etiya.academy.dto.product.ListProductDto;
import com.etiya.academy.entity.Product;

import java.util.List;

public interface ProductService
{
   // TODO: DTO!
   List<ListProductDto> getAll();
   void add(CreateProductDto createProductDto);
}
