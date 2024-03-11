/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.dao;

import com.zcy.rescue.passenger.entity.CarLevel;
import com.zcy.rescue.passenger.vo.CarLevelVO;

import java.util.List;


/**
 * 车辆级别列表数据访问接口
 *
 * @author zcy
 * @date 2024-1-28
 */
public interface CarLevelMapper {

    /**
     * 根据主键批量删除记录(逻辑删除)
     *
     * @param carLevelVO 车辆级别列表
     * @return
     */
    int deleteToUpdate(CarLevelVO carLevelVO);

    /**
     * 根据主键批量更新状态
     *
     * @param carLevelVO 车辆级别列表
     * @return
     */
    int updateBatchStatus(CarLevelVO carLevelVO);

    /**
     * @param list 车辆级别列表List
     * @Description: 批量新增
     */
    int insertBatch(List<CarLevel> list);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     *
     * @param carLevel 车辆级别列表
     * @return
     */
    int insertSelective(CarLevel carLevel);


    /**
     * 根据主键查询实体信息
     *
     * @param carLevel 车辆级别列表
     * @return
     */
    CarLevel getByPrimaryKey(CarLevel carLevel);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     *
     * @param carLevel 车辆级别列表
     * @return
     */
    int updateByPrimaryKeySelective(CarLevel carLevel);

    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     *
     * @param carLevelVO 车辆级别列表查询载体
     * @return
     */
    List<CarLevel> getList(CarLevelVO carLevelVO);

    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     *
     * @param carLevelVO 车辆级别列表查询载体
     * @return
     */
    List<CarLevel> getPage(CarLevelVO carLevelVO);

    /**
     * 根据查询条件获取行数
     *
     * @param carLevelVO 车辆级别列表查询载体
     * @return
     */
    int getCount(CarLevelVO carLevelVO);
}
