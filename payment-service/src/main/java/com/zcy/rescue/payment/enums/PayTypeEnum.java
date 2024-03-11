package com.zcy.rescue.payment.enums;

/**
 * 支付类型枚举
 *
 * @author Lv Zhongzhou
 * @date 2020/06/17
 */
public enum PayTypeEnum {
    /**
     * 支付类型枚举
     */
    WXPAY((byte) 1, "01", "微信支付"),
    ALIPAY((byte) 2, "02", "支付宝支付");

    private byte id;

    private String code;

    private String desc;

    private PayTypeEnum(byte id, String code, String desc) {
        this.id = id;
        this.code = code;
        this.desc = desc;
    }

    public byte getId() {
        return this.id;
    }

    public String getCode() {
        return this.code;
    }

    public String getDesc() {
        return this.desc;
    }
}
