/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.controller.managers;

import com.zcy.rescue.passenger.common.domain.DataResult;
import com.zcy.rescue.passenger.common.utils.BeanUtil;
import com.zcy.rescue.passenger.common.utils.JsonUtil;
import com.zcy.rescue.passenger.dto.PassengerInfoDto;
import com.zcy.rescue.passenger.entity.PassengerInfo;
import com.zcy.rescue.passenger.service.ChartInfoService;
import com.zcy.rescue.passenger.service.PassengerInfoService;
import com.zcy.rescue.passenger.service.UserInfoRelationService;
import com.zcy.rescue.passenger.vo.ChartInfoVO;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;


/**
 * 管理端/微信乘客信息的Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "微信乘客信息的Rest实现")
@RestController
@RequestMapping("/chartPassenger")
public class ChartPassengerController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChartPassengerController.class);

    /**
     * 注入的的Service接口实现
     */
	@Autowired
	private ChartInfoService chartInfoService;
    @Autowired
    private UserInfoRelationService userInfoRelationService;
    @Autowired
    private PassengerInfoService passengerInfoService;
	
    @PostMapping(value="/save", produces="application/json")
    /**
     *
     * 功能描述: 保存微信乘客信息
     * 1.保存微信信息
     * 2.根据手机号获取乘客信息
     * 3.如果存在乘客信息，则保存微信乘客关系表
     * 4.如果乘客信息不存在，则创建乘客信息，并保存微信乘客关系表
     *
     * @param: chartInfoVO
     * @return: com.zcy.rescue.passenger.common.domain.DataResult<com.zcy.rescue.passenger.dto.PassengerInfoDto>
     * @auther: zouco
     * @date: 2024-02-24 21:44
     */
    public DataResult<PassengerInfoDto> saveChartPassenger(@RequestBody ChartInfoVO chartInfoVO) {
        logger.debug("的save passengerInfo={}", JsonUtil.toJson(chartInfoVO));
        PassengerInfo passengerInfo = new PassengerInfo();
		BeanUtil.copyProperties(chartInfoVO, passengerInfo);
        passengerInfo = passengerInfoService.insertSelective(passengerInfo);
        logger.debug("的save result={}",JsonUtil.toJson(passengerInfo));
		if(passengerInfo != null){
			PassengerInfoDto passengerInfoDto = new PassengerInfoDto();
			BeanUtil.copyProperties(passengerInfo, passengerInfoDto);
			return DataResult.success(passengerInfoDto);
		}
		return DataResult.success(null);
		
    }

    public static void main(String[] args) {
        BigDecimal b=new BigDecimal("00.000");
        System.out.println("args = " + b);
        System.out.println("args = " + b.setScale(4));
        System.out.println("args = " + b.stripTrailingZeros());
        System.out.println("args = " + b.stripTrailingZeros().setScale(0));
    }
    
}
