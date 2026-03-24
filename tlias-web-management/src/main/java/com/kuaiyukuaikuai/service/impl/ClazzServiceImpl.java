package com.kuaiyukuaikuai.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kuaiyukuaikuai.exception.BusinessException;
import com.kuaiyukuaikuai.mapper.ClazzMapper;
import com.kuaiyukuaikuai.mapper.StudentMapper;
import com.kuaiyukuaikuai.pojo.Clazz;
import com.kuaiyukuaikuai.pojo.ClazzQueryParam;
import com.kuaiyukuaikuai.pojo.PageResult;
import com.kuaiyukuaikuai.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        //2.执行查询
        List<Clazz> rows = clazzMapper.list(clazzQueryParam);
        //3.进行时间判断将Clazz类中的status进行赋值
        for (Clazz clazz : rows) {
            if (clazz.getEndDate().isBefore(LocalDate.now())) {
                clazz.setStatus("已结课");
            } else if (clazz.getBeginDate().isBefore(LocalDate.now()) && clazz.getEndDate().isAfter(LocalDate.now())) {
                clazz.setStatus("进行中");
            } else if (clazz.getBeginDate().isAfter(LocalDate.now())) {
                clazz.setStatus("未开始");
            }
        }
        //4.封装PageResult对象并返回
        Page<Clazz> p = (Page<Clazz>) rows;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Clazz clazz) {
        //1.设置时间
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //2.保存班级信息
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(Integer id) {
        // 检查是否有学生关联
        Long count = studentMapper.countByClazzId(id);
        if (count > 0) {
            throw new BusinessException("该班级下有学生，无法删除");
        }
        clazzMapper.deleteById(id);
}

    @Override
    public List<Clazz> list() {
        return clazzMapper.listAll();
    }
}
