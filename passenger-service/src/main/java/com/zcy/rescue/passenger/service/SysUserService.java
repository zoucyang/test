/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.service;

import java.util.List;

import com.zcy.rescue.passenger.entity.SysUser;
import com.zcy.rescue.passenger.vo.SysUserVO;

/**
 * 的Service接口
 * 
 * @author zcy
 * @date 2024-1-28
 */
public interface SysUserService {

    /**
     * 根据主键删除记录
	 * @param sysUser 
     * @return
     */
	int deleteByPrimaryKey(SysUser sysUser);
	/**
     * 根据主键批量删除记录(逻辑删除)
     * @param sysUserVO 
     * @return
     */
    int deleteToUpdate( SysUserVO sysUserVO);
	/**
     * 根据主键批量更新状态
     * @param sysUserVO 
     * @return
     */
    int updateBatchStatus( SysUserVO sysUserVO);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param sysUser 
     * @return
     */
    SysUser insertSelective(SysUser sysUser);
	
    /**
     * 批量插入实体信息
     * @param list list集合
     * @return List<Long> 主键list集合
     */
    List<Long> insertBatch(List<SysUser> list);

    /**
     * 根据主键查询实体信息
	 * @param sysUser 
     * @return
     */
    SysUser getByPrimaryKey(SysUser sysUser);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param sysUser 
     * @return
     */
    SysUser updateByPrimaryKeySelective(SysUser sysUser);
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param sysUserVO 查询bean
     * @return
     */
    List<SysUser> getList(SysUserVO sysUserVO);
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param sysUserVO 查询bean
     * @return
     */
    List<SysUser> getPage(SysUserVO sysUserVO);
    
    /**
     * 根据查询条件获取行数
     * @param sysUserVO 查询bean
     * @return
     */
    int getCount(SysUserVO sysUserVO);
    
     /**
     * 检查数据库中是否存在数据
     * @param sysUser 
     * @return
     */
   SysUser checkExist(SysUser sysUser);
	
}
