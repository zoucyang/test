package com.zcy.rescue.payment.controller;

import com.zcy.rescue.payment.common.constant.Constants;
import com.zcy.rescue.payment.common.exception.BusinessException;
import com.zcy.rescue.payment.common.response.BusinessResult;
import com.zcy.rescue.payment.common.response.BusinessResultBase;
import com.zcy.rescue.payment.common.response.BusinessResultCode;
import com.zcy.rescue.payment.common.response.IBusinessResult;
import com.zcy.rescue.payment.dto.OpenidDTO;
import com.zcy.rescue.payment.dto.OrderQueryDTO;
import com.zcy.rescue.payment.dto.RefundDTO;
import com.zcy.rescue.payment.dto.UnifiedOrderDTO;
import com.zcy.rescue.payment.service.WxPayService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述: 微信支付控制器
 *
 * @author zouhx
 * @version 1.0.0
 * @date 2024/1/30 10:46
 */
@Slf4j
@RestController
@RequestMapping(value = Constants.API_PREFIX + "/wxpay")
public class WxPayController {

    @Autowired
    private WxPayService wxPayService;

    /**
     * 统一下单接口
     */
    @PostMapping(value = "/unifiedOrder")
    public IBusinessResult unifiedOrder(@RequestBody UnifiedOrderDTO unifiedOrderModel) {
        try {
            return new BusinessResult(BusinessResultCode.SUCCESS, wxPayService.unifiedOrder(unifiedOrderModel));
        } catch (BusinessException be) {
            return new BusinessResultBase(BusinessResultCode.BUSINESS_EXCEPTION, be.getMessage());
        } catch (Exception e) {
            log.error("统一下单接口异常", e);
            return new BusinessResultBase(BusinessResultCode.EXCEPTION, "统一下单接口异常");
        }
    }


    @PostMapping(value = "/openid")
    @ApiOperation("获取openId")
    public IBusinessResult openid(@RequestBody OpenidDTO openidDTO) {

        try {

            return new BusinessResult(BusinessResultCode.SUCCESS, wxPayService.getOpenid(openidDTO));

        } catch (BusinessException be) {
            return new BusinessResultBase(BusinessResultCode.BUSINESS_EXCEPTION, be.getMessage());
        } catch (Exception e) {
            log.error("获取 openid 接口异常", e);
            return new BusinessResultBase(BusinessResultCode.EXCEPTION, "获取 openid 接口异常");
        }
    }

    /**
     * 申请退款
     */
    @PostMapping(value = "/refund")
    public IBusinessResult refund(@RequestBody RefundDTO refundModel) {
        try {
            return new BusinessResult(BusinessResultCode.SUCCESS, wxPayService.refund(refundModel));
        } catch (BusinessException be) {
            return new BusinessResultBase(BusinessResultCode.BUSINESS_EXCEPTION, be.getMessage());
        } catch (Exception e) {
            log.error("申请退款接口异常", e);
            return new BusinessResultBase(BusinessResultCode.EXCEPTION, "申请退款接口异常");
        }
    }

    /**
     * 查询订单接口
     */
    @PostMapping(value = "/orderQuery")
    public IBusinessResult orderQuery(@RequestBody OrderQueryDTO orderQueryModel) {
        try {
            return new BusinessResult(BusinessResultCode.SUCCESS, wxPayService.orderQuery(orderQueryModel));
        } catch (BusinessException be) {
            return new BusinessResultBase(BusinessResultCode.BUSINESS_EXCEPTION, be.getMessage());
        } catch (Exception e) {
            log.error("查询订单接口异常", e);
            return new BusinessResultBase(BusinessResultCode.EXCEPTION, "查询订单接口异常");
        }
    }


}
