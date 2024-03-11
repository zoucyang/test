package com.zcy.rescue.payment.dto;

import lombok.Data;

/**
 * 统一下单模型
 *
 * @author zouhx
 * @date 2024/02/01
 */
@Data
public class UnifiedOrderDTO {
    /**
     * 交易类型-必填
     */
    private byte tradeType;

    /**
     * 订单总金额，单位元-必填
     */
    private String totalFee;

    /**
     * 商品描述-非必填
     */
    private String description = "易才-保险";

    /**
     * 用户标识
     */
    private String openid;

    /**
     * 支付配置id，支付网关提供-必填
     */
    private Integer configId;

    /**
     * 支付结果异步通知地址-业务系统提供-必填
     */
    private String notifyUrl;

    /**
     * 商品ID
     * trade_type=NATIVE时，此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
     */
    private String productId;

    /**
     * JSAPI、小程序、Native支付场景描述(非必填)
     * "scene_info" : {
     * "payer_client_ip" : "14.23.150.211",
     * "device_id" : "013467007045764",
     * "store_info" : {
     * "id" : "0001",
     * "name" : "腾讯大厦分店",
     * "area_code" : "440305",
     * "address" : "广东省深圳市南山区科技中一道10000号"
     * }
     * }
     * H5（必填）
     * "h5_info" : {
     * "type" : "iOS",
     * "app_name" : "王者荣耀",
     * "app_url" : "https://pay.qq.com",
     * "bundle_id" : "com.tencent.wzryiOS",
     * "package_name" : "com.tencent.tmgp.sgame"
     * }
     */
    private String sceneInfo;
}
