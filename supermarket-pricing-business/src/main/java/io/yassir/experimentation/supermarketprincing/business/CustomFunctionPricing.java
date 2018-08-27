package io.yassir.experimentation.supermarketprincing.business;

/**
 * @author  yassir
 * The same as Function 1.8 JDK but allow us to throws exceptions
 * @param <T>
 * @param <R>
 */
@FunctionalInterface
public interface CustomFunctionPricing<T, R> {


    /**
     * Applies this function to the given argument.
     * @param t the function argument
     * @return the function result
     * @throws Exception
     */
    R apply(T t) throws RuntimeException;

}
