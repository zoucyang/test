/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.service;

import java.util.List;

import com.zcy.rescue.passenger.entity.PassengerInfo;
import com.zcy.rescue.passenger.vo.PassengerInfoVO;

/**
 * 的Service接口
 * 
 * @author zcy
 * @date 2024-1-28
 */
public interface PassengerInfoService {

    /**
     * 根据主键删除记录
	 * @param passengerInfo 
     * @return
     */
	int deleteByPrimaryKey(PassengerInfo passengerInfo);
	/**
     * 根据主键批量删除记录(逻辑删除)
     * @param passengerInfoVO 
     * @return
     */
    int deleteToUpdate(PassengerInfoVO passengerInfoVO);
	/**
     * 根据主键批量更新状态
     * @param passengerInfoVO 
     * @return
     */
    int updateBatchStatus(PassengerInfoVO passengerInfoVO);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param passengerInfo 
     * @return
     */
    PassengerInfo insertSelective(PassengerInfo passengerInfo);
	
    /**
     * 批量插入实体信息
     * @param list list集合
     * @return List<Long> 主键list集合
     */
    List<Long> insertBatch(List<PassengerInfo> list);

    /**
     * 根据主键查询实体信息
	 * @param passengerInfo 
     * @return
     */
    PassengerInfo getByPrimaryKey(PassengerInfo passengerInfo);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param passengerInfo 
     * @return
     */
    PassengerInfo updateByPrimaryKeySelective(PassengerInfo passengerInfo);
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param passengerInfoVO 查询bean
     * @return
     */
    List<PassengerInfo> getList(PassengerInfoVO passengerInfoVO);
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param passengerInfoVO 查询bean
     * @return
     */
    List<PassengerInfo> getPage(PassengerInfoVO passengerInfoVO);
    
    /**
     * 根据查询条件获取行数
     * @param passengerInfoVO 查询bean
     * @return
     */
    int getCount(PassengerInfoVO passengerInfoVO);
    
     /**
     * 检查数据库中是否存在数据
     * @param passengerInfo 
     * @return
     */
   PassengerInfo checkExist(PassengerInfo passengerInfo);
	
}
