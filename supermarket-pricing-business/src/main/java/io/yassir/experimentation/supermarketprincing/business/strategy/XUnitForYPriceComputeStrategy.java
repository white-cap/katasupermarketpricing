package io.yassir.experimentation.supermarketprincing.business.strategy;

import io.yassir.experimentation.supermarketprincing.business.exception.PricingComputeException;
import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.PricingResponse;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author yassir
 * If you buy X unit Get it for Y price impl strategy
 */
public class XUnitForYPriceComputeStrategy implements CustomFunctionPricing {


    public static final String IS_UNEXPECTED = "is unexpected";

    /**
     * Apply If you buy X unit Get it for Y price impl strategy behavior
     *
     * @param pricingRequest request object (DTO)
     * @return PricingResponse
     * @throws PricingComputeException Exception on handle pricing compute
     */
    @Override
    public PricingResponse apply(PricingRequest pricingRequest) throws PricingComputeException {
        validateUnit(pricingRequest);
        BigDecimal amount;
        BigDecimal price = pricingRequest.getProduct().getPriceByUnitType().getPrice();
        double unit = pricingRequest.getUnit();
        BigDecimal realAmount = calculateAmount(price, unit).setScale(2, RoundingMode.HALF_UP);
        BigDecimal amountDown = calculateAmount(price, unit).setScale(0, RoundingMode.DOWN);
        amount = analyseAndChoicePrice(realAmount, amountDown);
        return new PricingResponse(pricingRequest, amount);
    }

    /**
     * choice price down if only the delta isn't greater than 30%
     *
     * @param realAmount
     * @param amountDown
     * @return
     */
    private BigDecimal analyseAndChoicePrice(BigDecimal realAmount, BigDecimal amountDown) {
        BigDecimal analyserAmount = BigDecimal.ONE.subtract(amountDown.divide(realAmount, 2, RoundingMode.HALF_UP));
        if (analyserAmount.compareTo(BigDecimal.valueOf(30)) == 1)
            return realAmount;
        return amountDown;
    }
}
