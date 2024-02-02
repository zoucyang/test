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
 * 司机信息表 查询bean
 * 
 * @author zcy
 * @date 2024-1-28
 */
@ApiModel(description = "司机信息表")
@Data
@Accessors(chain = true)
public class DriverInfoVO  extends ConditionVO<DriverInfoVO,Serializable> {

    private static final long serialVersionUID = -1706430185100L;

		/**
		 * 
		 */
		@ApiModelProperty(value = "")
		private Long id;
		/**
		 * 司机手机号
		 */
		@ApiModelProperty(value = "司机手机号")
		private String phoneNumber;
		/**
		 * 司机主管
		 */
		@ApiModelProperty(value = "司机主管")
		private Long driverLeader;
		/**
		 * 司机姓名
		 */
		@ApiModelProperty(value = "司机姓名")
		private String driverName;
		/**
		 * 注册时间
		 */
		@ApiModelProperty(value = "注册时间")
		private Date registerTime;
		/**
		 * 余额
		 */
		@ApiModelProperty(value = "余额")
		private Long balance;
		/**
		 * 性别
		 */
		@ApiModelProperty(value = "性别")
		private Integer gender;
		/**
		 * 车辆id
		 */
		@ApiModelProperty(value = "车辆id")
		private Long carId;
		/**
		 * 是否是顺风单0否 1是
		 */
		@ApiModelProperty(value = "是否是顺风单0否 1是")
		private Integer isFollowing;
		/**
		 * 司机工作状态
0：非出车状态，
1：出车，未接订单，
2：出车，接到订单,
3：暂停接单
		 */
		@ApiModelProperty(value = "司机工作状态 0：非出车状态， 1：出车，未接订单， 2：出车，接到订单, 3：暂停接单")
		private Integer workStatus;
		/**
		 * 司机头像
		 */
		@ApiModelProperty(value = "司机头像")
		private String headImg;
		/**
		 * 城市代码
		 */
		@ApiModelProperty(value = "城市代码")
		private String cityCode;
		/**
		 * 绑定时间
		 */
		@ApiModelProperty(value = "绑定时间")
		private Date bindTime;
		/**
		 * 启用停用状态，0：停用，1：启用
		 */
		@ApiModelProperty(value = "启用停用状态，0：停用，1：启用")
		private Integer useStatus;
		/**
		 * 车机工作状态
0：车机未登录登录
1：车机登录
2：车机听单
3：车机暂停听单
4：车机收车


		 */
		@ApiModelProperty(value = "车机工作状态 0：车机未登录登录 1：车机登录 2：车机听单 3：车机暂停听单 4：车机收车 ")
		private Integer csWorkStatus;
		/**
		 * 1：已签约，0：已解约
		 */
		@ApiModelProperty(value = "1：已签约，0：已解约")
		private Integer signStatus;
		/**
		 * 创建时间
		 */
		@ApiModelProperty(value = "创建时间")
		private Date createTime;
		/**
		 * 修改时间
		 */
		@ApiModelProperty(value = "修改时间")
		private Date updateTime;
}

