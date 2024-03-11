package com.zcy.rescue.passenger.common.domain;

import com.zcy.rescue.passenger.common.exceptions.SystemException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 * 分页接口统一数据返回结构
 *
 * @author: xiexindong
 * @date: 2019-07-23 13:37
 */
@Data
@SuppressWarnings("unused")
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "分页接口统一数据返回结构")
public class PageResult<T> extends DataResult<List<T>> implements Serializable {
    private static final long serialVersionUID = 8711678466543832764L;
    private static final String FULL_MESSAGE_FORMAT = "原因:%s，异常:%s";
    /**
     * 总数
     */
    @ApiModelProperty("总数")
    private int totalCount;
    /**
     * 当前页码
     */
    @ApiModelProperty("当前页码")
    private int pageIndex;
    /**
     * 页容量
     */
    @ApiModelProperty("页容量")
    private int pageSize;
    /**
     * 总页数
     */
    @ApiModelProperty("总页数")
    private int pageCount;


    /**
     * 设置是否是分页接口数据结果
     *
     * @param isPaging 是否是分页接口数据结果
     * @return 数据结果
     */
    @Override
    public PageResult<T> setPaging(boolean isPaging) {
        super.setPaging(isPaging);
        return this;
    }

    /**
     * 设置总数
     *
     * @param totalCount 总数
     * @return 分页结果
     */
    public PageResult<T> setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    /**
     * 设置页码
     *
     * @param pageIndex 页码
     * @return 分页结果
     */
    public PageResult<T> setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        return this;
    }

    /**
     * 设置页容量
     *
     * @param pageSize 页容量
     * @return 分页结果
     */
    public PageResult<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    /**
     * 设置页数
     *
     * @param pageCount 页数
     * @return 分页结果
     */
    public PageResult<T> setPageCount(int pageCount) {
        this.pageCount = pageCount;
        return this;
    }

    /**
     * 设置数据结果
     *
     * @param items 数据
     * @return 分页结果
     */
    @Override
    public PageResult<T> setResult(List<T> items) {
        super.setResult(items);
        return this;
    }

    /**
     * 返回默认空分页结果
     *
     * @param <T> 数据类型
     * @return 分页结果
     */
    public static <T> PageResult<T> empty() {
        return PageResult.page(1, 10, 0, null);
    }

    /**
     * 返回分页结果
     *
     * @param pageVO     分页查询实体
     * @param totalCount 总数
     * @param items      数据
     * @param <T>        数据类型
     * @return 分页结果
     */
    //public static <T> PageResult<T> page(TeaPageVO pageVO, int totalCount, List<T> items) {
    //    return page(pageVO.getPageIndex(), pageVO.getPageSize(), totalCount, items);
    //}

    /**
     * 返回分页结果
     *
     * @param pageVO     分页查询实体
     * @param totalCount 总数
     * @param items      数据
     * @param <T>        数据类型
     * @return 分页结果
     */
    public static <T> PageResult<T> page(PageVO pageVO, int totalCount, List<T> items) {
        return page(pageVO.getPageIndex(), pageVO.getPageSize(), totalCount, items);
    }

    /**
     * 返回分页结果
     *
     * @param pageIndex  页码
     * @param pageSize   页容量
     * @param totalCount 总数
     * @param items      数据
     * @param <T>        数据类型
     * @return 分页结果
     */
    public static <T> PageResult<T> page(int pageIndex, int pageSize, int totalCount, List<T> items) {
        PageResult<T> pageResult = new PageResult<>();
        return pageResult.setPaging(true)
                .setPageIndex(pageIndex)
                .setPageSize(pageSize)
                .setTotalCount(totalCount)
                .setResult(items);
    }

    /**
     * 返回错误结果
     *
     * @param code    错误码
     * @param message 错误消息
     * @param <T>     要返回的错误结果的类型
     * @return 错误结果
     */
    public static <T> PageResult<T> pageFailed(int code, String message) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(code != 0 ? code : 400);
        pageResult.setMessage(message);
        return pageResult;
    }

    /**
     * 返回错误结果
     * <p>message只包含系统异常的原因reason</p>
     *
     * @param systemException 系统异常对象
     * @param <T>             结果类型
     * @return 错误结果
     */
    public static <T> PageResult<T> pageFailed(SystemException systemException) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(systemException.getCode());
        pageResult.setMessage(systemException.getReason());
        return pageResult;
    }

    /**
     * 返回错误结果
     * <p>message包含系统异常的原因reason和异常message</p>
     *
     * @param systemException 系统异常对象
     * @param <T>             结果类型
     * @return 错误结果
     */
    public static <T> PageResult<T> pageFailedFull(SystemException systemException) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setCode(systemException.getCode());
        pageResult.setMessage(String.format(FULL_MESSAGE_FORMAT, systemException.getReason(), systemException.getMessage()));
        return pageResult;
    }
}
