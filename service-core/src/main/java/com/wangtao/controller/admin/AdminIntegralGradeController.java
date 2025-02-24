package com.wangtao.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wangtao.pojo.entity.IntegralGrade;
import com.wangtao.result.ResponseEnum;
import com.wangtao.result.ResponseVo;
import com.wangtao.service.IntegralGradeService;
import com.wangtao.util.SrbAssert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admin/core/integralGrade")
@Api(tags = "积分等级管理模块")
public class AdminIntegralGradeController {
    @Resource
    private IntegralGradeService integralGradeService;


    //分页查询所有的积分等级数据
    @GetMapping("{pageNum}/{pageSize}")
    @ApiOperation("分页查询所有积分等级列表")
    public ResponseVo page(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        return ResponseVo.ok().data("page",integralGradeService.page(new Page<>(pageNum,pageSize)));
    }


    //查询所有的积分等级数据
    @GetMapping
    @ApiOperation("查询所有积分等级列表")
    public ResponseVo list() {
        return ResponseVo.ok().data("items",integralGradeService.list());
    }

    @GetMapping("{id}")
    @ApiOperation("根据id查询积分等级")
    public ResponseVo getById(@ApiParam(value = "积分等级id",type = "long",example = "1") @PathVariable("id") Long id) {
        IntegralGrade integralGrade = integralGradeService.getById(id);
        if(integralGrade==null){
            return ResponseVo.setResult(ResponseEnum.DATA_NOT_EXISTS);
        }
        return ResponseVo.ok().data("item",integralGrade);
    }

    @DeleteMapping("{id}")
    @ApiOperation("根据id删除积分等级")
    public ResponseVo deleteById(@ApiParam("积分等级id") @PathVariable("id") Long id) {
        boolean b = integralGradeService.removeById(id);
        return b ? ResponseVo.ok() : ResponseVo.setResult(ResponseEnum.ERROR);
    }

    //更新
    @PutMapping
    @ApiOperation("根据id更新积分等级")
    public ResponseVo updateById(@RequestBody IntegralGrade integralGrade) {
        SrbAssert.assertNotNull(integralGrade.getId(),ResponseEnum.PARAM_IS_NULL_ERROR);
        boolean b = integralGradeService.updateById(integralGrade);
        return b ? ResponseVo.ok() : ResponseVo.setResult(ResponseEnum.ERROR);
    }

    //新增
    @PostMapping
    @ApiOperation("新增积分等级")
    public ResponseVo save(@RequestBody IntegralGrade integralGrade) {
        boolean b = integralGradeService.save(integralGrade);
        return b ? ResponseVo.ok() : ResponseVo.setResult(ResponseEnum.ERROR);
    }

}
