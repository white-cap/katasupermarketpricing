package io.yassir.experimentation.supermarketprincing.model;

import io.yassir.experimentation.supermarketprincing.model.Enum.UnitType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class PriceByUnitType {

    private final BigDecimal price;
    private final UnitType unitType;

    public PriceByUnitType(BigDecimal price, UnitType unitType) {
        this.price = price;
        this.unitType = unitType;
    }

    public PriceByUnitType(BigDecimal price) {
        this.price = price;
        this.unitType = UnitType.UNITARY;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PriceByUnitType)) return false;
        PriceByUnitType that = (PriceByUnitType) o;
        return Objects.equals(getPrice(), that.getPrice()) &&
                getUnitType() == that.getUnitType();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getUnitType());
    }

    @Override
    public String toString() {
        return "PriceByUnitType{" +
                "price=" + price +
                ", unitType=" + unitType +
                '}';
    }
}
