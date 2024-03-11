/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.controller.driver;

import com.zcy.rescue.passenger.common.domain.DataResult;
import com.zcy.rescue.passenger.common.domain.PageResult;
import com.zcy.rescue.passenger.common.utils.BeanUtil;
import com.zcy.rescue.passenger.common.utils.JsonUtil;
import com.zcy.rescue.passenger.controller.driver.vo.DriverOrderVO;
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
 * 司机端/司机订单Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "司机订单Rest实现")
@RestController
@RequestMapping("/driver/order")
public class DriverOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(DriverOrderController.class);

    /**
     * 注入的司机订单表的Service接口实现
     */
	@Autowired
	private OrderService orderService;

	@ApiOperation("司机接单")
	@PostMapping(value="/update", produces="application/json")
	public DataResult<OrderDto> update(@RequestBody DriverOrderVO orderVO) {
		logger.debug("司机订单表的update order={}", JsonUtil.toJson(orderVO));
		Order order = new Order();
		BeanUtil.copyProperties(orderVO, order);
		order = orderService.updateByPrimaryKeySelective(order);
		logger.debug("司机订单表的update result={}",JsonUtil.toJson(order));
		if(order != null){
			OrderDto orderDto = new OrderDto();
			BeanUtil.copyProperties(order, orderDto);
			return DataResult.success(orderDto);
		}
		return DataResult.success(null);
	}


	@ApiOperation("获取司机订单信息")
	@PostMapping(value="/get", produces="application/json")
	public DataResult<OrderDto> get (@RequestBody DriverOrderVO orderVO) {
		logger.debug("司机订单表的get orderVO={}",JsonUtil.toJson(orderVO));
		Order order = new Order();
		BeanUtil.copyProperties(orderVO, order);
		order = orderService.getByPrimaryKey(order);
		logger.debug("司机订单表的get result={}", order);
		OrderDto orderDto = new OrderDto();
		BeanUtil.copyProperties(order, orderDto);
		return DataResult.success(orderDto);
	}

	@ApiOperation("获取司机订单列表")
	@PostMapping(value="/getPage", produces="application/json")
	public PageResult<OrderDto> getPage(@RequestBody DriverOrderVO orderVO) {
		logger.debug("司机订单表的getListWithPage orderVO={}", JsonUtil.toJson( orderVO));
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
