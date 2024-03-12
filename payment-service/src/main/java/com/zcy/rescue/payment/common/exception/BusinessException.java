package com.zcy.rescue.payment.common.exception;

/**
 * 业务异常
 *
 * @author lvzhongzhou
 * @date 2020/06/03
 */
public class BusinessException extends RuntimeException {
    private static final String DEFAULT_ERROR_CODE = "40000";
    private final String errorCode;

    public BusinessException(String message) {
        super(message);
        errorCode = DEFAULT_ERROR_CODE;
    }

    public BusinessException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(String message, Throwable ex) {
        super(message, ex);
        errorCode = DEFAULT_ERROR_CODE;
    }

    public BusinessException(Throwable ex) {
        super(ex);
        errorCode = DEFAULT_ERROR_CODE;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
