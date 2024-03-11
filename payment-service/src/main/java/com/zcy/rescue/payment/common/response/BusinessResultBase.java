package com.zcy.rescue.payment.common.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * API 标准输出结构
 *
 * @author lvzhongzhou
 * @date 2020/06/02
 */
public class BusinessResultBase implements IBusinessResult, Serializable {

    @Getter
    @Setter
    private int code;

    @Getter
    @Setter
    private Object message;


    public BusinessResultBase() {

    }

    public BusinessResultBase(BusinessResultCode code) {
        this.setCode(code);
    }

    public BusinessResultBase(BusinessResultCode code, String message) {
        this.setCode(code);
        this.setMessage(message);
    }

    public void setCode(BusinessResultCode code) {
        this.code = code.val();
        this.setMessage(code.msg());
    }
}