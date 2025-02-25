package com.wangtao;

import com.alibaba.fastjson.JSON;
import com.wangtao.pojo.vo.DictExcelVo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class RedisTest {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    RedisTemplate<String, Object> redisTemplate;
    //RedisTemplate 是以序列化的形式将对象存到内存中，所以对象类型必须实现序列化接口
    //也可以给RedisTemplate配置键和值的序列化器：将键和值转为字符串存入  读取数据时也可以自动将字符串转为对象


    //所有测试方法执行之前执行
//    @BeforeEach
//    void init() {
//        redisTemplate.setKeySerializer(new StringRedisSerializer());  //直接将建转为字符串存入
//        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer()); //利用jackson将对象转为json字符串
//    }

    @Test
    void test() {
        //设置String类型的k-v到redis中 并设置过期时间20分钟
        stringRedisTemplate.opsForValue().set("sk1", "world",20, TimeUnit.MINUTES);
        DictExcelVo vo = new DictExcelVo();
        vo.setId(1L);
        vo.setName("world");

        //redis存对象 转成json字符串
        stringRedisTemplate.opsForValue().set("sk2", JSON.toJSONString(vo),1000, TimeUnit.SECONDS);

        //读数据
        String jsonStr = stringRedisTemplate.opsForValue().get("sk2");
        DictExcelVo dictExcelVo = JSON.parseObject(jsonStr,DictExcelVo.class);
        System.out.println(dictExcelVo);
    }

    @Test
    void test2() {
        //设置String类型的k-v到redis中 并设置过期时间20分钟
        redisTemplate.opsForValue().set("sk1", "world",20, TimeUnit.MINUTES);
        DictExcelVo vo = new DictExcelVo();
        vo.setId(1L);
        vo.setName("world");

        //redis存对象 转成json字符串
        redisTemplate.opsForValue().set("sk2", vo,1000, TimeUnit.SECONDS);

        //读数据
        DictExcelVo dictExcelVo =(DictExcelVo) redisTemplate.opsForValue().get("sk2");
        System.out.println(dictExcelVo);
    }
}
