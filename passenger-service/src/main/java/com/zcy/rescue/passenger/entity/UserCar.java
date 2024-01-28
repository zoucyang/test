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
 * 用户车辆关系表
 * 
 * @author zcy
 * @date 2024-1-28
 */
@Data
@Accessors(chain = true)
public class UserCar   implements Serializable {

    private static final long serialVersionUID = -1706430627857L;
	
	/**
	 * 
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 车型id
	 */
	private Long carId;
	/**
	 * 角色类型
	 */
	private Long roleType;
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


