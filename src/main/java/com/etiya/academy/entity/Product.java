package com.etiya.academy.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Boilerplate -> Basmakalıp

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product
{
  private int id;
  private String name;
  private double unitPrice;
  private int unitsInStock;
}
