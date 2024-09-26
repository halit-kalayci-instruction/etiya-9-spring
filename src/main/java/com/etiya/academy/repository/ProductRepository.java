package com.etiya.academy.repository;

import com.etiya.academy.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product, Integer>
{
  // 1. Derived Method Naming
  // 2. JPQL
  // 3. Native SQL
  List<Product> findByNameLikeIgnoreCase(String name);

  Optional<Product> findByNameIgnoreCase(String name);

  @Query(value = "Select p from Product p " +
          "inner join p.category " +
          "where p.unitPrice > :unitPrice and lower(p.name) like concat('%', lower(:name), '%')", nativeQuery=false) // JPQL-SQL -- JPA + SQL
  List<Product> findByNameAndUnitPriceGreaterThan(String name, BigDecimal unitPrice);

  // named,positional parameters
  @Query(value = "Select * from products p where p.price > :unitPrice and lower(p.name) LIKE concat('%', lower(:name), '%')", nativeQuery=true) // JPQL-SQL -- JPA + SQL
  List<Product> findByNameAndUnitPriceGreaterThanSql(String name, BigDecimal unitPrice);
}
