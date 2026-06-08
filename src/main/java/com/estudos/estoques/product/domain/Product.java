package com.estudos.estoques.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

  private ProductId id;
  private ProductName name;
  private ProductDescription description;
  private ProductSku sku;
  private ProductQuantity quantity;
  private ProductQuantity minQuantity;
}
