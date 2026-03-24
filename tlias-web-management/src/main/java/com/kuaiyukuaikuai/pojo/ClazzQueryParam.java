package com.kuaiyukuaikuai.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClazzQueryParam {
    
    private String name;      // 班级名称（可选，模糊查询）

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;  // 结课开始时间（可选）
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;    // 结课结束时间（可选）

    private Integer page=1;     // 页码（必填，默认 1）
    private Integer pageSize=10; // 每页记录数（必填，默认 10）
}
