package com.kuaiyukuaikuai.service;

import com.kuaiyukuaikuai.pojo.PageResult;
import com.kuaiyukuaikuai.pojo.Student;
import com.kuaiyukuaikuai.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {

    /**
     * 分页查询学生
     *
     * @param studentQueryParam 查询参数
     * @return 分页结果
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 新增学生
     *
     * @param student 学生数据
     */
    void save(Student student);

    /**
     * 删除学生
     *
     * @param ids 学生 ID 列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据 ID 查询学生信息
     *
     * @param id 学生 ID
     * @return 学生信息
     */
    Student getInfo(Integer id);

    /**
     * 更新学生信息
     *
     * @param student 学生信息
     */
    void update(Student student);

    /**
     * 查询全部学生信息
     *
     * @return 学生列表
     */
    List<Student> list();

    /**
     * 扣分
     *
     * @param id  学生 ID
     * @param score 扣分分数
     */
    void violation(Integer id, Integer score);
}
