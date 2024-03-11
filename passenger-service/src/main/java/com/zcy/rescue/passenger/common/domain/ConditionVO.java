package com.zcy.rescue.passenger.common.domain;

import com.zcy.rescue.passenger.common.annotations.ToStringField;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * 条件基类
 */
@SuppressWarnings("unused")
public class ConditionVO<T extends ConditionVO, M extends Serializable>
        extends PageVO<T, M>
        implements  Serializable {
    private static final long serialVersionUID = -247638007137446528L;


    /**
     * id集合用于in查询
     */
    @ApiModelProperty(value = "id集合用于in查询")
    @ToStringField
    private List<Long> idList;
    /**
     * 外键ID集合
     */
    @ApiModelProperty(value = "外键ID集合")
    @ToStringField
    private List<Long> fkIdList;
    /**
     * status集合用于in查询
     */
    @ApiModelProperty(value = "status集合用于in查询")
    private List<Integer> statusList;
    /**
     * 名称模糊查询
     */
    @ApiModelProperty(value = "名称模糊查询")
    private String nameLike;
    /**
     * 编码模糊查询
     */
    @ApiModelProperty(value = "编码模糊查询")
    private String codeLike;


    /**
     * 排序选项
     */
    @ApiModelProperty(value = "排序选项", hidden = true)
    private List<OrderByVO> orderBys;
    /**
     * 排序SQL(服务端自动生成，传入无效)
     */
    @ApiModelProperty(hidden = true)
    private transient String orderBySql;


    public List<Long> getIdList() {
        return idList;
    }

    public T setIdList(List<Long> idList) {
        this.idList = idList;
        return (T) this;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public T setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
        return (T) this;
    }

    public String getNameLike() {
        return nameLike;
    }

    public T setNameLike(String nameLike) {
        this.nameLike = nameLike;
        return (T) this;
    }

    public String getCodeLike() {
        return codeLike;
    }

    public T setCodeLike(String codeLike) {
        this.codeLike = codeLike;
        return (T) this;
    }


    public List<OrderByVO> getOrderBys() {
        return orderBys;
    }

    public T setOrderBys(List<OrderByVO> orderBys) {
        this.orderBys = orderBys;
        return (T) this;
    }

    public String getOrderBySql() {
        return orderBySql;
    }

    public T setOrderBySql(String orderBySql) {
        this.orderBySql = orderBySql;
        return (T) this;
    }

    public List<Long> getFkIdList() {
        return fkIdList;
    }

    public T setFkIdList(List<Long> fkIdList) {
        this.fkIdList = fkIdList;
        return (T) this;
    }


}