package io.yassir.experimentation.supermarketprincing.model;

import io.yassir.experimentation.supermarketprincing.model.Enum.UnitType;
import lombok.Getter;

import java.util.Objects;

@Getter
public class PricingRequest {

    private final Product product;
    private final UnitType unitType;
    private final double unit;

    public PricingRequest(Product product, UnitType unitType, double unit) {
        this.product = product;
        this.unitType = unitType;
        this.unit = unit;
    }

    public PricingRequest(Product product, double unit) {
        this.product = product;
        this.unitType = UnitType.UNITARY;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PricingRequest)) return false;
        PricingRequest that = (PricingRequest) o;
        return Double.compare(that.unit, unit) == 0 &&
                Objects.equals(product, that.product) &&
                unitType == that.unitType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, unitType, unit);
    }

    @Override
    public String toString() {
        return "PricingRequest{" +
                "product=" + product +
                ", unitType=" + unitType +
                ", unit=" + unit +
                '}';
    }
}
