package com.ss.servicer.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ss.mapper.OperateLogMapper;
import com.ss.pojo.OperateLog;
import com.ss.pojo.PageResult;
import com.ss.servicer.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> getLog(Integer page, Integer pageSize) {
        //设置分页参数（PageHelper会自动拼接limit并执行count语句）
        PageHelper.startPage(page, pageSize);
        //查询日志列表，返回的是Page对象
        List<OperateLog> logList = operateLogMapper.list();
        Page<OperateLog> p = (Page<OperateLog>) logList;
        //封装分页结果
        return new PageResult<>(p.getTotal(), p.getResult());
    }
}
