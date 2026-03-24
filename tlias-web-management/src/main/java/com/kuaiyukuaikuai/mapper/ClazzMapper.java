package com.kuaiyukuaikuai.mapper;

import com.kuaiyukuaikuai.pojo.Clazz;
import com.kuaiyukuaikuai.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 分页查询
     * @param clazzQueryParam
     * @return
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 查询全部班级信息
     * @return
     */
    List<Clazz> listAll();

    /**
     * 新增班级
     * @param clazz
     */
    void insert(Clazz clazz);

    /**
     * 根据id查询班级信息
     * @param id
     * @return
     */
    Clazz getById(Integer id);

    /**
     * 根据id更新班级信息
     * @param clazz
     */
    void updateById(Clazz clazz);

    /**
     * 根据id删除班级信息
     * @param id
     */
    void deleteById(Integer id);
}
