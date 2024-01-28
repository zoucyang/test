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
public class SysUser   implements Serializable {

    private static final long serialVersionUID = -1706430626337L;
	
	/**
	 * 
	 */
	private Long id;
	/**
	 * 角色：1-乘客、2-司机
	 */
	private Long roleType;
	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;
	/**
	 * 密码的盐
	 */
	private String salt;
	/**
	 * 
	 */
	private String phone;
	/**
	 * 
	 */
	private Integer isDeleted;
	/**
	 * 
	 */
	private Integer status;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 最后修改人ID
	 */
	private Long modifyId;
	/**
	 * 
	 */
	private Date lastUpdatePasswordTime;


}


