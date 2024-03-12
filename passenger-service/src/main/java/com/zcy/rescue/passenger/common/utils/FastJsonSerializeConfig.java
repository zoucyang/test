package com.zcy.rescue.passenger.common.utils;

import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.zcy.rescue.passenger.common.annotations.ToStringField;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * FastJsonSerializeConfig
 *
 * @author: xiexindong
 * @date: 2021-07-30 17:12
 */
public class FastJsonSerializeConfig extends SerializeConfig {

    @Override
    public ObjectSerializer getObjectWriter(Class<?> clazz) {
        // 从父类缓存取
        ObjectSerializer objectSerializer = get(clazz);
        if (objectSerializer == null) {
            Class currentClass = clazz;
            firstLoop:
            while (currentClass != null) {
                // 如果位于包com.yestae下，则全部自定义序列化
                boolean useCustomSerializer = currentClass.getPackage() != null &&
                        currentClass.getPackage().getName() != null &&
                        currentClass.getPackage().getName().startsWith("com.yestae");
                if (useCustomSerializer) {
                    objectSerializer = new FastJsonJavaBeanSerializer(clazz);
                    break firstLoop;
                }
                Map<String, Field> fieldMap = MetaObject.getDeclaredField(currentClass);
                for (Map.Entry<String, Field> item : fieldMap.entrySet()) {
                    Field field = item.getValue();
                    useCustomSerializer = field.isAnnotationPresent(ToStringField.class);
                    //if (!useCustomSerializer) {
                    //    // 如果需要自动检测大小，则判断是否有Long或者BigInteger类型的字段
                    //    // 有则使用自定义FastJsonJavaBeanSerializer
                    //    if (SystemWebMvcConfigurationSupport.getJsonProperties() == null
                    //            || SystemWebMvcConfigurationSupport.getJsonProperties().isAutoDetectLong()) {
                    //        useCustomSerializer = field.getType().equals(Long.class) || field.getType().equals(BigInteger.class);
                    //    }
                    //}
                    if (useCustomSerializer) {
                        objectSerializer = new FastJsonJavaBeanSerializer(clazz);
                        break firstLoop;
                    }
                }
                currentClass = currentClass.getSuperclass();
            }
            if (objectSerializer != null) {
                put(clazz, objectSerializer);
            }
        }
        if (objectSerializer == null) {
            objectSerializer = super.getObjectWriter(clazz);
        }
        return objectSerializer;
    }
}