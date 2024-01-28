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
public class SysUserDto  implements Serializable {

    private static final long serialVersionUID = -1706430626098L;
	
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private Long id;
	/**
	 * 角色：1-乘客、2-司机
	 */
	@ApiModelProperty(value = "角色：1-乘客、2-司机")
	private Long roleType;
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private String username;
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private String password;
	/**
	 * 密码的盐
	 */
	@ApiModelProperty(value = "密码的盐")
	private String salt;
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private String phone;
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private Integer isDeleted;
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private Integer status;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;
	/**
	 * 最后登录时间
	 */
	@ApiModelProperty(value = "最后登录时间")
	private Date lastLoginTime;
	/**
	 * 最后修改人ID
	 */
	@ApiModelProperty(value = "最后修改人ID")
	private Long modifyId;
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private Date lastUpdatePasswordTime;


}

