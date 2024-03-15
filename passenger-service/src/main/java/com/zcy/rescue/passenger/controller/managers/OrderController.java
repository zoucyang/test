/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.controller.managers;
 
import java.util.List; 
 
import com.zcy.rescue.passenger.common.domain.DataResult;
import com.zcy.rescue.passenger.common.domain.PageResult;
import com.zcy.rescue.passenger.common.exceptions.SystemException;
import com.zcy.rescue.passenger.common.utils.BeanUtil;
import com.zcy.rescue.passenger.common.utils.JsonUtil;
import com.zcy.rescue.passenger.controller.passenger.vo.PassengerOrderVO;
import com.zcy.rescue.passenger.entity.Order;
import com.zcy.rescue.passenger.enums.error.OrderErrorCodeEnum;
import com.zcy.rescue.passenger.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 管理端/订单表的Rest实现
 *
 * @author zcy
 * @date 2024-1-28
 */
 @Api(tags = "订单表的Rest实现")
@RestController
@RequestMapping("/order")
public class OrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    /**
     * 注入的订单表的Service接口实现
     */
	@Autowired
	private OrderService orderService;
	
    @PostMapping(value="/save", produces="application/json")
	
    public DataResult<PassengerOrderVO> save(@RequestBody com.zcy.rescue.passenger.vo.OrderVO orderVO) {
        logger.debug("订单表的save order={}", JsonUtil.toJson(orderVO));
        Order order = new Order();
		BeanUtil.copyProperties(orderVO, order);
        order = orderService.insertSelective(order);
        logger.debug("订单表的save result={}",JsonUtil.toJson(order));
		if(order != null){
			PassengerOrderVO orderDto = new PassengerOrderVO();
			BeanUtil.copyProperties(order, orderDto);
			return DataResult.success(orderDto);
		}
		return DataResult.success(null);
		
    }
    
	
    @PostMapping(value="/update", produces="application/json")
    public DataResult<PassengerOrderVO> update(@RequestBody com.zcy.rescue.passenger.vo.OrderVO orderVO) {
        logger.debug("订单表的update order={}", JsonUtil.toJson(orderVO));
        Order order = new Order();
		BeanUtil.copyProperties(orderVO, order);
        order = orderService.updateByPrimaryKeySelective(order);
        logger.debug("订单表的update result={}",JsonUtil.toJson(order));
		if(order != null){
			PassengerOrderVO orderDto = new PassengerOrderVO();
			BeanUtil.copyProperties(order, orderDto);
			return DataResult.success(orderDto);
		}
		return DataResult.success(null);
    }
	/**
     * 根据主键删除记录(逻辑删除)
	 * @param orderVO 订单表
     * @return
     */
    @ApiOperation("根据主键删除记录(逻辑删除)")
    @PostMapping(value="/delete", produces="application/json")
    public DataResult<com.zcy.rescue.passenger.vo.OrderVO> delete(@RequestBody com.zcy.rescue.passenger.vo.OrderVO orderVO) {
        logger.debug("订单表的delete order={}", JsonUtil.toJson(orderVO));
        Order order = new Order();
        BeanUtil.copyProperties(orderVO, order);
        int result = orderService.deleteByPrimaryKey(order);
        logger.debug("订单表的delete result={}",result); 
		if (result != 1) {
            throw new SystemException(OrderErrorCodeEnum.ORDER_DATA_DELETE_FAIL.getValue()
                    , OrderErrorCodeEnum.ORDER_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(orderVO);
    }
	/**
     * 根据主键批量删除记录(逻辑删除)
	 * @param orderVO 订单表
     * @return
     */
	@ApiOperation("根据主键批量删除记录(逻辑删除),idList必须传值")
    @PostMapping(value="/deleteBatch", produces="application/json")
    public DataResult<com.zcy.rescue.passenger.vo.OrderVO> deleteBatch(@RequestBody com.zcy.rescue.passenger.vo.OrderVO orderVO) {
        logger.debug("订单表的deleteBatch order={}", JsonUtil.toJson(orderVO)); 
        int result = orderService.deleteToUpdate(orderVO);
        logger.debug("订单表的deleteBatch result={}",result); 
		if (result < 1) {
            throw new SystemException(OrderErrorCodeEnum.ORDER_DATA_DELETE_FAIL.getValue()
                    , OrderErrorCodeEnum.ORDER_DATA_DELETE_FAIL.getDescriptions());
		}  
		return DataResult.success(orderVO);
    }
    /**
     * 根据主键批量更新状态
	 * @param orderVO 订单表
     * @return
     */
	@ApiOperation("根据主键批量更新状态,idList必须传值")
    @PostMapping(value="/updateBatchStatus", produces="application/json")
    public DataResult<com.zcy.rescue.passenger.vo.OrderVO> updateBatchStatus(@RequestBody com.zcy.rescue.passenger.vo.OrderVO orderVO) {
        logger.debug("订单表的updateBatchStatus order={}", JsonUtil.toJson(orderVO)); 
        int result = orderService.updateBatchStatus(orderVO);
        logger.debug("订单表的updateBatchStatus result={}",result); 
		if (result < 1) { 
            throw new SystemException(OrderErrorCodeEnum.ORDER_DATA_UPDATE_FAIL.getValue()
                    , OrderErrorCodeEnum.ORDER_DATA_UPDATE_FAIL.getDescriptions());
		}  
		return DataResult.success(orderVO);
    }
    
    /**
     * 批量添加数据
     * @param orderList 字典管理
     * @return
     */
    @ApiOperation("批量添加数据")
    @PostMapping(value="/insertBatch", produces="application/json")
    public DataResult<List<Long>> insertBatch(@RequestBody List<Order> orderList) {
        logger.debug("订单表insertBatch order={}", JsonUtil.toJson(orderList));
        List<Long> ids = orderService.insertBatch(orderList);
        logger.debug("订单表insertBatch result={}",JsonUtil.toJson(ids));
        if (CollectionUtils.isEmpty(ids)) {
            throw new SystemException(OrderErrorCodeEnum.ORDER_DATA_INSERT_FAIL.getValue()
                    , OrderErrorCodeEnum.ORDER_DATA_INSERT_FAIL.getDescriptions());
        }
        return DataResult.success(ids);
    }
	
    @PostMapping(value="/get", produces="application/json")
    public DataResult<PassengerOrderVO> get (@RequestBody com.zcy.rescue.passenger.vo.OrderVO orderVO) {
		logger.debug("订单表的get orderVO={}",JsonUtil.toJson(orderVO));
        Order order = new Order();
        BeanUtil.copyProperties(orderVO, order);
		order = orderService.getByPrimaryKey(order);
		logger.debug("订单表的get result={}", order);
		PassengerOrderVO orderDto = new PassengerOrderVO();
		BeanUtil.copyProperties(order, orderDto);
		return DataResult.success(orderDto);
    }
    
	
    @PostMapping(value="/getList", produces="application/json")
    public DataResult<List<PassengerOrderVO>> getList(@RequestBody com.zcy.rescue.passenger.vo.OrderVO orderVO) {
        logger.debug("订单表的getList orderVO={}",JsonUtil.toJson( orderVO));
        List<Order> orderList = orderService.getList(orderVO);
		List<PassengerOrderVO> orderDtoList = BeanUtil.copyList(orderList, PassengerOrderVO.class);
		return DataResult.success(orderDtoList);
    }
    
    
    @PostMapping(value="/getPage", produces="application/json")
    public PageResult<PassengerOrderVO> getPage(@RequestBody com.zcy.rescue.passenger.vo.OrderVO orderVO) {
        logger.debug("订单表的getListWithPage orderVO={}", JsonUtil.toJson( orderVO));
		
		int count = orderService.getCount(orderVO);
        if (count < 1) {
            return PageResult.empty();
        }
        List<Order> orderList = orderService.getPage(orderVO);
		List<PassengerOrderVO> orderDtoList =BeanUtil.copyList(orderList, PassengerOrderVO.class);
        PageResult<PassengerOrderVO> orderDtoPageResult = PageResult.page(orderVO,count,orderDtoList);
		return orderDtoPageResult;
    }
    
    
	
    @PostMapping(value="/count", produces="application/json")
    public DataResult<Integer> getCount(@RequestBody com.zcy.rescue.passenger.vo.OrderVO orderVO) {
        logger.debug("订单表的getCount orderVO={}", JsonUtil.toJson(orderVO));
        int count = orderService.getCount(orderVO);
        logger.debug("订单表的getCount count={}", count);
        return DataResult.success(count);
    }
    

 
    
}
