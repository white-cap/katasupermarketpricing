package io.yassir.experimentation.supermarketprincing.business;

import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.PricingResponse;

import java.util.function.Function;

/**
 * @author yassir
 *
 * Context strategy
 */
public class Context {

    private Function<PricingRequest, PricingResponse> strategy;

    /**
     * Constructor context
     * @param strategy
     */
    public Context(Function strategy){
        this.strategy = strategy;
    }

    /**
     * Compute pricing
     * @param pricingRequest
     * @return PricingResponse
     */
    public PricingResponse compute(PricingRequest pricingRequest){
        return strategy.apply(pricingRequest);
    }
}
