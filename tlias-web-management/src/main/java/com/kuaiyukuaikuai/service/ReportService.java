package com.kuaiyukuaikuai.service;

import com.kuaiyukuaikuai.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();

    /**
     * 统计各个性别的员工人数
     * @return
     */
    List<Map> getEmpGenderData();
}