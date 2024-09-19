package com.etiya.academy.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDto
{
  @NotNull // null
  @NotBlank // ""
  private String name;

  @NotNull
  @Positive
  private double unitPrice;

  @NotNull
  @PositiveOrZero
  private int stock;
}
