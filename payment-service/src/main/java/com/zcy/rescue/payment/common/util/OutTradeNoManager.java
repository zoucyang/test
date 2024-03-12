package com.zcy.rescue.payment.common.util;

import com.zcy.rescue.payment.common.constant.Constants;
import com.zcy.rescue.payment.enums.PayTypeEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 订单号管理器
 * @author zouhx
 * @date 2024/02/01
 */
@Service
public class OutTradeNoManager {
    @Resource
    private RedisManager redisManager;

    public String generateOutTradeNo(PayTypeEnum payTypeEnum) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateString = simpleDateFormat.format(new Date());

        String redisKey = "";
        if (PayTypeEnum.ALIPAY.equals(payTypeEnum)) {
            redisKey = Constants.REDIS_KEY_ALIPAY_ORDER_ID;
        } else if (PayTypeEnum.WXPAY.equals(payTypeEnum)) {
            redisKey = Constants.REDIS_KEY_WXPAY_ORDER_ID;
        }

        Random random = new Random();
        Integer randomNumber = random.nextInt(9000) + 1000;
        return String.format("%s%s0%05d%d", dateString, payTypeEnum.getCode(), redisManager.getOrderId(redisKey), randomNumber);
    }

    public String generateOutRefundNo(PayTypeEnum payTypeEnum) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String dateString = simpleDateFormat.format(new Date());

        String redisKey = "";
        if (PayTypeEnum.ALIPAY.equals(payTypeEnum)) {
            redisKey = Constants.REDIS_KEY_ALIPAY_REFUND_ORDER_ID;
        } else if (PayTypeEnum.WXPAY.equals(payTypeEnum)) {
            redisKey = Constants.REDIS_KEY_WXPAY_REFUND_ORDER_ID;
        }

        Random random = new Random();
        Integer randomNumber = random.nextInt(9000) + 1000;
        return String.format("%s%s1%05d%d", dateString, payTypeEnum.getCode(), redisManager.getOrderId(redisKey), randomNumber);
    }
}
