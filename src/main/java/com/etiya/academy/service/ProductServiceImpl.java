package com.etiya.academy.service;

import com.etiya.academy.core.exception.type.BusinessException;
import com.etiya.academy.dto.product.CreateProductDto;
import com.etiya.academy.dto.product.ListProductDto;
import com.etiya.academy.entity.Product;
import com.etiya.academy.mapper.ProductMapper;
import com.etiya.academy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
  private final ProductRepository productRepository;
  @Override
  public List<ListProductDto> getAll() {
    // Business Logic -> Loglama, Auth, İş Kuralları, Validasyonlar
    // Veri Erişim -> DB'e git verileri oku "Select * from Products"
    // bla bla..
    return productRepository.getAll().stream()
      .map(product -> {
        return new ListProductDto(product.getId(), product.getName(), product.getUnitPrice());
      }).toList();
  }

  @Override
  public void add(CreateProductDto createProductDto) {
    // Validasyon -> Direkt verinin üzerinde farklı veri gerektirmeden yapılabilen kontrollerdir.
    // İş Kuralı -> genelde diğer verilerle karşılaştırma üzerine bir yapıdır. -> Aynı isimde bir ürün var mı?

    // halit@kodlama.io
    // Eposta adresi @ işareti içermelidir - 1 Validasyon
    // Aynı e posta ile bir üye bulunmamalıdır. - 2 İş Kuralı
    // Stok değeri 0'dan küçük esit ise sipariş verilemez.
    // 1 validasyon (verinin üstünde olan bir kullanım) (constraint kısıt)
    // 2 iş kuralı
    // Mapping
    // Manual mapping
    /*
    Product product = new Product();
    product.setId(random.nextInt(1,9999));
    product.setName(createProductDto.getName());
    product.setUnitPrice(createProductDto.getUnitPrice());
    product.setUnitsInStock(createProductDto.getUnitsInStock());
    */
    // Auto mapping

    boolean productWithSameName = productRepository.getAll()
            .stream()
            .anyMatch(product -> product.getName().equals(createProductDto.getName()));

    if(productWithSameName)
      throw new BusinessException("Böyle bir ürün zaten var.");

    Product product = ProductMapper.INSTANCE.productFromCreateDto(createProductDto);
    //product.setId(random.nextInt(1,99999));

    // String sql = "Select * from products";

    // repository.execute(sql);

    // Product product = "Select * from products where id = 1";
    // product.getName()

    productRepository.add(product);
  }
}
// 10.30



// Bireysel: Veri Modelleme nedir? Normalizasyon nedir? Araştırılıp medium yazısı yazma.

// Pair: Projeye Category entitysi oluşturma ve crud işlemlerini kodlama.
// Hali hazırdaki product entitysi için de validation ve iş kurallarının entegre edilmesi
// (Create-Update en az 3'er validasyon ve iş kuralı örneği)

// !!!
// Podman kurulumları tamamlanmalı. (POSTGRESQL,DBEAVER)
// Veri Modelleme ve SQL