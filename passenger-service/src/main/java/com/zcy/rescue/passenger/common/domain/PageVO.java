package com.zcy.rescue.passenger.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 分页查询实体基类
 *
 * @author: xiexindong
 * @date: 2019-07-23 14:38
 */
@ApiModel(description = "分页查询实体基类")
public class PageVO<T extends PageVO, M extends Serializable>  implements Serializable {
    private static final long serialVersionUID = -3091837471164654066L;
    /**
     * 页码
     */
    @ApiModelProperty("页码")
    private int pageIndex = 1;
    /**
     * 页容量
     */
    @ApiModelProperty("页容量")
    private int pageSize = 20;
    /**
     * 查询条件载体
     */
    @ApiModelProperty("查询条件载体")
    private M moreCondition;

    /**
     * 起始条数 从0开始
     */
    @ApiModelProperty(value = "起始条数(从0开始)")
    private Integer pageStart;

    /**
     * 计算起始条数(从0开始)
     *
     * @return
     */
    public Integer getPageStart() {
        //if (pageStart == null) {// 如果没有设值则 自己计算
        //    pageStart = (pageIndex - 1) * pageSize;
        //    if (pageStart < 0) pageStart = 0;
        //}
        pageStart = (pageIndex - 1) * pageSize;
        if (pageStart < 0) {
            pageStart = 0;
        }
        return pageStart;
    }

    /**
     * 页码
     */
    public int getPageIndex() {
        return pageIndex;
    }

    public T setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
        return (T) this;
    }

    /**
     * 页容量
     */
    public int getPageSize() {
        return pageSize;
    }

    public T setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return (T) this;
    }

    /**
     * 查询条件载体
     */
    public M getMoreCondition() {
        return moreCondition;
    }

    public T setMoreCondition(M moreCondition) {
        this.moreCondition = moreCondition;
        return (T) this;
    }

    public T setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
        return (T) this;
    }
}