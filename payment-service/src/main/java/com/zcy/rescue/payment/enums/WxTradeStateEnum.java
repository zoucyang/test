package com.zcy.rescue.payment.enums;

/**
 * 微信交易状态枚举
 *
 * @author Lv Zhongzhou
 * @date 2020/06/22
 */
public enum WxTradeStateEnum {
    /**
     * 微信交易状态枚举
     */
    NOTPAY((byte) 1, "NOTPAY", "未支付"),
    CLOSED((byte) 2, "CLOSED", "已关闭"),
    USERPAYING((byte) 3, "USERPAYING", "用户支付中（付款码支付）"),
    REVOKED((byte) 4, "REVOKED", "已撤销（付款码支付）"),
    PAYERROR((byte) 5, "PAYERROR", "支付失败(其他原因，如银行返回失败)"),
    SUCCESS((byte) 6, "SUCCESS", "支付成功"),
    REFUND((byte) 7, "REFUND", "转入退款");

    private byte id;

    private String code;

    private String desc;

    private WxTradeStateEnum(byte id, String code, String desc) {
        this.id = id;
        this.code = code;
        this.desc = desc;
    }

    /**
     * 查询id
     *
     * @param code 编码
     * @return String
     */
    public static byte getId(String code) {
        for (WxTradeStateEnum tradeStateEnum : WxTradeStateEnum.values()) {
            if (tradeStateEnum.getCode().equals(code)) {
                return tradeStateEnum.getId();
            }
        }
        return 0;
    }

    /**
     * 查询编码
     *
     * @param id id
     * @return String
     */
    public static String getCode(byte id) {
        for (WxTradeStateEnum tradeStateEnum : WxTradeStateEnum.values()) {
            if (tradeStateEnum.id == id) {
                return tradeStateEnum.code;
            }
        }
        return null;
    }

    /**
     * 查询描述
     *
     * @param id id
     * @return String
     */
    public static String getDesc(byte id) {
        for (WxTradeStateEnum tradeStateEnum : WxTradeStateEnum.values()) {
            if (tradeStateEnum.id == id) {
                return tradeStateEnum.getDesc();
            }
        }
        return null;
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
