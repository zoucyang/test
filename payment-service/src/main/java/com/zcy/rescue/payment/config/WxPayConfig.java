package com.zcy.rescue.payment.config;

import lombok.Data;

import java.io.Serializable;

/**
 * 微信支付配置
 *
 * @author Lv zouhx
 * @date 2024/01/30
 */
@Data
public class WxPayConfig implements Serializable {

    private String mchId;

    private String appid;

    private String appSecret;

    private String apiKey;

    private String serialNumber;

    private String privateKeyFromPath;

}
