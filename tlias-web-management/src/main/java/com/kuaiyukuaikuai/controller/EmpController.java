package com.kuaiyukuaikuai.controller;

import com.kuaiyukuaikuai.pojo.Emp;
import com.kuaiyukuaikuai.pojo.EmpQueryParam;
import com.kuaiyukuaikuai.pojo.Result;
import com.kuaiyukuaikuai.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * 员工管理
 */
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     *
     * @return
     */
    @GetMapping()
    public Result page(EmpQueryParam empPageQuery) {
        log.info("分页查询参数：{}", empPageQuery);
        return Result.success(empService.page(empPageQuery));
    }

    /**
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工数据：{}", emp);
        empService.save(emp);
        return Result.success("员工数据新增成功");
    }

    /**
     * 删除员工
     */
    @DeleteMapping()
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工数据：{}", ids);
        empService.delete(ids);
        return Result.success();
    }

    /**
     * 根据id查询员工
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询员工数据：{}", id);
        return Result.success(empService.getInfo(id));
    }

    /**
     * 修改员工
     *
     * @param emp
     */
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工数据：{}", emp);
        empService.update(emp);
        return Result.success("员工数据修改成功");
    }

    /**
     * 查询全部员工
     */
    @GetMapping("/list")
    public Result list() {
        log.info("查询全部员工数据");
        return Result.success(empService.list());
    }
}