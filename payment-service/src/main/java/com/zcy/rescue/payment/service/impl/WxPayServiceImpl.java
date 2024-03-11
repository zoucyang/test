package com.zcy.rescue.payment.service.impl;

import com.alibaba.fastjson.JSON;
import com.wechat.pay.java.service.payments.jsapi.model.Amount;
import com.wechat.pay.java.service.payments.jsapi.model.Detail;
import com.wechat.pay.java.service.payments.jsapi.model.Payer;
import com.wechat.pay.java.service.payments.jsapi.model.PrepayRequest;
import com.zcy.rescue.payment.common.constant.WxPayConstants;
import com.zcy.rescue.payment.common.exception.BusinessException;
import com.zcy.rescue.payment.common.util.OutTradeNoManager;
import com.zcy.rescue.payment.common.util.WXPayUtil;
import com.zcy.rescue.payment.config.WxPayConfig;
import com.zcy.rescue.payment.dto.UnifiedOrderDTO;
import com.zcy.rescue.payment.entity.PayOrderEntity;
import com.zcy.rescue.payment.enums.PayTypeEnum;
import com.zcy.rescue.payment.enums.WxTradeStateEnum;
import com.zcy.rescue.payment.service.WxPayService;
import com.zcy.rescue.payment.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

/**
 * 功能描述: 微信支付实现类
 *
 * @author zouhx
 * @version 1.0.0
 * @date 2024/1/30 10:55
 */
@Service
@Slf4j
public class WxPayServiceImpl implements WxPayService {

    @Resource
    private OutTradeNoManager outTradeNoManager;

    @Value("${wxpay.notify-url}")
    private String wxPayNotifyUrl;

    @Value("${wxpay.refund-notify-url}")
    private String wxPayRefundNotifyUrl;

    @Autowired
    private JsapiService jsapiService;


    public static void main(String[] args) {


    }

