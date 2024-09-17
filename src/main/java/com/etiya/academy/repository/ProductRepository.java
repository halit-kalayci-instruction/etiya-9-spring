package com.etiya.academy.repository;

import com.etiya.academy.entity.Product;

import java.util.List;

public interface ProductRepository
{
  List<Product> getAll();
  void add(Product product);
}
