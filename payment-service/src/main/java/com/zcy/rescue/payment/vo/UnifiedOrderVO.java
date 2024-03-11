package com.zcy.rescue.payment.vo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @author zouhx
 * @date 2024/02/01
 */
@Data
public class UnifiedOrderVO {
    /**
     * 内部订单号
     */
    private String outTradeNo;

    /**
     * 微信交易流水号
     */
    private String tradeNo;

    private String appId;

    private String timeStamp;

    private String nonceStr;

    @JSONField(name = "package")
    private String prepayId;

    private String signType;

    private String paySign;

    /**
     * 二维码链接
     * trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
     * 注意：code_url的值并非固定，使用时按照URL格式转成二维码即可
     */
    private String codeUrl;

    /**
     * 支付跳转链接,trade_type=mweb时有返回
     * mweb_url为拉起微信支付收银台的中间页面，可通过访问该url来拉起微信客户端，完成支付,mweb_url的有效期为5分钟。
     */
    private String mwebUrl;
}
