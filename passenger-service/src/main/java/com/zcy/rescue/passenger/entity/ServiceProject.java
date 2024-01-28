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
public class ServiceProject implements Serializable {

    private static final long serialVersionUID = -1706430626088L;
	
	/**
	 * 
	 */
	private Long id;
	/**
	 * 城市编码
	 */
	private String cityCode;
	/**
	 * 服务类型id
	 */
	private Long serviceTypeId;
	/**
	 * 服务类型名称
	 */
	private String serviceTypeName;
	/**
	 * 服务开启状态 0暂停 1开启
	 */
	private Integer serviceStatus;
	/**
	 * 操作人id
	 */
	private Long operatorId;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;


}


