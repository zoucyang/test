package com.zcy.rescue.payment.dto;

import lombok.Data;

/**
 * 查询订单 Model
 * @author zouhx
 * @date 2024/02/01
 */
@Data
public class OrderQueryDTO {
    /**
     * 微信的订单号，建议优先使用
     */
    private String transactionId;

    /**
     * 商户系统内部订单号，即易才订单号
     */
    private String outTradeNo;
}
