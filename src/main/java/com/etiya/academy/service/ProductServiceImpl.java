package com.etiya.academy.service;

import com.etiya.academy.core.exception.type.BusinessException;
import com.etiya.academy.dto.product.CreateProductDto;
import com.etiya.academy.dto.product.ListProductDto;
import com.etiya.academy.entity.Product;
import com.etiya.academy.mapper.ProductMapper;
import com.etiya.academy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
  private final ProductRepository productRepository;
  @Override
  public List<ListProductDto> getAll() {
    var products = productRepository.findAll();
    return products.stream()
      .map(product -> {
        return new ListProductDto(product.getId(), product.getName(), product.getUnitPrice());
      }).toList();
  }

  @Override
  public List<ListProductDto> getByName(String name) {
    List<Product> products = productRepository.findByNameLikeIgnoreCase("%"+name+"%");
    return products.stream()
            .map(product -> {
              return new ListProductDto(product.getId(), product.getName(), product.getUnitPrice());
            }).toList();
  }

  @Override
  public List<ListProductDto> getByNameAndUnitPrice(String name, BigDecimal unitPrice) {
    List<Product> products = productRepository.findByNameAndUnitPriceGreaterThan(name, unitPrice);
    return products.stream()
            .map(product -> {
              return new ListProductDto(product.getId(), product.getName(), product.getUnitPrice());
            }).toList();
  }

  @Override
  public void add(CreateProductDto createProductDto) {
    boolean productWithSameName = productRepository.findAll()
            .stream()
            .anyMatch(product -> product.getName().equals(createProductDto.getName()));

    if(productWithSameName)
      throw new BusinessException("Böyle bir ürün zaten var.");

    Product product = ProductMapper.INSTANCE.productFromCreateDto(createProductDto);
    productRepository.save(product);
  }
}