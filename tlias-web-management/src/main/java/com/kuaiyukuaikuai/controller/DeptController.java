package com.kuaiyukuaikuai.controller;


import com.kuaiyukuaikuai.anno.Log;
import com.kuaiyukuaikuai.pojo.Dept;
import com.kuaiyukuaikuai.pojo.Result;
import com.kuaiyukuaikuai.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    //private static final Logger logger = Logger.getLogger(DeptController.class.getName()); 可以用@Slf4j注解
    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping()
    public Result list() {
        log.info("查询所有部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable Integer id) {
        log.info("查询部门数据" + id);
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    @Log
    @DeleteMapping()
    public Result delete(Integer id) {
        log.info("删除部门数据{}", id);
        deptService.delete(id);
        return Result.success("删除成功");
    }

    @Log
    @PostMapping()//@RequestBody注解：将前端传递的json数据转为java对象
    public Result insert(@RequestBody Dept dept) {
        log.info("保存部门数据" + dept);
        deptService.insert(dept);
        return Result.success("保存成功");
    }

    @Log
    @PutMapping()
    public Result update(@RequestBody Dept dept) {
        log.info("修改部门数据" + dept);
        deptService.update(dept);
        return Result.success("修改成功");
    }
}
