package com.ss.servicer;

import com.ss.pojo.OperateLog;
import com.ss.pojo.PageResult;

public interface LogService {
    // 分页查询日志信息
    PageResult<OperateLog> getLog(Integer page, Integer pageSize);
}
