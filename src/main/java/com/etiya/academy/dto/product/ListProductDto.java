package com.etiya.academy.dto.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ListProductDto implements Serializable
{
  private int id;
  private String name;
  private BigDecimal unitPrice;
}
