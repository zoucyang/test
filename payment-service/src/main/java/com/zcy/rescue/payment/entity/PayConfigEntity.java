package com.zcy.rescue.payment.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author zouhx
 * @date 2024/02/02
 */
@Data
public class PayConfigEntity {

    private Integer id;

    private Byte payType;

    private Byte tradeType;

    private String config;

    private Date createTime;

    private Date updateTime;

    private Byte deleteFlag;
}
