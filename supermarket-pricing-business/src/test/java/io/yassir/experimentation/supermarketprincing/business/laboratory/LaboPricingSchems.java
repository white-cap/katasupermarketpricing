package io.yassir.experimentation.supermarketprincing.business.laboratory;

import io.yassir.experimentation.supermarketprincing.business.strategy.Context;
import io.yassir.experimentation.supermarketprincing.model.Enum.DiscountType;
import io.yassir.experimentation.supermarketprincing.model.Enum.UnitType;
import io.yassir.experimentation.supermarketprincing.model.PriceByUnitType;
import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.PricingResponse;
import io.yassir.experimentation.supermarketprincing.model.Product;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

/**
 * It's a labo for experimentation of new models of discount
 */
public class LaboPricingSchems {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Product simpleProduct;
    private Product priceByUnitProduct;
    private Product xUnitForYPriceProduct;
    private Product buyXGetYFreeProduct;

    @Test
    public void experimentation() {
        PricingRequest request = new PricingRequest(this.xUnitForYPriceProduct, 3);
        Context context = new Context(LaboPricingSchems::experimentationImpl );
    }

    private static PricingResponse experimentationImpl(PricingRequest pricingRequest){
        //ADD YOUR LOGIC HERE
        return null;
    }

    @Before
    public void setUp() {
        simpleProduct = new Product("001", new PriceByUnitType(BigDecimal.valueOf(10)), DiscountType.SIMPLE);
        priceByUnitProduct = new Product("002", new PriceByUnitType(BigDecimal.valueOf(1.99), UnitType.POUND), DiscountType.PRICE_BY_UNIT);
        xUnitForYPriceProduct = new Product("003", new PriceByUnitType(BigDecimal.valueOf(0.40)), DiscountType.X_UNIT_FOR_Y_PRICE);
        buyXGetYFreeProduct = new Product("004", new PriceByUnitType(BigDecimal.valueOf(4)), DiscountType.BUY_X_GET_Y_FREE);
    }
}
