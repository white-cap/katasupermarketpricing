package io.yassir.experimentation.supermarketprincing.business.strategy;

import io.yassir.experimentation.supermarketprincing.business.exception.PricingComputeException;
import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.PricingResponse;

/**
 * @author yassir
 * <p>
 * Context strategy
 */
public class Context {

    private CustomFunctionPricing strategy;

    /**
     * Constructor context
     *
     * @param strategy the strategy
     */
    public Context(CustomFunctionPricing strategy) {
        this.strategy = strategy;
    }

    /**
     * Compute pricing
     *
     * @param pricingRequest
     * @return PricingResponse
     */
    public PricingResponse compute(PricingRequest pricingRequest) throws PricingComputeException {
        return strategy.apply(pricingRequest);
    }
}