    /**
     * 统一下单接口
     *
     * @param unifiedOrderModel
     * @return
     * @throws Exception
     */
    @Override
    public UnifiedOrderVO unifiedOrder(UnifiedOrderDTO unifiedOrderModel) throws Exception {

        // 生成订单号
        String outTradeNo = outTradeNoManager.generateOutTradeNo(PayTypeEnum.WXPAY);

        // 查询支付配置
        /*PayConfigDAO payConfigDAO = payConfigManager.getPayConfigById(unifiedOrderModel.getPayConfigId());
        if (null == payConfigDAO || null == payConfigDAO.getConfig()) {
            throw new BusinessException("没有查到微信支付配置");
        }
        WxPayConfig wxPayConfig = JSON.parseObject(payConfigDAO.getConfig(), WxPayConfig.class);
        */
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppid("wxb8bbd66afda2d434");
        wxPayConfig.setMchId("1667296939");
        wxPayConfig.setAppSecret("60d14a110555d486bcdc341778332793");
        wxPayConfig.setApiKey("bYykLipU0IgCC9aFJVHJCo9TsxUWty2z");
        wxPayConfig.setSerialNumber("3C0C82AFDD1DCB561DD32BD286E4253EC7DD73B2");
        wxPayConfig.setPrivateKeyFromPath("");

        /*sb.append("  appid: ").append(StringUtil.toIndentedString(this.appid)).append("\n");
        sb.append("    mchid: ").append(StringUtil.toIndentedString(this.mchid)).append("\n");
        sb.append("    description: ").append(StringUtil.toIndentedString(this.description)).append("\n");
        sb.append("    outTradeNo: ").append(StringUtil.toIndentedString(this.outTradeNo)).append("\n");
        sb.append("    timeExpire: ").append(StringUtil.toIndentedString(this.timeExpire)).append("\n");
        sb.append("    attach: ").append(StringUtil.toIndentedString(this.attach)).append("\n");
        sb.append("    notifyUrl: ").append(StringUtil.toIndentedString(this.notifyUrl)).append("\n");
        sb.append("    goodsTag: ").append(StringUtil.toIndentedString(this.goodsTag)).append("\n");
        sb.append("    limitPay: ").append(StringUtil.toIndentedString(this.limitPay)).append("\n");
        sb.append("    supportFapiao: ").append(StringUtil.toIndentedString(this.supportFapiao)).append("\n");
        sb.append("    amount: ").append(StringUtil.toIndentedString(this.amount)).append("\n");
        sb.append("    payer: ").append(StringUtil.toIndentedString(this.payer)).append("\n");
        sb.append("    detail: ").append(StringUtil.toIndentedString(this.detail)).append("\n");
        sb.append("    sceneInfo: ").append(StringUtil.toIndentedString(this.sceneInfo)).append("\n");
        sb.append("    settleInfo: ").append(StringUtil.toIndentedString(this.settleInfo)).append("\n");*/

        PrepayRequest request = new PrepayRequest();
        request.setAppid(wxPayConfig.getAppid());
        request.setMchid(wxPayConfig.getMchId());
        request.setDescription(unifiedOrderModel.getDescription());
        request.setOutTradeNo(outTradeNo);
        request.setTimeExpire(""); //"2018-06-08T10:34:56+08:00",
        request.setAttach("");
        request.setNotifyUrl(wxPayNotifyUrl);
        //request.setSupportFapiao(false);
        Amount amount = new Amount();
        amount.setCurrency("CNY");
        amount.setTotal(new BigDecimal(unifiedOrderModel.getTotalFee()).multiply(new BigDecimal(100)).intValue());
        request.setAmount(amount);
        Payer payer = new Payer();
        payer.setOpenid(unifiedOrderModel.getOpenid());
        request.setPayer(payer);
        //商品详情
        /*Detail detail = new Detail();
        request.setDetail(detail);*/




        log.info("微信统一下单接口,请求报文={}，响应报文={}",xmlRequestParam, xmlResponseData);


        //创建订单
        insertOrder(params, responseMap, unifiedOrderModel);


        if (!responseMap.containsKey(WxPayConstants.RETURN_CODE)) {
            throw new BusinessException("没有返回状态码：" + WxPayConstants.RETURN_CODE);
        }

        String returnCode = responseMap.get(WxPayConstants.RETURN_CODE);
        if (WxPayConstants.FAIL.equals(returnCode)) {
            throw new BusinessException(responseMap.get(WxPayConstants.RETURN_MSG));
        }

        if (WxPayConstants.FAIL.equals(responseMap.get(WxPayConstants.RESULT_CODE))) {
            throw new BusinessException(responseMap.getOrDefault(WxPayConstants.ERR_CODE, "")
                    + responseMap.getOrDefault(WxPayConstants.ERR_CODE_DES, ""));
        }
        UnifiedOrderVO unifiedOrderBO = new UnifiedOrderVO();
        unifiedOrderBO.setOutTradeNo(outTradeNo);
        unifiedOrderBO.setTradeNo(responseMap.getOrDefault("transaction_id", ""));
        unifiedOrderBO.setAppId(wxPayConfig.getAppid());
        unifiedOrderBO.setTimeStamp(String.valueOf(WXPayUtil.getCurrentTimestamp()));
        unifiedOrderBO.setNonceStr(WXPayUtil.generateNonceStr());
        unifiedOrderBO.setPrepayId("prepay_id=" + responseMap.getOrDefault("prepay_id", ""));
        unifiedOrderBO.setSignType(WxPayConstants.SIGN_TYPE);
        unifiedOrderBO.setPaySign(generateSign(unifiedOrderBO, wxPayConfig.getApiKey()));
        unifiedOrderBO.setCodeUrl(responseMap.getOrDefault(WxPayConstants.CODE_URL, ""));
        unifiedOrderBO.setMwebUrl(responseMap.getOrDefault(WxPayConstants.MWEB_URL, ""));
        return unifiedOrderBO;
        return null;

    }

    /**
     * 初始化请求参数
     *
     * @return Map<String                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               ,                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               String>
     */
    private Map<String, Object> initParams(WxPayConfig wxPayConfig) {
        Map<String, Object> params = new TreeMap<>();
        params.put("appid", wxPayConfig.getAppid());
        params.put("mch_id", wxPayConfig.getMchId());
        return params;
    }

