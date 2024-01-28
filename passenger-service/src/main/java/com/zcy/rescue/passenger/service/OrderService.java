/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.service;

import java.util.List;

import com.zcy.rescue.passenger.entity.Order;
import com.zcy.rescue.passenger.vo.OrderVO;

/**
 * 订单表的Service接口
 * 
 * @author zcy
 * @date 2024-1-28
 */
public interface OrderService {

    /**
     * 根据主键删除记录
	 * @param order 订单表
     * @return
     */
	int deleteByPrimaryKey(Order order);
	/**
     * 根据主键批量删除记录(逻辑删除)
     * @param orderVO 订单表
     * @return
     */
    int deleteToUpdate( OrderVO orderVO);
	/**
     * 根据主键批量更新状态
     * @param orderVO 订单表
     * @return
     */
    int updateBatchStatus( OrderVO orderVO);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param order 订单表
     * @return
     */
    Order insertSelective(Order order);
	
    /**
     * 批量插入实体信息
     * @param list 订单表list集合
     * @return List<Long> 主键list集合
     */
    List<Long> insertBatch(List<Order> list);

    /**
     * 根据主键查询实体信息
	 * @param order 订单表
     * @return
     */
    Order getByPrimaryKey(Order order);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param order 订单表
     * @return
     */
    Order updateByPrimaryKeySelective(Order order);
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param orderVO 订单表查询bean
     * @return
     */
    List<Order> getList(OrderVO orderVO);
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param orderVO 订单表查询bean
     * @return
     */
    List<Order> getPage(OrderVO orderVO);
    
    /**
     * 根据查询条件获取行数
     * @param orderVO 订单表查询bean
     * @return
     */
    int getCount(OrderVO orderVO);
    
     /**
     * 检查数据库中是否存在数据
     * @param order 订单表
     * @return
     */
   Order checkExist(Order order);
	
}
