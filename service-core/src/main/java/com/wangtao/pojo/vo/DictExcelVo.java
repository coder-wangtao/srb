package com.wangtao.pojo.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

// view object 跟视图交互
// dto 服务端接收的数据
@Data
public class DictExcelVo {
    @ExcelProperty("id")
    @ApiModelProperty(value = "id")
    private Long id;

    @ExcelProperty("上级id")
    @ApiModelProperty(value = "上级id")
    private Long parentId;

    @ExcelProperty("名称")
    @ApiModelProperty(value = "名称")
    private String name;

    @ExcelProperty("值")
    @ApiModelProperty(value = "值")
    private Integer value;

    @ExcelProperty("编码")
    @ApiModelProperty(value = "编码")
    private String dictCode;


}
