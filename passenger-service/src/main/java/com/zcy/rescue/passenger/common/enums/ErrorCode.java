package com.zcy.rescue.passenger.common.enums;

/**
 * @program: rescue
 * @description: 系统内置错误码枚举
 * @author: Administrator
 * @create: 2024-01-28 09:55
 **/
public enum ErrorCode {
    PART_ERROR(1000, "部分成功"),
    OTHER_ERROR(1001, "其他错误"),
    CALC_MD5_ERROR(1002, "calc MD5 error"),
    SYSTEM_LIMITER(1003, "系统限流保护"),
    API_LIMITER(1004, "API限流保护"),
    ILLEGAL_CONCURRENT_CALL(1005, "非法重复调用"),
    TIME_OUT(1006, "系统超时"),
    CONNECTION_TIME_OUT(1007, "连接超时"),
    VERIFY_FAILED(1008, "验签失败"),
    VERIFY_EXPIRE(1009, "签名已过期"),
    UNZIP_FAILED(1010, "解压失败"),
    ENCODER_FAILED(1011, "编码失败"),
    DECODER_FAILED(1012, "解码失败"),
    CONNECT_ERROR(1013, "连接异常"),
    MISSING_SERVER_PROVIDER(1014, "缺失服务提供者"),
    SYSTEM_ERROR(1015, "系统错误"),
    PARAM_ERROR(1500, "参数错误"),
    NOT_SUPPORT_METHOD(1501, "不支持的调用"),
    NOT_SUPPORT_OPERATOR(1502, "不支持的操作"),
    SERVER_REFUSE(1502, "服务拒绝"),
    ILLEGAL_ARGUMENT(1503, "非法参数"),
    JSON_SERIALIZE_FAIL(1504, "json序列化失败"),
    CONCURRENT_CACHE_KEY_TIMEOUT(1505, "concurrent cache key timeout"),
    PARAM_MISSING(1506, "缺少参数"),
    INVALID_SIGNATURE(1507, "无效签名");

    /**
     * 错误码
     */
    private int code;
    /**
     * 原因
     */
    private String reason;

    public int getCode() {
        return this.code;
    }

    public String getReason() {
        return this.reason;
    }

    private ErrorCode(int code, String reason) {
        this.code = code;
        this.reason = reason;
    }

    public static boolean isMember(int code) {
        for (ErrorCode errorCode : ErrorCode.values()) {
            if (code == errorCode.getCode()) {
                return true;
            }
        }
        return false;
    }
}
