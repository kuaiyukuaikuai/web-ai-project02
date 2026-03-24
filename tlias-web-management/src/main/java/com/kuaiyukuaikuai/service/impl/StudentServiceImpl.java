package com.kuaiyukuaikuai.service.impl;

import com.github.pagehelper.PageHelper;
import com.kuaiyukuaikuai.mapper.StudentMapper;
import com.kuaiyukuaikuai.pojo.PageResult;
import com.kuaiyukuaikuai.pojo.Student;
import com.kuaiyukuaikuai.pojo.StudentQueryParam;
import com.kuaiyukuaikuai.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Student> page(StudentQueryParam studentQueryParam) {
        // 1. 设置分页参数
        PageHelper.startPage(studentQueryParam.getPage(), studentQueryParam.getPageSize());
        
        // 2. 执行分页查询
        List<Student> studentList = studentMapper.list(studentQueryParam);
        
        // 3. 封装分页结果
        return new PageResult<>((long) studentList.size(), studentList);
    }

    @Override
    public void save(Student student) {
        // 1. 设置创建时间和修改时间
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        
        // 2. 设置违规记录和分数的默认值（如果为 null）
        if (student.getViolationCount() == null) {
            student.setViolationCount((short) 0);
        }
        if (student.getViolationScore() == null) {
            student.setViolationScore((short) 0);
        }
        
        // 3. 调用 Mapper 插入
        studentMapper.insert(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.deleteByIds(ids);
    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        // 1. 设置修改时间
        student.setUpdateTime(LocalDateTime.now());
        
        // 2. 调用 Mapper 更新
        studentMapper.updateById(student);
    }

    @Override
    public List<Student> list() {
        return studentMapper.listAll();
    }

    @Override
    public void violation(Integer id, Integer score) {
        studentMapper.violation(id, score);
    }
}
