package io.yassir.experimentation.supermarketprincing.business.strategy;

import io.yassir.experimentation.supermarketprincing.business.exception.PricingComputeException;
import io.yassir.experimentation.supermarketprincing.model.Enum.UnitType;
import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.PricingResponse;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author yassir
 * If you buy X unit Get Y unit for free impl strategy
 */
public class BuyXGetYFreeComputeStrategy implements CustomFunctionPricing {


    /**
     * Apply If you buy X unit Get Y unit for free behavior
     *
     * @param pricingRequest request object (DTO)
     * @return PricingResponse
     * @throws PricingComputeException Exception on handle pricing compute
     */
    @Override
    public PricingResponse apply(PricingRequest pricingRequest) throws PricingComputeException {
        validateUnit(pricingRequest);
        BigDecimal amount = BigDecimal.ZERO;
        BigDecimal price = pricingRequest.getProduct().getPriceByUnitType().getPrice();
        double unit = pricingRequest.getUnit();
        if (2 < unit) {
            unit = unit - Math.floor(unit / 2);
            amount = calculateAmount(price, unit);
        } else {
            amount = calculateAmount(price, unit);
        }
        return new PricingResponse(pricingRequest, amount);
    }
}
