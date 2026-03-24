package com.kuaiyukuaikuai.service;

import com.kuaiyukuaikuai.pojo.Dept;

import java.util.List;

public interface DeptService {
    //查询所有部门
    List<Dept> findAll();

    //根据id查询部门
    Dept findById(Integer id);

    //删除部门
    void delete(Integer id);

    //新增保存部门
    void insert(Dept dept);

    //修改部门
    void update(Dept dept);
}
