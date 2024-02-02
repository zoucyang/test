package com.zcy.rescue.payment.vo;

import lombok.Data;

/**
 * 查询订单业务模型
 * @author zouhx
 * @date 2024/02/01
 */
@Data
public class OrderQueryVO {

    private String returnCode;

    private String returnMsg;

    private String appid;

    private String mchId;

    private String nonceStr;

    private String sign;

    private String resultCode;

    private String errCode;

    private String errCodeDes;

    private String deviceInfo;

    private String openid;

    private String isSubscribe;

    private String tradeType;

    private String tradeState;

    private String bankType;

    private Integer totalFee;

    private Integer settlementTotalFee;

    private String feeType;

    private Integer cashFee;

    private String cashFeeType;

    private String transactionId;

    private String outTradeNo;

    private String attach;

    private String timeEnd;

    private String tradeStateDesc;
}
