package io.yassir.experimentation.supermarketprincing.model;

import io.yassir.experimentation.supermarketprincing.model.Enum.DiscountType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class Product {

    private final String ref;
    private final BigDecimal price;
    private final DiscountType discountType;

    public Product(String ref, BigDecimal price, DiscountType pricingType) {
        this.ref = ref;
        this.price = price;
        this.discountType = pricingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(ref, product.ref) &&
                Objects.equals(price, product.price) &&
                discountType == product.discountType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ref, price, discountType);
    }

    @Override
    public String toString() {
        return "Product{" +
                "ref='" + ref + '\'' +
                ", price=" + price +
                ", discountType=" + discountType +
                '}';
    }
}
