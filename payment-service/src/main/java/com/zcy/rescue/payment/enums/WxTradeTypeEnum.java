package com.zcy.rescue.payment.enums;

/**
 * 微信交易类型枚举
 *
 * @author Lv Zhongzhou
 * @date 2020/06/10
 */
public enum WxTradeTypeEnum {
    /**
     * 微信交易类型枚举
     */
    JSAPI((byte) 1, "JSAPI", "JSAPI支付"),
    MINI((byte) 2, "JSAPI", "小程序支付"),
    NATIVE((byte) 3, "NATIVE", "Native支付"),
    APP((byte) 4, "APP", "APP支付"),
    MWEB((byte) 5, "MWEB", "H5支付"),
    MICROPAY((byte) 6, "MICROPAY", "付款码支付"),
    FACE((byte) 7, "FACE", "刷脸支付");

    private byte id;

    private String code;

    private String desc;

    private WxTradeTypeEnum(byte id, String code, String desc) {
        this.id = id;
        this.code = code;
        this.desc = desc;
    }

    /**
     * 查询编码
     *
     * @param id id
     * @return String
     */
    public static String getCode(byte id) {
        for (WxTradeTypeEnum tradeTypeEnum : WxTradeTypeEnum.values()) {
            if (tradeTypeEnum.id == id) {
                return tradeTypeEnum.code;
            }
        }
        return null;
    }

    public byte getId() {
        return this.id;
    }
}
