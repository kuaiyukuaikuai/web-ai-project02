package com.kuaiyukuaikuai.service.impl;

import com.kuaiyukuaikuai.mapper.EmpLogMapper;
import com.kuaiyukuaikuai.pojo.EmpLog;
import com.kuaiyukuaikuai.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    @Transactional(rollbackFor = {Exception.class}, propagation = Propagation.REQUIRES_NEW)//REQUIRES_NEW需要在新事物中运行
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
