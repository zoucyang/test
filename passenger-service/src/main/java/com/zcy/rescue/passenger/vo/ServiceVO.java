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
public class ServiceVO  extends ConditionVO<ServiceVO,Serializable> {

    private static final long serialVersionUID = -1706430627563L;

		/**
		 * 
		 */
		@ApiModelProperty(value = "")
		private Long id;
		/**
		 * 城市编码
		 */
		@ApiModelProperty(value = "城市编码")
		private String cityCode;
		/**
		 * 服务类型id
		 */
		@ApiModelProperty(value = "服务类型id")
		private Long serviceTypeId;
		/**
		 * 服务类型名称
		 */
		@ApiModelProperty(value = "服务类型名称")
		private String serviceTypeName;
		/**
		 * 服务开启状态 0暂停 1开启
		 */
		@ApiModelProperty(value = "服务开启状态 0暂停 1开启")
		private Integer serviceStatus;
		/**
		 * 操作人id
		 */
		@ApiModelProperty(value = "操作人id")
		private Long operatorId;
		/**
		 * 创建时间
		 */
		@ApiModelProperty(value = "创建时间")
		private Date createTime;
		/**
		 * 更新时间
		 */
		@ApiModelProperty(value = "更新时间")
		private Date updateTime;
}

