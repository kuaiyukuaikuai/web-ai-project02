package com.kuaiyukuaikuai.service;

import com.kuaiyukuaikuai.pojo.Emp;
import com.kuaiyukuaikuai.pojo.EmpQueryParam;
import com.kuaiyukuaikuai.pojo.LoginInfo;
import com.kuaiyukuaikuai.pojo.PageResult;

import java.util.List;

public interface EmpService {

    /**
     * 分页查询
     *
     * @param empQueryParam 查询参数
     * @return 分页结果
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     *
     * @param emp 员工数据
     */
    void save(Emp emp);

    /**
     * 删除员工
     *
     * @param ids 员工ID列表
     */
    void delete(List<Integer> ids);

    /**
     * 根据ID查询员工信息
     *
     * @param id 员工ID
     * @return 员工信息
     */
    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     *
     * @param emp 员工信息
     */
    void update(Emp emp);

    /**
     * 查询全部员工信息
     *
     * @return 员工列表
     */
    List<Emp> list();

    /**
     * 员工登录
     *
     * @param emp 员工信息
     * @return 登录信息
     */
    LoginInfo login(Emp emp);
}
