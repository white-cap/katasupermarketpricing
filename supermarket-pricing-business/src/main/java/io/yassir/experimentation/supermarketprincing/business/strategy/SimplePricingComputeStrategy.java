package io.yassir.experimentation.supermarketprincing.business.strategy;

import io.yassir.experimentation.supermarketprincing.business.exception.PricingComputeException;
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
        validateUnit(pricingRequest);
        BigDecimal price =  pricingRequest.getProduct().getPriceByUnitType().getPrice();
        double unit = pricingRequest.getUnit();
        BigDecimal amount = calculateAmount(price,unit);
        return new PricingResponse(pricingRequest, amount);
    }
}
