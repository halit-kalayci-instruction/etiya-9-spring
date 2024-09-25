package com.etiya.academy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

// Boilerplate -> Basmakalıp

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // bu bir orm classı
@Table(name="products") // veritabanında şu tabloyla eşleşecek
public class Product
{
  @Id // bu tablonun PK'sı bu field.
  @GeneratedValue(strategy = GenerationType.IDENTITY) // bu değer auto verilecek db tarafından.
  @Column(name="id") // tablodaki ismi id olan column'ı temsil eden property, eğer iki isim de aynı ise name'e gerek yok.
  private int id;

  @Column(name="name")
  private String name;


  @Column(name="price") // isimler farklı ise bir zorunluluktur.
  private BigDecimal unitPrice;

  @Column(name="stock") // isimler farklı ise bir zorunluluktur.
  private int unitsInStock;

  // İlişkiler.
  // FK'yı alan olarak eklemiyoruz

  // Her ürünün tek kategorisi var.
  // @{BuClass}To{DiğerClass}
  @ManyToOne
  @JoinColumn(name="categoryid")
  private Category category;
}
