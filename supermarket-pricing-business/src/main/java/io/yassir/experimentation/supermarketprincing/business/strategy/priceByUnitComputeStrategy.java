package io.yassir.experimentation.supermarketprincing.business.strategy;

import io.yassir.experimentation.supermarketprincing.business.exception.PricingComputeException;
import io.yassir.experimentation.supermarketprincing.model.Enum.UnitType;
import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.PricingResponse;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * @author yassir
 * price by unit impl strategy
 */
public class priceByUnitComputeStrategy implements CustomFunctionPricing {


    public static final String IS_UNEXPECTED = "is unexpected";

    /**
     * Apply sprice by unit behavior
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
        if (pricingRequest.getProduct().getPriceByUnitType().getUnitType() == pricingRequest.getUnitType()) {
            amount = calculateAmount(price,unit);
        } else if (UnitType.POUND == pricingRequest.getProduct().getPriceByUnitType().getUnitType() &&
                UnitType.OUNCE == pricingRequest.getUnitType()) {
            unit = unit / 16L;
            amount = calculateAmount(price,unit);
        } else {
            StringBuilder sb = new StringBuilder(pricingRequest.getUnitType().name());
            throw new PricingComputeException(sb.append(StringUtils.SPACE).append(IS_UNEXPECTED).toString());
        }

        return new PricingResponse(pricingRequest, amount);
    }
}
