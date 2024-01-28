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
public class PassengerInfo   implements Serializable {

    private static final long serialVersionUID = -1706430628089L;
	
	/**
	 * 
	 */
	private Long id;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 学历
	 */
	private String educatioan;
	/**
	 * 生日
	 */
	private Date birthday;
	/**
	 * 乘客名称
	 */
	private String passengerName;
	/**
	 * 注册时间
	 */
	private Date registerTime;
	/**
	 * 余额
	 */
	private Long balance;
	/**
	 * 0：女，1：男
	 */
	private Integer gender;
	/**
	 * 头像
	 */
	private String headImg;
	/**
	 * 用户类型，1：个人用户，2：企业用户
	 */
	private Integer passengerType;
	/**
	 * 会员等级
	 */
	private Integer userLevel;
	/**
	 * 注册渠道 1 安卓 2 ios
	 */
	private Integer registerType;
	/**
	 * 最后一次登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 上次登陆方式:1,验证码,2密码
	 */
	private Integer lastLoginMethod;
	/**
	 * 上次登录大屏时间
	 */
	private Date lastLoginScreenTime;
	/**
	 * 上次登录大屏方式
	 */
	private String lastLoginScreenMethod;
	/**
	 * 最后一次下单时间
	 */
	private Date lastOrderTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;


}


