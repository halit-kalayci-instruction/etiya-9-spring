package com.etiya.academy.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="categories")
@Entity
public class Category
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id")
  private int id;

  @Column(name="name")
  private String name;

  // Her kategorinin çok ürünü var.
  // mappedBy => diğer entity içinde şu prop ile maple
  @OneToMany(mappedBy = "category")
  private List<Product> products;
}
