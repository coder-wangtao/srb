package com.wangtao.util;


import com.wangtao.exception.SrbException;
import com.wangtao.result.ResponseEnum;

public class SrbAssert {
    public static void assertNotNull(Object obj, ResponseEnum responseEnum) {
        if(obj == null){
            //自定义异常类：继承RuntimeException(运行时异常)
            //扩展异常类，鞋带响应的ResponseVo对象需要的code和message值
            throw new SrbException(responseEnum);
        }
    }
}
