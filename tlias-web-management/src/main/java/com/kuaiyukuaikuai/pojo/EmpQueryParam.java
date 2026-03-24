package com.kuaiyukuaikuai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 员工分页查询参数封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpQueryParam {
    
    private Integer page = 1; // 当前页码
    
    private Integer pageSize = 10; // 每页记录数
    
    private String name; // 姓名
    
    private Integer gender; // 性别，1:男，2:女
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // 开始入职日期
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // 结束入职日期
}
