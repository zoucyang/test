/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.enums;

import com.zcy.rescue.passenger.common.enums.ICommonEnum;

/**
 * 用户车辆关系表
 * 
 * @author zcy
 * @date 2024-1-28
 */
public enum UserCarErrorCodeEnum implements ICommonEnum{
    USERCAR_DATA_UNEXIST(5,"usercar_data_unexist", "用户车辆关系表数据不存在"),
    USERCAR_DATA_INSERT_FAIL(101,"usercar_data_insert_fail", "用户车辆关系表数据添加失败"),
    USERCAR_DATA_UPDATE_FAIL(102,"usercar_data_update_fail", "用户车辆关系表数据更新失败"),
    USERCAR_DATA_DELETE_FAIL(103,"usercar_data_delete_fail", "用户车辆关系表数据删除失败"),
    USERCAR_DATA_CODE_NOT_EMPTY(200,"usercar_data_code_not_empty","用户车辆关系表编码不能为空"),
    USERCAR_DATA_NAME_NOT_EMPTY(201,"usercar_data_name_not_empty", "用户车辆关系表名称不能为空"),
    USERCAR_DATA_CODE_EXIST(202,"usercar_data_code_exist", "用户车辆关系表编码已存在"),
    USERCAR_DATA_NAME_EXIST(203,"usercar_data_name_exist","用户车辆关系表名称已存在"),
    USERCAR_DATA_IDLIST_NOT_EMPTY(204,"usercar_data_idlist_not_empty","用户车辆关系表idList不能为空");


    private int value;
    private String name;
    private String description;

    private UserCarErrorCodeEnum(int value, String name, String description) {
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
 
    public static UserCarErrorCodeEnum getEnumByValue(int value) {
        for (UserCarErrorCodeEnum be : UserCarErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be;
            }
        }
        return null;
    }
 
    public static String getNameByValue(int value) {
        for (UserCarErrorCodeEnum be : UserCarErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be.getName();
            }
        }
        return "";
    }
 
}

