package com.zcy.rescue.passenger.common.utils;

import com.zcy.rescue.passenger.common.enums.CommonErrorCodeEnum;
import com.zcy.rescue.passenger.common.exceptions.SystemException;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: base
 * @description: 对象实体处理工具类
 * @author: zouco
 * @create: 2019-08-27 18:04
 **/
public class BeanUtil {

    /**
     * 对象转换
     *
     * @param source 原对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 对象转换
     *
     * @param source           原对象
     * @param target           目标对象
     * @param ignoreProperties 忽略属性
     */
    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        if (source == null || target == null) {
            return;
        }
        BeanUtils.copyProperties(source, target, ignoreProperties);
    }

    /**
     * 对象转换
     *
     * @param source 原对象
     * @param clazz  目标对象类型
     * @author
     */
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        T target;
        try {
            target = clazz.newInstance();
        } catch (Exception e) {
            throw new SystemException(CommonErrorCodeEnum.BEAN_INSTANCE.getErrorCode(),
                    CommonErrorCodeEnum.BEAN_INSTANCE.getErrorReason());
        }
        copyProperties(source, target);
        return target;
    }

    /**
     * 以BeanCopier方式将给定集合转换为指定类型的集合
     *
     * @param sourceList 要转换的集合
     * @param clazz      要转换为的类型
     * @param <S>        源集合元素对象类型
     * @param <T>        目标集合元素对象类型
     * @return
     * @author xiexindong
     * @date 2017-07-28
     */
    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> clazz) {
        if (sourceList == null) {
            throw new SystemException(CommonErrorCodeEnum.BEAN_COPY_SOURCE_IS_NULL.getErrorCode(),
                    CommonErrorCodeEnum.BEAN_COPY_SOURCE_IS_NULL.getErrorReason());
        }
        List<T> targetList;

        if (sourceList.size() > 0) {
            targetList = new ArrayList<>(sourceList.size());
            for (S item : sourceList) {
                T target = copyProperties(item, clazz);
                targetList.add(target);
            }
        } else {
            targetList = new ArrayList<>(0);
        }
        return targetList;
    }

    /**
     * 获取类的所有字段
     *
     * @param clazz
     * @param fieldMap
     * @return
     */
    public static Map<String, Field> getDeclaredField(Class clazz, Map<String, Field> fieldMap) {
        if (fieldMap == null) {
            fieldMap = new HashMap<>();
        }
        if (clazz.getPackage().getName().startsWith("com.yestae")) {
            Field[] objectDeclaredField = clazz.getDeclaredFields();
            for (Field item : objectDeclaredField) {
                String name = item.getName();
                if (!fieldMap.containsKey(name)) {
                    fieldMap.put(name, item);
                }
            }
            fieldMap = getDeclaredField(clazz.getSuperclass(), fieldMap);
        }
        return fieldMap;
    }
}
