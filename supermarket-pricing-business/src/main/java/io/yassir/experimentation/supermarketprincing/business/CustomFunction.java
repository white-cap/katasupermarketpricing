package io.yassir.experimentation.supermarketprincing.business;

@FunctionalInterface
public interface CustomFunction <T, R> {


    /**
     * Applies this function to the given argument.
     * @param t the function argument
     * @return the function result
     * @throws Exception
     */
    R apply(T t) throws RuntimeException;

}
