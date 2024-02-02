/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.dao;

import com.zcy.rescue.passenger.entity.UserCar;
import com.zcy.rescue.passenger.vo.UserCarVO;

import java.util.List;


/**
 * 用户车辆关系表数据访问接口
 *
 * @author zcy
 * @date 2024-1-28
 */
public interface UserCarMapper {

    /**
     * 根据主键批量删除记录(逻辑删除)
     *
     * @param userCarVO 用户车辆关系表
     * @return
     */
    int deleteToUpdate(UserCarVO userCarVO);

    /**
     * 根据主键批量更新状态
     *
     * @param userCarVO 用户车辆关系表
     * @return
     */
    int updateBatchStatus(UserCarVO userCarVO);

    /**
     * @param list 用户车辆关系表List
     * @Description: 批量新增
     */
    int insertBatch(List<UserCar> list);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     *
     * @param userCar 用户车辆关系表
     * @return
     */
    int insertSelective(UserCar userCar);


    /**
     * 根据主键查询实体信息
     *
     * @param userCar 用户车辆关系表
     * @return
     */
    UserCar getByPrimaryKey(UserCar userCar);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     *
     * @param userCar 用户车辆关系表
     * @return
     */
    int updateByPrimaryKeySelective(UserCar userCar);

    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     *
     * @param userCarVO 用户车辆关系表查询载体
     * @return
     */
    List<UserCar> getList(UserCarVO userCarVO);

    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     *
     * @param userCarVO 用户车辆关系表查询载体
     * @return
     */
    List<UserCar> getPage(UserCarVO userCarVO);

    /**
     * 根据查询条件获取行数
     *
     * @param userCarVO 用户车辆关系表查询载体
     * @return
     */
    int getCount(UserCarVO userCarVO);
}
