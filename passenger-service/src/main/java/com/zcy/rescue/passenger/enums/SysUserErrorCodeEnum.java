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
public enum SysUserErrorCodeEnum implements ICommonEnum{
    SYSUSER_DATA_UNEXIST(5,"sysuser_data_unexist", "数据不存在"),
    SYSUSER_DATA_INSERT_FAIL(101,"sysuser_data_insert_fail", "数据添加失败"),
    SYSUSER_DATA_UPDATE_FAIL(102,"sysuser_data_update_fail", "数据更新失败"),
    SYSUSER_DATA_DELETE_FAIL(103,"sysuser_data_delete_fail", "数据删除失败"),
    SYSUSER_DATA_CODE_NOT_EMPTY(200,"sysuser_data_code_not_empty","编码不能为空"),
    SYSUSER_DATA_NAME_NOT_EMPTY(201,"sysuser_data_name_not_empty", "名称不能为空"),
    SYSUSER_DATA_CODE_EXIST(202,"sysuser_data_code_exist", "编码已存在"),
    SYSUSER_DATA_NAME_EXIST(203,"sysuser_data_name_exist","名称已存在"),
    SYSUSER_DATA_IDLIST_NOT_EMPTY(204,"sysuser_data_idlist_not_empty","idList不能为空");


    private int value;
    private String name;
    private String description;

    private SysUserErrorCodeEnum(int value, String name, String description) {
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
 
    public static SysUserErrorCodeEnum getEnumByValue(int value) {
        for (SysUserErrorCodeEnum be : SysUserErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be;
            }
        }
        return null;
    }
 
    public static String getNameByValue(int value) {
        for (SysUserErrorCodeEnum be : SysUserErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be.getName();
            }
        }
        return "";
    }
 
}

