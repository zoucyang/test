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
 *  数据传输类
 * 
 * @author zcy
 * @date 2024-1-28
 */
@ApiModel(description = "")
@Data
@Accessors(chain = true)
public class CarBrandDto  implements Serializable {

    private static final long serialVersionUID = -1706430183817L;
	
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private Integer id;
	/**
	 * 车辆品牌ID
	 */
	@ApiModelProperty(value = "车辆品牌ID")
	private Integer pid;
	/**
	 * 车辆品牌
	 */
	@ApiModelProperty(value = "车辆品牌")
	private String brand;
	/**
	 * 车辆型号
	 */
	@ApiModelProperty(value = "车辆型号")
	private String model;
	/**
	 * 标准座位数量
	 */
	@ApiModelProperty(value = "标准座位数量")
	private Integer seats;
	/**
	 * 0：未删除，1：已删除
	 */
	@ApiModelProperty(value = "0：未删除，1：已删除")
	private Integer isDelete;


}

