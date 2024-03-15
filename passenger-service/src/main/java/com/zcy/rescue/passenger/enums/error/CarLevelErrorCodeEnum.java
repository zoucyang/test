/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.enums.error;

import com.zcy.rescue.passenger.common.enums.ICommonEnum;

/**
 * 车辆级别列表
 * 
 * @author zcy
 * @date 2024-1-28
 */
public enum CarLevelErrorCodeEnum implements ICommonEnum{
    CARLEVEL_DATA_UNEXIST(5,"carlevel_data_unexist", "车辆级别列表数据不存在"),
    CARLEVEL_DATA_INSERT_FAIL(101,"carlevel_data_insert_fail", "车辆级别列表数据添加失败"),
    CARLEVEL_DATA_UPDATE_FAIL(102,"carlevel_data_update_fail", "车辆级别列表数据更新失败"),
    CARLEVEL_DATA_DELETE_FAIL(103,"carlevel_data_delete_fail", "车辆级别列表数据删除失败"),
    CARLEVEL_DATA_CODE_NOT_EMPTY(200,"carlevel_data_code_not_empty","车辆级别列表编码不能为空"),
    CARLEVEL_DATA_NAME_NOT_EMPTY(201,"carlevel_data_name_not_empty", "车辆级别列表名称不能为空"),
    CARLEVEL_DATA_CODE_EXIST(202,"carlevel_data_code_exist", "车辆级别列表编码已存在"),
    CARLEVEL_DATA_NAME_EXIST(203,"carlevel_data_name_exist","车辆级别列表名称已存在"),
    CARLEVEL_DATA_IDLIST_NOT_EMPTY(204,"carlevel_data_idlist_not_empty","车辆级别列表idList不能为空");


    private int value;
    private String name;
    private String description;

    private CarLevelErrorCodeEnum(int value, String name, String description) {
        this.value = value;
        this.name = name;
        this.description = description;
    }

    @Override
    public int getValue() {
        return this.value;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescriptions() {
        return this.description;
    }
 
    public static CarLevelErrorCodeEnum getEnumByValue(int value) {
        for (CarLevelErrorCodeEnum be : CarLevelErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be;
            }
        }
        return null;
    }
 
    public static String getNameByValue(int value) {
        for (CarLevelErrorCodeEnum be : CarLevelErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be.getName();
            }
        }
        return "";
    }
 
}

