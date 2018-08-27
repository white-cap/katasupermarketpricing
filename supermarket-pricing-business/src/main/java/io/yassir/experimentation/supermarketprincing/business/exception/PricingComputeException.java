package io.yassir.experimentation.supermarketprincing.business.exception;

/**
 * Custom Exception for PricingComputeException
 */
public class PricingComputeException extends RuntimeException{

    public PricingComputeException(String alert){
        super(alert);
    }
}
