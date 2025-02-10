package com.wangtao.exception;

import com.wangtao.result.ResponseEnum;
import lombok.Getter;

@Getter
public class SrbException extends RuntimeException {
    private Integer code;
    private String message;

    public SrbException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public SrbException(ResponseEnum responseEnum) {
        super(responseEnum.getMessage());
        this.message = responseEnum.getMessage();
        this.code = responseEnum.getCode();
    }
}
