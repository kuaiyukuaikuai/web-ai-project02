package com.kuaiyukuaikuai.mapper;

import com.kuaiyukuaikuai.pojo.Emp;
import com.kuaiyukuaikuai.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    //---------------------------原始分页查询---------------------------------
/*    //查询总记录数
    @Select("select count(*) from emp e left join dept d on e.dept_id=d.id")
    public Long count();

    //分页查询
    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id " +
            "order by e.update_time desc limit #{start},#{pageSize}")
    public List<Emp> list(Integer start, Integer pageSize);*/

    //使用MyBatis的分页插件进行分页查询
/*    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id " +
            "order by e.update_time desc")*/

    /**
     * 分页查询
     * @param empQueryParam
     * @return
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    //查询所有员工
    @Select("select * from emp")
    List<Emp> findAll();

    /**
     * 新增员工基本信息
     * @param emp
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")//获取数据库生成的主键 -- mybatis的主键返回
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    /**
     * 根据id批量删除员工的基本信息
     * @param ids
     */
    void deleteEmpByIds(List<Integer> ids);

    /**
     * 根据员工id查询信息
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 根据id更新员工信息
     * @param emp
     */
    void updateById(Emp emp);

    /**
     * 统计各个职位的员工人数
     * @return
     */

    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别信息
     */
    List<Map> countEmpGenderData();

    /**
     * 根据用户名和密码查询员工信息
     * @param emp
     * @return
     */
    @Select("select * from emp where username = #{username} and password = #{password}")
    Emp getUsernameAndPassword(Emp emp);
}