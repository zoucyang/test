/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.controller.driver.vo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单表 数据传输类
 * 
 * @author zcy
 * @date 2024-1-28
 */
@ApiModel(description = "订单表")
@Data
@Accessors(chain = true)
public class DriverOrderVO implements Serializable {

    private static final long serialVersionUID = -1706430628343L;
	

	@ApiModelProperty(value = "订单id")
	private Long id;
	/**
	 * 订单号
	 */
	@ApiModelProperty(value = "订单号")
	private String orderNumber;

	/**
	 * 司机id
	 */
	@ApiModelProperty(value = "司机id")
	private Long driverId;


	/**
	 * 司机电话
	 */
	@ApiModelProperty(value = "司机电话")
	private String driverPhone;
	/**
	 * 车辆id
	 */
	@ApiModelProperty(value = "车辆id")
	private Long carId;
	/**
	 * 车牌号
	 */
	@ApiModelProperty(value = "车牌号")
	private String plateNumber;
	/**
	 * 用户位置经度
	 */
	@ApiModelProperty(value = "用户位置经度")
	private String userLongitude;
	/**
	 * 用户位置纬度
	 */
	@ApiModelProperty(value = "用户位置纬度")
	private String userLatitude;

	/**
	 * 订单开始时间
	 */
	@ApiModelProperty(value = "订单开始时间")
	private Date orderStartTime;

	/**
	 * 司机抢单时间
	 */
	@ApiModelProperty(value = "司机抢单时间")
	private Date driverGrabTime;
	/**
	 * 司机去接乘客出发时间
	 */
	@ApiModelProperty(value = "司机去接乘客出发时间")
	private Date driverStartTime;
	/**
	 * 司机到达时间
	 */
	@ApiModelProperty(value = "司机到达时间")
	private Date driverArrivedTime;
	/**
	 * 去接乘客经度
	 */
	@ApiModelProperty(value = "去接乘客经度")
	private String pickUpPassengerLongitude;
	/**
	 * 去接乘客纬度
	 */
	@ApiModelProperty(value = "去接乘客纬度")
	private String pickUpPassengerLatitude;
	/**
	 * 去接乘客地点
	 */
	@ApiModelProperty(value = "去接乘客地点")
	private String pickUpPassengerAddress;
	/**
	 * 接到乘客时间
	 */
	@ApiModelProperty(value = "接到乘客时间")
	private Date receivePassengerTime;
	/**
	 * 接到乘客经度
	 */
	@ApiModelProperty(value = "接到乘客经度")
	private String receivePassengerLongitude;
	/**
	 * 接到乘客纬度
	 */
	@ApiModelProperty(value = "接到乘客纬度")
	private String receivePassengerLatitude;

	/**
	 * 订单状态 0: 订单预估 1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.乘客取消订单
	 */
	@ApiModelProperty(value = "订单状态 0: 订单预估 1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.乘客取消订单")
	private Integer status;
	@ApiModelProperty(value = "司机状态 0：没有司机接单 1：司机接单 2.  去接乘客 3：司机到达上车点 4：开始行程 5：结束行程 6：发起收款 7：取消")
	private Integer driverStatus;

	/**
	 * 是否人工派单0 否 1 原来无司机, 人工派 2原来有司机，改派
	 */
	@ApiModelProperty(value = "是否人工派单0 否 1 原来无司机, 人工派 2原来有司机，改派")
	private Integer isManual;

	/**
	 * 备忘录
	 */
	@ApiModelProperty(value = "备忘录")
	private String memo;


}

