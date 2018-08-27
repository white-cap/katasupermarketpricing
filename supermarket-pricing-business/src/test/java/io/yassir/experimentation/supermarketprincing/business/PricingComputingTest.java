package io.yassir.experimentation.supermarketprincing.business;

import io.yassir.experimentation.supermarketprincing.model.Enum.DiscountType;
import io.yassir.experimentation.supermarketprincing.model.PricingRequest;
import io.yassir.experimentation.supermarketprincing.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author yassir
 * <p>
 * UT to experiment pricing models
 */
public class PricingComputingTest {

    private Product simpleProduct;
    private Product priceByUnitProduct;
    private Product xUnitForYPriceProduct;
    private Product buyXGetYFreeProduct;

    /**
     * handle simple pricing behavior
     */
    @Test
    public void simplePricingTest() {
        PricingRequest request = new PricingRequest(this.simpleProduct, 3);
        Context context = new Context(new SimplePricingComputeStrategy());
        Assert.assertEquals(BigDecimal.valueOf(30), context.compute(request).getAmount());
    }

    @Test
    public void priceByUnitPricingTest() {
        //U.T. to handle PRICE_BY_UNIT pricing behavior
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
        simpleProduct = new Product("001", BigDecimal.valueOf(10), DiscountType.SIMPLE);
        priceByUnitProduct = new Product("002", BigDecimal.valueOf(1.99), DiscountType.PRICE_BY_UNIT);
        xUnitForYPriceProduct = new Product("003",BigDecimal.valueOf(0.50), DiscountType.X_UNIT_FOR_Y_PRICE);
        buyXGetYFreeProduct = new Product("004",BigDecimal.valueOf(4), DiscountType.BUY_X_GET_Y_FREE);
    }
}
