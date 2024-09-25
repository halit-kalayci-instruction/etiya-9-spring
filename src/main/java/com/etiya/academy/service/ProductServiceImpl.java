package com.etiya.academy.service;

import com.etiya.academy.core.exception.type.BusinessException;
import com.etiya.academy.dto.product.CreateProductDto;
import com.etiya.academy.dto.product.ListProductDto;
import com.etiya.academy.entity.Product;
import com.etiya.academy.mapper.ProductMapper;
import com.etiya.academy.repository.ProductRepository;
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
  private final EntityManager entityManager;
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

    // SQL INJECTION'A ÇOK DİKKAT EDİLMELİ
    String sql = "Select p from Product p inner join p.category " +
            "where p.unitPrice > :unitPrice and lower(p.name) " +
            "like concat('%', lower(:name), '%')";

    Query query = entityManager.createQuery(sql);
    query.setParameter("unitPrice", unitPrice);
    query.setParameter("name", name);
    List result = query.getResultList();

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