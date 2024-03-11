package com.zcy.rescue.payment.common.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * redis 管理器
 *
 * @author zouhx
 * @date 2024/02/01
 */
@Service
public class RedisManager {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value);
    }

    public Object get(String key) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate.opsForValue().get(key);
    }

    public Long getOrderId(String key) {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        if (null == valueOperations.get(key)) {
            valueOperations.set(key, 1, getExpireSeconds(), TimeUnit.SECONDS);
        } else {
            valueOperations.increment(key, 1);
        }
        return (Long) valueOperations.get(key);
    }

    private Long getExpireSeconds() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return (calendar.getTimeInMillis() - System.currentTimeMillis()) / 1000;
    }
}
