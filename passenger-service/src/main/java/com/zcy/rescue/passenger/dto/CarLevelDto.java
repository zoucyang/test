/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.dto;
 
 
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 车辆级别列表 数据传输类
 * 
 * @author zcy
 * @date 2024-1-28
 */
@ApiModel(description = "车辆级别列表")
@Data
@Accessors(chain = true)
public class CarLevelDto  implements Serializable {

    private static final long serialVersionUID = -1706430184011L;
	
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 车辆级别标签
	 */
	@ApiModelProperty(value = "车辆级别标签")
	private String label;
	/**
	 * 车型图标
	 */
	@ApiModelProperty(value = "车型图标")
	private String icon;
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private Date createTime;
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private Date updateTime;
	/**
	 * 操作人ID
	 */
	@ApiModelProperty(value = "操作人ID")
	private Long operatorId;
	/**
	 * 状态:0未启用1启用
	 */
	@ApiModelProperty(value = "状态:0未启用1启用")
	private Integer enable;


}

