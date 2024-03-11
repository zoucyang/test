package com.zcy.rescue.payment.vo;

import lombok.Data;

/**
 * 申请退款VO
 * @author zouhx
 * @date 2024/02/01
 */
@Data
public class RefundVO {
    /**
     * 商户系统内部订单号
     * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    private String outTradeNo;

    /**
     * 商户系统内部的退款单号
     * 商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    private String outRefundNo;

    /**
     * 微信退款单号
     */
    private String refundId;

    /**
     * 退款总金额，单位元-必填
     */
    private String refundFee;

    private String appId;
    private String mchId;
}
