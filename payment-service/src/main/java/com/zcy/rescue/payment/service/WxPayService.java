package com.zcy.rescue.payment.service;

import com.zcy.rescue.payment.dto.OpenidDTO;
import com.zcy.rescue.payment.dto.OrderQueryDTO;
import com.zcy.rescue.payment.dto.RefundDTO;
import com.zcy.rescue.payment.dto.UnifiedOrderDTO;
import com.zcy.rescue.payment.vo.OpenidVO;
import com.zcy.rescue.payment.vo.OrderQueryVO;
import com.zcy.rescue.payment.vo.RefundVO;
import com.zcy.rescue.payment.vo.UnifiedOrderVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述: 微信支付服务
 *
 * @author zouhx
 * @version 1.0.0
 * @date 2024/1/30 10:50
 */
public interface WxPayService {

    /**
     * 统一下单
     *
     * @param unifiedOrderModel
     * @return
     * @throws Exception
     */
    UnifiedOrderVO unifiedOrder(UnifiedOrderDTO unifiedOrderModel) throws Exception;

    /**
     * 获取openId
     *
     * @param openidModel
     * @return
     */
    OpenidVO getOpenid(OpenidDTO openidModel);

    /**
     * 支付结果通知
     *
     * @param request
     * @param response
     */
    void callback(HttpServletRequest request, HttpServletResponse response);

    /**
     * 申请退款
     *
     * @param refundModel
     * @return
     * @throws Exception
     */
    RefundVO refund(RefundDTO refundModel) throws Exception;

    /**
     * 退款结果通知
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    void refundCallback(HttpServletRequest request, HttpServletResponse response);

    /**
     * @param orderQueryModel OrderQueryModel
     * @return OrderQueryBO
     */
    OrderQueryVO orderQuery(OrderQueryDTO orderQueryModel);


}
