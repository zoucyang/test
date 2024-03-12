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
 * 用户车辆关系表 查询bean
 * 
 * @author zcy
 * @date 2024-1-28
 */
@ApiModel(description = "用户车辆关系表")
@Data
@Accessors(chain = true)
public class UserCarVO  extends ConditionVO<UserCarVO,Serializable> {

    private static final long serialVersionUID = -1706430628588L;

		/**
		 * 
		 */
		@ApiModelProperty(value = "")
		private Long id;
		/**
		 * 用户id
		 */
		@ApiModelProperty(value = "用户id")
		private Long userId;
		/**
		 * 车型id
		 */
		@ApiModelProperty(value = "车型id")
		private Long carId;
		/**
		 * 角色类型
		 */
		@ApiModelProperty(value = "角色类型")
		private Long roleType;
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

