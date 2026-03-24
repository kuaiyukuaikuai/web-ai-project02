package com.kuaiyukuaikuai.mapper;

import com.kuaiyukuaikuai.pojo.Student;
import com.kuaiyukuaikuai.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     * 分页查询学生
     * @param studentQueryParam 查询参数
     * @return 学生列表
     */
    List<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 查询所有学生
     * @return 学生列表
     */
    List<Student> listAll();

    /**
     * 新增学生基本信息
     * @param student 学生信息
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time) " +
            "values (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{violationCount},#{violationScore},#{createTime},#{updateTime})")
    void insert(Student student);

    /**
     * 根据 id 批量删除学生的基本信息
     * @param ids 学生 ID 列表
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据学生 id 查询信息
     * @param id 学生 ID
     * @return 学生信息
     */
    Student getById(Integer id);

    /**
     * 根据 id 更新学生信息
     * @param student 学生信息
     */
    void updateById(Student student);

    /**
     * 添加学生违规信息
     * @param id 学生 ID
     * @param score 违规分数
     */
    void violation(Integer id, Integer score);

    /**
     * 根据班级 id 统计班级下学生数量
     * @param clazz_id 班级 ID
     * @return 班级下学生数量
     */
    Long countByClazzId(Integer clazz_id);
}
