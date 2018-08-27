package io.yassir.experimentation.supermarketprincing.business.strategy;

import io.yassir.experimentation.supermarketprincing.business.io.yassir.experimentation.supermarketprincing.business.exception.PricingComputeException;
import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.PricingResponse;

import java.math.BigDecimal;

/**
 * @author yassir
 * The same as Function 1.8 JDK but allow us to throws exceptions
 */
@FunctionalInterface
public interface CustomFunctionPricing {

    /**
     * Applies this function to the given argument.
     *
     * @param pricingRequest the function argument
     * @return the function result
     * @throws Exception
     */
    PricingResponse apply(PricingRequest pricingRequest) throws PricingComputeException;

    /**
     * validator of unit must be superior of zero
     *
     * @param pricingRequest pricing Request
     * @throws PricingComputeException
     */
    default void validateUnit(PricingRequest pricingRequest) throws PricingComputeException {
        if (pricingRequest.getUnit() == 0)
            throw new PricingComputeException("can't handle 0 unit");
    }

    /**
     * classic calculator of price
     *
     * @param price price
     * @param unit  unit
     * @return
     */
    default BigDecimal calculateAmount(BigDecimal price, double unit) {
        BigDecimal amount;
        amount = price.multiply(BigDecimal.valueOf(unit));
        return amount;
    }

}
