package com.zcy.rescue.payment.common.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 分页模型
 *
 * @author lvzhongzhou
 * @date 2020/06/03
 */
public class PageModel<T> implements Serializable {

    /**
     * 序列化标识
     */
    private static final long serialVersionUID = 5767407237944759335L;

    /**
     * 总条数
     */
    @Getter
    @Setter
    private long totalItems;

    /**
     * 页码
     */
    @Getter
    @Setter
    private int currentPageNo;

    /**
     * 列表对象
     */
    @Getter
    @Setter
    private List<T> listObject;

    /**
     * 总页数
     */
    @Getter
    @Setter
    private int totalPageNo;

    /**
     * 单页数量
     */
    @Getter
    @Setter
    private int pageSize;

    public PageModel() {
    }

    private PageModel(List<T> listObject, int currentPageNo, int pageSize, int totalPageNo, long totalItems) {
        this.currentPageNo = currentPageNo;
        this.pageSize = pageSize;
        this.totalPageNo = totalPageNo;
        this.totalItems = totalItems;
        this.listObject = listObject;
    }

    /**
     * 组装分页模型
     *
     * @param listObject    列表对象
     * @param currentPageNo 页码
     * @param pageSize      单页数量
     * @param totalPageNo   总页数
     * @param totalItems    总记录条数
     * @param <T>           泛型T
     * @return PageModel<T> 分页模型对象
     */
    public static <T> PageModel<T> newInstance(List<T> listObject, int currentPageNo, int pageSize, int totalPageNo, long totalItems) {
        return new PageModel<T>(listObject, currentPageNo, pageSize, totalPageNo, totalItems);
    }

    /**
     * 初始化没有分页的数据
     *
     * @param listObject
     * @param <T>
     * @return
     */
    public static <T> PageModel<T> newInstance(List<T> listObject) {
        return new PageModel<T>(listObject, 0, 0, 0, 0);
    }

    public static <T> PageModel<T> newInstance(List<T> listObject, PageModel pageModel) {
        return new PageModel<T>(listObject, pageModel.getCurrentPageNo(), pageModel.getPageSize(),
                pageModel.getTotalPageNo(), pageModel.getTotalItems());
    }
}
