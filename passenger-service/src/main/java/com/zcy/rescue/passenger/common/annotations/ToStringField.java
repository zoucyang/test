package com.zcy.rescue.passenger.common.annotations;

import java.lang.annotation.*;

/**
 * 标识字段需要序列化为字符串
 *
 * @author: xiexindong
 * @date: 2019-07-23 14:04
 */
@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ToStringField {
}
