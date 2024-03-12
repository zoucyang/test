package com.zcy.rescue.payment.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取openId
 *
 * @author zouhx
 * @date 2024/01/30
 */
@Data
public class OpenidDTO implements Serializable {
    /**
     * 支付配置id，支付网关提供-必填
     */
    private Integer configId;

    private String code;


}
