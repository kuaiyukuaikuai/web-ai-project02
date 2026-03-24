package com.kuaiyukuaikuai.exception;

import com.kuaiyukuaikuai.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //处理异常
    @ExceptionHandler
    public Result ex(Exception e) {//方法形参中指定能够处理的异常类型
        e.printStackTrace();//打印堆栈中的异常信息
        log.error("捕获到异常：{}", e.getMessage());
        //捕获到异常之后，响应一个标准的Result
        return Result.error("对不起,操作失败,请联系管理员");
    }

    //处理重复数据异常
    @ExceptionHandler
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("捕获到异常：{}", e.getMessage());
        String message = e.getMessage();
        int i= message.lastIndexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr= errMsg.split(" ");
        return Result.error(arr[2]+"已存在");
    }

    // 处理自定义业务异常
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        log.error("捕获到业务异常：{}", e.getMessage());
        return Result.error(e.getMessage());
    }
}