package com.etiya.academy.mapper;

import com.etiya.academy.dto.product.CreateProductDto;
import com.etiya.academy.dto.product.ListProductDto;
import com.etiya.academy.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper
{
  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
  @Mapping(source = "stock", target = "unitsInStock")
  @Mapping(source="categoryId", target = "category.id")
  Product productFromCreateDto(CreateProductDto dto);

  ListProductDto productDtoFromProduct(Product product);
  List<ListProductDto> productDtoListFromProductList(List<Product> products);
}

// Authentication -> Kimlik doğrulama (Giriş yapıldı mı?)
// Authorization -> Rol doğrulama (Giriş yapan kullanıcı bu işlemi yapmaya yetkili mi?)