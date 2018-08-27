package io.yassir.experimentation.supermarketprincing.business.strategy;

import io.yassir.experimentation.supermarketprincing.business.io.yassir.experimentation.supermarketprincing.business.exception.PricingComputeException;
import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.PricingResponse;

import java.math.BigDecimal;

/**
 * @author yassir
 * price by unit impl strategy
 */
public class priceByUnitComputeStrategy implements CustomFunctionPricing {


    /**
     * Apply sprice by unit behavior
     * @param pricingRequest request object (DTO)
     * @return PricingResponse
     * @throws PricingComputeException Exception on handle pricing compute
     */
    @Override
    public PricingResponse apply(PricingRequest pricingRequest) throws PricingComputeException {
        this.validateUnit(pricingRequest);
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal price =  pricingRequest.getProduct().getPriceByUnitType().getPrice();
        double unit = pricingRequest.getUnit();
        if(pricingRequest.getProduct().getPriceByUnitType().getUnitType() == pricingRequest.getUnitType()){
            amount = price.multiply(BigDecimal.valueOf(unit));
        }else{
            //TODO product on pound and request on ounce
        }
        return new PricingResponse(pricingRequest,amount);
    }
}
