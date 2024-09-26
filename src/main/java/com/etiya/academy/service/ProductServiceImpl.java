package com.etiya.academy.service;

import com.etiya.academy.core.exception.type.BusinessException;
import com.etiya.academy.dto.product.CreateProductDto;
import com.etiya.academy.dto.product.ListProductDto;
import com.etiya.academy.entity.Product;
import com.etiya.academy.mapper.ProductMapper;
import com.etiya.academy.repository.ProductRepository;
import com.etiya.academy.rules.ProductBusinessRules;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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
  private final ProductBusinessRules productBusinessRules;
  @Override
  public List<ListProductDto> getAll() {
    List<Product> products = productRepository.findAll();
    ProductMapper productMapper = ProductMapper.INSTANCE;
    return productMapper.productDtoListFromProductList(products);
  }

  @Override
  public List<ListProductDto> getByName(String name) {
    List<Product> products = productRepository.findByNameLikeIgnoreCase("%"+name+"%");
    ProductMapper productMapper = ProductMapper.INSTANCE;
    return productMapper.productDtoListFromProductList(products);
  }

  @Override
  public List<ListProductDto> getByNameAndUnitPrice(String name, BigDecimal unitPrice) {
    List<Product> products = productRepository.findByNameAndUnitPriceGreaterThan(name, unitPrice);
    ProductMapper productMapper = ProductMapper.INSTANCE;
    return productMapper.productDtoListFromProductList(products);
  }

  @Override
  public void add(CreateProductDto createProductDto) {
    productBusinessRules.productWithSameNameShouldNotExist(createProductDto.getName());
    Product product = ProductMapper.INSTANCE.productFromCreateDto(createProductDto);
    productRepository.save(product);
  }
}
