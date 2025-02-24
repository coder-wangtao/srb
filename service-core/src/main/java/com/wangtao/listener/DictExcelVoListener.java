package com.wangtao.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wangtao.mapper.DictMapper;
import com.wangtao.pojo.entity.Dict;
import com.wangtao.pojo.vo.DictExcelVo;
import com.wangtao.service.DictService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Service
public class DictExcelVoListener extends AnalysisEventListener<DictExcelVo> {
//    @Resource
    DictService dictService;

    //如果对象不在容器中，可以通过方法或者构造器接收参数
    public DictExcelVoListener(DictService dictService) {
        this.dictService = dictService;
    }

    private List<DictExcelVo> dictExcelVOS = new ArrayList<>();
    private int limit = 100;

    private List<DictExcelVo> list;

    @Override
    public void invoke(DictExcelVo data, AnalysisContext context) {
        //invoke 方法在每次解析 Excel 文件中的一行数据时被调用。每当读取到一条数据时，invoke
        //会将其传递给方法的参数 data（这里是 DictExcelVo 对象）。这个方法会处理每一行的 Excel 数据，并将其收集到一个列表中。

        dictExcelVOS.add(data);
        if(dictExcelVOS.size()==limit){
            List<Dict> dicts = convertExcelVos2Dicts();
            //保存到数据库
            dictService.saveBatch(dicts);
            dictExcelVOS.clear();
        }
    }


    private List<Dict> convertExcelVos2Dicts(){
        List<Dict> dicts = dictExcelVOS.stream().map(dictExcelVo -> {
            Dict dict = new Dict();
            //将源对象 相同的属性名+数据类型的 属性值 拷贝给目标对象 两个类型必须有set/get方法
            BeanUtils.copyProperties(dictExcelVo, dict);
            return dict;
        }).filter(dict -> {
            return dictService.count(Wrappers.lambdaQuery(Dict.class).eq(Dict::getId, dict.getId())) == 0;
        }).collect(Collectors.toList());
        return dicts;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
//        doAfterAllAnalysed 方法是在解析完成所有数据后（即所有数据都被读取和处理完）调用的。
//        这个方法主要用于处理剩余的未处理数据，或者做一些额外的清理工作。
        if(dictExcelVOS.size() > 0){
            List<Dict> dicts = convertExcelVos2Dicts();
            dictService.saveBatch(dicts);
            dictExcelVOS.clear();
        }
    }
}
