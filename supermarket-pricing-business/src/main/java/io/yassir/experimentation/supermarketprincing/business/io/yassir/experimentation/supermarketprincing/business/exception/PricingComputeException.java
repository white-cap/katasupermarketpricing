package io.yassir.experimentation.supermarketprincing.business.io.yassir.experimentation.supermarketprincing.business.exception;

/**
 * Custom Exception for PricingComputeException
 */
public class PricingComputeException extends RuntimeException{

    PricingComputeException(String alert){
        super(alert);
    }
}
