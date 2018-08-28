package io.yassir.experimentation.supermarketprincing.business;

import io.yassir.experimentation.supermarketprincing.business.exception.PricingComputeException;
import io.yassir.experimentation.supermarketprincing.business.strategy.*;
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
    private Product xUnitForYPriceProduct2;

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
        Context context = new Context(new PriceByUnitComputeStrategy());
        Assert.assertTrue(BigDecimal.valueOf(3.98).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * price by unit ko behavior
     */
    @Test
    public void priceByUnitKoTest() {
        PricingRequest request = new PricingRequest(this.priceByUnitProduct, UnitType.POUND, 2);
        Context context = new Context(new PriceByUnitComputeStrategy());
        Assert.assertFalse(BigDecimal.valueOf(1).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * price by unit behavior with 0 unit request
     */
    @Test
    public void priceByUnitExceptionTest() {
        thrown.expect(PricingComputeException.class);
        PricingRequest request = new PricingRequest(this.priceByUnitProduct, UnitType.POUND, 0);
        Context context = new Context(new PriceByUnitComputeStrategy());
        Assert.assertTrue(BigDecimal.valueOf(3.98).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * price of sub unit of the product behavior
     */
    @Test
    public void priceOfSubUnitTest() {
        PricingRequest request = new PricingRequest(this.priceByUnitProduct, UnitType.OUNCE, 2);
        Context context = new Context(new PriceByUnitComputeStrategy());
        Assert.assertTrue(BigDecimal.valueOf(0.25).compareTo(context.compute(request).getAmount()) == 0);
    }


    /**
     * UT
     * If you buy X unit Get Y unit behavior
     */
    @Test
    public void buyXGetYFreePricingTest() {
        PricingRequest request = new PricingRequest(this.buyXGetYFreeProduct, 3);//if you buy 2 you will get one for free
        Context context = new Context(new BuyXGetYFreeComputeStrategy());
        Assert.assertTrue(BigDecimal.valueOf(8).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * If you buy X unit Get Y unit behavior
     */
    @Test
    public void buyXGetYFreePricingKoTest() {
        PricingRequest request = new PricingRequest(this.buyXGetYFreeProduct, 4);
        Context context = new Context(new BuyXGetYFreeComputeStrategy());
        Assert.assertFalse(BigDecimal.valueOf(12).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * If you buy 1 unit you wont get a discount behavior
     */
    @Test
    public void buyXGetYFreePricingCaseOneUnitTest() {
        PricingRequest request = new PricingRequest(this.buyXGetYFreeProduct, 1);
        Context context = new Context(new BuyXGetYFreeComputeStrategy());
        Assert.assertTrue(BigDecimal.valueOf(4).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * If you buy 3 units you will get it for X price behavior
     */
    @Test
    public void xUnitForYPricePricingTest() {
        PricingRequest request = new PricingRequest(this.xUnitForYPriceProduct, 3);
        Context context = new Context(new XUnitForYPriceComputeStrategy());
        Assert.assertTrue(BigDecimal.valueOf(1).compareTo(context.compute(request).getAmount()) == 0);
    }

    /**
     * UT
     * If you buy 6 units with rounded price you will get it for X price behavior
     */
    @Test
    public void xUnitForYPricePricingOtherCaseTest() {
        PricingRequest request = new PricingRequest(this.xUnitForYPriceProduct2, 6);
        Context context = new Context(new XUnitForYPriceComputeStrategy());
        Assert.assertTrue(BigDecimal.valueOf(6).compareTo(context.compute(request).getAmount()) == 1);
    }


    @Before
    public void setUp() {
        simpleProduct = new Product("001", new PriceByUnitType(BigDecimal.valueOf(10)), DiscountType.SIMPLE);
        priceByUnitProduct = new Product("002", new PriceByUnitType(BigDecimal.valueOf(1.99), UnitType.POUND), DiscountType.PRICE_BY_UNIT);
        xUnitForYPriceProduct = new Product("003", new PriceByUnitType(BigDecimal.valueOf(0.50)), DiscountType.X_UNIT_FOR_Y_PRICE);
        buyXGetYFreeProduct = new Product("004", new PriceByUnitType(BigDecimal.valueOf(4)), DiscountType.BUY_X_GET_Y_FREE);
        xUnitForYPriceProduct2 = new Product("005", new PriceByUnitType(BigDecimal.valueOf(1)), DiscountType.X_UNIT_FOR_Y_PRICE);
    }
}
