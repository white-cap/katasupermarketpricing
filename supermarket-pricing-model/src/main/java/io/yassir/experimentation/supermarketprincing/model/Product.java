package io.yassir.experimentation.supermarketprincing.model;

import io.yassir.experimentation.supermarketprincing.model.Enum.DiscountType;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Product {

    private final String ref;
    private final BigDecimal price;
    private final DiscountType discountType;

    public Product(String name, BigDecimal price, DiscountType pricingType) {
        this.ref = name;
        this.price = price;
        this.discountType = pricingType;
    }


}
