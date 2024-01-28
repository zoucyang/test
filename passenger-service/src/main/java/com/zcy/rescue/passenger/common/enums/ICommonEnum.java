package  com.zcy.rescue.passenger.common.enums;


import com.zcy.rescue.passenger.common.domain.YestaeEnumModel;

import java.util.List;

/**
 * 枚举接口
 *
 * @author admin
 * @date 2020-08-07 17:25
 */
public interface ICommonEnum {
    /**
     * 获取枚举值
     *
     * @return
     */
    int getValue();

    /**
     * 获取枚举名称
     *
     * @return
     */
    String getName();

    /**
     * 获取枚举的描述
     *
     * @return
     */
    String getDescriptions();

    /**
     * 获取枚举列表
     *
     * @return
     */
    default List<YestaeEnumModel> getEnumModelList() {
        return null;
    }
}