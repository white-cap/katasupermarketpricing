package io.yassir.experimentation.supermarketprincing.model;

import io.yassir.experimentation.supermarketprincing.model.Enum.UnitType;
import lombok.Getter;

@Getter
public class CashierRequest {

    private Product product;
    private UnitType unitType;
    private double unit;

    public CashierRequest(Product product, UnitType unitType, double unit) {
        this.product = product;
        this.unitType = unitType;
        this.unit = unit;
    }
}
