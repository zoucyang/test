/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.enums.error;

import com.zcy.rescue.passenger.common.enums.ICommonEnum;

/**
 * 
 * 
 * @author zcy
 * @date 2024-1-28
 */
public enum PassengerInfoErrorCodeEnum implements ICommonEnum{
    PASSENGERINFO_DATA_UNEXIST(5,"passengerinfo_data_unexist", "数据不存在"),
    PASSENGERINFO_DATA_INSERT_FAIL(101,"passengerinfo_data_insert_fail", "数据添加失败"),
    PASSENGERINFO_DATA_UPDATE_FAIL(102,"passengerinfo_data_update_fail", "数据更新失败"),
    PASSENGERINFO_DATA_DELETE_FAIL(103,"passengerinfo_data_delete_fail", "数据删除失败"),
    PASSENGERINFO_DATA_CODE_NOT_EMPTY(200,"passengerinfo_data_code_not_empty","编码不能为空"),
    PASSENGERINFO_DATA_NAME_NOT_EMPTY(201,"passengerinfo_data_name_not_empty", "名称不能为空"),
    PASSENGERINFO_DATA_CODE_EXIST(202,"passengerinfo_data_code_exist", "编码已存在"),
    PASSENGERINFO_DATA_NAME_EXIST(203,"passengerinfo_data_name_exist","名称已存在"),
    PASSENGERINFO_DATA_IDLIST_NOT_EMPTY(204,"passengerinfo_data_idlist_not_empty","idList不能为空");


    private int value;
    private String name;
    private String description;

    private PassengerInfoErrorCodeEnum(int value, String name, String description) {
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
 
    public static PassengerInfoErrorCodeEnum getEnumByValue(int value) {
        for (PassengerInfoErrorCodeEnum be : PassengerInfoErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be;
            }
        }
        return null;
    }
 
    public static String getNameByValue(int value) {
        for (PassengerInfoErrorCodeEnum be : PassengerInfoErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be.getName();
            }
        }
        return "";
    }
 
}

