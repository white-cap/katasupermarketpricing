package io.yassir.experimentation.supermarketprincing.business;

import io.yassir.experimentation.supermarketprincing.business.io.yassir.experimentation.supermarketprincing.business.exception.PricingComputeException;
import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.PricingResponse;

import java.math.BigDecimal;

/**
 * @author yassir
 * simple pricing impl strategy
 */
public class SimplePricingComputeStrategy implements CustomFunctionPricing {


    /**
     * Apply simple pricing behavior
     * @param pricingRequest request object (DTO)
     * @return PricingResponse
     * @throws PricingComputeException Exception on handle pricing compute
     */
    @Override
    public PricingResponse apply(PricingRequest pricingRequest) throws PricingComputeException {
        this.validateUnit(pricingRequest);
        BigDecimal price =  pricingRequest.getProduct().getPrice();
        double unit = pricingRequest.getUnit();
        return new PricingResponse(pricingRequest, price.multiply(BigDecimal.valueOf(unit)));
    }
}
