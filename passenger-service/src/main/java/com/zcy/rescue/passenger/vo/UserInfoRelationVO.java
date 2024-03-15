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
 * 用户信息关系表 查询bean
 * 
 * @author zcy
 * @date 2024-2-5
 */
@ApiModel(description = "用户信息关系表")
@Data
@Accessors(chain = true)
public class UserInfoRelationVO  extends ConditionVO<UserInfoRelationVO,Serializable>   {

    private static final long serialVersionUID = -1707063372377L;

		/**
		 * 
		 */
		@ApiModelProperty(value = "")
		private Object id;
		/**
		 * 角色：1-乘客、2-司机
		 */
		@ApiModelProperty(value = "角色：1-乘客、2-司机")
		private Object roleType;
		/**
		 * 用户id
		 */
		@ApiModelProperty(value = "用户id")
		private Object uid;
		/**
		 * 用户信息id
		 */
		@ApiModelProperty(value = "用户信息id")
		private Object userId;
		/**
		 * 是否可用(0:不可用;1:可用)
		 */
		@ApiModelProperty(value = "是否可用(0:不可用;1:可用)")
		private Object enabled;
		/**
		 * 创建时间
		 */
		@ApiModelProperty(value = "创建时间")
		private Object createTime;
		/**
		 * 修改时间
		 */
		@ApiModelProperty(value = "修改时间")
		private Object updateTime;
}

