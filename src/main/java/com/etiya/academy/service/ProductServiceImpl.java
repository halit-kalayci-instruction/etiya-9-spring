package com.etiya.academy.service;

import com.etiya.academy.entity.Product;
import com.etiya.academy.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
  private final ProductRepository productRepository;
  @Override
  public List<Product> getAll() {
    // Business Logic -> Loglama, Auth, İş Kuralları, Validasyonlar
    // Veri Erişim -> DB'e git verileri oku "Select * from Products"
    // bla bla..
    return productRepository.getAll();
  }

  @Override
  public void add(Product product) {
    // Validasyon -> Direkt verinin üzerinde farklı veri gerektirmeden yapılabilen kontrollerdir.
    // İş Kuralı -> genelde diğer verilerle karşılaştırma üzerine bir yapıdır. -> Aynı isimde bir ürün var mı?

    // halit@kodlama.io
    // Eposta adresi @ işareti içermelidir - 1 Validasyon
    // Aynı e posta ile bir üye bulunmamalıdır. - 2 İş Kuralı
    if(product.getName().length() < 3)
      throw new RuntimeException("Ürün ismi 3 haneden kısa olamaz.");


    // Stok değeri 0'dan küçük esit ise sipariş verilemez.
    // 1 validasyon (verinin üstünde olan bir kullanım) (constraint kısıt)
    // 2 iş kuralı


    productRepository.add(product);
  }
}
