package com.etiya.academy.repository;

import com.etiya.academy.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class InMemoryProductRepository implements ProductRepository
{
  List<Product> products = new ArrayList<>();

  @Override
  public List<Product> getAll() {
    return products;
  }

  @Override
  public void add(Product product) {
    products.add(product);
  }
}
