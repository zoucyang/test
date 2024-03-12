/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.controller.passenger.vo;
 
 
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 订单表 数据传输类
 * 
 * @author zcy
 * @date 2024-1-28
 */
@ApiModel(description = "订单表")
@Data
@Accessors(chain = true)
public class PassengerOrderVO implements Serializable {

    private static final long serialVersionUID = -1706430628343L;


	@ApiModelProperty(value = "订单id")
	private Long id;
	/**
	 * 订单号
	 */
	@ApiModelProperty(value = "订单号")
	private String orderNumber;
	/**
	 * 乘客id
	 */
	@ApiModelProperty(value = "乘客id")
	private Long passengerInfoId;
	/**
	 * 乘客电话
	 */
	@ApiModelProperty(value = "乘客电话")
	private String passengerPhone;
	/**
	 * 乘客车辆id
	 */
	@ApiModelProperty(value = "乘客车辆id")
	private Long passengerCarId;
	/**
	 * 乘客车牌号
	 */
	@ApiModelProperty(value = "乘客车牌号")
	private String passengerPlateNumber;
	/**
	 * 是否新能源： 1-是，0-否
	 */
	@ApiModelProperty(value = "是否新能源： 1-是，0-否")
	private Integer isNewEnergy;
	/**
	 * 乘客服务项目类型：1-搭电,2-换胎,3-拖车,4-紧急脱困
	 */
	@ApiModelProperty(value = "乘客服务项目类型：1-搭电,2-换胎,3-拖车,4-紧急脱困")
	private Long rescueServerId;
	/**
	 * 乘客设备号
	 */
	@ApiModelProperty(value = "乘客设备号")
	private String deviceCode;

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
	 * 乘客下单起点经度
	 */
	@ApiModelProperty(value = "乘客下单起点经度")
	private String startLongitude;
	/**
	 * 乘客下单起点纬度
	 */
	@ApiModelProperty(value = "乘客下单起点纬度")
	private String startLatitude;
	/**
	 * 起点名称
	 */
	@ApiModelProperty(value = "起点名称")
	private String startAddress;
	/**
	 * 终点地址名称
	 */
	@ApiModelProperty(value = "终点地址名称")
	private String endAddress;
	/**
	 * 乘客下单时间
	 */
	@ApiModelProperty(value = "乘客下单时间")
	private Date startTime;

	/**
	 * 乘客下单终点经度
	 */
	@ApiModelProperty(value = "乘客下单终点经度")
	private String endLongitude;
	/**
	 * 乘客下单终点纬度
	 */
	@ApiModelProperty(value = "乘客下单终点纬度")
	private String endLatitude;

	/**
	 * 订单状态 0: 订单预估 1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.乘客取消订单
	 */
	@ApiModelProperty(value = "订单状态 0: 订单预估 1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.乘客取消订单")
	private Integer status;
	@ApiModelProperty(value = "叫车订单类型， 1：实时订单，2：预约订单，3：接机单，4：送机单，5：日租，6：半日租")
	private Integer serviceType=1;
	@ApiModelProperty(value = "设备来源 1: ios 2:android 3.other")
	private String source="1";
	/**
	 * 备忘录
	 */
	@ApiModelProperty(value = "备忘录")
	private String memo;
	/**
	 * create_time
	 */
	@ApiModelProperty(value = "create_time")
	private Date createTime;



}

