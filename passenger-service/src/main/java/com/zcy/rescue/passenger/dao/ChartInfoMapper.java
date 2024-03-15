/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.dao;

import com.zcy.rescue.passenger.entity.ChartInfo;
import com.zcy.rescue.passenger.vo.ChartInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 微信信息表数据访问接口
 * 
 * @author zcy
 * @date 2024-2-5
 */
 @Mapper
public interface ChartInfoMapper  {

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
     * @Description: 批量新增
     * @param list 微信信息表List
     */
    int insertBatch(List<ChartInfo> list);
    
    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param chartInfo 微信信息表
     * @return
     */
    int insertSelective( ChartInfo chartInfo);
    
	
    /**
     * 根据主键查询实体信息
     * @param chartInfo 微信信息表
     * @return
     */
    ChartInfo getByPrimaryKey( ChartInfo chartInfo);
    
    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param chartInfo 微信信息表
     * @return
     */
    int updateByPrimaryKeySelective( ChartInfo chartInfo);
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param chartInfoVO 微信信息表查询载体
     * @return
     */
    List<ChartInfo> getList( ChartInfoVO chartInfoVO);
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param chartInfoVO 微信信息表查询载体
     * @return
     */
    List<ChartInfo> getPage( ChartInfoVO chartInfoVO);
    
    /**
     * 根据查询条件获取行数
     * @param chartInfoVO 微信信息表查询载体
     * @return
     */
    int getCount( ChartInfoVO chartInfoVO);	
}
