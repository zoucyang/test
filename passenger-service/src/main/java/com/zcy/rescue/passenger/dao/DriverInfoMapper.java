/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.dao;

import com.zcy.rescue.passenger.entity.DriverInfo;
import com.zcy.rescue.passenger.vo.DriverInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * 司机信息表数据访问接口
 *
 * @author zcy
 * @date 2024-1-28
 */
public interface DriverInfoMapper{

    /**
     * 根据主键批量删除记录(逻辑删除)
     *
     * @param driverInfoVO 司机信息表
     * @return
     */
    int deleteToUpdate(DriverInfoVO driverInfoVO);

    /**
     * 根据主键批量更新状态
     *
     * @param driverInfoVO 司机信息表
     * @return
     */
    int updateBatchStatus(DriverInfoVO driverInfoVO);

    /**
     * @param list 司机信息表List
     * @Description: 批量新增
     */
    int insertBatch(List<DriverInfo> list);

    /**
     * 插入实体信息，只有当字段有值时，才会构建sql
     *
     * @param driverInfo 司机信息表
     * @return
     */
    int insertSelective(DriverInfo driverInfo);


    /**
     * 根据主键查询实体信息
     *
     * @param driverInfo 司机信息表
     * @return
     */
    DriverInfo getByPrimaryKey(DriverInfo driverInfo);

    /**
     * 根据对象中的主键更新其他信息，只有当字段有值时，才会构建sql
     *
     * @param driverInfo 司机信息表
     * @return
     */
    int updateByPrimaryKeySelective(DriverInfo driverInfo);

    /**
     * 根据查询条件获取列表，不分页，就算查询条件中有分页信息，返回结果也不会分页
     *
     * @param driverInfoVO 司机信息表查询载体
     * @return
     */
    List<DriverInfo> getList(DriverInfoVO driverInfoVO);

    /**
     * 根据查询条件获取列表，分页，如果查询条件中没有分页信息，使用默认分页信息
     * 分页信息 @see com.yestae.framework.common.domain.PageVO
     *
     * @param driverInfoVO 司机信息表查询载体
     * @return
     */
    List<DriverInfo> getPage(DriverInfoVO driverInfoVO);

    /**
     * 根据查询条件获取行数
     *
     * @param driverInfoVO 司机信息表查询载体
     * @return
     */
    int getCount(DriverInfoVO driverInfoVO);
}
