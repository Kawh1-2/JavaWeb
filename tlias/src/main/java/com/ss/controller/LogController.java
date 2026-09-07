package com.ss.controller;

import com.ss.pojo.Result;
import com.ss.servicer.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志查询
 */
@Slf4j
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    // 分页查询日志信息
    @GetMapping("/log/page")
    public Result logPage(Integer page, Integer pageSize) {
        log.info("日志分页查询，参数：{}，{}", page, pageSize);
        return Result.success(logService.getLog(page, pageSize));
    }
}
