/*
 * Powered By yestae atom source team
 * Web Site: https://www.yestae.com/
 * Since 2019
 */

package com.zcy.rescue.passenger.enums;

import com.zcy.rescue.passenger.common.enums.ICommonEnum;

/**
 * 订单表
 * 
 * @author zcy
 * @date 2024-1-28
 */
public enum OrderErrorCodeEnum implements ICommonEnum{
    ORDER_DATA_UNEXIST(5,"order_data_unexist", "订单表数据不存在"),
    ORDER_DATA_INSERT_FAIL(101,"order_data_insert_fail", "订单表数据添加失败"),
    ORDER_DATA_UPDATE_FAIL(102,"order_data_update_fail", "订单表数据更新失败"),
    ORDER_DATA_DELETE_FAIL(103,"order_data_delete_fail", "订单表数据删除失败"),
    ORDER_DATA_CODE_NOT_EMPTY(200,"order_data_code_not_empty","订单表编码不能为空"),
    ORDER_DATA_NAME_NOT_EMPTY(201,"order_data_name_not_empty", "订单表名称不能为空"),
    ORDER_DATA_CODE_EXIST(202,"order_data_code_exist", "订单表编码已存在"),
    ORDER_DATA_NAME_EXIST(203,"order_data_name_exist","订单表名称已存在"),
    ORDER_DATA_IDLIST_NOT_EMPTY(204,"order_data_idlist_not_empty","订单表idList不能为空");


    private int value;
    private String name;
    private String description;

    private OrderErrorCodeEnum(int value, String name, String description) {
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
 
    public static OrderErrorCodeEnum getEnumByValue(int value) {
        for (OrderErrorCodeEnum be : OrderErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be;
            }
        }
        return null;
    }
 
    public static String getNameByValue(int value) {
        for (OrderErrorCodeEnum be : OrderErrorCodeEnum.values()) {
            if (be.getValue() == value) {
                return be.getName();
            }
        }
        return "";
    }
 
}

