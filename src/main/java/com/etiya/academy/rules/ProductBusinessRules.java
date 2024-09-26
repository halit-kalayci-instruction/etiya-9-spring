package com.etiya.academy.rules;

import com.etiya.academy.core.exception.type.BusinessException;
import com.etiya.academy.entity.Product;
import com.etiya.academy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProductBusinessRules
{
  private final ProductRepository productRepository;

  public void productWithSameNameShouldNotExist(String name) {
    Optional<Product> product = productRepository
            .findByNameIgnoreCase(name);
    if(product.isPresent())
      throw new BusinessException("Böyle bir ürün zaten var.");
  }

  public void productWithIdShouldExist(int id)
  {
    Optional<Product> product = productRepository.findById(id);
    if(product.isEmpty())
      throw new BusinessException("Böyle bir ürün yok.");
  }
}
// cacheleme -> redis
// -d -> detached