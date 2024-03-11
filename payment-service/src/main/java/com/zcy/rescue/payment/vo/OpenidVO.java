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
public class OpenidVO implements Serializable {

    private String openid;

    private String sessionkey;

    private String unionid;



}
