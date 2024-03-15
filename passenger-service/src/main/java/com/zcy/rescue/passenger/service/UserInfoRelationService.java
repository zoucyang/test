/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.service;

import java.util.List;

import com.zcy.rescue.passenger.entity.UserInfoRelation;
import com.zcy.rescue.passenger.vo.UserInfoRelationVO;

/**
 * 用户信息关系表的Service接口
 * 
 * @author zcy
 * @date 2024-2-5
 */
public interface UserInfoRelationService {

    /**
     * 根据主键删除记录
	 * @param userInfoRelation 用户信息关系表
     * @return
     */
	int deleteByPrimaryKey(UserInfoRelation userInfoRelation);
	/**
     * 根据主键批量删除记录(逻辑删除)
     * @param userInfoRelationVO 用户信息关系表
     * @return
     */
    int deleteToUpdate( UserInfoRelationVO userInfoRelationVO);
	/**
     * 根据主键批量更新状态
     * @param userInfoRelationVO 用户信息关系表
     * @return
     */
    int updateBatchStatus( UserInfoRelationVO userInfoRelationVO);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     * @param userInfoRelation 用户信息关系表
     * @return
     */
    UserInfoRelation insertSelective(UserInfoRelation userInfoRelation);
	
    /**
     * 批量插入实体信息
     * @param list 用户信息关系表list集合
     * @return List<Long> 主键list集合
     */
    List<Long> insertBatch(List<UserInfoRelation> list);

    /**
     * 根据主键查询实体信息
	 * @param userInfoRelation 用户信息关系表
     * @return
     */
    UserInfoRelation getByPrimaryKey(UserInfoRelation userInfoRelation);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     * @param userInfoRelation 用户信息关系表
     * @return
     */
    UserInfoRelation updateByPrimaryKeySelective(UserInfoRelation userInfoRelation);
    
    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     * @param userInfoRelationVO 用户信息关系表查询bean
     * @return
     */
    List<UserInfoRelation> getList(UserInfoRelationVO userInfoRelationVO);
    
    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     * @param userInfoRelationVO 用户信息关系表查询bean
     * @return
     */
    List<UserInfoRelation> getPage(UserInfoRelationVO userInfoRelationVO);
    
    /**
     * 根据查询条件获取行数
     * @param userInfoRelationVO 用户信息关系表查询bean
     * @return
     */
    int getCount(UserInfoRelationVO userInfoRelationVO);
    
     /**
     * 检查数据库中是否存在数据
     * @param userInfoRelation 用户信息关系表
     * @return
     */
   UserInfoRelation checkExist(UserInfoRelation userInfoRelation);
	
}
