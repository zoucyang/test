/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.dao;

import com.zcy.rescue.passenger.entity.Order;
import com.zcy.rescue.passenger.vo.OrderVO;

import java.util.List;


/**
 * 订单表数据访问接口
 *
 * @author zcy
 * @date 2024-1-28
 */
public interface OrderMapper {

    /**
     * 根据主键批量删除记录(逻辑删除)
     *
     * @param orderVO 订单表
     * @return
     */
    int deleteToUpdate(OrderVO orderVO);

    /**
     * 根据主键批量更新状态
     *
     * @param orderVO 订单表
     * @return
     */
    int updateBatchStatus(OrderVO orderVO);

    /**
     * @param list 订单表List
     * @Description: 批量新增
     */
    int insertBatch(List<Order> list);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     *
     * @param order 订单表
     * @return
     */
    int insertSelective(Order order);


    /**
     * 根据主键查询实体信息
     *
     * @param order 订单表
     * @return
     */
    Order getByPrimaryKey(Order order);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     *
     * @param order 订单表
     * @return
     */
    int updateByPrimaryKeySelective(Order order);

    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     *
     * @param orderVO 订单表查询载体
     * @return
     */
    List<Order> getList(OrderVO orderVO);

    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     *
     * @param orderVO 订单表查询载体
     * @return
     */
    List<Order> getPage(OrderVO orderVO);

    /**
     * 根据查询条件获取行数
     *
     * @param orderVO 订单表查询载体
     * @return
     */
    int getCount(OrderVO orderVO);
}
