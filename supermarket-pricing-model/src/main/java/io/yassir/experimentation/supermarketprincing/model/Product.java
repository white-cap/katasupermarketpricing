package io.yassir.experimentation.supermarketprincing.model;

import io.yassir.experimentation.supermarketprincing.model.Enum.PricingType;
import lombok.Getter;

@Getter
public class Product {

    private final String ref;
    private final double price;
    private final PricingType pricingType;

    public Product(String name, double price, PricingType pricingType) {
        this.ref = name;
        this.price = price;
        this.pricingType = pricingType;
    }


}
