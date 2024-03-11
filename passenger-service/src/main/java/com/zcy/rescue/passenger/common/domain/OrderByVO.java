package com.zcy.rescue.passenger.common.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 排序实体
 *
 * @author: xiexindong
 * @date: 019-10-24 14:32
 */
@Data
@ApiModel("排序实体")
@Accessors(chain = true)
public class OrderByVO implements Serializable {
    private static final long serialVersionUID = 6119077933378207659L;
    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段", required = true)
    private String field;
    /**
     * 是否升序(默认降序)
     */
    @ApiModelProperty(value = "是否升序[不传则默认降序](0降序1升序)", required = false)
    private Integer isAsc;
}
