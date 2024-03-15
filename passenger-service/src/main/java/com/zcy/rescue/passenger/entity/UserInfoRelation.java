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
 * 用户信息关系表
 * 
 * @author zcy
 * @date 2024-2-5
 */
@Data
@Accessors(chain = true)
public class UserInfoRelation   implements Serializable {

    private static final long serialVersionUID = -1707063372188L;
	
	/**
	 * 
	 */
	private Object id;
	/**
	 * 角色：1-乘客、2-司机
	 */
	private Object roleType;
	/**
	 * 用户id
	 */
	private Object uid;
	/**
	 * 用户信息id
	 */
	private Object userId;
	/**
	 * 是否可用(0:不可用;1:可用)
	 */
	private Object enabled;
	/**
	 * 创建时间
	 */
	private Object createTime;
	/**
	 * 修改时间
	 */
	private Object updateTime;


}


