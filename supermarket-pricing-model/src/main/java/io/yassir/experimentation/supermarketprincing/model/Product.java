package io.yassir.experimentation.supermarketprincing.model;

import io.yassir.experimentation.supermarketprincing.model.Enum.DiscountType;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Product {

    private final String ref;
    private final PriceByUnitType priceByUnitType;
    private final DiscountType discountType;

    public Product(String ref, PriceByUnitType priceByUnitType, DiscountType pricingType) {
        this.ref = ref;
        this.priceByUnitType = priceByUnitType;
        this.discountType = pricingType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getRef(), product.getRef()) &&
                Objects.equals(getPriceByUnitType(), product.getPriceByUnitType()) &&
                getDiscountType() == product.getDiscountType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRef(), getPriceByUnitType(), getDiscountType());
    }

    @Override
    public String toString() {
        return "Product{" +
                "ref='" + ref + '\'' +
                ", priceByUnitType=" + priceByUnitType +
                ", discountType=" + discountType +
                '}';
    }
}
