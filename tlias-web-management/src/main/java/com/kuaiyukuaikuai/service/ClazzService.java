package com.kuaiyukuaikuai.service;


import com.kuaiyukuaikuai.pojo.ClazzQueryParam;
import com.kuaiyukuaikuai.pojo.PageResult;
import com.kuaiyukuaikuai.pojo.Clazz;

import java.util.List;

public interface ClazzService {
    PageResult<Clazz> page(ClazzQueryParam clazzsQueryParam);

    void save(Clazz clazz);

    Clazz getById(Integer id);

    void update(Clazz clazz);

    void delete(Integer id);

    List<Clazz> list();
}
