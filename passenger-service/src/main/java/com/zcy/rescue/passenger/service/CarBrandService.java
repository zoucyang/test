/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.service;

import java.util.List;

import com.zcy.rescue.passenger.entity.CarBrand;
import com.zcy.rescue.passenger.vo.CarBrandVO;

/**
 * 的Service接口
 * 
 * @author zcy
 * @date 2024-1-28
 */
public interface CarBrandService {

    /**
     * 根据主键删除记录
	 * @param carBrand 
     * @return
     */
	int deleteByPrimaryKey(CarBrand carBrand);
	/**
     * 根据主键批量删除记录(逻辑删除)
     * @param carBrandVO 
     * @return
     */
    int deleteToUpdate( CarBrandVO carBrandVO);
	/**
     * 根据主键批量更新状态
     * @param carBrandVO 
     * @return
     */
    int updateBatchStatus( CarBrandVO carBrandVO);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param carBrand 
     * @return
     */
    CarBrand insertSelective(CarBrand carBrand);
	
    /**
     * 批量插入实体信息
     * @param list list集合
     * @return List<Long> 主键list集合
     */
    List<Long> insertBatch(List<CarBrand> list);

    /**
     * 根据主键查询实体信息
	 * @param carBrand 
     * @return
     */
    CarBrand getByPrimaryKey(CarBrand carBrand);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param carBrand 
     * @return
     */
    CarBrand updateByPrimaryKeySelective(CarBrand carBrand);
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param carBrandVO 查询bean
     * @return
     */
    List<CarBrand> getList(CarBrandVO carBrandVO);
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param carBrandVO 查询bean
     * @return
     */
    List<CarBrand> getPage(CarBrandVO carBrandVO);
    
    /**
     * 根据查询条件获取行数
     * @param carBrandVO 查询bean
     * @return
     */
    int getCount(CarBrandVO carBrandVO);
    
     /**
     * 检查数据库中是否存在数据
     * @param carBrand 
     * @return
     */
   CarBrand checkExist(CarBrand carBrand);
	
}
