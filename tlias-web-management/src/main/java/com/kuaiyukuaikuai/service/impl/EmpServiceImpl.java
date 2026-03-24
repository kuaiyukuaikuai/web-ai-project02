package com.kuaiyukuaikuai.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.kuaiyukuaikuai.mapper.EmpExprMapper;
import com.kuaiyukuaikuai.mapper.EmpLogMapper;
import com.kuaiyukuaikuai.mapper.EmpMapper;
import com.kuaiyukuaikuai.pojo.*;
import com.kuaiyukuaikuai.pojo.EmpQueryParam;
import com.kuaiyukuaikuai.service.EmpLogService;
import com.kuaiyukuaikuai.service.EmpService;
import com.kuaiyukuaikuai.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 员工管理
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;


//-------------------原始分页查询--------------------------------
    /*    */

    /**
     * 分页查询员工数据
     *
     * @param page
     * @param pageSize
     * @return
     *//*
    @Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //1,调用mapper接口,查询总记录数
        Long total = empMapper.count();

        //2.调用mapper接口,查询结果列表
        Integer start = (page - 1) * pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);

        //3.封装PageResult对象并返回
        return new PageResult<>(total, rows);
    }*/
    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1.设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2.执行查询
        List<Emp> empList = empMapper.list(empQueryParam);

        //3.解析查询结果，封装为 PageResult 对象并返回
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})//事务管理,出现异常回滚,默认出现异常RuntimeException才回滚
    @Override
    public void save(Emp emp) {
        try {
            //1.保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //2. 保存员工的工作经历信息 - 批量
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //3. 保存员工日志信息
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工" + emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        try {
            //1.批量删除员工基本信息
            empMapper.deleteEmpByIds(ids);
            //2.批量删除员工工作经历信息
            empExprMapper.deleteEmpExprByEmpIds(ids);
        } finally {
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "删除员工" + ids);
            empLogService.insertLog(empLog);
        }
    }

    @Override
    public Emp getInfo(Integer id) {
        Emp emp = empMapper.getById(id);
        return emp;
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.根据id更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2.根据员工id删除员工工作经历信息
        //2.1 先根据员工id删除原有的员工工作经历信息
        empExprMapper.deleteEmpExprByEmpIds(List.of(emp.getId()));
        //2.2 批量插入新的员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if (!CollectionUtils.isEmpty(exprList)) {
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> list() {
        // 查询全部员工数据
        return empMapper.findAll();
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if(empLogin != null){
            //1. 生成JWT令牌
            Map<String,Object> dataMap = new HashMap<>();
            dataMap.put("id", empLogin.getId());
            dataMap.put("username", empLogin.getUsername());

            String jwt = JwtUtils.generateJwt(dataMap);
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
            return loginInfo;
        }
        return null;
    }
}