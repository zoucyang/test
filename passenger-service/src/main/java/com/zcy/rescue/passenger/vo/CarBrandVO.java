/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.vo;

import java.util.Date;
import java.io.Serializable;

import com.zcy.rescue.passenger.common.domain.ConditionVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.experimental.Accessors;
/**
 *  查询bean
 * 
 * @author zcy
 * @date 2024-1-28
 */
@ApiModel(description = "")
@Data
@Accessors(chain = true)
public class CarBrandVO  extends ConditionVO<CarBrandVO,Serializable> {

    private static final long serialVersionUID = -1706430185617L;

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

