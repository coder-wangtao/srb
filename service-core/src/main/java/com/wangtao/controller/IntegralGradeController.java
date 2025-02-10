package com.wangtao.controller;


import com.wangtao.pojo.entity.IntegralGrade;
import com.wangtao.service.IntegralGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 积分等级表 前端控制器
 * </p>
 *
 * @author wangtao
 * @since 2025-02-07
 */
@RestController
@RequestMapping("/integralGrade")
@Api(tags = "积分等级管理模块")
public class IntegralGradeController {
    @Resource
    private IntegralGradeService integralGradeService;

    //查询所有的积分等级数据
    @GetMapping
    @ApiOperation("查询所有积分等级列表")
    public List<IntegralGrade> list() {
        return integralGradeService.list();
    }

    @GetMapping("{id}")
    @ApiOperation("根据id查询积分等级")
    public IntegralGrade getById(@ApiParam("积分等级id") @PathVariable("id") Long id) {
        return integralGradeService.getById(id);
    }

}

