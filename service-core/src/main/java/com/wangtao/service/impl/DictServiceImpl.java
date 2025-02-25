package com.wangtao.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wangtao.exception.SrbException;
import com.wangtao.listener.DictExcelVoListener;
import com.wangtao.pojo.entity.Dict;
import com.wangtao.mapper.DictMapper;
import com.wangtao.pojo.vo.DictExcelVo;
import com.wangtao.result.ResponseEnum;
import com.wangtao.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.record.DVALRecord;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author wangtao
 * @since 2025-02-07
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Resource
    RedisTemplate redisTemplate;

    @Override
    public void importDict(MultipartFile file) {
        if(!(file.getOriginalFilename().endsWith(".xls") || file.getOriginalFilename().endsWith(".xlsx"))){
            throw new SrbException(-107, "上传文件必须为excel");
        }

        if(file.getSize() == 0){
            throw new SrbException(-107, "上传内容为空");
        }

        try {
            EasyExcel.read(file.getInputStream()).sheet()
                    .head(DictExcelVo.class).registerReadListener(new DictExcelVoListener(this)).doRead();
        } catch (IOException e) {
            e.printStackTrace();
            throw new SrbException(ResponseEnum.UPLOAD_ERROR);
        }
    }



    @Override
    public List<Dict> parent(Long pid) {
        //先查询缓存
        List<Dict> dicts = (List<Dict>) redisTemplate.opsForValue().get("srb:dicts:"+pid);
        if(!CollectionUtils.isEmpty(dicts)){
            return dicts;
        }

        //无缓存
        //select * from dict where parent_id = pid
        dicts = this.list(Wrappers.lambdaQuery(Dict.class).eq(Dict::getParentId, pid));
        if(CollectionUtils.isEmpty(dicts)){
            return null;
        }
        dicts.forEach(dict -> {
            //select count(*) from dict where parent_id = 一级数据字典的id
            boolean flag = hasChildren(dict.getId());
            dict.setHasChildren(flag);
        });

        //存到redis中
        redisTemplate.opsForValue().set("srb:dicts:"+pid, dicts);
        return dicts;
    }


    public boolean hasChildren(Long id) {
        return this.count(Wrappers.lambdaQuery(Dict.class).eq(Dict::getParentId, id)) > 0;
    }
}
