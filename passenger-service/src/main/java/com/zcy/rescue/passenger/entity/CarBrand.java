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
public class CarBrand   implements Serializable {

    private static final long serialVersionUID = -1706430184013L;
	
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 车辆品牌ID
	 */
	private Integer pid;
	/**
	 * 车辆品牌
	 */
	private String brand;
	/**
	 * 车辆型号
	 */
	private String model;
	/**
	 * 标准座位数量
	 */
	private Integer seats;
	/**
	 * 0：未删除，1：已删除
	 */
	private Integer isDelete;


}


