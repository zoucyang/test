package com.zcy.rescue.passenger.common.domain;

import com.zcy.rescue.passenger.common.enums.ErrorCode;
import com.zcy.rescue.passenger.common.exceptions.SystemException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 统一数据返回结构
 *
 * @author: xiexindong
 * @date: 2019-07-23 11:13
 */
@Data
@SuppressWarnings("unused")
@ApiModel(description = "统一数据返回结构")
public class DataResult<T> implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(DataResult.class);
    private static final long serialVersionUID = 7989360091080822988L;
    private static final String FULL_MESSAGE_FORMAT = "原因:%s，异常:%s";
    /**
     * 错误码
     * <p>0为成功，其他值为失败</p>
     */
    @ApiModelProperty("错误码")
    private int code = 0;

    @ApiModelProperty("错误消息")
    private String message;
    /**
     * 附件
     */
    @ApiModelProperty("附件")
    private Map<String, Object> attachments;
    /**
     * 是否是分页结果
     */
    @ApiModelProperty("是否是分页结果")
    private boolean isPaging = false;
    /**
     * 数据结果
     */
    @ApiModelProperty("结果")
    private T result;

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
     * 设置是否是分页接口数据结果
     *
     * @param isPaging 是否是分页接口数据结果
     * @return 数据结果
     */
    public DataResult<T> setPaging(boolean isPaging) {
        this.isPaging = isPaging;
        return this;
    }

    /**
     * 设置数据结果
     *
     * @param result 数据结果
     * @return 数据结果
     */
    public DataResult<T> setResult(T result) {
        this.result = result;
        return this;
    }

    /**
     * 是否成功
     *
     * @return true:成功；false:失败
     */
    public boolean isSuccess() {
        return code == 0;
    }

    /**
     * 是否失败
     *
     * @return true:失败；false:成功
     */
    public boolean isFailed() {
        return !isSuccess();
    }

    /**
     * 返回成功结果
     *
     * @param <T> 要返回的结果的类型
     * @return 成功结果
     */
    public static <T> DataResult<T> success() {
        DataResult<T> dataResult = new DataResult<>();
        dataResult.setCode(0);
        return dataResult.setResult(null);
    }

    /**
     * 返回成功结果
     *
     * @param result 要返回的结果
     * @param <T>    要返回的结果的类型
     * @return 成功结果
     */
    @SuppressWarnings("unchecked")
    public static <T> DataResult<T> success(T result) {
        DataResult<T> dataResult = new DataResult<>();
        dataResult.setCode(0);
        if (result instanceof List) {
            ((List) result).removeAll(Collections.singleton(null));
        }
        return dataResult.setResult(result);
    }

    /**
     * 返回错误结果
     *
     * @param code    错误码
     * @param message 错误消息
     * @param <T>     要返回的错误结果的类型
     * @return 错误结果
     */
    public static <T> DataResult<T> failed(int code, String message) {
        DataResult<T> dataResult = new DataResult<>();
        dataResult.setCode(code != 0 ? code : 400);
        dataResult.setMessage(message);
        return dataResult;
    }

    /**
     * 返回错误结果
     * <p>message只包含系统异常的原因reason</p>
     *
     * @param systemException 系统异常对象
     * @param <T>             结果类型
     * @return 错误结果
     */
    public static <T> DataResult<T> failed(SystemException systemException) {
        DataResult<T> dataResult = new DataResult<>();
        dataResult.setCode(systemException.getCode());
        dataResult.setMessage(systemException.getReason());
        return dataResult;
    }

    /**
     * 返回错误结果
     * <p>message包含系统异常的原因reason和异常message</p>
     *
     * @param systemException 系统异常对象
     * @param <T>             结果类型
     * @return 错误结果
     */
    public static <T> DataResult<T> failedFull(SystemException systemException) {
        DataResult<T> dataResult = new DataResult<>();
        dataResult.setCode(systemException.getCode());
        dataResult.setMessage(String.format(FULL_MESSAGE_FORMAT, systemException.getReason(), systemException.getMessage()));
        return dataResult;
    }

    /**
     * 添加附件
     *
     * @param key   键
     * @param value 值
     * @return 数据
     */
    public DataResult<T> addAttachment(String key, Object value) {
        this.getAttachments().put(key, value);
        return this;
    }

    /**
     * 移除附件
     *
     * @param key 键
     * @return 数据
     */
    public DataResult<T> removeAttachment(String key) {
        this.getAttachments().remove(key);
        return this;
    }

    /**
     * 断言请求成功
     */
    public void assertSuccess() {
        if (isFailed()) {
            logger.warn("断言失败：{}", this);
            throw new SystemException(code, message);
        }
    }

    /**
     * 断言请求成功且结果不为null
     */
    public void assertResult() {
        assertSuccess();
        if (result == null) {
            throw new SystemException(ErrorCode.OTHER_ERROR.getCode(), "返回结果为空");
        }
    }

    /**
     * 断言请求成功且结果不为null，且如果返回集合，集合不能为空
     * <p>目前支持的集合类型：Iterable、Map、数组[]</p>
     */
    public void assertNotEmpty() {
        assertResult();
        boolean error = false;
        if (result instanceof Iterable) {
            Iterable iterable = (Iterable) result;
            error = !iterable.iterator().hasNext();
        } else if (result instanceof Map) {
            Map map = (Map) result;
            error = map.isEmpty();
        } else if (result instanceof Object[]) {
            Object[] objects = (Object[]) result;
            error = objects.length < 1;
        }
        if (error) {
            throw new SystemException(ErrorCode.OTHER_ERROR.getCode(), "返回集合为空");
        }
    }
}
