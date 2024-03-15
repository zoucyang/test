/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.controller.passenger;

import com.zcy.rescue.passenger.common.domain.DataResult;
import com.zcy.rescue.passenger.common.domain.PageResult;
import com.zcy.rescue.passenger.common.utils.BeanUtil;
import com.zcy.rescue.passenger.common.utils.JsonUtil;
import com.zcy.rescue.passenger.controller.passenger.vo.PassengerOrderVO;
import com.zcy.rescue.passenger.dto.OrderDto;
import com.zcy.rescue.passenger.entity.Order;
import com.zcy.rescue.passenger.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 乘客端/乘客订单Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "乘客订单Rest实现")
@RestController
@RequestMapping("/passenger/order")
public class PassengerOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(PassengerOrderController.class);

    /**
     * 注入的乘客订单表的Service接口实现
     */
	@Autowired
	private OrderService orderService;

	@ApiOperation("乘客下单")
    @PostMapping(value="/save", produces="application/json")
    public DataResult<OrderDto> save(@RequestBody PassengerOrderVO orderVO) {
        logger.debug("乘客订单表的save order={}", JsonUtil.toJson(orderVO));
        Order order = new Order();
		BeanUtil.copyProperties(orderVO, order);
		order.setServiceType(1).setSource("1").setDriverStatus(0).setStatus(1);
        order = orderService.insertSelective(order);
        logger.debug("乘客订单表的save result={}",JsonUtil.toJson(order));
		if(order != null){
			OrderDto orderDto = new OrderDto();
			BeanUtil.copyProperties(order, orderDto);
			return DataResult.success(orderDto);
		}
		return DataResult.success(null);
		
    }


	@ApiOperation("获取乘客订单信息")
	@PostMapping(value="/get", produces="application/json")
	public DataResult<OrderDto> get (@RequestBody PassengerOrderVO orderVO) {
		logger.debug("乘客订单表的get orderVO={}",JsonUtil.toJson(orderVO));
		Order order = new Order();
		BeanUtil.copyProperties(orderVO, order);
		order = orderService.getByPrimaryKey(order);
		logger.debug("乘客订单表的get result={}", order);
		OrderDto orderDto = new OrderDto();
		BeanUtil.copyProperties(order, orderDto);
		return DataResult.success(orderDto);
	}


	@ApiOperation("获取乘客订单列表")
	@PostMapping(value="/getPage", produces="application/json")
	public PageResult<OrderDto> getPage(@RequestBody PassengerOrderVO orderVO) {
		logger.debug("乘客订单表的getListWithPage orderVO={}", JsonUtil.toJson( orderVO));
		com.zcy.rescue.passenger.vo.OrderVO vo = BeanUtil.copyProperties(orderVO, com.zcy.rescue.passenger.vo.OrderVO.class);
		int count = orderService.getCount(vo);
		if (count < 1) {
			return PageResult.empty();
		}
		List<Order> orderList = orderService.getPage(vo);
		List<OrderDto> orderDtoList =BeanUtil.copyList(orderList, OrderDto.class);
		PageResult<OrderDto> orderDtoPageResult = PageResult.page(vo,count,orderDtoList);
		return orderDtoPageResult;
	}


}
