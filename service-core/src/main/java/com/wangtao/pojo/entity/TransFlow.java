package com.wangtao.pojo.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 交易流水表
 * </p>
 *
 * @author wangtao
 * @since 2025-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="TransFlow对象", description="交易流水表")
public class TransFlow implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    private Long id;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "交易单号")
    private String transNo;

    @ApiModelProperty(value = "交易类型（1：充值 2：提现 3：投标 4：投资回款 ...）")
    private Integer transType;

    @ApiModelProperty(value = "交易类型名称")
    private String transTypeName;

    @ApiModelProperty(value = "交易金额")
    private BigDecimal transAmount;

    @ApiModelProperty(value = "备注")
    private String memo;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除(1:已删除，0:未删除)")
    @TableField("is_deleted")
    @TableLogic
    private Boolean deleted;


}
