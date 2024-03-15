/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.service;

import java.util.List;

import com.zcy.rescue.passenger.entity.ChartInfo;
import com.zcy.rescue.passenger.vo.ChartInfoVO;

/**
 * 微信信息表的Service接口
 * 
 * @author zcy
 * @date 2024-2-5
 */
public interface ChartInfoService {

    /**
     * 根据主键删除记录
	 * @param chartInfo 微信信息表
     * @return
     */
	int deleteByPrimaryKey(ChartInfo chartInfo);
	/**
     * 根据主键批量删除记录(逻辑删除)
     * @param chartInfoVO 微信信息表
     * @return
     */
    int deleteToUpdate( ChartInfoVO chartInfoVO);
	/**
     * 根据主键批量更新状态
     * @param chartInfoVO 微信信息表
     * @return
     */
    int updateBatchStatus( ChartInfoVO chartInfoVO);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param chartInfo 微信信息表
     * @return
     */
    ChartInfo insertSelective(ChartInfo chartInfo);
	
    /**
     * 批量插入实体信息
     * @param list 微信信息表list集合
     * @return List<Long> 主键list集合
     */
    List<Long> insertBatch(List<ChartInfo> list);

    /**
     * 根据主键查询实体信息
	 * @param chartInfo 微信信息表
     * @return
     */
    ChartInfo getByPrimaryKey(ChartInfo chartInfo);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param chartInfo 微信信息表
     * @return
     */
    ChartInfo updateByPrimaryKeySelective(ChartInfo chartInfo);
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param chartInfoVO 微信信息表查询bean
     * @return
     */
    List<ChartInfo> getList(ChartInfoVO chartInfoVO);
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param chartInfoVO 微信信息表查询bean
     * @return
     */
    List<ChartInfo> getPage(ChartInfoVO chartInfoVO);
    
    /**
     * 根据查询条件获取行数
     * @param chartInfoVO 微信信息表查询bean
     * @return
     */
    int getCount(ChartInfoVO chartInfoVO);
    
     /**
     * 检查数据库中是否存在数据
     * @param chartInfo 微信信息表
     * @return
     */
   ChartInfo checkExist(ChartInfo chartInfo);
	
}
