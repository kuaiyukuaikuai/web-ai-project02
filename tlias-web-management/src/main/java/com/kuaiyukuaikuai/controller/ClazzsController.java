package com.kuaiyukuaikuai.controller;

import com.kuaiyukuaikuai.anno.Log;
import com.kuaiyukuaikuai.pojo.Clazz;
import com.kuaiyukuaikuai.pojo.ClazzQueryParam;
import com.kuaiyukuaikuai.pojo.Result;
import com.kuaiyukuaikuai.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzsController {
    @Autowired
    private ClazzService clazzsService;

    /**
     * 分页查询班级信息
     *
     * @return
     */
    @GetMapping()
    public Result page(ClazzQueryParam clazzsPageQuery) {
        log.info("分页查询班级信息");
        return Result.success(clazzsService.page(clazzsPageQuery));
    }

    /**
     * 添加班级
     *
     * @return
     */
    @Log
    @PostMapping
    public Result save(@RequestBody Clazz clazz) {
        log.info("添加班级信息");
        clazzsService.save(clazz);
        return Result.success();
    }

    /**
     * 根据id查询班级
     *
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据id查询班级信息");
        return Result.success(clazzsService.getById(id));
    }

    /**
     * 修改班级信息
     *
     * @return
     */
    @Log
    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级信息");
        clazzsService.update(clazz);
        return Result.success();
    }

    /**
     * 删除班级信息
     *
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("删除班级信息");
        clazzsService.delete(id);
        return Result.success();
    }

    /**
     * 查询全部班级信息
     *
     * @return
     */
    @GetMapping("/list")
    public Result list(){
        return Result.success(clazzsService.list());
    }
}
