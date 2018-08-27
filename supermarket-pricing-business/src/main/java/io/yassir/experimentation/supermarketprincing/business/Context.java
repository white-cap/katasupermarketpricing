package io.yassir.experimentation.supermarketprincing.business;

import io.yassir.experimentation.supermarketprincing.business.io.yassir.experimentation.supermarketprincing.business.exception.PricingComputeException;
import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.PricingResponse;

/**
 * @author yassir
 *
 * Context strategy
 */
public class Context {

    private CustomFunction<PricingRequest, PricingResponse> strategy;

    /**
     * Constructor context
     * @param strategy the strategy
     */
    public Context(CustomFunction strategy){
        this.strategy = strategy;
    }

    /**
     * Compute pricing
     * @param pricingRequest
     * @return PricingResponse
     */
    public PricingResponse compute(PricingRequest pricingRequest) throws PricingComputeException {
        return strategy.apply(pricingRequest);
    }
}
