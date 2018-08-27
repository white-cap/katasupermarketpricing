package io.yassir.experimentation.supermarketprincing.business;

import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.PricingResponse;

import java.math.BigDecimal;
import java.util.function.Function;

public class SimplePricingComputeStrategy implements Function<PricingRequest, PricingResponse> {

    /**
     * Apply simple pricing behavior
     *
     * @param pricingRequest
     * @return PricingResponse
     */
    @Override
    public PricingResponse apply(PricingRequest pricingRequest) {
        BigDecimal price =  pricingRequest.getProduct().getPrice();
        double unit = pricingRequest.getUnit();
        return new PricingResponse(pricingRequest, price.multiply(BigDecimal.valueOf(unit)));
    }
}
