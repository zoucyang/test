package com.zcy.rescue.passenger.common.annotations;

import java.lang.annotation.*;

/**
 * 泛型化ID
 *
 * @author: xiexindong
 * @date: 2022-07-05 10:50
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface GeneralizedId {
    /**
     * 泛型化参数位置
     *
     * @return
     */
    int value() default 0;
}
