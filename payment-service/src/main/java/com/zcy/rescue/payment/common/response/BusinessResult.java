package com.zcy.rescue.payment.common.response;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * API 标准输出结构
 *
 * @author lvzhongzhou
 * @date 2020/06/03
 */
public class BusinessResult extends BusinessResultBase {

    @Getter
    @Setter
    private Object data;

    @Getter
    @Setter
    private long timeout;

    private JSONObject jsonObject = new JSONObject();

    public BusinessResult() {
        this.setCode(BusinessResultCode.SUCCESS);
        this.setMessage(BusinessResultCode.SUCCESS.msg());
        this.setData(jsonObject);
    }

    public BusinessResult(BusinessResultCode code) {
        this.setCode(code);
        this.setData(jsonObject);
    }

    public BusinessResult(BusinessResultCode code, String message) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(jsonObject);
    }

    public BusinessResult(BusinessResultCode code, Object data) {
        this.setCode(code);
        this.setData(data);
    }

    public BusinessResult(BusinessResultCode code, String message, Object data) {
        this.setCode(code);
        this.setMessage(message);
        this.setData(data);
    }
}