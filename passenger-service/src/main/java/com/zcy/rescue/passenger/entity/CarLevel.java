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
 * 车辆级别列表
 * 
 * @author zcy
 * @date 2024-1-28
 */
@Data
@Accessors(chain = true)
public class CarLevel   implements Serializable {

    private static final long serialVersionUID = -1706430184343L;
	
	/**
	 * 
	 */
	private Long id;
	/**
	 * 车辆级别标签
	 */
	private String label;
	/**
	 * 车型图标
	 */
	private String icon;
	/**
	 * 
	 */
	private Date createTime;
	/**
	 * 
	 */
	private Date updateTime;
	/**
	 * 操作人ID
	 */
	private Long operatorId;
	/**
	 * 状态:0未启用1启用
	 */
	private Integer enable;


}


