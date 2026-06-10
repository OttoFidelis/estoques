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

  public Product(
    ProductName name,
    ProductDescription description,
    ProductSku sku,
    ProductQuantity quantity,
    ProductQuantity minQuantity
  ) {
    this.name = name;
    this.description = description;
    this.sku = sku;
    this.minQuantity = minQuantity;
    if(quantity.getValue() >= minQuantity.getValue())this.quantity = quantity;
    else throw new IllegalArgumentException("Product quantity cannot be less than minimum quantity");
  }

  public void setQuantity(ProductQuantity quantity) {
    if(quantity.getValue() < this.minQuantity.getValue()) {
      throw new IllegalArgumentException("Product quantity cannot be less than minimum quantity");
    }
    this.quantity = quantity;
  }
}
