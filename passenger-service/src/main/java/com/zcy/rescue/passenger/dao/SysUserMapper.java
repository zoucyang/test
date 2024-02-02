/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.dao;

import com.zcy.rescue.passenger.entity.SysUser;
import com.zcy.rescue.passenger.vo.SysUserVO;

import java.util.List;


/**
 * 数据访问接口
 *
 * @author zcy
 * @date 2024-1-28
 */
public interface SysUserMapper {

    /**
     * 根据主键批量删除记录(逻辑删除)
     *
     * @param sysUserVO
     * @return
     */
    int deleteToUpdate(SysUserVO sysUserVO);

    /**
     * 根据主键批量更新状态
     *
     * @param sysUserVO
     * @return
     */
    int updateBatchStatus(SysUserVO sysUserVO);

    /**
     * @param list List
     * @Description: 批量新增
     */
    int insertBatch(List<SysUser> list);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     *
     * @param sysUser
     * @return
     */
    int insertSelective(SysUser sysUser);


    /**
     * 根据主键查询实体信息
     *
     * @param sysUser
     * @return
     */
    SysUser getByPrimaryKey(SysUser sysUser);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     *
     * @param sysUser
     * @return
     */
    int updateByPrimaryKeySelective(SysUser sysUser);

    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     *
     * @param sysUserVO 查询载体
     * @return
     */
    List<SysUser> getList(SysUserVO sysUserVO);

    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     *
     * @param sysUserVO 查询载体
     * @return
     */
    List<SysUser> getPage(SysUserVO sysUserVO);

    /**
     * 根据查询条件获取行数
     *
     * @param sysUserVO 查询载体
     * @return
     */
    int getCount(SysUserVO sysUserVO);
}
