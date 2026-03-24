package com.kuaiyukuaikuai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 学生分页查询参数封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentQueryParam {
    
    private Integer page = 1; // 当前页码
    
    private Integer pageSize = 10; // 每页记录数
    
    private String name; // 姓名
    
    private Integer gender; // 性别，1:男，2:女
    
    private String phone; // 手机号
    
    private String no; // 学号
    
    private Integer clazzId; // 班级 ID
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginGraduationDate; // 开始毕业时间
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endGraduationDate; // 结束毕业时间
}
