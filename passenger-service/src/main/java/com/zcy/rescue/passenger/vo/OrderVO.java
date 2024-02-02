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
 * 订单表 查询bean
 * 
 * @author zcy
 * @date 2024-1-28
 */
@ApiModel(description = "订单表")
@Data
@Accessors(chain = true)
public class OrderVO  extends ConditionVO<OrderVO,Serializable> {

    private static final long serialVersionUID = -1706430629021L;

		/**
		 * 
		 */
		@ApiModelProperty(value = "")
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
		 * 司机id
		 */
		@ApiModelProperty(value = "司机id")
		private Long driverId;
		/**
		 * 司机状态   
0：没有司机接单   
1：司机接单  
2.  去接乘客 
3：司机到达上车点   
4：开始行程   
5：结束行程  
6：发起收款  
7：取消
		 */
		@ApiModelProperty(value = "司机状态 0：没有司机接单 1：司机接单 2.  去接乘客 3：司机到达上车点 4：开始行程 5：结束行程 6：发起收款 7：取消")
		private Integer driverStatus;
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
		 * 订单开始时间
		 */
		@ApiModelProperty(value = "订单开始时间")
		private Date orderStartTime;
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
		 * 乘客下车时间
		 */
		@ApiModelProperty(value = "乘客下车时间")
		private Date passengerGetoffTime;
		/**
		 * 乘客下车经度
		 */
		@ApiModelProperty(value = "乘客下车经度")
		private String passengerGetoffLongitude;
		/**
		 * 乘客下车纬度
		 */
		@ApiModelProperty(value = "乘客下车纬度")
		private String passengerGetoffLatitude;
		/**
		 * 他人姓名 （乘车人）
		 */
		@ApiModelProperty(value = "他人姓名 （乘车人）")
		private String otherName;
		/**
		 * 他人电话　(乘车人）
		 */
		@ApiModelProperty(value = "他人电话　(乘车人）")
		private String otherPhone;
		/**
		 * 订单类型：1：自己叫车，2：他人叫车
		 */
		@ApiModelProperty(value = "订单类型：1：自己叫车，2：他人叫车")
		private Integer orderType;
		/**
		 * 叫车订单类型，
1：实时订单，
2：预约订单，
3：接机单，
4：送机单，
5：日租，
6：半日租
		 */
		@ApiModelProperty(value = "叫车订单类型， 1：实时订单，2：预约订单，3：接机单，4：送机单，5：日租，6：半日租")
		private Integer serviceType;
		/**
		 * 订单渠道 
1.自有订单
2.高德
3.飞猪
		 */
		@ApiModelProperty(value = "订单渠道 1.自有订单 2.高德 3.飞猪")
		private Integer orderChannel;
		/**
		 * 订单状态 0: 订单预估 1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.乘客取消订单
		 */
		@ApiModelProperty(value = "订单状态 0: 订单预估 1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.乘客取消订单")
		private Integer status;
		/**
		 * 商户交易id
		 */
		@ApiModelProperty(value = "商户交易id")
		private String transactionId;
		/**
		 * 订单状态 0: 订单预估 1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消
		 */
		@ApiModelProperty(value = "订单状态 0: 订单预估 1：订单开始 2：司机接单 3：去接乘客 4：司机到达乘客起点 5：乘客上车，司机开始行程 6：到达目的地，行程结束，未支付 7：发起收款 8: 支付完成 9.订单取消")
		private String mappingId;
		/**
		 * 关联号码
		 */
		@ApiModelProperty(value = "关联号码")
		private String mappingNumber;
		/**
		 * 商户id
		 */
		@ApiModelProperty(value = "商户id")
		private String merchantId;
		/**
		 * 乘客是否评价，0：未评价，1：已评价
		 */
		@ApiModelProperty(value = "乘客是否评价，0：未评价，1：已评价")
		private Integer isEvaluate;
		/**
		 * 发票状态：
1：未开票，
2：申请开票，
3：开票中，
4：已开票,
5：退回,
		 */
		@ApiModelProperty(value = "发票状态： 1：未开票，2：申请开票，3：开票中，4：已开票, 5：退回,")
		private Integer invoiceType;
		/**
		 * 通知客服状态
0，未通知  
1,  已通知
		 */
		@ApiModelProperty(value = "通知客服状态 0，未通知 1,  已通知")
		private Integer isAnnotate;
		/**
		 * 设备来源
1: ios
2:android
3.other
		 */
		@ApiModelProperty(value = "设备来源 1: ios 2:android 3.other")
		private String source;
		/**
		 * 是否使用优惠券 
0:未使用  1:使用
		 */
		@ApiModelProperty(value = "是否使用优惠券 0:未使用  1:使用")
		private Integer useCoupon;
		/**
		 * 取消原因类型id
		 */
		@ApiModelProperty(value = "取消原因类型id")
		private Integer cancelOrderType;
		/**
		 * 1:余额
2.微信
3.支付宝
		 */
		@ApiModelProperty(value = "1:余额 2.微信 3.支付宝")
		private Integer payType;
		/**
		 * 是否已支付 0：未支付  1：已支付
		 */
		@ApiModelProperty(value = "是否已支付 0：未支付  1：已支付")
		private Integer isPaid;
		/**
		 * 是否取消  0：未取消   1：已取消
		 */
		@ApiModelProperty(value = "是否取消  0：未取消   1：已取消")
		private Integer isCancel;
		/**
		 * 调帐状态  0：未调帐  1:调账中 2.调账完毕
		 */
		@ApiModelProperty(value = "调帐状态  0：未调帐  1:调账中 2.调账完毕")
		private Integer isAdjust;
		/**
		 * 是否疑义订单 0：否  1：是
		 */
		@ApiModelProperty(value = "是否疑义订单 0：否  1：是")
		private Integer isDissent;
		/**
		 * 是否人工派单0 否 1 原来无司机, 人工派 2原来有司机，改派
		 */
		@ApiModelProperty(value = "是否人工派单0 否 1 原来无司机, 人工派 2原来有司机，改派")
		private Integer isManual;
		/**
		 * 是否是顺风单0否 1是
		 */
		@ApiModelProperty(value = "是否是顺风单0否 1是")
		private Integer isFollowing;
		/**
		 * 是否是假成功单0 否 1是
		 */
		@ApiModelProperty(value = "是否是假成功单0 否 1是")
		private Integer isFakeSuccess;
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
		/**
		 * update_time
		 */
		@ApiModelProperty(value = "update_time")
		private Date updateTime;
		/**
		 * 1：儿童用车
2：女性用车
		 */
		@ApiModelProperty(value = "1：儿童用车 2：女性用车")
		private Integer userFeature;
}

