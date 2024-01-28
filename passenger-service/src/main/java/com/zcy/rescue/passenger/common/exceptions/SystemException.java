package com.zcy.rescue.passenger.common.exceptions;

import com.zcy.rescue.passenger.common.enums.ErrorCode;
import com.zcy.rescue.passenger.common.enums.ICommonEnum;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统异常
 *
 * @author: xiexindong
 * @date: 2019-07-23 11:41
 */
@SuppressWarnings({"unused", "FieldMayBeFinal"})
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 3062938427612960844L;
    /**
     * 是否将错误码自动添加为错误消息的前缀
     */
    private static Boolean isAutoPrefixCode = null;
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误描述
     */
    private String reason;

    /**
     * 附件
     */
    private Map<String, Object> attachments;

    /**
     * 格式化后消息
     */
    private final String formattedMessage;

    public SystemException(int code, String reason) {
        super("错误码:" + code + ",原因:" + reason);
        if (isAutoPrefixCode == null) {
            resetIsAutoPrefixCode(null);
        }
        if (Boolean.TRUE.equals(isAutoPrefixCode)) {
            formattedMessage = super.getMessage();
        } else {
            formattedMessage = reason;
        }
        this.code = code;
        this.reason = reason;
        if (code == 0) {
            throw new SystemException(500, "禁止使用0作为错误码");
        }
        this.attachments = new HashMap<>(1);
    }

    public SystemException(ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getReason());
    }

    public SystemException(ICommonEnum yestaeEnum) {
        this(yestaeEnum.getValue(), yestaeEnum.getDescriptions());
    }

    public SystemException(ICommonEnum yestaeEnum, Object... args) {
        this(yestaeEnum.getValue(), String.format(yestaeEnum.getDescriptions(), args));
    }

    public int getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    public SystemException setCause(Throwable t) {
        super.initCause(t);
        return this;
    }

    @Override
    public String getMessage() {
        if (StringUtils.isEmpty(formattedMessage)) {
            return super.getMessage();
        }
        return formattedMessage;
    }

    @Override
    public String getLocalizedMessage() {
        return getMessage();
    }

    /**
     * 获取附件
     *
     * @return 附件
     */
    public Map<String, Object> getAttachments() {
        if (attachments == null) {
            attachments = new HashMap<>();
        }
        return attachments;
    }

    /**
     * 添加附件
     *
     * @param key   键
     * @param value 值
     * @return 数据
     */
    public SystemException addAttachment(String key, Object value) {
        this.getAttachments().put(key, value);
        return this;
    }

    /**
     * 移除附件
     *
     * @param key 键
     * @return 数据
     */
    public SystemException removeAttachment(String key) {
        this.getAttachments().remove(key);
        return this;
    }

    /**
     * 重置是否自动错误码前缀标识
     * <p>为后续动态刷新做准备</p>
     *
     * @param value 标识值(传null时将尝试从系统变量读取)
     */
    public static void resetIsAutoPrefixCode(Boolean value) {
        if (value == null) {
            final String propertyValue = System.getProperty("yestae.system-exception.auto-prefix-code");
            isAutoPrefixCode = !StringUtils.isEmpty(propertyValue) && "true".equalsIgnoreCase(propertyValue.trim());
        } else {
            isAutoPrefixCode = value;
        }
    }
}
