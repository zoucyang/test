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
 * 微信信息表
 * 
 * @author zcy
 * @date 2024-2-5
 */
@Data
@Accessors(chain = true)
public class ChartInfo   implements Serializable {

    private static final long serialVersionUID = -1707064052218L;
	
	/**
	 * 
	 */
	private Object id;
	/**
	 * 角色：1-乘客、2-司机
	 */
	private Object roleType;
	/**
	 * 企业id
	 */
	private Object unionId;
	/**
	 * 应用id
	 */
	private Object appId;
	/**
	 * 用户id
	 */
	private Object openId;
	/**
	 * 手机号
	 */
	private Object phone;
	/**
	 * 昵称
	 */
	private Object nikeName;
	/**
	 * 头像
	 */
	private Object acatarUrl;
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


