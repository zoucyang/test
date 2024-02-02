package com.zcy.rescue.payment.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author zouhx
 * @date 2024/01/30
 */
@Getter
@Setter
@ToString
public class WxOpenidVO implements Serializable {
    private String openid;
    private String session_key;
    private String unionid;
    private String errcode;
    private String errmsg;
}
