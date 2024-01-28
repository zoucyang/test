/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.enums;

import com.zcy.rescue.passenger.common.enums.ICommonEnum;

/**
 * 司机信息表
 * 
 * @author zcy
 * @date 2024-1-28
 */
public enum DriverInfoErrorCodeEnum implements ICommonEnum{
    DRIVERINFO_DATA_UNEXIST(5,"driverinfo_data_unexist", "司机信息表数据不存在"),
    DRIVERINFO_DATA_INSERT_FAIL(101,"driverinfo_data_insert_fail", "司机信息表数据添加失败"),
    DRIVERINFO_DATA_UPDATE_FAIL(102,"driverinfo_data_update_fail", "司机信息表数据更新失败"),
    DRIVERINFO_DATA_DELETE_FAIL(103,"driverinfo_data_delete_fail", "司机信息表数据删除失败"),
    DRIVERINFO_DATA_CODE_NOT_EMPTY(200,"driverinfo_data_code_not_empty","司机信息表编码不能为空"),
    DRIVERINFO_DATA_NAME_NOT_EMPTY(201,"driverinfo_data_name_not_empty", "司机信息表名称不能为空"),
    DRIVERINFO_DATA_CODE_EXIST(202,"driverinfo_data_code_exist", "司机信息表编码已存在"),
    DRIVERINFO_DATA_NAME_EXIST(203,"driverinfo_data_name_exist","司机信息表名称已存在"),
    DRIVERINFO_DATA_IDLIST_NOT_EMPTY(204,"driverinfo_data_idlist_not_empty","司机信息表idList不能为空");


    private int value;
    private String name;
    private String description;

    private DriverInfoErrorCodeEnum(int value, String name, String description) {
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
 
    public static DriverInfoErrorCodeEnum getEnumByValue(int value) {
        for (DriverInfoErrorCodeEnum be : DriverInfoErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be;
            }
        }
        return null;
    }
 
    public static String getNameByValue(int value) {
        for (DriverInfoErrorCodeEnum be : DriverInfoErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be.getName();
            }
        }
        return "";
    }
 
}

