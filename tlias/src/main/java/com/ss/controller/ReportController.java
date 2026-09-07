package com.ss.controller;

import com.ss.pojo.ClazzOption;
import com.ss.pojo.JobOption;
import com.ss.pojo.Result;
import com.ss.servicer.ReportService;
import com.ss.servicer.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("获取员工职位数据");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("获取员工性别数据");
        List<Map<String,Object>> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }

    @GetMapping("/studentCountData")
    public Result getStudentCountData() {
        log.info("获取学生人数数据");
        ClazzOption clazzOption = reportService.getStudentCountData();
        return Result.success(clazzOption);
    }

    @GetMapping("/studentDegreeData")
    public Result getStudentDegreeData() {
        log.info("获取学生学历数据");
        List<Map<String,Object>> degreeList = reportService.getStudentDegreeData();
        return Result.success(degreeList);
    }
}
