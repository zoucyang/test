package com.zcy.rescue.payment.common.constant;

/**
 * 微信支付相关常量
 *
 * @author zouhx
 * @date 2024/02/02
 */
public class WxPayConstants {

    /**
     * 签名类型，默认为MD5
     */
    public static final String SIGN_TYPE = "MD5";
    public static final String FAIL = "FAIL";
    public static final String SUCCESS = "SUCCESS";
    /**
     * 标价币种，符合ISO 4217标准的三位字母代码，默认人民币：CNY
     */
    public static final String FEE_TYPE = "CNY";

    /**
     * 设备号，自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
     */
    //public static final String DEVICE_INFO = "WEB";

    /**
     * IP 默认值
     */
    public static final String IP_DEFAULT = "123.12.12.123";

    /**
     * 返回状态码
     * SUCCESS/FAIL
     * 此字段是通信标识，非交易标识，交易是否成功需要查看 result_code 来判断
     */
    public static final String RETURN_CODE = "return_code";

    /**
     * 返回信息
     * 当return_code为FAIL时返回信息为错误原因 ，例如
     * 签名失败
     * 参数格式校验错误
     */
    public static final String RETURN_MSG = "return_msg";

    /**
     * 业务结果
     * SUCCESS/FAIL
     */
    public static final String RESULT_CODE = "result_code";

    /**
     * 错误代码
     * 当result_code为FAIL时返回错误代码，详细参见下文错误列表
     */
    public static final String ERR_CODE = "err_code";

    /**
     * 错误代码描述
     * 当result_code为FAIL时返回错误描述，详细参见下文错误列表
     */
    public static final String ERR_CODE_DES = "err_code_des";

    /**
     * 二维码链接
     * trade_type=NATIVE时有返回，此url用于生成支付二维码，然后提供给用户进行扫码支付。
     * 注意：code_url的值并非固定，使用时按照URL格式转成二维码即可
     * weixin://wxpay/bizpayurl/up?pr=NwY5Mz9&groupid=00
     */
    public static final String CODE_URL = "code_url";

    public static final String MWEB_URL = "mweb_url";


}
