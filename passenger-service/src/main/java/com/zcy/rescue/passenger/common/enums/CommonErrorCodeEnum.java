package com.zcy.rescue.passenger.common.enums;

/**
 * @program: rescue
 * @description: 公共错误码
 * @author: Administrator
 * @create: 2024-01-28 10:10
 **/
public enum CommonErrorCodeEnum {
    SERVER_ERROR(1004, "服务器运行异常"),

    BEAN_COPY_SOURCE_IS_NULL(1005, "传入原有类不能为空"),
    BEAN_COPY_TARGET_IS_NULL(1006, "目标类不能为空"),
    BEAN_INSTANCE(1007, "bean实例化失败"),

    DISTRIBUTE_LOCK_FAIL(1019, "获取分布式出错"),
    FREQUENT_OPERATION(1010, "频繁操作，请稍后重试"),
    UN_SUPPORTED_OPERATE(1011, "不支持的操作类型"),
    PARAMETER_IS_EMPTY(1012, "缺少必要参数"),
    ;

    private int errorCode;
    private String errorReason;

    private CommonErrorCodeEnum(int errorCode, String errorReason) {
        this.errorCode = errorCode;
        this.errorReason = errorReason;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorReason() {
        return errorReason;
    }

    public static String getErrorCode(int errorCode) {
        for (CommonErrorCodeEnum ele : CommonErrorCodeEnum.values()) {
            if (ele.getErrorCode() == errorCode) {
                return ele.getErrorReason();
            }
        }
        return null;
    }

    public static CommonErrorCodeEnum getEnum(int errorCode) {
        for (CommonErrorCodeEnum ele : CommonErrorCodeEnum.values()) {
            if (ele.getErrorCode() == errorCode) {
                return ele;
            }
        }
        return null;
    }
}
