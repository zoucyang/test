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
public enum ServiceErrorCodeEnum implements ICommonEnum{
    SERVICE_DATA_UNEXIST(5,"service_data_unexist", "数据不存在"),
    SERVICE_DATA_INSERT_FAIL(101,"service_data_insert_fail", "数据添加失败"),
    SERVICE_DATA_UPDATE_FAIL(102,"service_data_update_fail", "数据更新失败"),
    SERVICE_DATA_DELETE_FAIL(103,"service_data_delete_fail", "数据删除失败"),
    SERVICE_DATA_CODE_NOT_EMPTY(200,"service_data_code_not_empty","编码不能为空"),
    SERVICE_DATA_NAME_NOT_EMPTY(201,"service_data_name_not_empty", "名称不能为空"),
    SERVICE_DATA_CODE_EXIST(202,"service_data_code_exist", "编码已存在"),
    SERVICE_DATA_NAME_EXIST(203,"service_data_name_exist","名称已存在"),
    SERVICE_DATA_IDLIST_NOT_EMPTY(204,"service_data_idlist_not_empty","idList不能为空");


    private int value;
    private String name;
    private String description;

    private ServiceErrorCodeEnum(int value, String name, String description) {
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
 
    public static ServiceErrorCodeEnum getEnumByValue(int value) {
        for (ServiceErrorCodeEnum be : ServiceErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be;
            }
        }
        return null;
    }
 
    public static String getNameByValue(int value) {
        for (ServiceErrorCodeEnum be : ServiceErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be.getName();
            }
        }
        return "";
    }
 
}

