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
public class CarInfoDto  implements Serializable {

    private static final long serialVersionUID = -1706430185092L;
	
	/**
	 * 车辆id
	 */
	@ApiModelProperty(value = "车辆id")
	private Long id;
	/**
	 * 车牌号
	 */
	@ApiModelProperty(value = "车牌号")
	private String plateNumber;
	/**
	 * 运营状态：-1，删除 0，上架 1，下架 2，待整备
	 */
	@ApiModelProperty(value = "运营状态：-1，删除 0，上架 1，下架 2，待整备")
	private Integer operationStatus;
	/**
	 * 上架时间
	 */
	@ApiModelProperty(value = "上架时间")
	private Date publishTime;
	/**
	 * 车辆全名
	 */
	@ApiModelProperty(value = "车辆全名")
	private String fullName;
	/**
	 * 车身颜色
	 */
	@ApiModelProperty(value = "车身颜色")
	private String color;
	/**
	 * 汽车图片
	 */
	@ApiModelProperty(value = "汽车图片")
	private String carImg;
	/**
	 * 城市
	 */
	@ApiModelProperty(value = "城市")
	private String cityCode;
	/**
	 * 是否新能源： 1-是，0-否
	 */
	@ApiModelProperty(value = "是否新能源： 1-是，0-否")
	private Integer isNewEnergy;
	/**
	 * 车辆类型
	 */
	@ApiModelProperty(value = "车辆类型")
	private Long carTypeId;
	/**
	 * 车辆级别
	 */
	@ApiModelProperty(value = "车辆级别")
	private Long carLevelId;
	/**
	 * 上牌日期
	 */
	@ApiModelProperty(value = "上牌日期")
	private Date registDate;
	/**
	 * 保险生效日期
	 */
	@ApiModelProperty(value = "保险生效日期")
	private Date insuranceStartDate;
	/**
	 * 保险失效日期
	 */
	@ApiModelProperty(value = "保险失效日期")
	private Date insuranceEndDate;
	/**
	 * 年检到期日期
	 */
	@ApiModelProperty(value = "年检到期日期")
	private Date annualEndDate;
	/**
	 * 行驶本图片地址
	 */
	@ApiModelProperty(value = "行驶本图片地址")
	private String carLicenseImg;
	/**
	 * 是否开启顺风单0：停用，1：启用
	 */
	@ApiModelProperty(value = "是否开启顺风单0：停用，1：启用")
	private Integer isFreeOrder;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注")
	private String remark;
	/**
	 * 启用停用状态，0：停用，1：启用
	 */
	@ApiModelProperty(value = "启用停用状态，0：停用，1：启用")
	private Integer useStatus;
	/**
	 * 大屏编号
	 */
	@ApiModelProperty(value = "大屏编号")
	private String largeScreenDeviceCode;
	/**
	 * 大屏品牌名称
	 */
	@ApiModelProperty(value = "大屏品牌名称")
	private String largeScreenDeviceBrand;
	/**
	 * 车机/脑设备号
	 */
	@ApiModelProperty(value = "车机/脑设备号")
	private String carScreenDeviceCode;
	/**
	 * 车机/脑品牌名称
	 */
	@ApiModelProperty(value = "车机/脑品牌名称")
	private String carScreenDeviceBrand;
	/**
	 * 操作人ID
	 */
	@ApiModelProperty(value = "操作人ID")
	private Long operatorId;
	/**
	 * 行驶总里程单位：km
	 */
	@ApiModelProperty(value = "行驶总里程单位：km")
	private Long totalMile;
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

