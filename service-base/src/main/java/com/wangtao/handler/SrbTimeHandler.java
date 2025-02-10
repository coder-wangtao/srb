package com.wangtao.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component  //service-core启动类必须能扫描到当前类 才可以使用自动填充
public class SrbTimeHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        if(metaObject.hasGetter("createTime")){
            metaObject.setValue("createTime", new Date());
        }
        if(metaObject.hasGetter("updateTime")){
            metaObject.setValue("updateTime", new Date());
        }

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(metaObject.hasGetter("updateTime")){
            metaObject.setValue("updateTime", new Date());
        }
    }
}
