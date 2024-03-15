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
 * 微信信息表 数据传输类
 * 
 * @author zcy
 * @date 2024-2-5
 */
@ApiModel(description = "微信信息表")
@Data
@Accessors(chain = true)
public class ChartInfoDto  implements Serializable {

    private static final long serialVersionUID = -1707064052154L;
	
	/**
	 * 
	 */
	@ApiModelProperty(value = "")
	private Object id;
	/**
	 * 角色：1-乘客、2-司机
	 */
	@ApiModelProperty(value = "角色：1-乘客、2-司机")
	private Object roleType;
	/**
	 * 企业id
	 */
	@ApiModelProperty(value = "企业id")
	private Object unionId;
	/**
	 * 应用id
	 */
	@ApiModelProperty(value = "应用id")
	private Object appId;
	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id")
	private Object openId;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "手机号")
	private Object phone;
	/**
	 * 昵称
	 */
	@ApiModelProperty(value = "昵称")
	private Object nikeName;
	/**
	 * 头像
	 */
	@ApiModelProperty(value = "头像")
	private Object acatarUrl;
	/**
	 * 是否可用(0:不可用;1:可用)
	 */
	@ApiModelProperty(value = "是否可用(0:不可用;1:可用)")
	private Object enabled;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Object createTime;
	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Object updateTime;


}

