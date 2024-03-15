/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.enums.error;

import com.zcy.rescue.passenger.common.enums.ICommonEnum;

/**
 * 微信信息表
 * 
 * @author zcy
 * @date 2024-2-5
 */
public enum ChartInfoErrorCodeEnum implements ICommonEnum{
    CHARTINFO_DATA_UNEXIST(5,"chartinfo_data_unexist", "微信信息表数据不存在"),
    CHARTINFO_DATA_INSERT_FAIL(101,"chartinfo_data_insert_fail", "微信信息表数据添加失败"),
    CHARTINFO_DATA_UPDATE_FAIL(102,"chartinfo_data_update_fail", "微信信息表数据更新失败"),
    CHARTINFO_DATA_DELETE_FAIL(103,"chartinfo_data_delete_fail", "微信信息表数据删除失败"),
    CHARTINFO_DATA_CODE_NOT_EMPTY(200,"chartinfo_data_code_not_empty","微信信息表编码不能为空"),
    CHARTINFO_DATA_NAME_NOT_EMPTY(201,"chartinfo_data_name_not_empty", "微信信息表名称不能为空"),
    CHARTINFO_DATA_CODE_EXIST(202,"chartinfo_data_code_exist", "微信信息表编码已存在"),
    CHARTINFO_DATA_NAME_EXIST(203,"chartinfo_data_name_exist","微信信息表名称已存在"),
    CHARTINFO_DATA_IDLIST_NOT_EMPTY(204,"chartinfo_data_idlist_not_empty","微信信息表idList不能为空");


    private int value;
    private String name;
    private String description;

    private ChartInfoErrorCodeEnum(int value, String name, String description) {
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
 
    public static ChartInfoErrorCodeEnum getEnumByValue(int value) {
        for (ChartInfoErrorCodeEnum be : ChartInfoErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be;
            }
        }
        return null;
    }
 
    public static String getNameByValue(int value) {
        for (ChartInfoErrorCodeEnum be : ChartInfoErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be.getName();
            }
        }
        return "";
    }
 
}

