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
        if (unit % 3 == 0) {
            BigDecimal realAmount = calculateAmount(price, unit).setScale(2, RoundingMode.HALF_UP);
            amount = analyseAndChoicePrice(realAmount);
        } else {
            amount = calculateAmount(price, unit);
        }
        return new PricingResponse(pricingRequest, amount);
    }

    /**
     * Apply an appropriate discount and return the amount
     *
     * @param realAmount real Amount
     * @return
     */
    private BigDecimal analyseAndChoicePrice(BigDecimal realAmount) {
        final BigDecimal discountPercent = BigDecimal.valueOf(0.3);//30%
        final BigDecimal afterDiscount = realAmount.subtract(realAmount.multiply(discountPercent));
        if(BigDecimal.ONE.compareTo(afterDiscount) == 1 )
            return afterDiscount.setScale(2, RoundingMode.HALF_UP);
        else
            return afterDiscount.setScale(0, RoundingMode.DOWN);

    }
}
