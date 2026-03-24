package com.kuaiyukuaikuai.mapper;

import com.kuaiyukuaikuai.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {
    /**
     * 批量保存员工工作经历信息
     */
    void insertBatch(List<EmpExpr> exprList);

    /**
     * 根据员工id删除员工工作经历信息
     */
    void deleteEmpExprByEmpIds(List<Integer> empIds);
}
