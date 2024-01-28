/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.service;

import java.util.List;

import com.zcy.rescue.passenger.entity.DriverInfo;
import com.zcy.rescue.passenger.vo.DriverInfoVO;

/**
 * 司机信息表的Service接口
 * 
 * @author zcy
 * @date 2024-1-28
 */
public interface DriverInfoService {

    /**
     * 根据主键删除记录
	 * @param driverInfo 司机信息表
     * @return
     */
	int deleteByPrimaryKey(DriverInfo driverInfo);
	/**
     * 根据主键批量删除记录(逻辑删除)
     * @param driverInfoVO 司机信息表
     * @return
     */
    int deleteToUpdate( DriverInfoVO driverInfoVO);
	/**
     * 根据主键批量更新状态
     * @param driverInfoVO 司机信息表
     * @return
     */
    int updateBatchStatus( DriverInfoVO driverInfoVO);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param driverInfo 司机信息表
     * @return
     */
    DriverInfo insertSelective(DriverInfo driverInfo);
	
    /**
     * 批量插入实体信息
     * @param list 司机信息表list集合
     * @return List<Long> 主键list集合
     */
    List<Long> insertBatch(List<DriverInfo> list);

    /**
     * 根据主键查询实体信息
	 * @param driverInfo 司机信息表
     * @return
     */
    DriverInfo getByPrimaryKey(DriverInfo driverInfo);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param driverInfo 司机信息表
     * @return
     */
    DriverInfo updateByPrimaryKeySelective(DriverInfo driverInfo);
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param driverInfoVO 司机信息表查询bean
     * @return
     */
    List<DriverInfo> getList(DriverInfoVO driverInfoVO);
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param driverInfoVO 司机信息表查询bean
     * @return
     */
    List<DriverInfo> getPage(DriverInfoVO driverInfoVO);
    
    /**
     * 根据查询条件获取行数
     * @param driverInfoVO 司机信息表查询bean
     * @return
     */
    int getCount(DriverInfoVO driverInfoVO);
    
     /**
     * 检查数据库中是否存在数据
     * @param driverInfo 司机信息表
     * @return
     */
   DriverInfo checkExist(DriverInfo driverInfo);
	
}
