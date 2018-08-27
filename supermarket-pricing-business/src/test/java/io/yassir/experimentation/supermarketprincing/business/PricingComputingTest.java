package io.yassir.experimentation.supermarketprincing.business;

import io.yassir.experimentation.supermarketprincing.business.exception.PricingComputeException;
import io.yassir.experimentation.supermarketprincing.business.strategy.Context;
import io.yassir.experimentation.supermarketprincing.business.strategy.SimplePricingComputeStrategy;
import io.yassir.experimentation.supermarketprincing.business.strategy.priceByUnitComputeStrategy;
import io.yassir.experimentation.supermarketprincing.model.Enum.DiscountType;
import io.yassir.experimentation.supermarketprincing.model.Enum.UnitType;
import io.yassir.experimentation.supermarketprincing.model.PriceByUnitType;
import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.math.BigDecimal;

/**
 * @author yassir
 * <p>
 * UT to experiment pricing models
 */
public class PricingComputingTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private Product simpleProduct;
    private Product priceByUnitProduct;
    private Product xUnitForYPriceProduct;
    private Product buyXGetYFreeProduct;

    /**
     * UT handle simple pricing behavior
     */
    @Test
    public void simplePricingTest() {
        PricingRequest request = new PricingRequest(this.simpleProduct, 3);
        Context context = new Context(new SimplePricingComputeStrategy());
        Assert.assertTrue(BigDecimal.valueOf(30).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * handle ko simple pricing behavior
     */
    @Test
    public void simplePricingKoTest() {
        PricingRequest request = new PricingRequest(this.simpleProduct, 3);
        Context context = new Context(new SimplePricingComputeStrategy());
        Assert.assertFalse(BigDecimal.valueOf(10).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * handle simple pricing behavior with 0 unit request
     */
    @Test
    public void simplePricingExceptionTest() {
        thrown.expect(PricingComputeException.class);
        PricingRequest request = new PricingRequest(this.simpleProduct, 0);
        Context context = new Context(new SimplePricingComputeStrategy());
        Assert.assertFalse(BigDecimal.valueOf(10).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * price by unit behavior
     */
    @Test
    public void priceByUnitTest() {
        PricingRequest request = new PricingRequest(this.priceByUnitProduct, UnitType.POUND, 2);
        Context context = new Context(new priceByUnitComputeStrategy());
        Assert.assertTrue(BigDecimal.valueOf(3.98).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * price by unit behavior with 0 unit request
     */
    @Test
    public void priceByUnitExceptionTest() {
        thrown.expect(PricingComputeException.class);
        PricingRequest request = new PricingRequest(this.priceByUnitProduct, UnitType.POUND, 0);
        Context context = new Context(new priceByUnitComputeStrategy());
        Assert.assertTrue(BigDecimal.valueOf(3.98).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * price of sub unit of the product behavior
     */
    @Test
    public void priceOfSubUnitTest() {
        PricingRequest request = new PricingRequest(this.priceByUnitProduct, UnitType.OUNCE, 2);
        Context context = new Context(new priceByUnitComputeStrategy());
        Assert.assertTrue(BigDecimal.valueOf(0.24875).compareTo(context.compute(request).getAmount()) == 0);
    }


    @Test
    public void buyXGetYFreePricingTest() {
        //U.T. to handle BUY_X_GET_Y_FREE pricing behavior
    }

    @Test
    public void xUnitForYPricePricingTest() {
        //U.T. to handle X_UNIT_FOR_Y_PRICE pricing behavior
    }

    @Before
    public void setUp() {
        simpleProduct = new Product("001", new PriceByUnitType(BigDecimal.valueOf(10)), DiscountType.SIMPLE);
        priceByUnitProduct = new Product("002", new PriceByUnitType(BigDecimal.valueOf(1.99), UnitType.POUND), DiscountType.PRICE_BY_UNIT);
        xUnitForYPriceProduct = new Product("003", new PriceByUnitType(BigDecimal.valueOf(0.50)), DiscountType.X_UNIT_FOR_Y_PRICE);
        buyXGetYFreeProduct = new Product("004", new PriceByUnitType(BigDecimal.valueOf(4)), DiscountType.BUY_X_GET_Y_FREE);
    }
}
