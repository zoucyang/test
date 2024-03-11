package com.zcy.rescue.payment.common.constant;

/**
 * 功能描述: <br>
 *
 * @author zjx
 * @version 1.0.0
 * @date 2024/1/30 10:48
 */
public class Constants {

    public static final String API_PREFIX = "/api/v1";

    public static final String OPEN__PREFIX = "/open/v1";

    /**
     * Redis key - 支付宝订单id
     */
    public static final String REDIS_KEY_ALIPAY_ORDER_ID = "payment-gateway-alipay-order-id";

    /**
     * Redis key - 微信订单id
     */
    public static final String REDIS_KEY_WXPAY_ORDER_ID = "payment-gateway-wxpay-order-id";

    /**
     * Redis key - 支付宝退款订单ID
     */
    public static final String REDIS_KEY_ALIPAY_REFUND_ORDER_ID = "payment-gateway-alipay-refund-order-count";

    /**
     * Redis key - 微信退款订单ID
     */
    public static final String REDIS_KEY_WXPAY_REFUND_ORDER_ID= "payment-gateway-wxpay-refund-order-count";


}
