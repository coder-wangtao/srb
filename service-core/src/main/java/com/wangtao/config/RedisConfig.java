package com.wangtao.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class RedisConfig {
    @Resource
    RedisTemplate redisTemplate;


    //当前类对象初始化后，给装配的redisTemplate配置键和值的序列化器
    @PostConstruct
    public void init() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());  //直接将建转为字符串存入
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); //利用jackson将对象转为json字符串
    }
}
