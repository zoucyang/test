package com.zcy.rescue.payment.common.response;

/**
 * 返回结果枚举定义
 *
 * @author liukui
 */
public enum BusinessResultCode {
    /**
     * 成功   正常返回请求时获取数据
     */
    SUCCESS(200, "成功", "SUCCESS"),

    /**
     * 没有该业务类型   输入不存在的访问地址
     */
    NOT_FOUND(404, "没有该业务类型", "BUSINESS_NOT_EXIST"),
    /**
     * 没有登录     验证未登录时返回
     */
    NOT_LOGIN(406, "没有登录", "USER_NOT_LOGIN"),
    /**
     * 不支持或已经废弃     不支持的请求类型或请求资源已废弃
     */
    NOT_SUPPORTED(410, "不支持或已经废弃", "NOT_SUPPORT_OR_ABANDON"),

    /**
     * AuthCode错误       如果有授权码，验证不通过
     */
    INVALID_AUTHCODE(401, "无效的AuthCode", "INVALID_AUTH_CODE"),

    /**
     * AuthCode错误       如果有授权码，验证不通过
     */
    NONE_PERMISSION(403, "没有权限访问当前资源", "NONE_PERMISSION"),

    /**
     * 系统错误     程序内部错误，运行时错误或BUG导致
     */
    SYS_ERROR(500, "系统错误", "SYS_ERROR"),

    /**
     * 发生异常     程序内部错误，运行时错误或BUG导致
     */
    EXCEPTION(503, "发生异常", "EXCEPTION"),
    /**
     * 参数错误     传递的参数不合规范，前段开发人员用于调试使用
     */
    PARAMS_ERROR(512, "参数错误", "PARAMS_ERROR"),

    /**
     * 业务逻辑异常       业务逻辑异常，前段研发人员需要抛出给用户
     */
    BUSINESS_EXCEPTION(513, "业务逻辑异常", "BUSINESS_EXCEPTION"),
    /**
     * 不支持的访问请求
     */
    HTTP_REQUEST_METHOD_NOT_SUPPORTED(514, "不支持的访问请求", "NOT_SUPPORTED_REQUEST"),

    /**
     * 登录业务逻辑异常
     */
    LOGIN_BUSINESS_EXCEPTION(515, "登录业务逻辑异常", "LOGIN_BUSINESS_EXCEPTION"),

    /**
     * 文件大小超过最大值
     */
    UOLOAD_FILE_OVERSIZE(516, "文件大小超过最大值", "UOLOAD_FILE_OVERSIZE"),

    /**
     * 系统升级暂停服务
     */
    SYSTEM_UPGRADE(599, "系统升级暂停服务", "SYSTEM_UPGRADE"),

    /**
     * 太频繁的调用
     */
    TOO_REQUEST(405, "太频繁的调用", "TOO_REQUEST"),

    /**
     * 未知的错误
     */
    UNKNOWN_ERROR(499, "未知错误", "UNKNOWN_ERROR");

    BusinessResultCode(int value, String msg, String subCode) {
        this.val = value;
        this.msg = msg;
        this.subCode = subCode;
    }

    public int val() {
        return val;
    }

    public String msg() {
        return msg;
    }

    public String subCode() {
        return subCode;
    }

    private int val;
    private String msg;
    private String subCode;

    public boolean equals(Integer val) {
        return val.equals(this.val);
    }
}