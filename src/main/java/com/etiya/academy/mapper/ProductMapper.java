package com.etiya.academy.mapper;

import com.etiya.academy.dto.product.CreateProductDto;
import com.etiya.academy.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper
{
  ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
  // Target methodIsmi(Source s);
  // Target -> Mapping işleminde elde etmeyi hedeflediğim class
  // Source -> Target'a ulaşmak için kullandığım kaynak.
  // Eğer isimler aynı ise ekstra konfigürasyon gerekmez
  @Mapping(source = "stock", target = "unitsInStock")
  Product productFromCreateDto(CreateProductDto dto);
}
