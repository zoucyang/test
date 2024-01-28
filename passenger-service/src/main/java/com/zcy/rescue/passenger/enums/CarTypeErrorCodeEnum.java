/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.enums;

import com.zcy.rescue.passenger.common.enums.ICommonEnum;

/**
 * 
 * 
 * @author zcy
 * @date 2024-1-28
 */
public enum CarTypeErrorCodeEnum implements ICommonEnum{
    CARTYPE_DATA_UNEXIST(5,"cartype_data_unexist", "数据不存在"),
    CARTYPE_DATA_INSERT_FAIL(101,"cartype_data_insert_fail", "数据添加失败"),
    CARTYPE_DATA_UPDATE_FAIL(102,"cartype_data_update_fail", "数据更新失败"),
    CARTYPE_DATA_DELETE_FAIL(103,"cartype_data_delete_fail", "数据删除失败"),
    CARTYPE_DATA_CODE_NOT_EMPTY(200,"cartype_data_code_not_empty","编码不能为空"),
    CARTYPE_DATA_NAME_NOT_EMPTY(201,"cartype_data_name_not_empty", "名称不能为空"),
    CARTYPE_DATA_CODE_EXIST(202,"cartype_data_code_exist", "编码已存在"),
    CARTYPE_DATA_NAME_EXIST(203,"cartype_data_name_exist","名称已存在"),
    CARTYPE_DATA_IDLIST_NOT_EMPTY(204,"cartype_data_idlist_not_empty","idList不能为空");


    private int value;
    private String name;
    private String description;

    private CarTypeErrorCodeEnum(int value, String name, String description) {
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
 
    public static CarTypeErrorCodeEnum getEnumByValue(int value) {
        for (CarTypeErrorCodeEnum be : CarTypeErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be;
            }
        }
        return null;
    }
 
    public static String getNameByValue(int value) {
        for (CarTypeErrorCodeEnum be : CarTypeErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be.getName();
            }
        }
        return "";
    }
 
}

