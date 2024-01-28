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
public class CarTypeDto  implements Serializable {

    private static final long serialVersionUID = -1706430188241L;
	
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 品牌
	 */
	@ApiModelProperty(value = "品牌")
	private String brand;
	/**
	 * 型号
	 */
	@ApiModelProperty(value = "型号")
	private String model;
	/**
	 * 座位数量
	 */
	@ApiModelProperty(value = "座位数量")
	private Integer seats;
	/**
	 * 城市
	 */
	@ApiModelProperty(value = "城市")
	private String city;
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private String typeDesc;
	/**
	 * 车型图片地址
	 */
	@ApiModelProperty(value = "车型图片地址")
	private String imgUrl;
	/**
	 * 0：未启用，1：已启用
	 */
	@ApiModelProperty(value = "0：未启用，1：已启用")
	private Integer status;
	/**
	 * 录入时间
	 */
	@ApiModelProperty(value = "录入时间")
	private Date createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	/**
	 * 操作人
	 */
	@ApiModelProperty(value = "操作人")
	private Long operatorId;


}

