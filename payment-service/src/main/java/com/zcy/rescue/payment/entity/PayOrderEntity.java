package com.zcy.rescue.payment.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author Lv Zhongzhou
 * @date 2020/06/09
 */
@Data
public class PayOrderEntity {

    private Integer id;

    private String outTradeNo;

    private String transactionId;

    private Byte payType;

    private Byte tradeType;

    private Byte tradeState;

    private Integer totalFee;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private Byte deleteFlag;

    private Integer payConfigId;

    private String notifyUrl;

    /**
     * 订单类型：1-支付，2-退款
     */
    private Byte type;

    /**
     * 内部退款订单号
     */
    private String outRefundNo;

    /**
     * 微信退款订单号
     */
    private String refundId;

    
}
