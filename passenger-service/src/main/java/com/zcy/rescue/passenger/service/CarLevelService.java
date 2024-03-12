/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.service;

import java.util.List;

import com.zcy.rescue.passenger.entity.CarLevel;
import com.zcy.rescue.passenger.vo.CarLevelVO;

/**
 * 车辆级别列表的Service接口
 * 
 * @author zcy
 * @date 2024-1-28
 */
public interface CarLevelService {

    /**
     * 根据主键删除记录
	 * @param carLevel 车辆级别列表
     * @return
     */
	int deleteByPrimaryKey(CarLevel carLevel);
	/**
     * 根据主键批量删除记录(逻辑删除)
     * @param carLevelVO 车辆级别列表
     * @return
     */
    int deleteToUpdate(CarLevelVO carLevelVO);
	/**
     * 根据主键批量更新状态
     * @param carLevelVO 车辆级别列表
     * @return
     */
    int updateBatchStatus(CarLevelVO carLevelVO);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param carLevel 车辆级别列表
     * @return
     */
    CarLevel insertSelective(CarLevel carLevel);
	
    /**
     * 批量插入实体信息
     * @param list 车辆级别列表list集合
     * @return List<Long> 主键list集合
     */
    List<Long> insertBatch(List<CarLevel> list);

    /**
     * 根据主键查询实体信息
	 * @param carLevel 车辆级别列表
     * @return
     */
    CarLevel getByPrimaryKey(CarLevel carLevel);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param carLevel 车辆级别列表
     * @return
     */
    CarLevel updateByPrimaryKeySelective(CarLevel carLevel);
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param carLevelVO 车辆级别列表查询bean
     * @return
     */
    List<CarLevel> getList(CarLevelVO carLevelVO);
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param carLevelVO 车辆级别列表查询bean
     * @return
     */
    List<CarLevel> getPage(CarLevelVO carLevelVO);
    
    /**
     * 根据查询条件获取行数
     * @param carLevelVO 车辆级别列表查询bean
     * @return
     */
    int getCount(CarLevelVO carLevelVO);
    
     /**
     * 检查数据库中是否存在数据
     * @param carLevel 车辆级别列表
     * @return
     */
   CarLevel checkExist(CarLevel carLevel);
	
}
