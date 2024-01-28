package com.zcy.rescue.passenger.common.domain;

import com.zcy.rescue.passenger.common.enums.ICommonEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 枚举Dto
 *
 * @author: xiexindong
 * @date: 2020-08-27 10:40
 */
@Data
@Accessors(chain = true)
public class YestaeEnumModel implements Serializable {
    /**
     * 值
     */
    private int value;
    /**
     * 名称
     */
    private String name;
    /**
     * 描述
     */
    private String description;

    public static YestaeEnumModel fromEnum(ICommonEnum yestaeEnum) {
        YestaeEnumModel enumModel = new YestaeEnumModel()
                .setValue(yestaeEnum.getValue())
                .setName(yestaeEnum.getName())
                .setDescription(yestaeEnum.getDescriptions());
        return enumModel;
    }
}
