package com.etiya.academy.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
public class CreateProductDto implements Serializable
{
  @NotNull // null
  @NotBlank // ""
  private String name;


  @NotNull
  @PositiveOrZero
  private int stock;

  @NotNull
  private BigDecimal unitPrice;

  @NotNull
  @Positive
  private int categoryId;
}
