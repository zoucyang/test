/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.service;

import java.util.List;

import com.zcy.rescue.passenger.entity.CarType;
import com.zcy.rescue.passenger.vo.CarTypeVO;

/**
 * 的Service接口
 * 
 * @author zcy
 * @date 2024-1-28
 */
public interface CarTypeService {

    /**
     * 根据主键删除记录
	 * @param carType 
     * @return
     */
	int deleteByPrimaryKey(CarType carType);
	/**
     * 根据主键批量删除记录(逻辑删除)
     * @param carTypeVO 
     * @return
     */
    int deleteToUpdate(CarTypeVO carTypeVO);
	/**
     * 根据主键批量更新状态
     * @param carTypeVO 
     * @return
     */
    int updateBatchStatus(CarTypeVO carTypeVO);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param carType 
     * @return
     */
    CarType insertSelective(CarType carType);
	
    /**
     * 批量插入实体信息
     * @param list list集合
     * @return List<Long> 主键list集合
     */
    List<Long> insertBatch(List<CarType> list);

    /**
     * 根据主键查询实体信息
	 * @param carType 
     * @return
     */
    CarType getByPrimaryKey(CarType carType);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param carType 
     * @return
     */
    CarType updateByPrimaryKeySelective(CarType carType);
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param carTypeVO 查询bean
     * @return
     */
    List<CarType> getList(CarTypeVO carTypeVO);
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param carTypeVO 查询bean
     * @return
     */
    List<CarType> getPage(CarTypeVO carTypeVO);
    
    /**
     * 根据查询条件获取行数
     * @param carTypeVO 查询bean
     * @return
     */
    int getCount(CarTypeVO carTypeVO);
    
     /**
     * 检查数据库中是否存在数据
     * @param carType 
     * @return
     */
   CarType checkExist(CarType carType);
	
}
