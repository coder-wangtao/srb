package com.wangtao.handler;

import com.wangtao.exception.SrbException;
import com.wangtao.result.ResponseEnum;
import com.wangtao.result.ResponseVo;
import io.swagger.models.RefResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice   //默认响应结果转化为json
@Slf4j
public class SrbExceptionHandler {

    //Controller接口处理请求时出现异常 由此方法来处理返回结果
    @ExceptionHandler(Exception.class)
    public ResponseVo exception(Exception e){
        log.info(e.getClass().getName());
        log.error("出现异常："+ ExceptionUtils.getStackTrace(e));
        return ResponseVo.error();
    }

    //具体异常处理器
    //BadSqlGrammarException
    @ExceptionHandler(BadSqlGrammarException.class)
    public ResponseVo exception(BadSqlGrammarException e){
        log.info(e.getClass().getName());
        log.error("出现异常："+ ExceptionUtils.getStackTrace(e));
        return ResponseVo.setResult(ResponseEnum.BAD_SQL_GRAMMAR_ERROR);
    }

    //自定义异常处理器
    @ExceptionHandler(SrbException.class)
    public ResponseVo exception(SrbException e){
        log.info(e.getClass().getName());
        log.error("出现异常："+ ExceptionUtils.getStackTrace(e));
        return ResponseVo.error().code(e.getCode()).message(e.getMessage());
    }


    //Controller上一层相关异常
    //springmvc解析请求报文时肯呢个出现异常的处理
    @ExceptionHandler({
            NoHandlerFoundException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMediaTypeNotSupportedException.class,
            MissingPathVariableException.class,
            MissingServletRequestParameterException.class,
            TypeMismatchException.class,
            HttpMessageNotReadableException.class,
            MethodArgumentNotValidException.class,
            HttpMediaTypeNotAcceptableException.class,
            ServletRequestBindingException.class,
            ConversionNotSupportedException.class,
            MissingServletRequestPartException.class,
            AsyncRequestTimeoutException.class
    })
    public ResponseVo handleServletException(Exception e){
        log.error(e.getMessage(),e);
        return ResponseVo.error().message(ResponseEnum.SERVLET_ERROR.getMessage()).code(ResponseEnum.SERVLET_ERROR.getCode());
    }
}
