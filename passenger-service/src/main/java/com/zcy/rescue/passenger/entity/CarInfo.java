/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.entity;



import java.util.Date;
import java.io.Serializable;

import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 
 * 
 * @author zcy
 * @date 2024-1-28
 */
@Data
@Accessors(chain = true)
public class CarInfo   implements Serializable {

    private static final long serialVersionUID = -1706430185230L;
	
	/**
	 * 车辆id
	 */
	private Long id;
	/**
	 * 车牌号
	 */
	private String plateNumber;
	/**
	 * 运营状态：-1，删除 0，上架 1，下架 2，待整备
	 */
	private Integer operationStatus;
	/**
	 * 上架时间
	 */
	private Date publishTime;
	/**
	 * 车辆全名
	 */
	private String fullName;
	/**
	 * 车身颜色
	 */
	private String color;
	/**
	 * 汽车图片
	 */
	private String carImg;
	/**
	 * 城市
	 */
	private String cityCode;
	/**
	 * 是否新能源： 1-是，0-否
	 */
	private Integer isNewEnergy;
	/**
	 * 车辆类型
	 */
	private Long carTypeId;
	/**
	 * 车辆级别
	 */
	private Long carLevelId;
	/**
	 * 上牌日期
	 */
	private Date registDate;
	/**
	 * 保险生效日期
	 */
	private Date insuranceStartDate;
	/**
	 * 保险失效日期
	 */
	private Date insuranceEndDate;
	/**
	 * 年检到期日期
	 */
	private Date annualEndDate;
	/**
	 * 行驶本图片地址
	 */
	private String carLicenseImg;
	/**
	 * 是否开启顺风单0：停用，1：启用
	 */
	private Integer isFreeOrder;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 启用停用状态，0：停用，1：启用
	 */
	private Integer useStatus;
	/**
	 * 大屏编号
	 */
	private String largeScreenDeviceCode;
	/**
	 * 大屏品牌名称
	 */
	private String largeScreenDeviceBrand;
	/**
	 * 车机/脑设备号
	 */
	private String carScreenDeviceCode;
	/**
	 * 车机/脑品牌名称
	 */
	private String carScreenDeviceBrand;
	/**
	 * 操作人ID
	 */
	private Long operatorId;
	/**
	 * 行驶总里程单位：km
	 */
	private Long totalMile;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;


}


