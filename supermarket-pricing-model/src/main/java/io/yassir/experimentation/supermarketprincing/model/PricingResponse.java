package io.yassir.experimentation.supermarketprincing.model;

import io.yassir.experimentation.supermarketprincing.model.Enum.UnitType;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
public class PricingResponse{

    private final PricingRequest pricingRequest;
    private final BigDecimal amount;


    public PricingResponse(final PricingRequest pricingRequest, final BigDecimal amount) {
        this.pricingRequest = pricingRequest;
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PricingResponse)) return false;
        PricingResponse that = (PricingResponse) o;
        return Objects.equals(pricingRequest, that.pricingRequest) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pricingRequest, amount);
    }

    @Override
    public String toString() {
        return "PricingResponse{" +
                "pricingRequest=" + pricingRequest +
                ", amount=" + amount +
                '}';
    }
}
