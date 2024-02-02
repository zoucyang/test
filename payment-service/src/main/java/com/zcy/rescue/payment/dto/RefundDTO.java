package com.zcy.rescue.payment.dto;

import lombok.Data;

/**
 * 申请退款模型
 * @author zouhx
 * @date 2024/02/01
 */
@Data
public class RefundDTO {
    /**
     * 商户系统内部订单号，即易才订单号-必填
     * 要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no
     */
    private String outTradeNo;

    /**
     * 退款总金额，单位元-必填
     */
    private String refundFee;

    /**
     * 退款原因-非必填
     */
    private String refundDesc = "用户退款";

    /**
     * 支付配置id，支付网关提供-必填
     */
    private Integer payConfigId;

    /**
     * 退款结果异步通知地址-业务系统提供-必填
     */
    private String notifyUrl;

}
