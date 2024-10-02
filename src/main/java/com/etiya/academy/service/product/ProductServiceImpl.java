package com.etiya.academy.service.product;

import com.etiya.academy.core.exception.type.BusinessException;
import com.etiya.academy.dto.product.CreateProductDto;
import com.etiya.academy.dto.product.ListProductDto;
import com.etiya.academy.entity.Product;
import com.etiya.academy.mapper.ProductMapper;
import com.etiya.academy.repository.ProductRepository;
import com.etiya.academy.rules.ProductBusinessRules;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
  private final ProductRepository productRepository;
  private final ProductBusinessRules productBusinessRules;

  @Override
  @Cacheable(value = "product", key = "#id") // product.1, product.2
  public ListProductDto getById(int id) {
    Product product = productRepository.findById(id).orElseThrow(() -> new BusinessException("Böyle bir ürün yok"));
    ProductMapper mapper = ProductMapper.INSTANCE;
    return mapper.productDtoFromProduct(product);
  }

  @Override
  @Cacheable(value = "products")
  public List<ListProductDto> getAll() {
    // başka bi servis url
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
  @CacheEvict(value = "products", allEntries = true)
  public void add(CreateProductDto createProductDto) {
    productBusinessRules.productWithSameNameShouldNotExist(createProductDto.getName());
    Product product = ProductMapper.INSTANCE.productFromCreateDto(createProductDto);
    productRepository.save(product);
  }
}
// loglama- ELK Stack elastic search-logstash-kibana  -> haftaya veri modelleme sonrası


// Mikroservis mimarisi
// Microservices