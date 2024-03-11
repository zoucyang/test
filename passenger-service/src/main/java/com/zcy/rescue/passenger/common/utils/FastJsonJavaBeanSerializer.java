package com.zcy.rescue.passenger.common.utils;

import com.alibaba.fastjson.serializer.*;
import com.zcy.rescue.passenger.common.annotations.ToStringField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.*;

/**
 * FastJsonJavaBeanSerializer
 *
 * @author: xiexindong
 * @date: 2021-07-30 17:11
 */
public class FastJsonJavaBeanSerializer extends JavaBeanSerializer {
    private static final Logger logger = LoggerFactory.getLogger(FastJsonJavaBeanSerializer.class);
    public static final String MAX_SAFE_NUMBER_TEXT = "9007199254740990";
    public static final Long MAX_SAFE_LONG = new Long(MAX_SAFE_NUMBER_TEXT);
    public static final BigInteger MAX_SAFE_BIG_INTEGER = new BigInteger(MAX_SAFE_NUMBER_TEXT);
    private static final List EMPTY_LIST = Arrays.asList();

    public FastJsonJavaBeanSerializer(Class<?> beanType) {
        super(beanType);
    }

    public FastJsonJavaBeanSerializer(Class<?> beanType, String... aliasList) {
        super(beanType, aliasList);
    }

    public FastJsonJavaBeanSerializer(Class<?> beanType, Map<String, String> aliasMap) {
        super(beanType, aliasMap);
    }

    public FastJsonJavaBeanSerializer(SerializeBeanInfo beanInfo) {
        super(beanInfo);
    }

    /**
     * 处理值
     * <p>
     * 1、当值不为null，则如果标注了ToStringField，就将值序列化为字符串
     * 2、当值为null，则如果字段类型是集合，就将值序列化为空集合
     * </p>
     *
     * @param jsonSerializer Json序列化器
     * @param beanContext    Bean上下文
     * @param object         对象
     * @param key            键
     * @param propertyValue  属性值
     * @param features       功能
     * @return
     */
    @Override
    protected Object processValue(JSONSerializer jsonSerializer, BeanContext beanContext, Object object, String key, Object propertyValue, int features) {
        Object value = super.processValue(jsonSerializer, beanContext, object, key, propertyValue, features);
        boolean canProcess = beanContext != null && beanContext.getField() != null && beanContext.getField() != null;
        if (value != null) {
            if (canProcess && beanContext.getField().isAnnotationPresent(ToStringField.class)) {
                if (value instanceof Iterable) {
                    Iterable iterable = (Iterable) value;
                    List<String> newValue = new ArrayList<>();
                    for (Object item : iterable) {
                        if (item == null) {
                            continue;
                        }
                        newValue.add(item.toString());
                    }
                    value = newValue;
                } else {
                    value = value.toString();
                }
            }
        } else if (canProcess && Collection.class.isAssignableFrom(beanContext.getField().getType())) {
            if (SerializerFeature.isEnabled(features, SerializerFeature.WriteNullListAsEmpty)) {
                value = EMPTY_LIST;
            }
        }
        return value;
    }
}