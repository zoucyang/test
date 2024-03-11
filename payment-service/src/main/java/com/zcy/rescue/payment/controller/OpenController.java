package com.zcy.rescue.payment.controller;

import com.zcy.rescue.payment.common.constant.Constants;
import com.zcy.rescue.payment.common.exception.BusinessException;
import com.zcy.rescue.payment.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述: 外部回调控制器
 *
 * @author zouhx
 * @version 1.0.0
 * @date 2024/2/1 15:09
 */
@Slf4j
@RestController
@RequestMapping(value = Constants.OPEN__PREFIX)
public class OpenController {

    @Autowired
    private WxPayService wxPayService;

    /**
     * 支付结果通知接口-微信
     */
    @PostMapping(value = "/wxpay/callback")
    public void callback(HttpServletRequest request, HttpServletResponse response) {
        try {

            wxPayService.callback(request, response);

        } catch (BusinessException be) {
            log.warn(be.getMessage());
        } catch (Exception e) {
            log.error("微信支付结果通知接口异常", e);
        }
    }

    /**
     * 退款结果通知接口-微信
     */
    @PostMapping(value = "/wxpay/refundCallback")
    public void refundCallback(HttpServletRequest request, HttpServletResponse response) {
        try {
            wxPayService.refundCallback(request, response);
        } catch (BusinessException be) {
            log.warn(be.getMessage());
        } catch (Exception e) {
            log.error("微信退款结果通知接口异常", e);
        }
    }


}