    /**
     * 创建订单
     *
     * @param responseMap Map
     * @return Integer
     */
    private Integer insertOrder(Map<String, String> params, Map<String, String> responseMap, UnifiedOrderDTO
            unifiedOrderModel) {
        PayOrderEntity orderDAO = new PayOrderEntity();
        orderDAO.setOutTradeNo(params.getOrDefault("out_trade_no", ""));
        orderDAO.setTransactionId(responseMap.getOrDefault("transaction_id", ""));
        orderDAO.setPayType(PayTypeEnum.WXPAY.getId());
        orderDAO.setTradeType(unifiedOrderModel.getTradeType());
        orderDAO.setTradeState(WxTradeStateEnum.NOTPAY.getId());
        orderDAO.setTotalFee(Integer.valueOf(params.getOrDefault("total_fee", "0")));
        orderDAO.setRemark(JSON.toJSONString(responseMap));
        orderDAO.setPayConfigId(unifiedOrderModel.getPayConfigId());
        orderDAO.setNotifyUrl(unifiedOrderModel.getNotifyUrl());
        //return orderManager.insertOrder(orderDAO);
        return 1;
    }


    @Override
    public OpenidVO getOpenid(OpenidDTO openidModel) {

        // 查询支付配置
        //PayConfigDAO payConfigDAO = payConfigManager.getPayConfigById(openidModel.getPayConfigId());
        //WxPayConfig wxPayConfig = JSON.parseObject(payConfigDAO.getConfig(), WxPayConfig.class);
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppid("wxb8bbd66afda2d434");
        wxPayConfig.setAppSecret("60d14a110555d486bcdc341778332793");


        String url = "https://api.weixin.qq.com/sns/jscode2session?"
                + "appid=" + wxPayConfig.getAppid()
                + "&secret=" + wxPayConfig.getAppSecret()
                + "&js_code=" + openidModel.getCode()
                + "&grant_type=authorization_code";

        /*String url = "https://api.weixin.qq.com/sns/oauth2/access_token?"
                + "appid=" + wxPayConfig.getAppid()
                + "&secret=" + wxPayConfig.getAppSecret()
                + "&code=" + openidModel.getCode()
                + "&grant_type=authorization_code";*/

        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            RequestConfig requestConfig = RequestConfig.custom().build();
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String responseJsonStr = EntityUtils.toString(httpEntity, "UTF-8");
            log.info("微信查询 openid, url={}, response={}", url, responseJsonStr);
            WxOpenidVO wxOpenidModel = JSON.parseObject(responseJsonStr, WxOpenidVO.class);
            if (null == wxOpenidModel.getOpenid() || StringUtils.isEmpty(wxOpenidModel)) {
                throw new BusinessException(wxOpenidModel.getErrmsg());
            }
            OpenidVO openidBO = new OpenidVO();
            openidBO.setOpenid(wxOpenidModel.getOpenid());
            openidBO.setSessionkey(wxOpenidModel.getSession_key());
            openidBO.setUnionid(wxOpenidModel.getUnionid());

            return openidBO;

        } catch (Exception e) {
            log.error("微信查询 openid 异常", e);
            throw new BusinessException("微信查询 openid 异常");
        }

    }

    /**
     * 支付结果通知
     *
     * @param request
     * @param response
     */
    @Override
    public void callback(HttpServletRequest request, HttpServletResponse response) {

    }

    /**
     * 申请退款
     *
     * @param refundModel
     * @return
     * @throws Exception
     */
    @Override
    public RefundVO refund(RefundDTO refundModel) throws Exception {
        return null;
    }

    /**
     * 退款结果通知
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     */
    @Override
    public void refundCallback(HttpServletRequest request, HttpServletResponse response) {


    }

    @Override
    public OrderQueryVO orderQuery(OrderQueryDTO orderQueryModel) {


        return null;
    }

}
