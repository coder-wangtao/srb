package com.wangtao.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@ApiModel("统一响应")
public class ResponseVo {
    @ApiModelProperty("状态码")
    private Integer code;
    @ApiModelProperty("描述")
    private String message;
    @ApiModelProperty("响应数据")
    private Map<String,Object> data = new HashMap<>();

    //成功
    public static ResponseVo ok(Map<String,Object> data) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseEnum.SUCCESS.getCode());
        responseVo.setMessage(ResponseEnum.SUCCESS.getMessage());
        responseVo.setData(data);
        return responseVo;
    }

    public static ResponseVo ok() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseEnum.SUCCESS.getCode());
        responseVo.setMessage(ResponseEnum.SUCCESS.getMessage());
        return responseVo;
    }


    //失败
    public static ResponseVo error() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(ResponseEnum.ERROR.getCode());
        responseVo.setMessage(ResponseEnum.ERROR.getMessage());
        return responseVo;
    }

    public ResponseVo data(String key,Object val) {
        this.data.put(key, val);
        return this;
    }

    public ResponseVo message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResponseVo code(Integer code) {
        this.setCode(code);
        return this;

    }

    public static ResponseVo setResult(ResponseEnum responseEnum) {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(responseEnum.getCode());
        responseVo.setMessage(responseEnum.getMessage());
        return responseVo;
    }
}
