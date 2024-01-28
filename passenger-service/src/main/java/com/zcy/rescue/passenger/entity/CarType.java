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
public class CarType   implements Serializable {

    private static final long serialVersionUID = -1706430188307L;
	
	/**
	 * 
	 */
	private Long id;
	/**
	 * 品牌
	 */
	private String brand;
	/**
	 * 型号
	 */
	private String model;
	/**
	 * 座位数量
	 */
	private Integer seats;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 
	 */
	private String typeDesc;
	/**
	 * 车型图片地址
	 */
	private String imgUrl;
	/**
	 * 0：未启用，1：已启用
	 */
	private Integer status;
	/**
	 * 录入时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 操作人
	 */
	private Long operatorId;


}


