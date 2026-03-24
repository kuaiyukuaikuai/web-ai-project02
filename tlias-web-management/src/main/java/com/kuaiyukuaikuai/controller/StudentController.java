package com.kuaiyukuaikuai.controller;

import com.kuaiyukuaikuai.pojo.Student;
import com.kuaiyukuaikuai.pojo.StudentQueryParam;
import com.kuaiyukuaikuai.pojo.Result;
import com.kuaiyukuaikuai.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 学生管理
 */
@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 分页查询学生
     *
     * @return 分页结果
     */
    @GetMapping()
    public Result page(StudentQueryParam studentPageQuery) {
        log.info("分页查询学生参数：{}", studentPageQuery);
        return Result.success(studentService.page(studentPageQuery));
    }

    /**
     * 新增学生
     */
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学生数据：{}", student);
        studentService.save(student);
        return Result.success("学生数据新增成功");
    }

    /**
     * 删除学生
     */
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除学生数据：{}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 根据 id 查询学生
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询学生数据：{}", id);
        return Result.success(studentService.getInfo(id));
    }

    /**
     * 修改学生
     *
     * @param student 学生信息
     */
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学生数据：{}", student);
        studentService.update(student);
        return Result.success("学生数据修改成功");
    }

    /**
     * 查询全部学生
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询全部学生数据");
        return Result.success(studentService.list());
    }

    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学生id：{}", id);
        log.info("学生扣分：{}", score);
        studentService.violation(id, score);
        return Result.success("学生扣分成功");
    }
}
