package com.zcy.rescue.passenger.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 〈功能概述〉<br>
 *
 * @className: BizDataUtil
 * @author: zcy
 * @date: 2019-11-05 10:17
 */
public class BizDataUtil {

    /**
     * 数据分批处理
     *
     * @param data
     * @param num
     * @param <T>
     * @return
     */
    public static <T> Map<String, List<T>> splitBatches(List<T> data, Integer num) {
        Map<String, List<T>> batchMap = new TreeMap<>();
        if (data == null) return batchMap;
        num = num == null || num < 1 ? 100 : num;
        List<T> temp = new ArrayList<>();
        int sum = data.size();
        int i = 0;
        int batchNum = 1;
        for (T o : data) {
            temp.add(o);
            if (num == temp.size() || i == sum - 1) {
                String key = String.format("第{%s}批, 分批条数%s,总条数%s", batchNum, temp.size(), sum);
                batchMap.put(key, temp);
                temp = new ArrayList<>();
                batchNum++;
            }
            i++;
        }
        return batchMap;
    }
}
